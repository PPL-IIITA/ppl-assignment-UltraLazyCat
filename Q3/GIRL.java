/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HP
 */
public abstract class GIRL {
    
    String Name;
    int Attractiveness; /* 1 to 10*/
    int M_cost;
    int Intelligence; /* 1 to 10*/
    int Criterion;/* attractiveness richness intelligence */
    int Happiness;
    String Type;
    BOY bf;
    
    boolean select (BOY b)
    {
        boolean selected = false;
        if(this.Attractiveness > b.Min_Attraction && this.M_cost < b.Budget )
            selected = true;
        return selected;
    }
    
    public String toString()
    {
        return Name;
    }
}

class CHOOSY extends GIRL
{
    public CHOOSY(String n,int a,int m,int i,int c)
    {
        Name = n;
        Attractiveness = a;
        M_cost = m;
        Intelligence = i;
        Criterion = c;
        Type = "Choosy";
        bf = null;
    }
}

class NORMAL extends GIRL
{
    public NORMAL(String n,int a,int m,int i,int c)
    {
        Name = n;
        Attractiveness = a;
        M_cost = m;
        Intelligence = i;
        Criterion = c;
        Type = "Normal";
        bf = null;
    }
}

class DESPERATE extends GIRL
{
    public DESPERATE(String n,int a,int m,int i,int c)
    {
        Name = n;
        Attractiveness = a;
        M_cost = m;
        Intelligence = i;
        Criterion = c;
        Type = "Desperate";
        bf = null;
    }
}
