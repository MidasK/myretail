
package com.myretail.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.myretail")
public class MyRetailApplication
{

    public static void main(final String[] args)
    {
        SpringApplication.run(MyRetailApplication.class, args);
    }
}
