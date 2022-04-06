package vendingMachine;

public class Product {

    /**
          We are able to hold all product info with the help of this 'Product' class.
          We can easily create an object and insert all infos that are shown as below.
          Because all Vending Machine's Products have common feature to store inside creating object.
          In this project we store product name, product price, product code and product amount.
          All these variables should be private.
          Only productAmount has setter() method to hold current amount of product into Vending Machine.
     */

    private String productName;
    private int productPrice;
    private int productCode;
    private int productAmount;

    public Product() {

    }

    public Product(String productName, int productPrice, int productCode, int productAmount) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCode = productCode;
        this.productAmount = productAmount;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getProductCode() {
        return productCode;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

}
