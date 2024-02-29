public class Security extends Personnel {
    private double hourOfWork;
    private double transMoney=24*5;
    private double severancePay;
    private double foodMoney=24*10;

    public Security() {

    }

    public double getSecurityTotalSalary() {
        return securityTotalSalary;
    }

    public double setSecurityTotalSalary(double securityTotalSalary) {
        this.securityTotalSalary = securityTotalSalary;
        return securityTotalSalary;
    }

    private double securityTotalSalary;

    public double getHoursPayment() {
        return hoursPayment;
    }

    public void setHoursPayment(double hoursPayment) {
        this.hoursPayment = hoursPayment;
    }

    private double hoursPayment;



    private double calculateHoursPayment(Personnel personnel) {
        int[] workingHours = personnel.getTotalWorkingHours();
        int totalHours = 0;
        if(workingHours != null){

        for (int hours : workingHours) {
            if (hours > 54) {
                hours = 54;
            } else if (hours < 30) {
                hours=30;
            }

            totalHours += hours;
        }}else{
            System.out.println("s null döndü");
        }

        return totalHours * 10;
    }


    public double getHourOfWork() {
        return hourOfWork;
    }

    public void setHourOfWork(double hourOfWork) {
        this.hourOfWork = hourOfWork;
    }

    public double getTransMoney() {
        return transMoney;
    }

    public void setTransMoney(double transMoney) {
        this.transMoney = transMoney;
    }

    @Override
    public double getSeverancePay() {
        return severancePay;
    }

    @Override
    public void setSeverancePay(double severancePay) {
        this.severancePay = severancePay;
    }

    public double getFoodMoney() {
        return foodMoney;
    }

    public void setFoodMoney(double foodMoney) {
        this.foodMoney = foodMoney;
    }



    public Security(String name, String surname, String registrationNumber, String position, int yearOfStart) {
        super(name, surname, registrationNumber, position, yearOfStart);
    }
    public double severancePay(Personnel personnel){
        return (2023- personnel.getYearOfStart())*20*0.8;
    }

    public double securityTotalSalary(Personnel i){
        return setSecurityTotalSalary(severancePay(i)+calculateHoursPayment(i)+foodMoney+transMoney);

    }

}