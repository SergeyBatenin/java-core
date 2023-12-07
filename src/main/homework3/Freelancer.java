package main.homework3;

public class Freelancer extends Employee{

    public Freelancer(String name, String department, double salaryRate) {
        super(name, department, salaryRate);
    }

    @Override
    public double calculationAvgMonthlySalary() {
        return 20.8 * 8 * salaryRate;
    }

}
