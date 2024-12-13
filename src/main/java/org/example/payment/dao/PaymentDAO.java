package org.example.payment.dao;

import org.example.payment.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PaymentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void savePayment(Payment payment) {
        String query = "INSERT INTO payment (amount, product_id, status) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, payment.getAmount(), payment.getProductId(), payment.getStatus());
    }

    public List<Payment> getPaymentsByProductId(Long productId) {
        String query = "SELECT * FROM payment WHERE product_id = ?";
        return jdbcTemplate.query(query, new Object[]{productId}, (rs, rowNum) -> {
            Payment payment = new Payment();
            payment.setId(rs.getLong("id"));
            payment.setAmount(rs.getBigDecimal("amount"));
            payment.setProductId(rs.getLong("product_id"));
            payment.setStatus(rs.getString("status"));
            return payment;
        });
    }
}