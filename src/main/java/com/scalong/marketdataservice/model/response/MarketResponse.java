package com.scalong.marketdataservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MarketResponse {

    private String symbol;

    private String lastPrice;

    private String change24hPercent;

    private String volume24h;
}
