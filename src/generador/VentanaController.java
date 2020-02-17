package generador;

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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
    @FXML    private TextField aditivoNo;
    @FXML    private TextField aditivoM;
    @FXML    private TextField aditivoN;
    @FXML    private TextField multiN;
    @FXML    private TextField multiK;
    @FXML    private TextField multiG;
    @FXML    private TextField multiSemilla;
    
    
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
    private void aditivoGenerar(ActionEvent event) {
        String noSemillasT, mT, nT;
        int noSemillas, m, n;
        int semillas[];
        
        noSemillasT = aditivoNo.getText();
        mT = aditivoM.getText();
        nT = aditivoN.getText();
        
        try{
            noSemillas = Integer.parseInt(noSemillasT);
            m = Integer.parseInt(mT);
            n = Integer.parseInt(nT);
            
            if(noSemillas>1 && m>0  && n>0){
                semillas = new int[noSemillas];
                for(int i=0;i<noSemillas;i++)
                    semillas[i] = mensajeEntrada("Introducir Semillas", "Dame semilla "+(i+1)+" de "+noSemillas);
                
                archivo.error = false;
                numeros = metodos.aditivo(semillas, m, n);
                mostrarTabla();
                
                repetidos();
                
                guardar.setVisible(true);
            }
            else
                mensajeError("Error",
                        "Xn = (Xn-1 - Xi-n) mod m\n\n"
                        + "Xn = Semilla(Xo>0)\n"
                        + "m = Modulo\n");
            
        }catch(NumberFormatException  e){
            mensajeError("Error", "Introduce solo números enteros");
        }
        
        
    }
    
    @FXML
    private void multiGenerar(ActionEvent event) {
        String semillaT, kT, gT, nT;
        int semilla, k, g,  n;
        
        semillaT = multiSemilla.getText();
        kT = multiK.getText();
        gT = multiG.getText();
        nT = multiN.getText();
        
        try{
            semilla = Integer.parseInt(semillaT);
            k = Integer.parseInt(kT);
            g = Integer.parseInt(gT);
            n = Integer.parseInt(nT);
            
            if(semilla>0 && k>0 && g>0 && n>0){
                
                archivo.error = false;
                numeros = metodos.multiplicativo(semilla, k, g, n);
                mostrarTabla();
                
                repetidos();
                
                guardar.setVisible(true);
            }
            else
                mensajeError("Error",
                        "Xn+1 = (a Xn + c) mod m\n\n"
                        + "Xo = Semilla(Xo>0)\n"
                        + "a = Multiplicador(a>0)\n"
                        + "c = Constante (c>0)\n"
                        + "m = Modulo(m>Xo, m>a y m>c)\n\n"
                        + "m = 2^g\n"
                        + "a = 5 + 8c");
            
        }catch(NumberFormatException  e){
            mensajeError("Error", "Introduce solo números enteros");
        }
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
                
                archivo.error = false;
                numeros = metodos.mixto(semilla, a, m, c, n);
                mostrarTabla();
                
                repetidos();
                
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
        //else
        //    mensajeError("Error", archivo.errorMensaje);
        
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
    
    public int mensajeEntrada(String titulo, String contenido)
    {
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(contenido);
        dialogo.initStyle(StageStyle.UTILITY);
        
        Optional<String> entrada = dialogo.showAndWait();
        
        if(entrada.isPresent())
        {
            try{
                return Integer.parseInt(entrada.get());
            }catch(NumberFormatException  e){
                return mensajeEntrada(titulo, contenido);
            }
            
        }
        else
            return mensajeEntrada(titulo, contenido);
    }
    
    @FXML
    private void aditivoInfo(ActionEvent event) {
        mensajeInformativo("Método congruencial aditivo",
                "Xn = (Xn-1 - Xi-n) mod m\n\n"
                + "Xn = Semilla(Xo>0)\n"
                + "m = Modulo\n");
    }
    
    @FXML
    private void multiInfo(ActionEvent event) {
        mensajeInformativo("Método congruencial multiplicativo",
                "Xn+1 = (a Xn + c) mod m\n\n"
                + "Xo = Semilla(Xo>0)\n"
                + "a = Multiplicador(a>0)\n"
                + "c = Constante (c>0)\n"
                + "m = Modulo(m>Xo, m>a y m>c)\n\n"
                + "m = 2^g\n"
                + "a = 5 + 8c");
    }

    @FXML
    private void mixtoInfo(ActionEvent event) {
        mensajeInformativo("Método congruencial mixto",
                "Xn+1 = (a Xn) mod m\n\n"
                + "Xo = Semilla(Xo>0)\n"
                + "a = Multiplicador(a>0)\n"
                + "c = Constante (c>0)\n"
                + "m = Modulo(m>Xo, m>a y m>c)\n");
    }
    
    public void repetidos(){
        boolean repetido[] = null;
        int seRepite;
        boolean hayRepetidos = false;
        String salida="";
        
        if(numeros!=null){
            repetido = new boolean[numeros.length];
            for(int i=0;i<numeros.length-1;i++){
                if(!repetido[i]){
                    seRepite = 1;
                    
                    for(int j=i+1;j<numeros.length;j++){
                        if(numeros[i] == numeros[j]){
                            hayRepetidos = true;
                            repetido[j] = true;
                            seRepite++;
                        }
                    }
                    
                    if(seRepite>1)
                        salida += "El número "+numeros[i]+" se repite "+seRepite+" veces.\n";
                }
            }
            if(hayRepetidos)
                mensajeInformativo("Información de repetidos", salida);
        }
    }
}
