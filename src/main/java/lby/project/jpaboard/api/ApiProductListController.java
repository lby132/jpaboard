package lby.project.jpaboard.api;

import lby.project.jpaboard.domain.Product;
import lby.project.jpaboard.dto.ProductDto;
import lby.project.jpaboard.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.ListUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apiProduct")
public class ApiProductListController {

    @Autowired
    private final ProductService productService;

   // @GetMapping("/getProductList")
    public Map<String, Object> getProducts() {
        final List<ProductDto> productList = productService.getProductList();
        Map<String, Object> result = new HashMap<>();

        if (ListUtils.isEmpty(productList)) {
            result.put("result", "상품이 비어있습니다.");
        } else {
            result.put("result", HttpStatus.OK);
            result.put("productList", productList);
        }
        return result;
    }

    //@PostMapping("/saveProduct")
    public void saveProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
    }

   // @GetMapping("/getProductOne/{productId}")
    public ProductDto getProductOne(@PathVariable("productId") Long productId) {
         return productService.getProductOne(productId);
    }

    @GetMapping("/getProductCnt/{itemId}")  //사용중
    public Map<String, Object> getProductCnt(@PathVariable("itemId") Long itemId) {
        Map<String, Object> result = new HashMap<>();
        final Product product = productService.findOne(itemId);
        final int productCnt = product.getProductCnt();

        if (productCnt > 0) {
            result.put("result", "OK");
            result.put("cnt", productCnt);
        } else {
            result.put("cnt", "");
        }

        return result;
    }
}
