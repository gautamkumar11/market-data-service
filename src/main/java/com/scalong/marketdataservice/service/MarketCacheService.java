package com.scalong.marketdataservice.service;

import com.scalong.marketdataservice.model.response.MarketResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/***
 *  I could have added an abstraction layer here by creating Service interface but avoided for now
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MarketCacheService {

    private final MarketService marketService;

    private final AtomicReference<List<MarketResponse>> marketCache =
            new AtomicReference<>(List.of());


    @Scheduled(fixedRate = 5000)
    public void refreshMarketData() {

        try {
            List<MarketResponse> latestMarkets =
                    marketService.getTopMarkets();

            marketCache.set(latestMarkets);

            log.info(
                    "Market cache refreshed. Total records: {}",
                    latestMarkets.size()
            );

        } catch (Exception e) {

            log.error(
                    "Failed to refresh market data",
                    e
            );
        }
    }

    public List<MarketResponse> getMarkets() {
        return marketCache.get();
    }

    @PostConstruct
    public void initialize() {
        refreshMarketData();
    }
}