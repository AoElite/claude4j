package com.aoelite.claude.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Set;

public class ImageUtils {

    public static BufferedImage createImageFromBase64(String json) {
        byte[] bytes = Base64.getDecoder().decode(json);
        try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertToBase64(BufferedImage image, String mimeType) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(image, mimeType.substring(6), out);
            byte[] bytes = out.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final static Set<String> VALID_IMAGES = Set.of(
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/webp"
    );

    public static boolean invalidImageType(String mimeType) {
        return mimeType == null || !VALID_IMAGES.contains(mimeType);
    }

    public static String convertToBase64(File file) {
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null || !VALID_IMAGES.contains(mimeType))
            throw new IllegalArgumentException("Unsupported image type: " + mimeType);
        try {
            BufferedImage image = ImageIO.read(file);
            return convertToBase64(image, mimeType);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read image file", e);
        }
    }

    public static String getMimeType(File file) {
        return URLConnection.guessContentTypeFromName(file.getName());
    }

}
