import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;

class MyQueue<T extends Comparable<T>> {
    private Node<T> head, tail;

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null) {
            head = tail;
        }
    }

    public T dequeue() {
        if (head == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return item;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public int calculateDistance() {
        int totalDistance = 0;
        Node<T> outer = head;
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

    public void removeGreater(T value, BufferedWriter queueOutputWriter) throws IOException {
        Node<T> current = head, prev = null;
        while (current != null) {
            if (current.data.compareTo(value) > 0) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                if (current == tail) {
                    tail = prev;
                }
            } else {
                prev = current;
            }
            current = current.next;
        }
        queueOutputWriter.write("After removeGreater " + value + ":\n");
        printQueue(queueOutputWriter);
    }

    public void reverse(int k, BufferedWriter queueOutputWriter) throws IOException {
        if (k > size() || k <= 0) {
            throw new IllegalArgumentException("Invalid k for reverse operation");
        }

        Node<T> current = head;
        Node<T> prev = null;
        Node<T> next = null;
        Node<T> tailOfReversedPart = current;
        int count = 0;

        // reverse First k element
        while (count < k && current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        // Merge the inverted section with the remaining tail
        tailOfReversedPart.next = current;
        head = prev;

        queueOutputWriter.write("After reverse " + k + ":\n");
        printQueue(queueOutputWriter);
    }

    public void sortElements(BufferedWriter queueOutputWriter) throws IOException {
        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
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
        queueOutputWriter.write("After sortElements:\n");
        printQueue(queueOutputWriter);
    }

    public void distinctElements(BufferedWriter queueOutputWriter) throws IOException {
        HashSet<T> distinctSet = new HashSet<>();
        Node<T> current = head;
        while (current != null) {
            distinctSet.add(current.data);
            current = current.next;
        }
        int distinctElementCount = distinctSet.size();
        queueOutputWriter.write("After distinctElements:\nTotal distinct element=" + distinctElementCount + "\n");
    }

    public void printQueue(BufferedWriter writer) throws IOException {
        Node<T> current = head;
        while (current != null) {
            writer.write(current.data + " ");
            current = current.next;
        }
        writer.newLine();
    }
}
