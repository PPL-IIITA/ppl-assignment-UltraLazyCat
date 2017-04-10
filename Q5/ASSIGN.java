/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author HP
 */
public class ASSIGN {
    
    void assign(int choice, BOY[] barr, GIRL[] garr)
    {
        int i,j,turn = 0;
        String str ="";
        if(choice == 2)
        {
            for(i = 0; i < 50; i++)
            {
                if(turn == 0)
                {
                    str = str + do_girl(barr,garr);
                    turn = 1;
                }
                else
                {
                    str = str + do_boy(barr,garr);
                    turn = 0;
                }
            }
        }
        else
        {
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
        }
        
        //out
        
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
        
        //out
    }
    
    String do_boy(BOY[] barr, GIRL[] garr)
    {
        int i,j,flag = 0;
        String str = ""; 
        i = 0;
        while(i < 100)
        {
            for(j = 0; j < 50; j++)
            {
                flag = 0;
                if(barr[i].select(garr[j]) && barr[i].gf == null && garr[j].bf == null)
                {
                    garr[j].bf = barr[i];
                    barr[i].gf = garr[j];
                    str = str + barr[i].Name + " is committed to " + garr[j].Name + "\n";
                    flag = 1;
                    break;
                }
            }
            if(flag == 1)
                    break;
            i++;
        }
        return str;
    }
    
    String do_girl(BOY[] barr, GIRL[] garr)
    {
        int i,j,flag = 0;
        String str = ""; 
        i = 0;
        while(i < 50)
        {
            for(j = 0; j < 100; j++)
            {
                flag = 0;
                if(garr[i].select(barr[j]) && barr[j].gf == null && garr[i].bf == null)
                {
                    garr[i].bf = barr[j];
                    barr[j].gf = garr[i];
                    str = str + garr[i].Name + " is committed to " + barr[j].Name + "\n";
                    flag = 1;
                    break;
                }
            }
            if(flag == 1)
                    break;
            i++;
        }
        return str;
    }
}
