package com.scalong.marketdataservice.controller;

import com.scalong.marketdataservice.model.response.MarketResponse;
import com.scalong.marketdataservice.service.MarketCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MarketController {

    private final MarketCacheService marketCacheService;

    @GetMapping("/api/markets")
    public List<MarketResponse> getMarkets() {
        return marketCacheService.getMarkets();
    }
}
