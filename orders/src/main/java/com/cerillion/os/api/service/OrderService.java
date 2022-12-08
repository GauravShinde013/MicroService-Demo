package com.cerillion.os.api.service;

import com.cerillion.os.api.common.Payment;
import com.cerillion.os.api.common.TransactionRequest;
import com.cerillion.os.api.common.TransactionResponse;
import com.cerillion.os.api.entity.Order;
import com.cerillion.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest request){
        String response = "";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        //do a rest call to payment and pass order id
        Payment paymentResponse = restTemplate.postForObject("http://localhost:9191/payment/doPayment",payment, Payment.class);
        response = paymentResponse.getPaymentStatus().equals("success")?"Payment Successful, Order Placed!":"Payment Fail, Order added to cart";
        repository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);

    }
}
