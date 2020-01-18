package steg;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class View extends Pane {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;

        setPrefSize(603 * 2, 650);

        Image image = new Image("http://www.pwsz.krosno.pl/gfx/pwszkrosno/userfiles/admin/bannery/20-lat-www.jpg");
        ImageView originalView = new ImageView(image);

        ImageView modifiedView = new ImageView();
        modifiedView.setTranslateX(603);

        TextField field = new TextField();
        field.setPromptText("Wprowadz wiadomosc: ");
        field.setTranslateY(609);
        field.setPrefWidth(200);
        field.setOnAction(e -> {
            if (field.getText() == null | field.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning");
                alert.setContentText("Brak wiadomosci");
                alert.showAndWait();
            } else controller.onEncode();
        });

        Button btnDecode = new Button("DEKODUJ");
        btnDecode.setPrefWidth(100);
        btnDecode.setTranslateX(503);
        btnDecode.setTranslateY(609);
        btnDecode.setOnAction(e -> controller.onDecode());

        controller.injectUI(originalView, modifiedView, field);

        getChildren().addAll(originalView, modifiedView, field, btnDecode);
    }
}