import java.util.ArrayList;
import java.util.Collections;

// Assignment 1: Student Marks Record Program
public class StudentMarks {
    public static void main(String[] args) {
        ArrayList<Integer> marks = new ArrayList<>();
        marks.add(78);
        marks.add(92);
        marks.add(65);
        marks.add(88);
        marks.add(54);

        System.out.println("Marks   : " + marks);
        System.out.println("Highest : " + Collections.max(marks));
        System.out.println("Lowest  : " + Collections.min(marks));
    }
}