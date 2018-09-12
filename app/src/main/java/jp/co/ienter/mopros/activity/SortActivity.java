package jp.co.ienter.mopros.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.base.BaseActivity;
import jp.co.ienter.mopros.adapter.SortListAdapter;
import jp.co.ienter.mopros.interfaces.DeliveryItemClickListener;
import jp.co.ienter.mopros.interfaces.IBasicApiNoDataCallback;
import jp.co.ienter.mopros.interfaces.IRegSortDeleveryCallBack;
import jp.co.ienter.mopros.interfaces.IListSortCallBack;
import jp.co.ienter.mopros.interfaces.OnClickChooseListener;
import jp.co.ienter.mopros.interfaces.OnItemChoosedListener;
import jp.co.ienter.mopros.interfaces.OnSortListChangedListener;
import jp.co.ienter.mopros.interfaces.helper.OnStartDragListener;
import jp.co.ienter.mopros.interfaces.helper.SimpleItemTouchHelperCallback;
import jp.co.ienter.mopros.models.MoprosCargo;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.services.SortService;
import jp.co.ienter.mopros.utils.Const;
import jp.co.ienter.mopros.utils.DateTimeConverter;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.InternetManager;
import jp.co.ienter.mopros.utils.PreferencesHelper;

import static jp.co.ienter.mopros.utils.Const.FLAG_DESTINATION_TYPE_PICK_UP;

/**
 * Created by thanhnv on 7/18/18.
 */

