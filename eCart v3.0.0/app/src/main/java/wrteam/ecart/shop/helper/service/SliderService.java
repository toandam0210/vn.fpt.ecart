package wrteam.ecart.shop.helper.service;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import wrteam.ecart.shop.model.Product;
import wrteam.ecart.shop.model.Slider;
@Dao
public interface SliderService {
    @Query("SELECT * FROM Slider")
    List<Slider> getAll();
}
