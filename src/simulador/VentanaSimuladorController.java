package simulador;

import io.Archivo;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mensajes.Mensajes;
/**
 * @author dzp
 */
public class VentanaSimuladorController implements Initializable {

    @FXML
    private TableView<FilaFrecuencia> tablaFrecuencias;
    @FXML
    private TextField media;
    @FXML
    private TextField desviacion;
    @FXML
    private TextField usuarios;
    @FXML
    private TableView<Fila> tabla;
    
    
    Simulador simulador;
    Archivo archivo;
    
    @FXML
    private TextField horaInicio;
    @FXML
    private TableView<FilaRango> tablaRango;
    @FXML
    private TableView<Operacion> tablaOperaciones;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        long noDatos;
        double[] numeros;
        
        archivo = new Archivo("numerosIndependientes.bin");
        
        noDatos = archivo.noDatos();
        numeros = archivo.leer((int)noDatos);
        
        simulador = new Simulador(numeros);
        
        TableColumn usuario = new TableColumn("Usuario");
        usuario.setCellValueFactory(new PropertyValueFactory("usuario"));
        
        TableColumn horaLlegada = new TableColumn("Hra. de llegada");
        horaLlegada.setCellValueFactory(new PropertyValueFactory("horaLlegada"));
        
        TableColumn noAleatorio1 = new TableColumn("No Aleatorio 1");
        noAleatorio1.setCellValueFactory(new PropertyValueFactory("noAleatorio1"));
        
        TableColumn tiempoLlegada = new TableColumn("Tiempo/Lleagada");
        tiempoLlegada.setCellValueFactory(new PropertyValueFactory("tiempoLlegada"));
        
        TableColumn tiempoAtencion = new TableColumn("Hra. de atención");
        tiempoAtencion.setCellValueFactory(new PropertyValueFactory("tiempoAtencion"));
        
        TableColumn tiempoEspera = new TableColumn("Tiempo de espera");
        tiempoEspera.setCellValueFactory(new PropertyValueFactory("tiempoEspera"));
        
        TableColumn operacion = new TableColumn("Operacion");
        operacion.setCellValueFactory(new PropertyValueFactory("operacion"));
        
        TableColumn tiempoOperacion = new TableColumn("Tiempo de op.");
        tiempoOperacion.setCellValueFactory(new PropertyValueFactory("tiempoOperacion"));
        
        TableColumn noAleatorio2 = new TableColumn("No Aleactorio 2");
        noAleatorio2.setCellValueFactory(new PropertyValueFactory("noAleatorio2"));
        
        TableColumn horaSalida = new TableColumn("Hora de salida");
        horaSalida.setCellValueFactory(new PropertyValueFactory("horaSalida"));
        
        tabla.getColumns().addAll(usuario, horaLlegada, noAleatorio1, tiempoLlegada, tiempoAtencion, tiempoEspera, operacion, tiempoOperacion, noAleatorio2, horaSalida);
    
        //TableColumn fx = new TableColumn("Fx");
        TableColumn fx = new TableColumn("Fx");
        fx.setCellValueFactory(new PropertyValueFactory("valor"));
        tablaRango.getColumns().add(fx);
        
        TableColumn x = new TableColumn("X");
        x.setCellValueFactory(new PropertyValueFactory("x"));
        TableColumn retiro = new TableColumn("Retiro");
        retiro.setCellValueFactory(new PropertyValueFactory("retiro"));
        TableColumn consulta = new TableColumn("Consulta");
        consulta.setCellValueFactory(new PropertyValueFactory("consulta"));
        TableColumn transferencia = new TableColumn("Transferencia");
        transferencia.setCellValueFactory(new PropertyValueFactory("transferencia"));
        TableColumn otros = new TableColumn("Otros");
        otros.setCellValueFactory(new PropertyValueFactory("otros"));
        
        tablaFrecuencias.getColumns().addAll(x, retiro, consulta, transferencia, otros);
        
