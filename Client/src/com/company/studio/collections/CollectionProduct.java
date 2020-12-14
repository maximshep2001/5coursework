package com.company.studio.collections;


import com.company.studio.behavior.Product;
import com.company.studio.connection.Connect;
import com.company.studio.controllers.CompanyManageMenuController;
import com.company.studio.behavior.ProductInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public final class CollectionProduct extends CompanyManageMenuController {

    private ObservableList<ProductInformation> prods = FXCollections.observableArrayList();

    private ObservableList<ProductInformation> selectedProduct = FXCollections.observableArrayList();

    private ObservableList<ProductInformation> ResProduct = FXCollections.observableArrayList();

    private static CollectionProduct instance;

    public static synchronized CollectionProduct getInstance(){
        if(instance == null){
            instance = new CollectionProduct();
        }
        return instance;
    }

    public ObservableList<ProductInformation> getProduct() { return prods; }

   /* public ObservableList<ProductInformation> getSelectedProductProducts() {
        return selectedProduct;
    }*/

    /*public ObservableList<ProductInformation> getResProducts() {
        return ResProduct;
    }*/

    /*public void delete(Product product){
        prods.remove(product);
    }*/

    /*public void deletePrTask(ProductInformation product){
        selectedProduct.remove(product);
    }*/

    public void deleteAllPrTask(){
        selectedProduct.removeAll(selectedProduct);
    }

    /*public void selectProduct(ProductInformation product){
        if(selectedProduct.size()>2){
            Alert alert = new Alert(  Alert.AlertType.WARNING);
            alert.setTitle( "Ошибка" );
            alert.setHeaderText( "В задаче не может быть более 3 продуктов!" );
            alert.showAndWait();
        }
        else {
            selectedProduct.add(product);
        }
    }*/

    /*public void fillTaskData(){
        Product array = new Product();
        JSONObject object = new JSONObject(array);

        Integer idproduct = object.getInt( "idproduct" );
        String productname = object.getString( "productname" );
        String material = object.getString( "material" );
        Integer cost_price = object.getInt( "cost_price" );
        ProductInformation product = new ProductInformation(idproduct, productname, material, cost_price );
        ResProduct.add( product );
    }*/

    public void fillData(){
        try {
            prods.removeAll(prods);
            String array = Connect.get();
            System.out.println(array);
            JSONArray newArray = null;
            if (array != null) {
                newArray = new JSONArray(array);
                int count = newArray.length();
                for(int i = 0; i<count; i++) {
                    JSONObject object = newArray.getJSONObject(i);
                    Integer idproduct = object.getInt("idproduct");
                    String productname = object.getString( "productname" );
                    String material = object.getString( "material" );
                    Integer cost_price = object.getInt("cost_price");

                    ProductInformation product = new ProductInformation(idproduct, productname,  material, cost_price);
                    prods.add(product);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*public void fillNewProduct(){
        Product array = Product.getInstance();
        JSONObject object = new JSONObject(array);

        Integer idproduct = object.getInt( "idproduct" );
        String productname = object.getString( "productname" );
        String material = object.getString( "material" );
        Integer cost_price = object.getInt( "cost_price" );
        ProductInformation product = new ProductInformation(idproduct, productname, material, cost_price );
        prods.add( product );
    }*/

    public void fillNewData(){
        String id = Connect.get();
        Integer idproduct = Integer.valueOf(id);
        String productname = Connect.get();
        String material = Connect.get();
        Integer cost_price = Integer.parseInt(Connect.get());

        ProductInformation prod = new ProductInformation(idproduct, productname,  material, cost_price);
        prods.add(prod);
    }

    public void deleteCollRes(){
        ResProduct.removeAll(ResProduct);
    }

    public void delete(ProductInformation prod){
        prods.remove(prod);
    }

}
