package lby.project.jpaboard.service;

import lby.project.jpaboard.dto.ProductDto;
import lby.project.jpaboard.entity.Product;
import lby.project.jpaboard.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public List<ProductDto> getProductList() {
        final List<Product> productList = productRepository.findAll();
        return productList.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public void saveProduct(ProductDto productDto) {
        final Product product = Product.builder()
                .product_name(productDto.getProductName())
                .product_cnt(productDto.getProductCnt())
                .build();

        productRepository.save(product);
    }

    @Override
    public ProductDto getProductOne(Long prodId) {
        final Product product = productRepository.findById(prodId)
                .orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(product, ProductDto.class);
    }


}
