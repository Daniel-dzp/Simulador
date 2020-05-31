package principal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author dzp
 */
public class MenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void generar(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try
        {
            root = loader.load(getClass().getResource("/generador/Ventana.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Generar Seudo Números");
            stage.show();
        }catch(IOException e2)
        {
            e2.printStackTrace();
        }
    }

    @FXML
    private void verificar(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try
        {
            root = loader.load(getClass().getResource("/pruebas/VentanaPruebas.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Verificar Aleatoriedad");
            stage.show();
        }catch(IOException e2)
        {
            e2.printStackTrace();
        }
        
    }

    @FXML
    private void pruebaYAjuste(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try
        {
            root = loader.load(getClass().getResource("/metodoyajuste/VentanaMA.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Prueba y ajuste");
            stage.show();
        }catch(IOException e2)
        {
            e2.printStackTrace();
        }
    }

    @FXML
    private void simulador(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try
        {
            root = loader.load(getClass().getResource("/simulador/VentanaSimulador.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Simulador");
            stage.show();
        }catch(IOException e2)
        {
            e2.printStackTrace();
        }
    }
    
}
