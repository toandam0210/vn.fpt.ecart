package wrteam.ecart.shop.helper.service;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import wrteam.ecart.shop.model.Product;
import wrteam.ecart.shop.model.Slider;
import wrteam.ecart.shop.model.Variants;
@Dao
public interface VariantsService {
    @Query("SELECT * FROM Variants")
    List<Variants> getAll();

    @Query("SELECT * FROM Variants where product_id = :productId")
    List<Variants> loadVariants(Integer productId);
}
