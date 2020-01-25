package steg;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class View extends Pane {
    private Controller controller;
    private FileChooser fileChooser = new FileChooser();
    public View(Controller controller) {
        this.controller = controller;
        //variables and objects

        Button btnDecode = new Button("DEKODUJ");
        Button btnEncode = new Button("KODUJ");
        Button btnRead = new Button("Wczytaj zdjecie");
        TextField field = new TextField();

        //elements aligment
        setPrefSize(400, 200);
        field.setPromptText("Wprowadz wiadomosc: ");
        field.setPrefSize(200, 30);
        field.setTranslateX(100);
        field.setTranslateY(30);
        btnDecode.setPrefSize(100, 70);
        btnEncode.setPrefSize(100, 70);
        btnDecode.setTranslateX(300);
        btnDecode.setTranslateY(90);
        btnEncode.setTranslateY(90);
        btnRead.setPrefSize(100, 40);
        btnRead.setTranslateX(150);
        btnRead.setTranslateY(105);

        //btn actions
        btnRead.setOnAction(e -> controller.readImage());
        btnDecode.setOnAction(e -> controller.onDecode());
        btnEncode.setOnAction(e-> {
            try {
                controller.onEncode();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        controller.injectUI(field);

        getChildren().addAll(field, btnDecode, btnEncode, btnRead);
    }
}