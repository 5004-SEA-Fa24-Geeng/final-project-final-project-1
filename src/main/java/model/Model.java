package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

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
    private List<CarRecord> wishList = new ArrayList<>();

    public Model() {
        loadData();

    }


    /**
     * load data from path
     */
    private void loadData() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(dataPath))) {
            reader.readLine();
            while ((reader.readLine()) != null) {
                String[] record = reader.readLine().split(",");
                CarRecord car = new CarRecord(
                        Double.parseDouble(record[1]), //price
                        record[3], //zip
                        Integer.parseInt(record[4]), //mileage
                        record[5], //make
                        record[6], //model
                        Integer.parseInt(record[7]), //year
                        record[8], //trim
                        record[9], //engineInfo
                        record[10], //bodyType
                        Integer.parseInt(record[11]), //numOfCylinders
                        record[12],  //driveType
                        FetchImage.fetchUrl(record[5], record[6], Integer.parseInt(record[7]))
                        );
                allCars.add(car);
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
        return Set.of();
    }

    /**
     * Return all models available for a given make.
     *
     * @param make the selected car make
     * @return a set of models for that make
     */
    @Override
    public Set<String> getModelsByMake(String make) {
        return Set.of();
    }

    /**
     * search a car by entering make and model.
     *
     * @param make  make
     * @param model model
     * @return list of car that fits the make and make
     */
    @Override
    public List<CarRecord> getCarsByModel(String make, String model) {
        return List.of();
    }

    /**
     * get the temp list that holds all the sort and filter.
     *
     * @return a car list after sorting
     */
    @Override
    public List<CarRecord> getWorkingList() {
        return List.of();
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

    }

    /**
     * filter by model.
     *
     * @param model model
     */
    @Override
    public void filterByModel(String model) {

    }

    /**
     * filter by price.
     *
     * @param price      price
     * @param largerThan larger than or smaller than amount
     */
    @Override
    public void filterByPrice(double price, boolean largerThan) {

    }

    /**
     * filter by mileage.
     *
     * @param mileage    mileage
     * @param largerThan larger than or smaller than amount
     */
    @Override
    public void filterByMileage(int mileage, boolean largerThan) {

    }

    /**
     * filter by trim.
     *
     * @param trim trim
     */
    @Override
    public void filterByTrim(String trim) {

    }

    /**
     * filter by year
     *
     * @param year       year
     * @param largerThan larger than or smaller than amount
     */
    @Override
    public void filterByYear(int year, boolean largerThan) {

    }

    /**
     * filter by drive type
     *
     * @param driveType drive type
     */
    @Override
    public void filterByDriveType(String driveType) {

    }

    /**
     * filter by dody type
     *
     * @param bodyType body type
     */
    @Override
    public void filterByBodyType(String bodyType) {

    }

    /**
     * filter by num of cylinder
     *
     * @param numOfCylinder num of cylinder
     */
    @Override
    public void filterByNumOfCylinder(int numOfCylinder) {

    }

    /**
     * sort by year
     *
     * @param descending order
     */
    @Override
    public void sortByYear(boolean descending) {

    }

    /**
     * sort by mileage
     *
     * @param descending order
     */
    @Override
    public void sortByMileage(boolean descending) {

    }

    /**
     * sort by price
     *
     * @param descending order
     */
    @Override
    public void sortByPrice(boolean descending) {

    }

    /**
     * Add a car to the wishlist.
     *
     * @param car the car to add
     */
    @Override
    public void addToWishlist(CarRecord car) {

    }

    /**
     * Remove a car from the wishlist.
     *
     * @param car the car to remove
     */
    @Override
    public void removeFromWishlist(CarRecord car) {

    }

    /**
     * Get the full wishlist.
     *
     * @return list of cars in the wishlist
     */
    @Override
    public List<CarRecord> getWishlist() {
        return List.of();
    }

    /**
     * Clear the wishlist.
     */
    @Override
    public void clearWishlist() {

    }
}
