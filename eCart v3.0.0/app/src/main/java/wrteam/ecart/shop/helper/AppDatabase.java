package wrteam.ecart.shop.helper;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import wrteam.ecart.shop.helper.service.CartItemsService;
import wrteam.ecart.shop.helper.service.CartService;
import wrteam.ecart.shop.helper.service.CategoryService;
import wrteam.ecart.shop.helper.service.FlashSaleService;
import wrteam.ecart.shop.helper.service.FlashSalesListService;
import wrteam.ecart.shop.helper.service.OrderTrackerService;
import wrteam.ecart.shop.helper.service.ProductService;
import wrteam.ecart.shop.helper.service.SliderService;
import wrteam.ecart.shop.helper.service.VariantsService;
import wrteam.ecart.shop.model.Cart;
import wrteam.ecart.shop.model.CartItems;
import wrteam.ecart.shop.model.Category;
import wrteam.ecart.shop.model.FlashSale;
import wrteam.ecart.shop.model.FlashSalesList;
import wrteam.ecart.shop.model.OrderItem;
import wrteam.ecart.shop.model.OrderTracker;
import wrteam.ecart.shop.model.Product;
import wrteam.ecart.shop.model.Slider;
import wrteam.ecart.shop.model.Variants;

@Database(entities = {Category.class, Cart.class, CartItems.class, FlashSale.class, FlashSalesList.class,
            OrderItem.class, OrderTracker.class, Product.class, Slider.class, Variants.class},  version = 1)

public abstract class AppDatabase extends RoomDatabase {

    public abstract CategoryService categoryService();

    public abstract CartService cartService();

    public abstract CartItemsService cartItemsService();

    public abstract FlashSaleService flashSaleService();

    public abstract FlashSalesListService flashSalesListService();

    public abstract OrderTrackerService orderTrackerService();

    public abstract ProductService productService();

    public abstract SliderService sliderService();

    public abstract VariantsService variantsService();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "foodOrder").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
