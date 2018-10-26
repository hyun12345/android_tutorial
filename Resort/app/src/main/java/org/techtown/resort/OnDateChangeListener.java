package org.techtown.resort;

public interface OnDateChangeListener {

    //Date + Time 합쳐서 인터페이스로 받음
    public void onDateChange(DatePicker view, int year, int monthOfYear, int dayOfMonth);
}
