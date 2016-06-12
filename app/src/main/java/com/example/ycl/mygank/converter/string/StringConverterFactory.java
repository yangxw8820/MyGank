package com.example.ycl.mygank.converter.string;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by ycl on 16/6/7.
 */

public class StringConverterFactory extends Converter.Factory {

    private static StringConverterFactory factory;
    public static StringConverterFactory create(){
        if (factory == null){
            factory = new StringConverterFactory();
        }
        return factory;
    }

    private StringConverterFactory() {
        super();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new StringResponseBody();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new StringRequestBody();
    }
}
