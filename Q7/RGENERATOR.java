/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileWriter;
import java.util.Random;

/**
 *
 * @author HP
 */
public class RGENERATOR {
    
    String[] gift = {"Utility","Luxury","Essential"};
    String[] boy = {"Geek","Generous","Miser"};
    String[] girl = {"Choosy","Desperate","Normal"};
    
    void create_boys()
    {
        String name,str = "",type;
        int i,j,attractiveness,intelligence,budget,min_attraction;
        Random r = new Random();
        try
        {
            FileWriter writer = new FileWriter("boys.csv");
            for(i = 1; i <= 100; i++)
            {
                //name = "BOY-" + i;
                //new
                String stri = "";
                for(j = 0; j < 5; j++)
                    stri = stri + (char)(r.nextInt(26)+65);
                name = stri;
                //new
                attractiveness = r.nextInt(11);
                intelligence = r.nextInt(11);
                budget = r.nextInt(501);
                min_attraction = r.nextInt(11);
                type = boy[r.nextInt(3)];
                str = str + name + " " + attractiveness + " " + intelligence + " " + budget + " " + min_attraction + " " + type +"\n";
            }    
            writer.write(str);
            writer.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    void create_girls()
    {
        String name,str = "",type;
        int i,j,attractiveness,M_cost,intelligence,criterion;
        Random r = new Random();
        try
        {
            FileWriter writer = new FileWriter("girls.csv");
            for(i = 1; i <= 50; i++)
            {
                //name = "GIRL-" + i;
                //new
                String stri = "";
                for(j = 0; j < 5; j++)
                    stri = stri + (char)(r.nextInt(26)+65);
                name = stri;
                //new
                attractiveness = r.nextInt(11);
                M_cost = r.nextInt(501);
                intelligence = r.nextInt(10);
                criterion = r.nextInt(3)+1;
                type = girl[r.nextInt(3)];
                str = str + name + " " + attractiveness + " " + M_cost + " " + intelligence + " " + criterion + " " + type + "\n";
            }    
            writer.write(str);
            writer.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
