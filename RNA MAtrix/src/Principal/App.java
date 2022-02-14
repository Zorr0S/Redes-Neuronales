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
        double[] Entrada = { Input };
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

    public static boolean RNAComparar(Matrix Entrada) {
        /// N1 - N2- N3- N4- N5- N6
        double[][] Red = { { 0, 1, -1, 1, -1, 1 }, // N1
                { 1, 0, -1, 1, -1, 1 }, // N2
                { -1, -1, 0, -1, 1, -1 }, // N3
                { 1, 1, -1, 0, -1, 1 }, // N4
                { -1, -1, 1, -1, 0, -1 }, // N5
                { 1, 1, -1, 1, -1, 0 }, // N6

        };

        // System.out.println("Dimenciones de entrada : [" +Entrada.getRows() + " , " + Entrada.getCols()  + " ]");

        Matrix redMatrix = new Matrix(Red);
        Matrix resultadoMatrix = MatrixMath.multiply(redMatrix, Entrada);
        // System.out.println("Dimenciones de salida : [" +resultadoMatrix.getRows() + " , " + resultadoMatrix.getCols()  + " ]");

        System.out.printf(" Salida [");
        for (int i = 0; i < resultadoMatrix.getRows(); i++) {
        
            for (int j = 0; j < resultadoMatrix.getCols(); j++) {
                if((resultadoMatrix.get(i, j)> 0)){
                    resultadoMatrix.set(i,j , 1);
                   System.out.printf(resultadoMatrix.get(i, j)+ ", ");
                }else{
                    resultadoMatrix.set(i,j , 0);
                    System.out.printf(resultadoMatrix.get(i, j)+ ", ");
                }
            }
            
        }
        System.out.println("]");   

        if (Entrada.equals(resultadoMatrix)) {
            System.out.println("Es reconocido");
            return true;
        } else {
            System.out.println("no es reconocido");

            return false;
        }
           
    }
    public static void  Tarae(){
        double[][] A= {{3,2},{1,3},{1,4}};
        double[][] B= {{1,2,3},{4,5,6}};

        Matrix matrixA = new Matrix(A);
        Matrix matrixB = new Matrix(B);

        Matrix resultadoMatrix = MatrixMath.multiply(matrixA, matrixB);
        for (int i = 0; i < resultadoMatrix.getRows(); i++) {
        
            for (int j = 0; j < resultadoMatrix.getCols(); j++) {
              
                   System.out.printf(resultadoMatrix.get(i, j)+ ", ");
               
            }
            System.out.println("");
        }
        double[][] C= {{6,2,7,4}};
        double[][] D ={{6,6,7,9}};
        Matrix matrixC = new Matrix(C);
        Matrix matrixD = new Matrix(D);
        System.out.println(MatrixMath.dotProduct(matrixC, matrixD));
    }

    public static void main(String[] args) {
        Tarae();
        double[][] Intput = { { 1, 1 },
                { 1, 0 },
                { 0, 1 },
                { 0, 0 } };
        

        double[][] Muestra = { { 0, 0, 1, 0, 1, 0 },
                { 1, 1, 0, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 1 }
        };
        for (int i = 0; i < Muestra.length; i++) {
            System.out.println("Entrada " + Arrays.toString(Muestra[i]));
            RNAComparar(Matrix.createColumnMatrix(Muestra[i]));

            System.out.println("\n");
        }

        Matrix Entrada = new Matrix(Intput);

        System.out.println("[ " + Intput[1][0] + " ]" + " NOT : " + RNA_NOT(Entrada.get(1, 0)));

        System.out.println("[ " + Intput[1][1] + " ]" + " NOT : " + RNA_NOT(Entrada.get(1, 1)));
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
