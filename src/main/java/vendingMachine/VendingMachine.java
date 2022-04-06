package vendingMachine;

import Exceptions.NotSufficientChangeException;
import Exceptions.NotValidCoinsExceptions;
import Exceptions.SoldOutException;

import java.util.*;

public class VendingMachine {

    /**
       Here is the place that I store all method together. First and foremost I had started by creating
       global variables to use them within different method at the same time.
       These variable are actually same with object's variables. Because after finding out with product is selected
       by customer we are going to assign product object's infos inside these global variables.
     */

    public static int currentTotalPrice;
    public String SelectedProduct;
    public int SelectedCode;
    public int priceOfSelectedProduct;
    Scanner scan = new Scanner(System.in);

    /**
        Here we have created a 'List' which stores 'Product' class's object inside itself.
        After creating product object from 'Product class' , we will add inside this list inside the
        VendingMachine constructor.

        Because whenever we need product infos to compared with selected item we will call this 'products' List
        to take same infos.
    */
    private List<Product> products = new ArrayList<>();

    Product water = new Product("Water", 40, 1, 1);
    Product crisp = new Product("Crisp",65 , 2, 1);
    Product chocolate = new Product("Chocolate", 75, 3, 1);


    public VendingMachine() {

        products.add(water);
        products.add(crisp);
        products.add(chocolate);

    }

    /**
        Here is the basic screen displaying Welcoming Message to customer.
     */

    public void displayOpeningMessage() {

        System.out.println(" *********************************************");
        System.out.println("    WELCOME TO THE VENDING MACHINE           ");
        System.out.println(" *********************************************");
        System.out.println(" Please Insert Accepted Coins as Explained");
        System.out.println("                                              ");

    }

    /**
     *
     * @throws NotValidCoinsExceptions
     *         -With this method we are basically able to display accepted coins and allow to customer to insert
     *         coins.
     *         -Checking every insert coin through by while loop. We call each coin defined inside Enum and
     *         if inserted coin matches with one of the Enum item triggers calculatePrice() to displayed
     *         total balanced the customer has.
     *         -In case of any inserted coin doesn't match with one of the Enum item, NotValidCoinsExceptions is thrown
     *         and display message to customer about invalid input.
     */
    public void displayedAcceptedCoins_AndGetCoins() throws NotValidCoinsExceptions {

        System.out.println(" All Accepted Coins are shown as Below");
        System.out.println("                                      ");
        System.out.println(" 1 Cent | 5 Cents | 10 Cents| 25 Cents");
        System.out.print(" Please Insert Coins\n Loading... ");

        Scanner scan = new Scanner(System.in);

        boolean flag = true;
        int customerRespond = scan.nextInt();
        while (flag == true) {

            int count = 0;
            for (AcceptedCoins coins : AcceptedCoins.values()) {

                if (coins.getNumberValues() == (customerRespond)) {

                    currentTotalPrice += customerRespond;

                    calculatePrice(currentTotalPrice);

                    count++;
                }
            }
            try {
                if (count == 0)
                    throw new NotValidCoinsExceptions(" Invalid Coin >> Please Insert One Of The Accepted Coins | 1-5-10-25 cents ");
            }
            catch (NotValidCoinsExceptions e){
                System.out.println(e.getMessage());
            }

            /**
               -After the insertion messages as below are shown and gives option to stop inserting coins or
               continue to insert coins.
               -In case of stop topping up coins 'flag' variable turn to 'false' to stop while loop
             */

            System.out.printf("%-20s%-15s", " Stop Insert Coins >>>", " PRESS 0");
            System.out.printf("%-10s%25s","OR"," Continue To Insert Coins\n Loading...");
            customerRespond = scan.nextInt();
            if (customerRespond == 0) {
                flag = false;
            }
        }
    }

    /**

      @param currentTotalPrice
      -At every call of this method with current price as an argument,
      Vending machine display the current balance the customer has.
     */
    public void calculatePrice(int currentTotalPrice) {

        if (currentTotalPrice >= 100) {

            int amountOfEuro = currentTotalPrice / 100;

            int amountOfCents = currentTotalPrice % 100;

            System.out.println(" Total balance is " + amountOfEuro + " dolar " + amountOfCents + " cents");

        } else {
            System.out.println(" Total balance is " + currentTotalPrice + " cents");

        }

    }

    /**

     @throws InterruptedException
     -When we execute this method ,machine display available products to customer.If one product is sold out
     it doesn't show run out product.
     */


    public void displayAvailableProduct() throws InterruptedException {

        System.out.println(" *Code*\t\t\t*Product*\t\t\t*Price*");
        System.out.println(" --------------------------------------------------");
        int count=0;
        for (int i = 0; i < products.size(); i++) {

            if(products.get(i).getProductAmount() != 0) {

                System.out.printf("%3s%-15d%-18s%-10s", " ", products.get(i).getProductCode(), products.get(i).getProductName(), products.get(i).getProductPrice() + " cents");
                System.out.println();
            }else{
                count++;
            }
        }
                 try{
                        if(count == products.size()){
                            throw new SoldOutException(" Product not available");
                         }
                    }
                 catch (SoldOutException e) {
                        System.out.println(e.getMessage());
                     CalculatingChanges();
                     System.out.println("<<<HAVE A GOOD REST OF DAY!>>>");
                     System.out.println("       ***BYE BYE***");
                     System.exit(0);

                    }
        }

