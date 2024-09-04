import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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

class Student extends User {
    public Student(String username, String firstName, String lastName, String password) {
        super(username, firstName, lastName, password);
    }
}

class Teacher extends User {
    public Teacher(String username, String firstName, String lastName, String password) {
        super(username, firstName, lastName, password);
    }
}

class Inspector extends User {
    public Inspector(String username, String firstName, String lastName, String password) {
        super(username, firstName, lastName, password);
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

    private StudentRecord findStudentRecord(String studentUsername) {
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
    private Integer[] homeworkPoints = new Integer[6]; // Точки за до 6 домашни
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
}


public class ApplicationExam {


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        RegistrationService registrationService = new RegistrationService();

        System.out.println("Welcome! Please configure the inspector profile.");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (registrationService.registerInspector(firstName, lastName, username, password)) {
            System.out.print("Was the registration successful? (yes/no): ");
            String confirmation = scanner.nextLine();
            if (!confirmation.equalsIgnoreCase("yes")) {
                System.out.println("Registration marked as unsuccessful.");
            } else {
                System.out.print("Inspector, do you want to add a new discipline? (yes/no): ");
                if (scanner.nextLine().equalsIgnoreCase("yes")) {
                    System.out.print("Enter discipline name: ");
                    String disciplineName = scanner.nextLine();
                    registrationService.registerDiscipline(disciplineName);
                }
            }
        }

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Register Teacher");
            System.out.println("2. Register Student");
            System.out.println("3. Register Discipline");
            System.out.println("4. Update Discipline Name");
            System.out.println("5. Assign Teacher to Discipline");
            System.out.println("6. Assign Student to Discipline");
            System.out.println("7. Add Homework Points");
            System.out.println("8. Add Exam Grade");
            System.out.println("9. Display All Disciplines");
            System.out.println("10. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter teacher first name: ");
                    firstName = scanner.nextLine();
                    System.out.print("Enter teacher last name: ");
                    lastName = scanner.nextLine();
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    if (registrationService.registerTeacher(firstName, lastName, username, password)) {
                        System.out.print("Was the registration successful? (yes/no): ");
                        String confirmation = scanner.nextLine();
                        if (!confirmation.equalsIgnoreCase("yes")) {
                            System.out.println("Registration marked as unsuccessful.");
                        } else {
                            System.out.print("Teacher, do you want to be assigned to a discipline? (yes/no): ");
                            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                                System.out.print("Enter discipline name: ");
                                String disciplineName = scanner.nextLine();
                                registrationService.assignTeacherToDiscipline(disciplineName, username);
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter student first name: ");
                    firstName = scanner.nextLine();
                    System.out.print("Enter student last name: ");
                    lastName = scanner.nextLine();
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    if (registrationService.registerStudent(firstName, lastName, username, password)) {
                        System.out.print("Was the registration successful? (yes/no): ");
                        String confirmation = scanner.nextLine();
                        if (!confirmation.equalsIgnoreCase("yes")) {
                            System.out.println("Registration marked as unsuccessful.");
                        } else {
                            System.out.print("Student, do you want to be assigned to a discipline? (yes/no): ");
                            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                                System.out.print("Enter discipline name: ");
                                String disciplineName = scanner.nextLine();
                                registrationService.assignStudentToDiscipline(disciplineName, username);
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter discipline name: ");
                    String disciplineName = scanner.nextLine();
                    if (registrationService.registerDiscipline(disciplineName)) {
                        System.out.print("Was the registration successful? (yes/no): ");
                        String confirmation = scanner.nextLine();
                        if (!confirmation.equalsIgnoreCase("yes")) {
                            System.out.println("Registration marked as unsuccessful.");
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter the current discipline name: ");
                    String currentName = scanner.nextLine();
                    System.out.print("Enter the new discipline name: ");
                    String newName = scanner.nextLine();
                    registrationService.updateDisciplineName(currentName, newName);
                    break;
                case 5:
                    System.out.print("Enter discipline name: ");
                    disciplineName = scanner.nextLine();
                    System.out.print("Enter student username: ");
                    String studentUsername = scanner.nextLine();
                    registrationService.assignStudentToDiscipline(disciplineName, studentUsername);
                    break;
                case 6:
                    registrationService.displayAllDisciplines();
                    break;
                case 7:
                    System.out.print("Enter discipline name: ");
                    String disciplineName = scanner.nextLine();
                    System.out.print("Enter student username: ");
                    String studentUsername = scanner.nextLine();
                    System.out.print("Enter homework number (1-6): ");
                    int homeworkNumber = scanner.nextInt();
                    System.out.print("Enter points: ");
                    int points = scanner.nextInt();
                    registrationService.addHomeworkPoints(disciplineName, studentUsername, homeworkNumber, points);
                    break;
                case 8:
                    System.out.print("Enter discipline name: ");
                    disciplineName = scanner.nextLine();
                    System.out.print("Enter student username: ");
                    studentUsername = scanner.nextLine();
                    System.out.print("Enter exam grade: ");
                    double grade = scanner.nextDouble();
                    registrationService.addExamGrade(disciplineName, studentUsername, grade);
                    break;
                case 9:
                    registrationService.displayAllDisciplines();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
