package com.darknet.service;

import com.darknet.model.BlackMarket;

public interface BlackMarketService {
    void saveBlackMarket(BlackMarket blackMarket);
    void deleteBlackMarket(Long id);
    void updateBook(BlackMarket blackMarket);

}
