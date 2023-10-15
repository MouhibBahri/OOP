import java.util.*;
import java.util.List;
public class Main {
    public static void main(String args[]) {
        //EX1
        Scanner sc = new Scanner(System.in);
        System.out.print("accountNumber:");
        int accountNumber = sc.nextInt();
        sc.nextLine();
        System.out.print("accountHolderName:");
        String accountHolderName = sc.nextLine();
        System.out.print("balance:");
        int balance = sc.nextInt();

        BankAccount b = new BankAccount(accountNumber,accountHolderName,balance);
        System.out.println("accountHolderName:"+b.getAccountHolderName());
        System.out.println("accountNumber:"+b.getAccountNumber());
        System.out.println("balance:"+b.getBalance());

        System.out.print("How much would you like to deposit:");
        b.deposit(sc.nextInt());
        System.out.println("balance:"+b.getBalance());
        System.out.print("How much would you like to withdraw:");
        b.withdraw(sc.nextInt());
        System.out.println("balance:"+b.getBalance());

        //EX2
        System.out.println("====================");
        Instructor ins1=new Instructor(1234,"Ahmed","Bouzid");
        Instructor ins2=new Instructor(7894,"Amir","Mallek");
        Course BD=new Course(102,"Database",ins1);
        Course Stats=new Course(103,"Statistics",ins2);
        Student s=new Student(555,"Mouhib","Bahri");
        s.enroll(BD);
        s.enroll(Stats);
        System.out.println("Student "+s.getFirstName()+" "+s.getLastName()+" is enrolled in: ");
        for(Course c:s.getCourses()){
            System.out.println("* "+c.getCourseName()+" instructed by "+c.getInstructor().getFirstName() + " "+ c.getInstructor().getLastName());
        }

        //EX3
        System.out.println("====================");
        CustomArrayList arr=new CustomArrayList();
        System.out.println("Is array empty:"+arr.isEmpty());
        for(int i=0;i<20;i++)
            arr.add(i);
        arr.affiche();
        System.out.println("arr[5]:"+arr.get(5));
        System.out.println("arr[6]:"+arr.get(6));
        System.out.println("size of arr:"+arr.size());
        System.out.println("Is array empty:"+arr.isEmpty());
        System.out.println("Is in 44:"+arr.isIn(44));
        System.out.println("Is in 1:"+arr.isIn(1));
        System.out.println("index of 44:"+arr.find(44));
        System.out.println("index of 1:"+arr.find(1));
        System.out.println("removing 1");
        arr.remove(1);
        arr.affiche();




    }
}

