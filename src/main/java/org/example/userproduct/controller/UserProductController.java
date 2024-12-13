package org.example.userproduct.controller;

import org.example.userproduct.entity.UserProduct;
import org.example.userproduct.service.UserProductService;
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

    @GetMapping("/getProduct")
    public ResponseEntity<UserProduct> getProduct(@RequestParam Long id) {
        return userProductService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
