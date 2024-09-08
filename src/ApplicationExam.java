import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;


public class ApplicationExam {

    private static final Scanner scanner = new Scanner(System.in);
    private static final RegistrationService registrationService = new RegistrationService();

    public static void main(String[] args) {

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleInspectorRegistration();
                    break;
                case 2:
                    handleTeacherRegistration();
                    break;
                case 3:
                    handleStudentRegistration();
                    break;
                case 4:
                    handleDisciplineRegistration();
                    break;
                case 5:
                    handleUpdateDiscipline();
                    break;
                case 6:
                    handleAssignTeacherToDiscipline();
                    break;
                case 7:
                    handleAssignStudentToDiscipline();
                    break;
                case 8:
                    handleAddHomeworkPoints();
                    break;
                case 9:
                    handleAddExamGrade();
                    break;
                case 10:
                    registrationService.displayAllDisciplines();
                    break;
                case 11:
                    registrationService.getAverageGradeForStudents();
                    break;
                case 12:
                    registrationService.getTop10StudentsByAverageGrade(true);
                    break;
                case 13:
                    registrationService.getTop10StudentsByAverageGrade(false);
                    break;
                case 14:
                    System.out.print("Enter discipline name: ");
                    String disciplineName = scanner.nextLine();
                    registrationService.getAverageGradeForDiscipline(disciplineName);
                    break;
                case 15:
                    System.out.print("Enter discipline name: ");
                    disciplineName = scanner.nextLine();
                    registrationService.getStudentCountByGradeRanges(disciplineName);
                    break;
                case 16:
                    System.out.print("Enter student username: ");
                    String studentUsername = scanner.nextLine();
                    registrationService.getStudentPerformance(studentUsername);
                    break;
                case 17:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Register Inspector");
        System.out.println("2. Register Teacher");
        System.out.println("3. Register Student");
        System.out.println("4. Register Discipline");
        System.out.println("5. Update Discipline Name");
        System.out.println("6. Assign Teacher to Discipline");
        System.out.println("7. Assign Student to Discipline");
        System.out.println("8. Add Homework Points");
        System.out.println("9. Add Exam Grade");
        System.out.println("10. Display All Disciplines");
        System.out.println("8. Get average grade for all students");
        System.out.println("9. Get top 10 students by highest average grade");
        System.out.println("10. Get top 10 students by lowest average grade");
        System.out.println("13. Get average grade for specific discipline");
        System.out.println("14. Get student count by grade ranges");
        System.out.println("15. Get detailed performance for a student");
        System.out.println("11. Exit");
        System.out.println();
    }

    private static void handleInspectorRegistration() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        registrationService.registerInspector(firstName, lastName, username, password);
    }

    private static void handleTeacherRegistration() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        registrationService.registerTeacher(firstName, lastName, username, password);
    }

    private static void handleStudentRegistration() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        registrationService.registerStudent(firstName, lastName, username, password);
    }

    private static void handleDisciplineRegistration() {
        System.out.print("Enter discipline name: ");
        String disciplineName = scanner.nextLine();
        registrationService.registerDiscipline(disciplineName);
    }

    private static void handleUpdateDiscipline() {
        System.out.print("Enter current discipline name: ");
        String currentDisciplineName = scanner.nextLine();
        System.out.print("Enter new discipline name: ");
        String newDisciplineName = scanner.nextLine();
        registrationService.updateDisciplineName(currentDisciplineName, newDisciplineName);
    }

    private static void handleAssignTeacherToDiscipline() {
        System.out.print("Enter discipline name: ");
        String disciplineName = scanner.nextLine();
        System.out.print("Enter teacher username: ");
        String teacherUsername = scanner.nextLine();
        registrationService.assignTeacherToDiscipline(disciplineName, teacherUsername);
    }

    private static void handleAssignStudentToDiscipline() {
        System.out.print("Enter discipline name: ");
        String disciplineName = scanner.nextLine();
        System.out.print("Enter student username: ");
        String studentUsername = scanner.nextLine();
        registrationService.assignStudentToDiscipline(disciplineName, studentUsername);
    }

    private static void handleAddHomeworkPoints() {
        System.out.print("Enter discipline name: ");
        String disciplineName = scanner.nextLine();
        System.out.print("Enter student username: ");
        String studentUsername = scanner.nextLine();
        System.out.print("Enter homework number (1-6): ");
        int homeworkNumber = scanner.nextInt();
        System.out.print("Enter points: ");
        int points = scanner.nextInt();
        scanner.nextLine();
        registrationService.addHomeworkPoints(disciplineName, studentUsername, homeworkNumber, points);
    }

    private static void handleAddExamGrade() {
        System.out.print("Enter discipline name: ");
        String disciplineName = scanner.nextLine();
        System.out.print("Enter student username: ");
        String studentUsername = scanner.nextLine();
        System.out.print("Enter exam grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine();
        registrationService.addExamGrade(disciplineName, studentUsername, grade);
    }
}

