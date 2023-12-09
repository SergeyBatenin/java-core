package main.homework4;

public class UnauthorizedAccessException extends MyCustomBankException{
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
