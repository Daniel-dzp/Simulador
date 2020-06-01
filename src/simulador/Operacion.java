package simulador;

/**
 * @author dzp
 */
public class Operacion {
    String nombre;
    int tiempo; // parametros
    int frecuencia;
    String distribucion;

    public Operacion(String nombre, int tiempo, int frecuencia) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.frecuencia = frecuencia;
        distribucion = "Uniforme";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTiempo() {
        return FormatoFecha.segAHora(tiempo);
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getDistribucion() {
        return distribucion;
    }

    public void setDistribucion(String distribucion) {
        this.distribucion = distribucion;
    }
    
    
}
