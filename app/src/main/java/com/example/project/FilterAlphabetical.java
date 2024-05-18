package com.example.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FilterAlphabetical implements Filter {
    @Override
    public ArrayList<River> apply(ArrayList<River> items, String query) {

        Collections.sort(items, new Comparator<River>() {
            @Override
            public int compare(River r1, River r2) {
                return r1.getName().compareTo(r2.getName());
            }
        });
        return items;

    }
}
