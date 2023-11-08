//Diego Salas
//cs 2050
import java.io.*;
import java.util.Scanner;
import java.util.EmptyStackException;

public class Program4
{
    public static void main(String[] args)
    {
        try
        {
            Scanner inputFile = new Scanner(new File("Program3.txt"));
            PrintWriter outputFile = new PrintWriter(new FileWriter("Program3.out"));

            while (inputFile.hasNextLine())
            {
                String infixExpression = inputFile.nextLine();
                String postfixExpression = infixToPostfix(infixExpression);
                if (postfixExpression != null)
                {
                    outputFile.println(infixExpression + " -> " + postfixExpression);
                } else
                {
                    // Handle unmatched parentheses and invalid characters
                    outputFile.println(infixExpression + " -> Error: Unmatched Parens or Invalid Character");
                }
            }

            inputFile.close();
            outputFile.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing to the output file: " + e.getMessage());
        }
    }

    public static String infixToPostfix(String s)
    {
        StringBuilder postfix = new StringBuilder();
        MyLinkedStack<Character> stack = new MyLinkedStack<>();

        boolean hasInvalidCharacter = false;

        for (char c : s.toCharArray())
        {
            if (isValidCharacter(c))
            {
                if (isOperand(c))
                {
                    postfix.append(c);
                    postfix.append(' ');
                } else if (c == '(')
                {
                    stack.push(c);
                } else if (c == ')')
                {
                    while (!stack.isEmpty() && stack.peek() != '(')
                    {
                        postfix.append(stack.pop());
                        postfix.append(' ');
                    }
                    if (!stack.isEmpty() && stack.peek() == '(')
                    {
                        stack.pop();
                    } else {
                        // Unmatched right parenthesis
                        return null;
                    }
                } else if (isOperator(c))
                {
                    while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek()))
                    {
                        postfix.append(stack.pop());
                        postfix.append(' ');
                    }
                    stack.push(c);
                }
            } else
            {
                // Invalid character
                hasInvalidCharacter = true;
            }
        }

        while (!stack.isEmpty())
        {
            if (stack.peek() == '(')
            {
                // Unmatched left parenthesis
                return null;
            }
            postfix.append(stack.pop());
            postfix.append(' ');
        }

        if (hasInvalidCharacter)
        {
            return null;
        }

        return postfix.toString().trim();
    }

    private static boolean isValidCharacter(char c)
    {
        // Only allow digits, decimal point, parentheses, and the specified operators
        return Character.isDigit(c) || c == '.' || c == '(' || c == ')' || isOperator(c) || c == ' ';
    }

    private static boolean isOperand(char c)
    {
        // Allow digits and decimal point
        return Character.isDigit(c) || c == '.';
    }

    private static boolean isOperator(char c)
    {
        // Only allow these operators
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '(' || c == ')';
    }

    private static int precedence(char c)
    {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/' || c == '%')
        {
            return 2;
        } else if (c == '(' || c == ')')
        {
            return 0; // Parentheses should not affect precedence
        }
        return -1; // Default precedence for other characters
    }
}
