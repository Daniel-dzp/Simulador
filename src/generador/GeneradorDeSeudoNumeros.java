package generadordeseudonumeros;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author dzp
 */
public class GeneradorDeSeudoNumeros extends Application {
    
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ventana.fxml"));        
        Parent sceneMain = loader.load();
        
        VentanaController controller = loader.<VentanaController>getController();
        
        Scene scene = new Scene(sceneMain/*, 1220, 650*/);
        stage.setScene(scene);
        
        stage.setTitle("Generador de numeros seudo aleatorios");
        stage.show();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent event)
            {
                
            }
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
