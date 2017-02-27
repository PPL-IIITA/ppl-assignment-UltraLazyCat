/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package ppl_assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author HP
 */
public class Q1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int i,j,attractiveness,intelligence,budget,min_attraction,min_budget,criterion;
        RGENERATOR r = new RGENERATOR();
        r.create_boys();
        r.create_girls();
        String name,str = "",type;
        BOY[] barr = new BOY[50];
        GIRL[] garr = new GIRL[25]; 
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("boys.csv"));
            String line = null;
            i =  0;
            while((line = reader.readLine()) != null)
            {
                String[] boy = line.split(" ");
                name = boy[0];
                attractiveness = Integer.parseInt(boy[1]);
                intelligence = Integer.parseInt(boy[2]);
                budget = Integer.parseInt(boy[3]);
                min_attraction = Integer.parseInt(boy[4]);
                type = boy[5];
                barr[i] = new BOY(name,attractiveness,intelligence,budget,min_attraction,type);
                i++;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("girls.csv"));
            String line = null;
            i =  0;
            while((line = reader.readLine()) != null)
            {
                String[] girl = line.split(" ");
                name = girl[0];
                attractiveness = Integer.parseInt(girl[1]);
                min_budget = Integer.parseInt(girl[2]);
                intelligence = Integer.parseInt(girl[3]);
                criterion = Integer.parseInt(girl[4]);
                type = girl[5];
                garr[i] = new GIRL(name,attractiveness,min_budget,intelligence,criterion,type);
                i++;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        i = 0;
        while(i < 25)
        {
            for(j = 0; j < 50; j++)
            {
                if(garr[i].select(barr[j]) && barr[j].gf == null && garr[i].bf == null)
                {
                    garr[i].bf = barr[j];
                    barr[j].gf = garr[i];
                    str = str + garr[i].Name + " is committed to " + barr[j].Name + "\n";
                    break;
                }
            }
            i++;
        }
        for(i = 0;i < 25; i++)
        {
            if(garr[i].bf == null)
                str = str + garr[i].Name + " is single\n";
        }
        try
        {
            FileWriter writer = new FileWriter(new File("Committed.csv"));
            writer.write(str);
            writer.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
}
