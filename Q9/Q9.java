/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author HP
 */
public class Q9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // arrs init
        
        int i,j,attractiveness,intelligence,budget,min_attraction,min_budget,criterion,price,value,amt,sum,couples=0,temp,k;
        RGENERATOR r = new RGENERATOR();
        r.create_boys();
        r.create_girls();
        r.create_gifts();
        Random R = new Random();
        k = R.nextInt(20)+ 1;
        String name,str = "",type;
        BOY[] barr = new BOY[100];
        GIRL[] garr = new GIRL[50]; 
        GIFT[] giarr = new GIFT[50];
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
                //System.out.println(barr[i]);
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
                garr[i] = new GIRL(name,attractiveness,min_budget,intelligence,criterion,type,k);
                //System.out.println(garr[i]);
                i++;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("gifts.csv"));
            String line = null;
            i =  0;
            while((line = reader.readLine()) != null)
            {
                String[] gift = line.split(" ");
                type = gift[0];
                price =Integer.parseInt(gift[1]);
                value = Integer.parseInt(gift[2]);
                giarr[i] = new GIFT(type,price,value);
                //System.out.println(giarr[i]);
                i++;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        //arrs init done
        
        i = 0;
        while(i < 50)
        {
            for(j = 0; j < 100; j++)
            {
                if(garr[i].select(barr[j],j) && barr[j].gf == null && garr[i].bf == null)
                {
                    couples++;
                    garr[i].bf = barr[j];
                    barr[j].gf = garr[i];
                    str = str + garr[i].Name + " is committed to " + barr[j].Name + "\n";
                    break;
                }
            }
            i++;
        }
        for(i = 0;i < 50; i++)
        {
            if(garr[i].bf == null)
                str = str + garr[i].Name + " is single\n";
        }
        
        try
        {
            FileWriter writer = new FileWriter(new File("couples.csv"));
            writer.write(str);
            writer.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        
        //calc happiness
        HAPPINESS h = new HAPPINESS();
        h.happiness(barr, garr, giarr,k);
        //calc happiness done
        
        // t happy couples
        int t;
        if(couples > 0)
            t = R.nextInt(couples) + 1;
        else 
            t = -1;
        int[] couplehappiness = new int[couples];
   int[] index = new int[50];
   j = 0;
   for(i = 0; i < 50 && j < couples; i++)
   {
       if(garr[i].bf != null)
       {
           couplehappiness[j] = garr[i].Happiness + garr[i].bf.Happiness;
           index[j++] = i;
       }  
   }
   for(i = 0; i < couples; i++)
   {
       for(j = 0; j < couples - 1 -i; j++)
       {
           if(couplehappiness[j] < couplehappiness[j+1])
           {
               temp = couplehappiness[j];
               couplehappiness[j] = couplehappiness[j+1];
               couplehappiness[j+1] = temp;
               
               temp = index[j];
               index[j] = index[j+1];
               index[j+1] = temp;
           }
       }
   }
    try
        {
            str = "";
            String t1 = new SimpleDateFormat("dd.mm.yyyy").format(new Date());
            String t2 = new SimpleDateFormat("hh.mm.ss").format(new Date());
            FileWriter writer = new FileWriter(new File("k_happiest_couples.csv"));
            for(i = 0; i < t; i++)
                str = str + garr[index[i]].Name + " is happy couple with " + garr[index[i]].bf.Name + " on " + t1 + " " + t2 + "\n";
            writer.write(str);
            writer.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }    
        
        
    }
    
}
