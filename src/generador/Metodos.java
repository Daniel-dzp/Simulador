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
    
    public double[] adictivo(int semillas[], int m, int n){
        double numeros[] = null;
        double semillasGeneradas[];
        int noSemillas;
        
        noSemillas = semillas.length;
        numeros = new double[n];
        semillasGeneradas = new double[noSemillas + n];
        
        for(int i=0;i<noSemillas;i++)
            semillasGeneradas[i] = semillas[i];
        
        for(int i=0;i<n;i++){
            semillasGeneradas[noSemillas+i] = (semillasGeneradas[i]+semillasGeneradas[noSemillas-1+i])% m;
            //System.out.println(semillasGeneradas[i]+" - "+semillasGeneradas[noSemillas-1+i]+" - "+m+"="+semillasGeneradas[noSemillas+i]);
            numeros[i] = (double) semillasGeneradas[noSemillas+i]/ (m-1);
            numeros[i] = (double)((int)(numeros[i]*10000))/10000;
        }
        
        return numeros;
    }
}
