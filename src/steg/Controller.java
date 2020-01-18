package steg;


import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {

    private Model model;

    public Controller(Model model) { this.model = model; }

    private ImageView originalView, modifiedView;
    private TextField fieldMessage;

    public void injectUI(ImageView original, ImageView modified, TextField fieldMessage) {
        this.originalView = original;
        this.modifiedView = modified;
        this.fieldMessage = fieldMessage;
    }

    public void onEncode() {
        Image modified = model.encode(originalView.getImage(), fieldMessage.getText());
        modifiedView.setImage(modified);
    }

    public void onDecode() {
        String message = model.decode(modifiedView.getImage());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rozkodowana wiadomosc");
        alert.setContentText(message);
        alert.showAndWait();
    }
}