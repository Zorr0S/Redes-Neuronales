package Principal;





import neural.activation.ActivationSigmoid;
import neural.matrix.Matrix;
import neural.matrix.MatrixMath;

 class RNA_Dinamico {
    private ActivationSigmoid Sigmoide = new ActivationSigmoid();
    public double AND(Matrix inputMatrix, Matrix Pesos){
        double Total = MatrixMath.dotProduct(inputMatrix, Pesos);
        Total = Sigmoide.activationFunction(Total);
        return Total;
    }
    public double OR(Matrix inputMatrix, Matrix Pesos){
        double Total = MatrixMath.dotProduct(inputMatrix, Pesos);
        Total = Sigmoide.activationFunction(Total);
        return Total;
    }
    public double XOR(Matrix inputMatrix, Matrix PesosAND,Matrix PesosOR, Matrix PesosXOR){
        double inputAND = AND(inputMatrix, PesosAND);
        double InputOR= OR(inputMatrix, PesosOR);
        double Salida[]= {inputAND,InputOR,1};
       // System.out.println(Arrays.toString(PesosXOR.toPackedArray()));  
        
       
        Matrix salidasMatrix = Matrix.createColumnMatrix(Salida);
        double Total=MatrixMath.dotProduct(salidasMatrix, PesosXOR);
      //  System.out.println(Total);
        Total = Sigmoide.activationFunction(Total);
        return  Total;
    }

    public double Binario(double Valor,double Umbral){
        if(Valor>=Umbral){
            return 1;
        }else{
            return 0;
        }

    }

    public double BinarioExacto(double Valor,double Umbral){
        if(Valor==Umbral){
            return 1;
        }else{
            return 0;
        }

    }
}
