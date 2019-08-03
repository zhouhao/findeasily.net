package net.findeasily.website.config;


import java.util.Set;

import org.springframework.http.MediaType;

import com.google.common.collect.Sets;

public final class Constant {

    private Constant() {
    }

    public static final Set<String> IMAGE_CONTENT_TYPE = Sets.newHashSet(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE);

    public static final String ORIGIN_IMAGE_PREFIX = "origin_";

}
