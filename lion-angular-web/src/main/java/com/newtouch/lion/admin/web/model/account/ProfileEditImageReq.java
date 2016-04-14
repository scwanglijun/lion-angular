package com.newtouch.lion.admin.web.model.account;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2016/4/8.
 */
public class ProfileEditImageReq {
    //图片路径
    private String icon;
    //图片
    private MultipartFile imagefile;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public MultipartFile getImagefile() {
        return imagefile;
    }

    public void setImagefile(MultipartFile imagefile) {
        this.imagefile = imagefile;
    }
}
