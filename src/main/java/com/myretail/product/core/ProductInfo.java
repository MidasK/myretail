
package com.myretail.product.core;

public class ProductInfo
{
    private Long id;

    private String name;

    private CurrentPrice currentPrice;

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
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

    public CurrentPrice getCurrentPrice()
    {
        return currentPrice;
    }

    public void setCurrentPrice(final CurrentPrice currentPrice)
    {
        this.currentPrice = currentPrice;
    }

}
