package org.example.product.controller;

import org.example.product.service.UserProductService;
import org.example.product.entity.UserProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class UserProductController {

    private final UserProductService userProductService;

    @Autowired
    public UserProductController(UserProductService userProductService) {
        this.userProductService = userProductService;
    }

    @GetMapping("/{id}/getAllProducts")
    public ResponseEntity<List<UserProduct>> getAllProducts(@PathVariable Long id) {
        List<UserProduct> products = userProductService.getAllProductsByUserId(id);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getAllProductsByUserId")
    public ResponseEntity<List<UserProduct>> getAllProductsByUserId(@RequestParam Long userId) {
        List<UserProduct> products = userProductService.getAllProductsByUserId(userId);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getProduct")
    public ResponseEntity<UserProduct> getProduct(@RequestParam Long id) {
        return userProductService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
