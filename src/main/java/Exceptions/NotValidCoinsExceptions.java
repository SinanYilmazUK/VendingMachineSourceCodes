package Exceptions;

public class NotValidCoinsExceptions extends RuntimeException{

    private String message;

    public NotValidCoinsExceptions(String message) {

        this.message = message; }

    @Override
    public String getMessage(){

        return message;
    }
}
