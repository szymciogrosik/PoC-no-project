package controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;

public class Controller {
    @FXML private Label nValueFirst;
    @FXML private Label fiValueFirst;
    @FXML private Label eValueFirst;
    @FXML private Label nValueSecond;
    @FXML private Label fiValueSecond;
    @FXML private Label eValueSecond;

    @FXML private ListView<String> errorTimeList;

    @FXML private TextField precisionTextField;

    final ObservableList<String> list = FXCollections.observableArrayList();

    public void initialize() {

    }

    @FXML
    private void loadFileWithData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(precisionTextField.getScene().getWindow());
        if (selectedFile != null) {
            loadStartDataList(selectedFile);
        }
    }

    @FXML
    private void calculateModels() {
        if(list.size() != 0 || precisionTextField.getLength() > 0) {
            System.out.println("Hello");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd walidacji");
            alert.setHeaderText("Błąd walidacji");
            alert.setContentText("Nie wprowadzono walidacji lub precyzji obliczeń.");
            alert.showAndWait();
        }
    }

    private void loadStartDataList(File selectedFile) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(selectedFile));
            String st;
            while ((st = br.readLine()) != null)
                list.add(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {
            errorTimeList.setItems(list);
        });
    }
}
