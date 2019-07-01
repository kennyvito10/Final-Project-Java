package sample;
//All the necessary imports
import javafx.beans.property.SimpleStringProperty;

public class Notes {
    //declaring string property for date and title
    private SimpleStringProperty title, date;


    //Notes setter and getter
    public Notes(String title, String date) {
        this.title = new SimpleStringProperty(title);
        this.date = new SimpleStringProperty(date);
    }

    public String getTitle() {
        return title.get();
    }

    public String getDate() {
        return date.get();
    }


}