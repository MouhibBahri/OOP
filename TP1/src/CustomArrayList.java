import java.util.List;


public class CustomArrayList {
    private int size;
    private int capacity;
    private int[] li;
    public CustomArrayList()
    {
        capacity=10;
        li=new int[capacity];
    }
    public CustomArrayList(int initialSize)
    {
        capacity=initialSize;
        li=new int[capacity];
    }
    private void resize(){
        int[] temp=new int[capacity*2];
        capacity*=2;
        for(int i=0;i<size;i++)
        {
            temp[i]=li[i];
        }
        li=temp;
    }
    public void add(int obj){
        if(size>=capacity)
        {
            resize();
        }
        li[size] = obj;
        size++;

    }
    public void add(int index,int x){
        if(index>=capacity)
            resize();
        for(int i=size;i>index;i--)
        {
            li[i]=li[i-1];
        }
        li[index]=x;
        size++;
    }
    public int get(int index){
        if(index>=size) {
            System.out.println("Error: index out of range");
            return -1;
        }
        return li[index];
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public boolean isIn(int x){
        int i=0;
        while(i<size && li[i]!=x)
        {
            i++;
        }
        return i<size;
    }
    public int find(int x){
        int i=0;
        while(i<size && li[i]!=x)
            i++;
        return i==size?-1:i;

    }
    public void remove(int x){
        int pos=find(x);
        if(pos==-1){
            return ;
        }
        for(int i=pos;i<size-1;i++){
            li[i]=li[i+1];
        }
        size--;
    }
    public void affiche(){
        for(int i=0;i<size;i++){
            System.out.print(li[i]);
            System.out.print(" ");

        }
        System.out.print("\n");
    }

}
