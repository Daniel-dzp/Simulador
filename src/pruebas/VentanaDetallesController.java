package pruebas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
/**
 * @author dzp
 */
public class VentanaDetallesController implements Initializable {
    @FXML    private TextArea procedimiento;
    @FXML    private TextArea hipotesis;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void cerrar(ActionEvent event) {
        
    }
    
    public void setString(String procedimiento, String hipotesis)
    {
        this.procedimiento.setText(procedimiento);
        this.hipotesis.setText(hipotesis);
    }
}
