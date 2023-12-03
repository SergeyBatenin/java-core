package homework1.sample;

import homework1.regular.MathDecorator;
import homework1.regular.SimpleMath;

public class Main {
    public static void main(String[] args) {
        System.out.println("Обычный вывод математических операций");
        System.out.println(5 + " + " + 6 + " = " + SimpleMath.add(5, 6));
        System.out.println(11 + " - " + 6 + " = " + SimpleMath.sub(11, 6));
        System.out.println(5 + " * " + 6 + " = " + SimpleMath.mult(5, 6));
        System.out.println(12 + " / " + 6 + " = " + SimpleMath.div(12, 6));
        System.out.println();

        System.out.println("Декорированный вывод математических операций");
        System.out.println(MathDecorator.add(5, 6));
        System.out.println(MathDecorator.sub(11, 6));
        System.out.println(MathDecorator.mult(5, 6));
        System.out.println(MathDecorator.div(12, 6));
    }
}
