package wrteam.ecart.shop.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import wrteam.ecart.shop.R;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {
    public final ArrayList<String> offerList;
    final int layout;

    public OfferAdapter(ArrayList<String> offerList, int layout) {
        this.offerList = offerList;
        this.layout = layout;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if (!offerList.get(position).equals("")) {
            File imgFile = new  File(offerList.get(position));
            if(imgFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                holder.offerImage.setImageBitmap(myBitmap);

            }
            holder.lytOfferImage.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView offerImage;
        final CardView lytOfferImage;

        public ViewHolder(View itemView) {
            super(itemView);
            offerImage = itemView.findViewById(R.id.offerImage);
            lytOfferImage = itemView.findViewById(R.id.lytOfferImage);

        }

    }
}
