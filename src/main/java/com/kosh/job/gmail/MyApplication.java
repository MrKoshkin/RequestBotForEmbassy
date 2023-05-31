package com.kosh.job.gmail;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;

public class MyApplication extends Application {

    public static final Logger logger = LogManager.getLogger(MyApplication.class);
    private Stage mainStage;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        this.mainStage = stage;

        // Фамилия
        final Text textLastName = new Text("Фамилия: *");
        final TextField textFieldLastName = new TextField(ConfigManager.getLastName());
        textFieldLastName.setPromptText("Иванов");
        textFieldLastName.setPrefWidth(300);
        textFieldLastName.setPrefHeight(20);

        // Имя
        final Text textFirstName = new Text("Имя: *");
        final TextField textFieldFirstName = new TextField(ConfigManager.getFirstName());
        textFieldFirstName.setPromptText("Иван");
        textFieldFirstName.setPrefWidth(300);
        textFieldFirstName.setPrefHeight(20);

        // Отчество
        final Text textMiddleName = new Text("Отчество: ");
        final TextField textFieldMiddleName = new TextField(ConfigManager.getMiddleName());
        textFieldMiddleName.setPromptText("Иваныч");
        textFieldMiddleName.setPrefWidth(300);
        textFieldMiddleName.setPrefHeight(20);

        // Телефон
        final Text textPhone = new Text("Телефон: *");
        final TextField textFieldPhone = new TextField(ConfigManager.getPhone());
        textFieldPhone.setPromptText("+7 705 804 59 36");
        textFieldPhone.setPrefWidth(300);
        textFieldPhone.setPrefHeight(20);

        // Электронная почта
        final Text textEmail = new Text("Адрес электронной почты: *");
        final TextField textFieldEmail = new TextField(ConfigManager.getEmail());
        textFieldEmail.setPromptText("example@gmail.com");
        textFieldEmail.setPrefWidth(300);
        textFieldEmail.setPrefHeight(20);

        // Дата рождения
        final Text textDateOfBirth = new Text("Дата рождения : *");
        final DatePicker datePicker;
        if (Files.exists(Path.of(ConfigManager.CONFIG_FILE))) {
            datePicker = new DatePicker(LocalDate.parse(ConfigManager.getDateOfBirth()));
        } else {
            datePicker = new DatePicker();
            datePicker.setPromptText("01.01.2000");
        }
        datePicker.setPrefWidth(150);
        datePicker.setPrefHeight(20);
        datePicker.setOnAction(new EventHandler() {     // Выбор даты из календаря
            public void handle(Event t) {
                LocalDate date = datePicker.getValue();

                System.err.println("Selected date: " + date);
            }
        });

        // Обращение
        final Text textAddress = new Text("Обращение: *");
        ComboBox<String> addressBox = new ComboBox<>();
        addressBox.setItems(FXCollections.observableArrayList("Уважаемый", "Уважаемая"));   // Блок выбора из списка
        addressBox.setPromptText("Уважаемый");
        addressBox.setPrefWidth(150);
        addressBox.setPrefHeight(20);


//        // Создаем контейнер root на Сцене 1
//        VBox root = new VBox(1); // Отступ между компонентами
//        root.getChildren().addAll(gridPane);
//        root.setPadding(new Insets(10)); // Отступы
//        root.setAlignment(Pos.TOP_CENTER);
//
//        scene = new Scene(gridPane, 640, 480);
//        stage.setTitle("Параметры заявки");
//        stage.setScene(scene);
//        stage.show();

        GridPane gridPane = new GridPane();     // Создаем стеку
        gridPane.setPadding(new Insets(10));    // Отступ между компонентами
        gridPane.setHgap(10);   // Горизонтальный отступ между столбцами сетки
        gridPane.setVgap(15);   // Вертикальный отступ между строками сетки
        gridPane.setAlignment(Pos.TOP_CENTER);

        // Устанавливаем положение в сетке (столбец, строка)
        GridPane.setConstraints(textLastName, 0, 0);
        GridPane.setConstraints(textFieldLastName, 1, 0);
        GridPane.setConstraints(textFirstName, 0, 1);
        GridPane.setConstraints(textFieldFirstName, 1, 1);
        GridPane.setConstraints(textMiddleName, 0, 2);
        GridPane.setConstraints(textFieldMiddleName, 1, 2);
        GridPane.setConstraints(textPhone, 0, 3);
        GridPane.setConstraints(textFieldPhone, 1, 3);
        GridPane.setConstraints(textEmail, 0, 4);
        GridPane.setConstraints(textFieldEmail, 1, 4);
        GridPane.setConstraints(textDateOfBirth, 0, 5);
        GridPane.setConstraints(datePicker, 1, 5);
        GridPane.setConstraints(textAddress, 0, 6);
        GridPane.setConstraints(addressBox, 1, 6);

        // Добавляем в сетку элементы
        gridPane.getChildren().addAll(
                textLastName, textFieldLastName,
                textFirstName, textFieldFirstName,
                textMiddleName,textFieldMiddleName,
                textPhone, textFieldPhone,
                textEmail, textFieldEmail,
                textDateOfBirth, datePicker,
                textAddress, addressBox
        );

        scene = new Scene(gridPane, 500, 480);
        mainStage.setScene(scene);
        mainStage.setTitle("Параметры заявки");
        mainStage.show();

        //TODO Первичная инциализация хромдрайвера

    }

    public static void main(String[] args) {
        try {
            launch(args);
            MyApplication.logger.info("Окно конфигурации успешно загружено");
        } catch (Exception e) {
            MyApplication.logger.error("Ошибка загрузки окна конфигурации " + e.getMessage());
        }

        String os = System.getProperty("os.name").toLowerCase();

    }
}
