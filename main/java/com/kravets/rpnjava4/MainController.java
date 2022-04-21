package com.kravets.rpnjava4;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.Socket;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    private TextField textField1, textField2, textField3, textField4, textField5;
    @FXML
    private TextField textField6, textField7, textField8, textField9, textField10;

    private Client client;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            client = new Client(new Socket("localhost", 4004));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void onTextInput1(ObservableValue observable, String oldValue, String newValue) {
        if (!newValue.equals("100") && (newValue.equals("0") || newValue.length() > 2 || !newValue.matches("\\d*")))
            textField1.setText(oldValue);
    }

    @FXML
    protected void onTextInput2(ObservableValue observable, String oldValue, String newValue) {
        if (!newValue.equals("100") && (newValue.equals("0") || newValue.length() > 2 || !newValue.matches("\\d*")))
            textField2.setText(oldValue);
    }

    @FXML
    protected void onTextInput3(ObservableValue observable, String oldValue, String newValue) {
        if (!newValue.equals("100") && (newValue.equals("0") || newValue.length() > 2 || !newValue.matches("\\d*")))
            textField3.setText(oldValue);
    }

    @FXML
    protected void onTextInput4(ObservableValue observable, String oldValue, String newValue) {
        if (!newValue.equals("100") && (newValue.equals("0") || newValue.length() > 2 || !newValue.matches("\\d*")))
            textField4.setText(oldValue);
    }

    @FXML
    protected void onTextInput5(ObservableValue observable, String oldValue, String newValue) {
        if (!newValue.equals("100") && (newValue.equals("0") || newValue.length() > 2 || !newValue.matches("\\d*")))
            textField5.setText(oldValue);
    }

    @FXML
    protected void onTextInput6(ObservableValue observable, String oldValue, String newValue) {
        if (!newValue.equals("100") && (newValue.equals("0") || newValue.length() > 2 || !newValue.matches("\\d*")))
            textField6.setText(oldValue);
    }

    @FXML
    protected void onTextInput7(ObservableValue observable, String oldValue, String newValue) {
        if (!newValue.equals("100") && (newValue.equals("0") || newValue.length() > 2 || !newValue.matches("\\d*")))
            textField7.setText(oldValue);
    }

    @FXML
    protected void onTextInput8(ObservableValue observable, String oldValue, String newValue) {
        if (!newValue.equals("100") && (newValue.equals("0") || newValue.length() > 2 || !newValue.matches("\\d*")))
            textField8.setText(oldValue);
    }

    @FXML
    protected void onTextInput9(ObservableValue observable, String oldValue, String newValue) {
        if (!newValue.equals("100") && (newValue.equals("0") || newValue.length() > 2 || !newValue.matches("\\d*")))
            textField9.setText(oldValue);
    }

    @FXML
    protected void onTextInput10(ObservableValue observable, String oldValue, String newValue) {
        if (!newValue.equals("100") && (newValue.equals("0") || newValue.length() > 2 || !newValue.matches("\\d*")))
            textField10.setText(oldValue);
    }

    @FXML
    protected void onAction() {
        Set<Integer> numbersSet = new HashSet<>();
        try {
            numbersSet.add(Integer.parseInt(textField1.getText()));
            numbersSet.add(Integer.parseInt(textField2.getText()));
            numbersSet.add(Integer.parseInt(textField3.getText()));
            numbersSet.add(Integer.parseInt(textField4.getText()));
            numbersSet.add(Integer.parseInt(textField5.getText()));
            numbersSet.add(Integer.parseInt(textField6.getText()));
            numbersSet.add(Integer.parseInt(textField7.getText()));
            numbersSet.add(Integer.parseInt(textField8.getText()));
            numbersSet.add(Integer.parseInt(textField9.getText()));
            numbersSet.add(Integer.parseInt(textField10.getText()));
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Спортлото");
            alert.setHeaderText("Все поля должны быть заполнены");
            alert.setContentText("");
            alert.showAndWait();
            return;
        }

        if (numbersSet.size() != 10) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Спортлото");
            alert.setHeaderText("Числа не должны повторяться");
            alert.setContentText("");
            alert.showAndWait();
            return;
        }

        Ticket ticket = new Ticket(numbersSet);
        client.sendToServer(ticket);
        client.receiveFromServer();
        ArrayList<Integer> correctIndexes = client.getCorrectNumbers();
        int bestId = client.getBestId();
        if (correctIndexes.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Спортлото");
            alert.setHeaderText("Ни одно из чисел не совпало");
            alert.setContentText("");
            alert.showAndWait();
        } else {
            StringBuilder content = new StringBuilder("Номера совпавших чисел:");
            for (int i: correctIndexes)
                content.append(" ").append(i);
            content.append("\nНаибольшее число совпадений с билетом №").append(bestId);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Спортлото");
            alert.setHeaderText("Число совпадений: " + correctIndexes.size());
            alert.setContentText(content.toString());
            alert.showAndWait();
        }
    }
}