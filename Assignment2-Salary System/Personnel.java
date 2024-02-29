public class Personnel {

    private String name;
    private String surname;
    private String registrationNumber;
    private String position;
    private int yearOfStart;

    private int[] totalWorkingHours;

    public int[] getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public Personnel(String name, String surname, String registrationNumber, String position, int yearOfStart) {
        this.name = name;
        this.surname = surname;
        this.registrationNumber = registrationNumber;
        this.position = position;
        this.yearOfStart = yearOfStart;
    }
    public Personnel() {
    }

    private double severancePay;
    private double totalSalary=calculateTotalSalary();



    public void setTotalWorkingHours(int[] totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public double setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
        return totalSalary;
    }
    private double calculateTotalSalary() {
        return totalSalary=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSeverancePay() {
        return severancePay;
    }

    public void setSeverancePay(double severancePay) {
        this.severancePay = severancePay;
    }

    private double calculateSeverancePay(Personnel personnel){
        return (2023-getYearOfStart())*20*0.8 ;
    }




    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getYearOfStart() {
        return yearOfStart;
    }

    public void setYearOfStart(int yearOfStart) {
        this.yearOfStart = yearOfStart;
    }






    @Override
    public String toString() {
        return "Name: " + getName() +
                ", Surname: " + getSurname() +
                ", Registration Number: " + getRegistrationNumber() +
                ", Position: " + getPosition() +
                ", Year of Start: " + getYearOfStart();
    }
}
