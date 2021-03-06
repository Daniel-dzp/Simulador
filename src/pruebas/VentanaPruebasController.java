package pruebas;


import io.Archivo;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import mensajes.Mensajes;
import principal.MenuController;
import pruebas.pruebas.ChiCuadrada;
import pruebas.pruebas.Autocorrelacion;
import pruebas.pruebas.Corridas;
import pruebas.pruebas.Huecos;
import pruebas.pruebas.Kolmogorov;
import pruebas.pruebas.Poker;
import pruebas.pruebas.Promedios;

/**
 * @author dzp
 */
public class VentanaPruebasController implements Initializable {

    @FXML    private CheckBox pruebaP;
    @FXML    private CheckBox pruebaChi;
    @FXML    private CheckBox pruebaK;
    @FXML    private CheckBox pruebaC;
    @FXML    private CheckBox pruebaH;
    @FXML    private CheckBox pruebaA;
    @FXML    private CheckBox pruebaPo;
    @FXML    private CheckBox seleccionarTodos;
    
    
    
    Archivo archivo;
    
    double numeros[];
    
    @FXML    private TextField entradaN;
    @FXML    private TableView<String[]> tabla;
    @FXML    private CheckBox resultadoP;
    @FXML    private CheckBox resultadoChi;
    @FXML    private CheckBox resultadoK;
    @FXML    private CheckBox resultadoC;
    @FXML    private CheckBox resultadoH;
    @FXML    private CheckBox resultadoA;
    @FXML    private CheckBox resultadoPo;
    @FXML    private Button detallesP;
    @FXML    private Button detallesChi;
    @FXML    private Button detallesK;
    @FXML    private Button detallesC;
    @FXML    private Button detallesH;
    @FXML    private Button detallesA;
    @FXML    private Button detallesPo;
    @FXML    private Button verificar;
    @FXML    private Button guardar;
    
