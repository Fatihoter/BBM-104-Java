public class FullTime extends Employee{
    private double dayOfWork;
    private double overWorkSalary;
    private double severancePay;

    public FullTime() {

    }


    public double getDayOfWork() {
        return dayOfWork;
    }

    public void setDayOfWork(double dayOfWork) {
        this.dayOfWork = dayOfWork;
    }

    public double getOverWorkSalary() {
        return overWorkSalary;
    }

    public void setOverWorkSalary(double overWorkSalary) {
        this.overWorkSalary = overWorkSalary;
    }

    @Override
    public double getSeverancePay() {
        return severancePay;
    }

    @Override
    public void setSeverancePay(double severancePay) {
        this.severancePay = severancePay;
    }


    public FullTime(String name, String surname, String registrationNumber, String position, int yearOfStart) {
        super(name, surname ,registrationNumber, position, yearOfStart);


    }



}
