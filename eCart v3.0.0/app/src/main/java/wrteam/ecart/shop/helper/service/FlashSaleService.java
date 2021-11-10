package wrteam.ecart.shop.helper.service;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import wrteam.ecart.shop.model.Cart;
import wrteam.ecart.shop.model.FlashSale;
@Dao
public interface FlashSaleService {
    @Query("SELECT * FROM FlashSale")
    List<FlashSale> getAll();
}
