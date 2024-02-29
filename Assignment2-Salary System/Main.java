import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static ArrayList<Personnel> personnelList = new ArrayList<>();


// this is main part
    public static void main(String[] args) {
        String personnelFile = args[0];
        String monitorringFile = args[1];

        // the personnel.txt file are read below
        ArrayList<Personnel> personnelList = new ArrayList<>();
        try {
            BufferedReader personnelReader = new BufferedReader(new FileReader(personnelFile));
            String personnelLine;
            while ((personnelLine = personnelReader.readLine()) != null) {  //parts of personnel is appointed there.
                String[] personnelData = personnelLine.split("\t");
                String[] nameSurnameParts = personnelData[0].split(" ");
                String name = nameSurnameParts[0];
                String surname = nameSurnameParts[1];
                String registrationNumber = personnelData[1];
                String position = personnelData[2];
                int yearOfStart = Integer.parseInt(personnelData[3]);
                Personnel personnel = createPersonnelObject(name, surname, registrationNumber, position, yearOfStart);
                personnelList.add(personnel);
            }
        } catch (FileNotFoundException e) {  //these are exceptions.
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        readWorkingHours(monitorringFile, personnelList);

        FacultyMembers facultyMembers = new FacultyMembers();       //this is new object create are for workingtimes
        ArrayList<Personnel> facultyMembers1 = new ArrayList<>();
        Chief chief = new Chief();
        ArrayList<Personnel> chief1 = new ArrayList<>();
        Officer officer = new Officer();
        ArrayList<Personnel> officer1 = new ArrayList<>();
        PartTime partTime = new PartTime();
        ArrayList<Personnel> partTime1 = new ArrayList<>();
        ResearchAssistants researchAssistants = new ResearchAssistants();
        ArrayList<Personnel> researchAssistants1 = new ArrayList<>();
        Security security = new Security();
        ArrayList<Personnel> security1 = new ArrayList<>();
        Worker worker = new Worker();
        ArrayList<Personnel> worker1 = new ArrayList<>();



        for (Personnel i : personnelList){
            if (i.getPosition().equals("FACULTY_MEMBER")){
                facultyMembers1.add(i);
            } else if (i.getPosition().equals("WORKER")) {
                worker1.add(i);
            }else if (i.getPosition().equals("CHIEF")) {
                chief1.add(i);
            }else if (i.getPosition().equals("PARTTIME_EMPLOYEE")) {
                partTime1.add(i);
            }else if (i.getPosition().equals("RESEARCH_ASSISTANT")) {
                researchAssistants1.add(i);
            }else if (i.getPosition().equals("OFFICER")) {
                officer1.add(i);
            }else if (i.getPosition().equals("SECURITY")) {
                security1.add(i);
            }
        }

        writeSalaries(personnelList);
    }

    private static void writeSalaries(List<Personnel> personnelList) {       //this part is writing part
        for (Personnel personnel : personnelList) {
            String registrationNumber = personnel.getRegistrationNumber();
            String outputFileName = registrationNumber + ".txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                writer.write("Name : " + personnel.getName());
                writer.newLine();
                writer.newLine();
                writer.write("Surname : " + personnel.getSurname());
                writer.newLine();
                writer.newLine();
                writer.write("Registration Number : " + personnel.getRegistrationNumber());
                writer.newLine();
                writer.newLine();
                writer.write("Position : " + personnel.getPosition());
                writer.newLine();
                writer.newLine();
                writer.write("Year of Start : " + personnel.getYearOfStart());
                writer.newLine();
                writer.newLine();
                writer.write("Total Salary : " + calculateTotalSalary(personnel)+" TL");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static double calculateTotalSalary(Personnel personnel) {    //call the calculate salaries formulas from classes.
        if (personnel instanceof FacultyMembers) {
            return ((FacultyMembers) personnel).facultyMemberCalculateSalary(personnel);
        } else if (personnel instanceof Chief) {
            return ((Chief) personnel).chiefTotalSalary(personnel);
        } else if (personnel instanceof Worker) {
            return ((Worker) personnel).workerTotalSalary(personnel);
        } else if (personnel instanceof Security) {
            return ((Security) personnel).securityTotalSalary(personnel);
        } else if (personnel instanceof ResearchAssistants) {
            return ((ResearchAssistants) personnel).researchAssistantsTotalSalary(personnel);
        } else if (personnel instanceof PartTime) {
            return ((PartTime) personnel).partTimeTotalSalary(personnel);
        } else if (personnel instanceof Officer) {
            return ((Officer) personnel).officerTotalSalary(personnel);
        } else {
            return 0.0;
        }
    }
    private static void readWorkingHours(String monitorringFile, ArrayList<Personnel> personnelList) { //read monitoring.txt.

        try (BufferedReader br = new BufferedReader(new FileReader(monitorringFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                String registrationNumber = parts[0];

                for (Personnel personnel : personnelList) {
                    if (personnel.getRegistrationNumber().equals(registrationNumber)) {
                        int[] workingHours = new int[4];
                        for (int i = 0; i < 4; i++) {
                            workingHours[i] = Integer.parseInt(parts[i + 1]);
                        }
                        personnel.setTotalWorkingHours(workingHours);
                        break;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static Personnel createPersonnelObject(String name, String surname, String registrationNumber, String position, int yearOfStart) {
        switch (position) {
            case "FACULTY_MEMBER":
                return new FacultyMembers(name, surname ,registrationNumber, position, yearOfStart);
            case "WORKER":
                return new Worker(name, surname ,registrationNumber, position, yearOfStart);
            case "SECURITY":
                return new Security(name, surname ,registrationNumber, position, yearOfStart);
            case "OFFICER":
                return new Officer(name, surname ,registrationNumber, position, yearOfStart);
            case "CHIEF":
                return new Chief(name, surname ,registrationNumber, position, yearOfStart);
            case "PARTTIME_EMPLOYEE":
                return new PartTime(name, surname ,registrationNumber, position, yearOfStart);
            case "RESEARCH_ASSISTANT":
                return new ResearchAssistants(name, surname ,registrationNumber, position, yearOfStart);
            default:
                return new Personnel(name, surname ,registrationNumber, position, yearOfStart);
        }
    }
}