    /**

      @throws NotSufficientChangeException
      @throws SoldOutException
      @throws InterruptedException

        -Here we start to execute essential part of Vending Machine. After taking code of product from the customer
        first we will check this product is available or not.
        -If the product is available next step is checking customer's balance is sufficient to buy product or not.
        -If the balanced is not sufficient , we will have to different option. Insert coins or cancel transaction.
     */


    public void allowToSelectProduct() throws NotSufficientChangeException, SoldOutException, InterruptedException {

        System.out.println(" Please Select Your Product And Enter Code...........");

        SelectedCode = scan.nextInt();

        boolean isAvailable = isProductAvailable(SelectedCode);

        try {
            if (isAvailable == false) {

                throw new SoldOutException(" Product not available");
            }
        } catch (SoldOutException e) {
            System.out.println(e.getMessage());
            allowToSelectProduct();
        }

        for (int i = 0; i < products.size(); i++) {

            if (products.get(i).getProductCode() == (SelectedCode)) {

                SelectedProduct = products.get(i).getProductName();
                priceOfSelectedProduct = products.get(i).getProductPrice();

                try {
                    if (currentTotalPrice < priceOfSelectedProduct) {

                        int remaining = priceOfSelectedProduct - currentTotalPrice;

                        throw new NotSufficientChangeException(" Your Balance Is Not Enough For This Product, " + remaining + " cents is needed to complete your request");
                    }
                } catch (NotSufficientChangeException e) {

                    System.out.println(e.getMessage());

                    System.out.printf("%20s%-10s"," Insert Coins >>>", " PRESS 0");
                    System.out.printf("%20s%-10s%n","CANCEL >>>"," PRESS 1");
                    int respond = scan.nextInt();
                    if (respond == 0) {
                        displayedAcceptedCoins_AndGetCoins();
                        displayAvailableProduct();
                        allowToSelectProduct();
                    } else {
                        cancel();
                        System.exit(0);
                    }
                }
            }
        }
    }

    /**
     *
     * @throws InterruptedException
     *  - Here we used this method to VERIFY product to buy or CANCEL product to buy.
     *  and with for loop we calculate how many product we have left.
     *
     */

    public void RemainingChanges() throws InterruptedException {

            System.out.println(" Your Selected Product is " + SelectedProduct);

            System.out.printf("%-20s%10s"," VERIFY Your Product >>>","PRESS 1");
            System.out.printf("%-20s%10s%n","\t\tCANCEL Your Product >>>","PRESS 0");

            int customerRespond = scan.nextInt();
            if(customerRespond==1) {

                currentTotalPrice = currentTotalPrice - priceOfSelectedProduct;

                CalculatingChanges();

                for (Product product : products) {

                    if (product.getProductName().equals(SelectedProduct)) {

                        product.setProductAmount(product.getProductAmount() - 1);
                    }else{
                        continue;
                    }

                }
            }
            else{
                cancel();
            }
        }

    /**
     *
     * @throws InterruptedException
     *  - Here we used this method to SELECT a new product to buy or CANCEL product to buy.
     */

        public void cancel () throws InterruptedException {

            System.out.println(" You selected to CANCEL");

            System.out.printf("%-20s%10s"," SELECT A New Product >>>","PRESS 1");
            System.out.printf("%-20s%10s%n","\t\tCANCEL Your Request >>>","PRESS 0");

            int customerRespond = scan.nextInt();
            if(customerRespond==0) {

                System.out.println(" Your Change is " + currentTotalPrice + " cents");

                CalculatingChanges();
            }
            else{
                allowToSelectProduct();
                RemainingChanges();
            }
        }
    /**
     *
     * @throws InterruptedException
     *  - Here we used this method to CALCULATE to remaining change given to customer by Vending Machine to the customer
     */

        public void CalculatingChanges () throws InterruptedException {

            int remaining = 0;
            int TWENTY_FIVE_cent = currentTotalPrice / 25;
            remaining = currentTotalPrice % 25;
            int TEN_cent = remaining / 10;
            remaining = remaining % 10;
            int FIVE_cent = remaining / 5;
            remaining = remaining % 5;
            int ONE_cent = remaining;

            int[] refund = {ONE_cent, FIVE_cent, TEN_cent, TWENTY_FIVE_cent};
            String[] changes = {" 1 cent", " 5 cents", " 10 Cents", " 25 cents"};


            System.out.println(" Your remaining change is " + currentTotalPrice + " cents....");
            System.out.println(" ((((Sound of calculating changes of VendingMachine... Still Processing $-) ))) ");
            for (int i = 0; i < 10; i++) {
                Thread.sleep(300);
                System.out.print(" .");
            }
            System.out.println(" Thanks For Your Patience, Please Take Your Changes");

            Thread.sleep(1000);

            for (int i = 0; i < refund.length; i++) {

                if (refund[i] != 0) {

                    System.out.println(" "+refund[i] + changes[i]);

                }
            }

        }

    /**
     *
     * @param SelectedCode
     * @return
     *
     * Here we are checking basically the selected product is available or not
     */

    public boolean isProductAvailable(int SelectedCode){

            for (Product product : products) {

                if(product.getProductCode()==SelectedCode) {

                    if (product.getProductAmount() < 1) {

                        return false;
                    }
                }
            }
            return true;
        }

    /**
     * AND finally here is resetting all saved variables before and make ready for next buying.
     */

    public void resetVendingMachine() {

            currentTotalPrice = 0;
            SelectedProduct = null;
            SelectedCode = 0;
            priceOfSelectedProduct = 0;
        }
    }

