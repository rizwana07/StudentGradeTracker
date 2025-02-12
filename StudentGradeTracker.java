import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    static class Student {
        String name;
        ArrayList<Integer> grades;

        Student(String name) {
            this.name = name;
            this.grades = new ArrayList<>();
        }

        void addGrade(int grade) {
            grades.add(grade);
        }

        double getAverageGrade() {
            int sum = 0;
            for (int grade : grades) {
                sum += grade;
            }
            return (grades.size() > 0) ? (double) sum / grades.size() : 0;
        }

        int getHighestGrade() {
            int highest = Integer.MIN_VALUE;
            for (int grade : grades) {
                if (grade > highest) {
                    highest = grade;
                }
            }
            return highest;
        }

        int getLowestGrade() {
            int lowest = Integer.MAX_VALUE;
            for (int grade : grades) {
                if (grade < lowest) {
                    lowest = grade;
                }
            }
            return lowest;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Student Grade Tracker ===");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade for Student");
            System.out.println("3. View Student Grades");
            System.out.println("4. View Average, Highest, and Lowest Scores");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner, students);
                    break;
                case 2:
                    addGrade(scanner, students);
                    break;
                case 3:
                    viewStudentGrades(students);
                    break;
                case 4:
                    viewStatistics(students);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Add a new student
    static void addStudent(Scanner scanner, ArrayList<Student> students) {
        System.out.print("Enter student's name: ");
        scanner.nextLine(); // Consume newline character
        String name = scanner.nextLine();
        students.add(new Student(name));
        System.out.println("Student " + name + " added.");
    }

    // Add grade for a student
    static void addGrade(Scanner scanner, ArrayList<Student> students) {
        System.out.print("Enter student's name: ");
        scanner.nextLine(); // Consume newline character
        String name = scanner.nextLine();

        Student student = findStudentByName(name, students);
        if (student != null) {
            System.out.print("Enter grade: ");
            int grade = scanner.nextInt();
            student.addGrade(grade);
            System.out.println("Grade " + grade + " added for " + name + ".");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Find student by name
    static Student findStudentByName(String name, ArrayList<Student> students) {
        for (Student student : students) {
            if (student.name.equals(name)) {
                return student;
            }
        }
        return null;
    }

    // View a student's grades
    static void viewStudentGrades(ArrayList<Student> students) {
        System.out.print("Enter student's name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        Student student = findStudentByName(name, students);
        if (student != null) {
            System.out.println(name + "'s Grades: " + student.grades);
        } else {
            System.out.println("Student not found.");
        }
    }

    // View average, highest, and lowest scores of all students
    static void viewStatistics(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        double totalAverage = 0;
        int highestScore = Integer.MIN_VALUE;
        int lowestScore = Integer.MAX_VALUE;
        int totalGrades = 0;

        for (Student student : students) {
            totalAverage += student.getAverageGrade();
            highestScore = Math.max(highestScore, student.getHighestGrade());
            lowestScore = Math.min(lowestScore, student.getLowestGrade());
            totalGrades += student.grades.size();
        }

        totalAverage /= students.size();

        System.out.println("\n=== Overall Statistics ===");
        System.out.println("Average score of all students: " + totalAverage);
        System.out.println("Highest score: " + highestScore);
        System.out.println("Lowest score: " + lowestScore);
    }
}