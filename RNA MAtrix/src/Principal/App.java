/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// 3.6 2-mayo
// Aplication to the financial  markets
package Principal;

import java.util.Arrays;
import neural.matrix.Matrix;
import neural.matrix.MatrixMath;

/**
 *
 * @author alex_
 */
public class App {

    /**
     * @param args the command line arguments
     */

    public static int RNA_AND(Matrix Input) {
        // Umbral
        double[] Pesos = { 1, 1 };
        Matrix weitghtMatrix = Matrix.createColumnMatrix(Pesos);

        double Total = MatrixMath.dotProduct(Input, weitghtMatrix);

        double Umbral = 1.5;
        if (Total >= Umbral) {
            return 1;
        } else {
            return 0;
        }

    }

    public static int RNA_OR(Matrix Input) {
        // Umbral
        double[] Pesos = { 1, 1 };
        Matrix weitghtMatrix = Matrix.createColumnMatrix(Pesos);

        double Total = MatrixMath.dotProduct(Input, weitghtMatrix);
        double Umbral = 0.5;
        if (Total >= Umbral) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int RNA_XOR(Matrix Input) {
        double[] Pesos = { -1, 1 };
        Matrix weitghtMatrix = Matrix.createColumnMatrix(Pesos);
        double[] Resultados = { (RNA_AND(Input)), RNA_OR(Input) };
        Matrix resultadosMatrix = Matrix.createColumnMatrix(Resultados);
        double Total = MatrixMath.dotProduct(resultadosMatrix, weitghtMatrix);
        // Umbral
        double Umbral = 0.5;
        if (Total >= Umbral) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int RNA_NOT(double Input) {

        double[] Pesos = { -2 };
        double[] Entrada = {Input};
        Matrix entradaMatrix = Matrix.createColumnMatrix(Entrada);
        Matrix weitghtMatrix = Matrix.createColumnMatrix(Pesos);
        double Total = MatrixMath.dotProduct(entradaMatrix, weitghtMatrix);
        

       

        double Umbral = -1;
        if (Total >= Umbral) {
            return 1;
        } else {
            return 0;
        }

    }
    public static boolean RNAComparar(Matrix Entrada){
        ///                N1 - N2- N3- N4- N5- N6
        double[][] Red = {{ 0 , 1 ,-1 , 1, -1, 1},   //N1
                          { 1 , 0 ,-1 , 1, -1, 1},   //N2
                          {-1 ,-1 , 0 ,-1,  1,-1},  //N3
                          { 1 , 1 ,-1 , 0, -1, 1},  //N4
                          {-1 ,-1 , 1 ,-1,  0,-1},  //N5
                          { 1 , 1 ,-1 , 1,- 1, 0},  //N6
    
                        };
        
        System.out.println(Arrays.toString(Red));               
        Matrix redMatrix= new Matrix(Red);
        Matrix resultadoMatrix = MatrixMath.multiply(Entrada, redMatrix);

        if(resultadoMatrix.equals(Entrada)){
           System.out.println("Es reconocido");
            return true;
        }else{
           System.out.println("no es reconocido");

            return false;
        }
        
        
    }

    public static void main(String[] args) {

        double[][] Intput = { { 1, 1 },
                { 1, 0 },
                { 0, 1 },
                { 0, 0 } };
        double[][] Muestra= {{0,0,1,0,1,0},
                             {1,1,0,1,0,1},
                            { 1,0,1,0,1,1}
                        };
        Matrix MuestraMatrix= new Matrix(Muestra);
        for (int i = 0; i < MuestraMatrix.getRows(); i++) {
            RNAComparar(MuestraMatrix.getRow(i));
            
        }
        Matrix Entrada = new Matrix(Intput);

        System.out.println("[ " + Intput[1][0] + " ]" + " NOT : " + RNA_NOT(Entrada.get(1,0)));
       
        System.out.println("[ " + Intput[1][1] + " ]" + " NOT : " + RNA_NOT(Entrada.get(1,1)));
        System.out.println();

        for (int i = 0; i < Entrada.getRows(); i++) {
            System.out.println(Arrays.toString(Intput[i]) + " AND : " + RNA_AND(Entrada.getRow(i)));
            System.out.println(Arrays.toString(Intput[i]) + " OR  : " + RNA_OR(Entrada.getRow(i)));

            System.out.println(Arrays.toString(Intput[i]) + " XOR : " + RNA_XOR(Entrada.getRow(i)));

            System.out.println("[ " + Intput[i][1] + " ]" + " NOT  : " + RNA_NOT(Entrada.get(i, 0)));

            System.out.println("\n");
        }

    }

}
