import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

// peaks stopperi kuidagi saama sudoku_välja ja ühendama controllerigaõ
// proovi, töötab ilma sudookuta
// siin sama jutt nagu Kell_õige.java failis
// nagu tead, ma teen kõike väga raskeks, ilmselt saab lihtsamalt ka
// teha neid aga mina ei oska või ma ei taha selle peale mõelda praegusel momendil.

public class Stopper_õige extends Application{

    Scene tundlikus;
    VBox vertikaalne_kast;
    HBox horisontaalne_kast;
    Button alustamis_nupp;
    Button puhastus_nupp;
    Text tekst;
    Timeline ajakulg;


    int minutid = 0;
    int sekundid = 0;
    int millisekundid = 0;
    boolean vastus = true;

    public static void main(String[] args) {
        launch(args);
    }


    private void change(Text tekst) {
        if(millisekundid == 1000) {
            sekundid++;
            millisekundid = 0;
        }
        if(sekundid == 60) {
            minutid++;
            sekundid = 0;
        }
        // Küsib või kontrollib

        tekst.setText((((minutid/10) == 0) ? "0" : "") + minutid + ":" + (((sekundid/10) == 0) ? "0" : "") + sekundid + ":"
                + (((millisekundid/10) == 0) ? "00" : (((millisekundid/100) == 0) ? "0" : "")) + millisekundid++);
    }

    @Override
    public void start(Stage stage) {

        tekst = new Text("00:00:000");
        // siit hakkab segadus, tuleks likvideerida
        ajakulg = new Timeline(new KeyFrame(Duration.millis(1), event -> change(tekst)));


        ajakulg.setCycleCount(Timeline.INDEFINITE);
        ajakulg.setAutoReverse(false);
        alustamis_nupp = new Button("Alusta");
        alustamis_nupp.setOnAction(event -> {
            if(vastus) {
                ajakulg.play();
                vastus = false;
                alustamis_nupp.setText("Alusta");
            }
            else {
                ajakulg.pause();
                vastus = true;
                alustamis_nupp.setText("Alusta uuesti");
            }
        });


        puhastus_nupp = new Button("Puhasta");
        puhastus_nupp.setOnAction(new EventHandler<ActionEvent>() {

            @Override

            public void handle(ActionEvent event) {
                minutid = 0;
                sekundid = 0;
                millisekundid = 0;
                ajakulg.pause();
                tekst.setText("00:00:000");
                if(!vastus) {
                    vastus = true;
                    alustamis_nupp.setText("Puhasta");
                }
            }
        });


        horisontaalne_kast = new HBox(50);
        horisontaalne_kast.setAlignment(Pos.CENTER);
        horisontaalne_kast.getChildren().addAll(alustamis_nupp, puhastus_nupp);

        vertikaalne_kast = new VBox(30);
        vertikaalne_kast.setAlignment(Pos.CENTER);
        vertikaalne_kast.getChildren().addAll(tekst, horisontaalne_kast);

        tundlikus = new Scene(vertikaalne_kast, 400, 350);


        stage.setScene(tundlikus);
        stage.setTitle("Aja_võtja");
        stage.show();
    }
}
