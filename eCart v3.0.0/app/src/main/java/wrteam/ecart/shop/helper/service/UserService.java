package wrteam.ecart.shop.helper.service;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import wrteam.ecart.shop.model.User;

@Dao
public interface UserService {
    @Query("select * from User where phoneNumber = :phoneNumber and password = :password")
    public User login(String phoneNumber, String password);

    @Insert
    void insertAll(User user);

}
