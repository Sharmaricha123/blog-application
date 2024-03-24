/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blog.serviceImpl;

import com.blog.services.FileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Asus
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

//        file name
        String name = file.getOriginalFilename();

        String randomID = UUID.randomUUID().toString();
        String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

//        fullpath
        String filePath = path + File.separator + fileName1;

//        create folder if not created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name;

    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;

    }

}
