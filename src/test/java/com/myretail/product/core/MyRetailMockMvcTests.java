package com.myretail.product.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.product.db.ProductPriceEntity;
import com.myretail.product.db.ProductPriceRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyRetailMockMvcTests
{
    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper;

    private static String PRODUCT_ID = "13860428";

    private static String BAD_PRODUCT_ID = "00011111";

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Before
    public void setup() throws Exception
    {

        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        productPriceRepository.deleteAll();
    }

    @Test
    public void testProductInfoPOST() throws Exception
    {
        ProductInfoDTO dto = buildProductInfoDTO();

        String createProductInfoJson = toJson(dto);
        MvcResult result = this.mvc
            .perform(post("/products/productInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createProductInfoJson))
            .andDo(print())
            .andExpect(status().isCreated())
            .andReturn();

        long id = Long.parseLong(result.getResponse().getForwardedUrl());
        assertEquals(id, dto.getId());

        productPriceRepository.deleteAll();
    }

    @Test
    public void testProductInfoGET() throws Exception
    {

        insertProductPriceDummyRecord();

        MvcResult result = this.mvc
            .perform(get("/products/productInfo/{id}", PRODUCT_ID))
                     .andDo(print())
                     .andExpect(status().isOk())
                     .andReturn();

        MvcResult badResult = this.mvc
            .perform(get("/products/productInfo/{id}", BAD_PRODUCT_ID))
                     .andDo(print())
                     .andExpect(status().isNotFound())
                     .andReturn();


        productPriceRepository.deleteAll();
    }

    private void insertProductPriceDummyRecord()
    {

        CurrentPrice currentPrice = new CurrentPrice(new BigDecimal(22.00), "HKD");
        ProductPriceEntity pProductPriceEntity = new ProductPriceEntity(currentPrice, String.valueOf(PRODUCT_ID));
        productPriceRepository.insert(pProductPriceEntity);
    }


    private ProductInfoDTO buildProductInfoDTO()
    {
        return new ProductInfoDTO(buildProductInfo());
    }

    private ProductInfo buildProductInfo()
    {
        long productId = 13860428;
        CurrentPrice currentPrice = new CurrentPrice(new BigDecimal(19.99), "AUD");

        ProductInfo productInfoCreated = new ProductInfo();
        productInfoCreated.setCurrentPrice(currentPrice);
        productInfoCreated.setId(productId);
        productInfoCreated.setName("testCreate");

        return productInfoCreated;

    }

    public String toJson(final Object obj)
    {
        assertNotNull(obj);

        try
        {
            return objectMapper.writeValueAsString(obj);
        }
        catch (JsonProcessingException exception)
        {
            throw new IllegalArgumentException(exception);
        }
    }


}

