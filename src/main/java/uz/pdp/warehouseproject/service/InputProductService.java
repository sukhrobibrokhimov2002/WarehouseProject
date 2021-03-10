package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.Repository.InputProductRepository;
import uz.pdp.warehouseproject.Repository.InputRepository;
import uz.pdp.warehouseproject.Repository.ProductRepository;
import uz.pdp.warehouseproject.entity.Input;
import uz.pdp.warehouseproject.entity.InputProduct;
import uz.pdp.warehouseproject.entity.Product;
import uz.pdp.warehouseproject.payload.InputProductDto;
import uz.pdp.warehouseproject.payload.Result;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    public Result add(InputProductDto inputProductDto) {

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        Optional<Product> productOptional = productRepository.findById(inputProductDto.getProductId());

        if (!optionalInput.isPresent() || !productOptional.isPresent()) return new Result("Error", false);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(productOptional.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new Result("Successfully added", true);
    }

    public Page<InputProduct> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<InputProduct> all = inputProductRepository.findAll(pageable);
        return all;
    }

    public List<InputProduct> getByInput(Integer inputId) {
        Optional<Input> inputOptional = inputRepository.findById(inputId);
        if (!inputOptional.isPresent()) return null;
        List<InputProduct> allByInput_id = inputProductRepository.findAllByInput_Id(inputId);
        return allByInput_id;
    }

    public List<InputProduct> getByProduct(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) return null;
        List<InputProduct> allByProduct_id = inputProductRepository.findAllByProduct_Id(productId);
        return allByProduct_id;

    }

    public Result delete(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) return new Result("input product not found", false);
        inputProductRepository.deleteById(id);
        return new Result("Successfully deleted", true);

    }

    public Result edit(Integer id, InputProductDto inputProductDto) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()) return new Result("Input product not found", false);

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        Optional<Product> productOptional = productRepository.findById(inputProductDto.getProductId());
        if (!optionalInput.isPresent() || !productOptional.isPresent()) return new Result("Error", false);

        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setProduct(productOptional.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new Result("Successfully edited", true);


    }

}
