import java.util.Scanner;

class dish{
    private String name;
    private double price;

    public dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return name + "-$" + price;
    }
}

class restaurant{
    private String name;
    private dish[] menu;

    public restaurant(String name, dish[] menu) {
        this.name = name;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public dish[] getMenu() {
        return menu;
    }

    public void showMenu() {
        for (int i=0; i< menu.length; i++) {
            System.out.println((i+1) + "." + menu[i]);
        }
    }
}

class order{
    private dish[] items;
    private int count;

    public order(int capacity){
        items = new dish[capacity];
        count = 0;
    }

    public boolean adddish( dish d) {
        if (count< items.length){
            items[count] = d;
            count++;
            return true;
        }
        else {
            return false;
        }
    }

    public void showorder() {
        if(count==0){
            System.out.println("your order is empty");
            return;
        }
        System.out.println("your order:");
        for(int i = 0; i< count; i++){
            System.out.println((i+1) + "." + items[i]);
        }
    }

    public double getTotal(){
        double total = 0;
        for (int i =0; i<count; i++){
            total += items[i].getPrice();
        }
        return total;
    }

    public int getCount(){
        return count;
    }
}

public class FoodOrderSystem {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        dish[] menu1 = new dish[3];
        menu1[0] = new dish("Burger", 6.99);
        menu1[1] = new dish("Fries", 2.99);
        menu1[2] = new dish("Coke", 1.99);

        dish[] menu2 = new dish[3];
        menu2[0] = new dish("Pizza", 7.99);
        menu2[1] = new dish("Pasta", 6.59);
        menu2[2] = new dish("Garlic Bread", 3.99);

        restaurant[] restaurants = new restaurant[2];
        restaurants[0] = new restaurant("Fast Bites", menu1);
        restaurants[1] = new restaurant("Italiano", menu2);

        order order = new order(10);

        while (true){
            System.out.println("Select a restaurant:");
            for (int i = 0; i < restaurants.length; i++){
                System.out.println((i+1) + ". " + restaurants[i].getName());
            }
            System.out.println("0. Checkout and Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            if (choice == 0){
                order.showorder();
                if (order.getCount() > 0){
                    System.out.printf("Total: $%.2f\n", order.getTotal());
                    System.out.print("Confirm Order? (1 = Yes, 0 = No): ");

                    int confirm = sc.nextInt();
                    if (confirm == 1){
                        System.out.println("Thank You! Your order has been placed.");
                    }
                    else {
                        System.out.println("Order cancelled.");
                    }
                }
                break;
            }


            if (choice < 1 || choice > restaurants.length){
                System.out.println("Invalid choice.");
                continue;
            }

            restaurant selected = restaurants[choice - 1];

            while (true){
                System.out.println("\nMenu for " + selected.getName() + ":");
                selected.showMenu();
                System.out.println("0. Back to restaurants");
                System.out.print("Enter dish number to add to order: ");

                int dishchoice = sc.nextInt();

                if (dishchoice == 0){
                    break;
                }

                if (dishchoice < 1 || dishchoice > selected.getMenu().length){
                    System.out.println("Invalid dish number.");
                    continue;
                }

                dish chosendish = selected.getMenu()[dishchoice - 1];
                boolean added = order.adddish(chosendish);

                if (added){
                    System.out.println(chosendish.getName() + " added to order");
                }
                else {
                    System.out.println("Cannot add more items.");
                }
            }
        }
        sc.close();
    }
}
