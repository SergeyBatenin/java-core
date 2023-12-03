package homework1.regular;

public class MathDecorator {

    public static String add(int firstNumber, int secondNumber) {
        return String.format("Результат сложения %d и %d равен %d",
                firstNumber, secondNumber, SimpleMath.add(firstNumber, secondNumber));
    }

    public static String sub(int firstNumber, int secondNumber) {
        return String.format("Результат вычитания %d и %d равен %d",
                firstNumber, secondNumber, SimpleMath.add(firstNumber, secondNumber));
    }

    public static String mult(int firstNumber, int secondNumber) {
        return String.format("Результат умножения %d и %d равен %d",
                firstNumber, secondNumber, SimpleMath.add(firstNumber, secondNumber));
    }

    public static  String div(int firstNumber, int secondNumber) {
        return String.format("Результат деления %d и %d равен %d",
                firstNumber, secondNumber, SimpleMath.add(firstNumber, secondNumber));
    }
}
