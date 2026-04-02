import java.util.*;

class Student {
    int id;
    String name;
    int age;
    String course;

    Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }
}

class StudentManagement {
    List<Student> list = new ArrayList<>();

    void addStudent(Student s) {
        list.add(s);
        System.out.println("Student added");
    }

    void displayStudents() {
        if (list.isEmpty()) {
            System.out.println("No students found");
            return;
        }
        for (Student s : list) {
            System.out.println(s.id + " " + s.name + " " + s.age + " " + s.course);
        }
    }

    void searchStudent(int id) {
        for (Student s : list) {
            if (s.id == id) {
                System.out.println(s.id + " " + s.name + " " + s.age + " " + s.course);
                return;
            }
        }
        System.out.println("Student not found");
    }

    void deleteStudent(int id) {
        Iterator<Student> it = list.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.id == id) {
                it.remove();
                System.out.println("Student deleted");
                return;
            }
        }
        System.out.println("Student not found");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagement sm = new StudentManagement();

        while (true) {
            System.out.println("1.Add 2.Display 3.Search 4.Delete 5.Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter id: ");
                int id = sc.nextInt();
                System.out.print("Enter name: ");
                String name = sc.next();
                System.out.print("Enter age: ");
                int age = sc.nextInt();
                System.out.print("Enter course: ");
                String course = sc.next();
                sm.addStudent(new Student(id, name, age, course));
            }
            else if (choice == 2) {
                sm.displayStudents();
            }
            else if (choice == 3) {
                System.out.print("Enter id: ");
                int id = sc.nextInt();
                sm.searchStudent(id);
            }
            else if (choice == 4) {
                System.out.print("Enter id: ");
                int id = sc.nextInt();
                sm.deleteStudent(id);
            }
            else {
                break;
            }
        }
    }
}