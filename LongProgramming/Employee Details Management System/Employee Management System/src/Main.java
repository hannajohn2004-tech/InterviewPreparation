import java.util.*;

class Employee {
    int id;
    String name;
    double salary;
    String department;

    Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
}
class EmployeeManagement {
    List<Employee> list = new ArrayList<>();

    void addEmployee(Employee e) {
        list.add(e);
        System.out.println("Employee added");
    }

    void displayEmployees() {
        if (list.isEmpty()) {
            System.out.println("No employees found");
            return;
        }
        for (Employee e : list) {
            System.out.println(e.id + " " + e.name + " " + e.salary + " " + e.department);
        }
    }

    void searchEmployee(int id) {
        for (Employee e : list) {
            if (e.id == id) {
                System.out.println(e.id + " " + e.name + " " + e.salary + " " + e.department);
                return;
            }
        }
        System.out.println("Employee not found");
    }

    void deleteEmployee(int id) {
        Iterator<Employee> it = list.iterator();
        while (it.hasNext()) {
            Employee e = it.next();
            if (e.id == id) {
                it.remove();
                System.out.println("Employee deleted");
                return;
            }
        }
        System.out.println("Employee not found");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeManagement em = new EmployeeManagement();

        while (true) {
            System.out.println("1.Add 2.Display 3.Search 4.Delete 5.Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter id: ");
                int id = sc.nextInt();
                System.out.print("Enter name: ");
                String name = sc.next();
                System.out.print("Enter salary: ");
                double salary = sc.nextDouble();
                System.out.print("Enter department: ");
                String dept = sc.next();
                em.addEmployee(new Employee(id, name, salary, dept));
            }
            else if (choice == 2) {
                em.displayEmployees();
            }
            else if (choice == 3) {
                System.out.print("Enter id: ");
                int id = sc.nextInt();
                em.searchEmployee(id);
            }
            else if (choice == 4) {
                System.out.print("Enter id: ");
                int id = sc.nextInt();
                em.deleteEmployee(id);
            }
            else {
                break;
            }
        }
    }
}
