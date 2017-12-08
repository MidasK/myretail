package com.myretail.product.client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDetailsClientTest
{

    @Autowired
    ProductDetailsClient productDetailsClient;

    //testing Beats product : productid - 16696652, provided resdsky.target.com has same data as at the time of writing this
    @Test
    public void testGetProductTitle()
    {

        String productId = "16696652";
        String title = productDetailsClient.getProductTitle(productId);

        assertEquals(title, "Beats Solo 2 Wireless - Black");

    }

}
