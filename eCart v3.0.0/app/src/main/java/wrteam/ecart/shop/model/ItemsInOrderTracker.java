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

    public OrderTracker getCategory() {
        return category;
    }

    public void setCategory(OrderTracker category) {
        this.category = category;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
