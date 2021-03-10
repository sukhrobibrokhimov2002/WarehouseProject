package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.Repository.AttachmentRepository;
import uz.pdp.warehouseproject.Repository.CategoryRepository;
import uz.pdp.warehouseproject.Repository.MeasurementRepository;
import uz.pdp.warehouseproject.Repository.ProductRepository;
import uz.pdp.warehouseproject.entity.Category;
import uz.pdp.warehouseproject.entity.Measurement;
import uz.pdp.warehouseproject.entity.Product;
import uz.pdp.warehouseproject.entity.attachment.Attachment;
import uz.pdp.warehouseproject.payload.ProductDto;
import uz.pdp.warehouseproject.payload.ProductInfoDto;
import uz.pdp.warehouseproject.payload.Result;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public Result add(ProductDto productDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(productDto.getPhotoId());
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());

        if (!categoryOptional.isPresent() || !attachmentOptional.isPresent() || !optionalMeasurement.isPresent())
            return new Result("Error", false);

        Product product = new Product();
        product.setCode(String.valueOf(UUID.randomUUID()));
        product.setCategory(categoryOptional.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhotoId(attachmentOptional.get());
        product.setName(productDto.getName());
        productRepository.save(product);
        return new Result("Successfully added", true);
    }


    public Page<Product> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Product> all = productRepository.findAll(pageable);
        return all;
    }

    public List<Product> getByCategoryId(Integer categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryOptional.isPresent()) return null;

        List<Product> byCategory_id = productRepository.findByCategory_Id(categoryId);
        return byCategory_id;
    }


    public Result deleteById(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) return new Result("Product not found", false);
        productRepository.deleteById(id);
        return new Result("Successfully deleted", true);
    }

    public Result editInfo(Integer id, ProductInfoDto productInfoDto) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) return new Result("Product not found", false);
        Optional<Category> categoryOptional = categoryRepository.findById(productInfoDto.getCategoryId());
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(productInfoDto.getPhotoId());
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productInfoDto.getMeasurementId());

        if (!categoryOptional.isPresent() || !attachmentOptional.isPresent() || !optionalMeasurement.isPresent())
            return new Result("Error", false);

        Product product = productOptional.get();
        product.setName(productInfoDto.getName());
        product.setMeasurement(optionalMeasurement.get());
        product.setCategory(categoryOptional.get());
        product.setPhotoId(attachmentOptional.get());
        productRepository.save(product);

        return new Result("Successfully edited", true);
    }



}
