package steg;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Controller {

    private Model model;

    public Controller(Model model) { this.model = model; }

    private Image image;
    private TextField fieldMessage;
    private FileChooser fileChooser = new FileChooser();

    public void readImage(){
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files (*.jpg, *.png)", "*.png", "*.jpg"),
                new FileChooser.ExtensionFilter("All Files (*.*)", "*.*")
        );
        File file = fileChooser.showOpenDialog(null);
        Image image = new Image(file.toURI().toString());
        this.image = image;
    }
    public void injectUI(TextField fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    public void onEncode() throws IOException {
        if (fieldMessage.getText() == null | fieldMessage.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setContentText("Brak wiadomosci");
            alert.showAndWait();
        } else {
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image File (*.png)", "*.png"));
            Image modified = model.encode(image, fieldMessage.getText());
            File file = fileChooser.showSaveDialog(null);
            ImageIO.write(SwingFXUtils.fromFXImage((modified), null), "png", file);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Zakodowano pomyślnie");
            alert.showAndWait();
        }
    }

    public void onDecode() {
        String message = model.decode(image);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rozkodowana wiadomosc");
        alert.setContentText(message);
        alert.showAndWait();
    }
}