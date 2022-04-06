package vendingMachine;

public enum AcceptedCoins {

    /**
        By creating Enum, we store accepted coins inside this Enum. Because these coins will not going to change
        and need to be remained without being changed in somewhere.
        We will call these coins to check if the customer insert valid accepted coins or not.

     */

    ONE_CENT(1), FIVE_CENTS(5),
    TEN_CENTS(10), TWENTYFIVE_CENTS(25);

    private final int numberValues;

    AcceptedCoins(int numberValues) {

        this.numberValues = numberValues;
    }

    public int getNumberValues(){

        return numberValues;
    }
}
