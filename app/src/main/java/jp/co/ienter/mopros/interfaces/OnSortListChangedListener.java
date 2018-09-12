package jp.co.ienter.mopros.interfaces;

import java.util.ArrayList;

import jp.co.ienter.mopros.models.MoprosDelivery;

/**
 * Created by thanhnv on 7/30/18.
 */

public interface OnSortListChangedListener {
    void onNoteListChanged(ArrayList<MoprosDelivery> listSort);
}
