package main.homework4;

public class Account {
    private String accountOwner;
    private double balance;

    /**
     * Конструктор банковского счета
     * @param balance Начальный баланс при открытии счета
     * @throws IllegalArgumentException При попытке создания счета с отрицательным балансом
     */
    public Account(String accountOwner, double balance) throws IllegalArgumentException {
        if (balance < 0) {
            throw new IllegalArgumentException("Нельзя открывать счет с отрицательным балансом");
        }
        this.accountOwner = accountOwner;
        this.balance = balance;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Метод пополнения банковского счета
     * @param amount сумма пополнения
     * @return true при успешном выполнении операции
     * @throws IllegalArgumentException При попытке пополнения баланса на отрицательное значение
     */
    public boolean depositToAccount(double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Нельзя пополнить счет на отрицательную сумму");
        }
        this.balance += amount;
        System.out.printf("Счет пополнен на %.2f. Текущий баланс %.2f\n", amount, balance);
        return true;
    }

    /**
     * Метод снятия денег с банковского счета
     * @param amount сумма снятия
     * @return true при успешной выполнении операции
     * @throws InsufficientFundsException При попытке снять с счета сумму превышающую баланс счета.
     */
    public boolean withdrawFromAccount(double amount) throws InsufficientFundsException, IllegalArgumentException {
        if (amount > balance) {
            throw new InsufficientFundsException(
                    String.format("На вашем счете недостаточно средств. Баланс вашего счета: %.2f", balance));
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Сумма списания не может быть отрицательной");
        }

        this.balance -= amount;
        System.out.printf("Списано %.2f. Текущий баланс %.2f\n", amount, balance);
        return true;
    }

    @Override
    public String toString() {
        return String.format("Владелец счета %s. Баланс счета: %.2f", accountOwner, balance);
    }
}
