
package com.myretail.product.core;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myretail.product.db.ProductPriceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoControllerTests
{

    @Autowired
    ProductInfoController productInfoController;

    @Autowired
    ProductPriceRepository productPriceRepository;

    @Test
    public void testCreateProduct() throws InterruptedException
    {

        /*create a new Product with following JSON :
        {
        "productId":13860428,
        "name":"testCreate",
        "current_price": {
        "value": 19.99,
        "currency_code": "AUD"
        }
        }
        */

        long productId = 13860428;
        CurrentPriceDTO currentPriceDTO = new CurrentPriceDTO(new CurrentPrice(new BigDecimal(19.99), "AUD"));

        ProductInfoDTO productInfoDTOCreated = new ProductInfoDTO();
        productInfoDTOCreated.setCurrentPrice(currentPriceDTO);
        productInfoDTOCreated.setId(productId);
        productInfoDTOCreated.setName("testCreate");

        String returnVal = productInfoController.createProductInformation(productInfoDTOCreated);

        Thread.sleep(1000);

        //Retrieve
        ProductInfoDTO productInfoDTORetrieved = productInfoController.getProductInformation(productId);

        assertEquals(productInfoDTOCreated.getId(), productInfoDTORetrieved.getId());
        assertEquals(productInfoDTOCreated.getCurrentPrice().getCurrencyCode(), productInfoDTORetrieved.getCurrentPrice().getCurrencyCode());

    }

    @Test
    public void testUpdateProduct_alreadyExists() throws InterruptedException
    {

        /*create a new Product with following JSON :
        {
        "productId":13860428,
        "name":"testCreate",
        "current_price": {
        "value": 19.99,
        "currency_code": "AUD"
        }
        }
        */

        long productId = 13860428;
        CurrentPriceDTO currentPriceDTO = new CurrentPriceDTO(new CurrentPrice(new BigDecimal(19.99), "AUD"));

        ProductInfoDTO productInfoDTOCreated = new ProductInfoDTO();
        productInfoDTOCreated.setCurrentPrice(currentPriceDTO);
        productInfoDTOCreated.setId(productId);
        productInfoDTOCreated.setName("testCreate");

        String returnVal = productInfoController.createProductInformation(productInfoDTOCreated);

        ProductInfoDTO productInfoDTOUpdate = productInfoDTOCreated;
        productInfoDTOUpdate.setCurrentPrice(new CurrentPriceDTO(new CurrentPrice(new BigDecimal(19.99), "JPY")));

        String updatedVal = productInfoController.updateProductInformation(productInfoDTOCreated);

        Thread.sleep(1000);

        //Retrieve
        ProductInfoDTO productInfoDTOUpdated = productInfoController.getProductInformation(productId);

        assertEquals(productInfoDTOCreated.getId(), productInfoDTOUpdated.getId());
        assertEquals("JPY", productInfoDTOUpdated.getCurrentPrice().getCurrencyCode());

    }

    @After
    public void teardown()
    {
        productPriceRepository.deleteAll();

    }

}
