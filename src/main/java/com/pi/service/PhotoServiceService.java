package com.pi.service;

import com.pi.model.PhotoService;
import com.pi.repository.PhotoServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Сервис упраления услугами
 */
@Service
public class PhotoServiceService {

    private final PhotoServiceRepo photoServiceRepo;

    @Autowired
    public PhotoServiceService(PhotoServiceRepo photoServiceRepo) {
        this.photoServiceRepo = photoServiceRepo;
    }

    /**
     * Получить услугу по id
     *
     * @param id id услуги
     * @return услугу
     */
    public PhotoService getById(Integer id) {
        return photoServiceRepo.getOne(id);
    }

    /**
     * получить весь список услуг
     *
     * @return коллекция услуг
     */
    public Collection<PhotoService> getAllPhotoService() {
        return photoServiceRepo.findAll();
    }
}
