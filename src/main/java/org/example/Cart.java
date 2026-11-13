
package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean removeProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void clear() {
        products.clear();
    }

    @Override
    public String toString() {
        if (products.isEmpty()) {
            return "Кошик порожній.";
        }

        StringBuilder sb = new StringBuilder("Кошик містить:\n");

        for (Product product : products) {
            sb.append(product.getId())
                    .append(" - ")
                    .append(product.getName())
                    .append(" - ")
                    .append(String.format(java.util.Locale.US,
                            "%.2f", product.getPrice()))
                    .append(" грн\n");
        }

        sb.append("Загальна вартість: ")
                .append(String.format(java.util.Locale.US,
                        "%.2f", getTotalPrice()))
                .append(" грн");

        return sb.toString();
    }
}
