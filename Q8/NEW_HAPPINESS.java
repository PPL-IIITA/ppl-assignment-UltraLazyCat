/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HP
 */
public class NEW_HAPPINESS {
    
    void happiness(BOY[] barr, GIRL[] garr, GIFT[] giarr){
    int i,j,amt,sum;
    giarr[0].Type = "Utility";
    giarr[1].Type = "Essential";
    giarr[1].Type = "Luxury";
    for(i = 0; i < 100; i++)
        {
            sum = 0;
            if(barr[i].gf != null)
            {
                if(barr[i].Type.equals("Geek"))
                {
                    if(barr[i].gf.Type.equals("Choosy"))
                    {
                        j = 0;
                        amt = barr[i].gf.M_cost;
                        while(sum <= amt && j < 50)
                        {
                            if(giarr[j].Type.equals("Luxury"))
                                sum = sum + 2 * giarr[j].Price;
                            else
                                sum = sum + giarr[j].Price;
                            j++;
                        }
                        while(j < 50)
                        {
                            if(giarr[j].Type.equals("Luxury") && (barr[i].Budget - sum) > giarr[j].Price)
                                sum = sum + 2 * giarr[j].Price;
                            j++;
                        }
                        barr[i].gf.Happiness = (int) Math.log((double)sum);
                        barr[i].Happiness =barr[i].gf.Intelligence;
                    }
                    else if(barr[i].gf.Type.equals("Normal"))
                    {
                        j = 0;
                        amt = barr[i].gf.M_cost;
                        while(sum <= amt && j < 50)
                        {
                            sum = sum + giarr[j].Price+giarr[j].Value;
                            j++;
                        }
                        while(j < 50)
                        {
                            if(giarr[j].Type.equals("Luxury") && (barr[i].Budget - sum) > giarr[j].Price)
                                sum = sum + giarr[j].Price+giarr[j].Value;
                            j++;
                        }
                        barr[i].gf.Happiness = sum;
                        barr[i].Happiness =barr[i].gf.Intelligence;
                    }
                    else if(barr[i].gf.Type.equals("Desperate"))
                    {
                        j = 0;
                        amt = barr[i].gf.M_cost;
                        while(sum <= amt && j < 50)
                        {
                            sum = sum + giarr[j].Price;
                            j++;
                        }  
                        while(j < 50)
                        {
                            if(giarr[j].Type.equals("Luxury") && (barr[i].Budget - sum) > giarr[j].Price)
                                sum = sum + giarr[j].Price;
                            j++;
                        }
                        barr[i].gf.Happiness = (int) Math.exp((double)sum);
                        barr[i].Happiness =barr[i].gf.Intelligence;
                }
                }
                else if(barr[i].equals("Generous"))
                {
                    if(barr[i].gf.Type.equals("Choosy"))
                    {
                        j = 49;
                        amt = barr[i].Budget;
                        while(sum <= amt && j >= 0)
                        {
                            if(giarr[j].Type.equals("Luxury"))
                                sum = sum + 2 * giarr[j].Price;
                            else
                                sum = sum + giarr[j].Price;
                            j--;
                        }
                        barr[i].gf.Happiness = (int) Math.log((double)sum);
                        barr[i].Happiness = barr[i].gf.Happiness;
                    }
                    else if(barr[i].gf.Type.equals("Normal"))
                    {
                        j = 49;
                        amt = barr[i].Budget;
                        while(sum <= amt && j >= 0)
                        {
                            sum = sum + giarr[j].Price + giarr[j].Value;
                            j--;
                        }
                        barr[i].gf.Happiness = sum;
                        barr[i].Happiness = barr[i].gf.Happiness;
                    }
                    else if(barr[i].gf.Type.equals("Desperate"))
                    {
                        j = 49;
                        amt = barr[i].Budget;
                        while(sum <= amt && j >= 0)
                        {
                            sum = sum + giarr[j].Price;
                            j--;
                        }
                        barr[i].gf.Happiness = (int) Math.exp((double)sum);
                        barr[i].Happiness = barr[i].gf.Happiness;
                    }
                }
                else
                {
                    if(barr[i].gf.Type.equals("Choosy"))
                    {
                        j = 0;
                        amt = barr[i].gf.M_cost;
                        while(sum <= amt && j < 50)
                        {
                            if(giarr[j].Type.equals("Luxury"))
                                sum = sum + 2 * giarr[j].Price;
                            else
                                sum = sum + giarr[j].Price;
                            j++;
                        }
                        barr[i].gf.Happiness = (int) Math.log((double)sum);
                        barr[i].Happiness = barr[i].Budget - sum;
                    }
                    else if(barr[i].gf.Type.equals("Normal"))
                    {
                        j = 0;
                        amt = barr[i].gf.M_cost;
                        while(sum <= amt && j < 50)
                        {
                            sum = sum + giarr[j].Price + giarr[j].Value;
                            j++;
                        }
                        barr[i].gf.Happiness = sum;
                        barr[i].Happiness = barr[i].Budget - sum;
                    }
                    else if(barr[i].gf.Type.equals("Desperate"))
                    {
                        j = 0;
                        amt = barr[i].gf.M_cost;
                        while(sum <= amt && j < 50)
                        {
                            sum = sum + giarr[j].Price;
                            j++;
                        }
                        barr[i].gf.Happiness = (int) Math.exp((double)sum);
                        barr[i].Happiness = barr[i].Budget - sum;
                    }
                }
           }
       } 
    }
    
}
