package pruebas.pruebas;

import Estadisticos.EstadisticoKolmogorov;

/**
 * @author dzp
 */
public class Kolmogorov {
    double numeros[];
    public String procedimiento;
    public String hipotesis;
    public boolean correcta=false;
    public int alfa;
    EstadisticoKolmogorov estadistico;
    
    public Kolmogorov(double numeros[], int alfa)
    {
        estadistico = new EstadisticoKolmogorov();
        this.numeros = numeros;
        this.alfa = alfa;
        procedimiento = "";
        hipotesis = "";
    }
    
    public void metodo()
    {
        int n;
        double tabla[][];
        double dmax=-1, dmenos=-1, d,Dalfa;
        
        n = numeros.length;
        tabla = new double[n][6];
        for(int i=0;i<n;i++)
        {
            // X
            tabla[i][0] = numeros[i];
            // i
            tabla[i][1] = i+1;
            // N
            tabla[i][2] = n;
            // i/N
            tabla[i][3] = tabla[i][1]/tabla[i][2];
            //D+  i/N - Xi
            tabla[i][4] = tabla[i][3] - tabla[i][0];
            //D-  Xi-((i-1)/N)
            tabla[i][5] = tabla[i][0] - ((tabla[i][1]-1)/tabla[i][2]);
            
            dmax = Math.max(dmax, tabla[i][4]);
            dmenos = Math.max(dmenos, tabla[i][5]);
        }
        
        procedimiento += String.format("%-6s  %-4s  %-4s  %-8s  %-15s  %-15s\n","X","i","N","i/N","D+ (i/n)-Xi","D- Xi((i-1)/N)");
        for(int i=0;i<tabla.length;i++)
        {
            procedimiento += String.format("%1.4f  ", tabla[i][0]);
            procedimiento += String.format("%4.0f  ", tabla[i][1]);
            procedimiento += String.format("%4.0f  ", tabla[i][2]);
            procedimiento += String.format("%1.4f%2s  ", tabla[i][3],"");
            procedimiento += String.format("%1.4f%9s  ", tabla[i][4],"");
            procedimiento += String.format("%1.4f\n", tabla[i][5]);
        }
        
        //hipotesis
        dmax = Math.abs(dmax);
        dmenos = Math.abs(dmenos);
        Dalfa = estadistico.estadistico(alfa, n);
        hipotesis += "|Max D+| = "+String.format("%1.4f", dmax)+"\n";
        hipotesis += "|Max D-| = "+String.format("%1.4f", dmenos)+"\n";
        hipotesis += "α = "+alfa+"\n";
        d = Math.max(dmax, dmenos);
        hipotesis += "|Max D+,D-| = "+d+" = Dn\n";
        if(n>100)
        {
            switch(alfa)
            {
                case 10:
                    hipotesis += String.format("Dα = 1.22/√(N)\n\t= "+"1.22/√("+n+")\n\t= "+Dalfa+"\n");
                    break;
                case 5: 
                    hipotesis += String.format("Dα = 1.36/√(N)\n\t= "+"1.36/√("+n+")\n\t= "+Dalfa+"\n");
                    break;
                case 1: 
                    hipotesis += String.format("Dα = 1.63/√(N)\n\t= "+"1.63/√("+n+")\n\t= "+Dalfa+"\n");
                    break;
            }
        }
        else
        {
            hipotesis += String.format("Dα = "+Dalfa+"\n");
        }
        
        if(d<Dalfa)
        {
            hipotesis += String.format("Ho (Dn<Dα,n) \n los datos siguen una distrubución uniforme."+"\n");
            correcta = true;
        }
        else
        {
            hipotesis += String.format("H1 (Dn>=Dα,n) \n los datos no siguen una distrubución uniforme."+"\n");
            correcta = false;
        }
    }
}
