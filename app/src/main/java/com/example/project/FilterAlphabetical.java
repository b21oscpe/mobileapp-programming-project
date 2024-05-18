package com.example.project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FilterAlphabetical implements Filter {
    @Override
    public List<River> apply(List<River> items) {

        Collections.sort(items, new Comparator<River>() {
            @Override
            public int compare(River r1, River r2) {
                return r1.getName().compareTo(r2.getName());
            }
        });
        return items;

    }
}
