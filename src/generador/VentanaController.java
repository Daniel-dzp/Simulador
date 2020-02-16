package generador;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author dzp
 */
public class VentanaController implements Initializable {

    @FXML    private AnchorPane mixtoGenerar;
    @FXML    private Button mixtoInfo;
    @FXML    private TextField mixtoSemilla;
    @FXML    private TextField mixtoA;
    @FXML    private TextField mixtoM;
    @FXML    private TextField mixtoC;
    @FXML    private TextField mixtoN;
    @FXML    private TextField visualizarN;
    @FXML    private TableView<String[]> tabla;
    @FXML    private Button guardar;
    
    Metodos metodos;
    Archivo archivo;
    
    double numeros[];
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        metodos = new Metodos();
        archivo = new Archivo();
        
        guardar.setVisible(false);
    }

    @FXML
    private void mixtoGenerar(ActionEvent event) {
        String semillaT, mT, aT, cT, nT;
        int semilla, m, a, c, n;
        
        semillaT = mixtoSemilla.getText();
        mT = mixtoM.getText();
        aT = mixtoA.getText();
        cT = mixtoC.getText();
        nT = mixtoN.getText();
        
        try{
            semilla = Integer.parseInt(semillaT);
            m = Integer.parseInt(mT);
            a = Integer.parseInt(aT);
            c = Integer.parseInt(cT);
            n = Integer.parseInt(nT);
            
            if(semilla>0 && m>0 && a>0 && c>0 && n>0
                    && m>semilla && m>a && m>c){
                
                numeros = metodos.mixto(semilla, a, m, c, n);
                mostrarTabla();
                guardar.setVisible(true);
            }
            else
                mensajeError("Error",
                        "Xn+1 = (a Xn + c) mod m, n>=0\n\n"
                        + "Xo = Semilla(Xo>0)\n"
                        + "a = Multiplicador(a>0)\n"
                        + "c = Constante (c>0)\n"
                        + "m = Modulo(m>Xo, m>a y m>c)\n");
            
        }catch(NumberFormatException  e){
            mensajeError("Error", "Introduce solo números enteros");
        }
        
        
    }
    
    public void mostrarTabla()
    {
        String[][] matrizNumeros;
        
        tabla.getColumns().clear();
        if(numeros != null){
            matrizNumeros = new String[(int)Math.ceil(numeros.length/10.0)+1][10];

            for(int i=0;i<10;i++)
                matrizNumeros[0][i] = (i+1)+""; 
            for(int i=0;i<(int)Math.ceil(numeros.length/10.0);i++){
                for(int j=0;j<10;j++){
                    if(numeros.length>i*10+j)
                        matrizNumeros[i+1][j] = ""+numeros[i*10+j];
                }
            }

            ObservableList<String[]> data = FXCollections.observableArrayList();
            data.addAll(Arrays.asList(matrizNumeros));
            data.remove(0);

            for (int i = 0; i < matrizNumeros[0].length; i++) {
                TableColumn tc = new TableColumn(matrizNumeros[0][i]);
                final int colNo = i;
                tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                        return new SimpleStringProperty((p.getValue()[colNo]));
                    }
                });
                tc.setPrefWidth(56);
                tabla.getColumns().add(tc);
            }
            tabla.setItems(data);
        }
        else
            mensajeError("Error", archivo.errorMensaje);
        
        if(archivo.error)
            mensajeError("Error", archivo.errorMensaje);
    }

    @FXML
    private void guardar(ActionEvent event) throws IOException {
        archivo.guardar(numeros);
        guardar.setVisible(false);
        mensajeInformativo("Numeros","Se guardaron correctamente los números");
    }
    
    @FXML
    private void visualizarMostrar(ActionEvent event) throws IOException {
        String nT;
        int n;
        
        nT = visualizarN.getText();
        
        try{
            n = Integer.parseInt(nT);
            
            if(n >= 1)
            {
                numeros = archivo.leer(n);
                mostrarTabla();
            }
            else
                mensajeError("Error", "Introduce solo números enteros mayores o igual a 1");
        }catch(NumberFormatException  e){
            mensajeError("Error", "Introduce solo números enteros mayores o igual a 1");
        }
    }

    @FXML
    private void tabCambio(Event event) {
        if(numeros != null){
            numeros = null;
            tabla.getColumns().clear();
            guardar.setVisible(false);
        }
    }
    
    
    
    public void mensajeInformativo(String titulo, String contenido)
    {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(contenido);
        dialogo.initStyle(StageStyle.UTILITY);
        
        dialogo.showAndWait();
    }
    
    public void mensajeError(String titulo, String contenido)
    {
        Alert dialogo = new Alert(Alert.AlertType.ERROR);
        
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(contenido);
        dialogo.initStyle(StageStyle.UTILITY);
        
        dialogo.showAndWait();
    }

    @FXML
    private void mixtoInfo(ActionEvent event) {
        mensajeInformativo("Método congruencial mixto",
                "Xn+1 = (a Xn + c) mod m, n>=0\n\n"
                + "Xo = Semilla(Xo>0)\n"
                        + "a = Multiplicador(a>0)\n"
                        + "c = Constante (c>0)\n"
                        + "m = Modulo(m>Xo, m>a y m>c)\n");
    }
}
