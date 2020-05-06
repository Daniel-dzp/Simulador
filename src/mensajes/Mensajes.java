package mensajes;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

/**
 * @author dzp
 */
public class Mensajes {
    public static void mensajeInformativo(String titulo, String contenido)
    {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(contenido);
        dialogo.initStyle(StageStyle.UTILITY);
        
        dialogo.showAndWait();
    }
    
    public static void mensajeError(String titulo, String contenido)
    {
        Alert dialogo = new Alert(Alert.AlertType.ERROR);
        
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(contenido);
        dialogo.initStyle(StageStyle.UTILITY);
        
        dialogo.showAndWait();
    }
    
    public static int mensajeEntradaG(String titulo, String contenido)
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
                return mensajeEntradaG(titulo, contenido);
            }
            
        }
        else
            return mensajeEntradaG(titulo, contenido);
    }
}
