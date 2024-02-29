public class PartTime extends Employee{
    private double hourOfWork;
    private double severancePay;

    public PartTime() {
    }

    public double getPartTimeTotalSalary() {
        return partTimeTotalSalary;
    }

    public double setPartTimeTotalSalary(double partTimeTotalSalary) {
        this.partTimeTotalSalary = partTimeTotalSalary;
        return partTimeTotalSalary;
    }

    private double partTimeTotalSalary;


    @Override
    public double getSeverancePay() {
        return severancePay;
    }

    @Override
    public void setSeverancePay(double severancePay) {
        this.severancePay = severancePay;
    }



    public double calculateSalary(Personnel personnel) {
        int[] workingHours = personnel.getTotalWorkingHours();
        int totalHours=0;
        if(workingHours != null){
            for (int hours : workingHours) {
                if (hours < 10) {
                    hours = 0;
                } else if(hours > 20) {
                    hours = 20;
                }
                totalHours += hours;
            }
        }else{
            System.out.println("f null döndü");
        }

        return totalHours * 18;
    }
    public double partTimeTotalSalary(Personnel i){
        return setPartTimeTotalSalary(severancePay(i)+calculateSalary(i));
    }
    public double severancePay(Personnel personnel){
        return (2023- personnel.getYearOfStart())*20*0.8;
    }


    public PartTime(String name, String surname, String registrationNumber, String position, int yearOfStart) {
        super(name, surname ,registrationNumber, position, yearOfStart);
    }


}
