package br.com.rrssistema.microserviceproductapi.config.interceptor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PublicEndPoints {

    FIND_PRODUCT_ALL("/api/product"),
    FIND_PRODUCT_ONE("/api/product/one/"),
    FIND_PRODUCT_ONE_ALL("/api/product/one/*"),
    FIND_PRODUCT_NAME("/api/product/name/"),
    FIND_PRODUCT_NAME_ALL("/api/product/name/*"),
    FIND_PRODUCT_CATEGORY("/api/product/category/"),
    FIND_PRODUCT_CATEGORY_ALL("/api/product/category/*"),
    FIND_PRODUCT_SUPPLIER("/api/product/supplier/"),
    FIND_PRODUCT_SUPPLIER_ALL("/api/product/supplier/*"),

    FIND_CATEGORY_ALL("/api/category"),
    FIND_CATEGORY_ONE("/api/category/one"),
    FIND_CATEGORY_NAME("/api/category/description"),

    FIND_SUPPLIER_ALL("/api/supplier"),
    FIND_SUPPLIER_ONE("/api/supplier/one"),
    FIND_SUPPLIER_NAME("/api/supplier/name");

    private final String publicEndPoint;
}
