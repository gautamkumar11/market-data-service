package com.scalong.marketdataservice.service;

import com.scalong.marketdataservice.client.OkxClient;
import com.scalong.marketdataservice.model.response.MarketResponse;
import com.scalong.marketdataservice.model.response.OkxTickerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;


/***
 *  I could have added an abstraction layer here by creating Service interface but avoided for now
 */
@Service
@RequiredArgsConstructor
public class MarketService {
    private final OkxClient okxClient;

    public List<MarketResponse> getTopMarkets() {

        OkxTickerResponse response =
                okxClient.getTickers("SPOT");

        return response.getData()
                .stream()
                .sorted(
                        Comparator.comparing(
                                this::getVolume
                        ).reversed()
                )
                .limit(20)
                .map(this::convert)
                .toList();
    }


    private MarketResponse convert(
            OkxTickerResponse.Ticker ticker) {

        return MarketResponse.builder()
                .symbol(ticker.getInstId())
                .lastPrice(ticker.getLast())
                .change24hPercent(
                        calculateChange(
                                ticker.getLast(),
                                ticker.getOpen24h()
                        )
                )
                .volume24h(ticker.getVolCcy24h())
                .build();
    }


    private BigDecimal getVolume(
            OkxTickerResponse.Ticker ticker) {

        return new BigDecimal(
                ticker.getVolCcy24h()
        );
    }


    private String calculateChange(
            String last,
            String open) {

        BigDecimal lastPrice =
                new BigDecimal(last);

        BigDecimal openPrice =
                new BigDecimal(open);

        BigDecimal change =
                lastPrice
                        .subtract(openPrice)
                        .multiply(BigDecimal.valueOf(100))
                        .divide(
                                openPrice,
                                2,
                                RoundingMode.HALF_UP
                        );

        return change + "%";
    }
}
