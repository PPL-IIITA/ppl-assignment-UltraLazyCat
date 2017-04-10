/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HP
 */
public class SORT {
    
    void sort(BOY[] barr, GIRL[] garr)
    {
        int i,j;
        GIRL gtemp; 
        BOY btemp;
        for(i = 0; i < 49; i++)
        {
            for(j = 0; j < 49 - i; j++)
            {
                if(garr[j].M_cost > garr[j + 1].M_cost)
                {
                    gtemp = garr[j];
                    garr[j] = garr[j + 1];
                    garr[j + 1] = gtemp;
                }
            }
        }
        for(i = 0; i < 99; i++)
        {
            for(j = 0; j < 99 - i; j++)
            {
                if(barr[j].Attractiveness > barr[j + 1].Attractiveness)
                {
                    btemp = barr[j];
                    barr[j] = barr[j + 1];
                    barr[j + 1] = btemp;
                }
            }
        }
    }
}
