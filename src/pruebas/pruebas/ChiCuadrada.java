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
    
    }
}
