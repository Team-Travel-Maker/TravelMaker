package com.app.travelmaker.controller.information;

import com.app.travelmaker.service.cs.CustomServiceFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cs/files/*")
public class CustomServiceFileApiController {

    private final CustomServiceFileService customServiceFileService;

    //    파일 업로드
    @PostMapping("upload")
    @ResponseBody
    public List<String> upload(@RequestParam("uploadFile") List<MultipartFile> uploadFiles) throws IOException {
        String path = "C:/upload/" + getPath();
        List<String> uuids = new ArrayList<>();
        File file = new File(path);
        //경로가 존재하지 않으면 그 상위 폴더까지 만들어준다. mkdir 와 mkdirs의 차이
        if(!file.exists()){file.mkdirs();}

        // input file 태그에 다중 파일 반복 돌려서 저장
        for (int i=0; i<uploadFiles.size(); i++){
            uuids.add(UUID.randomUUID().toString());
            uploadFiles.get(i).transferTo(new File(path, uuids.get(i) + "_" + uploadFiles.get(i).getOriginalFilename()));
        }

        // js 에서 submit을 통해 uuids 배열을 ajax 결과로 반환하여 사용한다.
        return uuids;
    }

    public String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }


//        파일 다운로드
    @GetMapping("download/{id}")
    public ResponseEntity<Resource> download(@PathVariable(required = true) Long id) throws UnsupportedEncodingException {
        AtomicReference<String> fileName = new AtomicReference<>("");

        customServiceFileService.findById(id).ifPresent(file -> {
            fileName.set(file.getFilePath() + "/" + file.getFileUuid() + "_" + file.getFileName());
        });

        // 파일 시스템 기준
        Resource resource = new FileSystemResource("C:/upload/" + fileName);
        // header에 정보를 담아준다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + new String(fileName.get().substring(fileName.get().indexOf("_") + 1).getBytes("UTF-8"), "ISO-8859-1"));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}
