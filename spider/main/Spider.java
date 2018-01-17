package main;

import download.DownloadUtil;
import data.SpiderData;
import filter.UrlIntercept;

/**
 * 爬虫主类
 * @author myj
 */
public class Spider {

    private static SpiderData spiderData = new SpiderData();

    /**
     * 初始化种子url
     * @param seeds 种子
     */
    public void initToDoList(String[] seeds){
        for(String seed : seeds){
            spiderData.enQueueUnvistedList(seed);
        }
    }

    /**
     * 抓取页面
     */
    public void crawl(){
        while(!spiderData.isUnVistedEmpty() && spiderData.getVisitedUrlNum() < 1000){
            Object urlO = spiderData.deQueueUnvisitedList();
            if(urlO == null){
                continue;
            }
            String url = String.valueOf(urlO);
            System.out.println(url);
            String domString = DownloadUtil.getPageDomString(url);
            spiderData.addVisitedUrl(url);
            DownloadUtil.extractLinks(domString, spiderData, new UrlIntercept() {
                public boolean shouldIntercept(String url) {
                   if(url.indexOf("www.baidu") == -1){
                       return true;
                   }
                   return false;
                }
            });
        }
    }

    public static void main(String[] args) {
        Spider spider = new Spider();
        spider.initToDoList(new String[]{"https://www.baidu.com"});
        spider.crawl();
    }

}
