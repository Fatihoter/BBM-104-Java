public class Chief extends FullTime{


    private double dayOfWorkSalary=20*125;
    private double overWorkSalary;
    private double severancePay;

    public Chief() {
    }

    public double getChiefTotalSalary() {
        return chiefTotalSalary;
    }

    public double setChiefTotalSalary(double chiefTotalSalary) {
        this.chiefTotalSalary = chiefTotalSalary;
        return chiefTotalSalary;
    }

    private double chiefTotalSalary;

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

    public double getDayOfWorkSalary() {
        return dayOfWorkSalary;
    }

    public void setDayOfWorkSalary(double dayOfWorkSalary) {
        this.dayOfWorkSalary = dayOfWorkSalary;
    }

    @Override
    public double getOverWorkSalary() {
        return overWorkSalary;
    }

    @Override
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

    public Chief(String name, String surname, String registrationNumber, String position, int yearOfStart) {
        super(name, surname ,registrationNumber, position, yearOfStart);
    }


    private double calculateOverWorkSalary(Personnel personnel){
        int[] workingHours = personnel.getTotalWorkingHours();
        int totalHours = 0;
        if(workingHours != null){


            for (int hours : workingHours) {
            int extrahours = 0;
            if (hours - 40 > 8) {
                extrahours = 8;
            } else
                extrahours= hours-40;

            totalHours += extrahours;
        }}
        else{
            System.out.println("c null döndü");
        }

        double overWorkSalary = totalHours * 15;

        return overWorkSalary;

    }
    public double severancePay(Personnel personnel){
        return (2023- personnel.getYearOfStart())*20*0.8;
    }
    public double chiefTotalSalary(Personnel i){
        return setChiefTotalSalary(getDayOfWorkSalary()+severancePay(i)+calculateOverWorkSalary(i));
    }
}
