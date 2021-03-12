package com.javainuse.requests;

public class AvatarReqBody {

    private String avatar;

    public AvatarReqBody() {
        super();
    }

    public AvatarReqBody(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "AvatarReqBody{" +
                "avatar='" + avatar + '\'' +
                '}';
    }
}
