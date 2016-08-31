package ua.shalimov.filemanager.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;

public class JavaFxGui extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getClassLoader().getResource("sample.fxml");
        if (url != null) {
            Parent root = FXMLLoader.load(url);
            primaryStage.setTitle("File manager");
            primaryStage.setMinHeight(520);
            primaryStage.setMinWidth(720);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            showErrorWindow();
        }
    }

    private void showErrorWindow() {
        Stage stage = new Stage();
        Label label = new Label("Error while loading the page.Page load file is not found");
        stage.setTitle("Page load error");
        stage.setMinHeight(150);
        stage.setMinWidth(500);
        stage.setMaxHeight(150);
        stage.setMaxWidth(500);
        Scene scene = new Scene(label, 150, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void startJavaFx() {
        launch();
    }
}
