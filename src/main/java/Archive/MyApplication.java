package Archive;

import com.kosh.job.gmail.ConfigManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class MyApplication extends Application {

    public static final Logger logger = LogManager.getLogger(MyApplication.class);


    @Override
    public void start(Stage stage) {

//        if (ConfigManager.getPathToChromeDriver()==null) {
//            startFirstRunStage(stage);
//        } else {
//            startMainStage(stage);
//        }

        startMainStage(stage);
    }

    private void startMainStage(Stage stage) {
        Stage mainStage = stage;

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






        Button exitButton = new Button("Выход");
        exitButton.setPrefWidth(150); // ширина кнопки
        exitButton.setPrefHeight(30); // высота кнопки
        exitButton.setOnAction(actionEvent -> {
            mainStage.hide();
        });


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

        // Создаем контейнер root
        VBox root = new VBox(50); // Отступ между компонентами
        root.getChildren().addAll(gridPane, exitButton);
        root.setPadding(new Insets(10)); // Отступы
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 500, 480);
        mainStage.setScene(scene);
        mainStage.setTitle("Параметры заявки");
        mainStage.show();

    }

//    private void startFirstRunStage (Stage stage) {
//        this.firstRunStage = stage;
//
//        //Выбор файла
//        final TextField pathTextField = new TextField();
//        pathTextField.setPrefWidth(350);
//        pathTextField.setPrefHeight(20);
//        final Button selectChromeDriver = new Button("Выбрать файл");
//        selectChromeDriver.setOnAction(event -> {
//            FileChooser fileChooser = new FileChooser();
//            File selectedFile = fileChooser.showOpenDialog(stage);
//            if (selectedFile != null) {
//                pathTextField.setText(selectedFile.getAbsolutePath());
//                logger.info("Указан путь к ChromeDriver: " + selectedFile.getAbsolutePath());
//            }
//        });
//
//        final Button downloadChromeDriver = new Button("Скачать ChromeDriver");
//        downloadChromeDriver.setPrefWidth(150);
//        downloadChromeDriver.setPrefHeight(30);
//        downloadChromeDriver.setOnAction(actionEvent -> {
//            try {
//                URI uri = new URI(ConfigManager.DOWNLOAD_CHROME_DRIVER_URL);
//                Desktop.getDesktop().browse(uri);
//                logger.info("Сайт для скачивания ChromeDriver успешно открыт");
//            } catch (Exception e) {
//                logger.error("Ошибка открытия сайта для скачивания ChromeDriver " + e.getMessage());
//            }
//        });
//
//        final Text description = new Text("Для успешной работы приложения необходимо скачать ChromeDriver");
//        description.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//
//        HBox HBox = new HBox(10); // Отступ между компонентами
//        HBox.getChildren().addAll(selectChromeDriver,pathTextField);
//        HBox.setPadding(new Insets(10));
//        HBox.setAlignment(Pos.TOP_CENTER);
//
//        VBox root = new VBox(50);
//        root.getChildren().addAll(description, HBox, downloadChromeDriver);
//        root.setPadding(new Insets(10));
//        root.setAlignment(Pos.TOP_CENTER);
//
//        scene = new Scene(root, 500, 480);
//
//        firstRunStage.setScene(scene);
//        firstRunStage.setTitle("Первый запуск");
//        firstRunStage.show();
//    }

    public static void main(String[] args) {

        try {
            ConfigManager.loadConfig(); // Загружаем конфигурацию
            launch(args);
            logger.info("Окно конфигурации успешно запущено");
        } catch (Exception e) {
            logger.error("Ошибка запуска окна конфигурации " + e.getMessage());
        }


        String os = System.getProperty("os.name").toLowerCase();

    }


}
