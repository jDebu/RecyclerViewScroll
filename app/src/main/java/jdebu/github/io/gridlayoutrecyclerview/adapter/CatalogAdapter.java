package jdebu.github.io.gridlayoutrecyclerview.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jdebu.github.io.gridlayoutrecyclerview.MainActivity;
import jdebu.github.io.gridlayoutrecyclerview.R;
import jdebu.github.io.gridlayoutrecyclerview.interfaces.OnLoadMoreListener;

/**
 * Created by Glup on 5/05/16.
 */
public class CatalogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;
    private ArrayList<WearItem> wearItems;
    private Context context;

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }
    private boolean isLoading=true;

    public CatalogAdapter(RecyclerView mRecyclerView,ArrayList<WearItem> wearItems){
        this.wearItems=wearItems;
        final GridLayoutManager gridLayoutManager=(GridLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    int visibleItemCount = gridLayoutManager.getChildCount();
                    int totalItemCount = gridLayoutManager.getItemCount();
                    int pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();
                    Log.e("items", "visibleItemCount:" + visibleItemCount + " totalItemCount:" + totalItemCount + " pastVisiblesItems:" + pastVisiblesItems);
                    if (isLoading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            isLoading = false;
                            //Log.e("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            if (onLoadMoreListener!=null){
                                onLoadMoreListener.loadMoreWears();
                            }
                        }
                    }
                }
            }
        });
    }
    @Override
    public int getItemViewType(int position) {
        return wearItems.get(position)==null? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        if (viewType==VIEW_TYPE_ITEM){
            View view=LayoutInflater.from(context).inflate(R.layout.wear_item,parent,false);
            return new WearViewHolder(view);
        }else if (viewType==VIEW_TYPE_LOADING){
            View view=LayoutInflater.from(context).inflate(R.layout.layout_loading_item,parent,false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WearViewHolder){
            WearItem wearItem=wearItems.get(position);
            WearViewHolder wearViewHolder=(WearViewHolder)holder;
            wearViewHolder.wearCode.setText(wearItem.getWearCode());
            Glide.with(context)
                    .load(wearItem.getImgCode())
                    .fitCenter()
                    .into(wearViewHolder.wearImg);
        }else if (holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder=(LoadingViewHolder)holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return wearItems == null? 0:wearItems.size();
    }
    public void setLoaded(){
        isLoading=true;
    }
}
