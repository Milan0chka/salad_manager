package com.zhuhaievych.coursework;

import com.zhuhaievych.coursework.controllers.Menu;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Recipe {
    private List<Ingredient> usedIngredients;
    private double totalCalority;
    private double totalPrice;
    private double totalExpTerm;

    public Recipe() {
        usedIngredients = new ArrayList<>();
        this.totalCalority = 0;
        this.totalPrice = 0;
        this.totalExpTerm = Double.MAX_VALUE;
    }

    public Recipe(List<Ingredient> usedIngredients, double totalCalority, double totalPrice, double totalExpTerm) {
        this.usedIngredients = usedIngredients;
        this.totalCalority = totalCalority;
        this.totalPrice = totalPrice;
        this.totalExpTerm = totalExpTerm;
    }

    public List<Ingredient> getUsedIngredients() { return usedIngredients; }
    public void setUsedIngredients(List<Ingredient> usedIngredients) { this.usedIngredients = usedIngredients; }

    public double getTotalCalority() { return totalCalority; }
    public void setTotalCalority(double totalCalority) { this.totalCalority = totalCalority; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public double getTotalExpTerm() { return totalExpTerm; }
    public void setTotalExpTerm(double totalExpTerm) { this.totalExpTerm = totalExpTerm; }

    public void changeTotalCalority(double ingCalority) { this.totalCalority += ingCalority; }
    public void changeTotalPrice(double ingPrice) { this.totalPrice += ingPrice; }

    public void addIngredient(String name, double weight) {
        Ingredient ing = DBHandler.SelectIngredient(name);
        double calority, price, expTerm;

        ing.setWeight(weight);

        calority = round(ing.getCalority() * 0.01 * weight, 3);
        ing.setCalority(calority);
        changeTotalCalority(calority);

        price = round(ing.getPrice() * 0.001 * weight, 3);
        ing.setPrice(price);
        changeTotalPrice(price);

        expTerm = ing.getExpTerm();
        if (expTerm < totalExpTerm) setTotalExpTerm(expTerm);
        usedIngredients.add(ing);
    }

    public void deleteIngredient(String name) {
        boolean isTotalExpTerm = false;
        double minExpTerm = Double.MAX_VALUE;

        for (Ingredient ing : usedIngredients) {
            if (ing.getName().equals(name)) {
                changeTotalCalority(-(ing.getCalority()));
                changeTotalPrice(-(ing.getPrice()));
                if (getTotalExpTerm() == ing.getExpTerm()) isTotalExpTerm = true;
                usedIngredients.remove(ing);
                break;
            }
        }

        if (isTotalExpTerm)
            for (Ingredient ing : usedIngredients)
                minExpTerm = Double.min(minExpTerm, ing.getExpTerm());
    }

    public void sortIngredients(String param) {
        switch (param) {
            case "Name" :
                this.usedIngredients.sort(Comparator.comparing(Ingredient::getName));
                break;
            case "Calority" :
                this.usedIngredients.sort(Comparator.comparing(Ingredient::getCalority));
                break;
            case "Price" :
                this.usedIngredients.sort(Comparator.comparing(Ingredient::getPrice));
                break;
            case "Expiration term" :
                this.usedIngredients.sort(Comparator.comparing(Ingredient::getExpTerm));
                break;
        }
        Menu.infoWindow("Success", "Ingredients were sorted by " + param);
    }

    public List<Ingredient> setCalorityRange(double min, double max) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient ing : usedIngredients) {
            if (ing.getCalority() >= min && ing.getCalority() <= max)
                ingredients.add(ing);
        }
        return ingredients;
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "usedIngredients=" + usedIngredients +
                ", totalCalority=" + totalCalority +
                ", totalPrice=" + totalPrice +
                ", totalExpTerm=" + totalExpTerm +
                '}';
    }
}
