package main.homework4;

public class InsufficientFundsException extends MyCustomBankException{
    public InsufficientFundsException(String message) {
        super(message);
    }
}
