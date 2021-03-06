package com.red.redmovie.utils;

import android.os.Handler;
import android.os.Looper;
import android.service.carrier.CarrierMessagingService;
import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.framed.FrameReader;

/**
 * Created by Red on 2016/10/20.
 */

public class OkHttpUtils {
    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    private OkHttpUtils() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        mDelivery = new Handler(Looper.getMainLooper());
    }

    private synchronized static OkHttpUtils getInstance() {
         if(mInstance==null) {
             mInstance = new OkHttpUtils();
         }
        return mInstance;
    }

    private void getRequest(String url, final ResultCallback callback) {
        final Request request = new Request.Builder().url(url).build();
        deliveryResult(callback, request);
    }

    private void postRequest(String url, final ResultCallback callback, List<Param> params) {
        final Request request = buildPostRequest(url, params);
        deliveryResult(callback, request);
    }

    private Request buildPostRequest(String url, List<Param> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for(Param param: params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder().url(url).post(requestBody).build();
    }

    private void deliveryResult(final ResultCallback callback, Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailCallback(callback, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String str = response.body().string();
                    if(callback.mType == String.class) {
                        sendSuccessCallback(callback, str);
                    }else {
                        Object object = JsonUtils.deserialize(str, callback.mType);
                        sendSuccessCallback(callback, object);
                    }
                }catch (final Exception e) {
                    sendFailCallback(callback, e);
                }
            }
        });
    }

    private void sendSuccessCallback(final ResultCallback callback, final Object object) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(object);
                }
            }
        });
    }

    private void sendFailCallback(final ResultCallback callback, final Exception e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }

    public static void get(String url, ResultCallback callback) {
        getInstance().getRequest(url, callback);
    }

    public static void post(String url, final ResultCallback callback, List<Param> params) {
        getInstance().postRequest(url, callback, params);
    }

    public static abstract class ResultCallback<T> {
        Type mType;
        public ResultCallback() {
            mType = getSuperClassTypeParameter(getClass());
        }

        static Type getSuperClassTypeParameter(Class<?> subClass) {
            Type superClass = subClass.getGenericSuperclass();
            if (superClass instanceof Class) {
                throw new RuntimeException("Missing type paramtere.");
            }
            ParameterizedType paramterized = (ParameterizedType) superClass;
            return $Gson$Types.canonicalize(paramterized.getActualTypeArguments()[0]);
        }
        public abstract void onSuccess(T response);
        public abstract void onFailure(Exception e);
    }

    public static class Param {
        String key;
        String value;
        public Param(){}
        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
