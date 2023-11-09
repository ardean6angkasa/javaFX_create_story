package sample;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class crud {
    private IntegerProperty id;
    private StringProperty nama;
    private StringProperty message;
    private StringProperty title;
    private StringProperty date;

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNama() {
        return nama.get();
    }

    public StringProperty NamaProperty() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }
    public String getMessage() {
        return message.get();
    }

    public StringProperty MessageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }
    public String getTitle() {
        return title.get();
    }

    public StringProperty TitleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }
    public String getDate() {
        return date.get();
    }

    public StringProperty DateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public crud(int id, String nama, String message, String title, String date) {
        this.id = new SimpleIntegerProperty(id);
        this.nama = new SimpleStringProperty(nama);
        this.message = new SimpleStringProperty(message);
        this.title = new SimpleStringProperty(title);
        this.date = new SimpleStringProperty(date);
    }
}
