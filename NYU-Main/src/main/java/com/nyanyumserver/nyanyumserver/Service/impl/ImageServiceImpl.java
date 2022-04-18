package com.nyanyumserver.nyanyumserver.Service.impl;

import com.nyanyumserver.nyanyumserver.Service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service("ImageService")
public class ImageServiceImpl implements ImageService {

    @Value("${img.host}")
    private String imgHost;
    @Value("${img.port}")
    private String imgPort;

    HttpHeaders headers = new HttpHeaders();
    MultiValueMap<String, Object> body  = new LinkedMultiValueMap<>();
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

    public String updateImage(MultipartFile file, String id, String option){
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        body.add("file", file.getResource());
        String serverUrl = String.format("http://%s:%s/updateImage?id=%s&option=%s", imgHost, imgPort, id, option);
        ResponseEntity<String> response = new RestTemplate().postForEntity(serverUrl, requestEntity, String.class);
        String resBody = response.getBody();
        String tag[] = resBody.split("\""); // Extract file uri from responseEntity
        return tag[3];
    }

    public void deleteImage(String id, String option){
        String serverUrl = String.format("http://%s:%s/deleteImage?id=%s&option=%s", imgHost, imgPort, id, option);
        new RestTemplate().delete(serverUrl);
    }
}
