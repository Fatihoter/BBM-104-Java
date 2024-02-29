import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.*;
class MyStack<T extends Comparable<T>> {
    private Node<T> top;

    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
    }

    public T pop() {
        if (top == null) {
            throw new NoSuchElementException("Stack is empty");
        }
        T item = top.data;
        top = top.next;
        return item;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        int count = 0;
        Node<T> current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public int calculateDistance() {
        int totalDistance = 0;
        Node<T> outer = top;
        while (outer != null) {
            Node<T> inner = outer.next;
            while (inner != null) {
                totalDistance += Math.abs(((Integer) outer.data) - ((Integer) inner.data));
                inner = inner.next;
            }
            outer = outer.next;
        }
        return totalDistance;
    }

    public void removeGreater(T value, BufferedWriter stackOutputWriter) throws IOException {
        Node<T> current = top, prev = null;
        while (current != null) {
            if (current.data.compareTo(value) > 0) {
                if (prev == null) {
                    top = current.next;
                } else {
                    prev.next = current.next;
                }
            } else {
                prev = current;
            }
            current = current.next;
        }
        stackOutputWriter.write("After removeGreater " + value + ":\n");
        printStack(stackOutputWriter);
    }

    public void reverse(int k, BufferedWriter stackOutputWriter) throws IOException {
        if (k > size() || k <= 0) {
            throw new IllegalArgumentException("Invalid k for reverse operation");
        }

        Node<T> current = top;
        Node<T> prev = null;
        Node<T> next = null;
        int count = 0;

        while (count < k && current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        if (top != null) {
            top.next = current;
        }
        top = prev;

        stackOutputWriter.write("After reverse " + k + ":\n");
        printStack(stackOutputWriter);
    }

    public void sortElements(BufferedWriter stackOutputWriter) throws IOException {
        boolean swapped;
        do {
            swapped = false;
            Node<T> current = top;
            while (current != null && current.next != null) {
                if (current.data.compareTo(current.next.data) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
        stackOutputWriter.write("After sortElements:\n");
        printStack(stackOutputWriter);
    }

    public void distinctElements(BufferedWriter stackOutputWriter) throws IOException {
        HashSet<T> distinctSet = new HashSet<>();
        Node<T> current = top;
        while (current != null) {
            distinctSet.add(current.data);
            current = current.next;
        }
        int distinctElementCount = distinctSet.size();
        stackOutputWriter.write("After distinctElements:\nTotal distinct element=" + distinctElementCount + "\n");
    }

    public void printStack(BufferedWriter writer) throws IOException {
        Node<T> current = top;
        while (current != null) {
            writer.write(current.data + " ");
            current = current.next;
        }
        writer.newLine();
    }
}