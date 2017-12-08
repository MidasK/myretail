
package com.myretail.product.core;

public interface ProductInfoService
{

    ProductInfo retrieveProductInfo(long productId);

    void saveProductInfo(CurrentPrice currentPrice, final long productInfo);

    void updateProductInfo(CurrentPrice currentPrice, long productInfo);

}
