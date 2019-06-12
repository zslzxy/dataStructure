package queue;

import java.util.Scanner;

/**
 * @author ${张世林}
 * @date 2019/06/10
 * 作用：数组环形队列
 */
public class AroundQueue {

    private int maxSzie;

    private int front;

    private int rear;

    private int[] arr;

    public AroundQueue(int maxSzie) {
        this.maxSzie = maxSzie;
        front = 0;
        rear = 0;
        arr = new int[maxSzie];
    }

    /**
     * 判断是否已经满了
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSzie == front;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    public void push(int val) {
        if (isFull()) {
            System.out.println("当前队列已满，无法添加数据");
            return;
        }
        arr[rear] = val;
        rear = (rear + 1)  % maxSzie;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("当前队列为空，无法取出数据");
        }
        int val = 0;
        val = arr[front];
        front = (front + 1) % maxSzie;
        return val;
    }

    public int getFirst() {
        if (isEmpty()) {
            throw new RuntimeException("队列没有元素");
        }
        return arr[front];
    }

    /**
     * 获取对应的数组队列大小
     * @return
     */
    public int size() {
        return (rear + maxSzie - front) % maxSzie;
    }

    public void show() {
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", (i % maxSzie), arr[i % maxSzie]);
        }
    }

    public static void main(String[] args) {
        AroundQueue queue = new AroundQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)");
            System.out.println("e(exit)");
            System.out.println("a(add)");
            System.out.println("g(get)");
            System.out.println("h(head)");
            key =scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.println("请输入需要加入的一个数：");
                    int val = scanner.nextInt();
                    queue.push(val);
                    break;
                case 'g':
                    try {
                        System.out.println("取出的数据为：" + queue.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int headQueue = queue.getFirst();
                        System.out.println("队列头的数据是：" + headQueue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }


}
