package lby.project.jpaboard.service.impl;

import lby.project.jpaboard.dto.ProductDto;
import lby.project.jpaboard.domain.Product;
import lby.project.jpaboard.repository.ProductRepository;
import lby.project.jpaboard.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

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
                .productName(productDto.getProductName())
                .productCnt(productDto.getProductCnt())
                .price(productDto.getPrice())
                .build();

        productRepository.save(product);
    }

    @Override
    public ProductDto getProductOne(Long prodId) {
        final Product product = productRepository.findById(prodId)
                .orElseThrow(() -> new IllegalArgumentException("상품 아이디가 없습니다."));
        return modelMapper.map(product, ProductDto.class);
    }


}
