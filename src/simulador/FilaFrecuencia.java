package simulador;

/**
 * @author dzp
 */
public class FilaFrecuencia {
    String x;
    double retiro;
    double consulta;
    double transferencia;
    double otros;

    public FilaFrecuencia(String x, double retiro, double consulta, double transferencia, double otros) {
        this.x = x;
        this.retiro = retiro;
        this.consulta = consulta;
        this.transferencia = transferencia;
        this.otros = otros;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public double getRetiro() {
        return retiro;
    }

    public void setRetiro(double retiro) {
        this.retiro = retiro;
    }

    public double getConsulta() {
        return consulta;
    }

    public void setConsulta(double consulta) {
        this.consulta = consulta;
    }

    public double getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(double transferencia) {
        this.transferencia = transferencia;
    }

    public double getOtros() {
        return otros;
    }

    public void setOtros(double otros) {
        this.otros = otros;
    }

    
    
}
