/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CitiesFx;

import java.util.StringTokenizer;
/**
 *
 * @author Юрий
 */
public class CityClass {
    protected String[] Name = new String[100];;
    protected int[] Citizens = new int[100];
    protected int[] Date = new int[100];
    protected int[] Schools = new int[100];
    protected double[] Area = new double[100];
    private int i;
     
    
    void SetElemValue(String str){
        i = 0;
        StringTokenizer st = new StringTokenizer(str);
        while(st.hasMoreTokens()){
            Name[i] = st.nextToken();
            Citizens[i] = Integer.parseInt(st.nextToken());
            Date[i] = Integer.parseInt(st.nextToken());
            Schools[i] = Integer.parseInt(st.nextToken());
            Area[i] = Double.parseDouble(st.nextToken());
            System.out.println( "\nНазвание : " + Name[i] + "\nГод : " + Date[i] + "\nЖителей : " + Citizens[i] + "\nКоличество школ : " + Schools[i] + "\nПлощадь : " +Area[i]);
            i++;
        }
        
    }
     
     void getInfo(int i){
System.out.println( "\nНазвание : " + Name[i] + "\nГод : " + Date[i] + "\nЖителей : " + Citizens[i] + "\nКоличество школ : " + Schools[i] + "\nПлощадь : " +Area[i]);
     }
     
}
