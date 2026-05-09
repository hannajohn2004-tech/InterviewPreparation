import java.util.*;

class Customer {
    int id;
    String name;
    String address;

    Customer(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}

class Dress {
    int id;
    String name;
    double price;
    int stock;

    Dress(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}

class CartItem {
    Dress dress;
    int quantity;

    CartItem(Dress dress, int quantity) {
        this.dress = dress;
        this.quantity = quantity;
    }
}

class Order {
    List<CartItem> items;
    double total;
    Customer customer;

    Order(List<CartItem> items, double total, Customer customer) {
        this.items = items;
        this.total = total;
        this.customer = customer;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<Dress> store = new ArrayList<>();
    static List<CartItem> cart = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();
    static Customer currentCustomer;

    public static void main(String[] args) {

        System.out.print("Enter Customer ID: ");
        int cid = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Customer Name: ");
        String cname = sc.nextLine();
        System.out.print("Enter Address: ");
        String addr = sc.nextLine();

        currentCustomer = new Customer(cid, cname, addr);

        store.add(new Dress(1, "Kurti", 1200, 10));
        store.add(new Dress(2, "Saree", 2500, 5));
        store.add(new Dress(3, "Lehenga", 5000, 3));

        while (true) {
            System.out.println("\n1.View Dresses\n2.Add to Cart\n3.View Cart\n4.Place Order\n5.View Orders\n6.Exit");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> viewDresses();
                case 2 -> addToCart();
                case 3 -> viewCart();
                case 4 -> placeOrder();
                case 5 -> viewOrders();
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid");
            }
        }
    }

    static void viewDresses() {
        for (Dress d : store) {
            System.out.println(d.id + " " + d.name + " Price:" + d.price + " Stock:" + d.stock);
        }
    }

    static void addToCart() {
        System.out.print("Enter Dress ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        for (Dress d : store) {
            if (d.id == id) {
                if (d.stock >= qty) {
                    cart.add(new CartItem(d, qty));
                    System.out.println("Added to cart");
                } else {
                    System.out.println("Not enough stock");
                }
                return;
            }
        }
        System.out.println("Dress not found");
    }

    static void viewCart() {
        double total = 0;
        for (CartItem c : cart) {
            double sub = c.quantity * c.dress.price;
            total += sub;
            System.out.println(c.dress.name + " x" + c.quantity + " = " + sub);
        }
        System.out.println("Total: " + total);
    }
    static void placeOrder() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        double total = 0;
        for (CartItem c : cart) {
            total += c.quantity * c.dress.price;
            c.dress.stock -= c.quantity;
        }

        orders.add(new Order(new ArrayList<>(cart), total, currentCustomer));
        cart.clear();

        System.out.println("Order placed successfully for " + currentCustomer.name + ". Total = " + total);
    }

    static void viewOrders() {
        for (Order o : orders) {
            System.out.println("\nCustomer: " + o.customer.name);
            System.out.println("Address: " + o.customer.address);
            for (CartItem c : o.items) {
                System.out.println(c.dress.name + " x" + c.quantity);
            }
            System.out.println("Total: " + o.total);
        }
    }
}