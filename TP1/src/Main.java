import java.util.*;
import java.util.List;
public class Main {
    public static void main(String args[]) {
        /*
        Scanner sc = new Scanner(System.in);
        System.out.print("accountNumber:");
        int accountNumber = sc.nextInt();
        sc.nextLine();
        System.out.print("accountHolderName:");
        String accountHolderName = sc.nextLine();
        System.out.print("balance:");
        int balance = sc.nextInt();

        BankAccount b = new BankAccount(accountNumber,accountHolderName,balance);
        System.out.println(b.getBalance());
        System.out.println(b.getAccountNumber());
        System.out.println(b.getAccountHolderName());

        b.deposit(200);
        System.out.println(b.getBalance());
        b.withdraw(260);
        System.out.println(b.getBalance());*/

        /*
        Instructor ins1=new Instructor(1234,"Ahmed","Bouzid");
        Instructor ins2=new Instructor(7894,"Amir","Mallek");
        Course BD=new Course(102,"Database",ins1);
        Course Proba=new Course(103,"Probabilities",ins2);
        Student s=new Student(555,"Mouhib","Bahri");
        s.enroll(BD);
        s.enroll(Proba);
        for(Course c:s.getCourses()){
            System.out.println(c.getInstructor().getFirstName());
            System.out.println(c.getInstructor().getLastName());
        }*/

        CustomArrayList arr=new CustomArrayList();
        System.out.println(arr.isEmpty());
        for(int i=0;i<20;i++)
            arr.add(i);
        arr.affiche();
        System.out.println(arr.get(5));
        System.out.println(arr.get(6));
        System.out.println(arr.size());
        System.out.println(arr.isEmpty());
        System.out.println(arr.isIn(44));
        System.out.println(arr.isIn(1));
        System.out.println(arr.find(44));
        System.out.println(arr.find(1));
        arr.remove(1);
        arr.affiche();




    }
}

