import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class DownloadPage {

    public static void downloadPage(String uri){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(uri);
        try{
            HttpResponse response = httpClient.execute(get);
            if(response.getStatusLine().getStatusCode() == 200){
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                httpClient.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }



    }

    public static void main(String[] args) {
        DownloadPage.downloadPage("https://www.baidu.com");
    }
}
