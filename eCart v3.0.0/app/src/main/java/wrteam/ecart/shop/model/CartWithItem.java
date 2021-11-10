package wrteam.ecart.shop.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class CartWithItem {
    @Embedded
    public OrderItem orderItem;
    @Relation(
            parentColumn = "id",
            entityColumn = "id",
            associateBy = @Junction(OrderItem.class)
    )
    public List<Cart> carts;
}
