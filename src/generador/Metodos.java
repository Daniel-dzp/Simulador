package generador;

/**
 * @author dzp
 */
public class Metodos {
    public double[] mixto(int semilla, int a, int m, int c, int n){
        //int a;
        double numeros[];
        
        //a = 5 + 8 * k;
        numeros = new double[n];
        
        for(int i=0;i<n;i++)
        {
            semilla = (a * semilla + c) % m;
            numeros[i] = (double)semilla / m;
            numeros[i] = (double)((int)(numeros[i]*10000))/10000;
        }
        
        return numeros;
    }
}
