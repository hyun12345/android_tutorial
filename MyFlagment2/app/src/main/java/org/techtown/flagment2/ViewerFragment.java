package org.techtown.flagment2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class ViewerFragment extends Fragment {

    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        return rootView;
    }

    public void setImage(int index) {
        //index값을 if/else if 조건으로 설정하여 set하는 ImageResource 값 각각 달리 줌
        if (index == 0) {
            imageView.setImageResource(R.drawable.what1);
        } else if (index == 1) {
            imageView.setImageResource(R.drawable.what2);
        } else if (index == 2) {
            imageView.setImageResource(R.drawable.what3);
        }
    }
}
