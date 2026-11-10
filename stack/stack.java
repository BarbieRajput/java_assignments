package DS_ESE;
import java.util{}
class MyStack {
    int a[];
    int top = -1;
    MyStack(int size){
        a = new int[size];
    }
    void push(int x) {
        if(top == a.length - 1)
            throw new RuntimeException("Stack overflow");
        a[++top] = x;
    }
    
    int pop() {
        if(top == -1)
            throw new RuntimeException("Stack Underflow");
        return a[top--];
    }
    
    boolean isEmpty() {
        return top == -1;
    }
    
    int size() {
        return top + 1;
    }
}

public class Stack {

    // Evaluate Postfix Expression (Simpler approach supporting multi-digit numbers)
    static int evalutePostfix(String exp) {
        MyStack s = new MyStack(100);
        int i = 0;
        int n = exp.length();
        
        while(i < n) {
            char ch = exp.charAt(i);
            
            if(ch == ' ') {
                i++;
                continue;
            }
            
            if(Character.isDigit(ch)) {    
                int num = 0;
                while(i < n && Character.isDigit(exp.charAt(i))) {
                    num = num * 10 + (exp.charAt(i) - '0');
                    i++;
                } 
                s.push(num);
                continue;
            } else {
                // Otherwise it's an operator
                if(s.size() < 2) {
                    System.out.println("Invalid Expression: Insufficient operands.");
                    return -1;
                }
                int operand2 = s.pop();
                int operand1 = s.pop();
                int result;
                
                switch(ch) {
                    case '+': result = operand1 + operand2; break;
                    case '-': result = operand1 - operand2; break;
                    case '*': result = operand1 * operand2; break;
                    case '/': 
                        if(operand2 == 0) {
                            System.out.println("Division by zero error.");
                            return -1;
                        }
                        result = operand1 / operand2; 
                        break;
                    default: 
                        System.out.println("Invalid operator.");
                        return -1;
                } 
                s.push(result);
                i++;
            }
        }
        return s.pop();    
    }
    
    // Reverse String 
    static String reverse(String str) {
        MyStack s = new MyStack(str.length() > 0 ? str.length() : 1);
        
        // Push every character
        for(int i = 0; i < str.length(); i++) {
            s.push(str.charAt(i));
        }
        
        String result = "";
        
        // Pop every element 
        while(!s.isEmpty()) {
            result = result + (char) s.pop();    
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int choice;
            do {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Evaluate Postfix Expression");
                System.out.println("2. Reverse String");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.print("Enter postfix expression (e.g., 5 3 + 2 *): ");
                        String exp = sc.nextLine();
                        int result = evalutePostfix(exp);
                        if (result != -1) {
                            System.out.println("Result: " + result);
                        }
                        break;
                    case 2:
                        System.out.print("Enter string to reverse: ");
                        String str = sc.nextLine();
                        String reversed = reverse(str);
                        System.out.println("Reversed String: " + reversed);
                        break;
                    case 3:
                        System.out.println("Exiting the program...");
                        break;
                    default:
                        System.out.println("Invalid choice...");
                }
            } while (choice != 3);
        } catch (Exception e) {
            System.out.println("Invalid input or error has occurred... " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}