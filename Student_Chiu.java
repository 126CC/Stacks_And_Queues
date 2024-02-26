public class Student_Chiu {
   private int studentID;
   private String firstName;
   private String lastName;

   public Student_Chiu() {
       lastName = null;
       firstName = null;
       studentID = 0;
   }
  
   public int getStudentID() {
       return studentID;
   }

   public String getFirstName() {
       return firstName;
   }
  
   public String getLastName() {
       return lastName;
   }

   public Student_Chiu(String firstName, String lastName, int studentID) {
       this.studentID = studentID;
       this.firstName = firstName;
       this.lastName = lastName;
   }

   public boolean checkIfSame(Student_Chiu s2) {
       return getStudentID() == s2.getStudentID();
   }

   public String toString() {
       return "First: " + getFirstName() + "   Last: " + getLastName() + "   ID: " + getStudentID();
   }
}

