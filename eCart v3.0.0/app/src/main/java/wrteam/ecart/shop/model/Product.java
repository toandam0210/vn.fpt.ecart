package wrteam.ecart.shop.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product implements Serializable {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo
    String name;
    @ColumnInfo
    String slug;
    @ColumnInfo
    String category_id;
    @ColumnInfo
    String indicator;
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
    String image;
    @ColumnInfo
    String size_chart;
    @ColumnInfo
    String description;
    @ColumnInfo
    String status;
    @ColumnInfo
    String ratings;
    @ColumnInfo
    String number_of_ratings;
    @ColumnInfo
    String tax_percentage;
    @ColumnInfo
    boolean is_favorite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSize_chart() {
        return size_chart;
    }

    public void setSize_chart(String size_chart) {
        this.size_chart = size_chart;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getNumber_of_ratings() {
        return number_of_ratings;
    }

    public void setNumber_of_ratings(String number_of_ratings) {
        this.number_of_ratings = number_of_ratings;
    }

    public String getTax_percentage() {
        return tax_percentage;
    }

    public void setTax_percentage(String tax_percentage) {
        this.tax_percentage = tax_percentage;
    }

    public boolean isIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }
}
