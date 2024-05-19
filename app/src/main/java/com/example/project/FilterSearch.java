package com.example.project;

import java.util.ArrayList;

public class FilterSearch implements Filter{
    @Override
    public ArrayList<River> apply(ArrayList<River> items, String query) {

        ArrayList<River> result = new ArrayList<>();
        for (River item : items){
            if (item.getName().toLowerCase().matches(String.format("%s(.*)", query.toLowerCase()))){
                result.add(item);
            }
        }
        if (!result.isEmpty()) return result;
        else return items;
    }
}
