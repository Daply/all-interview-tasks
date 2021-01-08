package com.hackerrank.stocktrades.controller;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.service.StockTradeService;
import com.hackerrank.stocktrades.validation.StockType;
import com.hackerrank.stocktrades.validation.StockTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trades")
public class StockTradeRestController {

    @Autowired
    private StockTradeService stockTradeService;

    @GetMapping
    public ResponseEntity<List<StockTrade>> getTrades(@RequestParam(required = false) String type,
                                      @RequestParam(required = false) Integer userId) {
        return new ResponseEntity<>(stockTradeService.getTrades(type, userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StockTrade> createTrade(@RequestBody StockTrade trade,
                                                  BindingResult result) {
        return new ResponseEntity<>(stockTradeService.createTrade(trade), HttpStatus.CREATED);
    }

    @GetMapping("/{tradeId}")
    public ResponseEntity<StockTrade> getTrade(@PathVariable Integer tradeId) {
        StockTrade trade = stockTradeService.getTrade(tradeId);
        if (trade != null)
            return new ResponseEntity<>(trade, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}