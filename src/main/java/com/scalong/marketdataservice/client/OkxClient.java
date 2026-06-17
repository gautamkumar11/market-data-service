package com.scalong.marketdataservice.client;

import com.scalong.marketdataservice.model.response.OkxTickerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * I could have used the FeignConfig class separately
 * if there would have multiple 3rd part integrations,
 * also we can optimise using properties file to import the base url,
 * if time allows we can also add errorDecoder, connection timeout etc
 * */
@FeignClient(
        name = "okxClient",
        url = "${okx.base-url}"
)
public interface OkxClient {

    @GetMapping("/api/v5/market/tickers")
    OkxTickerResponse getTickers(@RequestParam("instType") String instType);
}
