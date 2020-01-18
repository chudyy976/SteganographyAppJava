package steg;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StegApp extends Application {

    private Model makeModel() { return new Model(new BasicEncoder(), new BasicDecoder()); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new View(new Controller(makeModel())));
        primaryStage.setTitle("SteganographyApp");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.jpg")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}