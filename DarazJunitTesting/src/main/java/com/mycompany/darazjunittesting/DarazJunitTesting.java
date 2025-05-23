package com.mycompany.darazjunittesting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simulates backend functionality for a simplified Daraz-like platform.
 */
public class DarazJunitTesting {

    /**
     * Returns a product name based on the provided product ID.
     *
     * @param id the product ID
     * @return product name if ID is valid; otherwise "Unknown Product"
     */
    public String getProductById(int id) {
        return switch (id) {
            case 1 -> "Smartphone";
            case 2 -> "Laptop";
            case 3 -> "Shoes";
            default -> "Unknown Product";
        };
    }

    /**
     * Returns a list of available product categories.
     *
     * @return an unmodifiable list of categories
     */
    public List<String> getAvailableCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Electronics");
        categories.add("Fashion");
        categories.add("Groceries");
        categories.add("Home Appliances");
        return Collections.unmodifiableList(categories); // Prevent external modification
    }
}
