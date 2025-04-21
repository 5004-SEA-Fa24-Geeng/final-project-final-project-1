package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public record CarRecord(String id, double price, String zip, int mileage, String make, String model, int year,
                        String trim, String engineInfo, String bodyType, int numOfCylinders, String driveType,
                        @JsonIgnore String imageUrl) {

    /**
     * get display title.
     * @return display title
     */
    @JsonIgnore
    public String getTitle() {
        return year + " " + make + " " + model + " " + trim + " " + driveType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CarRecord other)) {
            return false;
        }

        return Objects.equals(id, other.id)
                && Double.compare(price, other.price) == 0
                && Objects.equals(zip, other.zip)
                && mileage == other.mileage
                && Objects.equals(make, other.make)
                && Objects.equals(model, other.model)
                && year == other.year
                && Objects.equals(trim, other.trim)
                && Objects.equals(engineInfo, other.engineInfo)
                && Objects.equals(bodyType, other.bodyType)
                && numOfCylinders == other.numOfCylinders
                && Objects.equals(driveType, other.driveType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, zip, mileage, make, model, year,
                trim, engineInfo, bodyType, numOfCylinders, driveType);
    }
}
