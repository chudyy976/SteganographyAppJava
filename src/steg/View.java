package steg;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;

public class View extends Pane {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files (*.jpg, *.png)", "*.png", "*.jpg"),
                new FileChooser.ExtensionFilter("All Files (*.*)", "*.*")
        );
        File file = fileChooser.showOpenDialog(null);
        ImageView originalView = new ImageView(file.toURI().toString());
        double heigh = originalView.getImage().getHeight();
        double width = originalView.getImage().getWidth();

        setPrefSize(width * 2, heigh+50);

        ImageView modifiedView = new ImageView();
        modifiedView.setTranslateX(width);

        TextField field = new TextField();
        field.setPromptText("Wprowadz wiadomosc: ");
        field.setTranslateY(width);
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
        btnDecode.setTranslateX(width - 100);
        btnDecode.setTranslateY(width);
        btnDecode.setOnAction(e -> controller.onDecode());

        controller.injectUI(originalView, modifiedView, field);

        getChildren().addAll(originalView, modifiedView, field, btnDecode);
    }
}