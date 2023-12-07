package com.example.demo.api;

import com.example.demo.model.RecentPurchaseResponse;
import com.example.demo.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class RestApplication {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping(value = "/recent_purchases/{userName}")
    @Cacheable(value = "reservationsCache", key = "#userName")
    public Flux<RecentPurchaseResponse> get2(@PathVariable String userName) {
        return purchaseService.findRecentlyPurchase(userName);
    }

}
