package wrteam.ecart.shop.helper.service;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import wrteam.ecart.shop.model.Cart;
import wrteam.ecart.shop.model.CartItems;

@Dao
public interface CartService {
    @Query("SELECT * FROM Cart")
    List<Cart> getAll();

    @Query("SELECT * FROM Cart where user_id = :userId order by id desc")
    Cart loadCartById(Integer userId);

    @Insert
    void insertAll(Cart cart);

    @Update
    void updateAll(Cart cart);
}
