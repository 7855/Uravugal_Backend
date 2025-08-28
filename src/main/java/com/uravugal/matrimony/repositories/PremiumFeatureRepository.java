// src/main/java/com/uravugal/matrimony/repositories/PremiumFeatureRepository.java
package com.uravugal.matrimony.repositories;

import com.uravugal.matrimony.models.PremiumFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PremiumFeatureRepository extends JpaRepository<PremiumFeature, Long> {
    List<PremiumFeature> findByIsActiveTrueOrderByDisplayOrderAsc();
}