    Promedios promedios;
    ChiCuadrada chiCuadrada;
    Kolmogorov kolmogorov;
    Huecos huecos;
    Corridas corridas;
    Poker poker;
    Autocorrelacion autocorrelacion;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        archivo = new Archivo("numerosSeudoAleatorios.bin");
        
        
    }

    @FXML
    private void seleccionarTodos(ActionEvent event) {
        boolean valor = seleccionarTodos.selectedProperty().getValue();
        pruebaP.setSelected(valor);
        pruebaChi.setSelected(valor);
        pruebaK.setSelected(valor);
        pruebaC.setSelected(valor);
        pruebaH.setSelected(valor);
        pruebaA.setSelected(valor);
        pruebaPo.setSelected(valor);
    }

    @FXML
    private void verificar(ActionEvent event) {
        detallesP.setDisable(true);
        detallesChi.setDisable(true);
        detallesK.setDisable(true);
        detallesC.setDisable(true);
        detallesH.setDisable(true);
        detallesA.setDisable(true);
        detallesPo.setDisable(true);
        
        resultadoP.setSelected(false);
        resultadoChi.setSelected(false);
        resultadoK.setSelected(false);
        resultadoC.setSelected(false);
        resultadoH.setSelected(false);
        resultadoA.setSelected(false);
        resultadoPo.setSelected(false);
        
        resultadoP.setIndeterminate(false);
        resultadoChi.setIndeterminate(false);
        resultadoK.setIndeterminate(false);
        resultadoC.setIndeterminate(false);
        resultadoH.setIndeterminate(false);
        resultadoA.setIndeterminate(false);
        resultadoPo.setIndeterminate(false);
        
        
        
        if(pruebaP.selectedProperty().getValue())
        {
            detallesP.setDisable(false);
            pruebaPromedios();
        }
        if(pruebaChi.selectedProperty().getValue())
        {
            detallesChi.setDisable(false);
            pruebaChiCuadrada();
        }
        if(pruebaK.selectedProperty().getValue())
        {
            detallesK.setDisable(false);
            pruebaKolmogorov();
        }
        if(pruebaC.selectedProperty().getValue())
        {
            detallesC.setDisable(false);
            pruebaCorridas();
        }
        if(pruebaH.selectedProperty().getValue())
        {
            detallesH.setDisable(false);
            pruebaHuecos();
        }
        if(pruebaA.selectedProperty().getValue())
        {
            detallesA.setDisable(false);
            pruebaAutocorrelacion();
        }
        if(pruebaPo.selectedProperty().getValue())
        {
            detallesPo.setDisable(false);
            pruebaPoker();
        }
        
        guardar.setDisable(false);
    }
    
    private void pruebaPromedios(){
        promedios = new Promedios(numeros, 5);
        promedios.metodo();
        if(promedios.correcta)
            resultadoP.setSelected(true);
        else
            resultadoP.setIndeterminate(true);
    }
    
    private void pruebaChiCuadrada(){
        chiCuadrada = new ChiCuadrada(numeros, 5, 5);
        chiCuadrada.metodo();
        if(chiCuadrada.correcta)
            resultadoChi.setSelected(true);
        else
            resultadoChi.setIndeterminate(true);
    }
    
    private void pruebaKolmogorov(){
        kolmogorov = new Kolmogorov(numeros, 5);
        kolmogorov.metodo();
        if(kolmogorov.correcta)
            resultadoK.setSelected(true);
        else
            resultadoK.setIndeterminate(true);
        
    }
    
    private void pruebaHuecos(){
        huecos = new Huecos(numeros, true, 4, 12, 5);
        huecos.metodo();
        if(huecos.correcta)
            resultadoH.setSelected(true);
        else
            resultadoH.setIndeterminate(true);
    }
    
    private void pruebaCorridas(){
        corridas = new Corridas(numeros);
        if(corridas.metodo())
            resultadoC.setSelected(true);
        else
            resultadoC.setIndeterminate(true);
    }
    
    private void pruebaPoker(){
        poker = new Poker(numeros);
        
        if(poker.metodo())
            resultadoPo.setSelected(true);
        else
            resultadoPo.setIndeterminate(true);
    }
    
    private void pruebaAutocorrelacion(){
        autocorrelacion = new Autocorrelacion(numeros);
        
        if(autocorrelacion.metodo())
            resultadoA.setSelected(true);
        else
            resultadoA.setIndeterminate(true);
    }

    @FXML
    private void guardar(ActionEvent event) throws IOException {
        archivo.guardar(numeros,"numerosAleatorios.bin");
        guardar.setDisable(true);
        Mensajes.mensajeInformativo("Numeros","Se guardaron correctamente los números");
    }

    @FXML
    private void desSeleccionarTodos(ActionEvent event) {
        seleccionarTodos.setSelected(false);
    }

    @FXML
    private void cargarNumeros(ActionEvent event) {
        String nT;
        int n;
        
        nT = entradaN.getText();
        
        try{
            n = Integer.parseInt(nT);
            
            if(n >= 1)
            {
                numeros = archivo.leer(n);
                mostrarTabla();
                
                verificar.setDisable(false);
            }
            else
                Mensajes.mensajeError("Error", "Introduce solo números enteros mayores o igual a 1");
        }catch(NumberFormatException  e){
            Mensajes.mensajeError("Error", "Introduce solo números enteros mayores o igual a 1");
        }
    }
    
    private void mostrarTabla()
    {
        String[][] matrizNumeros;
        
        tabla.getColumns().clear();
        if(numeros != null){
            matrizNumeros = new String[(int)Math.ceil(numeros.length/6.0)+1][6];

            for(int i=0;i<6;i++)
                matrizNumeros[0][i] = (i+1)+""; 
            for(int i=0;i<(int)Math.ceil(numeros.length/6.0);i++){
                for(int j=0;j<6;j++){
                    if(numeros.length>i*6+j)
                        matrizNumeros[i+1][j] = ""+numeros[i*6+j];
                }
            }

            ObservableList<String[]> data = FXCollections.observableArrayList();
            data.addAll(Arrays.asList(matrizNumeros));
            data.remove(0);

            for (int i = 0; i < matrizNumeros[0].length; i++) {
                TableColumn tc = new TableColumn(matrizNumeros[0][i]);
                final int colNo = i;
                tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                        return new SimpleStringProperty((p.getValue()[colNo]));
                    }
                });
                tc.setPrefWidth(56);
                tabla.getColumns().add(tc);
            }
            tabla.setItems(data);
        }
        //else
        //    mensajeError("Error", archivo.errorMensaje);
        
        if(archivo.error)
            Mensajes.mensajeError("Error", archivo.errorMensaje);
    }

    @FXML
    private void detallesP(ActionEvent event) {
        ventanaDetalles(promedios.procedimiento, promedios.hipotesis);
    }

    @FXML
    private void detallesChi(ActionEvent event) {
        ventanaDetalles(chiCuadrada.procedimiento, chiCuadrada.hipotesis);
    }

    @FXML
    private void detallesK(ActionEvent event) {
        ventanaDetalles(kolmogorov.procedimiento, kolmogorov.hipotesis);
    }

    @FXML
    private void detallesC(ActionEvent event) {
        ventanaDetalles(corridas.procedimiento, corridas.hipotesis);
    }

    @FXML
    private void detallesH(ActionEvent event) {
        ventanaDetalles(huecos.procedimiento, huecos.hipotesis);
    }
    
    @FXML
    private void detallesA(ActionEvent event) {
        ventanaDetalles(autocorrelacion.procedimiento, autocorrelacion.hipotesis);
    }

    @FXML
    private void detallesPo(ActionEvent event) {
        ventanaDetalles(poker.procedimiento, poker.hipotesis);
    }
    
    private void ventanaDetalles(String procedimiento, String hipotesis)
    {
        FXMLLoader loader = new FXMLLoader();
        try
        {
            loader.setLocation(getClass().getResource("/pruebas/VentanaDetalles.fxml"));
            loader.load();
            VentanaDetallesController document = loader.getController();
            document.setString(procedimiento,hipotesis);
            Parent p = loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            s.show();
        }catch(IOException e2)
        {
            e2.printStackTrace();
        }
    }

    @FXML
    private void mostrarTablas(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try
        {
            root = loader.load(getClass().getResource("/pruebas/Tablas.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(IOException e2)
        {
            e2.printStackTrace();
        }
    }
}
