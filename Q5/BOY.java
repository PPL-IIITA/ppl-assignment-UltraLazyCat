/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HP
 */
public class BOY {
    
    String Name;
    int Attractiveness;
    int Intelligence;
    int Budget;
    int Min_Attraction;
    int Happiness;
    String Type;
    GIRL gf;
    
    public BOY(String n,int a,int i,int b,int m,String type)
    {
        Name = n;
        Attractiveness = a;
        Intelligence = i;
        Budget = b;
        Min_Attraction = m;
        Type = type;
        gf = null;
    }
    
    public String toString()
    {
        return Name;
    }
    
    boolean select (GIRL g)
    {
        boolean selected = false;
        if(g.Attractiveness > this.Min_Attraction && g.M_cost < this.Budget)
            selected = true;
        return selected;
    }
    
}
