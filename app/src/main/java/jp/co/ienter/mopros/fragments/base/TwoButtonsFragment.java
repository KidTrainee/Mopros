package jp.co.ienter.mopros.fragments.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.FragmentDeliverReportChildBase;
import jp.co.ienter.mopros.utils.DialogUtils;

//public abstract class TwoButtonsFragment extends DeliverReportBaseFragment implements View.OnClickListener{
public abstract class TwoButtonsFragment extends FragmentDeliverReportChildBase implements View.OnClickListener {

    protected Button btnOne;
    private Button btnTwo;
    private FrameLayout flExtraView;
    private TextView tvError;

    private LayoutInflater mInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mInflater = inflater;
        View fragmentView = inflater.inflate(R.layout.fragment_two_buttons, container, false);
        btnOne = fragmentView.findViewById(R.id.fragment_two_buttons_btn_one);
        btnTwo = fragmentView.findViewById(R.id.fragment_two_buttons_btn_two);

        flExtraView = fragmentView.findViewById(R.id.fragment_two_buttons_extra_view);
        tvError = fragmentView.findViewById(R.id.fragment_two_buttons_tv_error);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        setupUI();
        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        hideErrorTextView();
        switch (v.getId()) {
            case R.id.fragment_two_buttons_btn_one:
                onButtonOneClick();
                break;
            case R.id.fragment_two_buttons_btn_two:
                onButtonTwoClick();
                break;
        }
    }

    private void setupUI() {
        String[] btnNames = setButtonNames();
        if (btnNames.length != 2)
            throw new IllegalArgumentException("There are only 2 buttons -> only two names");
        if (!TextUtils.isEmpty(btnNames[0])) btnOne.setText(btnNames[0]);
        if (!TextUtils.isEmpty(btnNames[1])) btnTwo.setText(btnNames[1]);
    }

    protected abstract void onButtonOneClick();

    protected abstract void onButtonTwoClick();


    protected abstract @NonNull
    String[] setButtonNames();

    protected void insertExtraView(@LayoutRes int layoutRes) {
        View view = mInflater.inflate(layoutRes, flExtraView, true);
        ButterKnife.bind(this, view);
    }

//    public void showError(Throwable error) {
////        tvError.setVisibility(View.VISIBLE);
////        Log.e(TAG, "showError: ", error);
////        String errorMess = error.getMessage();
////        tvError.setText((errorMess == null || errorMess.isEmpty())
////                ? getString(R.string.system_error) : errorMess);
//
//        new DialogUtils().showDialog(error.getMessage(), mContext);
//    }

    private void hideErrorTextView() {
        if (tvError.getVisibility() == View.VISIBLE)
            tvError.setVisibility(View.GONE);
    }

    protected void hideButtonTwo() {
        if (btnTwo.getVisibility() == View.VISIBLE) {
            btnTwo.setVisibility(View.GONE);
        }
    }
}
