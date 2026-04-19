package com.example.store;

public class Product {

    private String name;
    private double price;
    private String category;
    private String size;

    public Product(String name, double price, String category, String size) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

package com.example.store;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private List<Product> products = new ArrayList<>();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/add")
    public String addForm() {
        return "add";
    }
}

    public String getSize() {
        return size;
    }
}
package com.example.store;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String manufacturer;
    private double price;
    private String category;
    private String size;
    private String imageUrl;
    private String description;

    public Product() {}

    public Product(String title, String manufacturer, double price,
                   String category, String size, String imageUrl, String description) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.price = price;
        this.category = category;
        this.size = size;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getManufacturer() { return manufacturer; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public String getSize() { return size; }
    public String getImageUrl() { return imageUrl; }
    public String getDescription() { return description; }
}

private int rating;
private String review;

@GetMapping("/checkout")
public String checkout() {

    for (Product p : cart) {
        if (p.getStock() > 0) {
            p.setStock(p.getStock() - 1);
            productRepository.save(p);
        }
    }

    cart.clear();
    return "redirect:/";
}
private int rating;
private String review;

public int getRating() { return rating; }
public String getReview() { return review; }

public void setRating(int rating) { this.rating = rating; }
public void setReview(String review) { this.review = review; }
