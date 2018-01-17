package filter;

/**
 *  拦截器接口，定义以何种方式拦截URL
 *  @author myj
 */
public interface UrlIntercept {
    boolean shouldIntercept(String url);
}
