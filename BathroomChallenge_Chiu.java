import java.util.*;
public class Main {
   public static void main(String[] args) {
       Student_Chiu s1 = new Student_Chiu();
       Bathroom_Chiu b1 = new Bathroom_Chiu(3);
       Bathroom_Chiu b2 = new Bathroom_Chiu(2);
       List<Student_Chiu> roster = new ArrayList<Student_Chiu>();


       roster.add(new Student_Chiu("James", "Washington", 1));
       roster.add(new Student_Chiu("David", "Monte", 2));
       roster.add(new Student_Chiu("Charlie", "Jackson", 3));
       roster.add(new Student_Chiu("Johnny", "Johnson", 4));
       roster.add(new Student_Chiu("David", "Davidson", 5));
       roster.add(new Student_Chiu("Rachel", "Bobson", 6));
       roster.add(new Student_Chiu("Chuck", "Gary", 7));
       roster.add(new Student_Chiu("John", "Smith", 8));
       roster.add(new Student_Chiu("John", "Doe", 9));
       roster.add(new Student_Chiu("Jane", "Doe", 10));
       roster.add(new Student_Chiu("Janette", "Reed", 11));
       roster.add(new Student_Chiu("Michele", "Mickey", 12));


       b1.addLine(roster.get(0));
       b1.addLine(roster.get(1));
       b1.addLine(roster.get(8));
       b1.addLine(roster.get(7));
       b1.addLine(roster.get(6));


       b2.addLine(roster.get(10));
       b2.addLine(roster.get(9));
       b2.addLine(roster.get(5));
       b2.addLine(roster.get(11));


       System.out.println("Bathroom1: ");
       b1.currentlyIn();
       System.out.println("\nBathroom 2: ");
       b2.currentlyIn();


       System.out.println("\n" + b1.searchStudent(roster.get(0)));
       System.out.println(b1.searchStudent(roster.get(6)));
       System.out.println(b2.searchStudent(roster.get(0)));
       System.out.println(b1.searchStudent(roster.get(4)));


       System.out.println("\n" + b1.leaveBathroom(roster.get(0)));
       System.out.println(b2.leaveBathroom(roster.get(9)));


       System.out.println("\n" + b1.test());
       System.out.println(b2.test());
       b1.clearData();
       System.out.println(b1.test());


   }
}
