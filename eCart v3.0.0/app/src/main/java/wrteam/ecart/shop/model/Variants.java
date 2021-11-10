package wrteam.ecart.shop.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Variants implements Serializable {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo
    String product_id;
    @ColumnInfo
    String type;
    @ColumnInfo
    String measurement;
    @ColumnInfo
    String price;
    @ColumnInfo
    String discounted_price;
    @ColumnInfo
    String serve_for;
    @ColumnInfo
    String stock;
    @ColumnInfo
    String measurement_unit_name;
    @ColumnInfo
    String cart_count;
    @ColumnInfo
    boolean is_flash_sales;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
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

    public String getServe_for() {
        return serve_for;
    }

    public void setServe_for(String serve_for) {
        this.serve_for = serve_for;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getMeasurement_unit_name() {
        return measurement_unit_name;
    }

    public void setMeasurement_unit_name(String measurement_unit_name) {
        this.measurement_unit_name = measurement_unit_name;
    }

    public String getCart_count() {
        return cart_count;
    }

    public void setCart_count(String cart_count) {
        this.cart_count = cart_count;
    }

    public boolean isIs_flash_sales() {
        return is_flash_sales;
    }

    public void setIs_flash_sales(boolean is_flash_sales) {
        this.is_flash_sales = is_flash_sales;
    }
}