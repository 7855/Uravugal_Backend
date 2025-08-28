package com.uravugal.matrimony.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uravugal.matrimony.dtos.ResultResponse;
import com.uravugal.matrimony.enums.ResponseStatus;
import com.uravugal.matrimony.services.GalleryService;

@RestController
@RequestMapping("/gallery")
public class GalleryController {
    
    @Autowired
    private GalleryService galleryService;

    @GetMapping("/getAllImagesByUserId/{encodedId}")
    public ResultResponse getAllImagesByUserId(@PathVariable String encodedId) {
        ResultResponse response = new ResultResponse();
        try {
            
            response = galleryService.getAllImagesByUserId(encodedId);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Something Went Wrong. " + e.getMessage());
            response.setStatus(ResponseStatus.FAILURE);
        }
        return response;
    }

    @GetMapping("/changeImageActiveStatus/{galleryId}")
    public ResultResponse changeImageActiveStatus(@PathVariable Long galleryId) {
        ResultResponse response = new ResultResponse();
        try {
            response = galleryService.changeImageActiveStatus(galleryId);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Something Went Wrong. " + e.getMessage());
            response.setStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
