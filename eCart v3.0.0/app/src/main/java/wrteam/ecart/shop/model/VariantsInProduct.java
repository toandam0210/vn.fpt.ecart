package wrteam.ecart.shop.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class VariantsInProduct {
    @Embedded
    Product product;
    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public List<Variants> variants;


    public Product getProduct() {
        return product;
    }

    public VariantsInProduct(Product product, List<Variants> variants) {
        this.product = product;
        this.variants = variants;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Variants> getVariants() {
        return variants;
    }

    public void setVariants(List<Variants> variants) {
        this.variants = variants;
    }
}
