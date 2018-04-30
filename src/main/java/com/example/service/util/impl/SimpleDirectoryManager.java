package com.example.service.util.impl;

import com.example.service.util.DirectoryManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by araksgyulumyan
 * Date - 4/28/18
 * Time - 12:31 AM
 */
@Component
public class SimpleDirectoryManager implements DirectoryManager {

    @Value("${destinationPath}")
    private String destinationPath;

    // Public methods overrides
    @Override
    public File getUserPhotoUploadFolder(final Long userId) {
        assertUserId(userId);
        final String uploadDirectoryPath = String.format("%s/users/%d", destinationPath, userId);
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

    // Utility methods
    private static void assertUserId(final Long userId) {
        Assert.notNull(userId, "User id should not be null");
    }
}
