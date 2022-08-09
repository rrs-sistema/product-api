package br.com.rrssistema.microserviceproductapi.modules.product.controller;

import br.com.rrssistema.microserviceproductapi.config.exception.SuccessResponse;
import br.com.rrssistema.microserviceproductapi.modules.product.dto.ProductCheckStockRequest;
import br.com.rrssistema.microserviceproductapi.modules.product.dto.ProductRequest;
import br.com.rrssistema.microserviceproductapi.modules.product.dto.ProductResponse;
import br.com.rrssistema.microserviceproductapi.modules.product.dto.ProductSalesResponse;
import br.com.rrssistema.microserviceproductapi.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    private ProductResponse save(@RequestBody ProductRequest request) {
        return productService.save(request);
    }

    @PutMapping("{id}")
    public ProductResponse update(@RequestBody ProductRequest request, @PathVariable Integer id) {
        return productService.update(request, id);
    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable Integer id) {
        return productService.delete(id);
    }

    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findByAll();
    }

    @GetMapping("/all")
    public List<ProductResponse> getAll() {
        return productService.findByAll();
    }

    @GetMapping("/name/{name}")
    public List<ProductResponse> findByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping("{id}")
    public ProductResponse findById(@PathVariable Integer id) {
        return productService.findByIdResponse(id);
    }

    @GetMapping("/one/{id}")
    public ProductResponse findByOne(@PathVariable Integer id) {
        return productService.findByIdResponse(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductResponse> findByCategoryId(@PathVariable Integer categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<ProductResponse> findBySupplierId(@PathVariable Integer supplierId) {
        return productService.findBySupplierId(supplierId);
    }

    @PostMapping("check-stock")
    public SuccessResponse checkProductsStock(@RequestBody ProductCheckStockRequest request) {
        return productService.checkProductsStock(request);
    }

    @GetMapping("{id}/{sales}")
    public ProductSalesResponse findProductSales(@PathVariable Integer id) {
        return productService.findProductSales(id);
    }


}
