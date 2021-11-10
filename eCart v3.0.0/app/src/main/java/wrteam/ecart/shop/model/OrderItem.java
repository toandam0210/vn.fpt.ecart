package wrteam.ecart.shop.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class OrderItem implements Serializable {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo
    String user_id;
    @ColumnInfo
    String order_id;
    @ColumnInfo
    String product_variant_id;
    @ColumnInfo
    String quantity;
    @ColumnInfo
    String price;
    @ColumnInfo
    String discounted_price;
    @ColumnInfo
    String tax_amount;
    @ColumnInfo
    String tax_percentage;
    @ColumnInfo
    String discount;
    @ColumnInfo
    String sub_total;
    @ColumnInfo
    String deliver_by;
    @ColumnInfo
    String active_status;
    @ColumnInfo
    String date_added;
    @ColumnInfo
    String product_id;
    @ColumnInfo
    String variant_id;
    @ColumnInfo
    String rate;
    @ColumnInfo
    String review;
    @ColumnInfo
    String name;
    @ColumnInfo
    String image;
    @ColumnInfo
    String manufacturer;
    @ColumnInfo
    String made_in;
    @ColumnInfo
    String return_status;
    @ColumnInfo
    String cancelable_status;
    @ColumnInfo
    String till_status;
    @ColumnInfo
    String measurement;
    @ColumnInfo
    String unit;

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setReview_status(boolean review_status) {
        this.review_status = review_status;
    }
    @ColumnInfo
    boolean review_status;
    @ColumnInfo
    boolean applied_for_return;

    public Integer getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getProduct_variant_id() {
        return product_variant_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscounted_price() {
        return discounted_price;
    }

    public String getTax_amount() {
        return tax_amount;
    }

    public String getTax_percentage() {
        return tax_percentage;
    }

    public String getDiscount() {
        return discount;
    }

    public String getSub_total() {
        return sub_total;
    }

    public String getDeliver_by() {
        return deliver_by;
    }

    public String getActive_status() {
        return active_status;
    }

    public String getDate_added() {
        return date_added;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getVariant_id() {
        return variant_id;
    }

    public String getRate() {
        return rate;
    }

    public String getReview() {
        return review;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getMade_in() {
        return made_in;
    }

    public String getReturn_status() {
        return return_status;
    }

    public String getCancelable_status() {
        return cancelable_status;
    }

    public String getTill_status() {
        return till_status;
    }

    public String getMeasurement() {
        return measurement;
    }

    public String getUnit() {
        return unit;
    }

    public boolean isReview_status() {
        return review_status;
    }

    public boolean isApplied_for_return() {
        return applied_for_return;
    }
}