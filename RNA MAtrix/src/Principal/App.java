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
public class App {

    /**
     * @param args the command line arguments
     */

    public static double RNA_AND(Matrix Input) {
        // Umbral
        double[] Pesos = { 1, 1 ,1};
        Matrix weitghtMatrix = Matrix.createColumnMatrix(Pesos);
        // //Input.add(0, c, value);
        // double[] Sesgo = { 1 }; // Entrada del sesgo siempre vale 1
		// double[] Pesosesgo = { }; // Peso definido en el sesgo
		// Matrix sesgoMatrix = Matrix.createColumnMatrix(Sesgo);
		// Matrix pesosesgoMatrix = Matrix.createColumnMatrix(Pesosesgo);
		

        double Total = MatrixMath.dotProduct(Input, weitghtMatrix) ;

       
        ActivationSigmoid FuncionDeActivacion = new ActivationSigmoid();
        //System.out.println(FuncionDeActivacion.activationFunction(Total)); 
        Total=FuncionDeActivacion.activationFunction(Total);
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

        // double[] Sesgo = { 1 }; // Entrada del sesgo siempre vale 1
		// double[] Pesosesgo = { 0.5 }; // Peso definido en el sesgo
		// Matrix sesgoMatrix = Matrix.createColumnMatrix(Sesgo);
		// Matrix pesosesgoMatrix = Matrix.createColumnMatrix(Pesosesgo);
		

        double Total = MatrixMath.dotProduct(Input, weitghtMatrix);
        
        //double Umbral = 0.5;

        ActivationSigmoid FuncionDeActivacion = new ActivationSigmoid();
        //System.out.println(FuncionDeActivacion.activationFunction(Total)); 
        Total=FuncionDeActivacion.activationFunction(Total);
    
         double Umbral =0.8;
        if (Total >= Umbral) {
            return Total;
        } else {
            return Total;
        }
    }

    public static double RNA_XOR(Matrix Input) {
        double[] Pesos = { -1, 1,0.5};
        Matrix weitghtMatrix = Matrix.createColumnMatrix(Pesos);

        double[] Resultados = { (RNA_AND(Input)), RNA_OR(Input), 1 };
        
        Matrix resultadosMatrix = Matrix.createColumnMatrix(Resultados);
        
        
        
        double Total = MatrixMath.dotProduct(resultadosMatrix, weitghtMatrix) ;

        
        
        ActivationSigmoid FuncionDeActivacion = new ActivationSigmoid();
        System.out.print("XOR Funcion sigmondial: "+FuncionDeActivacion.activationFunction(Total)+ "  "); 
        
        // Umbral
       // double Umbral = 0.5;
       Total=FuncionDeActivacion.activationFunction(Total);
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
        //  System.out.println(FuncionDeActivacion.activationFunction(Total)); 
        Total=FuncionDeActivacion.activationFunction(Total);
        //double Umbral = -1;
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

    public static void main(String[] args) {
        ActivationSigmoid Prueba = new ActivationSigmoid();
        System.out.println(Prueba.activationFunction(1.5));

        double[][] Intput = { { 1, 1,1 },
                { 1, 0 ,1},
                { 0, 1 ,1},
                { 0, 0 ,1} };

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
        System.out.println("--------------------------------------------------");
        System.out.println("|  A  |  B  |  AND  |  OR  |  XOR  |  ¬A  |  ¬B  |"); 
        System.out.println("--------------------------------------------------");
        

        for (int i = 0; i < Entrada.getRows(); i++) {
          System.out.println( "| "+ Intput[i][0] +" | " + Intput[i][1] +" |  \n AND > "+RNA_AND(Entrada.getRow(i))+"  | \n OR >"+RNA_OR(Entrada.getRow(i))+" | \n XOR > "+RNA_XOR(Entrada.getRow(i))+"  |  \n NOT A > "+ RNA_NOT(Entrada.get(i, 0))+" | \n NOT B > " + RNA_NOT(Entrada.get(i, 1))+"  | ");
          
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
        Neurona(){

        }
        
    }

}
