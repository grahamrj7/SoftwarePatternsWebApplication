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
@GetMapping("/add-to-cart")
public String addToCart(@RequestParam Long id) {

    if (loggedInUser == null) return "redirect:/login";

    productRepository.findById(id).ifPresent(cart::add);

    return "redirect:/cart";
}

@GetMapping("/cart")
public String viewCart(Model model) {

    if (loggedInUser == null) return "redirect:/login";

    model.addAttribute("cart", cart);
    model.addAttribute("user", loggedInUser);

    return "cart";
}
@GetMapping("/checkout")
public String checkout(Model model) {

    if (loggedInUser == null) return "redirect:/login";

    double total = 0;

    for (Product p : cart) {
        if (p.getStock() > 0) {
            p.setStock(p.getStock() - 1);
            productRepository.save(p);
            total += p.getPrice();
        }
    }

    double discount = total > 100 ? total * 0.10 : 0;
    double finalTotal = total - discount;

    loggedInUser.getPurchasedProducts().addAll(cart);
    loggedInUser.setLoyaltyPoints(loggedInUser.getLoyaltyPoints() + (int) finalTotal);
    userRepository.save(loggedInUser);

    cart.clear();

    model.addAttribute("message", "Purchase successful");
    model.addAttribute("total", total);
    model.addAttribute("discount", discount);
    model.addAttribute("finalTotal", finalTotal);
    model.addAttribute("products", productRepository.findAll());
    model.addAttribute("cartSize", cart.size());
    model.addAttribute("user", loggedInUser);

    return "index";
}
