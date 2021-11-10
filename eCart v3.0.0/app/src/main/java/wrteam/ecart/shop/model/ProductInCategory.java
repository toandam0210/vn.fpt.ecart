package wrteam.ecart.shop.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ProductInCategory {
    @Embedded
    Category category;
    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public List<Product> productList;

    public ProductInCategory() {

    }
    public ProductInCategory(Category category, List<Product> productList) {
        this.category = category;
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
