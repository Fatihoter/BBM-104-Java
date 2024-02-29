public class Worker extends Personnel{

    private double dayOfWorkSalary=20*105;
    private double overWorkSalary;
    private double severancePay;

    public Worker() {

    }

    public double getWorkerTotalSalary() {
        return workerTotalSalary;
    }

    public double setWorkerTotalSalary(double workerTotalSalary) {
        this.workerTotalSalary = workerTotalSalary;
        return workerTotalSalary;
    }

    private double workerTotalSalary;



    public double getDayOfWorkSalary() {
        return dayOfWorkSalary;
    }

    public void setDayOfWorkSalary(double dayOfWorkSalary) {
        this.dayOfWorkSalary = dayOfWorkSalary;
    }

    @Override
    public double getTotalSalary() {
        return totalSalary;
    }

    @Override
    public double setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
        return totalSalary;
    }



    private double totalSalary;




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



    public Worker(String name, String surname, String registrationNumber, String position, int yearOfStart) {
        super(name, surname ,registrationNumber, position, yearOfStart);
    }

    private double calculateOverWorkSalary(Personnel personnel){
        int[] workingHours = personnel.getTotalWorkingHours();
        int totalHours = 0;
        if(workingHours != null){

        for (int hours : workingHours) {
            int extrahours = 0;
            if (hours - 40 > 10) {
                extrahours = 10;
            } else
                extrahours= hours-40;

            totalHours += extrahours;
        }}else {
            System.out.println("w null döndü");
        }

        double overWorkSalary = totalHours * 11;

        return overWorkSalary;

    }
    public double severancePay(Personnel personnel){
        return (2023- personnel.getYearOfStart())*20*0.8;
    }
    public double workerTotalSalary(Personnel i){
        return setWorkerTotalSalary(getDayOfWorkSalary()+severancePay(i)+calculateOverWorkSalary(i));
    }
}
