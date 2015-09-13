package com.tangko.secret.net;

import android.os.AsyncTask;

import com.tangko.secret.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2015/7/11.
 */
public class NetConnection {
    public NetConnection(final String url, final HttpMethod method,final SuccessCallback successCallback, final FailCallback failCallback,final String ...kvs){

        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... params) {
                StringBuffer paramsStr = new StringBuffer();
                for(int i=0;i<kvs.length;i+=2){
                    paramsStr.append(kvs[i]).append("-").append(kvs[i+1]).append("&");
                }
                URLConnection uc = null;
                try {


                    switch (method){
                        case POST:
                            uc = new URL(url).openConnection();
                            uc.setDoOutput(true);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
                            bw.write(paramsStr.toString());
                            bw.flush();
                            break;
                        case GET:
                            break;
                        default:
                            uc = new URL(url+"?"+paramsStr.toString()).openConnection();
                            break;
                    }
                    System.out.println("Request url:" +uc.getURL());
                    System.out.println("Request data:" +paramsStr);
                    BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(),Config.CHARSET));
                    String line= null;
                    StringBuffer result = new StringBuffer();
                    while ((line=br.readLine())!=null){
                        result.append(line);
                    }
                    System.out.println("Result:" + result);
                    return  result.toString();
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s!=null){
                    if(successCallback!=null){
                        successCallback.onSuccess(s);
                    }
                }else{
                    if(failCallback!=null){
                        failCallback.onFail();
                    }
                }
                super.onPostExecute(s);
            }
        }.execute();
    }

    public static interface SuccessCallback{
        void onSuccess(String result);
    }

    public static interface FailCallback{
        void onFail();
    }
}
