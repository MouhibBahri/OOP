import java.util.*;

public class SingleNumber {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int res = arr[0];
        for (int i = 1; i < n; i++)
            res ^= arr[i];
        System.out.println(res);

    }
}

