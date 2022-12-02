package lby.project.jpaboard.controller;

import lby.project.jpaboard.domain.Product;
import lby.project.jpaboard.form.ProductForm;
import lby.project.jpaboard.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping("/productReg")
    public String productForm(Model model) {
        model.addAttribute("form", new ProductForm());
        return "product/productReg";
    }

    @PostMapping("/productReg")
    public String productReg(ProductForm form) {
        final Product product = Product.builder()
                .productName(form.getProductName())
                .productCnt(form.getProductCnt())
                .price(form.getPrice())
                .build();

        productService.saveItem(product);

        return "product/proChooseView";
    }

    @GetMapping("/productList")
    public String productList() {
        return "product/productList";
    }
}
