package main;

import java.util.ArrayList;
import java.util.Scanner;

class Course {
    private String name;
    private String id;
    private int maxStudents;

    public Course(String name, String id, int maxStudents) {
        this.name = name;
        this.id = id;
        this.maxStudents = maxStudents;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getMaxStudents() {
        return maxStudents;
    }
}

class Student {
    private String name;
    private String studentNumber;

    public Student(String name, String studentNumber) {
        this.name = name;
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
}

class Enrollment {
    private Student student;
    private Course course;
    private int grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = -1; // Default grade
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

public class App {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;
    private ArrayList<Enrollment> enrollments;
    private String universityName;

    public App(String universityName) {
        this.universityName = universityName;
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.enrollments = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudentToCourse(int studentIndex, int courseIndex) {
        Student student = students.get(studentIndex);
        Course course = courses.get(courseIndex);
        Enrollment enrollment = new Enrollment(student, course);
        enrollments.add(enrollment);
    }

    public void giveCourseGrades(int courseIndex, Scanner scanner) {
        Course course = courses.get(courseIndex);
        System.out.println("Anna arvosanat kurssille " + course.getName() + ":");
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourse().equals(course)) {
                System.out.print("Anna arvosana opiskelijalle " + enrollment.getStudent().getName() + ": ");
                int grade = scanner.nextInt();
                enrollment.setGrade(grade);
            }
        }
    }

    public void listStudentsInCourse(int courseIndex) {
        Course course = courses.get(courseIndex);
        System.out.println("Opiskelijat kurssilla " + course.getName() + ":");
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourse().equals(course)) {
                System.out.println(enrollment.getStudent().getName());
            }
        }
    }

    public void listStudentGrades(int studentIndex) {
        Student student = students.get(studentIndex);
        System.out.println("Opiskelijan " + student.getName() + " arvosanat:");
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().equals(student)) {
                System.out.println(enrollment.getCourse().getName() + ", arvosana: " + enrollment.getGrade());
            }
        }
    }

    public void listAllGrades() {
        System.out.println("Kaikkien kurssien kaikkien opiskelijoiden arvosanat:");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment.getCourse().getName() + ", " + enrollment.getStudent().getName() + ", arvosana: " + enrollment.getGrade());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tervetuloa Gifu-järjestelmään");
        System.out.println("Anna yliopiston nimi:");
        String universityName = scanner.nextLine();
        App gifu = new App(universityName);

        while (true) {
            System.out.println("1) Luo uusi kurssi, 2) Luo uusi opiskelija, 3) Listaa kurssit, 4) Listaa opiskelijat, 5) Lisää opiskelija kurssille, 6) Anna kurssiarvosanat, 7) Listaa kurssilla olevat opiskelijat, 8) Listaa opiskelijan arvosanat, 9) Listaa kaikkien kurssien kaikkien opiskelijoiden arvosanat, 0) Lopeta ohjelma");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Anna kurssin nimi:");
                    String courseName = scanner.nextLine();
                    System.out.println("Anna kurssin ID:");
                    String courseId = scanner.nextLine();
                    System.out.println("Anna kurssin maksimi opiskelijamäärä:");
                    int maxStudents = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Course course = new Course(courseName, courseId, maxStudents);
                    gifu.addCourse(course);
                    break;
                case 2:
                    System.out.println("Anna opiskelijan nimi:");
                    String studentName = scanner.nextLine();
                    System.out.println("Anna opiskelijan opiskelijanumero:");
                    String studentNumber = scanner.nextLine();
                    Student student = new Student(studentName, studentNumber);
                    gifu.addStudent(student);
                    break;
                case 3:
                    System.out.println("Kurssit:");
                    ArrayList<Course> courses = gifu.getCourses();
                    for (int i = 0; i < courses.size(); i++) {
                        System.out.println(i + ") " + courses.get(i).getId() + " " + courses.get(i).getName());
                    }
                    break;
                case 4:
                    System.out.println("Opiskelijat:");
                    ArrayList<Student> students = gifu.getStudents();
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println(i + ") " + students.get(i).getStudentNumber() + " " + students.get(i).getName());
                    }
                    break;
                case 5:
                    System.out.println("Mille kurssille haluat lisätä opiskelijan? Syötä kurssin numero:");
                    int courseIndex = scanner.nextInt();
                    System.out.println("Minkä opiskelijan haluat lisätä kurssille? Syötä opiskelijan numero:");
                    int studentIndex = scanner.nextInt();
                    gifu.addStudentToCourse(studentIndex, courseIndex);
                    break;
                case 6:
                    System.out.println("Kurssit:");
                    ArrayList<Course> coursesForGrades = gifu.getCourses();
                    for (int i = 0; i < coursesForGrades.size(); i++) {
                        System.out.println(i + ") " + coursesForGrades.get(i).getId() + " " + coursesForGrades.get(i).getName());
                    }
                    System.out.println("Minkä kurssin haluat arvostella? Syötä kurssin numero:");
                    int courseGradeIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    gifu.giveCourseGrades(courseGradeIndex, scanner);
                    break;
                case 7:
                    System.out.println("Minkä kurssin opiskelijat haluat listata? Syötä kurssin numero:");
                    int courseStudentsIndex = scanner.nextInt();
                    gifu.listStudentsInCourse(courseStudentsIndex);
                    break;
                case 8:
                    System.out.println("Minkä opiskelijan arvosanat haluat listata? Syötä opiskelijan numero:");
                    int studentGradesIndex = scanner.nextInt();
                    gifu.listStudentGrades(studentGradesIndex);
                    break;
                case 9:
                    gifu.listAllGrades();
                    break;
                case 0:
                    System.out.println("Kiitos ohjelman käytöstä.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Virheellinen valinta. Yritä uudelleen.");
            }
        }
    }
}