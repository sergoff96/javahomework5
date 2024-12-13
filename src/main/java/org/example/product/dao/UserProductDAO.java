package org.example.product.dao;

import org.example.product.entity.UserProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserProductDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserProduct> getAllProductsByUserId(Long userId) {
        String query = "SELECT * FROM user_product WHERE id = ?";
        return jdbcTemplate.query(query, new Object[]{userId}, (rs, rowNum) -> {
            UserProduct product = new UserProduct();
            product.setId(rs.getLong("id"));
            product.setAccountNumber(rs.getString("account_number"));
            product.setBalance(rs.getBigDecimal("balance"));
            product.setProductType(rs.getString("product_type"));
            return product;
        });
    }

    public Optional<UserProduct> getProductById(Long id) {
        String query = "SELECT * FROM user_product WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(UserProduct.class)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
