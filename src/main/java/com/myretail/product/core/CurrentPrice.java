
package com.myretail.product.core;

import java.math.BigDecimal;

public class CurrentPrice
{
    private BigDecimal value;

    private String currencyCode;

    public CurrentPrice(final BigDecimal value, final String currencyCode)
    {
        this.value = value;
        this.currencyCode = currencyCode;
    }

    public CurrentPrice()
    {
    }

    public BigDecimal getValue()
    {
        return value;
    }

    public void setValue(final BigDecimal value)
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

}
