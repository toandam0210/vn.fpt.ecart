package wrteam.ecart.shop.helper.service;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import wrteam.ecart.shop.model.OrderTracker;
import wrteam.ecart.shop.model.Product;
@Dao
public interface ProductService {
    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Query("SELECT * FROM Product where category_id = :categoryId")
    List<Product> loadProduct(Integer categoryId);
}
