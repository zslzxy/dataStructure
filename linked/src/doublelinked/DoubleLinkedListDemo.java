package doublelinked;

/**
 * @author ${张世林}
 * @date 2019/06/11
 * 作用：
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DobuleLinkedList linkedList = new DobuleLinkedList();
        HeroNode node1 = new HeroNode(1, "张世林", "林林");
        HeroNode node2 = new HeroNode(2, "田水龙", "水水");
        HeroNode node3 = new HeroNode(3, "王旭", "嘘嘘");
        HeroNode node4 = new HeroNode(4, "杨书平", "杨总");
//        linkedList.addByLast(node1);
//        linkedList.addByLast(node2);
//        linkedList.addByLast(node3);
//        linkedList.addByLast(node4);
        linkedList.addByOrder(node1);
        linkedList.addByOrder(node2);
        linkedList.addByOrder(node4);
        linkedList.addByOrder(node3);
//        linkedList.changeNode(new HeroNode(35, "ttt","yyy"));
        linkedList.show();
//        linkedList.delNode(7);
//        linkedList.show();
    }
}

class DobuleLinkedList {

    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 在双向链表的末尾添加节点
     * @param heroNode
     */
    public void addByLast(HeroNode heroNode) {
        HeroNode temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        heroNode.pre = temp;
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = this.head;
        boolean addSuccess = false;
        while (temp.next != null) {
            if (heroNode.no > temp.no && heroNode.no <= temp.next.no) {
                heroNode.next = temp.next;
                heroNode.pre = temp.next.pre;
                temp.next.pre = heroNode;
                temp.next = heroNode;
                addSuccess = true;
                break;
            }
            temp = temp.next;
        }
        if (!addSuccess) {
            temp.next = heroNode;
            heroNode.pre = temp;
        }
    }

    /**
     * 修改双向链表节点信息
     */
    public void changeNode(HeroNode node) {
        if (isEmpty()) {
            System.out.println("双向链表为空，无法进行修改操作");
            return;
        }
        boolean changeSuccess = false;
        HeroNode temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
            if (temp.no == node.no) {
                temp.name = node.name;
                temp.nickName = node.nickName;
                changeSuccess = true;
                break;
            }
        }
        if (!changeSuccess) {
            System.out.println("未查找到对应的节点数据");
        } else {
            System.out.println("修改节点成功");
        }
    }

    /**
     * 删除双向链表中的节点
     */
    public void delNode(int no) {
        HeroNode temp = this.head.next;
        boolean findSuccess = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                findSuccess = true;
                break;
            }
            temp = temp.next;
        }
        if (findSuccess) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
            System.out.println("删除成功");
        } else {
            System.out.println("未查找到对应的节点，删除失败");
        }
    }


    /**
     * 判断链表是否为空
     */
    public boolean isEmpty() {
        return head.next == null;
    }

    /**
     * 遍历双向链表的方法
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("双向链表中不存在节点，无法进行遍历");
            return;
        }
        HeroNode temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp);
        }
    }

}

/**
 * 定义一个双向节点
 */
class HeroNode {
    //id
    public int no;
    //姓名
    public String name;
    //昵称
    public String nickName;
    //下一个节点
    public HeroNode next;
    //前一个节点
    public HeroNode pre;

    //创建一个节点的构造器
    public HeroNode(int no, String name, String nickName) {
        this.name = name;
        this.no = no;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}