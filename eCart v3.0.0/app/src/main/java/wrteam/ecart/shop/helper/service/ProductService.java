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

    @Query("SELECT * FROM Product where id = :product_id")
    Product loadProductDetail(int product_id);

    @Query("SELECT * FROM Product where id = :product_id AND is_favorite = :isFav")
    List<Product> getFavoriteProduct(Integer product_id, boolean isFav);
}
