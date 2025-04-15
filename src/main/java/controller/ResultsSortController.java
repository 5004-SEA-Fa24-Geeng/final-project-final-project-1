package controller;

import model.CarRecord;
import view.ResultsWin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResultsSortController {

    private ResultsWin resultsWin;

    private List<CarRecord> originalCarRecords;

    private List<CarRecord> filteredCarRecords = new ArrayList<>();

    private List<CarRecord> sortedCarRecords = new ArrayList<>();

    public ResultsSortController(ResultsWin resultsWin, List<CarRecord> originalCarRecords) {
        this.resultsWin = resultsWin;
        this.originalCarRecords = originalCarRecords;
        this.filteredCarRecords = new ArrayList<>(originalCarRecords);
    }

    public void updateFilteredCarRecords(List<CarRecord> filteredCarRecords) {
        this.filteredCarRecords = new ArrayList<>(filteredCarRecords);
    }

    public void applySort(String selectedSort) {
        if ("Select".equals(selectedSort)) {
            return;
        }

        if (filteredCarRecords.isEmpty()) {
            sortedCarRecords = new ArrayList<>(originalCarRecords);
        } else {
            sortedCarRecords = new ArrayList<>(filteredCarRecords);
        }

        switch (selectedSort) {
            case "Price (Low to High)" -> sortedCarRecords.sort(Comparator.comparingDouble(CarRecord::price));
            case "Price (High to Low)" -> sortedCarRecords.sort(Comparator.comparingDouble(CarRecord::price).reversed());
            case "Year (Newest First)" -> sortedCarRecords.sort(Comparator.comparingInt(CarRecord::year).reversed());
            case "Year (Oldest First)" -> sortedCarRecords.sort(Comparator.comparingInt(CarRecord::year));
            case "Mileage (Low to High)" -> sortedCarRecords.sort(Comparator.comparingInt(CarRecord::mileage));
            case "Mileage (High to Low)" -> sortedCarRecords.sort(Comparator.comparingInt(CarRecord::mileage).reversed());
        }

        resultsWin.updateGridPanel(sortedCarRecords);
    }
}
