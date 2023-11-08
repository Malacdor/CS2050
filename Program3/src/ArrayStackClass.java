import java.util.EmptyStackException;

public class ArrayStackClass<T>
{
    private final Object[] stackArray;
    private int top;

    public ArrayStackClass(int N)
    {
        stackArray = new Object[N];
        top = -1;
    }

    public void push(T value)
    {
        if (top != stackArray.length - 1)
        {
            top++;
            stackArray[top] = value;
        }
    }

    public T pop() throws EmptyStackException
    {
        if (empty())
        {
            throw new EmptyStackException();
        } else
        {
            T value = (T) stackArray[top];
            top--;
            return value;
        }
    }

    public T peek() throws EmptyStackException
    {
        if (empty())
        {
            throw new EmptyStackException();
        } else
        {
            return (T) stackArray[top];
        }
    }

    public boolean empty() {
        return top == -1;
    }

    // The size() method is not used, so it can be removed.
}