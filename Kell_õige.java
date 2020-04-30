import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Kell_õige extends Application {
    public final Text txtTime = new Text();

    private volatile boolean enough = false;

    Thread aja_näitaja = new Thread(() -> {
        SimpleDateFormat kell = new SimpleDateFormat("hh:mm:ss");

        while(!enough) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException ex) {}
            final String time = kell.format(new Date());
            Platform.runLater(()-> {

                txtTime.setText(time);
            });
        }
    });


    @Override
    public void start(Stage stage) {
        BorderPane raam = new BorderPane();
        raam.setCenter(txtTime);

        Scene scene = new Scene(raam, 400, 350);
        stage.initStyle(StageStyle.UTILITY);

        stage.setScene(scene);


        aja_näitaja.start();
        stage.show();
    }


    @Override
    public void stop() {

        enough = true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}