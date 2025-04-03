package model;

import java.util.List;
import java.util.Set;

public interface Imodel {
    String dataPath = "data/used_car_sales.csv";
    String wishListPath = "data/wishlist.csv";
    String outPutDataPath = "data";

    /**
     * return all the makes.
     * @return all the unique makes
     */
    Set<String> getAllMakes();

    /**
     * Return all models available for a given make.
     * @param make the selected car make
     * @return a set of models for that make
     */
    Set<String> getModelsByMake(String make);

    /**
     * search a car by entering make and model.
     * @param make make
     * @param model model
     * @return list of car that fits the make and make
     */
    List<CarRecord> getCarsByModel(String make, String model);

    /**
     * get the temp list that holds all the sort and filter.
     * @return a car list after sorting
     */
    List<CarRecord> getWorkingList();

    /**
     * reset the working list.
     */
    void resetWorkingList();

    /**
     * filter by make.
     * @param make make
     */
    void filterByMake(String make);

    /**
     * filter by model.
     * @param model model
     */
    void filterByModel(String model);

    /**
     * filter by price.
     * @param price price
     * @param largerThan larger than or smaller than amount
     */
    void filterByPrice(double price, boolean largerThan);

    /**
     * filter by mileage.
     * @param mileage mileage
     * @param largerThan larger than or smaller than amount
     */
    void filterByMileage(int mileage, boolean largerThan);

    /**
     * filter by trim.
     * @param trim trim
     */
    void filterByTrim(String trim);

    /**
     * filter by year
     * @param year year
     * @param largerThan larger than or smaller than amount
     */
    void filterByYear(int year, boolean largerThan);

    /**
     * filter by drive type
     * @param driveType drive type
     */
    void filterByDriveType(String driveType);

    /**
     * filter by dody type
     * @param bodyType body type
     */
    void filterByBodyType(String bodyType);

    /**
     * filter by num of cylinder
     * @param numOfCylinder num of cylinder
     */
    void filterByNumOfCylinder(int numOfCylinder);



    /**
     * sort by year
     * @param descending order
     */
    void sortByYear(boolean descending);

    /**
     * sort by mileage
     * @param descending order
     */
    void sortByMileage(boolean descending);

    /**
     * sort by price
     * @param descending order
     */
    void sortByPrice(boolean descending);

    /**
     * Add a car to the wishlist.
     * @param car the car to add
     */
    void addToWishlist(CarRecord car);

    /**
     * Remove a car from the wishlist.
     * @param car the car to remove
     */
    void removeFromWishlist(CarRecord car);

    /**
     * Get the full wishlist.
     * @return list of cars in the wishlist
     */
    List<CarRecord> getWishlist();

    /**
     * Clear the wishlist.
     */
    void clearWishlist();

}
