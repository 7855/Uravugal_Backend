package com.uravugal.matrimony.services;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uravugal.matrimony.dtos.ResultResponse;
import com.uravugal.matrimony.enums.ActiveStatus;
import com.uravugal.matrimony.enums.ResponseStatus;
import com.uravugal.matrimony.models.GalleryEntity;
import com.uravugal.matrimony.repositories.GalleryRepository;

@Service
public class GalleryService {
    
    @Autowired
    private GalleryRepository galleryRepository;

    public ResultResponse getAllImagesByUserId(String encodedId) {
        ResultResponse response = new ResultResponse();
        try {
            String decodedId = new String(Base64.getDecoder().decode(encodedId));
            Long userId = Long.parseLong(decodedId);
            // System.out.println("gyegyeeeeeeeeeg "+userId);
            List<GalleryEntity> images = galleryRepository.findByUserIdAndIsActive(userId, ActiveStatus.Y);
            
            response.setCode(200);
            response.setStatus(ResponseStatus.SUCCESS);     
            response.setMessage("Images fetched successfully.");
            response.setData(images);
        } catch (Exception e) {
            response.setCode(500);
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage("Error fetching images: " + e.getMessage());
        }
        return response;
    }

    public ResultResponse changeImageActiveStatus(Long galleryId) {
        ResultResponse response = new ResultResponse();
        try {
            GalleryEntity image = galleryRepository.findByGalleryIdAndIsActive(galleryId, ActiveStatus.Y);
            if (image != null) {
                image.setIsActive(ActiveStatus.N);
                galleryRepository.save(image);
                
                response.setCode(200);
                response.setStatus(ResponseStatus.SUCCESS);
                response.setMessage("Image status updated successfully.");
            } else {
                response.setCode(404);
                response.setStatus(ResponseStatus.FAILURE);
                response.setMessage("Image not found or already inactive.");
            }
        } catch (Exception e) {
            response.setCode(500);
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage("Error updating image status: " + e.getMessage());
        }
        return response;
    }
}
