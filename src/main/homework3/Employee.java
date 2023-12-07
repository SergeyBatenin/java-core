package main.homework3;

public abstract class Employee implements Comparable<Employee> {
    protected String name;
    protected String department;
    protected double salaryRate;

    public Employee(String name, String department, double salaryRate) {
        this.name = name;
        this.department = department;
        this.salaryRate = salaryRate;
    }

    public abstract double calculationAvgMonthlySalary();

    @Override
    public int compareTo(Employee o) {
        if (this.calculationAvgMonthlySalary() < o.calculationAvgMonthlySalary()) {
            return 1;
        } else if (this.calculationAvgMonthlySalary() > o.calculationAvgMonthlySalary()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return name + ", работает в отделе: " + department +
                ", и получает зарплату: " + calculationAvgMonthlySalary();
    }
}
