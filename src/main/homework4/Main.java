package main.homework4;

public class Main {
    public static void main(String[] args) {
        /*
        1. Создать программу управления банковским счетом (Account).

        Программа должна позволять пользователю вводить начальный баланс счета, сумму депозита и сумму снятия средств.
        При этом она должна обрабатывать следующие исключительные ситуации:

        Попытка создать счет с отрицательным начальным балансом должна вызывать исключение IllegalArgumentException
        с соответствующим сообщением.
        Попытка внести депозит с отрицательной суммой должна вызывать исключение IllegalArgumentException
        с соответствующим сообщением.
        Попытка снять средства, сумма которых превышает текущий баланс, должна вызывать исключение
        InsufficientFundsException с сообщением о недостаточных средствах и текущим балансом.

        Продемонстрируйте работу вашего приложения:
        Программа должна обрабатывать все исключения с помощью конструкции try-catch,
        выводя соответствующие сообщения об ошибках.

        2*.
        Создать несколько типов счетов, унаследованных от Account, например:
            CreditAcciunt, DebitAccount.
        Создать класс (Transaction), позволяющий проводить транзакции между счетами
        (переводить сумму с одного счета на другой)

        Класс Transaction должен возбуждать исключение в случае неудачной попытки перевести деньги
        с одного счета на другой.

        Продемонстрируйте работу вашего приложения:
        Программа должна обрабатывать все исключения с помощью конструкции try-catch,
        выводя соответствующие сообщения об ошибках.
         */
        Bank bank = new Bank();

        bank.createAccount("Vasya", -100); // попытка создания счета с отрицательным значением, Ошибка
        bank.createAccount("Vasya", 100); // Корректное создание счета

        bank.createAccount("Petya", 0); // Второй пользователь для проверки функционала банковских операций

        System.out.println("Начальное состояние счетов");
        bank.printStatisticAccounts();

        // Попытка пополнить счет на отрицательную сумму, Ошибка
        try {
            bank.depositToAccount("Vasya", 1, -1000);
        } catch (AccountDoesNotExistException | UnauthorizedAccessException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Попытка работы с чужим счетом, Ошибка
        try {
            bank.depositToAccount("Vasya", 2, 1000);
        } catch (AccountDoesNotExistException | UnauthorizedAccessException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Попытка работы с несуществующим счетом
        try {
            bank.depositToAccount("Vasya", 3, 1000);
        } catch (AccountDoesNotExistException | UnauthorizedAccessException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Корректное пополнение счета
        try {
            bank.depositToAccount("Vasya", 1, 500);
        } catch (AccountDoesNotExistException | UnauthorizedAccessException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // попытка снять сумму превыщающую баланс, Ошибка
        try {
            bank.withdrawFromAccount("Vasya", 1, 1000);
        } catch (AccountDoesNotExistException | InsufficientFundsException | UnauthorizedAccessException e) {
            System.out.println(e.getMessage());
        }

        // попытка снять сумму c чужого счета, Ошибка
        try {
            bank.withdrawFromAccount("Petya", 1, 100);
        } catch (AccountDoesNotExistException | InsufficientFundsException | UnauthorizedAccessException e) {
            System.out.println(e.getMessage());
        }

        // попытка снять сумму c несуществующего, Ошибка
        try {
            bank.withdrawFromAccount("Vasya", 3, 1000);
        } catch (AccountDoesNotExistException | InsufficientFundsException | UnauthorizedAccessException e) {
            System.out.println(e.getMessage());
        }

        // Корректная снятие средств с счета
        try {
            bank.withdrawFromAccount("Vasya", 1, 100);
        } catch (AccountDoesNotExistException | InsufficientFundsException | UnauthorizedAccessException e) {
            System.out.println(e.getMessage());
        }

        // Некорректный перевод недостаточно средств, Ошибка
        try {
            bank.transferMoney("Petya", 2, 1, 100);
        } catch (AccountDoesNotExistException | UnauthorizedAccessException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        // Некорректный перевод на несуществующий счет, Ошибка
        try {
            bank.transferMoney("Vasya", 1, 3, 100);
        } catch (AccountDoesNotExistException | UnauthorizedAccessException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        // Некорректный перевод с несуществующего счета, Ошибка
        try {
            bank.transferMoney("Vasya", 3, 2, 100);
        } catch (AccountDoesNotExistException | UnauthorizedAccessException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        // Корректный перевод
        try {
            bank.transferMoney("Vasya", 1, 2, 100);
        } catch (AccountDoesNotExistException | UnauthorizedAccessException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Конечное состояние счетов после операций");
        bank.printStatisticAccounts();
    }
}
