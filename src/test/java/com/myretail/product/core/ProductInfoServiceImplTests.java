package com.myretail.product.core;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.myretail.product.client.ProductDetailsClient;
import com.myretail.product.db.ProductPriceEntity;
import com.myretail.product.db.ProductPriceRepository;
import com.myretail.web.exception.ResourceNotfoundException;

@RunWith(MockitoJUnitRunner.class)
public class ProductInfoServiceImplTests
{

    @Mock
    public ProductDetailsClient mockProductDetailsClient;

    @Mock
    public ProductPriceRepository mockProductPriceRepository;

    @InjectMocks
    public final ProductInfoServiceImpl productInfoServiceImpl = new ProductInfoServiceImpl();

    @Test
    public void testRetrieveProductInfo()
    {
        long productId = 11111111l;
        String title = "testTitle";
        String productIdString = String.valueOf(productId);
        ProductPriceEntity entity = new ProductPriceEntity("99999.99", "KOW");
        when(mockProductDetailsClient.getProductTitle(productIdString)).thenReturn(title);
        when(mockProductPriceRepository.findOne(productIdString)).thenReturn(entity);

        //SUT
        ProductInfo productInfo = productInfoServiceImpl.retrieveProductInfo(productId);

        assertEquals(productInfo.getName(), title);
        verify(mockProductDetailsClient).getProductTitle(eq(productIdString));
        verify(mockProductPriceRepository).findOne(eq(productIdString));

    }

    @Test(expected = ResourceNotfoundException.class)
    public void testRetrieveProductInfo_noRecordInDB()
    {
        long productId = 11111111l;
        String title = "testTitle";
        String productIdString = String.valueOf(productId);
        ResourceNotfoundException rne = new ResourceNotfoundException();
        ProductPriceEntity entity = new ProductPriceEntity("99999.99", "KOW");
        when(mockProductDetailsClient.getProductTitle(productIdString)).thenReturn(title);
        when(mockProductPriceRepository.findOne(productIdString)).thenReturn(null);


        //SUT
        ProductInfo productInfo = productInfoServiceImpl.retrieveProductInfo(productId);

    }

}
