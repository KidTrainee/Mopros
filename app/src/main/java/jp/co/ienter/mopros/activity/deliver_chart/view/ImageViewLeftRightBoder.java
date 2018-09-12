package jp.co.ienter.mopros.activity.deliver_chart.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alexvasilkov.gestures.views.GestureImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import jp.co.ienter.mopros.R;

public class ImageViewLeftRightBoder extends LinearLayout {

    private GestureImageView imgContent;

    public ImageViewLeftRightBoder(Context context) {
        super(context);
        inflate(context, R.layout.layout_item_image_view_with_left_right_boder, this);
        init();
    }

    public ImageViewLeftRightBoder(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_item_image_view_with_left_right_boder, this);
        init();
    }

    public ImageViewLeftRightBoder(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_item_image_view_with_left_right_boder, this);
        init();
    }

    @SuppressLint("NewApi")
    public ImageViewLeftRightBoder(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflate(context, R.layout.layout_item_image_view_with_left_right_boder, this);
        init();
    }

    private void init(){
        initUI();
    }

    private void initUI(){
        imgContent = (GestureImageView) findViewById(R.id.img_content);
        imgContent.getController().getSettings()
                .setMaxZoom(5f);
    }

    public void setImgContent(String imgUrl){
        if(imgUrl.isEmpty()){
            return;
        }
        Picasso.get().load(imgUrl).into(imgContent, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    public void setPaddingImgContent(int left, int top, int right, int bottom){
        imgContent.setPadding(left, top, right, bottom);
    }

    public void setColumnColor(int columnColor){
    }


}
