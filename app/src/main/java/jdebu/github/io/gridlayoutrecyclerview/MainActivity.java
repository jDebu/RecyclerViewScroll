package jdebu.github.io.gridlayoutrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jdebu.github.io.gridlayoutrecyclerview.adapter.CatalogAdapter;
import jdebu.github.io.gridlayoutrecyclerview.adapter.WearItem;
import jdebu.github.io.gridlayoutrecyclerview.datasources.DCatalog;
import jdebu.github.io.gridlayoutrecyclerview.interfaces.OnGetWears;
import jdebu.github.io.gridlayoutrecyclerview.interfaces.OnLoadMoreListener;

public class MainActivity extends AppCompatActivity implements OnGetWears{
    private RecyclerView rvWears;
    private CatalogAdapter catalogAdapter;
    private ArrayList<WearItem> wearItems;
    private GridLayoutManager gridLayoutManager;
    private boolean loading=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        final DCatalog dCatalog=new DCatalog(this);
        gridLayoutManager=new GridLayoutManager(this,2);
        wearItems=WearItem.createWearList(20);
        rvWears=(RecyclerView)findViewById(R.id.rvWears);
        rvWears.setLayoutManager(gridLayoutManager);
        catalogAdapter=new CatalogAdapter(rvWears, wearItems);
        rvWears.setAdapter(catalogAdapter);
        rvWears.setHasFixedSize(true);
        catalogAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMoreWears() {
                wearItems.add(null);
                catalogAdapter.notifyItemInserted(wearItems.size() - 1);
                //get prendas
                //dCatalog.setOnGetWears(MainActivity.this);
                //dCatalog.loadWear("1","20","genM");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        wearItems.remove(wearItems.size()-1);
                        catalogAdapter.notifyItemRemoved(wearItems.size());
                        //load data
                        ArrayList<WearItem> aum=WearItem.createWearList(20);
                        wearItems.addAll(aum);
                        catalogAdapter.notifyDataSetChanged();
                        catalogAdapter.setLoaded();
                    }
                },5000);

            }
        });
        /*rvWears.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) //check for scroll down
                {
                    int visibleItemCount = gridLayoutManager.getChildCount();
                    int totalItemCount = gridLayoutManager.getItemCount();
                    int pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();
                    //Log.e("items","visibleItemCount:"+visibleItemCount+" totalItemCount:"+totalItemCount+" pastVisiblesItems:"+pastVisiblesItems);
                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            //Log.e("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data

                        }
                    }
                }
            }
        });*/

    }


    @Override
    public void getWears(Boolean status, ArrayList<WearItem> rwearItems, String message) {
        if (status){
            wearItems.remove(wearItems.size()-1);
            catalogAdapter.notifyItemRemoved(wearItems.size());
            //load data
            //ArrayList<WearItem> aum=WearItem.createWearList(20);
            wearItems.addAll(rwearItems);
            catalogAdapter.notifyDataSetChanged();
            catalogAdapter.setLoaded();
        }
    }
}
