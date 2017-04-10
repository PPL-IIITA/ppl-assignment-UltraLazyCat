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
public class Q6 {

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
                garr[i] = new GIRL(name,attractiveness,min_budget,intelligence,criterion,type);
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
        
        for(i = 0; i < 50; i++)
        {
            for(j = 0; j < 50-1-i; j++)
            {
                if(giarr[j].Price > giarr[j+1].Price)
                {
                    price = giarr[j].Price;
                    giarr[j].Price = giarr[j+1].Price;
                    giarr[j+1].Price = price;
                    
                    value = giarr[j].Value;
                    giarr[j].Value = giarr[j+1].Value;
                    giarr[j+1].Value = value;
                    
                    type = giarr[j].Type;
                    giarr[j].Type = giarr[j+1].Type;
                    giarr[j+1].Type = type;
                }
            }
        }
        // commit
        
        String t1 = new SimpleDateFormat("dd.mm.yyyy").format(new Date());
            String t2 = new SimpleDateFormat("hh.mm.ss").format(new Date());
        i = 0;
        while(i < 50)
        {
            for(j = 0; j < 100; j++)
            {
                if(garr[i].select(barr[j]) && barr[j].gf == null && garr[i].bf == null)
                {
                    couples++;
                    garr[i].bf = barr[j];
                    barr[j].gf = garr[i];
                    str = str + garr[i].Name + " is committed to " + barr[j].Name + " on "+ t1 + " " + t2 + "\n";
                    break;
                }
            }
            i++;
        }
        for(i = 0;i < 50; i++)
        {
            if(garr[i].bf == null)
                str = str + garr[i].Name + " is single on " + t1 + " " + t2 + " \n" ;
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
        //commit done
        
        //Calculate Happiness
        HAPPINESS h = new HAPPINESS(); 
        h.happiness(barr,garr,giarr);
        //Calculate Happiness
        //
        Random rand = new Random();
        k = rand.nextInt(20)+1;
        int t = rand.nextInt(5)+1;
        int[] couplehappiness = new int[couples];
        int[] index = new int[50];
        //
        for(int p = 0; p < t; p++)
        {
            // break up
            j = 0;
   for(i = 0; i < 50 && j < couples; i++)
   {
       if(garr[i].bf != null)
       {
           couplehappiness[j] = garr[i].Happiness + garr[i].bf.Happiness;
           //System.out.println("gh = "+garr[i].Happiness+" "+ "bh = "+garr[i].bf.Happiness);
           index[j++] = i;
       }  
   }
   for(i = 0; i < couples; i++)
   {
       for(j = 0; j < couples - 1 - i; j++)
       {
           if(couplehappiness[j] > couplehappiness[j+1]) //change to desc
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
            for(i = 0; i < k; i++)
            {
                str = str + garr[index[i]].Name + " is Break-up with " + garr[index[i]].bf.Name +  t1 + " on " + t2 + "\n";
                garr[index[i]].bf.gf = null;
                garr[index[i]].bf = null;
            }
            
            // break up done
            
            // next coupling
            i = 49;
        while(i >= 0)
        {
            if(garr[i].bf != null){
                str = str + garr[i].Name + " is committed to " + garr[i].bf.Name + " " +t1 + " on "+ t2+"\n";
                //System.out.println(garr[i].Name + " is committed to " + garr[i].bf.Name);
            }
            else {
            for(j = 99; j >= 0; j--)
            {
                if(garr[i].select(barr[j]) && barr[j].gf == null && garr[i].bf == null)
                {
                    //couples++;
                    garr[i].bf = barr[j];
                    barr[j].gf = garr[i];
                    str = str + garr[i].Name + " is committed to " + barr[j].Name + " " + t1 + " on "+ t2 +"\n";
                    //System.out.println(garr[i].Name + " is committed to " + barr[j].Name);
                    break;
                }
            }
            }
            i--;
        }
        //System.out.println("outta while");
        for(i = 0;i < 50; i++)
        {
            if(garr[i].bf == null)
                str = str + garr[i].Name + " is single" + " " + t1 + " on " + t2 + "\n";
        }
        // next coupling done
        
        }
        try
        {
            FileWriter writer = new FileWriter(new File("couples & breakups in t_days.csv"));
            writer.write(str);
            writer.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
    
}
