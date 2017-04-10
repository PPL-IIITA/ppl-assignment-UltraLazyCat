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
    
    public BOY(String n,int a,int i,int b,int m,String t)
    {
        Name = n;
        Attractiveness = a;
        Intelligence = i;
        Budget = b;
        Min_Attraction = m;
        Type = t;
        gf = null;
    }
    
    public String toString()
    {
        return Name;
    }
    
}
