package co.edu.udea.compumovil.gr01_20192.lab2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class POIAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<POI> PoiList;

    public POIAdapter(Context context, int layout, ArrayList<POI> poilist) {
        this.context = context;
        this.layout = layout;
        this.PoiList = poilist;
    }

    @Override
    public int getCount() {
        return PoiList.size();
    }

    @Override
    public Object getItem(int position) {
        return PoiList.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtDesc, txtPoint;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();


        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName =(TextView)row.findViewById(R.id.txtName);
            holder.txtDesc=(TextView)row.findViewById(R.id.txtDesc);
            holder.txtPoint=(TextView)row.findViewById(R.id.txtPoint);
            holder.imageView=(ImageView) row.findViewById(R.id.imagePOI);
            row.setTag(holder);

        }else{
            holder = (ViewHolder) row.getTag();
        }

        POI poi = PoiList.get(position);

        holder.txtName.setText(poi.getName());
        holder.txtDesc.setText(poi.getDesc());
        holder.txtPoint.setText(poi.getPoint());
        byte[] poiimage=poi.getImage();

        Bitmap bitmap = BitmapFactory.decodeByteArray(poiimage, 0, poiimage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
