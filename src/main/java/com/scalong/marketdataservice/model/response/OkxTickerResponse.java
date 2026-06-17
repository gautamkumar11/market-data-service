package com.scalong.marketdataservice.model.response;

import lombok.Data;

import java.util.List;

@Data
public class OkxTickerResponse {

    private String code;
    private String msg;
    private List<Ticker> data;


    @Data
    public static class Ticker {

        private String instId;

        // last traded price
        private String last;

        // opening price 24 hours ago
        private String open24h;

        // 24h trading volume
        private String volCcy24h;
    }
}
