package Duck;

public class DecoyDuck extends Duck {
    public void display()
    {
        System.out.println("this is a Decoy");
    }
    public void swim()
    {
        System.out.print("A decoy is ");
        super.swim();
    }
}
