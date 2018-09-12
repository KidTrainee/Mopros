package jp.co.ienter.mopros.utils;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_chart.view.ImageViewLeftRightBoder;

public class TableHelper {

    private Context mContext;

    public TableHelper(Context context){
        this.mContext = context;
    }

    /**
     *
     * @param color
     * @return bottom line
     */
    public TableRow createRowLine(int color) {
        TableRow row = new TableRow(mContext);
        TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(trParams);
        row.setPadding(0,1,0,1);
        View line = new View(mContext);
        line.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                getSize(R.dimen.item_row_line_height)));
        row.addView(line);
        row.setBackgroundColor(color);
        return row;
    }


    /**
     *
     * @param arr
     * @param textColor
     * @param bgColor
     * @param columnColor
     * @return row for table layout
     */
    public TableRow createRow( String[] arr, int textColor, int bgColor, int columnColor) {
        TableRow row = new TableRow(mContext);
        TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        row.setPadding(0, 0, 0, 0);
        row.setLayoutParams(trParams);

        if (arr != null && arr.length > 0) {
            for (String content : arr) {
                TextView tvColumn = new TextView(mContext);
                tvColumn.setLayoutParams(new TableRow.LayoutParams(getSize(R.dimen.item_row_line_height),
                        TableRow.LayoutParams.MATCH_PARENT
                ));
                tvColumn.setBackgroundColor(columnColor);
                TextView tvContent = new TextView(mContext);
                tvContent.setBackgroundColor(bgColor);
                tvContent.setLayoutParams(new TableRow.LayoutParams(0,
                        TableRow.LayoutParams.WRAP_CONTENT, 1));
                tvContent.setPadding(getSize(R.dimen.table_content_padding_left),
                        getSize(R.dimen.table_content_padding_top),
                        getSize(R.dimen.table_content_padding_right),
                        getSize(R.dimen.table_content_padding_bottom));
                tvContent.setTextColor(textColor);
                tvContent.setText(content);
                row.addView(tvColumn);
                row.addView(tvContent);
            }
            TextView tvColumn = new TextView(mContext);
            tvColumn.setLayoutParams(new TableRow.LayoutParams(getSize(R.dimen.item_row_line_height),
                    TableRow.LayoutParams.MATCH_PARENT
            ));
            tvColumn.setBackgroundColor(columnColor);
            row.addView(tvColumn);
        }
        return row;
    }

    /**
     *
     * @param arr
     * @param columnColor
     * @return
     */
    public TableRow createRow(ItemRow[] arr, int columnColor){
        TableRow row = new TableRow(mContext);
        TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        row.setPadding(0, 0, 0, 0);
        row.setLayoutParams(trParams);
        if(arr != null && arr.length > 0){
            for (ItemRow item: arr) {
                TextView tvColumn = new TextView(mContext);
                tvColumn.setLayoutParams(new TableRow.LayoutParams( getSize(R.dimen.item_row_line_height),
                        TableRow.LayoutParams.MATCH_PARENT
                ));
                tvColumn.setBackgroundColor(columnColor);
                TextView tvContent = new TextView(mContext);
                tvContent.setBackgroundColor(item.getBgColor());
                tvContent.setLayoutParams(new TableRow.LayoutParams(0,
                        TableRow.LayoutParams.MATCH_PARENT, 1));
                tvContent.setPadding(getSize(R.dimen.table_content_padding_left),
                        getSize(R.dimen.table_content_padding_top),
                        getSize(R.dimen.table_content_padding_right),
                        getSize(R.dimen.table_content_padding_bottom));
                tvContent.setTextColor(item.getTextColor());
                tvContent.setText(item.getContent());
                row.addView(tvColumn);
                row.addView(tvContent);
            }
            TextView tvColumn = new TextView(mContext);
            tvColumn.setLayoutParams(new TableRow.LayoutParams( getSize(R.dimen.item_row_line_height),
                    TableRow.LayoutParams.MATCH_PARENT
            ));
            tvColumn.setBackgroundColor(columnColor);
            row.addView(tvColumn);
        }
        return  row;
    }

    /**
     *
     * @param key
     * @param imgUrl
     * @param textColor
     * @param bgTextColor
     * @param colunmColor
     * @return a row have a key and a imageview
     */
    public TableRow createRow(String key, String imgUrl, int textColor, int bgTextColor, int colunmColor){
        TableRow row = new TableRow(mContext);
        TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        row.setPadding(0, 0, 0, 0);
        row.setLayoutParams(trParams);
        TextView tvColumn = new TextView(mContext);
        tvColumn.setLayoutParams(new TableRow.LayoutParams(getSize(R.dimen.item_row_line_height),
                TableRow.LayoutParams.MATCH_PARENT
        ));
        tvColumn.setBackgroundColor(colunmColor);

        TextView tvKey = new TextView(mContext);
        tvKey.setBackgroundColor(bgTextColor);
        tvKey.setLayoutParams(new TableRow.LayoutParams(0,
                TableRow.LayoutParams.MATCH_PARENT, 1));
        tvKey.setPadding(getSize(R.dimen.table_content_padding_left),
                getSize(R.dimen.table_content_padding_top),
                getSize(R.dimen.table_content_padding_right),
                getSize(R.dimen.table_content_padding_bottom));
        tvKey.setTextColor(textColor);
        tvKey.setText(key);

        ImageViewLeftRightBoder ivContent = new ImageViewLeftRightBoder(mContext);
        TableRow.LayoutParams paramsContent = new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 2);
        ivContent.setPaddingImgContent(getSize(R.dimen.table_content_padding_left),
                getSize(R.dimen.table_content_padding_top),
                getSize(R.dimen.table_content_padding_right),
                getSize(R.dimen.table_content_padding_bottom));
        paramsContent.gravity = Gravity.CENTER_HORIZONTAL;
        ivContent.setLayoutParams(paramsContent);
        ivContent.setImgContent(imgUrl);

        if(tvKey.getHeight() > ivContent.getHeight()){

        }


        row.addView(tvColumn);
        row.addView(tvKey);
        row.addView(ivContent);
        return row;
    }

    /**
     * convert size from diment to pixel
     * @param resouce
     * @return
     */
    private int getSize(int resouce){
        return mContext.getResources().getDimensionPixelSize(resouce);
    }

    public class ItemRow{
        private String content;
        private int textColor;
        private int bgColor;

        public ItemRow() {
        }

        public ItemRow(String content, int textColor, int bgColor) {
            this.content = content;
            this.textColor = textColor;
            this.bgColor = bgColor;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        public int getBgColor() {
            return bgColor;
        }

        public void setBgColor(int bgColor) {
            this.bgColor = bgColor;
        }
    }



}
