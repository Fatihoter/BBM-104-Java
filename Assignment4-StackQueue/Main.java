import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


//Fatih Öter 2210356119
//BBM104-Assignment-4

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter stackOutputWriter = null;
        BufferedWriter queueOutputWriter = null;
        try {
            String inputFileName = args[0];
            Scanner commandScanner = new Scanner(new File(inputFileName));
            Scanner queueScanner = new Scanner(new File("queue.txt"));
            Scanner stackScanner = new Scanner(new File("stack.txt"));
            stackOutputWriter = new BufferedWriter(new FileWriter("stackOut.txt"));
            queueOutputWriter = new BufferedWriter(new FileWriter("queueOut.txt"));

            MyStack<Integer> stack = new MyStack<>();
            MyQueue<Integer> queue = new MyQueue<>();

            List<Integer> stackValues = new ArrayList<>();
            while (stackScanner.hasNext()) {
                stackValues.add(stackScanner.nextInt());
            }

            // Add elements to the stack in reverse order
            for (int i = stackValues.size() - 1; i >= 0; i--) {
                stack.push(stackValues.get(i));
            }
            while (queueScanner.hasNextInt()) {
                queue.enqueue(queueScanner.nextInt());
            }

            while (commandScanner.hasNext()) {
                String operationType = commandScanner.next();
                switch (operationType) {
                    case "S":
                        processStackOperation(commandScanner, stack, stackOutputWriter);
                        break;
                    case "Q":
                        processQueueOperation(commandScanner, queue, queueOutputWriter);
                        break;
                    default:
                        stackOutputWriter.write("Invalid operation type\n");
                        break;
                }

                // Update stack.txt and queue.txt files after each transaction
                updateStackFile(stack);
                updateQueueFile(queue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stackOutputWriter != null) {
                stackOutputWriter.close();
            }
            if (queueOutputWriter != null) {
                queueOutputWriter.close();
            }
        }
    }

    private static void processStackOperation(Scanner scanner, MyStack<Integer> stack, BufferedWriter stackOutputWriter) throws IOException {
        String operation = scanner.next();
        switch (operation) {
            case "calculateDistance":
                int totalDistance = stack.calculateDistance();
                stackOutputWriter.write("After calculateDistance:\nTotal distance=" + totalDistance + "\n");
                break;
            case "addOrRemove":
                int k = scanner.nextInt();
                if (k > 0) {
                    Random rand = new Random();
                    for (int i = 0; i < k; i++) {
                        stack.push(rand.nextInt(51)); // 0-50 interval random number.
                    }
                } else {
                    for (int i = 0; i < Math.abs(k); i++) {
                        if (!stack.isEmpty()) {
                            stack.pop();
                        }
                    }
                }
                stackOutputWriter.write("After addOrRemove " + k + ":\n");
                stack.printStack(stackOutputWriter);
                break;
            case "distinctElements":
                stack.distinctElements(stackOutputWriter);
                break;
            case "removeGreater":
                int value = scanner.nextInt();
                stack.removeGreater(value, stackOutputWriter);
                break;
            case "reverse":
                int count = scanner.nextInt();
                stack.reverse(count, stackOutputWriter);
                break;
            case "sortElements":
                stack.sortElements(stackOutputWriter);
                break;
            default:
                stackOutputWriter.write("Invalid stack operation\n");
                break;
        }
    }


    private static void processQueueOperation(Scanner scanner, MyQueue<Integer> queue, BufferedWriter queueOutputWriter) throws IOException {
        String operation = scanner.next();
        switch (operation) {
            case "calculateDistance":
                int totalDistance = queue.calculateDistance();
                queueOutputWriter.write("After calculateDistance:\nTotal distance=" + totalDistance + "\n");
                break;
            case "addOrRemove":
                int k = scanner.nextInt();
                if (k > 0) {
                    Random rand = new Random();
                    for (int i = 0; i < k; i++) {
                        queue.enqueue(rand.nextInt(51)); // 0-50 interval random number.
                    }
                } else {
                    for (int i = 0; i < Math.abs(k); i++) {
                        if (!queue.isEmpty()) {
                            queue.dequeue();
                        }
                    }
                }
                queueOutputWriter.write("After addOrRemove " + k + ":\n");
                queue.printQueue(queueOutputWriter);
                break;
            case "distinctElements":
                queue.distinctElements(queueOutputWriter);
                break;
            case "removeGreater":
                int value = scanner.nextInt();
                queue.removeGreater(value, queueOutputWriter);
                break;
            case "reverse":
                int count = scanner.nextInt();
                queue.reverse(count, queueOutputWriter);
                break;
            case "sortElements":
                queue.sortElements(queueOutputWriter);
                break;
            default:
                queueOutputWriter.write("Invalid queue operation\n");
                break;
        }
    }

    private static void updateStackFile(MyStack<Integer> stack) throws IOException {
        BufferedWriter stackFileWriter = new BufferedWriter(new FileWriter("stack.txt"));
        stack.printStack(stackFileWriter);
        stackFileWriter.close();
    }

    private static void updateQueueFile(MyQueue<Integer> queue) throws IOException {
        BufferedWriter queueFileWriter = new BufferedWriter(new FileWriter("queue.txt"));
        queue.printQueue(queueFileWriter);
        queueFileWriter.close();
    }
}
