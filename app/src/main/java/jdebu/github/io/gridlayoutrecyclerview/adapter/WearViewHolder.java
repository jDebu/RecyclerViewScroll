package jdebu.github.io.gridlayoutrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import jdebu.github.io.gridlayoutrecyclerview.R;

/**
 * Created by Glup on 5/05/16.
 */
public class WearViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView wearCode;
    public ImageView wearImg;
    public WearViewHolder(View itemView) {
        super(itemView);
        wearCode=(TextView)itemView.findViewById(R.id.wearCode);
        wearImg=(ImageView)itemView.findViewById(R.id.wearImg);
        //events/listeners
        clickEvents();
    }

    private void clickEvents() {
        wearImg.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wearImg:
                Log.e("Clicked",wearCode.getText().toString());
                break;
        }
    }
}