class User {
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String password;

    public User(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return String.format("%s %s (Username: %s)", firstName, lastName, username);
    }
}

class Inspector extends User {
    public Inspector(String username, String firstName, String lastName, String password) {
        super(username, firstName, lastName, password);
    }
}
class Teacher extends User {
    public Teacher(String username, String firstName, String lastName, String password) {
        super(username, firstName, lastName, password);
    }
}

class Student extends User {
    public Student(String username, String firstName, String lastName, String password) {
        super(username, firstName, lastName, password);
    }
}

class RegistrationService {


    private List<User> users = new ArrayList<>();
    private List<Discipline> disciplines = new ArrayList<>();

    public boolean registerInspector(String firstName, String lastName, String username, String password) {
        if (users.stream().anyMatch(u -> u.getUsername().equals(username))) {
            System.out.println("Username already exists. Please choose another username.");
            return false;
        } else {
            users.add(new Inspector(username, firstName, lastName, password));
            System.out.println("Inspector registered successfully.");
            return true;
        }
    }

    public boolean registerTeacher(String firstName, String lastName, String username, String password) {
        if (users.stream().anyMatch(u -> u.getUsername().equals(username))) {
            System.out.println("Username already exists. Please choose another username.");
            return false;
        } else {
            users.add(new Teacher(username, firstName, lastName, password));
            System.out.println("Teacher registered successfully.");
            return true;
        }
    }

    public boolean registerStudent(String firstName, String lastName, String username, String password) {
        if (users.stream().anyMatch(u -> u.getUsername().equals(username))) {
            System.out.println("Username already exists. Please choose another username.");
            return false;
        } else {
            users.add(new Student(username, firstName, lastName, password));
            System.out.println("Student registered successfully.");
            return true;
        }
    }

    public boolean registerDiscipline(String name) {
        if (disciplines.stream().anyMatch(d -> d.getName().equals(name))) {
            System.out.println("Discipline already exists. Please choose another name.");
            return false;
        } else {
            disciplines.add(new Discipline(name));
            System.out.println("Discipline registered successfully.");
            return true;
        }
    }

    public void assignTeacherToDiscipline(String disciplineName, String teacherUsername) {
        Discipline discipline = disciplines.stream()
                .filter(d -> d.getName().equals(disciplineName))
                .findFirst()
                .orElse(null);

        if (discipline == null) {
            System.out.println("Discipline not found.");
            return;
        }

        Teacher teacher = (Teacher) users.stream()
                .filter(u -> u instanceof Teacher && u.getUsername().equals(teacherUsername))
                .findFirst()
                .orElse(null);

        if (teacher == null) {
            System.out.println("Teacher not found.");
            return;
        }

        discipline.addTeacher(teacher);
        System.out.println("Teacher assigned to discipline successfully.");
    }

    public void assignStudentToDiscipline(String disciplineName, String studentUsername) {
        Discipline discipline = disciplines.stream()
                .filter(d -> d.getName().equals(disciplineName))
                .findFirst()
                .orElse(null);

        if (discipline == null) {
            System.out.println("Discipline not found.");
            return;
        }

        Student student = (Student) users.stream()
                .filter(u -> u instanceof Student && u.getUsername().equals(studentUsername))
                .findFirst()
                .orElse(null);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        discipline.addStudent(student);
        System.out.println("Student assigned to discipline successfully.");
    }

