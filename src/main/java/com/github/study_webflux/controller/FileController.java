package com.github.study_webflux.controller;

import com.github.study_webflux.service.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileIOService fileIOService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Void> uploadFile(@RequestPart("file") final FilePart filePart) {
        return fileIOService.uploadFile(filePart);
    }

    @GetMapping(value = "/download/{file-name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Flux<DataBuffer> downloadFile(@PathVariable("file-name") final String fileName) {
        return fileIOService.downloadFile(fileName);
    }

}
