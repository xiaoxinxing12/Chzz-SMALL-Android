package org.chzz.app.main.engine;

import android.content.Context;
import android.util.Log;

import org.chzz.app.main.utlis.GsonTools;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by copy on 2016/4/25.
 */
public class CookiesInterceptor implements Interceptor {
    private Context context;

    public CookiesInterceptor(Context context) {
        this.context = context;
    }
    //重写拦截方法，处理自定义的Cookies信息
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request compressedRequest = request.newBuilder()
                .header("cookie", "")
                .build();
        Response response = chain.proceed(compressedRequest);
       // CookieUtil.saveCookies(response.headers(), context);
       // Log.i("header",request.body()+"");
        Log.i("header",request.url()+"");
        Log.i("header", response.body()+"");

        return response;
    }
}