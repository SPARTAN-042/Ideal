package com.spartan.ideal.repository;

import com.spartan.ideal.model.TrackedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackedItemRepository extends JpaRepository<TrackedItem, Long> {
}

