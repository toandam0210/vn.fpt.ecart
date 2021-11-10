package wrteam.ecart.shop.helper.service;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import wrteam.ecart.shop.model.Cart;
import wrteam.ecart.shop.model.CartItems;

@Dao
public interface CartService {
    @Query("SELECT * FROM Cart")
    List<Cart> getAll();
}
