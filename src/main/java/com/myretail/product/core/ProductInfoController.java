
package com.myretail.product.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/productInfo")
public class ProductInfoController
{

    @Autowired
    public ProductInfoService productInfoService;

    /*
     * Aggregates product data for a product from multiple sources and returns it as JSON to the caller
     *
     * @param id : the id of the product.
     */
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ProductInfoDTO getProductInformation(@PathVariable final long id)
    {
        ProductInfo productInfo;
        productInfo = productInfoService.retrieveProductInfo(id);

        ProductInfoDTO productInfoDTO = new ProductInfoDTO(productInfo);

        return productInfoDTO;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createProductInformation(@RequestBody final ProductInfoDTO productInfoDTO)
    {
        CurrentPrice currentPrice = new CurrentPrice(productInfoDTO.getCurrentPrice().getValue(), productInfoDTO.getCurrentPrice().getCurrencyCode());
        productInfoService.saveProductInfo(currentPrice, productInfoDTO.getId());

        return String.valueOf(productInfoDTO.getId());
    }

    /*
     * takes a JSON request body as input, and updates the product’s price in the data store.
     *
     * @param id : the id of the product you want to update.
     */
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public String updateProductInformation(@RequestBody final ProductInfoDTO productInfoDTO)
    {
        CurrentPrice currentPrice = new CurrentPrice(productInfoDTO.getCurrentPrice().getValue(), productInfoDTO.getCurrentPrice().getCurrencyCode());
        productInfoService.updateProductInfo(currentPrice, productInfoDTO.getId());

        return String.valueOf(productInfoDTO.getId());
    }

}
