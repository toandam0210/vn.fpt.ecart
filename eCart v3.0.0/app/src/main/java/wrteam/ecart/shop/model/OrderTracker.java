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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(String delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public String getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(String tax_amount) {
        this.tax_amount = tax_amount;
    }

    public String getTax_percentage() {
        return tax_percentage;
    }

    public void setTax_percentage(String tax_percentage) {
        this.tax_percentage = tax_percentage;
    }

    public String getWallet_balance() {
        return wallet_balance;
    }

    public void setWallet_balance(String wallet_balance) {
        this.wallet_balance = wallet_balance;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPromo_code() {
        return promo_code;
    }

    public void setPromo_code(String promo_code) {
        this.promo_code = promo_code;
    }

    public String getPromo_discount() {
        return promo_discount;
    }

    public void setPromo_discount(String promo_discount) {
        this.promo_discount = promo_discount;
    }

    public String getFinal_total() {
        return final_total;
    }

    public void setFinal_total(String final_total) {
        this.final_total = final_total;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getSeller_notes() {
        return seller_notes;
    }

    public void setSeller_notes(String seller_notes) {
        this.seller_notes = seller_notes;
    }

    public String getLocal_pickup() {
        return local_pickup;
    }

    public void setLocal_pickup(String local_pickup) {
        this.local_pickup = local_pickup;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public String getActive_status() {
        return active_status;
    }

    public void setActive_status(String active_status) {
        this.active_status = active_status;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getOrder_from() {
        return order_from;
    }

    public void setOrder_from(String order_from) {
        this.order_from = order_from;
    }

    public String getTotal_attachment() {
        return total_attachment;
    }

    public void setTotal_attachment(String total_attachment) {
        this.total_attachment = total_attachment;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDiscount_rupees() {
        return discount_rupees;
    }

    public void setDiscount_rupees(String discount_rupees) {
        this.discount_rupees = discount_rupees;
    }

    public String getBank_transfer_message() {
        return bank_transfer_message;
    }

    public void setBank_transfer_message(String bank_transfer_message) {
        this.bank_transfer_message = bank_transfer_message;
    }

    public String getBank_transfer_status() {
        return bank_transfer_status;
    }

    public void setBank_transfer_status(String bank_transfer_status) {
        this.bank_transfer_status = bank_transfer_status;
    }
}