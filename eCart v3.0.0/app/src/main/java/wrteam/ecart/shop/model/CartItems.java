package wrteam.ecart.shop.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class CartItems implements Serializable {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo
    String is_cod_allowed;
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
    String name;
    @ColumnInfo
    String image;
    @ColumnInfo
    String unit;
    @ColumnInfo
    String tax_percentage;
    @ColumnInfo
    String tax_title;


    public Integer getId() {
        return id;
    }

    public String getIs_cod_allowed() {
        return is_cod_allowed;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getType() {
        return type;
    }

    public String getMeasurement() {
        return measurement;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscounted_price() {
        return discounted_price;
    }

    public String getServe_for() {
        return serve_for;
    }

    public String getStock() {
        return stock;
    }


    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getUnit() {
        return unit;
    }

    public String getTax_percentage() {
        return tax_percentage;
    }

    public String getTax_title() {
        return tax_title;
    }
}
