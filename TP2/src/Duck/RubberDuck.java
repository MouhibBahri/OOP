package Duck;

public class RubberDuck extends Duck implements Quackable {
    public void display()
    {
        System.out.println("this is a RubberDuck");
    }
    public void swim()
    {
        System.out.print("A rubber duck is ");
        super.swim();
    }
    public void quack()
    {
        System.out.println("quack quack...");
    }
}
