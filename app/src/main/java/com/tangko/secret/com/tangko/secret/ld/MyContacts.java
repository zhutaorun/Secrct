package com.tangko.secret.com.tangko.secret.ld;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.tangko.secret.Config;
import com.tangko.secret.tools.MD5Tool;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by Administrator on 2015/7/11.
 */
public class MyContacts {

    public static String getContactJSONS(Context context){
        Cursor c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        String phoneNum;
        JSONArray jsonArr = new JSONArray();
        JSONObject jsonObj;
        while(c.moveToNext()){
            phoneNum = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if(phoneNum.charAt(0)=='+'&&phoneNum.charAt(1)=='8'&&phoneNum.charAt(2)=='6'){
                phoneNum = phoneNum.substring(3);
            }
            jsonObj = new JSONObject();
            try {
                jsonObj.put(Config.KEY_PHONE_MD5, MD5Tool.md5(phoneNum));
            }catch (Exception e){
                e.printStackTrace();
            }
            jsonArr.put(jsonObj);
            System.out.println(phoneNum);
        }
        return jsonArr.toString();
    }
}
