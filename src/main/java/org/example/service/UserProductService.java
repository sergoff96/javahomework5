package org.example.service;

import org.example.entity.UserProduct;
import org.example.repository.UserProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProductService {

    private final UserProductRepository userProductRepository;

    @Autowired
    public UserProductService(UserProductRepository userProductRepository) {
        this.userProductRepository = userProductRepository;
    }

    public List<UserProduct> getAllProductsByUserId(Long userId) {
        return userProductRepository.findAllById(userId);
    }

    public Optional<UserProduct> getProductById(Long id) {
        return userProductRepository.findById(id);
    }
}
