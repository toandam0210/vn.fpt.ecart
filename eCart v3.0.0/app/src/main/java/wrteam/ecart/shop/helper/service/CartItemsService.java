package wrteam.ecart.shop.helper.service;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import wrteam.ecart.shop.model.CartItems;

@Dao
public interface CartItemsService {
    @Query("SELECT * FROM CartItems")
    List<CartItems> getAll();
}
