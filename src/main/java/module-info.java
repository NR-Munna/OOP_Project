module com.example.oop_project_group19 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oop_project_group19 to javafx.fxml;
    exports com.example.oop_project_group19;
    opens com.example.oop_project_group19.Shawon to javafx.fxml;
    exports com.example.oop_project_group19.Shawon;
    opens com.example.oop_project_group19.Samira to javafx.fxml;
    exports com.example.oop_project_group19.Samira;
    opens com.example.oop_project_group19.Naimur to javafx.fxml;
    exports com.example.oop_project_group19.Naimur;
    opens com.example.oop_project_group19.Adnan to javafx.fxml;
    exports com.example.oop_project_group19.Adnan;
}