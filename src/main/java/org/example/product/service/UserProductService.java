package org.example.product.service;

import org.example.product.dao.UserProductDAO;
import org.example.product.entity.UserProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProductService {

    private final UserProductDAO userProductDAO;

    @Autowired
    public UserProductService(UserProductDAO userProductDAO) {
        this.userProductDAO = userProductDAO;
    }

    public List<UserProduct> getAllProductsByUserId(Long userId) {
        return userProductDAO.getAllProductsByUserId(userId);
    }

    public Optional<UserProduct> getProductById(Long id) {
        return userProductDAO.getProductById(id);
    }
}
