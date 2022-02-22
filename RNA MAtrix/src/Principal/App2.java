/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// 3.6 2-mayo
// Aplication to the financial  markets
package Principal;

import java.util.Arrays;

import neural.activation.ActivationSigmoid;
import neural.matrix.Matrix;
import neural.matrix.MatrixMath;

/**
 *
 * @author alex_
 */
public class App2 {

    /**
     * @param args the command line arguments
     */

    public static double RNA_AND(Matrix Input) {
        double[] Pesos = { 1, 1, 1 };
        Matrix weitghtMatrix = Matrix.createColumnMatrix(Pesos);

        double Total = MatrixMath.dotProduct(Input, weitghtMatrix);

        ActivationSigmoid FuncionDeActivacion = new ActivationSigmoid();
        // System.out.println(FuncionDeActivacion.activationFunction(Total));
        Total = FuncionDeActivacion.activationFunction(Total);
        // double Umbral = 1.5;
        double Umbral = 0.9;

        if (Total >= Umbral) {
            return Total;
        } else {
            return Total;
        }

    }

    public static double RNA_OR(Matrix Input) {
        // Umbral
        double[] Pesos = { 1, 1, 0.5 };
        Matrix weitghtMatrix = Matrix.createColumnMatrix(Pesos);

        double Total = MatrixMath.dotProduct(Input, weitghtMatrix);

        // double Umbral = 0.5;

        ActivationSigmoid FuncionDeActivacion = new ActivationSigmoid();
        // System.out.println(FuncionDeActivacion.activationFunction(Total));
        Total = FuncionDeActivacion.activationFunction(Total);

        double Umbral = 0.8;
        if (Total >= Umbral) {
            return Total;
        } else {
            return Total;
        }
    }

    public static double RNA_XOR(Matrix Input) {
        double[] Pesos = { -1, 1, 0.5 };
        Matrix weitghtMatrix = Matrix.createColumnMatrix(Pesos);

        double[] Resultados = { (RNA_AND(Input)), RNA_OR(Input), 1 };

        Matrix resultadosMatrix = Matrix.createColumnMatrix(Resultados);

        double Total = MatrixMath.dotProduct(resultadosMatrix, weitghtMatrix);

        ActivationSigmoid FuncionDeActivacion = new ActivationSigmoid();
        //System.out.print("XOR Funcion sigmondial: " + FuncionDeActivacion.activationFunction(Total) + "  ");

        // Umbral
        // double Umbral = 0.5;
        Total = FuncionDeActivacion.activationFunction(Total);
        double Umbral = 0.5;
        if (Total > Umbral) {
            return Total;
        } else {
            return Total;
        }
    }

