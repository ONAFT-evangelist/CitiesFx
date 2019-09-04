/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CitiesFx;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 *
 * @author Юрий
 */
public class FXMLDocumentController implements Initializable {
    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt"));
    }
    final FileChooser fileChooser = new FileChooser();
    File file;
    boolean find = false;
      
    @FXML
    private Label label;
    @FXML
    private Button openbtn;
    @FXML
    private Button savebtn;
    @FXML
    private Button srcbtn;
    @FXML
    private Tab ta;
    @FXML
    private TextArea viewarea;
    @FXML
    private TextArea editarea;
    @FXML
    private TextField scrfield;
    @FXML
    private TextArea ansarea;
    @FXML
    private Label chnglabel;
    @FXML
    private TextField chngfield;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    }    

 
    @FXML
    private void handleButtonAction1(ActionEvent event) {
      opendial();
      openreopen();
    }

    @FXML
    private void handleButtonAction2(ActionEvent event) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));) { 
            String res = editarea.getText();
            bw.write(res);
            bw.write(System.lineSeparator());
            bw.flush();
            bw.close();
        } catch (FileNotFoundException e) {
            System.err.println("FILE NOT FOUND! " + file.getName());
        } catch (IOException e) {
            System.err.println("PERMISSION DENIED! " + file.getName());
        }
        openreopen();
    }


    @FXML
    private void handleButtonAction3(ActionEvent event) throws FileNotFoundException, IOException {
          String searchWord = scrfield.getText();
          String changeWord = chngfield.getText();

    StringBuilder sb = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
        String strLine;
         while ((strLine = br.readLine()) != null) {
            sb.append(strLine.replace(searchWord, changeWord)).append("\r\n");
            if(strLine.equalsIgnoreCase(searchWord)){
                find = true;
            }
        }
    }

 
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(sb.toString());
            fileWriter.flush();
            fileWriter.close();
            if(find) {ansarea.appendText("Succesfully replaced!\n");}
            else{ansarea.appendText("Совпадений не найдено! \n Nothing to change! \n");}
            openreopen();     
         }           
     }
      
    
    
      
     private  void opendial(){
          configureFileChooser(fileChooser);
                    file = fileChooser.showOpenDialog(new Stage());
                    if (file == null) {
                       System.out.print("EMPTY FILE!!!");
                    }
      }
       
       private void openreopen(){
            viewarea.clear();
            editarea.clear();
            find = false;
           try {
        CityClass elem = new CityClass();
        Scanner s = new Scanner(file);
        String readString = s.nextLine();
        while(readString!=null) {
           elem.SetElemValue(readString);
    viewarea.appendText(readString);
    viewarea.appendText("\n");
    editarea.appendText(readString);
    editarea.appendText("\n");
    if (s.hasNextLine())
        readString = s.nextLine();
    else
        readString = null;}
    } catch (FileNotFoundException | NullPointerException ex) {
        System.out.println(ex);
    }
       }
}
