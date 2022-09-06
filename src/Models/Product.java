/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 * @author fomri
 */
public enum Product {
    BURGER(1, "burger.jpg", 0.55f, "American"), SUSHI(2, "sushi.jpg", 0.78f, "Japanese "), TACOS(3, "tacos.jpg", 0.56f, "Mexican");
    private int id;
    private String imageFile;
    private float price;
    private String category;
    private String name;

    Product(int id, String imageFile, float price, String category) {
        this.id = id;
        this.imageFile = imageFile;
        this.price = price;
        this.category = category;
    }

    Product(int id, String imageFile, float price) {
        this.id = id;
        this.imageFile = imageFile;
        this.price = price;
    }

    Product(String name, int id, String imageFile, float price, String category) {
        this.name = name;
        this.id = id;
        this.imageFile = imageFile;
        this.price = price;
        this.category = category;
    }

    public String getImageFile() {
        return imageFile;
    }

    public float getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