    public static double RNA_NOT(double Input) {

        double[] Pesos = { -2 };

        double[] Entrada = { Input };
        Matrix entradaMatrix = Matrix.createColumnMatrix(Entrada);
        Matrix weitghtMatrix = Matrix.createColumnMatrix(Pesos);
        double Total = MatrixMath.dotProduct(entradaMatrix, weitghtMatrix);

        ActivationSigmoid FuncionDeActivacion = new ActivationSigmoid();
        // System.out.println(FuncionDeActivacion.activationFunction(Total));
        Total = FuncionDeActivacion.activationFunction(Total);
        // double Umbral = -1;
        double Umbral = 0.2;
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

        // System.out.println("Dimenciones de entrada : [" +Entrada.getRows() + " , " +
        // Entrada.getCols() + " ]");

        Matrix redMatrix = new Matrix(Red);
        Matrix resultadoMatrix = MatrixMath.multiply(redMatrix, Entrada);
        // System.out.println("Dimenciones de salida : [" +resultadoMatrix.getRows() + "
        // , " + resultadoMatrix.getCols() + " ]");

        System.out.printf(" Salida [");
        for (int i = 0; i < resultadoMatrix.getRows(); i++) {

            for (int j = 0; j < resultadoMatrix.getCols(); j++) {
                if ((resultadoMatrix.get(i, j) > 0)) {
                    resultadoMatrix.set(i, j, 1);
                    System.out.printf(resultadoMatrix.get(i, j) + ", ");
                } else {
                    resultadoMatrix.set(i, j, 0);
                    System.out.printf(resultadoMatrix.get(i, j) + ", ");
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

    public static void main(String[] args) {
        RNA_Dinamico RNA = new RNA_Dinamico();
      

        double[][] Input = { { 0, 0, 1 },
                             { 0, 1, 1 },
                             { 1, 0, 1 },
                             { 1, 1, 1 } };
        Matrix Entrada = new Matrix(Input);
        double[][] Muestra = { { 0, 0, 1, 0, 1, 0 },
                { 1, 1, 0, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 1 }
        };
        for (int i = 0; i < Muestra.length; i++) {
            System.out.println("Entrada " + Arrays.toString(Muestra[i]));
            RNAComparar(Matrix.createColumnMatrix(Muestra[i]));

            System.out.println("\n");
        }

       

        double PesosArreglo[][]= { { 1, 1, 1 }, { 1, 1, 0.5 }, { -1, 1, 0.5 }};
        Matrix PesosMatriz= new Matrix(PesosArreglo);
        double ValoresFinales[]= new double[3];
        double HidNeu = 2;
        double InpCou = 2;
        double beta=0.7*((Math.pow(HidNeu,(1/InpCou))));
        //double NormH1 =  Math.sqrt(Math.pow(pesos2.get(0,0),2)+Math.pow(pesos2.get(0,1),2)+Math.pow(pesos2.get(0,2),2));
        //double NormH2 =  Math.sqrt(Math.pow(pesos2.get(1,0),2)+Math.pow(pesos2.get(1,1),2)+Math.pow(pesos2.get(1,2),2));
        double[] Norm = new double[(int) HidNeu];
        
        for(int i=0; i < HidNeu; i++){
            Norm[i] = MatrixMath.vectorLength(PesosMatriz.getRow(i));
            for(int j=0; j < PesosMatriz.getCols(); j++){   
                double NewWei = (beta*PesosMatriz.get(i,j)/Norm[i]);
                PesosMatriz.set(i,j,NewWei);
            }
        }

        System.out.println("[ " + Input[1][0] + " ]" + " NOT : " + RNA_NOT(Entrada.get(1, 0)));

        System.out.println("[ " + Input[1][1] + " ]" + " NOT : " + RNA_NOT(Entrada.get(1, 1)));
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("|  A  |  B  |  AND  |  OR  |  XOR  |  ¬A  |  ¬B  |");
        System.out.println("--------------------------------------------------");

        for (int i = 0; i < Entrada.getRows(); i++) {
        
            ValoresFinales[0]= RNA.AND(Entrada.getRow(i), PesosMatriz.getRow(0));
            ValoresFinales[1]= RNA.OR(Entrada.getRow(i), PesosMatriz.getRow(1));
            ValoresFinales[2]= RNA.XOR(Entrada.getRow(i), PesosMatriz.getRow(0),
                                                          PesosMatriz.getRow(1),
                                                          PesosMatriz.getRow(2));   
            
           
            // System.out.println("| " + Input[i][0] + " | " + Input[i][1] + 
            // " |  " +  ValoresFinales[0]
            //         + "  |  " +ValoresFinales[1]
            //         + " |  " + ValoresFinales[2]
            //         + "  | " + RNA_NOT(Entrada.get(i, 0)) 
            //         + "  | " + RNA_NOT(Entrada.get(i, 1))
            //         + "  | " + 
            //         "XOR > " + ValoresFinales[2]);
        
        
             System.out.println("| " + Input[i][0] + " | " + Input[i][1] + 
            "  | " +  RNA.Binario(ValoresFinales[0], 0.7891705023859108)
                    + "  |  " + RNA.Binario(ValoresFinales[1], 0.6224593312018546)
                    + "  | " + RNA.BinarioExacto(ValoresFinales[2], 0.6155803000154294)
                    + "  | " + RNA_NOT(Entrada.get(i, 0)) 
                    + "  | " + RNA_NOT(Entrada.get(i, 1))
                    + "  | " + 
                    "XOR > " + ValoresFinales[2]);

        }
        System.out.println("--------------------------------------------------");

    }

    /**
     * Neurona
     */
    public class Neurona {
        Matrix Entrada;
        Matrix Pesos;
        double SalidaNeta;
        Double SalidaSesgo;

        Neurona() {

        }

    }

}
