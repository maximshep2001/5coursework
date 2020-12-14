package com.company.studio.collections;

import com.company.studio.behavior.Product;
import com.company.studio.behavior.ProductInformation;
import com.company.studio.connection.Connect;
import com.company.studio.behavior.CatalogInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

import static java.lang.Integer.valueOf;

public final class CollectionCatalog {
    private ObservableList<CatalogInformation> catalogs = FXCollections.observableArrayList();

    private ObservableList<CatalogInformation> history = FXCollections.observableArrayList();

    private static com.company.studio.collections.CollectionCatalog instance;

    public static synchronized com.company.studio.collections.CollectionCatalog getInstance(){
        if(instance == null){
            instance = new com.company.studio.collections.CollectionCatalog();
        }
        return instance;
    }

    private ObservableList<CatalogInformation> selectedCatalog = FXCollections.observableArrayList();

    public ObservableList<CatalogInformation> getSelectedCatalogCatalog() { return selectedCatalog; }

    public ObservableList<CatalogInformation> getResCatalogs() {
        return ResCatalogs;
    }

    private ObservableList<CatalogInformation> ResCatalogs = FXCollections.observableArrayList();

    private ObservableList<CatalogInformation> Corzina = FXCollections.observableArrayList();

    public ObservableList<CatalogInformation> getCorzina() { return Corzina; }

    public void deleteCatalogFromTask(CatalogInformation catalog){
        selectedCatalog.remove(catalog);
    }

    public void deleteAllFromTask(){
        selectedCatalog.remove( selectedCatalog);
    }

    public void deleteAllFromCorzina(){
        Corzina.removeAll(Corzina);
    }

    public ObservableList<CatalogInformation> getCatalogs() {
        return catalogs;
    }

    public ObservableList<CatalogInformation> getHistory() {
        return history;
    }

    public void selectCatalog(CatalogInformation catalog){
        if(selectedCatalog.size()>2){
            Alert alert = new Alert(  Alert.AlertType.WARNING);
            alert.setTitle( "Ошибка" );
            alert.setHeaderText( "В задаче не может быть более 3 продуктов!" );
            alert.showAndWait();
        }
        else {
            selectedCatalog.add(catalog);
        }
    }

    public void catalogToCorzina(CatalogInformation catalog){
        Corzina.add(catalog);
    }

    public void fillNewData(){
        String id = Connect.get();
        Integer idcatalog = valueOf(id);
        String productname = Connect.get();
        String material = Connect.get();
        Integer cost_price = valueOf(Connect.get());
        Integer sale_value = valueOf(Connect.get());

        CatalogInformation catalog = new CatalogInformation(idcatalog, productname, material, cost_price, sale_value);
        catalogs.add(catalog);
    }

    public void fillData(){
        try {
            catalogs.removeAll(catalogs);
            String array = Connect.get();
            System.out.println(array);
            JSONArray newArray = null;
            if (array != null) {
                newArray = new JSONArray(array);
                int count = newArray.length();
                for(int i = 0; i<count; i++) {
                    JSONObject object = newArray.getJSONObject(i);
                    Integer idcatalog = object.getInt("idcatalog");
                    String productname = object.getString( "productname" );
                    String material = object.getString( "material" );
                    Integer cost_price = object.getInt( "cost_price" );
                    Integer sale_value = object.getInt("sale_value");
                    CatalogInformation catalog = new CatalogInformation(idcatalog, productname, material, cost_price, sale_value);
                    catalogs.add(catalog);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void fillHistoryData(){
        try {
            history.removeAll(history);
            String array = Connect.get();
            if (!array.equals("null")) {
                System.out.println(array);
                JSONArray newArray = null;
                if (array != null) {
                    newArray = new JSONArray(array);
                    int count = newArray.length();
                    for (int i = 0; i < count; i++) {
                        JSONObject object = newArray.getJSONObject(i);
                        String productname = object.getString("productname");
                        String material = object.getString("material");
                        Integer sale_value = object.getInt("sale_value");
                        String sale_date = object.getString("sale_date");
                        Date date = Date.valueOf(sale_date);
                        CatalogInformation catalog = new CatalogInformation(productname, material, sale_value, date);
                        history.add(catalog);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void fillTaskData(){
        String array = Connect.get();
        JSONObject object = new JSONObject(array);

        Integer idcatalog = object.getInt( "idcatalog" );
        String productname = object.getString( "productname" );
        String material = object.getString( "material" );
        Integer cost_price = object.getInt( "cost_price" );
        Integer sale_value = object.getInt( "sale_value" );
        CatalogInformation catalog = new CatalogInformation(idcatalog, productname, material, cost_price, sale_value );
        ResCatalogs.add( catalog );
    }

    public void deleteCollRes(){
        ResCatalogs.removeAll(ResCatalogs);
    }

    public void delete(CatalogInformation catalog){
        catalogs.remove(catalog);
    }

}
