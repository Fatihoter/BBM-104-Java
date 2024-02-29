public class Officer extends Personnel {
    private double baseSalary=2600;
    private double ssBenefits=baseSalary*0.65;
    private double severancePay=getSeverancePay();
    private double overWorkSalary;

    public Officer() {

    }

    public double getOfficerTotalSalary() {
        return officerTotalSalary;
    }

    public double setOfficerTotalSalary(double officerTotalSalary) {
        this.officerTotalSalary = officerTotalSalary;
        return officerTotalSalary;
    }

    private double officerTotalSalary;



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
        }}
        else {
            System.out.println("o null döndü");
        }

        double overWorkSalary = totalHours * 20;

        return overWorkSalary;
    }

    public Officer(String name, String surname, String registrationNumber, String position, int yearOfStart) {
        super(name, surname ,registrationNumber, position, yearOfStart);
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

    public double getOverWorkSalary() {
        return overWorkSalary;
    }

    public void setOverWorkSalary(double overWorkSalary) {
        this.overWorkSalary = overWorkSalary;
    }

    public double severancePay(Personnel personnel){
        return (2023- personnel.getYearOfStart())*20*0.8;
    }

    public double officerTotalSalary(Personnel i){
        return setOfficerTotalSalary(getBaseSalary()+getSsBenefits()+severancePay(i)+calculateOverWorkSalary(i));

    }




}
