package wrteam.ecart.shop.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import wrteam.ecart.shop.R;
import wrteam.ecart.shop.activity.MainActivity;
import wrteam.ecart.shop.adapter.CategoryAdapter;
import wrteam.ecart.shop.adapter.OfferAdapter;
import wrteam.ecart.shop.adapter.SectionAdapter;
import wrteam.ecart.shop.adapter.SliderAdapter;
import wrteam.ecart.shop.helper.ApiConfig;
import wrteam.ecart.shop.helper.AppDatabase;
import wrteam.ecart.shop.helper.Constant;
import wrteam.ecart.shop.helper.Session;
import wrteam.ecart.shop.helper.service.CategoryService;
import wrteam.ecart.shop.helper.service.ProductService;
import wrteam.ecart.shop.helper.service.SliderService;
import wrteam.ecart.shop.model.Category;
import wrteam.ecart.shop.model.Product;
import wrteam.ecart.shop.model.ProductInCategory;
import wrteam.ecart.shop.model.Slider;


public class HomeFragment extends Fragment {

    public static List<ProductInCategory> sectionList;
    public static List<Category> categoryArrayList;
    public Session session;
    List<String> sliderArrayList;
    Activity activity;
    NestedScrollView nestedScrollView;
    SwipeRefreshLayout swipeLayout;
    View root;
    int timerDelay = 0, timerWaiting = 0;
    EditText searchView;
    RecyclerView categoryRecyclerView, sectionView, offerView;
    TabLayout tabLayout;
    ViewPager mPager, viewPager;
    LinearLayout mMarkersLayout;
    int size;
    Timer swipeTimer;
    Handler handler;
    Runnable Update;
    int currentPage = 0;
    LinearLayout lytCategory, lytSearchView;
    Menu menu;
    TextView tvMore, tvMoreFlashSale;
    boolean searchVisible = false;
    private ShimmerFrameLayout mShimmerViewContainer;
    AppDatabase db;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        activity = getActivity();
        session = new Session(activity);

        timerDelay = 3000;
        timerWaiting = 3000;
        setHasOptionsMenu(true);

        swipeLayout = root.findViewById(R.id.swipeLayout);

        categoryRecyclerView = root.findViewById(R.id.categoryRecyclerView);

        sectionView = root.findViewById(R.id.sectionView);
        sectionView.setLayoutManager(new LinearLayoutManager(activity));
        sectionView.setNestedScrollingEnabled(false);

        offerView = root.findViewById(R.id.offerView);
        offerView.setLayoutManager(new LinearLayoutManager(activity));
        offerView.setNestedScrollingEnabled(false);

        tabLayout = root.findViewById(R.id.tabLayout);
        viewPager = root.findViewById(R.id.viewPager);

        nestedScrollView = root.findViewById(R.id.nestedScrollView);
        mMarkersLayout = root.findViewById(R.id.layout_markers);
        lytCategory = root.findViewById(R.id.lytCategory);
        lytSearchView = root.findViewById(R.id.lytSearchView);
        lytSearchView = root.findViewById(R.id.lytSearchView);
        tvMoreFlashSale = root.findViewById(R.id.tvMoreFlashSale);
        tvMore = root.findViewById(R.id.tvMore);
        mShimmerViewContainer = root.findViewById(R.id.mShimmerViewContainer);

        searchView = root.findViewById(R.id.searchView);

        db = AppDatabase.getDbInstance(activity.getApplicationContext());

