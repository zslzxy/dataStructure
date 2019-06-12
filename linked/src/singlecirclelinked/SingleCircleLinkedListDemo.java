package singlecirclelinked;

/**
 * @author ${张世林}
 * @date 2019/06/11
 * 作用：单向环形链表来 解决 约瑟夫问题
 */
public class SingleCircleLinkedListDemo {

    public static void main(String[] args) {
        SingleCircleLinkedList linkedList = new SingleCircleLinkedList();
        linkedList.add(new Node(1));
        linkedList.add(new Node(2));
        linkedList.add(new Node(3));
        linkedList.add(new Node(4));
        linkedList.add(new Node(5));
        linkedList.show();
        System.out.println(linkedList.size());
        System.out.println("=============");
        linkedList.pop(2,3);
    }

}

/**
 * 创建单向环形链表
 */
class SingleCircleLinkedList {

    //创建first节点，用于指向第一个节点
    private Node first = null;
    //创建一个辅助节点，该辅助节点是用于指向最后添加的那个位置的节点
    private Node curNode = null;

    public void add(Node node) {
        if (first == null) {
            first = node;
            first.setNext(first);
            curNode = first;
        } else {
            curNode.setNext(node);
            node.setNext(first);
            curNode = node;
        }
    }

    /**
     * 解决约瑟夫问题
     *      设编号为1，2，...n的n个人围坐一圈，约定编号为k（1 <= k <= n）的人从1开始报数，
     *      数到m的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，
     *      直到所有的人出列为止，由此产生一个出队编号序列。
     * 代码实现步骤：
     *  1. 第一步，先移动对应的节点，让first与curNode节点一起向前移动 k - 1 次
     *  1. 第二步，让first指向该被删除的下一个节点；
     *  2. 第三步，让curNode节点一直跟在first的上一个节点。
     * @param startNo:表示从第几个小孩开始数数
     * @param countNo:表示每一次数多少个数
     */
    public void pop(int startNo, int countNo) {
        //验证数据是否正常
        if (first == null || startNo < 1 || startNo > size() || countNo < 1) {
            System.out.println("链表为空，无法执行出圈操作");
            return;
        }
        //让first与curNode移动到startNO的位置
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            curNode = curNode.getNext();
        }
        //让其进行循环出圈的工作
        while (true) {
            //验证first与curNode相同的时候，说明圈中只有一个人
            if (first == curNode) {
                break;
            }
            //进行遍历，遍历完成以后，就是需要出圈的节点
            for (int i = 0; i < countNo - 1; i++) {
                first = first.getNext();
                curNode =curNode.getNext();
            }
            System.out.println("出圈的节点为："+first);
            first = first.getNext();
            curNode.setNext(first);
        }
        System.out.println("最后出圈的节点为："+first);

    }

    /**
     * 获取环形链表的大小
     * @return
     */
    public int size() {
        if (first == null) {
            System.out.println("链表为空，大小为0");
            return 0;
        }
        int num = 1;
        Node temp = this.first.getNext();
        while (true) {
            if (temp != null) {
                if (temp == first) {
                    break;
                }
                num++;
            }
            temp = temp.getNext();
        }
        return num;
    }


    /**
     * 遍历输出
     */
    public void show() {
        if (first == null) {
            System.out.println("链表为空，无法进行遍历");
        }
        Node temp = this.first;
        System.out.println(temp);
        while (temp.getNext() != first) {
            temp = temp.getNext();
            System.out.println(temp);
        }
    }

}

/**
 * 创建节点
 */
class Node {
    //编号
    private int no;
    //指向下一个节点
    private Node next;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}