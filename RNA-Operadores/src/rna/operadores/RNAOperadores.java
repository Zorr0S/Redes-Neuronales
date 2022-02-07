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
   public static int RNA_AND(int[] Input) {
      // Umbral
      
      int Total = IntStream.of(Input).sum();
      double Umbral = 1.5;
      if (Total >= Umbral) {
         return 1;
      } else {
         return 0;
      }

   }
   public static int RNA_OR(int[] Input) {
      // Umbral
      int Total = IntStream.of(Input).sum();
      double Umbral = 0.5;
      if (Total >= Umbral) {
         return 1;
      } else {
         return 0;
      }
   }
   public static int RNA_XOR(int[] Input) {
    
      int[] Totales = {(RNA_AND(Input))*-1,RNA_OR(Input)*1};
      int Total=  IntStream.of(Totales).sum();
      
      // Umbral
      double Umbral = 0.5;
      if (Total >= Umbral) {
         return 1;
      } else {
         return 0;
      }
   }




   public static void main(String[] args) {
      System.out.println("\n ----------------- Operador AND ----------------- ");
      int[] Intput = { 0, 0 };
      Intput[0] = 1;
      Intput[1] = 1;
      System.out.println( Arrays.toString(Intput) +" AND : " + RNA_AND(Intput));
      
      Intput[0] = 0;
      Intput[1] = 1;
      System.out.println( Arrays.toString(Intput) +" AND : " + RNA_AND(Intput));
      
      Intput[0] = 1;
      Intput[1] = 0;
      System.out.println( Arrays.toString(Intput) +" AND : " + RNA_AND(Intput));
      
      Intput[0] = 0;
      Intput[1] = 0;
      System.out.println( Arrays.toString(Intput) +" AND : " + RNA_AND(Intput));
      
      System.out.println("\n ----------------- Operador OR ----------------- ");

      Intput[0] = 1;
      Intput[1] = 1;
      System.out.println( Arrays.toString(Intput) +" OR : " + RNA_OR(Intput));
      
      Intput[0] = 0;
      Intput[1] = 1;
      System.out.println( Arrays.toString(Intput) +" OR : " + RNA_OR(Intput));
      
      Intput[0] = 1;
      Intput[1] = 0;
      System.out.println( Arrays.toString(Intput) +" OR : " + RNA_OR(Intput));
      
      Intput[0] = 0;
      Intput[1] = 0;
      System.out.println( Arrays.toString(Intput) +" OR : " + RNA_OR(Intput));

      System.out.println("\n ----------------- Operador XOR ----------------- ");

      Intput[0] = 1;
      Intput[1] = 1;
      System.out.println( Arrays.toString(Intput) +" XOR : " + RNA_XOR(Intput));
      
      Intput[0] = 0;
      Intput[1] = 1;
      System.out.println( Arrays.toString(Intput) +" XOR : " + RNA_XOR(Intput));
      
      Intput[0] = 1;
      Intput[1] = 0;
      System.out.println( Arrays.toString(Intput) +" XOR : " + RNA_XOR(Intput));
      
      Intput[0] = 0;
      Intput[1] = 0;
      System.out.println( Arrays.toString(Intput) +" XOR : " + RNA_XOR(Intput));

      
   }

   
}
