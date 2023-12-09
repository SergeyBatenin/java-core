package main.homework4;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private static int accountNumber = 0; // Имитация генерации номера банковского счета
    private Map<Integer, Account> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    /**
     * Метод открытия нового банковского счета
     * @param ownerName Имя владельца счета
     * @param amount Начальный баланс счета
     * @return true при успешном открытии счета, иначе false
     */
    public boolean createAccount(String ownerName, double amount) {
        Account newAccount;
        try {
            newAccount = new Account(ownerName, amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
        accounts.put(++accountNumber,newAccount);
        System.out.printf("Счет успешно открыт. Номер счета %d\n", accountNumber);
        return true;
    }

    /**
     * Метод пополнения счета
     * @param clientName Имя клиента
     * @param accountNumber номер банковског о счета
     * @param amount сумма пополнения
     * @return true при успешном пополнении счета, иначе генерируется исключение
     * @throws UnauthorizedAccessException При попытке воспользоваться чужим счетом
     * @throws AccountDoesNotExistException При попытке воспользоваться несуществующим счетом
     * @throws IllegalArgumentException При некорректной попытке пополнить счет
     */
    public boolean depositToAccount(String clientName, int accountNumber, double amount)
            throws UnauthorizedAccessException, AccountDoesNotExistException, IllegalArgumentException {

        Account account = findAccountByNumber(accountNumber);
        if (account == null) {
            throw new AccountDoesNotExistException("Такого счета не существует");
        }

        if (!isValidateAccountOwner(account, clientName)) {
            throw new UnauthorizedAccessException("Этот счет вам не принадлежит. Несанкционированная попытка доступа");
        }

        return account.depositToAccount(amount);
    }

    /**
     *
     * @param clientName Имя клиента
     * @param accountNumber Номер банковского  счета
     * @param amount сумму снятия
     * @return true при успешном выполнении операции
     * @throws AccountDoesNotExistException При попытке воспользоваться несуществующим счетом
     * @throws UnauthorizedAccessException При попытке воспользоваться чужим счетом
     * @throws InsufficientFundsException При некорректной попытке списания со счета
     */
    public boolean withdrawFromAccount(String clientName, int accountNumber, double amount)
            throws AccountDoesNotExistException, UnauthorizedAccessException, InsufficientFundsException {

        Account account = findAccountByNumber(accountNumber);
        if (account == null) {
            throw new AccountDoesNotExistException("Такого счета не существует");
        }

        if (!isValidateAccountOwner(account, clientName)) {
            throw new UnauthorizedAccessException("Этот счет вам не принадлежит. Несанкционированная попытка доступа");
        }

        return account.withdrawFromAccount(amount);
    }

    /**
     * Метод перевода средств между клиентами
     * @param senderName Имя отправителя
     * @param senderAccountNumber Счет отправителя
     * @param receiverAccountNumber Счет получателя
     * @param amount Сумма перевода
     * @return true при успешной операции перевода
     * @throws AccountDoesNotExistException Если сче отправителя или получателя не существует
     * @throws UnauthorizedAccessException Если имя клиента, осуществляющег оперевод не совпадет с именем владелцьа счета
     * @throws InsufficientFundsException Если у отправителя недостаточно средств для осуществления перевода
     * @throws IllegalArgumentException При некорректной сумме перевода
     */
    public boolean transferMoney(String senderName, int senderAccountNumber, int receiverAccountNumber,double amount)
            throws AccountDoesNotExistException, UnauthorizedAccessException, InsufficientFundsException, IllegalArgumentException {

        Account accountSender = findAccountByNumber(senderAccountNumber);
        if (accountSender == null) {
            throw new AccountDoesNotExistException(
                    "Выполнить перевод с данного счета невозможно. Такого счета не существует");
        }
        if (!isValidateAccountOwner(accountSender, senderName)) {
            throw new UnauthorizedAccessException(
                    "Перевод невозможен. Счет не принадлежит исполнителю. Несанкционированная попытка доступа");
        }

        Account accountReceiver = findAccountByNumber(receiverAccountNumber);
        if (accountReceiver == null) {
            throw new AccountDoesNotExistException("Перевод невозможен. Такого счета не существует.");
        }

        return accountSender.withdrawFromAccount(amount) && accountReceiver.depositToAccount(amount);
    }

    /**
     * Внутренний вспомогающий метод по поиску счета по его номеру
     * @param accountNumber Номер счета для поиска
     * @return банковский счет, если найдлет, иначе null
     */
    private Account findAccountByNumber(int accountNumber) {
        return accounts.get(accountNumber);
    }

    /**
     * Внутренний вспомогающий метод для проверки владельца счета
     * @param account банковский счет
     * @param clientName Имя клиента
     * @return true если имя владельца счета совпадает с именем клиента, иначе false
     */
    private boolean isValidateAccountOwner(Account account, String clientName) {
        return account.getAccountOwner().equals(clientName);
    }

    /**
     * Метод печати состояния банковских счетов и имена владельцев
     */
    public void printStatisticAccounts() {
        accounts.forEach((accountNumber, account) -> System.out.println(accountNumber + " - " + account));
        System.out.println();
    }
}
