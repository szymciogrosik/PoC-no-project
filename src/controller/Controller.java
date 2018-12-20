package controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import model.JelinskiMorandyModel;
import model.SchickWolvertonModel;

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

    @FXML private Button calculateButton;

    final ObservableList<String> errorListString = FXCollections.observableArrayList();
    private int[] errorListInt;

    public void initialize() { }

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
        if(errorListString.size() != 0 || precisionTextField.getLength() > 0) {
            calculateButton.setDisable(true);

            JelinskiMorandyModel jmm = new JelinskiMorandyModel(errorListInt, Double.parseDouble(precisionTextField.getText()));
            jmm.calculateModel();

            SchickWolvertonModel swm = new SchickWolvertonModel(errorListInt, Double.parseDouble(precisionTextField.getText()));
            swm.calculateModel();

            updateGuiValues(jmm.getNString(), jmm.getFiString(), jmm.getExpectedValueString(), swm.getNString(), swm.getFiString(), swm.getExpectedValueString());
            calculateButton.setDisable(false);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd walidacji");
            alert.setHeaderText("Błąd walidacji");
            alert.setContentText("Nie wprowadzono walidacji lub precyzji obliczeń.");
            alert.showAndWait();
        }
    }

    private void loadStartDataList(File selectedFile) {
        errorListString.clear();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(selectedFile));
            String st;
            while ((st = br.readLine()) != null) {
                errorListString.add(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {
            errorTimeList.setItems(errorListString);
        });

        errorListInt = new int[errorListString.size()];
        for (int i = 0; i < errorListString.size(); i++) {
            errorListInt[i] = Integer.parseInt(errorListString.get(i));
        }
    }

    private void updateGuiValues(String nFirst, String fiFirst, String exFirst, String nSecond, String fiSecond, String exSecond) {
        nValueFirst.setText(nFirst);
        fiValueFirst.setText(fiFirst);
        eValueFirst.setText(exFirst);
        nValueSecond.setText(nSecond);
        fiValueSecond.setText(fiSecond);
        eValueSecond.setText(exSecond);
    }
}
