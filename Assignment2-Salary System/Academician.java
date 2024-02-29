public class Academician extends Personnel{
    private double baseSalary=2600;
    private double ssBenefits;
    private double severancePay= getSeverancePay();









    public Academician(String name, String surname, String registrationNumber, String position, int yearOfStart) {
        super(name, surname ,registrationNumber, position, yearOfStart);
    }
    public Academician() {
    }


    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getSsBenefits() {
        return ssBenefits;
    }

    public void setSsBenefits(double ssBenefits) {
        this.ssBenefits = ssBenefits;
    }

    public double getSeverancePay() {
        return severancePay;
    }

    public void setSeverancePay(double severancePay) {
        this.severancePay = severancePay;
    }

}
