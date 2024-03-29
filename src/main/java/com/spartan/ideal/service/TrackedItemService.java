package com.spartan.ideal.service;

import com.spartan.ideal.model.Product;
import com.spartan.ideal.model.TrackedItem;
import com.spartan.ideal.model.User;
import com.spartan.ideal.repository.ProductRepository;
import com.spartan.ideal.repository.TrackedItemRepository;
import com.spartan.ideal.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TrackedItemService {

    private final ProductRepository productRepository;

    private final TrackedItemRepository trackedItemRepository;

    private final UserRepository userRepository;

    public TrackedItemService(ProductRepository productRepository, TrackedItemRepository trackedItemRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.trackedItemRepository = trackedItemRepository;
        this.userRepository = userRepository;
    }

    public void trackItem(Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        TrackedItem trackedItem = new TrackedItem();
        trackedItem.setProduct(product);
        trackedItem.setUser(user);
        trackedItem.setPrice(product.getPrice());

        trackedItemRepository.save(trackedItem);
    }

    public void untrackProduct(Long trackedItemId) {
        trackedItemRepository.deleteById(trackedItemId);
    }
}


