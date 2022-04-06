package vendingMachine;

import java.util.Scanner;

public class ShoppingCart {

    public static void main(String[] args) throws InterruptedException {

        /**
            Here is the main class to run our Vending Machine.
            This class has been created to communicate with customers by simulating UI for Vending Machine.
            We have just created an object from VendingMachine class to call methods in order.
         */

        VendingMachine vendingMachine = new VendingMachine();
        Scanner scan = new Scanner(System.in);

        boolean request = true;
        while(request) {

            vendingMachine.displayOpeningMessage();

            vendingMachine.displayedAcceptedCoins_AndGetCoins();

            vendingMachine.displayAvailableProduct();

            vendingMachine.allowToSelectProduct();

            vendingMachine.RemainingChanges();

            System.out.println(" Would you like to make another purchase? Type 'Yes' or 'No'");

            String respond = scan.nextLine();

            if(respond.equalsIgnoreCase("No")){
                request = false;
                System.out.println("<<<HAVE A GOOD REST OF DAY!>>>");
                System.out.println("       ***BYE BYE***");
            }
            else{
                vendingMachine.resetVendingMachine();
            }
        }
    }
}

