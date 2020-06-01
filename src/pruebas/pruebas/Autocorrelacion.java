package pruebas.pruebas;

/**
 * @author dzp
 */
public class Autocorrelacion {
    int i_pos = 1; // se puede modificar
    int m = 5;
    int N = 0;
    double M = 0;
    int alfa = 0;
    double[] numeros;
    public String procedimiento;
    public String hipotesis;
    
    public Autocorrelacion(double[] numeros){
        this.numeros = numeros;
    }
    
    public boolean metodo() {
        procedimiento = "";
        hipotesis = "";
        
        N = numeros.length;
        
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

        //calculamos M
        M = ((double)(N - i_pos) / m) - 1;
        //Acercamos al entero mas cercano para cumplir el criterio
        M = Math.round(M);
        
        procedimiento += "\n\nM = "+M;
        
        
        //calculamos P_im
        return calcP_im(M, i_pos, m);
    }

    private boolean calcP_im(double M, int i_pos, int saltos) {
        double aux = 0, pim=0, pim2=0 ,auxpim=0;
        int k=0,_M = 0;
        double Ri = 0;
        double Rim = 0;
        
        for (int i = i_pos; i < numeros.length; i+=saltos) {
            if (k == 0 && _M == 0) {
                Ri = numeros[i-1]; //obtiene el primer salto
                aux = Ri;
                k++;
            } else {
                auxpim += aux * numeros[i-1];
                aux = numeros[i-1];  
            }
        }
        pim = ((double)(1/(M+1))*auxpim)-0.25;
        pim2 = Math.sqrt((13*M)+7)/(12*(M+1));
        double z = calcEstadistico(pim, pim2);
        z = Math.abs(z);
        
        hipotesis += "\nα = "+alfa;
        hipotesis += "\nz = "+z;
        
        if(-alfa <= z && z <= alfa){
            hipotesis += "\n-"+alfa+" <= "+z+" && "+z+" <= "+alfa;
            System.out.println("");
            hipotesis += "\nHo La hipotesis nula se acepta.";
            return true;
        }else{
            hipotesis += "\n-"+alfa+" > "+z+" || "+z+" > "+alfa;
            hipotesis += "\nH1 La hipotesis alterna se cumple.";
            return false;
        }
    }

    private double calcEstadistico(double pim, double pim2) {
        double estadistico=0;
        estadistico = pim / pim2;
        
        return estadistico;
    }
}
