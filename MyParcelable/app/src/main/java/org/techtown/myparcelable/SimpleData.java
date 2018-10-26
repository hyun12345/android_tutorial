package org.techtown.myparcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {

    int number;
    String message;

    //생성자
    public SimpleData(int number, String message) {
        this.number = number;
        this.message = message;
    }

    //변수값 read 해서 할당
    public SimpleData(Parcel src) {
        number = src.readInt();
        message = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public SimpleData createFromParcel(Parcel src) {
            return new SimpleData(src);
            //SimpleData 메소드 호출, Parcel에서 두 값을 읽어서 변수에 할당
        }

        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
        //위의 두 메소드 = 필수 메소드
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(message);
        //SimpleData를 Parcel로 바꿔줌
        //Parcel : 객체 안의 데이터를 다른 데에 전달할 때 사용되는 객체
    }
}
