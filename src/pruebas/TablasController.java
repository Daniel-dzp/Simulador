package pruebas;

import Estadisticos.EstadisticoKolmogorov;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/**
 * @author dzp
 */
public class TablasController implements Initializable {
    ObservableList<String> items = FXCollections.observableArrayList();
    @FXML    private ComboBox<String> select;
    EstadisticoKolmogorov ek;
    @FXML    private TextArea textArea;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ek = new EstadisticoKolmogorov();
        
        items.add("Distribución  chi-2");
        items.add("Distribución normal");
        items.add("Kolmogorov");
        
        select.setItems(items);
        
        select.getSelectionModel().select(0);
        
        seleccion(null);
    }

    @FXML
    private void seleccion(ActionEvent event) {
        textArea.setText("");
        switch(select.getSelectionModel().getSelectedIndex())
        {
            case 0: break;
            case 1: break;
            case 2: textArea.setText(ek.getTabla()); break;
        }
    }
    
}
