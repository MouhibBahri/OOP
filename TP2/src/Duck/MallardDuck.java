package Duck;

public class MallardDuck extends Duck implements Quackable,Flyable {
    public void display()
    {
        System.out.println("this is a MallardDuck");
    }
    public void swim()
    {
        System.out.print("A mallard duck is ");
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
