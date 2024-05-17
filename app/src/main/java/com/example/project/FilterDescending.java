package com.example.project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FilterDescending implements Filter{
    @Override
    public List<River> apply(List<River> items, String query) {

        Collections.sort(items, new Comparator<River>() {
            @Override
            public int compare(River r1, River r2) {
                return r2.getSize().compareTo(r1.getSize());
            }
        });
        return items;

    }
}
