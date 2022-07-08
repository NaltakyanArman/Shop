package com.darknet.controller;

import com.darknet.model.BlackMarket;
import com.darknet.model.User;
import com.darknet.repository.BlackMarketRepo;
import com.darknet.service.BlackMarketService;
import com.darknet.service.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Slf4j
@Controller
public class BlackMarketController {


    private final BlackMarketService blackMarketService;
    private final BlackMarketRepo blackMarketRepo;

    public BlackMarketController(BlackMarketService blackMarketService, BlackMarketRepo blackMarketRepo) {
        this.blackMarketService = blackMarketService;
        this.blackMarketRepo = blackMarketRepo;
    }

    @GetMapping(value = "/register")
    public String registerPage() {
        return "regPage";
    }

    @GetMapping("/view/{id}")
    public String getList(@PathVariable Long id, ModelMap modelMap) {
        modelMap.addAttribute("itemInfo", blackMarketRepo.findById(id).get());
        return "singleitem";
    }

    @GetMapping("/singleitem")
    public String singleItemPage() {
        return "singleitem";
    }


    @PostMapping("/register")
    public String saveItem(BlackMarket blackMarket) {

        blackMarketService.saveBlackMarket(blackMarket);
        return "redirect:/itemslist";

    }

    @PostMapping("/saveItem")
    public String editBlackMarket(BlackMarket blackMarket
    ) {

        blackMarketService.saveBlackMarket(blackMarket);
        return "redirect:/itemslist";
    }

    @GetMapping(value = "/editpage/{id}")
    public String editingPage(@PathVariable Long id, ModelMap modelMap) {

        Optional<BlackMarket> byId = blackMarketRepo.findById(id);
        if (byId.isPresent()) {

            BlackMarket blackMarket = byId.get();
            modelMap.addAttribute("item", blackMarket);

            return "editpage";
        }

        return "redirect:/itemslist";

    }

    @GetMapping("/delete/{id}")
    public String deleteBlackMarket(@PathVariable Long id) {
        blackMarketService.deleteBlackMarket(id);
        return "redirect:/itemslist";
    }

    @GetMapping("/itemslist")
    public String getList(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {

        if (currentUser != null) {
            User user = currentUser.getUser();
            modelMap.addAttribute("user1", user);
        }

        modelMap.addAttribute("items", blackMarketRepo.findAll());
        return "itemslist";
    }

}
