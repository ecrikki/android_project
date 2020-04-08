package com.example.calories.ViewHolder_Adapter;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calories.Class.Class_prod;
import com.example.calories.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class Class_prodViewHolder extends GroupViewHolder {
    private TextView class_pr;
    private ImageView arrow;

    public Class_prodViewHolder(View itemView) {
        super(itemView);
        class_pr = itemView.findViewById(R.id.textView);
        arrow = itemView.findViewById(R.id.arrow);
    }

    public void bind(Class_prod class_prod){
        class_pr.setText(class_prod.getTitle());
    }

    @Override
    public void expand() {

        animateExpand();
    }

    @Override
    public void collapse() {

        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
