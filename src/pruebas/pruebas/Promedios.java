package pruebas.pruebas;
/**
 * @author dzp
 */
public class Promedios {
    float numeros[];
    float tabla[];
    float media;
    float z;
    float a;
    int n;
    
    public Promedios(float numeros[], int a){
        this.numeros = numeros;
        this.n = numeros.length;
    }
    
    public void calcular(){
        float suma=0;
        float sumaTotal=0;
        
        this.tabla = new float[this.n];
        
        for(int i=0;i<this.numeros.length;i++)
            suma += this.numeros[i];
        
        media = suma/numeros.length;
        
        for(int i=0;i<this.numeros.length;i++){
            this.tabla[i] = (float) ((media-1.0/2)*(Math.pow(n,1.0/2))/(Math.pow(1.0/12, 1.0/2)));
            this.z += this.tabla[i];
        }
    }
    
    public String salida(){
        String salida="";
        
        return null;
    }
}
