package by.dzmitry.yarashevich.controllers;

import by.dzmitry.yarashevich.models.Assortment;
import by.dzmitry.yarashevich.models.Product;
import by.dzmitry.yarashevich.models.SaleRequest;
import by.dzmitry.yarashevich.repositories.SaleRepository;
import by.dzmitry.yarashevich.services.AssortmentService;
import by.dzmitry.yarashevich.services.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SaleController {

    SaleService saleService;
    SaleRepository saleRepository;
    AssortmentService assortmentService;

    public SaleController(SaleService saleService, SaleRepository saleRepository, AssortmentService assortmentService) {
        this.saleService = saleService;
        this.saleRepository = saleRepository;
        this.assortmentService = assortmentService;
    }

    @GetMapping("/collectionSale")
    public String productList(Model model, Assortment assortment) {
        Assortment[] assortmentList = assortmentService.getAllAssortment().toArray(new Assortment[0]);
        model.addAttribute("assortmentList", assortmentList);
        model.addAttribute("assortment", assortment);
        return "collectionSale";
    }

    @PostMapping("/collectionSale")
    public String futureProductsList(@ModelAttribute SaleRequest saleRequest, RedirectAttributes redirectAttributes) {
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < saleRequest.getExbarBody().length; i++) {
            if(saleRequest.getQuantity()[i] != null) {
                Product product = assortmentService.getAssortmentForProduct(saleRequest.getExbarBody()[i],
                        saleRequest.getQuantity()[i],
                        saleRequest.getIsDiscountProvided()[i]);
                products.add(product);
            }
        }
        String message = saleService.processSale(products, saleRequest.getCashPrice(), saleRequest.getCardPrice(), redirectAttributes);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/sale";
    }

    @PostMapping("/sale")
    public String createSales(@ModelAttribute("products") ArrayList<Product> products,
                              @ModelAttribute("cashPrice") BigDecimal cashPrice,
                              @ModelAttribute("cardPrice") BigDecimal cardPrice,
                              RedirectAttributes redirectAttributes) {

        String message  = saleService.processSale(products, cashPrice, cardPrice, redirectAttributes);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/sale";
    }

    @GetMapping("/sale")
    public String thankYouPage(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("message", message);
        return "sale";
    }

    @GetMapping("/sales/{salesTime}")
    public ResponseEntity<String> deleteSalesByTime(@PathVariable LocalDateTime salesTime) {
        saleService.deleteBySalesTime(salesTime);
        return ResponseEntity.ok("Sales deleted successfully by time: " + salesTime);
    }
}