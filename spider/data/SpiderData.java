package data;

import java.util.HashSet;
import java.util.Set;

/**
 * 爬虫数据POJO
 * 已遍历URL集以及未遍历URL队列
 * @author myj
 */
public class SpiderData {

    /**
     *  已遍历集
     */
    private Set<String> visitedSet = new HashSet<String>();

    /**
     * 未遍历队列
     */
    private TodoQueue unVistedList = new TodoQueue();

    public void addVisitedUrl(String url){
        visitedSet.add(url);
    }

    public void removeVisitedUrl(String url){
        visitedSet.remove(url);
    }

    public int getVisitedUrlNum(){
        return visitedSet.size();
    }

    /**
     * 向未遍历队列中添加url，需要保证该url不重复，否则将会无线循环
     * @param url
     */
    public void enQueueUnvistedList(String url){
        if(!unVistedList.contains(url) && !visitedSet.contains(url)){
            unVistedList.enQueue(url);
        }
    }

    public Object deQueueUnvisitedList(){
        return unVistedList.deQueue();
    }

    public boolean isUnVistedEmpty(){
        return unVistedList.isEmpty();
    }
}
