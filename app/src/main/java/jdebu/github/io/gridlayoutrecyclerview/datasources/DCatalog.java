package jdebu.github.io.gridlayoutrecyclerview.datasources;

import android.content.Context;

import java.util.ArrayList;

import jdebu.github.io.gridlayoutrecyclerview.adapter.WearItem;
import jdebu.github.io.gridlayoutrecyclerview.interfaces.OnGetWears;
import jdebu.github.io.gridlayoutrecyclerview.interfaces.OnLoadMoreListener;

/**
 * Created by Glup on 5/05/16.
 */
public class DCatalog {
    private Context context;
    private OnGetWears onGetWears;

    public void setOnGetWears(OnGetWears onGetWears) {
        this.onGetWears = onGetWears;
    }

    public DCatalog(Context context) {
        this.context = context;
    }
    public void loadWear(String numPage,String numWears,String gender){
        ArrayList<WearItem> aum=WearItem.createWearList(20);
        onGetWears.getWears(true,aum,"Bien");
    }
}