        if (nestedScrollView != null) {
            nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                Rect scrollBounds = new Rect();
                nestedScrollView.getHitRect(scrollBounds);
                if (!lytSearchView.getLocalVisibleRect(scrollBounds) || scrollBounds.height() > lytSearchView.getHeight()) {
                    searchVisible = true;
                    menu.findItem(R.id.toolbar_search).setVisible(true);
                } else {
                    searchVisible = false;
                    menu.findItem(R.id.toolbar_search).setVisible(false);
                }
                activity.invalidateOptionsMenu();
            });
        }

        tvMore.setOnClickListener(v -> {
            if (!MainActivity.categoryClicked) {
                MainActivity.fm.beginTransaction().add(R.id.container, MainActivity.categoryFragment).show(MainActivity.categoryFragment).hide(MainActivity.active).commit();
                MainActivity.categoryClicked = true;
            } else {
                MainActivity.fm.beginTransaction().show(MainActivity.categoryFragment).hide(MainActivity.active).commit();
            }
            MainActivity.bottomNavigationView.setSelectedItemId(R.id.navCategory);
            MainActivity.active = MainActivity.categoryFragment;
        });

        tvMoreFlashSale.setOnClickListener(v -> {
            Fragment fragment = new ProductListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id", "");
            bundle.putString("cat_id", "");
            bundle.putString(Constant.FROM, "flash_sale_all");
            bundle.putString("name", activity.getString(R.string.flash_sales));
            fragment.setArguments(bundle);
            MainActivity.fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
        });

        searchView.setOnTouchListener((View v, MotionEvent event) -> {
            Fragment fragment = new ProductListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.FROM, "search");
            bundle.putString(Constant.NAME, activity.getString(R.string.search));
            bundle.putString(Constant.ID, "");
            fragment.setArguments(bundle);
            MainActivity.fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
            return false;
        });

        lytSearchView.setOnClickListener(v -> {
            Fragment fragment = new ProductListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.FROM, "search");
            bundle.putString(Constant.NAME, activity.getString(R.string.search));
            bundle.putString(Constant.ID, "");
            fragment.setArguments(bundle);
            MainActivity.fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
        });

        mPager = root.findViewById(R.id.pager);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int position) {
                ApiConfig.addMarkers(position, sliderArrayList, mMarkersLayout, activity);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        categoryArrayList = new ArrayList<>();

        swipeLayout.setColorSchemeColors(ContextCompat.getColor(activity, R.color.colorPrimary));

        swipeLayout.setOnRefreshListener(() -> {
            if (swipeTimer != null) {
                swipeTimer.cancel();
            }
            if (ApiConfig.isConnected(getActivity())) {
                ApiConfig.getWalletBalance(activity, new Session(activity));
                GetHomeData(db);
            }
            swipeLayout.setRefreshing(false);
        });

        if (ApiConfig.isConnected(getActivity())) {
            ApiConfig.getWalletBalance(activity, new Session(activity));
            GetHomeData(db);
        } else {
            nestedScrollView.setVisibility(View.VISIBLE);
            mShimmerViewContainer.setVisibility(View.GONE);
            mShimmerViewContainer.stopShimmer();
        }

        return root;
    }

    public void GetHomeData(AppDatabase db) {
        nestedScrollView.setVisibility(View.GONE);
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmer();
        Map<String, String> params = new HashMap<>();
        if (session.getBoolean(Constant.IS_USER_LOGIN)) {
            params.put(Constant.USER_ID, session.getData(Constant.ID));
        }
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    //SONObject jsonObject = new JSONObject(response);
                    //GetOfferImage(jsonObject.getJSONArray(Constant.OFFER_IMAGES));
                    GetFlashSale(db);
                    GetCategory(db);
                    SectionProductRequest(db);
                    GetSlider(db);
                } catch (Exception e) {
                    nestedScrollView.setVisibility(View.VISIBLE);
                    mShimmerViewContainer.setVisibility(View.GONE);
                    mShimmerViewContainer.stopShimmer();

                }
            }
        }, getActivity(), Constant.GET_ALL_DATA_URL, params, false);
    }

    @SuppressWarnings("deprecation")
    public void GetFlashSale(AppDatabase db) {
        tabLayout.removeAllTabs();
        SliderService sliderService = db.sliderService();
        List<Slider> sliderList = sliderService.getAll();
        for (int i = 0; i < sliderList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(sliderList.get(i).getName()));
        }

        TabAdapter tabAdapter = new TabAdapter(MainActivity.fm, tabLayout.getTabCount(), sliderList);
        viewPager.setAdapter(tabAdapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//            tabLayout.setupWithViewPager(viewPager);

    }

    public void GetOfferImage(JSONArray jsonArray) {
        ArrayList<String> offerList = new ArrayList<>();
        try {
            if (jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    offerList.add(object.getString(Constant.IMAGE));
                }
                offerView.setAdapter(new OfferAdapter(offerList, R.layout.offer_lyt));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void GetCategory(AppDatabase db) {
        categoryArrayList = new ArrayList<>();
        CategoryService categoryService = db.categoryService();
        categoryArrayList = categoryService.getAll();
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        categoryRecyclerView.setAdapter(new CategoryAdapter(activity, categoryArrayList, R.layout.lyt_category_grid, "home", categoryArrayList.size()));
    }


    public void SectionProductRequest(AppDatabase db) {  //json request for product search
        sectionList = new ArrayList<>();
        CategoryService categoryService = db.categoryService();
        List<Category> categoryList = categoryService.getAll();
        if (categoryList.size() > 0) {
            for (Category category : categoryList) {
                Category section = new Category();
                ProductService productService = db.productService();
                List<Product> productList = productService.loadProduct(category.getId());
                section.setName(category.getName());
                section.setId(category.getId());
                section.setStyle(category.getStyle());
                section.setSubtitle(category.getSubtitle());
                ProductInCategory productInCategory = new ProductInCategory(section, productList);
                //section.setProductList(ApiConfig.GetProductList(db));
                sectionList.add(productInCategory);
            }

            sectionView.setVisibility(View.VISIBLE);
            SectionAdapter sectionAdapter = new SectionAdapter(activity, getActivity(), sectionList);
            sectionView.setAdapter(sectionAdapter);

        }
    }

    void GetSlider(AppDatabase db) {
        SliderService sliderService = db.sliderService();
        List<Slider> sliderList = sliderService.getAll();
        size = sliderList.size();
        for (Slider slider : sliderList) {
            Slider slider1 = new Slider();
            slider1.setImage(slider.getImage());
            slider1.setType(slider.getType());
            slider1.setType_id(slider.getType_id());
            slider1.setName(slider.getName());
        }
        mPager.setAdapter(new SliderAdapter(sliderArrayList, getActivity(), R.layout.lyt_slider, "home"));
        ApiConfig.addMarkers(0, sliderArrayList, mMarkersLayout, activity);
        handler = new Handler();
        Update = () -> {
            if (currentPage == size) {
                currentPage = 0;
            }
            try {
                mPager.setCurrentItem(currentPage++, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, timerDelay, timerWaiting);


        nestedScrollView.setVisibility(View.VISIBLE);
        mShimmerViewContainer.setVisibility(View.GONE);
        mShimmerViewContainer.stopShimmer();
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.invalidateOptionsMenu();
        ApiConfig.GetSettings(activity);
        hideKeyboard();
    }

    public void hideKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(root.getApplicationWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.toolbar_layout).setVisible(false);
        this.menu = menu;
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.toolbar_cart).setVisible(true);
        menu.findItem(R.id.toolbar_sort).setVisible(false);
        menu.findItem(R.id.toolbar_search).setVisible(searchVisible);
    }

    @SuppressWarnings("deprecation")
    public static class TabAdapter extends FragmentStatePagerAdapter {

        final int mNumOfTabs;
        final List<Slider> sliderList;

        @SuppressWarnings("deprecation")
        public TabAdapter(FragmentManager fm, int NumOfTabs, List<Slider> sliderList) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
            this.sliderList = sliderList;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            fragment = FlashSaleFragment.AddFragment(sliderList.get(position));

            assert fragment != null;
            return fragment;
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

}