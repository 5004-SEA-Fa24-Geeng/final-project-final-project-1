package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Model implements Imodel {
    /**
     * all cars list.
     */
    private List<CarRecord> allCars = new ArrayList<>();
    /**
     * working list(list after sorting or filtering).
     */
    private List<CarRecord> workingList = new ArrayList<>();
    /**
     * wishlist
     */
    private Set<CarRecord> wishList = new HashSet<>();

    /**
     * Constructs the model.
     */
    public Model() {
        loadData();
        loadWishList();
        resetWorkingList();
    }

    /**
     * load data from path
     */
    private void loadData() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(dataPath))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",", -1);

                if (record.length != 13) {
                    continue;
                }

                try {
                    String id = record[0].isEmpty() ? "Unknown ID" : record[0];
                    double price = record[1].isEmpty() ? 0.0 : Double.parseDouble(record[1]);
                    String zip = record[3].isEmpty() ? "00000" : record[3];
                    int mileage = record[4].isEmpty() ? 0 : Integer.parseInt(record[4]);
                    String make = record[5].isEmpty() ? "Unknown Make" : record[5];
                    String model = record[6].isEmpty() ? "Unknown Model" : record[6];
                    int year = record[7].isEmpty() ? 0 : Integer.parseInt(record[7]);
                    String trim = record[8].isEmpty() ? "Unknown Trim" : record[8];
                    String engineInfo = record[9].isEmpty() ? "Unknown Engine" : record[9];
                    String bodyType = record[10].isEmpty() ? "Unknown Body Type" : record[10];
                    int numOfCylinders = record[11].isEmpty() ? 0: Integer.parseInt(record[11]);
                    String driveType = record[12].isEmpty() ? "Unknown Drive Type" : record[12];

                    // Use static images
                    String makeFirstWord = make.split(" ")[0];
                    String modelFirstWord = model.split(" ")[0];
                    String imageUrl = "data/images/" + year + makeFirstWord + modelFirstWord + ".jpg";

                    CarRecord car = new CarRecord(id, price, zip, mileage, make, model, year,
                            trim, engineInfo, bodyType, numOfCylinders, driveType, imageUrl);

                    allCars.add(car);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            resetWorkingList();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error reading data from file");
        }
    }

    private void loadWishList() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(wishListPath))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");

                if (record.length != 12) {
                    continue;
                }

                try {
                    String id = record[0].isEmpty() ? "Unknown ID" : record[0];
                    double price = record[1].isEmpty() ? 0.0 : Double.parseDouble(record[1]);
                    String zip = record[2].isEmpty() ? "00000" : record[2];
                    int mileage = record[3].isEmpty() ? 0 : Integer.parseInt(record[3]);
                    String make = record[4].isEmpty() ? "Unknown Make" : record[4];
                    String model = record[5].isEmpty() ? "Unknown Model" : record[5];
                    int year = record[6].isEmpty() ? 0 : Integer.parseInt(record[6]);
                    String trim = record[7].isEmpty() ? "Unknown Trim" : record[7];
                    String engineInfo = record[8].isEmpty() ? "Unknown Engine" : record[8];
                    String bodyType = record[9].isEmpty() ? "Unknown Body Type" : record[9];
                    int numOfCylinders = record[10].isEmpty() ? 0: Integer.parseInt(record[10]);
                    String driveType = record[11].isEmpty() ? "Unknown Drive Type" : record[11];

                    String imageUrl = FetchImage.fetchUrl(make, model, year);

                    CarRecord car = new CarRecord(id, price, zip, mileage, make, model, year,
                            trim, engineInfo, bodyType, numOfCylinders, driveType, imageUrl);

                    wishList.add(car);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            resetWorkingList();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error reading data from file");
        }
    }

    /**
     * return all the makes.
     *
     * @return all the unique makes
     */
    @Override
    public Set<String> getAllMakes() {

        return allCars.stream()
                .map(CarRecord::make)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Return all models available for a given make.
     *
     * @param make the selected car make
     * @return a set of models for that make
     */
    @Override
    public Set<String> getModelsByMake(String make) {
        return allCars.stream()
                .filter(car -> car.make().equalsIgnoreCase(make))
                .map(CarRecord::model)
                .collect(Collectors.toCollection(TreeSet::new));

    }

    /**
     * return all cars.
     * @return list of all cars
     */
    @Override
    public List<CarRecord> getAllCars() {
        return new ArrayList<>(allCars);
    }

    /**
     * search a car by entering make.
     * @param make  make
     * @return list of car that fits the make
     */
    @Override
    public List<CarRecord> getCarsByMake(String make) {
        return allCars.stream()
                .filter(car -> car.make().equalsIgnoreCase(make))
                .collect(Collectors.toList());
    }

    /**
     * search a car by entering make and model.
     *
     * @param make  make
     * @param model model
     * @return list of car that fits the make and make
     */
    @Override
    public List<CarRecord> getCarsByMakeAndModel(String make, String model) {
        return allCars.stream()
                .filter(car -> car.make().equalsIgnoreCase(make))
                .filter(car -> car.model().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    /**
     * get the temp list that holds all the sort and filter.
     *
     * @return a car list after sorting
     */
    @Override
    public List<CarRecord> getWorkingList() {
        return workingList;
    }

    /**
     * reset the working list.
     */
    @Override
    public void resetWorkingList() {
        workingList = new ArrayList<>(allCars);

    }

    /**
     * filter by make.
     *
     * @param make make
     */
    @Override
    public void filterByMake(String make) {
        workingList = workingList.stream()
                .filter(c -> c.make().equalsIgnoreCase(make))
                .collect(Collectors.toList());
    }

    /**
     * filter by model.
     *
     * @param model model
     */
    @Override
    public void filterByModel(String model) {
        workingList = workingList.stream()
                .filter(c -> c.model().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    /**
     * filter by price.
     * ex. if largerThan == true, Keeps only cars with price >= the input value
     *
     * @param price      price
     * @param largerThan larger than or smaller than amount
     */
    @Override
    public void filterByPrice(double price, boolean largerThan) {
        if (largerThan) {
            workingList = workingList.stream()
                    .filter(c -> c.price() >= price)
                    .collect(Collectors.toList());
        } else {
            workingList = workingList.stream()
                    .filter(c -> c.price() <= price)
                    .collect(Collectors.toList());
        }
    }

    /**
     * filter by mileage.
     * ex. if largerThan == true, Keeps only cars with mileage >= the input value
     * @param mileage    mileage
     * @param largerThan larger than or smaller than amount
     */
    @Override
    public void filterByMileage(int mileage, boolean largerThan) {
        if (largerThan) {
            workingList = workingList.stream()
                    .filter(c -> c.mileage() >= mileage)
                    .collect(Collectors.toList());
        } else {
            workingList = workingList.stream()
                    .filter(c -> c.mileage() <= mileage)
                    .collect(Collectors.toList());
        }

    }

    /**
     * filter by trim, will only keep trim() == this.trim
     *
     * @param trim trim
     */
    @Override
    public void filterByTrim(String trim) {
        workingList = workingList.stream()
                .filter(c -> c.trim().equalsIgnoreCase(trim))
                .collect(Collectors.toList());
    }

    /**
     * filter by year
     *
     * @param year       year
     * @param largerThan larger than or smaller than amount
     */
    @Override
    public void filterByYear(int year, boolean largerThan) {
        if (largerThan) {
            workingList = workingList.stream()
                    .filter(c -> c.year() >= year)
                    .collect(Collectors.toList());
        } else {
            workingList = workingList.stream()
                    .filter(c -> c.year() <= year)
                    .collect(Collectors.toList());
        }
    }

    /**
     * filter by drive type
     *
     * @param driveType drive type
     */
    @Override
    public void filterByDriveType(String driveType) {
        workingList = workingList.stream()
                .filter(c -> c.driveType().equalsIgnoreCase(driveType))
                .collect(Collectors.toList());
    }

    /**
     * filter by dody type
     *
     * @param bodyType body type
     */
    @Override
    public void filterByBodyType(String bodyType) {
        workingList = workingList.stream()
                .filter(c -> c.bodyType().equalsIgnoreCase(bodyType))
                .collect(Collectors.toList());
    }

    /**
     * filter by num of cylinder
     *
     * @param numOfCylinders num of cylinder
     */
    @Override
    public void filterByNumOfCylinder(int numOfCylinders) {
        workingList = workingList.stream()
                .filter(c -> c.numOfCylinders() == numOfCylinders)
                .collect(Collectors.toList());

    }

    /**
     * sort by year
     *
     * @param descending order
     */
    @Override
    public void sortByYear(boolean descending) {
        if (descending) {
            workingList.sort(Comparator.comparing(CarRecord::year).reversed());
        } else {
            workingList.sort(Comparator.comparing(CarRecord::year));
        }

    }

    /**
     * sort by mileage
     *
     * @param descending order
     */
    @Override
    public void sortByMileage(boolean descending) {
        if (descending) {
            workingList.sort(Comparator.comparing(CarRecord::mileage).reversed());
        } else {
            workingList.sort(Comparator.comparing(CarRecord::mileage));
        }
    }

    /**
     * sort by price
     *
     * @param descending order
     */
    @Override
    public void sortByPrice(boolean descending) {
        if (descending) {
            workingList.sort(Comparator.comparing(CarRecord::price).reversed());
        } else {
            workingList.sort(Comparator.comparing(CarRecord::price));
        }
    }

    /**
     * Add a car to the wishlist.
     *
     * @param car the car to add
     */
    @Override
    public void addToWishlist(CarRecord car) {
        wishList.add(car);
    }

    /**
     * Remove a car from the wishlist.
     *
     * @param car the car to remove
     */
    @Override
    public void removeFromWishlist(CarRecord car) {
        wishList.remove(car);
    }

    /**
     * Get the full wishlist.
     *
     * @return Set of cars in the wishlist
     */
    @Override
    public Set<CarRecord> getWishlist() {
        return wishList;
    }

    /**
     * Clear the wishlist.
     */
    @Override
    public void clearWishlist() {
        wishList.clear();

    }
    /**
     * save wishlist.
     */
    @Override
    public void saveWishlist() {
        Formatter.write(wishList, "csv", wishListPath);
    }
    /**
     * export wishlist based on format
     * @param format xml json or csv
     */
    @Override
    public void exportWishlist(String format) {
        String path;
        switch (format) {
            case "csv" -> path = csvPath;
            case "json" -> path = jsonPath;
            case "xml" -> path = xmlPath;
            default -> {
                System.out.println("Unsupported format: " + format);
                return;
            }
        }
        Formatter.write(wishList, format, path);
    }

    /**
     * setter for all cars.
     * @param allCars all cars
     */
    public void setAllCars(List<CarRecord> allCars) {
        this.allCars = allCars;
    }

    /**
     * setter wishlist.
     * @param wishList whitelist
     */
    public void setWishList(Set<CarRecord> wishList) {
        this.wishList = wishList;
    }
}
