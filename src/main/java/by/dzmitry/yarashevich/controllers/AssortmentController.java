package by.dzmitry.yarashevich.controllers;

import by.dzmitry.yarashevich.models.Assortment;
import by.dzmitry.yarashevich.models.Product;
import by.dzmitry.yarashevich.services.AssortmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class AssortmentController {

    private final AssortmentService assortmentService;

    public AssortmentController(AssortmentService assortmentService) {
        this.assortmentService = assortmentService;
    }

    @GetMapping("/assortment")
    public String showAssortmentPage(Model model) {
        List<Assortment> assortmentList = assortmentService.getAllAssortment();
        model.addAttribute("assortmentList", assortmentList);
        return "assortment";
    }

    @GetMapping("/calculatePrice")
    public String calculatePricePage(Model model, Assortment assortment) {
        model.addAttribute("packType", assortment);
        return "calculatePrice";
    }

    @PostMapping("/calculatePrice")                                    //передать user_id
    public String calculatePrice(
            @RequestParam("exbarBody") String exbarBody,
            @RequestParam("quantity") BigDecimal quantity,
            @RequestParam("isDiscountProvided") Boolean isDiscountProvided,
            RedirectAttributes redirectAttributes
    ) {
        Product product = assortmentService.getAssortmentForProduct(exbarBody, quantity, isDiscountProvided);

        if (!product.equals(new Product())) {
            redirectAttributes.addFlashAttribute("product", product);
            return "redirect:/product";
        } else {
            return "redirect:/calculatePrice?error=notfound";
        }
    }
}