public class SortActivity extends BaseActivity implements OnSortListChangedListener,
        OnStartDragListener,OnClickChooseListener,DeliveryItemClickListener,OnItemChoosedListener {
//    @BindView(R.id.btnSortNoShip) TextView btnSortNoShip;
    @BindView(R.id.include_general_info_tv_delivery_date) TextView include_general_info_tv_delivery_date;
    @BindView(R.id.include_general_info_tv_id)
    TextView include_general_info_tv_id;
    @BindView(R.id.btnSortNoShip)
    Button btnSortNoShip;
    @BindView(R.id.btnSortDeletePickUp) Button btnSortDeletePickUp;
    private RecyclerView rcSort;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private SortListAdapter mAdapter;
    public ArrayList<MoprosDelivery> listSortDelivery;
    public ArrayList<MoprosDelivery> listRemove = new ArrayList<MoprosDelivery>();
    private int posItemRemove=-1;
    private int posCurrent=-1;
    private DialogUtils dialogUtils = new DialogUtils(SortActivity.this);
    private MoprosDelivery objDelivery=new MoprosDelivery();
    private ArrayList<MoprosCargo> listCago = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ButterKnife.bind(this);
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        include_general_info_tv_delivery_date.setText(dateTimeConverter.convertHaisoDate(PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate()));
        include_general_info_tv_id.setText(PreferencesHelper.getInstance(this).getUserID());
        dialogUtils.showProgress(SortActivity.this,getResources().getString(R.string.loading));
        getListSort();
        setDisableButton(false,"0",null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        finish();
//        startActivity(getIntent());
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                MoprosCargo myObject = data.getExtras().getParcelable("objMoprosDelivery");
                MoprosDelivery obj = new MoprosDelivery();
                listCago.add(myObject);
                 obj.setNonyu_name(myObject.getShuka_name());
                 obj.setShuka_code(myObject.getShuka_code());
                 obj.setSharyo_code(myObject.getSyaryo_code());
                 obj.setData_type("2");
                 obj.setKanryo_flg("0");
                 obj.setShitei_time("");
//                listSortDelivery.add(myObject);
                listSortDelivery.add(obj);
                mAdapter.updateData(listSortDelivery);
                Log.d("OK: ",myObject.toString());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

    }

    @OnClick(R.id.btnMainMenu)
    public void onCLickBtnMainMenu(View view) {
        finish();
    }

    @OnClick(R.id.btnSortComplete)
    public void onCLickBtnSortComplete(View view) {
        try {
            if(mAdapter == null || mAdapter.getListSort().isEmpty()){
                return;
            }
            dialogUtils.showProgress(SortActivity.this, getResources().getString(R.string.loading));
            if (!InternetManager.hasInternet(this)) {
                dialogUtils.showDialog(getResources().getString(R.string.no_internet), this);
                dialogUtils.hideProgress();
                return;
            }
            SortService.getInstance().updateSort(PreferencesHelper.getInstance(SortActivity.this).getUserID(),
                    PreferencesHelper.getInstance(SortActivity.this).getHaiSoDate(),
                    mAdapter.getListSort(), new IRegSortDeleveryCallBack() {
                        @Override
                        public void onSuccess() {
                            dialogUtils.hideProgress();
                            finish();
                        }

                        @Override
                        public void onError(String error) {
                            dialogUtils.hideProgress();
                            dialogUtils.showDialog(error,SortActivity.this);
                        }
                    });
        } catch (JSONException e) {
            dialogUtils.hideProgress();
            e.printStackTrace();
        }

    }

    @OnClick(R.id.btnSortNoShip)
    public void onCLickBtnSortNoShip(View view) {
        String kanryoFlag="";
        dialogUtils.showProgress(SortActivity.this, getResources().getString(R.string.loading));
        if (!InternetManager.hasInternet(this)) {
            dialogUtils.showDialog(getResources().getString(R.string.no_internet), this);
            dialogUtils.hideProgress();
            return;
        }
        if(Const.SORT_IGNORE_NO_TRANSPORT.equalsIgnoreCase(objDelivery.getKanryo_flg())){
            btnSortNoShip.setText(R.string.btn_sort_no_transport);
            kanryoFlag="0";
        }else{
            btnSortNoShip.setText(R.string.btn_sort_ignore_no_transport);
            kanryoFlag="2";
        }
        objDelivery.setKanryo_flg(kanryoFlag);
        listSortDelivery.set(posCurrent,objDelivery);
        SortService.getInstance().updateNoDelivery(PreferencesHelper.getInstance(
                getApplicationContext()).getUserID(), PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate(),
                objDelivery.getCourse_code_1(),objDelivery.getCourse_code_2(),objDelivery.getHaiso_order_no(),
                objDelivery.getTrip(),kanryoFlag, new
                        IRegSortDeleveryCallBack() {
                            @Override
                            public void onSuccess() {
                                dialogUtils.hideProgress();
                            }

                            @Override
                            public void onError(String error) {
                                dialogUtils.hideProgress();
                                dialogUtils.showDialog(error,SortActivity.this);
                            }
                        });

    }

    @OnClick(R.id.btnMsg)
    public void onCLickBtnMsg(View view) {
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnSortPickUp)
    public void onCLickBtnSortPickUp(View view) {
        Intent intent = new Intent(this, PickUpActivity.class);
        Bundle args = new Bundle();
//        args.putSerializable("listRemove",(Serializable)listRemove);
        args.putParcelableArrayList("listRemove", listCago);
        intent.putExtra("BUNDLE",args);
        startActivityForResult(intent, 1);

    }

    @OnClick(R.id.btnSortDeletePickUp)
    public void onCLickBtnSortDeletePickUp(View view) {
        //update
        if(Const.FLAG_DESTINATION_TYPE_PICK_UP.equalsIgnoreCase(listSortDelivery.get(posCurrent).getData_type())) {
            if (posItemRemove > -1 && listSortDelivery.size() > 0) {
                if (listSortDelivery.get(posItemRemove).getData_type().equals(FLAG_DESTINATION_TYPE_PICK_UP)) {
                    listRemove.add(listSortDelivery.get(posItemRemove));
                    //Delete API
                    dialogUtils.showProgress(SortActivity.this, getResources().getString(R.string.loading));
                    SortService.getInstance().deletePickup(PreferencesHelper.getInstance(
                            getApplicationContext()).getUserID(),
                            PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate(),
                            listSortDelivery.get(posItemRemove).getShuka_code(),
                            listSortDelivery.get(posItemRemove).getSharyo_code() != null ? listSortDelivery.get(posItemRemove).getSharyo_code() : "",
                            new IBasicApiNoDataCallback() {
                                @Override
                                public void onSuccess() {
                                    MoprosCargo objCargo=new MoprosCargo(listSortDelivery.get(posItemRemove).getShuka_code(),"","");
                                    for(int i=0; i<listCago.size();i++){
                                        if(listCago.get(i).getShuka_code().equalsIgnoreCase(objCargo.getShuka_code())){
                                            listCago.remove(i);
                                        }
                                    }
                                    listCago.remove(objCargo);
                                    listSortDelivery.remove(posItemRemove);
                                    mAdapter.updateData(listSortDelivery);
                                    posItemRemove = -1;
                                    dialogUtils.hideProgress();
                                }

                                @Override
                                public void onError(String error) {
                                    dialogUtils.hideProgress();
                                    dialogUtils.showDialog(error, SortActivity.this);
                                }
                            });

                } else {
                    dialogUtils.showDialog("Cannot Delete", this);
                }
            } else {
                dialogUtils.showDialog("Please select an item", this);
            }
        }else{
            dialogUtils.showDialog("Please select an item pick up", this);
        }
    }

//    private void sortListDelivery(ArrayList<MoprosDelivery> listMoprosDelivery){
//        Collections.sort(listMoprosDelivery, new Comparator<MoprosDelivery>(){
//            public int compare(MoprosDelivery s1, MoprosDelivery s2) {
//                return s1.getOrder_no().compareToIgnoreCase(s2.getOrder_no());
//            }
//        });
//    }

    private void setupRecyclerView(ArrayList<MoprosDelivery> listMoprosDelivery){
        rcSort = findViewById(R.id.rcSort);
        rcSort.setHasFixedSize(false);
        rcSort.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(this);
        rcSort.setLayoutManager(mLayoutManager);
        //setup the adapter with empty list
//        sortListDelivery(listMoprosDelivery);
        mAdapter = new SortListAdapter(listMoprosDelivery, this, this, this,this,this);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rcSort);
        rcSort.setAdapter(mAdapter);
    }

    private void getListSort(){
        if(!InternetManager.hasInternet(this)) {
            dialogUtils.showDialog(getResources().getString(R.string.no_internet),this);
            dialogUtils.hideProgress();
            return;
        }

        SortService.getInstance().getListInfo(PreferencesHelper.getInstance(
                getApplicationContext()).getUserID(), PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate(),
                new IListSortCallBack() {

            @Override
            public void onSuccess(ArrayList<MoprosDelivery> listSort) {
                listSortDelivery=new ArrayList<>();
                listSortDelivery=listSort;
                for(MoprosDelivery delivery : listSort){
                    if(delivery.getData_type().equals(Const.FLAG_DESTINATION_TYPE_PICK_UP)){
                        MoprosCargo cargo = new MoprosCargo(delivery.getShuka_code(), delivery.getNonyu_name(),delivery.getSharyo_code());
                        listCago.add(cargo);
                    }
                }
                setupRecyclerView(listSortDelivery);
                dialogUtils.hideProgress();
            }

            @Override
            public void onError(String error) {
                dialogUtils.hideProgress();
                dialogUtils.showDialog(error, SortActivity.this);
            }
        });
    }

    @Override
    public void onNoteListChanged(ArrayList<MoprosDelivery> listSort) {
        listSortDelivery=listSort;
        //after drag and drop operation, the new list of Customers is passed in here

        //create a List of Long to hold the Ids of the
        //Customers in the List
        List<String> listOfSortedCustomerId = new ArrayList<String>();

        for (MoprosDelivery objSort: listSort){
            listOfSortedCustomerId.add(objSort.getNonyu_code());
        }

        //convert the List of Longs to a JSON string
        Gson gson = new Gson();
        String jsonListOfSortedCustomerIds = gson.toJson(listOfSortedCustomerId);
        String jsonObj=gson.toJson((listSort));
        Log.d("Json Update: ",jsonObj);
    }


    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onItemClick(MoprosDelivery delivery, int position) {
            if(Const.SORT_IGNORE_NO_TRANSPORT.equalsIgnoreCase(delivery.getKanryo_flg())){
                btnSortNoShip.setText(R.string.btn_sort_ignore_no_transport);
            }else{
                btnSortNoShip.setText(R.string.btn_sort_no_transport);
            }
            objDelivery=delivery;

    }

    @Override
    public void onChooseItem(int position) {
        posItemRemove=position;
    }

    @Override
    public void onIsChooseItem(boolean isChoosed,int pos,String kanryoFlag, MoprosDelivery objDelivery) {
        setDisableButton(isChoosed,kanryoFlag,objDelivery);
        posCurrent=pos;
    }

    private void setDisableButton(boolean isChoosed,String kanryoFlag, MoprosDelivery objDelivery){
        if(objDelivery==null){
            btnSortNoShip.setEnabled(isChoosed);
            btnSortDeletePickUp.setEnabled(isChoosed);
        }else if(Const.FLAG_DESTINATION_TYPE_PICK_UP.equalsIgnoreCase(objDelivery.getData_type())){
            btnSortDeletePickUp.setEnabled(isChoosed);
            btnSortNoShip.setEnabled(!isChoosed);
        }else {
            btnSortDeletePickUp.setEnabled(!isChoosed);
            btnSortNoShip.setEnabled(isChoosed);
        }

    }
}
