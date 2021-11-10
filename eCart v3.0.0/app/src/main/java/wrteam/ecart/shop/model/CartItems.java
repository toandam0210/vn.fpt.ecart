package wrteam.ecart.shop.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class CartItems implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIs_cod_allowed() {
        return is_cod_allowed;
    }

    public void setIs_cod_allowed(String is_cod_allowed) {
        this.is_cod_allowed = is_cod_allowed;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTax_percentage() {
        return tax_percentage;
    }

    public void setTax_percentage(String tax_percentage) {
        this.tax_percentage = tax_percentage;
    }

    public String getTax_title() {
        return tax_title;
    }

    public void setTax_title(String tax_title) {
        this.tax_title = tax_title;
    }
}
