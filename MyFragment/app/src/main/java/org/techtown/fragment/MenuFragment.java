package org.techtown.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //LayoutInflater, ViewGroup 파라미터로 전달됨
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);

        //프래그먼트 화면으로 보임 / 프래그먼트 : 실제 뷰가 아닌 부분 화면을 담기 위한 일종의 틀
        return rootView;
    }
}
