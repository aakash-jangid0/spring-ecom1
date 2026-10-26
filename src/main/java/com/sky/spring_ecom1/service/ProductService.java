package com.sky.spring_ecom1.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sky.spring_ecom1.model.Product;
import com.sky.spring_ecom1.repo.ProductRepo;

@Service 
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    List<Product> products = List.of(
          new Product(0, "Wireless Mouse", "Ergonomic wireless mouse with adjustable DPI.", "Logitech", 24.99, "Electronics", LocalDate.parse("2024-03-15"), true, 85),
          new Product(0, "Mechanical Keyboard", "Tenkeyless mechanical keyboard with blue switches.", "Keychron", 79.50, "Electronics", LocalDate.parse("2023-11-02"), true, 32),
          new Product(0, "Stainless Steel Water Bottle", "Insulated 750 ml bottle that keeps drinks cold.", "Hydro Flask", 34.00, "Outdoor", LocalDate.parse("2024-05-20"), true, 48),
          new Product(0, "Everyday Backpack", "Lightweight 20 L backpack with padded laptop sleeve.", "North Face", 59.95, "Accessories", LocalDate.parse("2022-08-10"), true, 17),
          new Product(0, "Ceramic Coffee Mug", "Dishwasher-safe 350 ml ceramic mug.", "Fellow", 18.00, "Kitchen", LocalDate.parse("2023-06-01"), true, 120),
          new Product(0, "USB-C Hub", "Seven-port hub with HDMI, USB-A, and SD card reader.", "Anker", 42.99, "Electronics", LocalDate.parse("2024-01-12"), false, 0),
          new Product(0, "Running Shoes", "Breathable daily trainers with cushioned sole.", "Asics", 89.00, "Footwear", LocalDate.parse("2023-09-18"), true, 26),
          new Product(0, "Desk Lamp", "Dimmable LED lamp with adjustable arm.", "Philips", 31.75, "Home Office", LocalDate.parse("2022-12-05"), true, 14),
          new Product(0, "Notebook Set", "Three ruled A5 notebooks with recycled paper.", "Moleskine", 15.25, "Stationery", LocalDate.parse("2024-02-28"), true, 63),
          new Product(0, "Bluetooth Speaker", "Portable waterproof speaker with 12-hour battery.", "JBL", 49.99, "Electronics", LocalDate.parse("2021-07-14"), false, 0)
    );

    public void load() {
        productRepo.saveAll(products);
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product addOrUpdateProduct(Product product, MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                product.setImageName(imageFile.getOriginalFilename());
                product.setImageType(imageFile.getContentType());
                product.setImageData(imageFile.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productRepo.save(product);
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> searchProducts(String keyword) {
        return productRepo.findByKeyword(keyword);
    }

}
