package com.zhoulesin.arouterdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class JavaBean implements Parcelable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public JavaBean() {

    }

    public JavaBean(String name, int age) {

        this.name = name;
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    protected JavaBean(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<JavaBean> CREATOR = new Parcelable.Creator<JavaBean>() {
        @Override
        public JavaBean createFromParcel(Parcel source) {
            return new JavaBean(source);
        }

        @Override
        public JavaBean[] newArray(int size) {
            return new JavaBean[size];
        }
    };
}
