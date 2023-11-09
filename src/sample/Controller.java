package sample;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public TextField mainid;
    public TextArea text1;
    public TextField text2;
    public TextField text3;
    public ListView <String> timeline_sto;
    public TableView<crud> datatables;
    public TableColumn<crud, SimpleIntegerProperty> id1;
    public TableColumn<crud, SimpleStringProperty> id2;
    public TableColumn<crud, SimpleStringProperty> id3;
    public TableColumn<crud, SimpleStringProperty> id4;
    public TableColumn<crud, SimpleStringProperty> id5;
    public Label alert;
    private koneksi Koneksi=new koneksi();
    public Button upload;


    public void execute(ActionEvent actionEvent) {
        String Identity = mainid.getText();
        String data1 = text1.getText();
        String data2 = text2.getText();
        String data3 = text3.getText();

        if (!data1.isEmpty() && Identity.isEmpty()) {
        String query="INSERT INTO timeline(nama,message,title) VALUES('"+data3+"','"+data1+"','"+data2+"')";
        int hasil=Koneksi.manipulasiData(query);
        if (hasil==1){
            alert.setText("Your Stoy Has Successfully Been Uploaded");
            this.tampilData();
            this.tampilDataTabelView();
        }
    }
        else if (!Identity.isEmpty()) {
            String query = "UPDATE timeline SET nama='"+data3+"', title='"+data2+"',message='" + data1 + "'  WHERE id=" + Integer.parseInt(Identity);
            int hasil = Koneksi.manipulasiData(query);
            if (hasil == 1) {
                this.tampilData();
                this.tampilDataTabelView();
                alert.setText("Your Timelie Data Has Successfully Been Altered");
            }
        }
    }

    public void tampilData() {
        try {
            String query = "SELECT * FROM timeline";
            ResultSet hasil = Koneksi.getData(query);
            ObservableList<String> items = FXCollections.observableArrayList();
            timeline_sto.setItems(items);
            while (hasil.next()) {
                items.add(hasil.getString(2));
                items.add(hasil.getString(3));
                items.add(hasil.getString(4));
                items.add(hasil.getString(5));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            this.tampilDataTabelView();
            this.tampilData();
            alert.setText("");
        }
    private void tampilDataTabelView() {
        id1.setCellValueFactory(new PropertyValueFactory<>("Id"));
        id2.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        id4.setCellValueFactory(new PropertyValueFactory<>("Title"));
        id3.setCellValueFactory(new PropertyValueFactory<>("Message"));
        id5.setCellValueFactory(new PropertyValueFactory<>("Date"));
        try {
            String query = "SELECT * FROM timeline";
            ResultSet hasil = Koneksi.getData(query);
            ObservableList<crud> datatable = FXCollections.observableArrayList();
            datatables.setItems(datatable);
            while (hasil.next()) {
                int id = hasil.getInt(1);
                String nama = hasil.getString(4);
                String title = hasil.getString(2);
                String message = hasil.getString(3);

                String date = hasil.getString(5);
                datatable.add(new crud(id,nama, title, message, date));
            }
        }catch (Exception e) {
            System.out.println(e);
        }

        datatables.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPesanDetail(newValue));
    }

    private void showPesanDetail(crud timeline) {
        if (timeline != null) {
            mainid.setText(timeline.idProperty().getValue().toString());
            text3.setText(timeline.getNama());
            text2.setText(timeline.getMessage());
            text1.setText(timeline.getTitle());





            upload.setText("Update");
        } else {
            mainid.setText("");
            text3.setText("");
            text2.setText("");
            text1.setText("");
            alert.setText("");
        }
    }


    public void deletee(ActionEvent actionEvent) {
        String Identity = mainid.getText();
        if (!Identity.isEmpty()) {
            String query = "DELETE FROM timeline WHERE id=" + Integer.parseInt(Identity);
            int hasil = Koneksi.manipulasiData(query);
            if (hasil == 1) {


                this.tampilData();
                this.tampilDataTabelView();
                alert.setText("Your Timeline Data Has Successfully Been Deleted");
            }
        }
    }

    public void resett(ActionEvent actionEvent) {
        mainid.setText("");
        text3.setText("");
        text2.setText("");
        text1.setText("");
        text3.setFocusTraversable(false);
        text2.setFocusTraversable(false);
        text1.setFocusTraversable(false);
        alert.setText("");
        upload.setText("Upload");
    }
}

