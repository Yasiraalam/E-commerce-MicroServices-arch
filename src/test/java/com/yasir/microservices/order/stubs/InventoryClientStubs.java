package com.yasir.microservices.order.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStubs {
    public static void stubInventoryCall(String skuCode, Integer quantity){
        stubFor(get(urlEqualTo("/api/inventory?skucode=" + skuCode + "&quantity=" + quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("content-Type", "application/json")
                        .withBody("true")));

    }
}