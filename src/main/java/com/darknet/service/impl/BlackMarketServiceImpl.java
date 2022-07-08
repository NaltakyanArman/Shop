package com.darknet.service.impl;

import com.darknet.model.BlackMarket;
import com.darknet.repository.BlackMarketRepo;
import com.darknet.service.BlackMarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlackMarketServiceImpl implements BlackMarketService {
    private final BlackMarketRepo blackMarketRepo;

    @Override
    public void saveBlackMarket(BlackMarket blackMarket)
    {
        blackMarketRepo.save(blackMarket);
    }

    @Override
    public void deleteBlackMarket(Long id)
    {
        blackMarketRepo.deleteById(id);
    }

    @Override
    public void updateBook(BlackMarket blackMarket) {
        blackMarketRepo.save(blackMarket);
    }
}
