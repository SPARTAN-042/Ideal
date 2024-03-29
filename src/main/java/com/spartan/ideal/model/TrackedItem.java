package com.spartan.ideal.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tracked_item")
public class TrackedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trackedItemId")
    private Long trackedItemId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private LocalDateTime lastUpdated;

    public Long getTrackedItemId() {
        return trackedItemId;
    }

    public void setTrackedItemId(Long trackedItemId) {
        this.trackedItemId = trackedItemId;
    }

    public void setPrice(String price) {
    }

    public void setProduct(Product product) {
    }

    public void setUser(User user) {
    }
}
