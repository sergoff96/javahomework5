package org.example.payment.service;

import org.example.payment.dao.PaymentDAO;
import org.example.payment.entity.Payment;
import org.example.product.entity.UserProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final PaymentDAO paymentDAO;
    private final RestTemplate restTemplate;

    @Autowired
    public PaymentService(PaymentDAO paymentDAO, RestTemplate restTemplate) {
        this.paymentDAO = paymentDAO;
        this.restTemplate = restTemplate;
    }

    public void processPayment(Long productId, BigDecimal amount) {
        // Call User Product Service to validate product and balance
        ResponseEntity<UserProduct> response = restTemplate.getForEntity(
                "http://localhost:8080/api/products/getProduct?id=" + productId,
                UserProduct.class
        );

        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new IllegalArgumentException("Product not found");
        }

        UserProduct product = response.getBody();

        if (product == null || product.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        // Deduct balance and save payment
        product.setBalance(product.getBalance().subtract(amount));
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setProductId(productId);
        payment.setStatus("COMPLETED");
        paymentDAO.savePayment(payment);
    }
}
