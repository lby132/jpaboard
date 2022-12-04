package lby.project.jpaboard.controller;

import lby.project.jpaboard.domain.Product;
import lby.project.jpaboard.form.ProductForm;
import lby.project.jpaboard.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping("/productList")
    public String getProducts(Model model) {
        final List<Product> productList = productService.findItems();

        model.addAttribute("productList", productList);
        return "product/productList";
    }

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

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        final Product item = productService.findOne(id);

        final ProductForm product = ProductForm.builder()
                .productId(item.getId())
                .productName(item.getProductName())
                .productCnt(item.getProductCnt())
                .price(item.getPrice())
                .build();

        model.addAttribute("form", product);
        model.addAttribute("cnt", item.getProductCnt());

        return "product/updateProductForm";
    }

    @PostMapping("/{id}/edit")
    public String editSave(@PathVariable("id") Long prodId, @ModelAttribute("form") ProductForm form) {

        productService.updateProduct(prodId, form.getProductName(), form.getProductCnt(), form.getPrice());

        return "redirect:/product/productList";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long prodId) {

        productService.deleteProduct(prodId);

        return "redirect:/product/productList";
    }
}
