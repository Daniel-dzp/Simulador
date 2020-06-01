/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas.pruebas;

/**
 *
 * @author dzp
 */
public class Corridas {
    double numeros[];
    public String procedimiento;
    public String hipotesis;
    
    
    public Corridas(double numeros[]){
        this.numeros = numeros;
        
        procedimiento = "";
        hipotesis = "";
    }
    
    public boolean metodo(){
        int no_corridas=1;
        double media, varianza,z;
        char [] secuencia = new char[numeros.length-1];
        
        hipotesis = "N = "+numeros.length;
        
        // mostrar numeros
        procedimiento += "Numero de datos(N) = "+numeros.length+"\n";
        procedimiento += "Datos"+"\n";
        for(int i=0;i<numeros.length/15+1;i++){
            for(int j=0;j<15;j++)
                if(numeros.length> i*15+j)
                    procedimiento += String.format("%1.4f", numeros[i*15+j])+"  ";
            procedimiento += "\n";
        }
        
        procedimiento += "\n";
        
        //Calcula secuancia
        for(int i=1; i<numeros.length; i++)
        {
            if(numeros[i-1]>numeros[i])
            {
                secuencia[i-1]='-';
            }
            else
            {
                secuencia[i-1]='+';
            }
            procedimiento += secuencia[i-1]+" | ";
        }
        //Calcula número de corridas
        for(int i=1; i<secuencia.length; i++)
        {
            if(secuencia[i]!=secuencia[i-1]){no_corridas++; }
        }
        
        procedimiento +="\n\nCorridas: "+no_corridas;

        //Calcular la media, la varianza y el valor Z
        media = (2*numeros.length-1)/3;
        hipotesis += "\nmedia = "+media;
        varianza = (16*numeros.length-29)/90;
        hipotesis += "\nvarianza = "+varianza;
        hipotesis += "\nNumero de Corridas = "+no_corridas;
        z=(no_corridas-media)/Math.sqrt(varianza);
        hipotesis += "\nz= "+z;
        //Validar la hipotesis nula comparando con la distribución normal de alfa/2
        if(z<=1.96)
        {
            hipotesis += "\nz<=1.96";
            hipotesis += "\nLos datos son estadísticamente independientes";
            return true;
        }
        else
        {
            hipotesis += "\nz>1.96";
            hipotesis += "\nLos datos no son estadísticamente independientes";
            return false;
        }
    }
}
