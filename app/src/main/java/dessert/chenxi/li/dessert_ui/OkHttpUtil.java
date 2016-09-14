package dessert.chenxi.li.dessert_ui;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 李天烨 on 2016/8/16.
 */

public class OkHttpUtil {
    public static boolean result = false;
    public static String weatherJSON, loginStr="";
    public static OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    /**
     * Post键值对
     */

    public static boolean LoginPostParams(String url, final String account, final String password) {
        RequestBody body = new FormBody.Builder().add("username", account)
                                                .add("password", password).build();

        Request request = new Request.Builder().url(url).post(body).build();
        Log.i("request",request.toString());
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                loginStr = response.body().string();
                if (response.isSuccessful()) {
                    Log.i("200", "httpGet OK: " + account+","+password +","+ response.toString());
                    Log.i("body", loginStr);
                    setResult(true);
                } else {
                    Log.i("!200", "httpGet error: " + account+","+password +","+ response.toString());
                    Log.i("body", loginStr);
                    setResult(false);
                }
            }
        });
        return result;
    }

    public static boolean postMoreParams(String url, final String account, final String devID,
                                                     final String temp, final String hum, final String air) {
        RequestBody body = new FormBody.Builder().add("username", account)
                                                 .add("devID", devID)
                                                 .add("temp", temp)
                                                 .add("hum", hum)
                                                 .add("air", air)
                                                 .build();
        Request request = new Request.Builder().url(url).post(body).build();
        Log.i("request",request.toString());
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    setResult(true);
                    Log.i("200", "httpGet OK: "+response.toString());
                    Log.i("body", response.body().string());
                } else {
                    setResult(false);
                    Log.i("!200", "httpGet error: " + response.toString());
                    Log.i("body", response.body().string());
                }
            }
        });
        return result;
    }

    /**
     * POST提交Json数据
     *
     * @param url
     */
    public static void postJson(String url) {
        Log.i("点击确认", "点了");
        String json =  "{\"username\":\"0000\",\"devID\":\"001\",\"temp\":\"32\",\"hum\":\"30\",\"air\":\"150\",\"elec\":\"50\",\"pic\":\"2333\"}";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = client.newCall(request);
        Log.i("request",request.toString());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String r = response.body().string();
                if (response.isSuccessful()) {
                    Log.i("200", "httpGet1 OK: " + r);
                } else {
                    Log.i("!200", "httpGet1 error: " + r);
                }
            }
        });
    }

    public static boolean getResult(){
        return result;
    }

    public static void setResult(boolean num){
        result = num;
    }

    public static String weatherGet() throws IOException{
        String url = " http://api.yytianqi.com/forecast7d?city=CH070101&key=w5ersf4nbd17ajhf";
        Request request = new Request.Builder()
                    .url(url)
                    .build();
        Log.i("request", request.toString());

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    weatherJSON = response.body().string();
                    Log.i("Weather", "httpGet OK: " + response.toString());
                    Log.i("body", response.body().string());
                } else {
                    weatherJSON = response.body().string();
                    Log.i("Weather", "httpGet error: " + response.toString());
                    Log.i("body", response.body().string());

                }
            }
        });

        return weatherJSON;
    }



}
