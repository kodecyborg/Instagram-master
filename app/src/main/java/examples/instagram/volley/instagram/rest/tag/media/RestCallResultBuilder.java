package examples.instagram.volley.instagram.rest.tag.media;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Mines the JSONObject returned by the Instagram API to extract the list of the images urls.
 * Based on the builder pattern
 */
public class RestCallResultBuilder {
    private List<String> urls;
    private String nextUrl;

    public RestCallResultBuilder(JSONObject mainJson) throws JSONException{
        urls = new ArrayList<String>(mainJson.length());
        extractLinks(mainJson);
        getNextUrl(mainJson);
    }

    /**
     * Extract all the links from the data
     * path: root -> data
     *
     * @param mainJson the root object
     * @throws org.json.JSONException
     */
    private void extractLinks(JSONObject mainJson) throws JSONException{
        JSONArray array = mainJson.getJSONArray("data");
        int length = array.length();
        for (int i = 0; i < length; i++) {
            addLinkToUrls(array.getJSONObject(i));
        }
    }

    /**
     * Extract a single url
     * path: root -> data -> ARRAY ITEM -> images -> low resolution
     *
     * @param selfieData single selfie information
     * @throws org.json.JSONException
     */
    private void addLinkToUrls(JSONObject selfieData) throws JSONException{
        selfieData = selfieData.getJSONObject("images");
        selfieData = selfieData.getJSONObject("low_resolution");
        urls.add(selfieData.getString("url"));
    }

    /**
     * Get the next url that need to called to gather more data
     * path: root -> pagination -> next_url
     *
     * @param mainJson the json data from traning.example.instagramvolley.instagram as JSONObject
     * @throws org.json.JSONException
     */
    private void getNextUrl(JSONObject mainJson) throws  JSONException{
        JSONObject pagination = mainJson.getJSONObject("pagination");
        nextUrl = pagination.getString("next_url");
    }

    public RestCallResult build()throws JSONException{
        return new RestCallResult( urls, nextUrl);
    }
}
