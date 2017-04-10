/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Q7 {

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
        BOY[] barr = new BOY[100];
        GIRL[] garr = new GIRL[50]; 
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
        i = 0;
        while(i < 50)
        {
            for(j = 0; j < 100; j++)
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
        
        //start
        
//        System.out.println("Enter the number of boys in list");
//        Scanner sc = new Scanner(System.in);
//        int num  = sc.nextInt();
//        int[] boys = new int[num];
//        Random rand = new Random();
//        for(i = 0; i < num; i++)
//            boys[i] = rand.nextInt(20) + 1;
//        System.out.println("BOY LIST");
//        for(i = 0; i < num; i++)
//            System.out.println("boy " + boys[i]);
//        System.out.println("Choose implementation 1. array 2. Binary search 3. hash table");
//        int choice = sc.nextInt();
//        if(choice == 2)
//        {
//            binary_search b = new binary_search();
//            Arrays.sort(boys);
//            for(i = 0; i < num; i++)
//            {
//                int index = b.indexOf(boys,boys[i]);
//                if(index != -1)
//                {
//                    if(barr[boys[index] - 1].gf == null)
//                        System.out.println("BOY-" + (boys[index]) + " is single");
//                    else
//                        System.out.println("BOY-" + (boys[index]) + " is committed to " + barr[boys[index] - 1].gf);
//                }
//            }
//            
//        }
//        else if(choice == 3)
//        {
//            Hashtable h = new Hashtable();
//            for(i = 0; i < num; i++)
//                h.put(i,boys[i]);
//            for(i = 0; i < num; i++)
//            {
//                if(barr[(int)h.get(i) - 1].gf == null)
//                    System.out.println("BOY-" + boys[i] + " is single");
//                else
//                    System.out.println("BOY-" + boys[i] + " is committed to " + barr[boys[i] - 1].gf);
//            }
//        }
//        else
//        {
//            for(i = 0; i < num ; i++)
//            {
//                if(barr[boys[i] - 1].gf == null)
//                    System.out.println("BOY-" + boys[i] + " is single");
//                else
//                    System.out.println("BOY-" + boys[i] + " is committed to " + barr[boys[i] - 1].gf);
//            }
//        }
        //stop
        
        FIND_GIRLFRIENDS f = new  FIND_GIRLFRIENDS(barr);
        f.find_gfs();
//        System.out.println("Enter the number of boys in list");
//        Scanner sc = new Scanner(System.in);
//        int num  = sc.nextInt();
//        String[] boys = new String[num];
//        Random rand = new Random();
//        for(i = 0; i < num; i++)
//            boys[i] = barr[rand.nextInt(20)].Name;
//        System.out.println("BOY LIST");
//        for(i = 0; i < num; i++)
//            System.out.println(boys[i]);
//        System.out.println("Choose implementation 1. array 2. Binary search 3. hash table");
//        int choice = sc.nextInt();
//        if(choice == 2)
//        {
//            int inde,index = 0;
//            binary_search b = new binary_search();
//            Arrays.sort(boys);
//            for(i = 0; i < num; i++)
//            {
//                inde = b.indexOf(boys,boys[i]);
//                if(inde != -1)
//                {
//                    for(j = 0; j < 20; j++)
//                    {
//                        if(barr[j].Name.equals(boys[inde]))
//                        {
//                            index = j;
//                        }
//                    }
//                    if(barr[index].gf == null)
//                        System.out.println(boys[i] + " is single");
//                    else
//                        System.out.println( boys[i] + " is committed to " + barr[index].gf);
//                }
//            }
//            
//        }
//        else if(choice == 3)
//        {
//            Hashtable h = new Hashtable();
//            for(i = 0; i < num; i++)
//                h.put(i,boys[i]);
//            int index = 0;
//            for(i = 0; i < num ; i++)
//            {
//                for(j = 0; j < 20; j++)
//                {
//                    if(barr[j].Name.equals(h.get(i)))
//                    {
//                        index = j;
//                    }
//                }
//                if(barr[index].gf == null)
//                    System.out.println(boys[i] + " is single");
//                else
//                    System.out.println( boys[i] + " is committed to " + barr[index].gf);
//            }
//        }
//        else
//        {
//            int index = 0;
//            for(i = 0; i < num ; i++)
//            {
//                for(j = 0; j < 20; j++)
//                {
//                    if(barr[j].Name.equals(boys[i]))
//                    {
//                        index = j;
//                    }
//                }
//                if(barr[index].gf == null)
//                    System.out.println(boys[i] + " is single");
//                else
//                    System.out.println( boys[i] + " is committed to " + barr[index].gf);
//            }
//        }
        
    }
    
}

//class binary_search
//{
//     public static int indexOf(String[] a, String key) {
//        int lo = 0;
//        int hi = a.length - 1;
//        while (lo <= hi) {
//            // Key is in a[lo..hi] or not present.
//            int mid = lo + (hi - lo) / 2;
//            if(key.compareTo(a[mid]) < 0) hi = mid - 1;
//            else if (key.compareTo(a[mid]) > 0) lo = mid + 1;
//            else return mid;
//        }
//        return -1;
//    }
//
//}