package br.com.rrssistema.microserviceproductapi.modules.product.dto;

import br.com.rrssistema.microserviceproductapi.modules.product.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String name;
    private String description;
    @JsonProperty("quantity_available")
    private Integer quantityAvailable;
    private BigDecimal price;
    @JsonProperty("img_url")
    private String imgUrl;
    private Integer supplierId;
    private Integer categoryId;

    public static ProductRequest of(Product Product) {
        var request = new ProductRequest();
        BeanUtils.copyProperties(Product, request);
        return request;
    }
}
