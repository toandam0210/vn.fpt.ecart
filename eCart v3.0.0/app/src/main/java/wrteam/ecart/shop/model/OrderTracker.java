package wrteam.ecart.shop.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class OrderTracker implements Serializable {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo
    String user_id;
    @ColumnInfo
    String otp;
    @ColumnInfo
    String mobile;
    @ColumnInfo
    String order_note;
    @ColumnInfo
    String total;
    @ColumnInfo
    String delivery_charge;
    @ColumnInfo
    String tax_amount;
    @ColumnInfo
    String tax_percentage;
    @ColumnInfo
    String wallet_balance;
    @ColumnInfo
    String discount;
    @ColumnInfo
    String promo_code;
    @ColumnInfo
    String promo_discount;
    @ColumnInfo
    String final_total;
    @ColumnInfo
    String payment_method;
    @ColumnInfo
    String address;
    @ColumnInfo
    String latitude;
    @ColumnInfo
    String longitude;
    @ColumnInfo
    String delivery_time;
    @ColumnInfo
    String seller_notes;
    @ColumnInfo
    String local_pickup;
    @ColumnInfo
    String pickup_time;
    @ColumnInfo
    String active_status;
    @ColumnInfo
    String date_added;
    @ColumnInfo
    String order_from;
    @ColumnInfo
    String total_attachment;
    @ColumnInfo
    String user_name;
    @ColumnInfo
    String discount_rupees;
    @ColumnInfo
    String bank_transfer_message;
    @ColumnInfo
    String bank_transfer_status;

    public String getOrder_note() {
        return order_note;
    }

    public String getPromo_code() {
        return promo_code;
    }

    public String getBank_transfer_message() {
        return bank_transfer_message;
    }

    public String getBank_transfer_status() {
        return bank_transfer_status;
    }


    public Integer getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getOtp() {
        return otp;
    }

    public String getMobile() {
        return mobile;
    }

    public String getTotal() {
        return total;
    }

    public String getDelivery_charge() {
        return delivery_charge;
    }

    public String getTax_amount() {
        return tax_amount;
    }

    public String getTax_percentage() {
        return tax_percentage;
    }

    public String getWallet_balance() {
        return wallet_balance;
    }

    public String getDiscount() {
        return discount;
    }

    public String getPromo_discount() {
        return promo_discount;
    }

    public String getFinal_total() {
        return final_total;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public String getSeller_notes() {
        return seller_notes;
    }

    public String getLocal_pickup() {
        return local_pickup;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public String getActive_status() {
        return active_status;
    }

    public String getDate_added() {
        return date_added;
    }

    public String getOrder_from() {
        return order_from;
    }


    public String getTotal_attachment() {
        return total_attachment;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getDiscount_rupees() {
        return discount_rupees;
    }


}