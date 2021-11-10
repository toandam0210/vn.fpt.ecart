package wrteam.ecart.shop.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class FlashSaleInVariants {
    @Embedded
    Variants variants;

    public FlashSaleInVariants(Variants variants, List<FlashSale> flashSales) {
        this.variants = variants;
        this.flashSales = flashSales;
    }

    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public List<FlashSale> flashSales;

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

    public List<FlashSale> getFlashSales() {
        return flashSales;
    }

    public void setFlashSales(List<FlashSale> flashSales) {
        this.flashSales = flashSales;
    }
}
