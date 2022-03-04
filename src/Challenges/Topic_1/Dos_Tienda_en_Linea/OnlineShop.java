package Challenges.Topic_1.Dos_Tienda_en_Linea;

import java.util.*;

public class OnlineShop {

    private static Product[] productsList = new Product[3]; //  all 3 products are singleton


    public static void printProducts() {
        Arrays.stream(productsList).forEach(product -> {
            System.out.println("-- " + product.getName() + "\t\t$" + product.getPrice());
        });
    }

    public static int printMenu() {
        Scanner read = new Scanner(System.in);
        int input = 0;
        System.out.println("");
        System.out.println("1. See all products.");
        System.out.println("2. Change products prices.");
        System.out.println("3. Manage Subscribe Users list.");
        System.out.println("4. exit.");
        while (true) {
            System.out.print("> ");
            try {
                input = read.nextInt();
            } catch (InputMismatchException e) {
                read.nextLine();        //  flush input buffer from scanner object
                continue;
            }
            if (input >= 1 && input <= 4)
                return input;
            else
                System.err.println("-- invalid input");
        }
    }

    public static void manageUsers() {
        Scanner read = new Scanner(System.in);
        SubscribedUsersHandler subscribedUsers = SubscribedUsersHandler.getInstance();  //  this handler is an observer implementation also singleton
        int input = 0;
        subscribedUsers.printSubscribedUsers(); //  print all subscribed user into observer
        while (true) {      //  menu for action with users
            System.out.println("");
            System.out.println("1. subscribe new user");
            System.out.println("2. unsubscribe user");
            System.out.print("> ");
            try {
                input = read.nextInt();
            } catch (InputMismatchException e) {
                read.nextLine();        //  flush input buffer from scanner object
                continue;
            }
            if (input >= 1 && input <= 2)
                break;
        }
        if (input == 1) {
            if (!subscribedUsers.getSubscribedUsers().isEmpty()) {
                //  search on users list for the last number and add a new user with last number + 1
                int last = subscribedUsers.getSubscribedUsers().stream().max(Comparator.comparing(User::getNumUser)).get().getNumUser();
                subscribedUsers.getSubscribedUsers().add(new User(last + 1));
                subscribedUsers.printSubscribedUsers(); //  print all subscribed user into observer
            } else {
                subscribedUsers.getSubscribedUsers().add(new User(1));
                subscribedUsers.printSubscribedUsers(); //  print all subscribed user into observer
            }
        } else {
            System.out.print("Insert user number: ");
            int numUser = read.nextInt();
            Observer user = null;
            for (User u : subscribedUsers.getSubscribedUsers()) {   //  search for the user with the input number
                if (u.getNumUser() == numUser) {
                    user = u;
                    break;
                }
            }
            if (user != null) {
                subscribedUsers.getSubscribedUsers().remove(user);
                subscribedUsers.printSubscribedUsers(); //  print all subscribed user into observer
                return;
            }
            System.out.println("-- any user with that number");
        }
    }

    public static void changePrices() {
        Scanner read = new Scanner(System.in);
        SubscribedUsersHandler subscribedUsers = SubscribedUsersHandler.getInstance();  //  this handler is an observer implementation also singleton
        int product = 0;
        double newPrice = 0;
        while (true) {
            System.out.print("Insert number of product to change price: ");
            try {
                product = read.nextInt();
                if (product < 1 || product > 3)
                    throw new InputMismatchException();
                else
                    break;
            } catch (InputMismatchException e) {
                read.nextLine(); //  flush input buffer from scanner object
                continue;
            }
        }
        System.out.print("Insert new price: ");
        try {
            newPrice = read.nextDouble();
            if (newPrice < 0)
                throw new InputMismatchException();
        } catch (InputMismatchException e) {
            read.nextLine(); //  flush input buffer from scanner object
            System.err.println("-- invalid input");
            return;
        }
        productsList[product - 1].setPrice(newPrice);
        subscribedUsers.notifyAll("The " + productsList[product - 1].getName() + " has changed his price to $" + newPrice);
    }

    public static void run() {
        ProductFactory productFactory = new ProductFactory();   //  implements a factory method
        productsList[0] = productFactory.getProduct(ProductType.ONE);
        productsList[1] = productFactory.getProduct(ProductType.TWO);
        productsList[2] = productFactory.getProduct(ProductType.THREE);
        while (true) {
            switch (printMenu()) {
                case 1 -> {     //  see all products
                    printProducts();
                    continue;
                }
                case 2 -> {     //  change products prices
                    changePrices();
                    continue;
                }
                case 3 -> {     //  manage users
                    manageUsers();
                    continue;
                }
                case 4 -> {     //  exit
                    break;
                }
            }
            break;
        }
    }

    public static void main(String[] args) {
        run();
    }
}