package jdebu.github.io.gridlayoutrecyclerview.interfaces;

import java.util.ArrayList;

import jdebu.github.io.gridlayoutrecyclerview.adapter.WearItem;

/**
 * Created by Glup on 5/05/16.
 */
public interface OnGetWears {
    void getWears(Boolean status,ArrayList<WearItem> wearItems,String message);
}
