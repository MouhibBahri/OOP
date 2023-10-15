import java.util.*;

public class LicenseKeyFormatting {
    public static void main(String args[]) {
        System.out.print("s:");
        Scanner sc =new Scanner(System.in);
        String s=sc.nextLine();
        System.out.print("k:");
        int k=sc.nextInt();

        StringBuilder chars=new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(c!='-')
            {
                chars.append(Character.toUpperCase(c));
            }
        }
        int start=chars.length()%k;
        StringBuilder ans=new StringBuilder();
        ans.append(chars.substring(0,start));
        for(int i=start;i<chars.length();i+=k) {
            if (i != 0)
                ans.append( '-');
            ans.append(chars.substring(i, i + k));
        }

        System.out.println(ans.toString());
    }
}