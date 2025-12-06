/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManager;

/**
 *
 * @author Admin
 */
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> list = new ArrayList<>();

    public static void main(String[] args) {

        int choice = -1;

        do {
            try {
                System.out.println("\n====== STUDENT MANAGEMENT ======");
                System.out.println("1. Add student");
                System.out.println("2. Show all students");
                System.out.println("3. Search student (Binary Search)");
                System.out.println("4. Search student (Linear Search)");
                System.out.println("5. Delete student");
                System.out.println("6. Edit student");
                System.out.println("7. Sort (Bubble Sort)");
                System.out.println("8. Sort (Quick Sort)");
                System.out.println("0. Exit");
                System.out.print("Your choice: ");

                choice = Integer.parseInt(sc.nextLine());

            } catch (Exception e) {
                System.out.println("Invalid option! Please enter number only.");
                continue;
            }

            switch (choice) {
                case 1: addStudent(); break;
                case 2: showStudents(); break;
                case 3: binarySearchStudent(); break;
                case 4: linearSearchStudent(); break;
                case 5: deleteStudent(); break;
                case 6: editStudent(); break;
                case 7: bubbleSortScore(); break;
                case 8: quickSortStudents(); break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid option!");
            }

        } while (choice != 0);

    }


    public static void addStudent() {

        try {
            System.out.print("Enter ID: ");
            String id = sc.nextLine();

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            double score = -1;

            while (true) {
                try {
                    System.out.print("Enter score: ");
                    score = Double.parseDouble(sc.nextLine());

                    if (score < 0 || score > 10) {
                        System.out.println("Score must be between 0 and 10!");
                        continue;
                    }
                    break;

                } catch (Exception e) {
                    System.out.println("Invalid input! Score must be a number.");
                }
            }

            list.add(new Student(id, name, score));
            System.out.println("Student added!");

        } catch (Exception e) {
            System.out.println("Error while adding student!");
        }
        }


    public static void showStudents() {
        if (list.isEmpty()) {
            System.out.println("No students yet!");
            return;
        }

        System.out.println("\n===== STUDENT LIST =====");
        for (Student sv : list)
            System.out.println(sv);
    }

    public static void linearSearchStudent() {

        if (list.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        System.out.print("Enter ID to search: ");
        String id = sc.nextLine();

        for (Student sv : list) {
            if (sv.getId().equalsIgnoreCase(id)) {
                System.out.println("Found: " + sv);
                return;
            }
        }

        System.out.println("Student not found!");
    }


    public static void binarySearchStudent() {

        if (list.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        list.sort(Comparator.comparing(Student::getId));

        System.out.print("Enter ID to search: ");
        String id = sc.nextLine();

        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            Student sv = list.get(mid);

            int comp = id.compareToIgnoreCase(sv.getId());

            if (comp == 0) {
                System.out.println("Found: " + sv);
                return;
            }

            if (comp > 0)
                left = mid + 1;
            else
                right = mid - 1;
        }

        System.out.println("Student not found!");
    }


    public static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        String id = sc.nextLine();

        Iterator<Student> it = list.iterator();

        while (it.hasNext()) {
            Student sv = it.next();

            if (sv.getId().equalsIgnoreCase(id)) {
                it.remove();
                System.out.println("Student deleted!");
                return;
            }
        }

        System.out.println("Student not found!");
    }


    public static void editStudent() {

        System.out.print("Enter ID to edit: ");
        String id = sc.nextLine();

        for (Student sv : list) {

            if (sv.getId().equalsIgnoreCase(id)) {

                System.out.println("Editing: " + sv);

                System.out.print("Enter new name (leave empty to keep): ");
                String newName = sc.nextLine();
                if (!newName.isEmpty()) {
                    sv.setName(newName);
                }

                while (true) {
                    try {
                        System.out.print("Enter new score (0–10, empty to keep): ");
                        String str = sc.nextLine();

                        if (str.isEmpty()) break;

                        double newScore = Double.parseDouble(str);

                        if (newScore < 0 || newScore > 10) {
                            System.out.println("Score must be between 0 and 10!");
                            continue;
                        }

                        sv.setScore(newScore);
                        break;

                    } catch (Exception e) {
                        System.out.println("Invalid score! Try again.");
                    }
                }

                System.out.println("Student updated: " + sv);
                return;
            }
        }

        System.out.println("Student not found!");
    }


    public static void bubbleSortScore() {

        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (list.get(j).getScore() < list.get(j + 1).getScore()) {

                    Student temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }

        System.out.println("Sorted by Bubble Sort!");
        showStudents();
    }

    public static void quickSortStudents() {

        if (list.isEmpty()) {
            System.out.println("List empty!");
            return;
        }

        quickSort(0, list.size() - 1);
        System.out.println("Sorted by Quick Sort!");
        showStudents();
    }

    private static void quickSort(int left, int right) {

        if (left >= right) return;

        double pivot = list.get((left + right) / 2).getScore();

        int i = left;
        int j = right;

        while (i <= j) {

            while (list.get(i).getScore() > pivot) i++;
            while (list.get(j).getScore() < pivot) j--;

            if (i <= j) {
                Collections.swap(list, i, j);
                i++;
                j--;
            }
        }

        if (left < j) quickSort(left, j);
        if (i < right) quickSort(i, right);
    }

}
       