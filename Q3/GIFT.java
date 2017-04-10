/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HP
 */
public abstract class GIFT {
    String Type;
    int Price;
    int Value;
    
    
}

class UTILITY extends GIFT
{
    public UTILITY(int price,int value)
    {
        Type = "Utility";
        Price = price;
        Value = value;
    }
    
    //public String toString()
    //{
    //    return Type;
    //}
}

class LUXURY extends GIFT
{
    public LUXURY(int price,int value)
    {
        Type = "Luxury";
        Price = price;
        Value = value;
    }
}

class ESSENTIAL extends GIFT
{
    public ESSENTIAL(int price,int value)
    {
        Type = "Essential";
        Price = price;
        Value = value;
    }
}