public class CustomStack {
    private int capacity;
    private int size;
    private Object[] stack;

    public CustomStack(int capacity)
    {
        this.capacity=capacity;
        size=0;
        stack=new Object[capacity];
    }
    public void AddElement(Object e)
    {
        if(size<capacity)
        {
            stack[size]=e;
            size++;
        }else{
            System.out.println("The stack is at full capacity");

        }
    }
    public void RemoveElement()
    {
        if(size>0)
            size--;
        else
            System.out.println("The stack is already empty");
    }
    public Object LastInStack()
    {
        if(size>0)
            return stack[size-1];
        else
        {
            System.out.println("The stack is empty");
            return null;
        }

    }
    public boolean StackIsEmpty()
    {
        return size==0;
    }
    public boolean StackIsFull()
    {
        return size==capacity;
    }
}
