package controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.JelinskiMorandyModel;
import model.SchickWolvertonModel;

import java.io.*;

public class Controller {
    @FXML private Text nValueFirst;
    @FXML private Text fiValueFirst;
    @FXML private Text eValueFirst;
    @FXML private Text nValueSecond;
    @FXML private Text fiValueSecond;
    @FXML private Text eValueSecond;

    @FXML private ListView<String> errorTimeList;

    @FXML private TextField precisionTextField;

    @FXML private Button calculateButton;

    final ObservableList<String> errorListString = FXCollections.observableArrayList();
    private int[] errorListInt;

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
            Task<Void> syncNewMails = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    JelinskiMorandyModel jmm = new JelinskiMorandyModel(errorListInt, Double.parseDouble(precisionTextField.getText()));
                    jmm.calculateModel();

                    SchickWolvertonModel swm = new SchickWolvertonModel(errorListInt, Double.parseDouble(precisionTextField.getText()));
                    swm.calculateModel();

                    nValueFirst.setText(jmm.getNString());
                    fiValueFirst.setText(jmm.getFiString());
                    eValueFirst.setText(jmm.getExpectedValueString());
                    nValueSecond.setText(swm.getNString());
                    fiValueSecond.setText(swm.getFiString());
                    eValueSecond.setText(swm.getExpectedValueString());

                    calculateButton.setDisable(false);
                    return null;
                }
            };

            calculateButton.setDisable(true);
            Thread backgroundThread = new Thread(syncNewMails);
            backgroundThread.setDaemon(true);
            backgroundThread.start();
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
        Platform.runLater(() -> errorTimeList.setItems(errorListString));

        errorListInt = new int[errorListString.size()];
        for (int i = 0; i < errorListString.size(); i++) {
            errorListInt[i] = Integer.parseInt(errorListString.get(i));
        }
    }
}
