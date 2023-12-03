package main.homework2;

import java.util.Random;
import java.util.Scanner;

public class Program {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';

    private static final int WIN_COUNT = 3;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int maxGameTurns;
    private static int currentGameTurns;


    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkGameState(DOT_HUMAN, "Вы победили!")) {
                    break;
                }
                aiTurn();
                printField();
                if (checkGameState(DOT_AI, "Победил компьютер!"))
                    break;
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }
    }

    /**
     * Инициализация игрового поля
     */
    static void initialize() {
        fieldSizeY = 3;
        fieldSizeX = 3;
        maxGameTurns = fieldSizeY * fieldSizeX;
        currentGameTurns = 0;

        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать текущего состояния игрового поля
     */
    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print("-" + (i + 1));
        }
        System.out.println("-");

        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print(y + 1 + "|");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Ход игрока (человека)
     */
    static void humanTurn() {
        int x;
        int y;

        do {
            System.out.print("Введите координаты хода X и Y (от 1 до 3)\nчерез пробел: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));

        field[y][x] = DOT_HUMAN;
        currentGameTurns++;
    }

    /**
     * Ход игрока (компьютера)
     */
    static void aiTurn() {
        int x;
        int y;

        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));

        field[y][x] = DOT_AI;
        currentGameTurns++;
    }

    /**
     * Проверка, является ли ячейка игрового поля пустой
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    /**
     * Проверка доступности ячейки игрового поля
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }


    /**
     * Метод проверки состояния игры
     *
     * @param dot фишка игрока
     * @param s   победный слоган
     * @return результат проверки состояния игры
     */
    static boolean checkGameState(char dot, String s) {
        if (checkWin(dot)) {
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false; // Игра продолжается
    }

    /**
     * Проверка на ничью
     *
     * @return true если количество сделанных ходов становится равно максимальному количеству клеток
     */
    static boolean checkDraw() {
//        for (int y = 0; y < fieldSizeY; y++) {
//            for (int x = 0; x < fieldSizeX; x++) {
//                if (isCellEmpty(x, y))
//                    return false;
//            }
//        }
//        return true;
        return currentGameTurns == maxGameTurns; // мне показалось это чуть проще чем каждый раз бежать по массиву
    }

    /**
     * Проверка победы игрока
     *
     * @param dot фишка игрока
     * @return признак победы
     */
    static boolean checkWin(char dot) {
//        // Проверка по трем горизонталям
//        if (field[0][0] == dot && field[0][1] == dot && field[0][2] == dot) return true;
//        if (field[1][0] == dot && field[1][1] == dot && field[1][2] == dot) return true;
//        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;
//
//        // Проверка по трем вертикалям
//        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
//        if (field[0][1] == dot && field[1][1] == dot && field[2][1] == dot) return true;
//        if (field[0][2] == dot && field[1][2] == dot && field[2][2] == dot) return true;
//
//        // Проверка по диагоналям
//        if (field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) return true;
//        return field[0][2] == dot && field[1][1] == dot && field[2][0] == dot;
        boolean winFlag = true;
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                // если метка на текущей позиции равна метке игрока, тогда совершаем проверки
                if (field[y][x] == dot) {
                    // Проверка горизонтали вправо, если в правую сторону еще достаточно ячеек, чтобы попытаться выиграть
                    if (x + WIN_COUNT <= fieldSizeX) {
                        for (int k = 0; k < WIN_COUNT; k++) {
                            // если ячейка поля не совпадает с меткой игрока прерывает цикл, нет смысла проверять
                            if (field[y][x + k] != dot) {
                                winFlag = false;
                                break;
                            }
                        }
                        // если флаг не поменялся значит у нас было необходимое количество меток для победы
                        if (winFlag) {
                            return true;
                        }
                    }

                    // Проверка вертикали вниз
                    winFlag = true;
                    if (y + WIN_COUNT <= fieldSizeY) {
                        for (int k = 0; k < WIN_COUNT; k++) {
                            if (field[y + k][x] != dot) {
                                winFlag = false;
                                break;
                            }
                        }
                        if (winFlag) {
                            return true;
                        }
                    }

                    // Проверка горизонтали вправо вверх
                    winFlag = true;
                    if (x + WIN_COUNT <= fieldSizeX && y - WIN_COUNT + 1 >= 0) {
                        for (int k = 0; k < WIN_COUNT; k++) {
                            if (field[y - k][x + k] != dot) {
                                winFlag = false;
                                break;
                            }
                        }
                        if (winFlag) {
                            return true;
                        }
                    }

                    // Проверка горизонтали вправо вниз
                    winFlag = true;
                    if (y + WIN_COUNT <= fieldSizeY && x + WIN_COUNT <= fieldSizeX) {
                        for (int k = 0; k < WIN_COUNT; k++) {
                            if (field[y + k][x + k] != dot) {
                                winFlag = false;
                                break;
                            }
                        }
                        if (winFlag) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    static boolean check1(int x, int y, char dot, int winCount) {
        return false;
    }

    static boolean check2(int x, int y, char dot, int winCount) {
        return false;
    }

    static boolean check3(int x, int y, char dot, int winCount) {
        return false;
    }

    static boolean check4(int x, int y, char dot, int winCount) {
        return false;
    }


}
