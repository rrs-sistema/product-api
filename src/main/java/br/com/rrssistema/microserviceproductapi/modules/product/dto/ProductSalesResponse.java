package br.com.rrssistema.microserviceproductapi.modules.product.dto;

import br.com.rrssistema.microserviceproductapi.modules.category.dto.CategoryResponse;
import br.com.rrssistema.microserviceproductapi.modules.product.model.Product;
import br.com.rrssistema.microserviceproductapi.modules.supplier.dto.SupplierResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSalesResponse {

    private Integer id;
    private String name;
    private String description;
    @JsonProperty("quantity_available")
    private Integer quantityAvailable;
    private BigDecimal price;
    @JsonProperty("img_url")
    private String imgUrl;
    @JsonProperty("created_at")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    private SupplierResponse supplier;
    private CategoryResponse category;
    private List<String> sales;

    public static ProductSalesResponse of(Product product, List<String> sales) {
        return ProductSalesResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .quantityAvailable(product.getQuantityAvailable())
                .price(product.getPrice())
                .imgUrl(product.getImgUrl())
                .createdAt(product.getCreatedAt())
                .supplier(SupplierResponse.of(product.getSupplier()))
                .category(CategoryResponse.of(product.getCategory()))
                .sales(sales)
                .build();
    }

}
