package jp.co.ienter.mopros.activity.deliver_chart.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_chart.listener.IExpandViewClickListner;

public class ExpandView extends CardView {

    private RelativeLayout layoutHeader;
    private RelativeLayout layoutContent;
    private ImageView btnExpand;
    private boolean isShowContent;
    private TableLayout mTableLayout;
    private TextView tvHeader;
    private IExpandViewClickListner mIExpandViewClickListner;

    public ExpandView(Context context) {
        super(context);
        inflate(context, R.layout.layout_expand_view, this);
        init();

    }

    public ExpandView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_expand_view, this);
        init();
    }

    public ExpandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_expand_view, this);
        init();
    }

    private void init(){
        initUI();
        initControl();
    }

    private void initUI(){
        layoutHeader = (RelativeLayout) findViewById(R.id.include_header);
        btnExpand = (ImageView) findViewById(R.id.btn_toggle);
        tvHeader = (TextView) findViewById(R.id.tv_title);
        layoutContent = (RelativeLayout) findViewById(R.id.layout_content);
        mTableLayout = (TableLayout) findViewById(R.id.table_content);
        notifyDataSetChange();
    }

    private void initControl(){
        layoutHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setShowContent(!isShowContent());
                notifyDataSetChange();
                if(mIExpandViewClickListner != null)
                mIExpandViewClickListner.onHeaderClick();
            }
        });
    }

    public void setmIExpandViewClickListner(IExpandViewClickListner listener){
        this.mIExpandViewClickListner = listener;
    }

    public void setHeader(String text){
        tvHeader.setText(text);
        notifyDataSetChange();
    }

    public String getHeader(){
        if(tvHeader == null){
            return "";
        }
        return tvHeader.getText().toString();
    }

    public void setHideHeader(boolean isHideHeader){
        if(isHideHeader){
            layoutHeader.setVisibility(View.GONE);
        } else {
            layoutHeader.setVisibility(View.VISIBLE);
        }
        notifyDataSetChange();
    }

    public void disableExpandButton(boolean isDisableExpand){
        if(isDisableExpand){
            btnExpand.setVisibility(View.GONE);
            layoutHeader.setClickable(false);
        } else {
            btnExpand.setVisibility(View.GONE);
            layoutHeader.setClickable(false);
        }
        notifyDataSetChange();
    }

    public boolean isShowContent() {
        return isShowContent;
    }

    public void setShowContent(boolean showContent) {
        isShowContent = showContent;
    }

    public void notifyDataSetChange(){
        if(layoutHeader.getVisibility() == View.GONE){
            isShowContent = true;
        } else if(btnExpand.getVisibility() == View.GONE){
            isShowContent = true;
        }
        if(isShowContent){
            layoutContent.setVisibility(View.VISIBLE);
            btnExpand.setBackgroundResource(R.drawable.gray_circle_collapse);
        } else {
            layoutContent.setVisibility(View.GONE);
            btnExpand.setBackgroundResource(R.drawable.gray_circle_expand);
        }
    }

    public void addRowToTableLayout(View row){
        mTableLayout.addView(row);
    }

    public void clearContent(){
        mTableLayout.removeAllViews();
    }

}
