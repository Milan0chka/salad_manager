package com.zhuhaievych.coursework;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class IngredientsForTable {
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty weight;
    private final SimpleDoubleProperty calority;
    private final SimpleDoubleProperty price;
    private final SimpleDoubleProperty expTerm;

    public IngredientsForTable(String name, double weight, double calority, double price, double expTerm) {
        this.name = new SimpleStringProperty(name);
        this.weight = new SimpleDoubleProperty(weight);
        this.calority = new SimpleDoubleProperty(calority);
        this.price = new SimpleDoubleProperty(price);
        this.expTerm = new SimpleDoubleProperty(expTerm);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getCalority() {
        return calority.get();
    }

    public SimpleDoubleProperty calorityProperty() {
        return calority;
    }

    public void setCalority(double calority) {
        this.calority.set(calority);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public double getExpTerm() {
        return expTerm.get();
    }

    public SimpleDoubleProperty expTermProperty() {
        return expTerm;
    }

    public void setExpTerm(double expTerm) {
        this.expTerm.set(expTerm);
    }

    public double getWeight() {
        return weight.get();
    }

    public SimpleDoubleProperty weightProperty() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }
}
