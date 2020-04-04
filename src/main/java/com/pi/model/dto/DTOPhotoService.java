package com.pi.model.dto;

import com.pi.model.PhotoService;

public class DTOPhotoService {

    private String name;
    private Integer price;

    public DTOPhotoService(PhotoService photoService) {
        this.name = photoService.getName();
        this.price = photoService.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
