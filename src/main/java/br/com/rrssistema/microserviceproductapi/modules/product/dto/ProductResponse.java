package br.com.rrssistema.microserviceproductapi.modules.product.dto;

import br.com.rrssistema.microserviceproductapi.modules.category.model.Category;
import br.com.rrssistema.microserviceproductapi.modules.product.model.Product;
import br.com.rrssistema.microserviceproductapi.modules.supplier.model.Supplier;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Integer id;
    private String name;
    private String description;
    private Supplier supplier;
    private Category category;
    @JsonProperty("quantity_available")
    private Integer quantityAvailable;
    private BigDecimal price;
    @JsonProperty("img_url")
    private String imgUrl;
    @JsonProperty("created_at")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    public static ProductResponse of(Product product) {
        return ProductResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .quantityAvailable(product.getQuantityAvailable())
                .price(product.getPrice())
                .imgUrl(product.getImgUrl())
                .createdAt(product.getCreatedAt())
                .supplier(product.getSupplier())
                .category(product.getCategory())
                .build();
    }
}
