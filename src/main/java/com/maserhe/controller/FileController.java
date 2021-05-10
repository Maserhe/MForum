package com.maserhe.controller;

import com.maserhe.util.FileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 描述:
 * 文件上传
 *
 * @author Maserhe
 * @create 2021-04-04 22:03
 */
@Controller
public class FileController {

    @Autowired
    private FileClient fileClient;

    @PostMapping("/postFrom")
    public String fileUpload(String title, String article, @RequestParam(value = "uploadImage")  MultipartFile uploadImage) {

        System.out.println(title);
        System.out.println(article);
        System.out.println(uploadImage);

        String s = null;
        try {
            s = fileClient.uploadFile(uploadImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(s);
        return "redirect:/index.html";
    }

}
