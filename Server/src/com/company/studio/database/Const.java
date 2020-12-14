package com.company.studio.database;

public class Const {

    public static final String USER_TABLE="users";

    public static final String USER_ID="id";
    public static final String USER_NAME="name";
    public static final String USER_SURNAME="surname";
    public static final String USER_PHONE="phone";
    public static final String USER_EMAIL="email";
    public static final String USER_PASSWORD="password";
    public static final String USER_ROLE="role";


    public static final String PRODUCTS_TABLE="products";

    public static final String PRODUCT_ID="idproduct";
    public static final String PRODUCT_NAME="productname";
    public static final String PRODUCT_MATERIAL="material";
    public static final String PRODUCT_COST_PRICE="cost_price";


    public static final String CATALOG_TABLE="catalogs";

    public static final String CATALOG_ID="idcatalog";
    public static final String CATALOG_PRODUCT_ID="products_idproduct";
    public static final String CATALOG_SALE_VALUE="sale_value";


    public static final String ORDER_TABLE="orders";

    public static final String ORDER_ID="idorder";
    public static final String ORDER_PAID="paid";
    public static final String ORDER_BRON="bron";
    public static final String ORDER_USERS_ID="users_id";
    public static final String ORDER_GARANT_ID="garant_idgarant";


    public static final String GARANT_TABLE="garant";

    public static final String GARANT_ID="idgarant";
    public static final String GARANT_SALE_DATE="sale_date";
    public static final String GARANT_ID_CATALOG="catalog_idcatalog";


    public static final String SOLVENCY_TABLE="solvency";

    public static final String SOLVENCY_ID="idsolvency";
    public static final String SOLVENCY_CASH="cash";
    public static final String SOLVENCY_USER_ID="user_id";


    public static final String INFO_TABLE="info";

    public static final String INFO_ID="idinfo";
    public static final String INFO_CONDITIONS="conditions";


    public static final String OPROS_TABLE="opros";

    public static final String OPROS_ID="idopros";
    public static final String OPROS_OPTION1="option1";
    public static final String OPROS_OPTION2="option2";
    public static final String OPROS_OPTION3="option3";

    public static final String STATISTIC_TABLE = "statistic";

    public static final String STATISTIC_YEAR = "year";
    public static final String STATISTIC_REVENUE = "revenue";
    public static final String STATISTIC_EXPENSES = "expenses";
    public static final String STATISTIC_PROFIT = "profit";

}
