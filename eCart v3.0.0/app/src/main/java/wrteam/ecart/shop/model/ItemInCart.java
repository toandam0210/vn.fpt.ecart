package wrteam.ecart.shop.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ItemInCart {
        @Embedded
        public Cart cart;
        @Relation(
                parentColumn = "id",
                entityColumn = "id",
                associateBy = @Junction(Cart.class)
        )
        public List<OrderItem> orderItems;
}
