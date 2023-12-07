package main.homework3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        1. Построить три класса (базовый и 2 потомка), описывающих некоторых работников с почасовой оплатой
            (один из потомков - Freelancer) и фиксированной оплатой (второй потомок -Worker).
        а) Описать в базовом классе абстрактный метод для расчёта среднемесячной заработной платы.
        Для «повременщиков» формула для расчета такова: «среднемесячная заработная плата = 20.8 * 8 * почасовая ставка»,
             для работников с фиксированной оплатой «среднемесячная заработная плата = фиксированная месячная оплата».
        б) Создать на базе абстрактного класса массив/коллекцию сотрудников и заполнить его.
        в) ** Реализовать интерфейсы для возможности сортировки массива/коллекции
            (обратите ваше внимание на интерфейсы Comparator, Comparable), добавьте новое состояние на урове базового типа и создайте свой уникальный компаратор.
        г) ** Создать класс, содержащий массив или коллекцию сотрудников
            (как Worker так и Freelancer), и реализовать возможность вывода данных с использованием foreach
            (подсказка: вам потребуется поработать с интерфейсом Iterable).
         */

        // Создаем список работников
        List<Employee> employees = new ArrayList<>();
        employees.add(new Worker("Sasha", "Marketing", 30000));
        employees.add(new Worker("Katya", "HR", 25000));
        employees.add(new Worker("Pasha", "Logistic", 28000.128));
        employees.add(new Freelancer("Dasha", "Courier", 124));
        employees.add(new Freelancer("Elena", "Copywriter", 168.27));
        employees.add(new Freelancer("Petya", "IT", 300000));
        // Петя у нас мифический айтишник с зарплатой 300к в наносек :)


        System.out.println("До сортировки");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        System.out.println();


        System.out.println("Сортировка по имени через Comparator");
        employees.sort(new Comparator<Employee>() { // Простой вариант сортировки
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        System.out.println();

        System.out.println("Сортировка по зарплате через реализацию интерфейса Comparable, с выводом через итератор");
        employees.sort(Employee::compareTo);
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }
        Iterator<Employee> iter = employees.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