    public void displayAllDisciplines() {
        for (Discipline discipline : disciplines) {
            discipline.displayDetails();
        }
    }

    public boolean updateDisciplineName(String currentName, String newName) {
        Discipline discipline = disciplines.stream()
                .filter(d -> d.getName().equals(currentName))
                .findFirst()
                .orElse(null);

        if (discipline == null) {
            System.out.println("Discipline not found.");
            return false;
        } else if (disciplines.stream().anyMatch(d -> d.getName().equals(newName))) {
            System.out.println("A discipline with the new name already exists.");
            return false;
        } else {
            discipline.setName(newName);
            System.out.println("Discipline name updated successfully.");
            return true;
        }
    }

    public void addHomeworkPoints(String disciplineName, String studentUsername, int homeworkNumber, int points) {
        Discipline discipline = findDiscipline(disciplineName);
        if (discipline != null) {
            discipline.addHomeworkPoints(studentUsername, homeworkNumber, points);
        } else {
            System.out.println("Discipline not found.");
        }
    }

    public void addExamGrade(String disciplineName, String studentUsername, double grade) {
        Discipline discipline = findDiscipline(disciplineName);
        if (discipline != null) {
            discipline.addExamGrade(studentUsername, grade);
        } else {
            System.out.println("Discipline not found.");
        }
    }

    private Discipline findDiscipline(String disciplineName) {
        return disciplines.stream()
                .filter(d -> d.getName().equals(disciplineName))
                .findFirst()
                .orElse(null);
    }

    public void getAverageGradeForStudents() {
        double totalGrades = 0;
        int totalStudents = 0;

        for (Discipline discipline : disciplines) {
            for (Student student : discipline.getStudents()) {
                StudentRecord record = discipline.findStudentRecord(student.getUsername());
                if (record != null && record.getExamGrade() != null) {
                    totalGrades += record.getExamGrade();
                    totalStudents++;
                }
            }
        }

    }
    public void getTop10StudentsByAverageGrade(boolean highest) {
        List<StudentRecord> allRecords = new ArrayList<>();

        for (Discipline discipline : disciplines) {
            for (Student student : discipline.getStudents()) {
                StudentRecord record = discipline.findStudentRecord(student.getUsername());
                if (record != null && record.getExamGrade() != null) {
                    allRecords.add(record);
                }
            }
        }

        allRecords.sort((record1, record2) -> {
            if (highest) {
                return Double.compare(record2.getExamGrade(), record1.getExamGrade());
            } else {
                return Double.compare(record1.getExamGrade(), record2.getExamGrade());
            }
        });

        System.out.println((highest ? "Top 10 highest" : "Top 10 lowest") + " students:");
        for (int i = 0; i < Math.min(10, allRecords.size()); i++) {
            StudentRecord record = allRecords.get(i);
            System.out.println(record.getStudentUsername() + " - " + record.getExamGrade());
        }
    }
    public void getAverageGradeForDiscipline(String disciplineName) {
        Discipline discipline = findDiscipline(disciplineName);
        if (discipline != null) {
            double totalGrades = 0;
            int count = 0;

            for (Student student : discipline.getStudents()) {
                StudentRecord record = discipline.findStudentRecord(student.getUsername());
                if (record != null && record.getExamGrade() != null) {
                    totalGrades += record.getExamGrade();
                    count++;
                }
            }

            if (count > 0) {
                double average = totalGrades / count;
                System.out.println("Average grade for discipline " + disciplineName + ": " + average);
            } else {
                System.out.println("No exam grades available for this discipline.");
            }
        } else {
            System.out.println("Discipline not found.");
        }
    }

