package com.aoelite.claude.data.input;

import com.aoelite.claude.utils.ImageUtils;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.io.File;

@Getter
public class ImageMessage implements ClaudeMessage {

    private final String base64;
    private final String mediaType;
    //
    public ImageMessage(File file) {
        this.base64 = ImageUtils.convertToBase64(file);
        this.mediaType = ImageUtils.getMimeType(file);
        if (ImageUtils.invalidImageType(mediaType)) throw new RuntimeException("Invalid image type: " + mediaType);
    }

    @Override
    public JsonObject toJsonObject() {
        //
        JsonObject image = new JsonObject();
        image.addProperty("type", "image");
        //
        JsonObject source = new JsonObject();
        source.addProperty("type", "base64");
        source.addProperty("media_type", mediaType);
        source.addProperty("data", base64);
        image.add("source", source);
        //
        return image;
    }




}
