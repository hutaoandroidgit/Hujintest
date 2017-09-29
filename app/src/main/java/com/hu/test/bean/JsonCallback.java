package com.hu.test.bean;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by TT on 2017/9/6.
 */
public abstract class  JsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;



    @Override
    public T convertResponse(Response response) throws Throwable {


        //详细自定义的原理和文档，看这里： https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback
        ResponseBody body = response.body();
        if(body == null){
            return null;
        }

        T data = null;
        Gson gson = new Gson();

        JsonReader jsonReader = new JsonReader(body.charStream());

        Type genType = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];

        data = gson.fromJson(jsonReader, type);
        return data;
    }
}
