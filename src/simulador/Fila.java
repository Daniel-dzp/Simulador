/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

/**
 *
 * @author dzp
 */
public class Fila {
    int usuario;
    String horaLlegada;
    double noAleatorio1;
    String tiempoLlegada;
    String tiempoAtencion;
    String tiempoEspera;
    String operacion;
    String tiempoOperacion;
    double noAleatorio2;
    String horaSalida;

    public Fila(int usuario, String horaLlegada, double noAleatorio1, String tiempoLlegada, String tiempoAtencion, String tiempoEspera, String operacion, String tiempoOperacion, double noAleatorio2, String horaSalida) {
        this.usuario = usuario;
        this.horaLlegada = horaLlegada;
        this.noAleatorio1 = noAleatorio1;
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoAtencion = tiempoAtencion;
        this.tiempoEspera = tiempoEspera;
        this.operacion = operacion;
        this.tiempoOperacion = tiempoOperacion;
        this.noAleatorio2 = noAleatorio2;
        this.horaSalida = horaSalida;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public double getNoAleatorio1() {
        return noAleatorio1;
    }

    public void setNoAleatorio1(double noAleatorio1) {
        this.noAleatorio1 = noAleatorio1;
    }

    public String getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(String tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public String getTiempoAtencion() {
        return tiempoAtencion;
    }

    public void setTiempoAtencion(String tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public String getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(String tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getTiempoOperacion() {
        return tiempoOperacion;
    }

    public void setTiempoOperacion(String tiempoOperacion) {
        this.tiempoOperacion = tiempoOperacion;
    }

    public double getNoAleatorio2() {
        return noAleatorio2;
    }

    public void setNoAleatorio2(double noAleatorio2) {
        this.noAleatorio2 = noAleatorio2;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }
    
    
}
