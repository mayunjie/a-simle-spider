package data;

import java.util.LinkedList;

/**
 * 宽度优先遍历,需要保证先进先出，所以使用队列来完成功能
 * @author myj
 */
public class TodoQueue {
    private LinkedList<Object> queue = new LinkedList<Object>();

    /**
     * 加入队列
     * @param o
     */
    public void enQueue(Object o){
        queue.addLast(o);
    }

    /**
     * 将队列第一个数据移除
     * @return
     */
    public Object deQueue(){
        return queue.removeFirst();
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return queue.isEmpty();
    }

    /**
     * 是否包含
     * @param o
     * @return
     */
    public boolean contains(Object o){
        return queue.contains(o);
    }
}
