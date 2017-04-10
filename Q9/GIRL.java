/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HP
 */
public class GIRL {
    
    String Name;
    int Attractiveness; /* 1 to 10*/
    int M_cost;
    int Intelligence; /* 1 to 10*/
    int Criterion;/* attractiveness richness intelligence */
    int Happiness;
    String Type;
    int k;
    BOY bf;
    
    public GIRL(String n,int a,int m,int i,int c,String type,int k)
    {
        Name = n;
        Attractiveness = a;
        M_cost = m;
        Intelligence = i;
        Criterion = c;
        Type = type;
        this.k = k;
        bf = null;
    }
    
    public String toString()
    {
        return Name;
    }
    
    boolean select (BOY b, int bindex)
    {
        boolean selected = false;
        if(this.Attractiveness > b.Min_Attraction && this.M_cost < b.Budget && bindex < k )
            selected = true;
        return selected;
    }
}
