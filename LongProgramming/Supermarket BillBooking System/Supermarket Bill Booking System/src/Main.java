import java.util.*;

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getTotal() {
        return product.price * quantity;
    }
}

class Supermarket {
    List<Product> products = new ArrayList<>();
    List<CartItem> cart = new ArrayList<>();

    void addProduct(Product p) {
        products.add(p);
    }

    void displayProducts() {
        for (Product p : products) {
            System.out.println(p.id + " " + p.name + " " + p.price);
        }
    }

    Product findProduct(int id) {
        for (Product p : products) {
            if (p.id == id) return p;
        }
        return null;
    }

    void addToCart(int id, int qty) {
        Product p = findProduct(id);
        if (p != null) {
            cart.add(new CartItem(p, qty));
            System.out.println("Added to cart");
        } else {
            System.out.println("Product not found");
        }
    }

    void generateBill() {
        double total = 0;
        System.out.println("------ BILL ------");
        for (CartItem c : cart) {
            System.out.println(c.product.name + " x " + c.quantity + " = " + c.getTotal());
            total += c.getTotal();
        }
        System.out.println("Total Amount: " + total);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Supermarket sm = new Supermarket();

        sm.addProduct(new Product(1, "Rice", 50));
        sm.addProduct(new Product(2, "Milk", 30));
        sm.addProduct(new Product(3, "Bread", 25));

        while (true) {
            System.out.println("1.View Products 2.Add to Cart 3.Bill 4.Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                sm.displayProducts();
            } else if (ch == 2) {
                System.out.print("Enter product id: ");
                int id = sc.nextInt();
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                sm.addToCart(id, qty);
            } else if (ch == 3) {
                sm.generateBill();
            } else {
                break;
            }
        }
    }
}