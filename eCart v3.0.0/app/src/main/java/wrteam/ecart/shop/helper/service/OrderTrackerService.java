package wrteam.ecart.shop.helper.service;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import wrteam.ecart.shop.model.FlashSalesList;
import wrteam.ecart.shop.model.OrderTracker;
@Dao
public interface OrderTrackerService {
    @Query("SELECT * FROM OrderTracker")
    List<OrderTracker> getAll();
}
