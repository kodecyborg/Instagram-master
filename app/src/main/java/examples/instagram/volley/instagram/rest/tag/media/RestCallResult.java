package examples.instagram.volley.instagram.rest.tag.media;

import java.util.List;

import examples.instagram.volley.instagram.rest.EndPointResult;

/**
 * Container to pass the result from the Media end point to the EndPointListeners,
 * and between the RestCallResultBuilder and the Media end point.
 */
public class RestCallResult implements EndPointResult<List<String>> {
    private List<String> urls;
    private String nextUrl;

    public RestCallResult(List<String> urls, String nextUrl){
        this.urls = urls;
        this.nextUrl = nextUrl;
    }

    @Override
    public List<String> getData() {
        return urls;
    }

    public String getNextUrl() {
        return nextUrl;
    }
}
