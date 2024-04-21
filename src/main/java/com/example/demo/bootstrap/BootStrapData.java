package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (partRepository.count() == 0 && productRepository.count() == 0) addSampleInventory();


        List<OutsourcedPart> outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for (OutsourcedPart part : outsourcedParts) {
            System.out.println(part.getName() + " " + part.getCompanyName());
        }


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products" + productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts" + partRepository.count());
        System.out.println(partRepository.findAll());

    }

    private void addSampleInventory() {
        /* Add sample parts */
        createAndSaveOutsourcedPart("Neck", 50.0, 10, 5, 5, "Neck Supplier Co.");
        createAndSaveOutsourcedPart("Fretboard", 30.0, 20, 5, 5, "Fretboard Supplies Ltd.");
        createAndSaveOutsourcedPart("Body", 100.0, 15, 5, 5, "Guitar Bodies Inc.");
        createAndSaveOutsourcedPart("Pickup", 75.0, 25, 5, 5, "Bare Knuckle Pickups Ltd");
        createAndSaveOutsourcedPart("Tuning Machine", 45.0, 30, 5, 5, "Sperzel USA Tuners");

        /* Add sample products */
        createAndSaveProduct("Stratocaster", 700.0, 5);
        createAndSaveProduct("Telecaster", 650.0, 5);
        createAndSaveProduct("Les Paul", 800.0, 5);
        createAndSaveProduct("SG", 750.0, 5);
        createAndSaveProduct("Jazzmaster", 720.0, 5);
    }

    private void createAndSaveOutsourcedPart(String name, double price, int inventory, int minInv, int maxInv, String companyName) {
        OutsourcedPart part = new OutsourcedPart();
        part.setName(name);
        part.setPrice(price);
        part.setInv(inventory);
        part.setMinInv(minInv);
        part.setMaxInv(maxInv);
        part.setCompanyName(companyName);
        outsourcedPartRepository.save(part);
    }

    private void createAndSaveProduct(String name, double price, int inventory) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setInv(inventory);
        productRepository.save(product);
    }
}
