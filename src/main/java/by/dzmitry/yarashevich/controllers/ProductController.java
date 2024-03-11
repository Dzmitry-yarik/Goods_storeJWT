package by.dzmitry.yarashevich.controllers;

import by.dzmitry.yarashevich.models.Product;
import by.dzmitry.yarashevich.repositories.AssortmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    AssortmentRepository assortmentRepository;

    public ProductController(AssortmentRepository assortmentRepository) {
        this.assortmentRepository = assortmentRepository;
    }

    @GetMapping("/product")
    public String showProduct(Model model) {
        Product product = (Product) model.getAttribute("product");
        if (product == null) {
            return "redirect:/admin/calculatePrice";
        }
        model.addAttribute("product", product);
        return "product";
    }
}