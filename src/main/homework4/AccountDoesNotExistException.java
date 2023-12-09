package main.homework4;

public class AccountDoesNotExistException extends MyCustomBankException{
    public AccountDoesNotExistException(String message) {
        super(message);
    }
}
