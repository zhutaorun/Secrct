package com.tangko.secret.atys;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.tangko.secret.Config;
import com.tangko.secret.R;
import com.tangko.secret.com.tangko.secret.ld.MyContacts;
import com.tangko.secret.net.Message;
import com.tangko.secret.net.Timeline;
import com.tangko.secret.net.UploadContacts;
import com.tangko.secret.tools.MD5Tool;

import java.util.List;

/**
 * Created by Administrator on 2015/7/11.
 */
public class AtyTimeline extends ListActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_timeline);


        adapter = new AtyTimelineMessageListAdapter(this);
        setListAdapter(adapter);
        phone_num = getIntent().getStringExtra(Config.KEY_PHONE_NUM);
        token = getIntent().getStringExtra(Config.KEY_TOKEN);
        phone_md5 = MD5Tool.md5(phone_num);

        final ProgressDialog pd = ProgressDialog.show(this,getResources().getString(R.string.connecting),getResources().getString(R.string.connecting_to_server));
        new UploadContacts(phone_md5, token, MyContacts.getContactJSONS(this), new UploadContacts.SuccessCallback() {
            @Override
            public void onSuccess() {
                loadMessage();

                pd.dismiss();
            }
        }, new UploadContacts.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                pd.dismiss();
                if(errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(AtyTimeline.this,AtyLogin.class));
                    finish();
                }else {
                    loadMessage();
                }
            }
        });
    }

    private void loadMessage(){
        //System.out.println(">>>>>LoadMessage");
        final ProgressDialog pd = ProgressDialog.show(this,getResources().getString(R.string.connecting),getResources().getString(R.string.connecting_to_server));
        new Timeline(phone_md5, token, 1, 20, new Timeline.SuccessCallback() {
            @Override
            public void onSuccess(int page, int perpage, List<Message> timeline) {
                pd.dismiss();
                adapter.clear();
                adapter.addAll(timeline);
            }
        }, new Timeline.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                pd.dismiss();
                if(errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(AtyTimeline.this,AtyLogin.class));
                    finish();
                }
                else {
                    Toast.makeText(AtyTimeline.this,R.string.fail_to_login_timeline_data,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Message msg = adapter.getItem(position);
        Intent i = new Intent(this,AtyMessage.class);
        i.putExtra(Config.KEY_MSG,msg.getMsg());
        i.putExtra(Config.KEY_MSG_ID,msg.getMsgId());
        i.putExtra(Config.KEY_PHONE_MD5,msg.getPhone_md5());
        i.putExtra(Config.KEY_TOKEN,token);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_aty_timeline,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuShowAtyPublish:
                Intent i = new Intent(AtyTimeline.this,AtyPublish.class);
                i.putExtra(Config.KEY_PHONE_MD5,phone_md5);
                i.putExtra(Config.KEY_TOKEN,token);
                startActivityForResult(i, 0);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Config.ACTIVITY_RESULT_NEED_REFEESH:
                loadMessage();
                break;
            default:
                break;
        }

    }

    private String phone_num,token,phone_md5;
    private AtyTimelineMessageListAdapter adapter;
}
