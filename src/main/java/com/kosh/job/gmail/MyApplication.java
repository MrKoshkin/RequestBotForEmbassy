package com.kosh.job.gmail;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyApplication extends Application {

    public static final Logger logger = LogManager.getLogger(MyApplication.class);
    private Stage stage;
    private Scene scene;
    private TextField textField;
    @Override
    public void start(Stage stage) {
        this.stage = stage;



        textField = new TextField();
        // Создаем контейнер root на Сцене 1
        VBox root = new VBox(30); // Отступ между компонентами
        root.setPadding(new Insets(10)); // Отступы
        root.setAlignment(Pos.CENTER);

        scene = new Scene(textField, 840, 600);
        stage.setTitle("Параметры заявки");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
        String os = System.getProperty("os.name").toLowerCase();

    }
}
