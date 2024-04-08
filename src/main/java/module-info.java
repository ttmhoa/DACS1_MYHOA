module org.example.dacs_myhoa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires sqljdbc42;

    opens org.example.dacs_myhoa to javafx.fxml;
    exports org.example.dacs_myhoa;
}