    public void getStudentCountByGradeRanges(String disciplineName) {
        Discipline discipline = findDiscipline(disciplineName);
        if (discipline != null) {
            int[] ranges = new int[6];

            for (Student student : discipline.getStudents()) {
                StudentRecord record = discipline.findStudentRecord(student.getUsername());
                if (record != null && record.getExamGrade() != null) {
                    double grade = record.getExamGrade();
                    if (grade >= 0 && grade <= 2) ranges[0]++;
                    else if (grade > 2 && grade <= 4) ranges[1]++;
                    else if (grade > 4 && grade <= 6) ranges[2]++;
                    else if (grade > 6 && grade <= 8) ranges[3]++;
                    else if (grade > 8 && grade <= 10) ranges[4]++;
                }
            }

            System.out.println("Grade ranges for discipline: " + disciplineName);
            System.out.println("0-2: " + ranges[0]);
            System.out.println("3-4: " + ranges[1]);
            System.out.println("5-6: " + ranges[2]);
            System.out.println("7-8: " + ranges[3]);
            System.out.println("9-10: " + ranges[4]);
        } else {
            System.out.println("Discipline not found.");
        }
    }
    public void getStudentPerformance(String studentUsername) {
        for (Discipline discipline : disciplines) {
            StudentRecord record = discipline.findStudentRecord(studentUsername);
            if (record != null) {
                System.out.println("Discipline: " + discipline.getName());
                System.out.println("Homework Points: " + Arrays.toString(record.getHomeworkPoints()));
                System.out.println("Exam Grade: " + record.getExamGrade());
            }
        }
    }

}

class Discipline {
    private String name;
    private List<Teacher> teachers = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<StudentRecord> studentRecords = new ArrayList<>();

    public Discipline(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTeacher(Teacher teacher) {
        if (teachers.size() < 3) {
            teachers.add(teacher);
        } else {
            System.out.println("Cannot add more than 3 teachers to a discipline.");
        }
    }

    public void addStudent(Student student) {
        if (students.size() < 30) {
            students.add(student);
        } else {
            System.out.println("Cannot add more than 30 students to a discipline.");
        }
    }

    public void addHomeworkPoints(String studentUsername, int homeworkNumber, int points) {
        if (points < 0 || points > 100) {
            System.out.println("Invalid points. Points must be between 0 and 100.");
            return;
        }

        StudentRecord record = findStudentRecord(studentUsername);
        if (record != null) {
            record.setHomeworkPoints(homeworkNumber, points);
            System.out.println("Homework points added successfully.");
        } else {
            System.out.println("Student not found in this discipline.");
        }
    }

    public void addExamGrade(String studentUsername, double grade) {
        StudentRecord record = findStudentRecord(studentUsername);
        if (record != null) {
            record.setExamGrade(grade);
            System.out.println("Exam grade added successfully.");
        } else {
            System.out.println("Student not found in this discipline.");
        }
    }

    public StudentRecord findStudentRecord(String studentUsername) {
        return studentRecords.stream()
                .filter(record -> record.getStudentUsername().equals(studentUsername))
                .findFirst()
                .orElse(null);
    }

    public void displayDetails() {
        System.out.println("Discipline: " + name);
        System.out.println("Teachers:");
        for (Teacher teacher : teachers) {
            System.out.println(" - " + teacher);
        }
        System.out.println("Students:");
        for (Student student : students) {
            System.out.println(" - " + student);
            StudentRecord record = findStudentRecord(student.getUsername());
            if (record != null) {
                System.out.print("   Homework Points: ");
                Integer[] points = record.getHomeworkPoints();
                for (int i = 0; i < points.length; i++) {
                    System.out.print("HW" + (i + 1) + ": "
                            + (points[i] != null ? points[i] : "N/A") + " ");
                }
                System.out.println();
                System.out.println("   Exam Grade: "
                        + (record.getExamGrade() != null ? record.getExamGrade() : "Not graded yet"));
            }
        }
    }
}

class StudentRecord {
    private String studentUsername;
    private Integer[] homeworkPoints = new Integer[6];
    private Double examGrade;

    public StudentRecord(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setHomeworkPoints(int homeworkNumber, int points) {
        this.homeworkPoints[homeworkNumber - 1] = points;
    }

    public Integer[] getHomeworkPoints() {
        return homeworkPoints;
    }

    public void setExamGrade(double grade) {
        this.examGrade = grade;
    }

    public Double getExamGrade() {
        return examGrade;
    }
}
