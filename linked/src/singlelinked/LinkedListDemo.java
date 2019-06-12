package singlelinked;

/**
 * @author ${张世林}
 * @date 2019/06/10
 * 作用：单向的、带有头结点的、按照添加顺序加入链表的 单链表
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "张世林", "林林");
        HeroNode node2 = new HeroNode(2, "田水龙", "水水");
        HeroNode node3 = new HeroNode(3, "王旭", "嘘嘘");
        HeroNode node4 = new HeroNode(4, "杨书平", "杨总");
        LinkedList linkedList = new LinkedList();
        linkedList.addByOrder(node1);
        linkedList.addByOrder(node2);
        linkedList.addByOrder(node4);
        linkedList.addByOrder(node3);
        linkedList.show();

//        linkedList.changeNode(new HeroNode(2, "tom", "house"));
        System.out.println("======================");
//        linkedList.deleteNode(3);
//        linkedList.show();
//        System.out.println("链表的有效个数为" + linkedList.size());
//        System.out.println(linkedList.getNodeByLastIndex(3));
//        linkedList.reverseLinkedList();
//        linkedList.show();
        LinkedList linkedList1 = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        linkedList1.addByOrder(new HeroNode(1, "", ""));
        linkedList1.addByOrder(new HeroNode(2, "", ""));
        linkedList1.addByOrder(new HeroNode(4, "", ""));
        linkedList2.addByOrder(new HeroNode(1, "", ""));
        linkedList2.addByOrder(new HeroNode(3, "", ""));
        linkedList2.addByOrder(new HeroNode(4, "", ""));
        HeroNode heroNode = mergeTwoLists(linkedList1.getHead().next, linkedList2.getHead().next);
        while (heroNode != null) {
            System.out.println(heroNode);
            heroNode = heroNode.next;
        }

    }

    /**
     * 按序合并两个单链表
     */
    public static HeroNode mergeTwoLists(HeroNode l1, HeroNode l2) {
        HeroNode node = new HeroNode(0, "", "");
        HeroNode result = node;
        while (l1 != null && l2 != null) {
            if (l1.no < l2.no) {
                result.next = l1;
                result = result.next;
                l1 = l1.next;
            } else {
                result.next = l2;
                result = result.next;
                l2 = l2.next;
            }
        }

        if (l1 != null) {
            result.next = l1;
        }
        if (l2 != null) {
            result.next = l2;
        }
        return node.next;
    }

    public static HeroNode mergeLinkedList(HeroNode head1, HeroNode head2) {
        HeroNode newHead = new HeroNode(0, "", "");
        HeroNode temp = newHead;
        while (head1 != null && head2 != null) {
            if (head1.no <= head2.no) {
                temp.next = head1.next;
                temp = temp.next;
                head1 = head1.next;


            } else {
                temp.next = head2.next;
                temp = temp.next;
                head2 = head2.next;
            }
        }
        if (head1 != null) {
            newHead.next = head1;
        }
        if (head2 != null) {
            newHead.next = head2;
        }
        return newHead.next;
    }

}

/**
 * 定义一个单链表
 */
class LinkedList {
    /**
     * 先初始化一个头结点，通常情况下，头结点是不会改变的。
     */
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点方式一：直接将节点添加到链表最后位置处
     * 步骤：
     * 1. 找到当前链表的最后的一个节点
     * 2. 将最后的一个节点的next指向 新的节点
     */
    public void addByLast(HeroNode heroNode) {
        //创建一个辅助节点，用于遍历该链表
        HeroNode temp = this.head;
        //遍历链表，一直到链表的最后
        while (true) {
            //如果temp这个辅助节点一直指向了最后一个节点，则将会跳出循环。
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //将temp.next指向新的节点
        temp.next = heroNode;
    }

    /**
     * 添加节点方式二：遍历链表数据，在数据合适的位置插入该节点。
     * 步骤：
     * 1. 找到该新的节点需要在链表的对应位置处。
     * 2. 将该位置的前一个节点的next指向新节点，新节点的next指向该位置的后一个节点
     */
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = this.head;
        boolean addSuccess = false;
        while (temp.next != null) {
            if (temp.no < heroNode.no && temp.next.no >= heroNode.no) {
                heroNode.next = temp.next;
                temp.next = heroNode;
                addSuccess = true;
            }
            temp = temp.next;
        }
        if (!addSuccess) {
            temp.next = heroNode;
        }
    }

