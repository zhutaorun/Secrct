package com.tangko.secret.net;

/**
 * Created by Administrator on 2015/7/20.
 */
public class Comment {

    public Comment(String content,String phone_md5){
        this.content = content;
        this.phone_md5 =phone_md5;

    }

    private String content,phone_md5;

    public String getPhone_md5() {
        return phone_md5;
    }

    public String getContent() {
        return content;
    }
}
