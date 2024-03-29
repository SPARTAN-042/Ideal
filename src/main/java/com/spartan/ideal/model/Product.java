package com.spartan.ideal.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String productName;
    private String price;
    private String imgUrl;
    private String productLink;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<TrackedItem> trackedItems;

    @ManyToOne
    @JoinColumn(name = "website_id")
    private Website website;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public List<TrackedItem> getTrackedItems() {
        return trackedItems;
    }

    public void setTrackedItems(List<TrackedItem> trackedItems) {
        this.trackedItems = trackedItems;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }
}
