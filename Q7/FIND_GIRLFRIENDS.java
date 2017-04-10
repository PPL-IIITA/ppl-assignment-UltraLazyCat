/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class FIND_GIRLFRIENDS {
    
    BOY[] barr;
    public FIND_GIRLFRIENDS(BOY[] barr)
    {
        this.barr = barr;
    }
    
        void find_gfs(){
        int i,j;
        System.out.println("Enter the number of boys in list");
        Scanner sc = new Scanner(System.in);
        int num  = sc.nextInt();
        String[] boys = new String[num];
        Random rand = new Random();
        for(i = 0; i < num; i++)
            boys[i] = barr[rand.nextInt(100)].Name;
        //System.out.println("BOY LIST");
        //for(i = 0; i < num; i++)
            //System.out.println(boys[i]);
        System.out.println("Choose implementation 1. array 2. Binary search 3. hash table");
        int choice = sc.nextInt();
        if(choice == 2)
        {
            int inde,index = 0;
            binary_search b = new binary_search();
            Arrays.sort(boys);
            for(i = 0; i < num; i++)
            {
                inde = b.indexOf(boys,boys[i]);
                if(inde != -1)
                {
                    for(j = 0; j < 100; j++)
                    {
                        if(barr[j].Name.equals(boys[inde]))
                        {
                            index = j;
                        }
                    }
                    if(barr[index].gf == null)
                        System.out.println(boys[i] + " is single");
                    else
                        System.out.println( boys[i] + " is committed to " + barr[index].gf);
                }
            }
            
        }
        else if(choice == 3)
        {
            Hashtable h = new Hashtable();
            for(i = 0; i < num; i++)
                h.put(i,boys[i]);
            int index = 0;
            for(i = 0; i < num ; i++)
            {
                for(j = 0; j < 100; j++)
                {
                    if(barr[j].Name.equals(h.get(i)))
                    {
                        index = j;
                    }
                }
                if(barr[index].gf == null)
                    System.out.println(boys[i] + " is single");
                else
                    System.out.println( boys[i] + " is committed to " + barr[index].gf);
            }
        }
        else
        {
            int index = 0;
            for(i = 0; i < num ; i++)
            {
                for(j = 0; j < 100; j++)
                {
                    if(barr[j].Name.equals(boys[i]))
                    {
                        index = j;
                    }
                }
                if(barr[index].gf == null)
                    System.out.println(boys[i] + " is single");
                else
                    System.out.println( boys[i] + " is committed to " + barr[index].gf);
            }
        }
        }
    
}

class binary_search
{
     public static int indexOf(String[] a, String key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if(key.compareTo(a[mid]) < 0) hi = mid - 1;
            else if (key.compareTo(a[mid]) > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

}
