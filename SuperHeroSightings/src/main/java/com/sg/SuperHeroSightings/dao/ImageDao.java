
package com.sg.SuperHeroSightings.dao;

import org.springframework.web.multipart.MultipartFile;


public interface ImageDao {
    public String saveImage(MultipartFile file, String fileName, String directory);
    
    public String updateImage(MultipartFile file, String fileName, String directory);
    
    public boolean deleteImage(String fileName);
}
