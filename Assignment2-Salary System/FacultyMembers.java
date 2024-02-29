import java.util.ArrayList;

public class FacultyMembers extends Academician{
    private double severancePay=getSeverancePay();


    public double getFacultyMemberTotalSalary() {
        return facultyMemberTotalSalary;
    }

    public double setFacultyMemberTotalSalary(double facultyMemberTotalSalary) {
        this.facultyMemberTotalSalary = facultyMemberTotalSalary;
        return facultyMemberTotalSalary;
    }

    private double facultyMemberTotalSalary;



    public FacultyMembers(String name, String surname, String registrationNumber, String position, int yearOfStart) {
        super(name, surname ,registrationNumber, position, yearOfStart);
    }
    public FacultyMembers() {
    }


    private double totalSalary;

    private double ssBenefits=getBaseSalary()*1.35;


    private double addCourseFee;

    private double calculateAddCourseFee(Personnel personnel) {
        int[] workingHours = personnel.getTotalWorkingHours();
        int totalHours = 0;

        if(workingHours != null){

            for (int hours : workingHours) {
                int extraHours = 0;
                if (hours - 40 > 8) {
                    extraHours = 8;
                } else
                    extraHours= hours-40;

                totalHours += extraHours;
            }
        }else{
            System.out.println("f null döndü");
        }

        return totalHours * 20;
    }

    public double severancePay(Personnel personnel){
        return (2023- personnel.getYearOfStart())*20*0.8;
    }


    public double facultyMemberCalculateSalary(Personnel i) {
        return setFacultyMemberTotalSalary(getBaseSalary()+ssBenefits+severancePay(i)+calculateAddCourseFee(i));

    }




    public double getAddCourseFee() {
        return addCourseFee;
    }

    public void setAddCourseFee(double addCourseFee) {
        this.addCourseFee = addCourseFee;
    }


}
