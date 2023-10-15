import java.util.*;
import java.util.Stack;
public class ValidParentheses {
    public static void main(String args[])
    {
        System.out.print("S:");
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        Stack<Character> stack=new Stack<>();

        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c == '(' || c=='[' || c=='{')
            {
                stack.push(c);
            }else if(stack.isEmpty())
            {
                stack.push(c);
                break;
            }else
            {
                char top=stack.peek();
                if((c==')' && top=='(') || (c==']' && top=='[') || (c=='}' && top=='{') )
                {
                    stack.pop();
                }
                else break;

            }
        }
        if(stack.isEmpty())
            System.out.println("String is valid");
        else
            System.out.println("String is not valid");
    }
}