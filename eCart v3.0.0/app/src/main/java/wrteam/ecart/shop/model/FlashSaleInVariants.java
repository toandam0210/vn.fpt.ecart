package wrteam.ecart.shop.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class FlashSaleInVariants {
    @Embedded
    Variants variants;
    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public List<FlashSale> flashSales;
}
