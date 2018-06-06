package CM.Functions;

import CM.Models.DataProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenerateID {
    public static String create(String tableTable,String id,String keyword) {
        String ID="";
        String query ="SELECT "+id+" FROM "+tableTable+" ORDER BY "+id+" ASC";
        DataProvider dataProvider =  new DataProvider();
        ObservableList<String> data = FXCollections.observableArrayList();
        ResultSet resultSet = null;
        try {
            resultSet = dataProvider.getData(query);
            while (resultSet.next()){
                data.add(resultSet.getString(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


     /*   for (int i =0; i<= data.size();i++){
            if (!data.get(i).substring(keyword.length()).equals(data.get(i + 1).substring(keyword.length())+1))
            {

                return ID=
            }
        }*/
        if(data.size() <= 0){
            ID= keyword+"001";
        }

        else{
            int key;
            ID= keyword;
            key = Integer.parseInt(data.get(data.size()-1).substring(keyword.length()));
            key = key +1;
            if (key <10){
                ID = ID +"00";
            }
            else if(key <10){
                ID= ID +"0";
            }
            ID= ID + String.valueOf(key);
        }
        return ID;
    }
}
