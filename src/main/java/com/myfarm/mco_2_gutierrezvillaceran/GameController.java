package com.myfarm.mco_2_gutierrezvillaceran;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private Label myLabel;
    @FXML
    private TextField myTextField;
    @FXML
    private Button myButton;

    String choice;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void submit(ActionEvent event){
        try{
            choice=myTextField.getText();
            System.out.println(choice);
        }
        catch (Exception e){
            System.out.println(e);
        }
        evaluateChoice(choice);
    }

    public void evaluateChoice(String choice){
        switch (choice) {
            case "P" -> {
                myLabel.setText("What would you like to plant? \n[1] Turnip\n[2] Carrot");

                //seedChoice = input.nextInt();
                //input.nextLine();

                //System.out.println("Where would you like to plant it?");
                //farmLand = player.plantSeed(seedList.get(seedChoice), farmLand);
            }
        }
    }
}