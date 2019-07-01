package sample;

//All the necessary imports
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;



public class NewNotesController extends DBConnect implements Initializable {
    //declaring fxml type
    @FXML
    TextField titleText;

    //calling date function
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");
    LocalDate date = LocalDate.now();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    //adding notes function
    public void addNotes(){
        addTableAndRow(titleText.getText());
        insertNotes(titleText.getText() , dtf.format(date));
        titleText.setText("");
        Controller.stage.close();
    }

}
