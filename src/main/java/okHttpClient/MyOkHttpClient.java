package okHttpClient;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * @Author: Small Bear
 * @Description:测试okHttpClent
 * @Data:Create in 19:16 2017/10/24
 * @Modified By:Small Bear
 */
public class MyOkHttpClient {
    /**
     * 处理get请求
     */
    public void doGet(){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url("url").build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现post请求
     */
    public void doPost(){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody body=new FormBody.Builder()
                .add("key","value")
                .add("key","value")
                .build();
        Request request=new Request.Builder().post(body).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现文件上传(混合参数和文件请求)
     */
    private void upload(){
        OkHttpClient client=new OkHttpClient();
        //创建要上传的文件
        File file=new File("");
        String fileName=file.getName();
        RequestBody fileBody=RequestBody.create(MultipartBody.FORM,file);
        //可以实现多文件上传
        MultipartBody mBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                /* 底下是上传了两个文件 */
                .addFormDataPart("file" , fileName , fileBody)
                .addFormDataPart("name","zhangsan")
                .build();
        Request request=new Request.Builder().url("").post(mBody).build();
        try {
            Response response=client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
