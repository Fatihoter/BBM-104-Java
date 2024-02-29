public class ResearchAssistants extends Academician {

    private double severancePay=getSeverancePay();

    public ResearchAssistants() {

    }


    @Override
    public double getSsBenefits() {
        return ssBenefits;
    }

    @Override
    public void setSsBenefits(double ssBenefits) {
        this.ssBenefits = ssBenefits;
    }

    public double getResearchAssistantsTotalSalary() {
        return researchAssistantsTotalSalary;
    }

    public double setResearchAssistantsTotalSalary(double researchAssistantsTotalSalary) {
        this.researchAssistantsTotalSalary = researchAssistantsTotalSalary;
        return researchAssistantsTotalSalary;
    }

    private double researchAssistantsTotalSalary;

    public ResearchAssistants(String name, String surname, String registrationNumber, String position, int yearOfStart) {
        super(name, surname ,registrationNumber, position, yearOfStart);
    }

    private double ssBenefits=getBaseSalary()*1.05;
    public double severancePay(Personnel personnel){
        return (2023- personnel.getYearOfStart())*20*0.8;
    }
    public double researchAssistantsTotalSalary(Personnel i) {
        return setResearchAssistantsTotalSalary(getBaseSalary()+ssBenefits+severancePay(i));
    }

}
