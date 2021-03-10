package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.Repository.OutputProductRepository;
import uz.pdp.warehouseproject.Repository.OutputRepository;
import uz.pdp.warehouseproject.Repository.ProductRepository;
import uz.pdp.warehouseproject.entity.Output;
import uz.pdp.warehouseproject.entity.OutputProduct;
import uz.pdp.warehouseproject.entity.Product;
import uz.pdp.warehouseproject.payload.OutputProductDto;
import uz.pdp.warehouseproject.payload.Result;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ProductRepository productRepository;

    public Result add(OutputProductDto outputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());

        if (!optionalOutput.isPresent() || !optionalProduct.isPresent())
            return new Result("Error", false);
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new Result("Successfully added", true);
    }

    public Page<OutputProduct> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<OutputProduct> all = outputProductRepository.findAll(pageable);
        return all;
    }

    public List<OutputProduct> getByOutputId(Integer outputId) {
        Optional<Output> optionalOutput = outputRepository.findById(outputId);
        if (!optionalOutput.isPresent()) return null;

        List<OutputProduct> allByOutput_id = outputProductRepository.findAllByOutput_Id(outputId);
        return allByOutput_id;
    }

    public List<OutputProduct> getByProductId(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) return null;
        List<OutputProduct> allByProduct_id = outputProductRepository.findAllByProduct_Id(productId);
        return allByProduct_id;
    }

    public Result delete(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) return new Result("OutputProduct Not found", false);
        outputProductRepository.deleteById(id);
        return new Result("Successfully deleted", true);
    }

    public Result edit(Integer id, OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new Result("OutputProduct not found", false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent() || !optionalOutput.isPresent())
            return new Result("Error", false);

        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProductRepository.save(outputProduct);
        return new Result("Successfully edited", true);


    }
}
