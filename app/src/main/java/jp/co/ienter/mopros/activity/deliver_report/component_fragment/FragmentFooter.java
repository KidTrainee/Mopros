package jp.co.ienter.mopros.activity.deliver_report.component_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.MainMenuActivity;
import jp.co.ienter.mopros.activity.MessageActivity;
import jp.co.ienter.mopros.fragments.base.BaseFragment;
import jp.co.ienter.mopros.interfaces.MessageCallBack;
import jp.co.ienter.mopros.models.Message;
import jp.co.ienter.mopros.services.MessageService;
import jp.co.ienter.mopros.utils.PreferencesHelper;

public class FragmentFooter extends BaseFragment {

    @BindView(R.id.btnMsg)
    Button btnMessage;

    public static FragmentFooter newInstance() {

        Bundle args = new Bundle();

        FragmentFooter fragment = new FragmentFooter();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainmenu_message_button, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnMainMenu)
    public void onCLickBtnMainMenu() {
        Intent intent = new Intent(mContext, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @OnClick(R.id.btnMsg)
    public void onCLickBtnMsg() {
        startActivity(new Intent(mContext, MessageActivity.class));
    }

    public void updateMessage() {
        String id = PreferencesHelper.getInstance(mContext).getUserID();
        String haisoDate =  PreferencesHelper.getInstance(mContext).getHaiSoDate();
        MessageService.getInstance().getMessage(id, haisoDate, new MessageCallBack() {
            @Override
            public void onSuccess(ArrayList<Message> listMessage) {
                for(int i = 0;i<listMessage.size();i++){
                    if(listMessage.get(i).getIs_read() == 0){
                        btnMessage.setTextColor(getResources().getColor(R.color.colorBlack));
                        btnMessage.setBackgroundResource(R.drawable.background_message);
                        return;
                    }
                }
                btnMessage.setTextColor(getResources().getColor(R.color.colorWhite));
                btnMessage.setBackgroundResource(R.drawable.my_button_mainmenu);
            }

            @Override
            public void onError(String error) {
            }
        });
    }
}
