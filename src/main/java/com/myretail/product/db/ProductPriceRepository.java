
package com.myretail.product.db;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductPriceRepository extends MongoRepository<ProductPriceEntity, String>
{

}
