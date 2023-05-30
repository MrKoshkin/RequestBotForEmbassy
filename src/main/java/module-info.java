module RequestBotForEmbassy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;
    requires org.apache.logging.log4j;


    opens com.kosh.job.gmail to javafx.fxml;
    exports com.kosh.job.gmail;

}