        TableColumn op = new TableColumn("operacion");
        op.setCellValueFactory(new PropertyValueFactory("nombre"));
        TableColumn distribucion = new TableColumn("Distribución");
        distribucion.setCellValueFactory(new PropertyValueFactory("distribucion"));
        TableColumn paramentros = new TableColumn("Paramentros");
        paramentros.setCellValueFactory(new PropertyValueFactory("tiempo"));
        tablaOperaciones.getColumns().addAll(op, distribucion, paramentros);
    }    

    @FXML
    private void simular(ActionEvent event) {
        ArrayList<Fila> t;
        int n;
        int med;
        int desv;
        int inicio;
        double [][]tabla;
        
        try{
            n = Integer.parseInt(usuarios.getText());
            inicio = FormatoFecha.HoraASeg(horaInicio.getText());
            med = FormatoFecha.HoraASeg(media.getText());
            desv = FormatoFecha.HoraASeg(desviacion.getText());
            
            
            if(inicio>=0 && desv>=0 && med>=0)
            {
                tabla = simulador.simular(n, med, desv, inicio);
                t = new ArrayList();
                for(int i=0;i<tabla.length;i++)
                {
                    System.out.println(
                            FormatoFecha.segAHora((int)tabla[i][0])+" - "
                            +String.format("%1.4f", tabla[i][1])+" - "
                            +FormatoFecha.segAHora((int)tabla[i][2])+" - "
                            +FormatoFecha.segAHora((int)tabla[i][3])+" - "
                            +FormatoFecha.segAHora((int)tabla[i][4])+" - "
                            +String.format("%-15s",simulador.operaciones.get((int)tabla[i][5]).nombre)+" - "
                            +FormatoFecha.segAHora((int)tabla[i][6])+" - "
                            +String.format("%1.4f", tabla[i][7])+" - "
                            +FormatoFecha.segAHora((int)tabla[i][8]));
                    t.add(new Fila(i+1,
                            FormatoFecha.segAHora((int)tabla[i][0]),
                            tabla[i][1],
                            FormatoFecha.segAHora((int)tabla[i][2]),
                            FormatoFecha.segAHora((int)tabla[i][3]),
                            FormatoFecha.segAHora((int)tabla[i][4]),
                            simulador.operaciones.get((int)tabla[i][5]).nombre,
                            FormatoFecha.segAHora((int)tabla[i][6]),
                            tabla[i][7],
                            FormatoFecha.segAHora((int)tabla[i][8])));
                    
                }
                this.tabla.getItems().addAll(t);
                this.tabla.setStyle("-fx-alignment: CENTER-RIGHT;");
                
                this.tablaRango.getItems().clear();
                for(int i=0;i<simulador.fAcomulada.length;i++)
                    this.tablaRango.getItems().add(new FilaRango(String.format("%2.4f", simulador.fAcomulada[i])));
                
                
                this.tablaFrecuencias.getItems().clear();
                this.tablaFrecuencias.getItems().add(new FilaFrecuencia("f", simulador.operaciones.get(0).frecuencia, simulador.operaciones.get(1).frecuencia, simulador.operaciones.get(2).frecuencia, simulador.operaciones.get(3).frecuencia));
                this.tablaFrecuencias.getItems().add(new FilaFrecuencia("f(x)", simulador.fRelativa[0], simulador.fRelativa[1], simulador.fRelativa[2], simulador.fRelativa[3]));
                this.tablaFrecuencias.getItems().add(new FilaFrecuencia("f(x)", simulador.fAcomulada[0], simulador.fAcomulada[1], simulador.fAcomulada[2], simulador.fAcomulada[3]));
                
                this.tablaOperaciones.getItems().addAll(simulador.operaciones);
            }
            else
                Mensajes.mensajeInformativo("Error","Error formato de tiempo erroneo");
        }
        catch(NumberFormatException  e){
            Mensajes.mensajeInformativo("Error","Error no de usuarios debe ser entero");
        }
        
        
        
        
    }
    
}
