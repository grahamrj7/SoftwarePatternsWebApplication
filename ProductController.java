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
