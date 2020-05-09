package pruebas.pruebas;
/**
 * @author dzp
 */
public class ChiCuadrada {
    
    double numeros[];
    public String procedimiento;
    public String hipotesis;
    public int alfa;
    public boolean correcta=false;
    
    int noIntervalos;
    
    public ChiCuadrada(double numeros[],int noIntervalos,int alfa)
    {
        this.numeros = numeros;
        this.noIntervalos = noIntervalos;
        this.alfa = alfa;
        procedimiento = "";
        hipotesis = "";
    }
    
    public void metodo()
    {
        int longIntervalo;
        int N;
        double tabla[][];
        
        N=numeros.length;
        
        tabla = new double[noIntervalos][6];
        longIntervalo = (int) Math.ceil((double)(N/noIntervalos));
        double Xp = 0;
        
        for(int i=0;i<noIntervalos;i++){
            tabla[i][0] = i+1; // 1, 2, 3 ...
            tabla[i][1] = i*(1.0/noIntervalos); //Rango inferior
            tabla[i][2] = i*(1.0/noIntervalos)+(1.0/noIntervalos); // Rango superior
            // contar Fo
            for(int j=0;j<N;j++)
                if(tabla[i][1] <= numeros[j] && tabla[i][2]>numeros[j])
                    tabla[i][3]++;
            // FE
            tabla[i][4] = N / noIntervalos;
            // (Fo - FE)^2/FE
            tabla[i][5] = Math.pow((tabla[i][3]-tabla[i][4]),2)/tabla[i][4];
            // suma de X2p
            Xp += tabla[i][5];
        }
        // mostrar
        procedimiento += String.format("\n%-2s   %-9s   %-3s   %-3s   %-5s","i","Rango","Fo","FE","(Foi-FEi)^2/FEi");
        for(int i=0;i<noIntervalos;i++)
            procedimiento += String.format("\n%2.0f   %1.1f - %1.1f   %3.0f   %3.0f   %1.4f",tabla[i][0],tabla[i][1],tabla[i][2],tabla[i][3],tabla[i][4],tabla[i][5]);
        procedimiento +="\n";
        procedimiento +="X2p = "+Xp;
        //Hipotesis
        hipotesis += "X2p = "+Xp+"\n";
        hipotesis += "α = "+alfa+"\n";
        
        if(Xp<0.711)
        {
            hipotesis += "Ho La hipotesis nula se acepta.";
            correcta = true;
        }
        else
        {
            hipotesis += "H1 La hipotesis alterna se cumple.";
            correcta = false;
        }       
    }
}
