package com.cplh.web.rest;

import com.cplh.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    private String folder = "D:/";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {

        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try (InputStream is = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream os = response.getOutputStream()) {

            response.setContentType("application/x-download");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=test.txt");
            IOUtils.copy(is, os);
            os.flush();
        }
    }
}
