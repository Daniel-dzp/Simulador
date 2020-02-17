package generador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author dzp
 */
public class Archivo{
    public String errorMensaje = "";
    public boolean error = false;
    
    public Archivo(){
    
    }
    
    public void guardar(double datos[]) throws IOException
    {   
        //FileWriter archivo = new FileWriter("binario.bin");
        FileOutputStream arch = new FileOutputStream("binario.bin", true);
        DataOutputStream archivo = new DataOutputStream(arch);
        
        for(int i=0;i<datos.length;i++){
            archivo.writeInt(i+1);
            archivo.writeDouble(datos[i]);
        }
        
        error = false;
        archivo.close();
    }
    
    public double[] leer(int n){
        double numeros[] = null;
        int llave;
        int noBloques;
        int i=0;
        
        error = false;
        try{
            FileInputStream arch = new FileInputStream("binario.bin");
            DataInputStream archivo = new DataInputStream(arch);

            numeros = new double[n];
            for(i=0;i<n;i++)
            {
                llave = archivo.readInt();
                numeros[i] = archivo.readDouble();
            }
            archivo.close();
        }catch(FileNotFoundException e){
            error = true;
            errorMensaje = "No existe el archivo";
        }catch(IOException e){
            error = true;
            errorMensaje = "Solo existen "+i+" numeros guardados.";
        }
        return numeros;
    }
    
}
