
package com.myretail.product.client;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.myretail.web.exception.ResourceNotfoundException;

@Component
public class ProductDetailsClient
{

    private static final String URL_SEPARATOR = "/";

    private static final String URL_QUERY_SEPARATOR = "?";

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${myretail.productdetail.url}")
    private String productDetailsUrl;

    @Value("${myretail.productdetail.url.querystring}")
    private String productDetailsUrlQueryString;

    public String getProductTitle(final String productId)
    {
        JSONObject json;
        String title = "";
        String responseJSON;

        String url = buildUrl(productId, productDetailsUrl);

        try
        {
            responseJSON = restTemplate.getForObject(url, String.class);
        }
        catch (RestClientException rce)
        {
            throw new ResourceNotfoundException(rce.getMessage());
        }

        try
        {
            json = new JSONObject(responseJSON);
            title = json.getJSONObject("product").getJSONObject("item").getJSONObject("product_description").getString("title");
        }
        catch (JSONException e)
        {
            throw new JSONException(e.getMessage());
        }

        return title;
    }

    private String buildUrl(final String productId, final String productDetailsUrl)
    {
        String url = productDetailsUrl + URL_SEPARATOR + productId + URL_QUERY_SEPARATOR + productDetailsUrlQueryString;

        return url;
    }

}
