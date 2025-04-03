package model;

public record CarRecord(double price, String zip, int mileage, String make, String model, int year
        , String trim, String engineInfo, String bodyType, int numOfCylinders, String driveType, String imageUrl) {

    /**
     * get display title.
     * @return display title
     */
    public String getTitle() {
        return year + " " + make + " " + model + " " + trim + " " + driveType;
    }
}
