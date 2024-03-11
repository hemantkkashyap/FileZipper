package com.zip.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;

import java.io.*;
import java.util.zip.*;

@Controller
public class FileZipperController {

    @PostMapping("/zip")
    public String zipFiles(@RequestParam("fileInput") MultipartFile[] files, Model model) {
        try {
            String zipFileName = "ZippedFiles.zip";

            File zipFile = new File(zipFileName);
            try (FileOutputStream fos = new FileOutputStream(zipFile);
                 ZipOutputStream zipOut = new ZipOutputStream(fos)) {

                for (MultipartFile file : files) {
                    String fileName = file.getOriginalFilename();
                    ZipEntry zipEntry = new ZipEntry(fileName);
                    zipOut.putNextEntry(zipEntry);

                    ByteArrayOutputStream compressedStream = Huffman.compress(file.getBytes());
                    zipOut.write(compressedStream.toByteArray());


                    zipOut.closeEntry();
                }
            }

            model.addAttribute("zipFileName", zipFileName);
            return "result";

        } catch (IOException e) {
            model.addAttribute("error", "Error zipping files: " + e.getMessage());
            return "error";
        }
    }

    @SuppressWarnings("null")
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable String fileName) {
        File file = new File(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource(file));
    }

   
}

