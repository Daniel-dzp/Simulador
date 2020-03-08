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
            stage.show();
        }catch(IOException e2)
        {
            e2.printStackTrace();
        }
        
    }
    
}
