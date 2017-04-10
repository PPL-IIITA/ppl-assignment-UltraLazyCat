/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HP
 */
public abstract class BOY {
    
    String Name;
    int Attractiveness;
    int Intelligence;
    int Budget;
    int Min_Attraction;
    int Happiness;
    String Type;
    GIRL gf;
    
//    public BOY(String n,int a,int i,int b,int m,String t)
    
//    {
//        Name = n;
//        Attractiveness = a;
//        Intelligence = i;
//        Budget = b;
//        Min_Attraction = m;
//        Type = t;
//        gf = null;
//    }
    
    public String toString()
    {
        return Name;
    }
}

class MISER extends BOY
{
    public MISER(String n,int a,int i,int b,int m)
    {
        Name = n;
        Attractiveness = a;
        Intelligence = i;
        Budget = b;
        Min_Attraction = m;
        Type = "Miser";
        gf = null;
    }
}

class GENEROUS extends BOY
{
    public GENEROUS(String n,int a,int i,int b,int m)
    {
        Name = n;
        Attractiveness = a;
        Intelligence = i;
        Budget = b;
        Min_Attraction = m;
        Type = "Generous";
        gf = null;
    }
    
}

class GEEK extends BOY
{
    public GEEK(String n,int a,int i,int b,int m)
    {
        Name = n;
        Attractiveness = a;
        Intelligence = i;
        Budget = b;
        Min_Attraction = m;
        Type = "Geek";
        gf = null;
    }
    
}