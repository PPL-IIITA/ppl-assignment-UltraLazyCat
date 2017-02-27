/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package q2;

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
public class Q2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int i,j,attractiveness,intelligence,budget,min_attraction,min_budget,criterion,price,value,amt,sum,couples=0,temp,k;
        RGENERATOR r = new RGENERATOR();
        r.create_boys();
        r.create_girls();
        r.create_gifts();
        String name,str = "",type;
        BOY[] barr = new BOY[50];
        GIRL[] garr = new GIRL[25]; 
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
                i++;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
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
        
        i = 0;
        while(i < 25)
        {
            for(j = 0; j < 50; j++)
            {
                if(garr[i].select(barr[j]) && barr[j].gf == null && garr[i].bf == null)
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
        for(i = 0;i < 25; i++)
        {
            if(garr[i].bf == null)
                str = str + garr[i].Name + " is single\n";
        }
        
        for(i = 0; i < 50; i++)
        {
            sum = 0;
            if(barr[i].gf != null)
            {
                if(barr[i].Type.equals("Geek"))
                {
                    
                    if(barr[i].gf.Type == "Choosy")
                    {
                        j = 0;
                        amt = barr[i].gf.M_cost;
                        while(sum <= amt && j < 50)
                        {
                            if(giarr[j].Type.equals("Luxury"))
                                sum = sum + 2 * giarr[j].Price;
                            else
                                sum = sum + 2 * giarr[j].Price;
                            j++;
                        }
                        while(j < 50)
                        {
                            if(giarr[j].Type.equals("Luxury") && (barr[i].Budget - sum) > giarr[j].Price)
                                sum = sum + 2 * giarr[j].Price;
                            j++;
                        }
                        barr[i].gf.Happiness = (int) Math.log((double)sum);
                        barr[i].Intelligence =barr[i].gf.Intelligence;
                    }
                    else if(barr[i].gf.Type == "Normal")
                    {
                        j = 0;
                        amt = barr[i].gf.M_cost;
                        while(sum <= amt && j < 50)
                        {
                            sum = sum + giarr[j].Price+giarr[j].Value;
                            j++;
                        }
                        while(j < 50)
                        {
                            if(giarr[j].Type.equals("Luxury") && (barr[i].Budget - sum) > giarr[j].Price)
                                sum = sum + giarr[j].Price+giarr[j].Value;
                            j++;
                        }
                        barr[i].gf.Happiness = sum;
                        barr[i].Intelligence =barr[i].gf.Intelligence;
                    }
                    else if(barr[i].gf.Type == "Desperate")
                    {
                        j = 0;
                        amt = barr[i].gf.M_cost;
                        while(sum <= amt && j < 50)
                        {
                            sum = sum + giarr[j].Price;
                            j++;
                        }  
                        while(j < 50)
                        {
                            if(giarr[j].Type.equals("Luxury") && (barr[i].Budget - sum) > giarr[j].Price)
                                sum = sum + giarr[j].Price;
                            j++;
                        }
                        barr[i].gf.Happiness = (int) Math.exp((double)sum);
                        barr[i].Intelligence =barr[i].gf.Intelligence;
                }
                else if(barr[i].equals("Generous"))
                {
                    if(barr[i].gf.Type == "Choosy")
                    {
                        j = 49;
                        amt = barr[i].Budget;
                        while(sum <= amt && j >= 0)
                        {
                            if(giarr[j].Type.equals("Luxury"))
                                sum = sum + 2 * giarr[j].Price;
                            else
                                sum = sum + 2 * giarr[j].Price;
                            j--;
                        }
                        barr[i].gf.Happiness = (int) Math.log((double)sum);
                        barr[i].Happiness = barr[i].gf.Happiness;
                    }
                    else if(barr[i].gf.Type == "Normal")
                    {
                        j = 49;
                        amt = barr[i].Budget;
                        while(sum <= amt && j >= 0)
                        {
                            sum = sum + giarr[j].Price + giarr[j].Value;
                            j--;
                        }
                        barr[i].gf.Happiness = sum;
                        barr[i].Happiness = barr[i].gf.Happiness;
                    }
                    else if(barr[i].gf.Type == "Desperate")
                    {
                        j = 49;
                        amt = barr[i].Budget;
                        while(sum <= amt && j >= 0)
                        {
                            sum = sum + giarr[j].Price;
                            j--;
                        }
                        barr[i].gf.Happiness = (int) Math.exp((double)sum);
                        barr[i].Happiness = barr[i].gf.Happiness;
                    }
                }
                else
                {
                    if(barr[i].gf.Type == "Choosy")
                    {
                        j = 0;
                        amt = barr[i].gf.M_cost;
                        while(sum <= amt && j < 50)
                        {
                            if(giarr[j].Type.equals("Luxury"))
                                sum = sum + 2 * giarr[j].Price;
                            else
                                sum = sum + 2 * giarr[j].Price;
                            j++;
                        }
                        barr[i].gf.Happiness = (int) Math.log((double)sum);
                        barr[i].Happiness = barr[i].Budget - sum;
                    }
                    else if(barr[i].gf.Type == "Normal")
                    {
                        j = 0;
                        amt = barr[i].gf.M_cost;
                        while(sum <= amt && j < 50)
                        {
                            sum = sum + giarr[j].Price + giarr[j].Value;
                            j++;
                        }
                        barr[i].gf.Happiness = sum;
                        barr[i].Happiness = barr[i].Budget - sum;
                    }
                    else if(barr[i].gf.Type == "Desperate")
                    {
                        j = 0;
                        amt = barr[i].gf.M_cost;
                        while(sum <= amt && j < 50)
                        {
                            sum = sum + giarr[j].Price;
                            j++;
                        }
                        barr[i].gf.Happiness = (int) Math.exp((double)sum);
                        barr[i].Happiness = barr[i].Budget - sum;
                    }
                }
           }
       } 
   } 
    
   Random rand = new Random();
   k = rand.nextInt(10)+1;
   int[] couplehappiness = new int[couples];
   int[] index = new int[25];
   j = 0;
   for(i = 0; i < 25 && j < couples; i++)
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
   int[] compatibility = new int[couples];
   int[] index1 = new int[25];
   j = 0;
   for(i = 0; i < 25 && j < couples; i++)
   {
       if(garr[i].bf != null)
       {
           couplehappiness[j] = Math.abs(garr[i].bf.Budget - garr[i].M_cost) + Math.abs(garr[i].Attractiveness - garr[i].bf.Attractiveness) + Math.abs(garr[i].Intelligence - garr[i].bf.Intelligence);
           index1[j++] = i;
       }  
   }
        
   for(i = 0; i < couples; i++)
   {
       for(j = 0; j < couples - 1 -i; j++)
       {
           if(compatibility[j] < compatibility[j+1])
           {
               temp = compatibility[j];
               compatibility[j] = compatibility[j+1];
               compatibility[j+1] = temp;
               
               temp = index1[j];
               index1[j] = index1[j+1];
               index1[j+1] = temp;
           }
       }
   }
   
        try
        {
            str = "";
            String t1 = new SimpleDateFormat("dd.mm.yyyy").format(new Date());
            String t2 = new SimpleDateFormat("hh.mm.ss").format(new Date());
            FileWriter writer = new FileWriter(new File("happy_couples.csv"));
            for(i = 0; i < k; i++)
                str = str + garr[index[i]].Name + " is happy couple with " + garr[index[i]].bf.Name + " on " + t1 + " " + t2 + "\n";
            writer.write(str);
            writer.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        try
        {
            str = "";
            String t1 = new SimpleDateFormat("dd.mm.yyyy").format(new Date());
            String t2 = new SimpleDateFormat("hh.mm.ss").format(new Date());
            FileWriter writer = new FileWriter(new File("compatible_couples.csv"));
            for(i = 0; i < k; i++)
                str = str + garr[index1[i]].Name + " is happy compatible with " + garr[index1[i]].bf.Name + " on "+ t1 +" " + t2 + "\n";
            writer.write(str);
            writer.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
