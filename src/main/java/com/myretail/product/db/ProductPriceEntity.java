
package com.myretail.product.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.myretail.product.core.CurrentPrice;

@Document
public class ProductPriceEntity
{
    @Id
    private String id;

    private String value;

    private String currencyCode;

    public String getId()
    {
        return id;
    }

    public void setId(final String productId)
    {
        this.id = productId;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(final String value)
    {
        this.value = value;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void setCurrencyCode(final String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public ProductPriceEntity()
    {
    }

    public ProductPriceEntity(final String value, final String currencyCode)
    {
        super();
        this.value = value;
        this.currencyCode = currencyCode;
    }

    public ProductPriceEntity(final CurrentPrice currentPrice, final String id)
    {
        this.id = id;
        this.value = currentPrice.getValue().toString();
        this.currencyCode = currentPrice.getCurrencyCode();
    }
}
