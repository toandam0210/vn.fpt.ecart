package wrteam.ecart.shop.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import wrteam.ecart.shop.R;
import wrteam.ecart.shop.activity.MainActivity;
import wrteam.ecart.shop.fragment.ProductDetailFragment;
import wrteam.ecart.shop.fragment.ProductListFragment;
import wrteam.ecart.shop.helper.ApiConfig;
import wrteam.ecart.shop.helper.AppDatabase;
import wrteam.ecart.shop.helper.Constant;
import wrteam.ecart.shop.helper.Session;
import wrteam.ecart.shop.helper.service.FlashSaleService;
import wrteam.ecart.shop.helper.service.VariantsService;
import wrteam.ecart.shop.model.FlashSale;
import wrteam.ecart.shop.model.FlashSaleInVariants;
import wrteam.ecart.shop.model.Product;
import wrteam.ecart.shop.model.Variants;
import wrteam.ecart.shop.model.VariantsInProduct;

/**
 * Created by shree1 on 3/16/2017.
 */

public class FlashSaleAdapter extends RecyclerView.Adapter<FlashSaleAdapter.HolderItems> {

    public final ArrayList<Product> productList;
    public final Activity activity;
    final String from;
    final Session session;
    AppDatabase db;

    public FlashSaleAdapter(Activity activity, ArrayList<Product> productList, String from) {
        this.activity = activity;
        this.productList = productList;
        this.from = from;
        this.session = new Session(activity);
    }

