package simulador;

import java.util.ArrayList;

/**
 * @author dzp
 */
public class Simulador {
    ArrayList<Operacion> operaciones;
    double[] numerosAleatorios;
    int n;
    double fRelativa[], fAcomulada[];
    
    public Simulador(double[] numerosAleatorios){
        this.numerosAleatorios = numerosAleatorios;
        
        operaciones = new ArrayList();
        operaciones.add(new Operacion("Retiro", 240, 57));
        operaciones.add(new Operacion("Consulta", 120,47));
        operaciones.add(new Operacion("Transferencia", 420, 47));
        operaciones.add(new Operacion("Otros", 160, 47));
    }
    
    public double[][] simular(int noClientes, double media, double desviacion, int horaInicio){
        
        double suma;
        double [][]tabla;
        
        fRelativa = new double[4];
        fAcomulada = new double[4];
        
        this.n=0;
        
        suma = 0;
        for(Operacion o: operaciones)
            suma += o.frecuencia;
  
        for(int i=0;i<4;i++)
        {
            fRelativa[i] = operaciones.get(i).frecuencia/suma;
            fAcomulada[i] = fRelativa[i]+(i==0 ? 0.0 : fAcomulada[i-1]);
        }
        
        tabla = new double[noClientes][9];
        
        for(int i=0;i<noClientes;i++){
            // hora de llegada
            tabla[i][0] = i==0 ? horaInicio : tabla[i-1][0]+tabla[i-1][2];
            // numero aleatorio;
            tabla[i][1] = obtenerNumeroAleatorio();
            // tiempo entre llegada
            tabla[i][2] = distribucionNormalInversa(tabla[i][1], media, desviacion);
            // hora de atencion
            tabla[i][3] = i==0 ? tabla[i][0]
                    :(tabla[i][0] > tabla[i-1][8]? tabla[i][0] : tabla[i-1][8] );
            // tiempo de espera
            tabla[i][4] = tabla[i][3] - tabla[i][0];
            // numero aleatorio
            tabla[i][7] = obtenerNumeroAleatorio();
            // operacion seleccionada
            tabla[i][5] = seleccionarOperacion(tabla[i][7], fAcomulada); 
            // tiempo de operacion
            tabla[i][6] = operaciones.get((int)tabla[i][5]).tiempo;
            // hora de salida
            tabla[i][8] = tabla[i][3] + tabla[i][6];
        }
        
        return tabla;
    }
    
    public double obtenerNumeroAleatorio(){
        //return Math.random();
        Double numero;
        
        if(numerosAleatorios.length>n)
        {
            numero = numerosAleatorios[n];
            n++;
            return numero; 
        }
        else
        {
            n = 0;
            return numerosAleatorios[n];
        }
        
    }
    
    //no se supo como sacar
    public double distribucionNormalInversa(double noAleatorio, double media, double desviacion){
        return noAleatorio*(desviacion*5)+(media - (desviacion*5)/2);
    }
    
    public int seleccionarOperacion(double noAleatorio, double[] fAcomulada){
        if(0.0 <=noAleatorio && fAcomulada[0]> noAleatorio)
            return 0;
        if(fAcomulada[0]<=noAleatorio && fAcomulada[1]> noAleatorio)
            return 1;
        if(fAcomulada[1]<=noAleatorio && fAcomulada[2]> noAleatorio)
            return 2;
        if(fAcomulada[2]<=noAleatorio && fAcomulada[3]> noAleatorio)
            return 3;
        return 3;
    }
}
