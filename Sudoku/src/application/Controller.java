package application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller<enough> implements Initializable {
    @FXML Button button_one;
    @FXML Button button_two;
    @FXML Button button_three;
    @FXML Button button_four;
    @FXML Button button_five;
    @FXML Button button_six;
    @FXML Button button_seven;
    @FXML Button button_eight;
    @FXML Button button_nine;
    @FXML Canvas tulemus;
    @FXML Canvas canvas;
    @FXML Canvas Kell;
    @FXML Button button_clear;
    @FXML Button button_kontroll;
    @FXML Button button_kerge;
    @FXML Button button_keskmine;
    @FXML Button button_raske;
    int player_selected_row;
    int player_selected_col;
    GameBoard gameboard;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        GraphicsContext result = tulemus.getGraphicsContext2D();
        GraphicsContext kell = Kell.getGraphicsContext2D();
        result.clearRect(0, 0, 300, 200);
        context.clearRect(0, 0, 450, 450);
        setkell(Kell.getGraphicsContext2D());

    }
    public void drawOnCanvas(GraphicsContext context, GraphicsContext result) {
        result.clearRect(0,0,450,450);
        context.clearRect(0, 0, 450, 450);
        context.setStroke(Color.BLACK);
        context.setLineWidth(3);
        context.strokeLine(0,150,450,150);
        context.strokeLine(0,300,450,300);
        context.strokeLine(150,0,150,450);
        context.strokeLine(300,0,300,450);
        for(int row = 0; row<9; row++) {
            for(int col = 0; col<9; col++) {
                int position_y = row * 50 + 2;
                int position_x = col * 50 + 2;
                int width = 46;
                context.setFill(Color.WHITE);
                context.fillRoundRect(position_x, position_y, width, width, 10, 10);
            }


            context.setStroke(Color.RED);
            context.setLineWidth(5);
            context.strokeRoundRect(player_selected_col * 50 + 2, player_selected_row * 50 + 2, 46, 46, 10, 10);

        }

        int[][] initial = gameboard.getInitial();
        for(int row = 0; row<9; row++) {
            for(int col = 0; col<9; col++) {

                int position_y = row * 50 + 35;

                int position_x = col * 50 + 17;

                context.setFill(Color.BLACK);

                context.setFont(new Font("arial black", 30));
                if(initial[row][col]!=0) {

                    context.fillText(initial[row][col] + "", position_x, position_y);
                }
            }
        }
        int[][] player = gameboard.getPlayer();
        for(int row = 0; row<9; row++) {
            for(int col = 0; col<9; col++) {
                int position_y = row * 50 + 35;
                int position_x = col * 50 + 17;
                context.setFill(Color.PURPLE);
                context.setFont(new Font("arial black", 30));
                if(player[row][col]!=0) {
                    context.fillText(player[row][col] + "", position_x, position_y);
                }
            }
        }
    }
    public void canvasMouseClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int mouse_x = (int) event.getX();
                int mouse_y = (int) event.getY();

                player_selected_row = (int) (mouse_y / 50);
                player_selected_col = (int) (mouse_x / 50);
                drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());
            }
        });
    }
    public void canvasKeyPressed(){
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
               if(ke.getCode() == KeyCode.S){
                   player_selected_col = player_selected_col + 1;
               }
                if(ke.getCode() == KeyCode.A){
                    player_selected_row = player_selected_row - 1;
                }
                if(ke.getCode() == KeyCode.D){
                    player_selected_row = player_selected_row + 1;
                }
                if(ke.getCode() == KeyCode.W){
                    player_selected_col = player_selected_col - 1;
                }
                drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());
            }
        });
    }


    public void buttonOnePressed() {
        gameboard.modifyPlayer(1, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());
    }
    public void buttonTwoPressed() {
        gameboard.modifyPlayer(2, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());
    }

    public void buttonThreePressed() {
        gameboard.modifyPlayer(3, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());
    }

    public void buttonFourPressed() {
        gameboard.modifyPlayer(4, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());
    }

    public void buttonFivePressed() {
        gameboard.modifyPlayer(5, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());
    }

    public void buttonSixPressed() {
        gameboard.modifyPlayer(6, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());
    }

    public void buttonSevenPressed() {
        gameboard.modifyPlayer(7, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());
    }

    public void buttonEightPressed() {
        gameboard.modifyPlayer(8, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());
    }

    public void buttonNinePressed() {
        gameboard.modifyPlayer(9, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());
    }
    public void Clear() {
        gameboard.modifyPlayer(0, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());}
    public void kontroll(){
        Tulemus(tulemus.getGraphicsContext2D(), canvas.getGraphicsContext2D());
    }public void loo_kerge(){
        gameboard = new GameBoard("kerge");
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());
    }
    public void loo_keskmine(){
        gameboard = new GameBoard("keskmine");
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());}
    public void loo_raske(){
        gameboard = new GameBoard("raske");
        drawOnCanvas(canvas.getGraphicsContext2D(), tulemus.getGraphicsContext2D());}
    public void Tulemus(GraphicsContext result, GraphicsContext context){
        result.setFont(new Font(20));
        if(gameboard.checkForSuccess() == true) {
            context.clearRect(0, 0, 450, 450);
            result.clearRect(0,0,300,200);
            result.setFill(Color.GREEN);
            result.setFont(new Font(36));
            result.fillText("Õige!", 100, 150);
        }
        if(gameboard.checkForSuccess() == false) {
            context.clearRect(0, 0, 450, 450);
            result.clearRect(0,0,300,200);
            result.setFill(Color.RED);
            result.setFont(new Font(36));
            result.fillText("Vale!", 100, 150);
        }
    }
    public static void setkell(GraphicsContext kell){
        kell.setFill(Color.BLACK);
        LocalDateTime aeg = LocalDateTime.now();
        DateTimeFormatter aeg1 = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formaaditud = aeg.format(aeg1);
        kell.fillText(formaaditud, 150, 60);
    }

}
