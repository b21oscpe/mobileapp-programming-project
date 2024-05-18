package com.example.project;

import java.util.ArrayList;

public interface Filter {
    ArrayList<River> apply(ArrayList<River> items, String query);
}
