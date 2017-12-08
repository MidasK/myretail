
package com.myretail.product.core;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentPriceDTO
{

    @JsonProperty
    private BigDecimal value;

    //TODO IF POSSIBLE : convert currency_code to currencyCode, while using some annotation to keep "currency_code" in JSON somehow.
    @JsonProperty("currency_code")
    private String currencyCode;

    public CurrentPriceDTO()
    {

    }

    public CurrentPriceDTO(final CurrentPrice currentPrice)
    {
        this.value = currentPrice.getValue();
        this.currencyCode = currentPrice.getCurrencyCode();
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
