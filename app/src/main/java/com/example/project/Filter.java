package com.example.project;

import java.util.List;

public interface Filter {
    List<River> apply(List<River> items, String query);
}
