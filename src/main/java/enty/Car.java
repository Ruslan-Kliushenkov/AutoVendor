package enty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name= "cars")
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="price")
    private int price;

    @Column(name="date_manufacture")
    private String dateManufacture;

    @Column(name="sell_date")
    private String sellDate;

    @Column(name = "gear_type")
    private String gearType;

    @Column(name = "fuel_volume")
    private int fuelVolume;

    public Car() {
    }

    public Car(int id, String title, int price, String dateManufacture, String sellDate, String gearType, int fuelVolume) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.dateManufacture = dateManufacture;
        this.sellDate = sellDate;
        this.gearType = gearType;
        this.fuelVolume = fuelVolume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDateManufacture() {
        return dateManufacture;
    }

    public void setDateManufacture(String dateManufacture) {
        this.dateManufacture = dateManufacture;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public int getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(int fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", dateManufacture='" + dateManufacture + '\'' +
                ", sellDate='" + sellDate + '\'' +
                ", gearType='" + gearType + '\'' +
                ", fuelVolume=" + fuelVolume +
                '}';
    }
}
