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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_variant_id() {
        return product_variant_id;
    }

    public void setProduct_variant_id(String product_variant_id) {
        this.product_variant_id = product_variant_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(String discounted_price) {
        this.discounted_price = discounted_price;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }

    public String getDeliver_by() {
        return deliver_by;
    }

    public void setDeliver_by(String deliver_by) {
        this.deliver_by = deliver_by;
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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getVariant_id() {
        return variant_id;
    }

    public void setVariant_id(String variant_id) {
        this.variant_id = variant_id;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMade_in() {
        return made_in;
    }

    public void setMade_in(String made_in) {
        this.made_in = made_in;
    }

    public String getReturn_status() {
        return return_status;
    }

    public void setReturn_status(String return_status) {
        this.return_status = return_status;
    }

    public String getCancelable_status() {
        return cancelable_status;
    }

    public void setCancelable_status(String cancelable_status) {
        this.cancelable_status = cancelable_status;
    }

    public String getTill_status() {
        return till_status;
    }

    public void setTill_status(String till_status) {
        this.till_status = till_status;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isReview_status() {
        return review_status;
    }

    public boolean isApplied_for_return() {
        return applied_for_return;
    }

    public void setApplied_for_return(boolean applied_for_return) {
        this.applied_for_return = applied_for_return;
    }
}