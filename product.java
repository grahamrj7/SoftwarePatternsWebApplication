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
