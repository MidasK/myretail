
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
@RequestMapping(value = "/products")
public class ProductInfoController
{

    //TODO : try adding logger, output to a log file somewhere in server, that you can see

    @Autowired
    public ProductInfoService productInfoService;

    //TODO : make URL better
    /*
     * MyRetail.Product.GetProductInformation
     * This endpoint
     *
     */
    @RequestMapping(value = "/productInfo/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ProductInfoDTO getProductInformation(@PathVariable final long id)
    {
        ProductInfo productInfo = productInfoService.retrieveProductInfo(id);
        ProductInfoDTO productInfoDTO = new ProductInfoDTO(productInfo);

        return productInfoDTO;
    }

    //TODO : make URL better
    @RequestMapping(value = "/productInfo", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createProductInformation(@RequestBody final ProductInfoDTO productInfoDTO)
    {
        CurrentPrice currentPrice = new CurrentPrice(productInfoDTO.getCurrentPrice().getValue(), productInfoDTO.getCurrentPrice().getCurrencyCode());
        productInfoService.saveProductInfo(currentPrice, productInfoDTO.getId());

        return String.valueOf(productInfoDTO.getId());
    }

    /*
     *
     *
     */
    @RequestMapping(value = "/productInfo/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateProductInformation(@RequestBody final ProductInfoDTO productInfoDTO)
    {
        CurrentPrice currentPrice = new CurrentPrice(productInfoDTO.getCurrentPrice().getValue(), productInfoDTO.getCurrentPrice().getCurrencyCode());
        productInfoService.updateProductInfo(currentPrice, productInfoDTO.getId());

        return String.valueOf(productInfoDTO.getId());
    }

}
