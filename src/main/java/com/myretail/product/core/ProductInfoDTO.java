
package com.myretail.product.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductInfoDTO
{

    @JsonProperty("productId")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("current_price")
    private CurrentPriceDTO currentPrice;

    public ProductInfoDTO()
    {

    }

    public ProductInfoDTO(final ProductInfo productInfo)
    {
        this.id = productInfo.getId();
        this.name = productInfo.getName();
        this.currentPrice = new CurrentPriceDTO(productInfo.getCurrentPrice());

    }

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public CurrentPriceDTO getCurrentPrice()
    {
        return currentPrice;
    }

    public void setCurrentPrice(final CurrentPriceDTO currentPrice)
    {
        this.currentPrice = currentPrice;
    }

}
