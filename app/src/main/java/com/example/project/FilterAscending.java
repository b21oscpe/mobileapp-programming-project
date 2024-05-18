package com.example.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FilterAscending implements Filter{
    @Override
    public ArrayList<River> apply(ArrayList<River> items, String query) {

        Collections.sort(items, new Comparator<River>() {
            @Override
            public int compare(River r1, River r2) {
                return r1.getSize().compareTo(r2.getSize());
            }
        });
        return items;

    }
}
