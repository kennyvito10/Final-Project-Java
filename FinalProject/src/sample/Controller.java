package sample;

//All the necessary imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller extends DBConnect implements Initializable {

    //declaring all fxml and stage
    public static Stage stage;

    @FXML
    TextArea bodyText;

    @FXML
    Text notesTitle;

    @FXML
    Text imgText;

    @FXML
    ImageView imageView;

    @FXML
    TextField imageText;


    @FXML private TableView<Notes> table;
    @FXML private TableColumn<Notes, String> title;
    @FXML private TableColumn<Notes, String> datenow;

    public ObservableList<Notes> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    // view image function to view image
    public void addimage(){
        File f = new File(imageText.getText());
        Image img = new Image(f.toURI().toString());
        imageView.setImage(img);
    }



    //function to open a new fxml window
    public void newWindow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("newnotes.fxml"));
        stage = new Stage();
        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("New Note");
        stage.setResizable(false);
        stage.show();

    }
    //function to open a new fxml window of about me
    public void newsboutWindow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("about.fxml"));
        stage = new Stage();
        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle("About Us");
        stage.setResizable(false);
        stage.show();

    }

    //load data to the table view
    public void loadData(){
        table.getItems().clear();
        try {
            Connection con = DBConnect.getConnection();
            String sql = String.format("select * from notes");
            ResultSet rs =  con.createStatement().executeQuery(sql);

            while (rs.next()) {
                list.add(new Notes(rs.getString("title") , rs.getString("date")));
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        title.setCellValueFactory(new PropertyValueFactory<Notes, String>("title"));
        datenow.setCellValueFactory(new PropertyValueFactory<Notes, String>("date"));
        // load dummy data
        table.setItems(list);
    }

    //load note to edit
    public void loadNote(){
        String notes = table.getSelectionModel().getSelectedItem().getTitle();
        System.out.println(notes);


        notesTitle.setText(notes);

        try {
            Connection con = DBConnect.getConnection();
            String sql = String.format("select * from %s" , notes);
            ResultSet rs =  con.createStatement().executeQuery(sql);

            while (rs.next()) {
                notesTitle.setText(notes);
                bodyText.setText(rs.getString("body"));
                imageText.setText(rs.getString("img"));
                imgText.setText(rs.getString("img"));
                File f = new File(imgText.getText());
                Image img = new Image(f.toURI().toString());
                imageView.setImage(img);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //save notes function after the notes was editted
    public void saveNotes(){
        String notes = bodyText.getText();
        String imgt = imageText.getText();
        System.out.println(notes);

        updateNote(notes , notesTitle.getText() ,imgt);
        notesTitle.setText("");
        bodyText.setText("");
        imgText.setText("");
        imageText.setText("");
        imageView.setImage(null);
    }

    //delete row function
    public void deleteNote(){
        String title = table.getSelectionModel().getSelectedItem().getTitle();
        deleteItem(title);
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
    }
}



