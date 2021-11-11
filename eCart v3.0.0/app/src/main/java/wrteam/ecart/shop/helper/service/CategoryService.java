package wrteam.ecart.shop.helper.service;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import wrteam.ecart.shop.model.Cart;
import wrteam.ecart.shop.model.Category;

@Dao
public interface CategoryService {
    @Query("SELECT * FROM Category")
    List<Category> getAll();

    @Query("SELECT * FROM Category WHERE category_id = :cate_id")
    List<Category> getCategoryById(String cate_id);
}
