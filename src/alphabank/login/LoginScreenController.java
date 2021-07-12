/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabank.login;

import alphabank.App;
import alphabank.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pyles
 */
public class LoginScreenController {
    
    @FXML
    private TextField myTextField;
        SceneController sceneController = new SceneController();
        
    int id;
    
    public void submit(ActionEvent event) {
        try {
            id = Integer.parseInt(myTextField.getText());
            App.bankingSystem.login(id);  
            sceneController.renderHomeScreen(event);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

}
