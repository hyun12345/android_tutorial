package org.techtown.mylist;

public class SingerItem {

    int resId;
    String name;
    String mobile;

    public SingerItem(int resId, String name, String mobile) {
        this.resId = resId;
        this.name = name;
        this.mobile = mobile;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    //문자열로 배열 값 출력
    @Override
    public String toString() {
        return "SingerItem{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
