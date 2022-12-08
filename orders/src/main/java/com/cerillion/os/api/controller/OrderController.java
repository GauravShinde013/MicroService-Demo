package com.cerillion.os.api.controller;

import com.cerillion.os.api.common.TransactionRequest;
import com.cerillion.os.api.common.TransactionResponse;
import com.cerillion.os.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){
        return service.saveOrder(request);
    }
}
