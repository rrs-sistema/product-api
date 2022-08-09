package br.com.rrssistema.microserviceproductapi.config.interceptor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PublicEndPoints {

    FIND_PRODUCT_ALL("/api/product"),
    FIND_PRODUCT_ONE("/api/product/{id}"),
    FIND_PRODUCT_NAME("/api/product/name/{name}"),
    FIND_PRODUCT_CATEGORY("/api/product/category/{categoryId}"),
    FIND_PRODUCT_SUPPLIER_ALL("/api/product/supplier/{supplierId}"),

    FIND_CATEGORY_ALL("/api/category"),
    FIND_CATEGORY_NAME("/api/category/description/{description}"),

    FIND_SUPPLIER_ALL("/api/supplier"),
    FIND_SUPPLIER_NAME("/api/supplier/name/{name}");

    private final String publicEndPoint;
}
