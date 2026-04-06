import java.util.*;

class Student {
    int id;
    String name;
    int age;
    String grade;

    Student(int id, String name, int age, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
}

class Teacher {
    int id;
    String name;
    String subject;

    Teacher(int id, String name, String subject) {
        this.id = id;
        this.name = name;
        this.subject = subject;
    }
}

class SchoolManagement {
    List<Student> students = new ArrayList<>();
    List<Teacher> teachers = new ArrayList<>();

    void addStudent(Student s) {
        students.add(s);
        System.out.println("Student added");
    }

    void displayStudents() {
        for (Student s : students) {
            System.out.println(s.id + " " + s.name + " " + s.age + " " + s.grade);
        }
    }

    void searchStudent(int id) {
        for (Student s : students) {
            if (s.id == id) {
                System.out.println(s.id + " " + s.name + " " + s.age + " " + s.grade);
                return;
            }
        }
        System.out.println("Student not found");
    }

    void addTeacher(Teacher t) {
        teachers.add(t);
        System.out.println("Teacher added");
    }

    void displayTeachers() {
        for (Teacher t : teachers) {
            System.out.println(t.id + " " + t.name + " " + t.subject);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SchoolManagement sm = new SchoolManagement();

        while (true) {
            System.out.println("1.Add Student 2.Display Students 3.Search Student 4.Add Teacher 5.Display Teachers 6.Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Enter id: ");
                int id = sc.nextInt();
                System.out.print("Enter name: ");
                String name = sc.next();
                System.out.print("Enter age: ");
                int age = sc.nextInt();
                System.out.print("Enter grade: ");
                String grade = sc.next();
                sm.addStudent(new Student(id, name, age, grade));
            }
            else if (ch == 2) {
                sm.displayStudents();
            }
            else if (ch == 3) {
                System.out.print("Enter id: ");
                int id = sc.nextInt();
                sm.searchStudent(id);
            }
            else if (ch == 4) {
                System.out.print("Enter id: ");
                int id = sc.nextInt();
                System.out.print("Enter name: ");
                String name = sc.next();
                System.out.print("Enter subject: ");
                String sub = sc.next();
                sm.addTeacher(new Teacher(id, name, sub));
            }
            else if (ch == 5) {
                sm.displayTeachers();
            }
            else {
                break;
            }
        }
    }
}