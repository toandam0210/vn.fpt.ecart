package wrteam.ecart.shop.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class FlashSale implements Serializable {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo
    String flash_sales_id;
    @ColumnInfo
    String product_id;
    @ColumnInfo
    String price;
    @ColumnInfo
    String discounted_price;
    @ColumnInfo
    String start_date;
    @ColumnInfo
    String end_date;
    @ColumnInfo
    String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlash_sales_id() {
        return flash_sales_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}