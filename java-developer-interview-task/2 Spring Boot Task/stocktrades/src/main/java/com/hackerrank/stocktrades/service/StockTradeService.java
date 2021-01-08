package com.hackerrank.stocktrades.service;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import com.hackerrank.stocktrades.validation.StockType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockTradeService {

    @Autowired
    private StockTradeRepository stockTradeRepository;

    public List<StockTrade> getTrades(String type, Integer userId) {
        return stockTradeRepository.getTradesSortedByIds(type, userId);
    }

    public StockTrade createTrade(StockTrade trade) {
        return stockTradeRepository.save(trade);
    }

    public StockTrade getTrade(Integer tradeId) {
        return stockTradeRepository.findById(tradeId).orElse(null);
    }

}
