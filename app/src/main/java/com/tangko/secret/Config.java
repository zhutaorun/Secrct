package com.tangko.secret;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/7/11.
 */
public class Config {

    //public static final String SERVER_URL = "http://dome.eoeschool.com/api/v1/nimings/io";
    public static final String SERVER_URL = "http://192.168.10.53:8080/Test/api.jsp?";

    public static final String KEY_TOKEN = "token";
    public static final String KEY_ACTION = "action";
    public static final String KEY_PHONE_NUM = "phone";
    public static final String KEY_PHONE_MD5 = "phone_md5";
    public static final String KEY_STATUS ="status";
    public static final String KEY_CODE = "code";
    public static final String KEY_CONTACTS = "contacts";
    public static final String KEY_PAGE = "page";
    public static final String KEY_PERPAGE = "perpage";
    public static final String KEY_TIMELINE = "timeline" ;
    public static final String KEY_MSG_ID = "msgId";
    public static final String KEY_MSG = "msg";
    public static final String KEY_COMMENTS = "comment";
    public static final String KEY_CONTENT = "content";

    public static final int RESULT_STATUS_SUCCESS=1;
    public static final int RESULT_STATUS_FAIL =0;
    public static final int RESULT_STATUS_INVALID_TOKEN =2;

    public static final String APP_ID ="com.tangko.secret";
    public static final String CHARSET = "utf-8";

    public static final String ACTION_GET_CODE = "send_pass";
    public static final String ACTION_UPLOAD_CONTACTS = "upload_contacts";
    public static final String ACTION_LOGIN = "login";
    public static final String ACTION_TIMELINE = "timeline";
    public static final String ACTION_GET_COMMENT ="get_comment" ;
    public static final String ACTION_PUB_COMMENT = "pub_comment" ;
    public static final String ACTION_PUBLISH = "publish";

    public static final int ACTIVITY_RESULT_NEED_REFEESH = 10000;

    public static String getCachedToken(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_TOKEN,null);
    }

    public static void cacheToken(Context context,String token){
        SharedPreferences.Editor e =context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN,token);
        e.commit();
    }

    public static String getCachedPhoneNum(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_PHONE_NUM,null);
    }

    public static void cachePhoneNum(Context context,String phoneNum){
        SharedPreferences.Editor e =context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        e.putString(KEY_PHONE_NUM,phoneNum);
        e.commit();
    }
}
