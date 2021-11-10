package wrteam.ecart.shop.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import wrteam.ecart.shop.R;
import wrteam.ecart.shop.fragment.CheckoutFragment;
import wrteam.ecart.shop.helper.ApiConfig;
import wrteam.ecart.shop.helper.AppDatabase;
import wrteam.ecart.shop.helper.Constant;
import wrteam.ecart.shop.helper.Session;
import wrteam.ecart.shop.helper.service.CartItemsService;
import wrteam.ecart.shop.helper.service.CartService;
import wrteam.ecart.shop.model.Cart;
import wrteam.ecart.shop.model.CartItems;
import wrteam.ecart.shop.model.ItemInCart;
import wrteam.ecart.shop.model.OrderItem;

/**
 * Created by shree1 on 3/16/2017.
 */

public class CheckoutItemListAdapter extends RecyclerView.Adapter<CheckoutItemListAdapter.ItemHolder> {

    public List<Cart> carts;
    public Activity activity;
    Session session;
    AppDatabase db;

    public CheckoutItemListAdapter(Activity activity, List<Cart> carts) {
        try {
            this.activity = activity;
            this.carts = carts;
            session = new Session(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NotNull final ItemHolder holder, final int position) {
        try {

            final Cart cart = carts.get(position);
            db = AppDatabase.getDbInstance(activity.getApplicationContext());
            CartService cartService = db.cartService();
            CartItemsService cartItemsService = db.cartItemsService();
            Cart carts = cartService.loadCartById(Integer.valueOf(session.getData(Constant.USER_ID)), false);
            List<CartItems> orderItems = cartItemsService.loadCartItem(cart.getId());

            float price;
            if (orderItems.get(0).getDiscounted_price().equals("0")) {
                price = Float.parseFloat(orderItems.get(0).getPrice());
            } else {
                price = Float.parseFloat(orderItems.get(0).getDiscounted_price());
            }

            String taxPercentage = orderItems.get(0).getTax_percentage();

            if (orderItems.get(0).getIs_cod_allowed().equals("0")) {
                Constant.isCODAllow = false;
            }

            if (orderItems.get(0).getServe_for().equalsIgnoreCase(Constant.SOLD_OUT_TEXT)) {
                CheckoutFragment.isSoldOut = true;
                holder.tvStatus.setVisibility(View.VISIBLE);
            }

            holder.tvItemName.setText(orderItems.get(0).getName() + " (" + orderItems.get(0).getMeasurement() + " " + ApiConfig.toTitleCase(orderItems.get(0).getUnit()) + ")");
            holder.tvQty.setText(activity.getString(R.string.qty_1) + cart.getQty());
            holder.tvPrice.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + price));

            if (orderItems.get(0).getDiscounted_price().equals("0") || orderItems.get(0).getDiscounted_price().equals("")) {
                holder.tvTaxTitle.setText(orderItems.get(0).getTax_title());
                holder.tvTaxAmount.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + (Integer.parseInt(cart.getQty()) * ((Float.parseFloat(orderItems.get(0).getPrice()) * Float.parseFloat(taxPercentage)) / 100))));
            } else {
                holder.tvTaxTitle.setText(orderItems.get(0).getTax_title());
                holder.tvTaxAmount.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + (Integer.parseInt(cart.getQty()) * ((Float.parseFloat(orderItems.get(0).getDiscounted_price()) * Float.parseFloat(taxPercentage)) / 100))));
            }
            if (orderItems.get(0).getTax_percentage().equals("0")) {
                holder.tvTaxTitle.setText("TAX");
            }
            holder.tvTaxPercent.setText("(" + orderItems.get(0).getTax_percentage() + "%)");

            if (orderItems.get(0).getDiscounted_price().equals("0") || orderItems.get(0).getDiscounted_price().equals("")) {
                holder.tvSubTotal.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + (Integer.parseInt(cart.getQty()) * (Float.parseFloat(orderItems.get(0).getPrice()) + ((Float.parseFloat(orderItems.get(0).getPrice()) * Float.parseFloat(taxPercentage)) / 100)))));
            } else {
                holder.tvSubTotal.setText(session.getData(Constant.currency) + ApiConfig.StringFormat("" + (Integer.parseInt(cart.getQty()) * (Float.parseFloat(orderItems.get(0).getDiscounted_price()) + ((Float.parseFloat(orderItems.get(0).getDiscounted_price()) * Float.parseFloat(taxPercentage)) / 100)))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lyt_checkout_item_list, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        public final TextView tvItemName;
        public final TextView tvQty;
        public final TextView tvPrice;
        public final TextView tvSubTotal;
        public final TextView tvTaxPercent;
        public final TextView tvTaxTitle;
        public final TextView tvTaxAmount;
        public final TextView tvStatus;

        public ItemHolder(View itemView) {
            super(itemView);

            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvQty = itemView.findViewById(R.id.tvQty);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvSubTotal = itemView.findViewById(R.id.tvSubTotal);
            tvTaxPercent = itemView.findViewById(R.id.tvTaxPercent);
            tvTaxTitle = itemView.findViewById(R.id.tvTaxTitle);
            tvTaxAmount = itemView.findViewById(R.id.tvTaxAmount);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }


    }
}

