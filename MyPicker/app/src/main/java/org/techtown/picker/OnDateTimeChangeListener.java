package org.techtown.picker;

public interface OnDateTimeChangeListener {

    //Date + Time 합쳐서 인터페이스로 받음
    public void onDateTimeChange(DateTimePicker view, int year, int monthOfYear, int dayOfMonth, int hour, int minute);
}
