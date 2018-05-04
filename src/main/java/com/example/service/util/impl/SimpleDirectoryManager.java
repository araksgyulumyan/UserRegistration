package com.example.service.util.impl;

import com.example.service.util.DirectoryManager;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Created by araksgyulumyan
 * Date - 4/28/18
 * Time - 12:31 AM
 */
@Component
public class SimpleDirectoryManager implements DirectoryManager {

    @Value("${photoPath}")
    private String photoPath;

    private static final String ENCODING_CHARSET = "UTF-8";

    // Public methods overrides
    @Override
    public File getUserPhotoUploadFolder(final Long userId) {
        assertUserId(userId);
        final String uploadDirectoryPath = String.format("%s/users/%d", photoPath, userId);
        final File uploadFolder = new File(uploadDirectoryPath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdir();
        }
        return uploadFolder;
    }

    @Override
    public File createFile(final String name, final File parentFolder) {
        final File newFile = new File(parentFolder.getAbsolutePath() + File.separator + name);
        Path path = Paths.get(parentFolder.getAbsolutePath() + File.separator + name);

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            newFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newFile;
    }

    @Override
    public String getUserPhoto(Long userId) {
        final File file = new File(String.format("%s/users/%d/profile.jpg", photoPath, userId));
        try {
            final byte[] bytes = FileUtils.readFileToByteArray(file);
            return new String(Base64.getEncoder().encode(bytes), ENCODING_CHARSET);
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }

    // Utility methods
    private static void assertUserId(final Long userId) {
        Assert.notNull(userId, "User id should not be null");
    }
}
