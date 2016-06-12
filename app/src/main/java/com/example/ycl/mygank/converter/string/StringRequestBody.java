package com.example.ycl.mygank.converter.string;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by ycl on 16/6/7.
 */

public class StringRequestBody implements Converter<String, RequestBody> {

    public static final MediaType MEDIA_TYPE = MediaType.parse("Accept: application/json");
    @Override
    public RequestBody convert(String value) throws IOException {
        return RequestBody.create(MEDIA_TYPE, value);
    }
}
