package com.uravugal.matrimony.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uravugal.matrimony.models.UserDeviceInformation;

public interface PushNotificationRepository extends JpaRepository<UserDeviceInformation, Long> {
    List<UserDeviceInformation> findAllByUserId(Long userId);
    
    Optional<UserDeviceInformation> findByUserIdAndDeviceId(Long userId, String deviceId);
    
    Optional<UserDeviceInformation> findByFcmToken(String fcmToken);
}
