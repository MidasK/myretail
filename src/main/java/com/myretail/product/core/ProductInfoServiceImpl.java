
package com.myretail.product.core;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.product.client.ProductDetailsClient;
import com.myretail.product.db.ProductPriceEntity;
import com.myretail.product.db.ProductPriceRepository;
import com.myretail.web.exception.ResourceNotfoundException;

@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService
{

    @Autowired
    ProductPriceRepository productPriceRepository;

    @Autowired
    ProductDetailsClient productDetailsClient;

    @Override
    public ProductInfo retrieveProductInfo(final long productId)
    {
        ProductInfo productInfo = new ProductInfo();
        String product = String.valueOf(productId);

        productInfo.setName(productDetailsClient.getProductTitle(product));
        productInfo.setCurrentPrice(getCurrentPrice(product));
        productInfo.setId(Long.parseLong(product));

        return productInfo;
    }

    private CurrentPrice getCurrentPrice(final String productId)
    {
        ProductPriceEntity productPricefromDB = productPriceRepository.findOne(productId);

        if (productPricefromDB == null)
        {
            throw new ResourceNotfoundException("No Product found with productId:" + productId);
        }

        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setCurrencyCode(productPricefromDB.getCurrencyCode());
        currentPrice.setValue(new BigDecimal(productPricefromDB.getValue()));

        return currentPrice;
    }

    @Override
    public void saveProductInfo(final CurrentPrice currentPrice, final long productInfo)
    {
        ProductPriceEntity pProductPriceEntity = new ProductPriceEntity(currentPrice, String.valueOf(productInfo));
        productPriceRepository.insert(pProductPriceEntity);
    }

    @Override
    public void updateProductInfo(final CurrentPrice currentPrice, final long productInfo)
    {
        ProductPriceEntity pProductPriceEntity = new ProductPriceEntity(currentPrice, String.valueOf(productInfo));
        productPriceRepository.save(pProductPriceEntity);
    }
}
