//Diego Salas
//cs 2050

class Node<T>
{
    T data;
    Node<T> next;

    public Node(T data)
    {
        this.data = data;
        this.next = null;
    }
}

public class MyLinkedStack<T>
{
    private Node<T> top;

    public MyLinkedStack()
    {
        this.top = null;
    }

    public void push(T data)
    {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
    }

    public T pop()
    {
        if (isEmpty())
        {
            return null; // Handle empty stack condition as needed
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    public T peek()
    {
        if (isEmpty())
        {
            return null; // Handle empty stack condition as needed
        }
        return top.data;
    }

    public boolean isEmpty()
    {
        return top == null;
    }
}
