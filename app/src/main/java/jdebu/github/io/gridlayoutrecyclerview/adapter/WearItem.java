package jdebu.github.io.gridlayoutrecyclerview.adapter;

import java.util.ArrayList;

/**
 * Created by Glup on 5/05/16.
 */
public class WearItem {
    private String wearCode,imgCode;

    public String getWearCode() {
        return wearCode;
    }

    public void setWearCode(String wearCode) {
        this.wearCode = wearCode;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    //prueba
    private static int lastWear=0;

    private static int lastNumPage=0;

    public static void incNumPage() {
        ++lastNumPage;
    }

    public static ArrayList<WearItem> createWearList(int numPrendas){
        ArrayList<WearItem> wearItems=new ArrayList<WearItem>();
        for (int i=1;i<=numPrendas;i++){
            WearItem wearItem=new WearItem();
            wearItem.setWearCode("Wear "+ ++lastWear);
            wearItem.setImgCode("http://es.pixelz.com/wp-content/uploads/sites/38/2014/06/Apparel-mistakes_angles_front.jpg");
            wearItems.add(wearItem);
        }
        return wearItems;
    }
}
