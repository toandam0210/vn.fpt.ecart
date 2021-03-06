package wrteam.ecart.shop.adapter;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import wrteam.ecart.shop.R;
import wrteam.ecart.shop.activity.MainActivity;
import wrteam.ecart.shop.fragment.ProductListFragment;
import wrteam.ecart.shop.helper.Constant;
import wrteam.ecart.shop.model.Category;
import wrteam.ecart.shop.model.Product;
import wrteam.ecart.shop.model.ProductInCategory;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionHolder> {

    public final List<ProductInCategory> sectionList;
    public final Activity activity;
    final Context context;

    public SectionAdapter(Context context, Activity activity, List<ProductInCategory> sectionList) {
        this.context = context;
        this.activity = activity;
        this.sectionList = sectionList;
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    @Override
    public void onBindViewHolder(SectionHolder holder1, final int position) {
        final ProductInCategory section;
        section = sectionList.get(position);
        holder1.tvTitle.setText(section.getCategory().getName());
        holder1.tvSubTitle.setText(section.getCategory().getSubtitle());

        switch (section.getCategory().getStyle()) {
            case "style_1":
                holder1.recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                AdapterStyle1 adapter = new AdapterStyle1(context, activity, section.getProductList(), R.layout.offer_layout);
                holder1.recyclerView.setAdapter(adapter);
                break;
            case "style_2":
                holder1.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                AdapterStyle2 adapterStyle2 = new AdapterStyle2(context, activity, section.getProductList());
                holder1.recyclerView.setAdapter(adapterStyle2);
                break;
            case "style_3":
                holder1.recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
                AdapterStyle1 adapter3 = new AdapterStyle1(context, activity, section.getProductList(), R.layout.lyt_style_3);
                holder1.recyclerView.setAdapter(adapter3);
                break;
        }

        holder1.tvMore.setOnClickListener(view -> {

            Fragment fragment = new ProductListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.FROM, "section");
            bundle.putString(Constant.NAME, section.getCategory().getName());
            bundle.putInt(Constant.ID, section.getCategory().getId());
            fragment.setArguments(bundle);

            MainActivity.fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
        });
    }

    @NonNull
    @Override
    public SectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_layout, parent, false);
        return new SectionHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class SectionHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvSubTitle;
        final TextView tvMore;
        final RecyclerView recyclerView;

        public SectionHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubTitle = itemView.findViewById(R.id.tvSubTitle);
            tvMore = itemView.findViewById(R.id.tvMore);
            recyclerView = itemView.findViewById(R.id.recyclerView);

        }
    }


}