    /**
     * 判断当前链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return head.next == null;
    }

    /**
     * 遍历链表数据
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("链表为空，无法进行遍历");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("链表遍历完成");
                break;
            }
            //将temp输出
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 根据id删除链表中的一个节点
     */
    public void deleteNode(int no) {
        HeroNode temp = this.head;
        while (true) {
            if (temp.next == null) {
                System.out.printf("未查到no=%d的数据，删除失败。\n", no);
                break;
            }
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                System.out.println("删除对应的节点成功");
                return;
            }
            temp = temp.next;
        }
    }

    /**
     * 修改一个节点的数据
     */
    public void changeNode(HeroNode newHeroNode) {
        HeroNode temp = this.head;
        while (true) {
            if (temp == null) {
                System.out.printf("未查到no=%d的数据，修改失败。\n", newHeroNode.no);
                break;
            }
            if (temp.no == newHeroNode.no) {
                temp.name = newHeroNode.name;
                temp.nickName = newHeroNode.nickName;
                System.out.printf("修改no=%d的数据成功。\n", newHeroNode.no);
                return;
            }
            temp = temp.next;
        }
    }

    /**
     * 获取单链表的有效节点个数，如果有头结点，则不会统计头结点
     *
     * @return
     */
    public int size() {
        int num = 0;
        if (head.next == null) {
            return 0;
        }
        HeroNode temp = this.head.next;
        while (true) {
            if (temp == null) {
                return num;
            }
            num++;
            temp = temp.next;
        }
    }

    /**
     * 获取链表中倒数第 k 个节点数据
     */
    public HeroNode getNodeByLastIndex(int lastIndex) {
        int size = size();
        if (size == 0) {
            return null;
        }
        //验证数据是否规范
        if (lastIndex <= 0 || lastIndex > size) {
            return null;
        }
        //循环定位到倒数第k个的位置
        HeroNode temp = this.head.next;
        for (int i = 0; i < size - lastIndex; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 单链表的反转
     * 思路：
     * 1. 首先先定义一个节点 reverseHead = new HeroNode();
     * 2. 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，放置到 reverseHead 链表的最前端。
     * 3. 使用  head.next = reverseHead.next;
     */
    public void reverseLinkedList() {
        //创建一个新链表的头结点
        HeroNode reverseNode = new HeroNode(1, "", "");
        //遍历原始链表
        HeroNode temp = this.head.next;
        //创建一个节点对象，其作用是用于保存 原始链表指向的当前节点的下一个节点
        HeroNode defaultNextNode = null;
        //循环遍历原始链表
        while (temp != null) {
            //首先先将原数据的下一个节点地址保存在这个变量中
            defaultNextNode = temp.next;
            //将temp的下一个节点指向新的链表的最前端
            temp.next = reverseNode.next;
            //将temp连接到新的链表中
            reverseNode.next = temp;
            //让temp继续回到原始链表
            temp = defaultNextNode;
        }
        head.next = reverseNode.next;

//        int size = size();
//        for (int i = 0; i < size; i++) {
//            //首先先将原数据的下一个节点地址保存在这个变量中
//            defaultNextNode = temp.next;
//            //将temp的下一个节点指向新的链表的最前端
//            temp.next = reverseNode.next;
//            //将temp连接到新的链表中
//            reverseNode.next = temp;
//            //让temp继续回到原始链表
//            temp = defaultNextNode;
//        }
//        head.next = reverseNode.next;
    }


}

/**
 * 定义一个节点
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