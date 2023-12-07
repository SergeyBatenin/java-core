package main.homework3;

public class Worker extends Employee{

    public Worker(String name, String department, double salaryRate) {
        super(name, department, salaryRate);
    }

    @Override
    public double calculationAvgMonthlySalary() {
        return salaryRate;
    }
}
