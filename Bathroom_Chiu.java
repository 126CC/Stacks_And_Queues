import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

public class Bathroom_Chiu {

   private int maxIn;

   public Bathroom_Chiu(int maxIn) {
       this.maxIn = maxIn;
   }

   public Bathroom_Chiu() {
       maxIn = 0;
   }

   public void setMaxIn(int maxIn) {
       this.maxIn = maxIn;
   }

   public int getMaxIn() {
       return maxIn;
   }

   LinkedTransferQueue<Student_Chiu> line = new LinkedTransferQueue<>();
   List<Student_Chiu> studentsIn = new ArrayList<>();
   List<Student_Chiu> studentsOut = new ArrayList<>();

   public void clearData() {
       studentsIn.clear();
       studentsOut.clear();
       line.clear();
   }

   public void addLine(Student_Chiu student) {
       if (studentsOut.contains(student)) {
           System.out.println(student.getFirstName() + " has already gone to the bathroom once.");
       } else if (studentsIn.size() < maxIn) {
           studentsIn.add(student);
       } else {
           line.add(student);
       }
   }

   public String leaveBathroom(Student_Chiu student) {
       if(studentsIn.contains(student)) {
           studentsIn.remove(student);
           studentsOut.add(student);
           enterBathroom();
           return student.getFirstName() + " has left bathroom.";
       } else {
           return student.getFirstName() + " is not in the bathroom.";
       }
   }

   public void currentlyIn() {
       for(int i = 0; i < studentsIn.size(); i++) {
           System.out.println(studentsIn.get(i).toString());
       }
   }

   public void enterBathroom() {
       if (studentsIn.size() < maxIn && line != null) {
           studentsIn.add(line.poll());
       }
   }

   public String searchStudent(Student_Chiu student) {
       if (line.contains(student)) {
           return student.getFirstName() + " " + student.getLastName() + " is currently in the line.";
       }
       if (studentsIn.contains(student)) {
           return student.getFirstName() + " " + student.getLastName() + " is in the bathroom.";
       }
       if (studentsOut.contains(student)) {
           return student.getFirstName() + " " + student.getLastName() + " left the bathroom.";
       }
       return student.getFirstName() + " " + student.getLastName() + " not found.";
   }

   public int lengthOfLine() {
       return line.size();
   }

   public String test() {
       return null;
   }
}
