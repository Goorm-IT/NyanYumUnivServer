

package com.nyanyumserver.nyuimg.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.nyanyumserver.nyuimg.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;


import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("ImageService")
public class ImageServiceImpl implements ImageService {
    private final AmazonS3 amazonS3;
    private final String ID_PREFIX = "/";
    private final String FILE_EXTENSION_SEPARATOR = ".";
    private final List<String> FILE_EXTENSION_ALLOW =  Arrays.asList(".bmp", ".jpeg", ".jpg", ".png", ".tif", ".tiff", ".svg");

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private void validateFileExists(MultipartFile multipartFile) {
        if (multipartFile == null) {
            throw new IllegalArgumentException("The upload file is empty"); // Image Not Exist
        }
    }

    private String buildFileName(String id, String option, String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        Optional<String> anyExtension = FILE_EXTENSION_ALLOW.stream()
                .filter(s -> s.equals(fileExtension)).findAny();
        if (anyExtension.isEmpty()) {
            throw new IllegalArgumentException("The file's extension is invalid"); // file extension invalid
        }
        String fileName = originalFileName.substring(0, fileExtensionIndex);

        return option + ID_PREFIX + id + ID_PREFIX + fileName + fileExtension;
    }

    // Comparing id with bucket images
    private String buildObjectKey(String id, String option) {
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucket);
        List<S3ObjectSummary> objects = result.getObjectSummaries(); // Objects List
        for (S3ObjectSummary os : objects) {
            String object = os.getKey();
            String imageSep[] = object.split(ID_PREFIX);
            if (imageSep.length > 1 && imageSep[0].equals(option) && imageSep[1].equals(id)) {
                return object;
            }
        }
        return null;
    }

    public String uploadImage(String id, String option, MultipartFile multipartFile) {
        validateFileExists(multipartFile);

        String fileName = buildFileName(id, option, multipartFile.getOriginalFilename());

        if (buildObjectKey(id, option) != null){ // file exist -> delete
            deleteImage(id, option);
        }

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return downloadImage(id, option);

        } catch (IOException e) { // converting file error
            e.printStackTrace();
            throw new IllegalArgumentException(String.format("Converting File Error (uid : %s)", multipartFile.getOriginalFilename()));
        }
    }

    public String downloadImage(String id, String option) {
        String resourcePath = amazonS3.getUrl(bucket, buildObjectKey(id, option)).toString();
        if (buildObjectKey(id, option) == null) {
            throw new NullPointerException(String.format("Not Found Image in %s (id : %s)", option, id));
        }
        return resourcePath;
    }

    public void deleteImage(String id, String option) {
        String deleteObjectKey = buildObjectKey(id, option);
        if (deleteObjectKey == null) {
            throw new NullPointerException(String.format("Delete Request Rejected For Not Found Image in %s (id : %s)", option, id));
        }
        amazonS3.deleteObject(bucket, deleteObjectKey);
    }
}