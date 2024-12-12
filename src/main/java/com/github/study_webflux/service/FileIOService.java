package com.github.study_webflux.service;


import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

@Service
public class FileIOService {

    private static final String UPLOAD_DIR = "uploads";

    public Mono<Void> uploadFile(final FilePart filePart) {
        var filePath = Path.of(UPLOAD_DIR, filePart.filename());

        return filePart.transferTo(filePath)
                .then(Mono.empty());
    }

    public Flux<DataBuffer> downloadFile(final String fileName) {
        Path filePath = Path.of(UPLOAD_DIR, fileName);

        try {
            return DataBufferUtils.read(filePath,
                    new DefaultDataBufferFactory(),
                    1024
            );
        } catch (Exception e) {
            return Flux.error(e);
        }
    }
}
