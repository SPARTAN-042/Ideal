package com.spartan.ideal.model;

import jakarta.persistence.*;

@Entity
public class TrackedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackedItemId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getTrackedItemId() {
        return trackedItemId;
    }

    public void setTrackedItemId(Long trackedItemId) {
        this.trackedItemId = trackedItemId;
    }
}
