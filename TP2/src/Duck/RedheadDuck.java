package Duck;

public class RedheadDuck extends Duck implements Quackable,Flyable {
    public void display()
    {
        System.out.println("this is a RedheadDuck");
    }
    public void swim()
    {
        System.out.print("A redhead duck is ");
        super.swim();
    }
    public void quack()
    {
        System.out.println("quack quack...");
    }
    public void fly()
    {
        System.out.println("flying...");
    }
}
