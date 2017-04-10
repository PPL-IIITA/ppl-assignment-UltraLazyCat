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
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Q8 {

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
        while(i < 50)
        {
            for(j = 0; j < 100; j++)
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
        
        Random rand = new Random();
        k = rand.nextInt(20)+1;
        int[] couplehappiness = new int[couples];
        int[] index = new int[50];
        HAPPINESS h = new HAPPINESS();
        NEW_HAPPINESS nh = new NEW_HAPPINESS();
        Scanner sc = new Scanner(System.in);
        int choice = 1;
        while(choice == 1 || choice == 2) {
        System.out.println("Implementation 1.Default Gifting 2.New Gifting 3.exit");
        choice = sc.nextInt();
        if(choice == 2)
        {
            h.happiness(barr, garr, giarr);
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
        
        }
        else
        {
            nh.happiness(barr, garr, giarr);
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
            for(i = 0; i < k; i++)
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
   
    }
    
}
