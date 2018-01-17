package download;

import data.SpiderData;
import filter.UrlIntercept;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.util.regex.Pattern;

/**
 * 抓取页面工具类
 * @author myj
 */
public class DownloadUtil {

    /**
     * get方法对象
     */
    public static HttpGet getMethod = new HttpGet();

    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36";
    static{
        //需要设置请求头，否则请求到的数据会有所不同
        getMethod.setHeader("User-Agent", userAgent);
    }

    /**
     *
     * @param url
     * @return
     */
    public static String getPageDomString(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try{
            getMethod.setURI(URI.create(url));
            response = httpClient.execute(getMethod);
            if(response.getStatusLine().getStatusCode() == 200){
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            //关闭资源
            try{
                httpClient.close();
                if(response != null){
                    response.close();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 用jsoup解析抓取到的html，解析其中的url
     * @param html
     * @param spiderData
     */
    public static void extractLinks(String html, SpiderData spiderData, UrlIntercept intercept){
        Document doc = Jsoup.parse(html);
        Elements links = doc.getElementsByTag("a");
        for(Element link : links){
            String url = link.attr("href");
            while(url.endsWith("/")){
                url = url.substring(0, url.length() - 1);
            }
            if(isUrlPattern(url) && !intercept.shouldIntercept(url)){
                spiderData.enQueueUnvistedList(url);
            }
        }
    }

    public static boolean isUrlPattern(String url){
        String urlPattern = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(urlPattern);
        return pattern.matcher(url).matches();
    }

    public static void main(String[] args) {
        //System.out.println(getPageDomString("http://www.baidu.com"));
    }
}
