@PostMapping("/add")
public String addProduct(@RequestParam String title,
                         @RequestParam String manufacturer,
                         @RequestParam double price,
                         @RequestParam String category,
                         @RequestParam String size,
                         @RequestParam String imageUrl,
                         @RequestParam String description) {

    products.add(new Product(title, manufacturer, price, category, size, imageUrl, description));
    return "redirect:/";
}

private List<User> users = new ArrayList<>();

@GetMapping("/register")
public String registerForm() {
    return "register";
}

@PostMapping("/register")
public String registerUser(@RequestParam String name,
                           @RequestParam String address,
                           @RequestParam String paymentMethod) {

    users.add(new User(name, address, paymentMethod));
    return "redirect:/";
}

@GetMapping("/search")
public String search(@RequestParam String query, Model model) {

    List<Product> results = new ArrayList<>();

    for (Product p : products) {
        if (p.getTitle().toLowerCase().contains(query.toLowerCase()) ||
            p.getCategory().toLowerCase().contains(query.toLowerCase()) ||
            p.getManufacturer().toLowerCase().contains(query.toLowerCase())) {

            results.add(p);
        }
    }

    model.addAttribute("products", results);
    return "index";
}

@PostMapping("/review")
public String review(@RequestParam Long id,
                     @RequestParam int rating,
                     @RequestParam String review) {

    Product p = productRepository.findById(id).orElse(null);

    if (p != null) {
        p.setRating(rating);
        p.setReview(review);
        productRepository.save(p);
    }

    return "redirect:/";
}

@GetMapping("/sort")
public String sort(@RequestParam String type, Model model) {

    List<Product> products;

    switch (type) {
        case "priceAsc":
            products = productRepository.findAllByOrderByPriceAsc();
            break;
        case "priceDesc":
            products = productRepository.findAllByOrderByPriceDesc();
            break;
        case "title":
            products = productRepository.findAllByOrderByTitleAsc();
            break;
        default:
            products = productRepository.findAll();
    }

    model.addAttribute("products", products);
    return "index";
}
private List<Product> cart = new ArrayList<>();
