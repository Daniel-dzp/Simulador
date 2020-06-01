package pruebas;

import Estadisticos.EstadisticoChiCuadrada;
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
    @FXML    private TextArea textArea;
    
    EstadisticoKolmogorov ek;
    EstadisticoChiCuadrada eC;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ek = new EstadisticoKolmogorov();
        eC = new EstadisticoChiCuadrada();
        
        items.add("Distribución  chi-2");
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
            case 0: textArea.setText(eC.getTabla()); break;
            case 1: textArea.setText(ek.getTabla()); break;
        }
    }
    
}
