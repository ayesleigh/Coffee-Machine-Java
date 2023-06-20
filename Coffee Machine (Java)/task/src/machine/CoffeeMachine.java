package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] coffeeOptions = {"espresso", "latte",
            "cappuccino"};
    private static final int[] prices = {4, 7, 6};
    private static int water;
    private static int milk;
    private static int  beans;
    private static int cups;
    private static int money;
    private static final String WRONG_INPUT_MESSAGE = "Wrong input. Please, " +
            "try again.";

    public static void main(String[] args) {
        boolean switchOff = false;
        water = 400;
        milk = 540;
        beans = 120;
        cups = 9;
        money = 550;

        while (!switchOff) {
            System.out.println("\nWrite action (buy, fill, take, remaining, " +
                    "exit):");
            String action = scanner.nextLine();

            switch (action) {
                case "buy" -> makeCoffee();
                case "take" -> withdrawCashFromCoffeeMachine();
                case "fill" -> resupplyCoffeeMachine();
                case "remaining" -> printResources();
                case "exit" -> switchOff = true;
            }
        }
    }

    private static void printResources() {
        System.out.printf("""
                          
                          The coffee machine has:
                          %d ml of water
                          %d ml of milk
                          %d g of coffee beans
                          %d disposable cups
                          $%d of money
                          """, water, milk, beans, cups, money);
    }

    private static void makeCoffee() {
        System.out.println("\nWhat do you want to buy? 1 - " +
                "espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String userChoice = scanner.nextLine();
        if ("back".equals(userChoice)) {
            return;
        }

        int userChoiceAsInt = Integer.parseInt(userChoice);
        if (userChoiceAsInt < 1 || userChoiceAsInt > coffeeOptions.length) {
            System.out.println(WRONG_INPUT_MESSAGE);
            return;
        }

        boolean isEnoughResources = true;
        String coffee = coffeeOptions[userChoiceAsInt - 1];

        switch (coffee) {
            case "espresso" -> {
                if (water < 250) {
                    System.out.println("Sorry, not enough water!");
                    isEnoughResources = false;
                }
                if (beans < 16) {
                    System.out.println("Sorry, not enough beans!");
                    isEnoughResources = false;
                }
                if (isEnoughResources) {
                    water -= 250;
                    beans -= 16;
                }
            }
            case "latte" -> {
                if (water < 350) {
                    System.out.println("Sorry, not enough water!");
                    isEnoughResources = false;
                }
                if (milk < 75) {
                    System.out.println("Sorry, not enough milk!");
                    isEnoughResources = false;
                }
                if (beans < 20) {
                    System.out.println("Sorry, not enough beans!");
                    isEnoughResources = false;
                }
                if (isEnoughResources) {
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                }
            }
            case "cappuccino" -> {
                if (water < 200) {
                    System.out.println("Sorry, not enough water!");
                    isEnoughResources = false;
                }
                if (milk < 100) {
                    System.out.println("Sorry, not enough milk!");
                    isEnoughResources = false;
                }
                if (beans < 12) {
                    System.out.println("Sorry, not enough beans!");
                    isEnoughResources = false;
                }
                if (isEnoughResources) {
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                }
            }
        }

        if (isEnoughResources) {
            cups--;
            money += prices[userChoiceAsInt - 1];
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    private static void resupplyCoffeeMachine() {
        try {
            System.out.println("Write how many ml of water you want to add: ");
            String addWater = scanner.nextLine();
            water += Integer.parseInt(addWater);

            System.out.println("Write how many ml of milk you want to add: ");
            String addMilk = scanner.nextLine();
            milk += Integer.parseInt(addMilk);

            System.out.println(
                    "Write how many grams of coffee beans you want to add: ");
            String addBeans = scanner.nextLine();
            beans += Integer.parseInt(addBeans);

            System.out.println("Write how many disposable cups you want to add:");
            String addCups = scanner.nextLine();
            cups += Integer.parseInt(addCups);
        } catch (NumberFormatException numberFormatException) {
            System.out.println(WRONG_INPUT_MESSAGE);
        }
    }

    private static void withdrawCashFromCoffeeMachine() {
        System.out.printf("%nI gave you $%d%n", money);
        money = 0;
    }
}
