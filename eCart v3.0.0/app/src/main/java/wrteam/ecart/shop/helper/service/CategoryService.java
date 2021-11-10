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
}
