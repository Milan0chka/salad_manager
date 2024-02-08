package com.zhuhaievych.coursework;

public class Ingredient {
    private String name;
    private double weight;
    private double calority;
    private double price;
    private double expTerm;

    public Ingredient() {
        this.name = "";
        this.weight = 0;
        this.calority = 0;
        this.price = 0;
        this.expTerm = 0;
    }

    public Ingredient(String name, double weight, double calority, double price, double expTerm) {
        this.name = name;
        this.weight = weight;
        this.calority = calority;
        this.price = price;
        this.expTerm = expTerm;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getCalority() { return calority; }
    public void setCalority(double calority) { this.calority = calority; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getExpTerm() { return expTerm; }
    public void setExpTerm(double expTerm) { this.expTerm = expTerm; }

    public double getWeight() { return weight; }

    public void setWeight(double weight) { this.weight = weight; }

    @Override
    public String toString() {
        return "Ingredient {" +
                "name = '" + name + '\'' +
                ", calority = " + calority +
                ", price = " + price +
                ", expiration term = " + expTerm +
                ", weight = " + weight +
                '}';
    }

}
