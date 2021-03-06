package br.com.rrssistema.microserviceproductapi.modules.supplier.service;

import br.com.rrssistema.microserviceproductapi.config.exception.SuccessResponse;
import br.com.rrssistema.microserviceproductapi.config.exception.ValidationException;
import br.com.rrssistema.microserviceproductapi.modules.product.service.ProductService;
import br.com.rrssistema.microserviceproductapi.modules.supplier.dto.SupplierRequest;
import br.com.rrssistema.microserviceproductapi.modules.supplier.dto.SupplierResponse;
import br.com.rrssistema.microserviceproductapi.modules.supplier.model.Supplier;
import br.com.rrssistema.microserviceproductapi.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierService {
    @Autowired
    private ProductService productService;
    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierResponse findByIdResponse(Integer id) {
        return SupplierResponse.of(findById(id));
    }

    public List<SupplierResponse> findByAll() {
        return supplierRepository
                .findAll()
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    public List<SupplierResponse> findByName(String name) {
        if(isEmpty(name)) {
            throw new ValidationException("The supplier name must be informed.");
        }
        return supplierRepository
                .findByNameIgnoreCaseContaining(name)
                .stream()
                .map(SupplierResponse::of)
                //Ou//.map(category -> CategoryResponse.of(category))
                .collect(Collectors.toList());
    }

    public Supplier findById(Integer id) {
        validateInformedId(id);
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no supplier for given ID."));
    }

    public SupplierResponse save(SupplierRequest request) {
        validateSupplierNameInformed(request);
        var supplier  = supplierRepository.save(Supplier.of(request));
        return SupplierResponse.of(supplier);
    }

    public SupplierResponse update(SupplierRequest request, Integer id) {
        validateSupplierNameInformed(request);
        validateInformedId(id);
        var supplier = Supplier.of(request);
        supplier.setId(id);
        supplierRepository.save(supplier);
        return SupplierResponse.of(supplier);
    }

    public SuccessResponse delete(Integer id) {
        validateInformedId(id);
        if(productService.existsBySupplierId(id)){
            throw new ValidationException("You cannot delete this supplier because it's already defined by a product.");
        }
        supplierRepository.deleteById(id);
        return SuccessResponse.create("The supplier ID must be informed");
    }

    private void validateSupplierNameInformed(SupplierRequest request) {
        if(isEmpty(request.getName())) {
            throw new ValidationException("The supplier's name was not informed.");
        }
    }

    private void validateInformedId(Integer id) {
        if(isEmpty(id)) {
            throw new ValidationException("The supplier ID must be informed.");
        }
    }
}
