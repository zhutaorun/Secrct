package com.tangko.secret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.tangko.secret.atys.AtyLogin;
import com.tangko.secret.atys.AtyTimeline;
import com.tangko.secret.com.tangko.secret.ld.MyContacts;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println(MyContacts.getContactJSONS(this));

        startActivity(new Intent(this, AtyTimeline.class));

        String token = Config.getCachedToken(this);
        String phone_num = Config.getCachedPhoneNum(this);
        if(token!=null&&phone_num!=null){
            Intent i = new Intent(this, AtyTimeline.class);
            i.putExtra(Config.KEY_TOKEN,token);
            i.putExtra(Config.KEY_PHONE_NUM,phone_num);
            startActivity(i);
        }else {
            startActivity(new Intent(this, AtyLogin.class));
        }
        finish();
    }
}
