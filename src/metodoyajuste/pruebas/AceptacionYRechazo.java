package metodoyajuste.pruebas;

import java.util.ArrayList;

/**
 * @author dzp
 */
public class AceptacionYRechazo {
    ArrayList<Double> rechazados;
    ArrayList<Double> aceptados;
    
    public void AceptacionYRechazo(){
        rechazados = new ArrayList<>();
        aceptados = new ArrayList<>();
    }
    
    public ArrayList<Double> AR(double []numerosLineales)
    {
        double r1, r2;
        double x;
        double f;
        ArrayList<Double> numerosUniformes = new ArrayList<>();
        
        rechazados = new ArrayList<>();
        
        for(int i=0;i<numerosLineales.length/2;i+=2)
        {
            // Paso 0
            r1 = numerosLineales[i];
            
            // Paso 1
            x = r1 * 2;
            
            //Paso 2
            if(x >= 0 && x < 1){ //[0,1)
                f = x;
            }else if(x >= 1 && x <= 2){  //[1,2)
                f = 1 - (x - 1);
            }else{ // cualquier otro valor
                f = 0;
            }
            
            // Paso 3 (Generar r2)
            r2 = numerosLineales[i+1];
            
            // Paso final
            if(r2 <= f){
                // guardar valor
                f = (double)((int)(f*10000))/10000;
                numerosUniformes.add(f);
            }
            else
            {
                rechazados.add(r1);
                rechazados.add(r2);
            }
        }
        
        
        aceptados = numerosUniformes;
        
        return numerosUniformes;
        
    }
    
    @Override
    public String toString()
    {
        String salida = "Numeros Aceptados\n";
        
        for(Double n: aceptados)
        {
            salida += n+"\n";
        }
        
        salida +="Numeros Rechazados\n";
        
        for(Double n: rechazados)
        {
            salida += n+"\n";
        }
        
        return salida;
    }
}
