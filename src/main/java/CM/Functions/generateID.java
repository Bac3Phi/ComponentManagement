package CM.Functions;

import CM.Models.DataProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class generateID {
    public static String create(String tableTable,String id,String keyword) throws SQLException {
        String ID="";
        String query ="SELECT "+id+" FROM "+tableTable+"";
        DataProvider dataProvider =  new DataProvider();
        ResultSet resultSet =dataProvider.getData(query);
        ObservableList<String> data = FXCollections.observableArrayList();
        while (resultSet.next()){
            data.add(resultSet.getString(id));
        }
        for (String value : data) {

        }
        if(data.size() <= 0){
            ID= keyword+"001";
        }

        else{
            int k;
            ID= keyword;
            k = Integer.parseInt(data.get(data.size()-1).substring(keyword.length()));
            k = k+1;
            if (k<10){
                ID = ID +"00";
            }
            else if(k<10){
                ID= ID +"0";
            }
            ID= ID + String.valueOf(k);
        }

        return ID;
    }
}
