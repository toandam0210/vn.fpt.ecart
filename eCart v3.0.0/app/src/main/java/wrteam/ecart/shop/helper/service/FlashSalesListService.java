package wrteam.ecart.shop.helper.service;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import wrteam.ecart.shop.model.FlashSale;
import wrteam.ecart.shop.model.FlashSalesList;
@Dao
public interface FlashSalesListService {
    @Query("SELECT * FROM FlashSalesList")
    List<FlashSalesList> getAll();
}
