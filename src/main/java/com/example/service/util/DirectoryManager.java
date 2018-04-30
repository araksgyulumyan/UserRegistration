package com.example.service.util;

import java.io.File;

/**
 * Created by araksgyulumyan
 * Date - 4/28/18
 * Time - 12:29 AM
 */
public interface DirectoryManager {

    /**
     * Returns photo upload folder for the user with the provided id
     *
     * @param userId
     * @return folder file
     */
    File getUserPhotoUploadFolder(final Long userId);

    /**
     * Creates file with provided name in the given directory
     *
     * @param name
     * @param parentDirectory
     * @return File
     */
    File createFile(final String name, final File parentDirectory);
}
