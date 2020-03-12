package pruebas.pruebas;
/**
 * @author dzp
 */
public class Promedios {
    double numeros[];
    public String procedimiento;
    public String hipotesis;
    public int alfa;
    public boolean correcta=false;
    
    public Promedios(double numeros[], int alfa){
        this.numeros = numeros;
        this.alfa = alfa;
        procedimiento = "";
        hipotesis = "";
    }
    
    public void metodo()
    {
        double media, z;
        double contador = 0;
        int n;
        
        n = numeros.length;
        
        for (int i = 0; i < n ; i++)
        {
            contador += numeros[i];
            procedimiento += (i+1)+"   "+numeros[i]+"\n";
        }
        
        media = contador/n;
        
        z = ((media - 0.5)* Math.sqrt(n))/(Math.sqrt(1.0/12.0));
        
        System.out.println(((media - 0.5)* Math.sqrt(n)));
        System.out.println((Math.sqrt(1.0/12)));
        
        hipotesis += "media="+contador+"/"+n+"\n ="+media+"\n";
        hipotesis += "Z=((media - 1/2)√N)/√(12)="+z+"\n";
        hipotesis += "Z0=|Z|="+Math.abs(z)+"\n";
        hipotesis += "α = "+alfa+"\n";
        hipotesis += "N = "+n+"\n";
        hipotesis += "Condición de aceptabilidad:\n |Z0|<=Zα/2, N-1\n";
        z=Math.abs(z);
        if (z<=1.96)
        {
            hipotesis +="Ho La hipotesis NULA es aceptada";
            correcta=true;
        }
        else
        {
            hipotesis +="H1 La hipotesis ALTERNA es aceptada";
            correcta=false;
        }
    }
}