    @Override
    public int getItemCount() {
        return Math.min(productList.size(), from.equals("home") ? 6 : productList.size());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HolderItems holder, final int position) {
        try {
            final Product product = productList.get(position);
            holder.setIsRecyclable(false);
            db = AppDatabase.getDbInstance(activity.getApplicationContext());
            VariantsService variantsService = db.variantsService();
            List<Variants> variants = variantsService.loadVariants(product.getId());
            VariantsInProduct variant = new VariantsInProduct(product, variants);
            List<Variants> variants1 = variantsService.loadVariantsByStatus(product.getId(), true);
            FlashSaleService flashSaleService = db.flashSaleService();
            List<FlashSale> flashSales = flashSaleService.getAll();
            List<FlashSaleInVariants> listFlashSale = new ArrayList<>();
            for (Variants variants2 : variants1) {
                FlashSaleInVariants flashSaleInVariants = new FlashSaleInVariants(variants2,flashSales);
                listFlashSale.add(flashSaleInVariants);
            }

            if (from.equals("home")) {
                if (position == 5) {
                    holder.tvViewAll.setVisibility(View.VISIBLE);
                    holder.lytMain_.setVisibility(View.INVISIBLE);
                } else {
                    holder.tvViewAll.setVisibility(View.GONE);
                    holder.lytMain_.setVisibility(View.VISIBLE);
                }
            }

            Picasso.get()
                    .load(product.getImage())
                    .fit()
                    .centerInside()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(holder.imgThumb);

            holder.setIsRecyclable(false);
            holder.productName.setText(product.getName());

            double OriginalPrice = 0, DiscountedPrice = 0;
            String taxPercentage = "0";

            try {
                taxPercentage = (Double.parseDouble(product.getTax_percentage()) > 0 ? product.getTax_percentage() : "0");
            } catch (Exception e) {
                e.printStackTrace();
            }

            String strCurrentDate = session.getData(Constant.current_date);
            String strStartDate = listFlashSale.get(0).getFlashSales().get(0).getStart_date().split("\\s")[0];
            String strEndDate = listFlashSale.get(0).getFlashSales().get(0).getEnd_date().split("\\s")[0];

            long timeDiff = ApiConfig.dayBetween(strCurrentDate, strStartDate);

            if (timeDiff < 0) {
                timeDiff = (ApiConfig.dayBetween(strCurrentDate, strEndDate) * (-1));
            }

            if (timeDiff < 0) {
                holder.tvTimerTitle.setText(activity.getString(R.string.ends_in));
                holder.tvTimer.setText((timeDiff * (-1)) + activity.getString(R.string.day));
                if (listFlashSale.get(0).getFlashSales().get(0).getDiscounted_price().equals("0") || listFlashSale.get(0).getFlashSales().get(0).getDiscounted_price().equals("")) {
                    holder.showDiscount.setVisibility(View.GONE);
                    holder.tvPrice.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + OriginalPrice));
                } else {
                    holder.showDiscount.setVisibility(View.VISIBLE);
                    DiscountedPrice = ((Float.parseFloat(listFlashSale.get(0).getFlashSales().get(0).getDiscounted_price()) + ((Float.parseFloat(listFlashSale.get(0).getFlashSales().get(0).getDiscounted_price()) * Float.parseFloat(taxPercentage)) / 100)));
                    OriginalPrice = (Float.parseFloat(listFlashSale.get(0).getFlashSales().get(0).getPrice()) + ((Float.parseFloat(listFlashSale.get(0).getFlashSales().get(0).getPrice()) * Float.parseFloat(taxPercentage)) / 100));
                    holder.tvOriginalPrice.setPaintFlags(holder.tvOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.tvOriginalPrice.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + OriginalPrice));
                    holder.tvPrice.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + DiscountedPrice));
                }
            } else if (timeDiff > 0) {
                holder.tvTimerTitle.setText(activity.getString(R.string.starts_in));
                holder.tvTimer.setText(timeDiff + activity.getString(R.string.day));
                if (listFlashSale.get(0).getFlashSales().get(0).getDiscounted_price().equals("0") || listFlashSale.get(0).getFlashSales().get(0).getDiscounted_price().equals("")) {
                    holder.showDiscount.setVisibility(View.GONE);
                    holder.tvPrice.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + OriginalPrice));
                } else {
                    holder.showDiscount.setVisibility(View.VISIBLE);
                    DiscountedPrice = ((Float.parseFloat(listFlashSale.get(0).getFlashSales().get(0).getDiscounted_price()) + ((Float.parseFloat(listFlashSale.get(0).getFlashSales().get(0).getDiscounted_price()) * Float.parseFloat(taxPercentage)) / 100)));
                    OriginalPrice = (Float.parseFloat(listFlashSale.get(0).getFlashSales().get(0).getPrice()) + ((Float.parseFloat(listFlashSale.get(0).getFlashSales().get(0).getPrice()) * Float.parseFloat(taxPercentage)) / 100));
                    holder.tvOriginalPrice.setPaintFlags(holder.tvOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.tvOriginalPrice.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + OriginalPrice));
                    holder.tvPrice.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + DiscountedPrice));
                }
            } else {
                holder.tvTimerTitle.setText(activity.getString(R.string.ends_in));
                StartTimer(holder, listFlashSale.get(0));
                if (listFlashSale.get(0).getFlashSales().get(0).getDiscounted_price().equals("0") || listFlashSale.get(0).getFlashSales().get(0).getDiscounted_price().equals("")) {
                    holder.showDiscount.setVisibility(View.GONE);
                    holder.tvPrice.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + OriginalPrice));
                } else {
                    holder.showDiscount.setVisibility(View.VISIBLE);
                    DiscountedPrice = ((Float.parseFloat(listFlashSale.get(0).getFlashSales().get(0).getDiscounted_price()) + ((Float.parseFloat(listFlashSale.get(0).getFlashSales().get(0).getDiscounted_price()) * Float.parseFloat(taxPercentage)) / 100)));
                    OriginalPrice = (Float.parseFloat(listFlashSale.get(0).getFlashSales().get(0).getPrice()) + ((Float.parseFloat(listFlashSale.get(0).getFlashSales().get(0).getPrice()) * Float.parseFloat(taxPercentage)) / 100));
                    holder.tvOriginalPrice.setPaintFlags(holder.tvOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.tvOriginalPrice.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + OriginalPrice));
                    holder.tvPrice.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + DiscountedPrice));
                }
            }

            holder.showDiscount.setText("-" + ApiConfig.GetDiscount(OriginalPrice, DiscountedPrice));

            holder.lytMain_.setOnClickListener(view -> {
                if (listFlashSale.get(0).getFlashSales().get(0).getProduct_id() != null) {
                    AppCompatActivity activity1 = (AppCompatActivity) activity;
                    Fragment fragment = new ProductDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.ID, listFlashSale.get(0).getFlashSales().get(0).getProduct_id());
                    bundle.putString(Constant.FROM, "section");
                    bundle.putInt("variantsPosition", 0);
                    fragment.setArguments(bundle);
                    activity1.getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
                }
            });

            holder.tvViewAll.setOnClickListener(view -> {
                Fragment fragment = new ProductListFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.FROM, "flash_sale");
                bundle.putString(Constant.NAME, activity.getString(R.string.flash_sale));
                bundle.putString(Constant.ID, listFlashSale.get(0).getFlashSales().get(0).getFlash_sales_id());
                fragment.setArguments(bundle);
                MainActivity.fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public HolderItems onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lyt_flash_item_grid, parent, false);
        return new HolderItems(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class HolderItems extends RecyclerView.ViewHolder {
        final RelativeLayout lytMain_;
        final TextView showDiscount;
        final TextView productName;
        final TextView tvOriginalPrice;
        final TextView tvPrice;
        final TextView tvTimer;
        final TextView tvTimerTitle;
        final TextView tvViewAll;
        final ImageView imgThumb;

        public HolderItems(View itemView) {
            super(itemView);
            lytMain_ = itemView.findViewById(R.id.lytMain_);
            showDiscount = itemView.findViewById(R.id.showDiscount);
            productName = itemView.findViewById(R.id.productName);
            tvOriginalPrice = itemView.findViewById(R.id.tvOriginalPrice);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTimer = itemView.findViewById(R.id.tvTimer);
            tvTimerTitle = itemView.findViewById(R.id.tvTimerTitle);
            imgThumb = itemView.findViewById(R.id.imgThumb);
            tvViewAll = itemView.findViewById(R.id.tvViewAll);
        }
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n", "SimpleDateFormat"})
    public void StartTimer(HolderItems itemHolder, FlashSaleInVariants variants) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setLenient(false);

            String endTime = variants.getFlashSales().get(0).getEnd_date();
            long milliseconds = 0;

            Date endDate;
            try {
                endDate = formatter.parse(endTime);
                assert endDate != null;
                milliseconds = endDate.getTime();

            } catch (ParseException e) {
                e.printStackTrace();
            }

            new CountDownTimer(milliseconds, 1000) {
                @SuppressWarnings("deprecation")
                @Override
                public void onTick(long millisUntilFinished) {
                    String hoursLeft = String.format(((millisUntilFinished / (1000 * 60 * 60)) % 24) > 9 ? "%d" : "%02d", (millisUntilFinished / (1000 * 60 * 60)) % 24);
                    String minutesLeft = String.format(((millisUntilFinished / (1000 * 60)) % 60) > 9 ? "%d" : "%02d", (millisUntilFinished / (1000 * 60)) % 60);
                    String secondsLeft = String.format(((millisUntilFinished / 1000) % 60) > 9 ? "%d" : "%02d", (millisUntilFinished / 1000) % 60);

                    if ((Integer.parseInt(hoursLeft) >= 0 && Integer.parseInt(minutesLeft) >= 0 && Integer.parseInt(secondsLeft) >= 0)) {
                        itemHolder.tvTimer.setText(hoursLeft + ":" + minutesLeft + ":" + secondsLeft);
                    } else {
                        variants.getVariants().setIs_flash_sales(false);
                        notifyItemChanged(itemHolder.getPosition());
                    }
                }

                @Override
                public void onFinish() {
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}