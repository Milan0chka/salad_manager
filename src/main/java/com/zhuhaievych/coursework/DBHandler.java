package com.zhuhaievych.coursework;

import com.zhuhaievych.coursework.controllers.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/chefsassistant";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void InsertIngredient(String name, double weight, double calority, double price, double expTerm) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "INSERT INTO ingredients (name, weight, calority, price, expTerm) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, weight);
            preparedStatement.setDouble(3, calority);
            preparedStatement.setDouble(4, price);
            preparedStatement.setDouble(5, expTerm);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DBUpdateIngredient(name, weight, calority, price, expTerm);
            Menu.infoWindow("Success", name + " was updated");
        }
    }

    public static void DBUpdateIngredient(String name, double weight, double calority, double price, double expTerm) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "UPDATE ingredients SET weight = weight + ?, calority = ?, price = ?, expTerm = ? WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, weight);
            preparedStatement.setDouble(2, calority);
            preparedStatement.setDouble(3, price);
            preparedStatement.setDouble(4, expTerm);
            preparedStatement.setString(5, name);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void DeleteIngredient(String name) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "DELETE FROM ingredients WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Menu.infoWindow("Error!", "No ingredient with name '" + name + "'");
        }

    }

    public static Ingredient SelectIngredient(String name) {
        Ingredient ing = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT name, weight, calority, price, expTerm FROM ingredients WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                ing = new Ingredient(rs.getString("name"), rs.getDouble("weight"), rs.getDouble("calority"),
                        rs.getDouble("price"), rs.getDouble("expTerm"));
            }
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ing;
    }

    public static List<String> SelectAllIngredients() {
        List<String> IngredientsFromDB = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT name FROM ingredients";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                IngredientsFromDB.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return IngredientsFromDB;
    }

    public static void InsertRecipe(String name, String recipe) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "INSERT INTO recipes (name, recipe) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, recipe);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Menu.infoWindow("Error", "Recipe '" + name + "' already exists!");
        }
    }

    public static void DeleteRecipe(String name) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "DELETE FROM recipes WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Menu.infoWindow("Error!", "No recipe with name '" + name + "'");
        }

    }

    public static String SelectRecipe(String name) {
        String recipe = "";
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT recipe FROM recipes WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                recipe = rs.getString("recipe");
            }
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return recipe;
    }

    public static List<String> SelectAllRecipes() {
        List<String> RecipesFromDB = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT name FROM recipes";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                RecipesFromDB.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return RecipesFromDB;
    }

    public static double getAvailableWeight(String name) {
        double weight = 0;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT weight FROM ingredients WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                weight = rs.getDouble("weight");
            }
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        weight *= 1000;
        return weight;
    }

    public static void updateAvailableWeight(String name, double usedWeight) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "UPDATE ingredients SET weight = weight + ? WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, usedWeight/1000);
            preparedStatement.setString(2, name);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
