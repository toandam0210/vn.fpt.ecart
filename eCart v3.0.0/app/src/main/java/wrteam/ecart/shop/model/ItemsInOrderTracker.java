package wrteam.ecart.shop.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ItemsInOrderTracker {
    @Embedded
    OrderTracker category;
    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public List<OrderItem> orderItems;
}
