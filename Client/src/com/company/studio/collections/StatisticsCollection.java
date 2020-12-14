package com.company.studio.collections;


import com.company.studio.connection.Connect;
import com.company.studio.controllers.CompanyManageMenuController;
import com.company.studio.behavior.Statistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public final class StatisticsCollection  {
    private ObservableList<Statistics> statistics = FXCollections.observableArrayList();
    public static Map<Integer, Integer> statisticMap = new HashMap<>();
    private ObservableList<Statistics> selectedStat = FXCollections.observableArrayList();

    private static StatisticsCollection instance;

    public static synchronized StatisticsCollection getInstance(){
        if(instance == null){
            instance = new StatisticsCollection();
        }
        return instance;
    }
    public ObservableList<Statistics> getSelectedStat() {
        return selectedStat;
    }
    public ObservableList<Statistics> getStat() {
        return statistics;
    }

    public void fillNewData(){
        String y = Connect.get();
        Integer year = Integer.valueOf(y);
        String r = Connect.get();
        Integer revenue = Integer.valueOf(r);
        String e = Connect.get();
        Integer expenses = Integer.valueOf(e);
        String p = Connect.get();
        Integer profit = Integer.valueOf(p);

        Statistics statistic = new Statistics(year, revenue, expenses, profit);
        statistics.add(statistic);
    }

    public void fillData(){
        try {
            statisticMap.clear();
            statistics.removeAll(statistics);
            String array = Connect.get();
            System.out.println(array);
            JSONArray newArray = null;
            if (array != null) {
                newArray = new JSONArray(array);
                int count = newArray.length();
                for(int i = 0; i<count; i++) {
                    JSONObject object = newArray.getJSONObject(i);

                    int year = object.getInt( "year" );
                    int revenue = object.getInt("revenue");
                    int expenses = object.getInt( "expenses" );
                    int profit = object.getInt("profit");
                    Statistics statistic = new Statistics(year, revenue, expenses, profit);
                    statistics.add(statistic);
                    statisticMap.put(year, profit);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}