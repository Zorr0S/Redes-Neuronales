/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rna.operadores;


import java.util.Arrays;
import java.util.stream.IntStream;


/**
 *
 * @author alex_
 */
public class RNAOperadores {

   /**
    * @param args the command line arguments
    */
   public static int RNA_AND(int[] Input ) {
      // Umbral
      int[] Pesos={1,1};
      int Total =0;
      
      for (int i = 0; i < Pesos.length; i++) {
         Total += Input[i] * Pesos[i]; 
      }
      double Umbral = 1.5;
      if (Total >= Umbral) {
         return 1;
      } else {
         return 0;
      }

   }
   public static int RNA_OR(int[] Input) {
      // Umbral
      int[] Pesos={1,1};
      int Total =0;
      
      for (int i = 0; i < Pesos.length; i++) {
         Total += Input[i] * Pesos[i]; 
      }
      double Umbral = 0.5;
      if (Total >= Umbral) {
         return 1;
      } else {
         return 0;
      }
   }
   public static int RNA_XOR(int[] Input) {
      
      int[] Totales = {(RNA_AND(Input ))*-1,RNA_OR(Input)*1};
      int Total=  IntStream.of(Totales).sum();
      
      // Umbral
      double Umbral = 0.5;
      if (Total >= Umbral) {
         return 1;
      } else {
         return 0;
      }
   }


   public static int RNA_NOT(int Input){
      
      int[] Pesos={-2};
      int Total =0;
      
      for (int i = 0; i < Pesos.length; i++) {
         Total += Input * Pesos[i]; 
      }
      
      double Umbral = -1;
      if (Total >= Umbral) {
         return 1;
      } else {
         return 0;
      }

   }

   public static void main(String[] args) {
      
      int[][] Intput ={ { 1, 1 },
                        { 1, 0 },
                        { 0, 1 },
                        { 0, 0 }};
   
      
      System.out.println( "[ "+Intput[1][0]+" ]" +" NOT : " + RNA_NOT(Intput[1][0]));
      System.out.println( "[ "+Intput[1][1]+" ]" +" NOT : " + RNA_NOT(Intput[1][1]));
      System.out.println();
      for (int i = 0; i < Intput.length; i++) {
         System.out.println( Arrays.toString(Intput[i]) +" AND : " + RNA_AND(Intput[i]));
         System.out.println( Arrays.toString(Intput[i]) +" OR : " + RNA_OR(Intput[i]));
         
         System.out.println( Arrays.toString(Intput[i]) +" XOR : " + RNA_XOR(Intput[i]));
        
         System.out.println( "[ "+Intput[i][1]+" ]" +" NOT : " + RNA_NOT(Intput[i][1]));
         
         System.out.println("\n");
      }
      
      
   }

   
}
