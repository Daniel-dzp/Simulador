/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadordeseudonumeros;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author dzp
 */
public class VentanaController implements Initializable {

    @FXML
    private AnchorPane mixtoGenerar;
    @FXML
    private Button mixtoInfo;
    @FXML
    private TextField mixtoSemilla;
    @FXML
    private TextField mixtoA;
    @FXML
    private TextField mixtoM;
    @FXML
    private TextField mixtoC;
    @FXML
    private TextField mixtoN;
    @FXML
    private Button visualizarInfo;
    @FXML
    private TextField visualizarN;
    @FXML
    private TableView<String[]> tabla;
    
    
    Metodos metodos;
    Archivo archivo;
    
    double numeros[];
    @FXML
    private Button guardar;
    
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
            
            numeros = metodos.mixto(semilla, a, m, c, n);
            
            mostrarTabla();
            guardar.setVisible(true);
            
        }catch(NumberFormatException  e){
            System.out.println("Error");
        }
        
        
    }
    
    public void mostrarTabla()
    {
        String[][] matrizNumeros;
        
        matrizNumeros = new String[(int)Math.ceil(numeros.length/10.0)+1][10];
        
        for(int i=0;i<10;i++)
            matrizNumeros[0][i] = (i+1)+""; 
        for(int i=1;i-1<(int)Math.ceil(numeros.length/10.0);i++){
            for(int j=0;j<10;j++){
                if(numeros.length>i*10+j)
                    matrizNumeros[i][j] = ""+numeros[i*10+j];
            }
        }
        
        tabla.getColumns().clear();
        
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

    @FXML
    private void guardar(ActionEvent event) throws IOException {
        archivo.guardar(numeros);
        guardar.setVisible(false);
    }
    
    @FXML
    private void visualizarMostrar(ActionEvent event) throws IOException {
        String nT;
        int n;
        
        nT = visualizarN.getText();
        
        try{
            n = Integer.parseInt(nT);
            
            numeros = archivo.leer(n);
            System.out.println("");
            mostrarTabla();
        }catch(NumberFormatException  e){
            System.out.println("Error");
        }
    }

    @FXML
    private void tabCambio(Event event) {
        if(numeros != null){
            numeros = null;
            tabla.getColumns().clear();
        }
    }
}
