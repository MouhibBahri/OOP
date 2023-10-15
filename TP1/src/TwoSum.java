import java.util.*;

public class TwoSum {
    
    public static void main(String[] args) 
    {

    	Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int target=sc.nextInt();
        Map<Integer,Integer> m=new HashMap<>();
        for(int i=0;i<n;i++){
            int x;
            x=sc.nextInt();
            if(m.containsKey(target-x)){
                System.out.println(m.get(target-x));
                System.out.println(i);
                break;
            }
            m.put(x,i);
        }
    }
}

