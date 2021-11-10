package wrteam.ecart.shop.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import wrteam.ecart.shop.R;
import wrteam.ecart.shop.helper.ApiConfig;
import wrteam.ecart.shop.helper.AppDatabase;
import wrteam.ecart.shop.helper.Constant;
import wrteam.ecart.shop.helper.DatabaseHelper;
import wrteam.ecart.shop.helper.Session;
import wrteam.ecart.shop.helper.Utils;
import wrteam.ecart.shop.helper.service.UserService;
import wrteam.ecart.shop.model.User;
import wrteam.ecart.shop.ui.PinView;

public class LoginActivity extends AppCompatActivity {

    LinearLayout lytOTP;
    EditText edtResetPass, edtResetCPass, edtRefer, edtLoginPassword, edtLoginMobile, edtName, edtEmail, edtPassword, edtConfirmPassword, edtMobileVerify;
    Button btnVerify, btnResetPass, btnLogin, btnRegister;
    CountryCodePicker edtCountryCodePicker;
    PinView pinViewOTP;
    TextView tvSignUp, tvMobile, tvWelcome, tvTimer, tvResend, tvForgotPass, tvPrivacyPolicy;
    ScrollView lytLogin, lytSignUp, lytVerify, lytResetPass, lytWebView;
    Session session;
    Toolbar toolbar;
    CheckBox chPrivacy;
    Animation animShow, animHide;
    ImageView imgVerifyClose, imgResetPasswordClose, imgSignUpClose, imgWebViewClose;

    ////Firebase
    String phoneNumber, firebase_otp = "", otpFor = "";
    boolean resendOTP = false;
    FirebaseAuth auth;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    DatabaseHelper databaseHelper;
    Activity activity;
    ImageView img;
    WebView webView;
    String from, mobile, countryCode;
    ProgressDialog dialog;
    final boolean forMultipleCountryUse = true;
    AppDatabase db;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = LoginActivity.this;
        session = new Session(activity);
        databaseHelper = new DatabaseHelper(activity);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        animShow = AnimationUtils.loadAnimation(this, R.anim.view_show);
        animHide = AnimationUtils.loadAnimation(this, R.anim.view_hide);

        from = getIntent().getStringExtra(Constant.FROM);
        db = AppDatabase.getDbInstance(activity.getApplicationContext());

        chPrivacy = findViewById(R.id.chPrivacy);
        tvWelcome = findViewById(R.id.tvWelcome);
        edtCountryCodePicker = findViewById(R.id.edtCountryCodePicker);
        edtResetPass = findViewById(R.id.edtResetPass);
        edtResetCPass = findViewById(R.id.edtResetCPass);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        edtLoginMobile = findViewById(R.id.edtLoginMobile);
        lytLogin = findViewById(R.id.lytLogin);
        lytResetPass = findViewById(R.id.lytResetPass);
        lytVerify = findViewById(R.id.lytVerify);
        lytSignUp = findViewById(R.id.lytSignUp);
        lytOTP = findViewById(R.id.lytOTP);
        pinViewOTP = findViewById(R.id.pinViewOTP);
        btnResetPass = findViewById(R.id.btnResetPass);
        btnVerify = findViewById(R.id.btnVerify);
        edtMobileVerify = findViewById(R.id.edtMobileVerify);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        tvMobile = findViewById(R.id.tvMobile);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtRefer = findViewById(R.id.edtRefer);
        tvResend = findViewById(R.id.tvResend);
        tvTimer = findViewById(R.id.tvTimer);
        tvForgotPass = findViewById(R.id.tvForgotPass);
        tvPrivacyPolicy = findViewById(R.id.tvPrivacy);
        img = findViewById(R.id.img);
        lytWebView = findViewById(R.id.lytWebView);
        webView = findViewById(R.id.webView);
        btnResetPass = findViewById(R.id.btnResetPass);
        btnVerify = findViewById(R.id.btnVerify);
        tvResend = findViewById(R.id.tvResend);
        tvSignUp = findViewById(R.id.tvSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        imgVerifyClose = findViewById(R.id.imgVerifyClose);
        imgResetPasswordClose = findViewById(R.id.imgResetPasswordClose);
        imgSignUpClose = findViewById(R.id.imgSignUpClose);
        imgWebViewClose = findViewById(R.id.imgWebViewClose);

        tvForgotPass.setText(underlineSpannable(getString(R.string.forgot_text)));
        edtLoginMobile.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_phone, 0, 0, 0);

        edtLoginPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pass, 0, R.drawable.ic_show, 0);
        edtPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pass, 0, R.drawable.ic_show, 0);
        edtConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pass, 0, R.drawable.ic_show, 0);
        edtResetPass.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pass, 0, R.drawable.ic_show, 0);
        edtResetCPass.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pass, 0, R.drawable.ic_show, 0);

        Utils.setHideShowPassword(edtPassword);
        Utils.setHideShowPassword(edtConfirmPassword);
        Utils.setHideShowPassword(edtLoginPassword);
        Utils.setHideShowPassword(edtResetPass);
        Utils.setHideShowPassword(edtResetCPass);

        lytResetPass.setVisibility(View.GONE);
        lytLogin.setVisibility(View.VISIBLE);
        lytVerify.setVisibility(View.GONE);
        lytSignUp.setVisibility(View.GONE);
        lytOTP.setVisibility(View.GONE);
        lytWebView.setVisibility(View.GONE);

        tvWelcome.setText(getString(R.string.welcome) + getString(R.string.app_name));

        edtCountryCodePicker.setCountryForNameCode("IN");

//        forMultipleCountryUse = false;

        if (from != null) {
            switch (from) {
                case "drawer":
                case "checkout":
                case "tracker":
                    lytLogin.setVisibility(View.VISIBLE);
                    lytLogin.startAnimation(animShow);
                    new Handler().postDelayed(() -> edtLoginMobile.requestFocus(), 500);
                    break;
                case "refer":
                    otpFor = "new_user";
                    lytVerify.setVisibility(View.VISIBLE);
                    lytVerify.startAnimation(animShow);
                    new Handler().postDelayed(() -> edtMobileVerify.requestFocus(), 500);
                    break;
                default:
                    lytVerify.setVisibility(View.GONE);
                    lytResetPass.setVisibility(View.GONE);
                    lytVerify.setVisibility(View.GONE);
                    lytLogin.setVisibility(View.GONE);
                    lytSignUp.setVisibility(View.VISIBLE);
                    tvMobile.setText(mobile);
                    edtRefer.setText(Constant.FRIEND_CODE_VALUE);
                    break;
            }
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        } else {
            lytLogin.setVisibility(View.VISIBLE);
            lytVerify.setVisibility(View.GONE);
            lytResetPass.setVisibility(View.GONE);
            lytVerify.setVisibility(View.GONE);
            lytSignUp.setVisibility(View.GONE);
        }

        tvSignUp.setOnClickListener(v -> {
            otpFor = "new_user";
            edtMobileVerify.setText("");
            edtMobileVerify.setEnabled(true);
            edtCountryCodePicker.setCcpClickable(forMultipleCountryUse);
            lytOTP.setVisibility(View.GONE);
            lytVerify.setVisibility(View.VISIBLE);
            lytVerify.startAnimation(animShow);
        });
        tvForgotPass.setOnClickListener(v -> {
            otpFor = "exist_user";
            edtMobileVerify.setText("");
            edtMobileVerify.setEnabled(true);
            edtCountryCodePicker.setCcpClickable(forMultipleCountryUse);
            lytOTP.setVisibility(View.GONE);
            lytVerify.setVisibility(View.VISIBLE);
            lytVerify.startAnimation(animShow);
        });

        btnLogin.setOnClickListener(v -> {
            mobile = edtLoginMobile.getText().toString();
            final String password = edtLoginPassword.getText().toString();

            if (ApiConfig.CheckValidation(mobile, false, false)) {
                edtLoginMobile.requestFocus();
                edtLoginMobile.setError(getString(R.string.enter_mobile_no));
            } else if (ApiConfig.CheckValidation(mobile, false, true)) {
                edtLoginMobile.requestFocus();
                edtLoginMobile.setError(getString(R.string.enter_valid_mobile_no));
            } else if (ApiConfig.CheckValidation(password, false, false)) {
                edtLoginPassword.requestFocus();
                edtLoginPassword.setError(getString(R.string.enter_pass));
            } else if (ApiConfig.isConnected(activity)) {
                UserLogin(mobile, password);
            }

        });
        btnRegister.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String email = "" + edtEmail.getText().toString().trim();
            final String password = edtPassword.getText().toString().trim();
            String confirmPassword = edtConfirmPassword.getText().toString().trim();
            if (ApiConfig.CheckValidation(name, false, false)) {
                edtName.requestFocus();
                edtName.setError(getString(R.string.enter_name));
            } else if (ApiConfig.CheckValidation(email, false, false)) {
                edtEmail.requestFocus();
                edtEmail.setError(getString(R.string.enter_email));
            } else if (ApiConfig.CheckValidation(email, true, false)) {
                edtEmail.requestFocus();
                edtEmail.setError(getString(R.string.enter_valid_email));
            } else if (ApiConfig.CheckValidation(password, false, false)) {
                edtConfirmPassword.requestFocus();
                edtPassword.setError(getString(R.string.enter_pass));
            } else if (ApiConfig.CheckValidation(confirmPassword, false, false)) {
                edtConfirmPassword.requestFocus();
                edtConfirmPassword.setError(getString(R.string.enter_confirm_pass));
            } else if (!password.equals(confirmPassword)) {
                edtConfirmPassword.requestFocus();
                edtConfirmPassword.setError(getString(R.string.pass_not_match));
            } else if (!chPrivacy.isChecked()) {
                Toast.makeText(activity, getString(R.string.alert_privacy_msg), Toast.LENGTH_LONG).show();
            } else if (ApiConfig.isConnected(activity)) {
                UserSignUpSubmit(name, password);
            }
        });


        imgVerifyClose.setOnClickListener(v -> {
            lytOTP.setVisibility(View.GONE);
            lytVerify.setVisibility(View.GONE);
            lytVerify.startAnimation(animHide);
            edtMobileVerify.setText("");
            edtMobileVerify.setEnabled(true);
            edtCountryCodePicker.setCcpClickable(forMultipleCountryUse);
            pinViewOTP.setText("");
        });
        imgResetPasswordClose.setOnClickListener(v -> {
            edtResetPass.setText("");
            edtResetCPass.setText("");
            lytResetPass.setVisibility(View.GONE);
            lytResetPass.startAnimation(animHide);
        });
        imgSignUpClose.setOnClickListener(v -> {
            lytSignUp.setVisibility(View.GONE);
            lytSignUp.startAnimation(animHide);
            tvMobile.setText("");
            edtName.setText("");
            edtEmail.setText("");
            edtPassword.setText("");
            edtConfirmPassword.setText("");
            edtRefer.setText("");
        });
        imgWebViewClose.setOnClickListener(v -> {
            lytWebView.setVisibility(View.GONE);
            lytWebView.startAnimation(animHide);
        });

    }

    public void UserLogin(String username, String password) {

        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_NAME, username);
        params.put(Constant.PASSWORD, password);
        ApiConfig.RequestToVolley((result, response) -> {

            //System.out.println ("============login res " + response);
            if (result) {
                try {
                        StartMainActivity(username, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, activity, Constant.LoginUrl, params, true);
    }


    public void UserSignUpSubmit(String name, String password) {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.TYPE, Constant.REGISTER);
        params.put(Constant.NAME, name);
        params.put(Constant.PASSWORD, password);
        params.put(Constant.COUNTRY_CODE, session.getData(Constant.COUNTRY_CODE));
        params.put(Constant.MOBILE, mobile);
        params.put(Constant.FCM_ID, "" + session.getData(Constant.FCM_ID));
        params.put(Constant.REFERRAL_CODE, Constant.randomAlphaNumeric(8));
        params.put(Constant.FRIEND_CODE, edtRefer.getText().toString().trim());
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    UserService userService = db.userService();
                    User user = new User();
                    user.setUsername(name);
                    user.setPassword(password);
                    userService.insertAll(user);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }, activity, Constant.RegisterUrl, params, true);
    }

    public void StartMainActivity(String username, String password) {
        try {
            UserService userService = db.userService();
            User user = userService.login(username,password);

            session.setData(Constant.USER_ID, String.valueOf(user.getUserId()));

            MainActivity.homeClicked = false;
            MainActivity.categoryClicked = false;
            MainActivity.favoriteClicked = false;
            MainActivity.drawerClicked = false;

            Intent intent = new Intent(activity, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Constant.FROM, "");
            if (from != null && from.equals("checkout")) {
                intent.putExtra("total", ApiConfig.StringFormat("" + Constant.FLOAT_TOTAL_AMOUNT));
                intent.putExtra(Constant.FROM, "checkout");
            } else if (from != null && from.equals("tracker")) {
                intent.putExtra(Constant.FROM, "tracker");
            }
            startActivity(intent);

            finish();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public SpannableString underlineSpannable(String text) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        return spannableString;
    }

//    public void GetContent(final String type, final String key) {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constant.SETTINGS, Constant.GetVal);
//        params.put(type, Constant.GetVal);
//
//        ApiConfig.RequestToVolley((result, response) -> {
//            if (result) {
//                try {
//                    JSONObject obj = new JSONObject(response);
//                    if (!obj.getBoolean(Constant.ERROR)) {
//
//                        String privacyStr = obj.getString(key);
//                        webView.setVerticalScrollBarEnabled(true);
//                        webView.loadDataWithBaseURL("", privacyStr, "text/html", "UTF-8", "");
//                    } else {
//                        Toast.makeText(activity, obj.getString(Constant.MESSAGE), Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, activity, Constant.SETTING_URL, params, false);
//    }

//    public void PrivacyPolicy() {
//        tvPrivacyPolicy.setClickable(true);
//        tvPrivacyPolicy.setMovementMethod(LinkMovementMethod.getInstance());
//
//        String message = getString(R.string.msg_privacy_terms);
//        String s2 = getString(R.string.terms_conditions);
//        String s1 = getString(R.string.privacy_policy);
//        final Spannable wordToSpan = new SpannableString(message);
//
//        wordToSpan.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(View view) {
//                GetContent(Constant.GET_PRIVACY, "privacy");
//                try {
//                    Thread.sleep(500);
//                    lytWebView.setVisibility(View.VISIBLE);
//                    lytWebView.startAnimation(animShow);
//                } catch (Exception e) {
//                    e.printStackTrace();
//
//                }
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
//                ds.isUnderlineText();
//            }
//        }, message.indexOf(s1), message.indexOf(s1) + s1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        wordToSpan.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(View view) {
//                GetContent(Constant.GET_TERMS, "terms");
//                try {
//                    Thread.sleep(500);
//                    lytWebView.setVisibility(View.VISIBLE);
//                    lytWebView.startAnimation(animShow);
//                } catch (Exception e) {
//                    e.printStackTrace();
//
//                }
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
//                ds.isUnderlineText();
//            }
//        }, message.indexOf(s2), message.indexOf(s2) + s2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tvPrivacyPolicy.setText(wordToSpan);
//    }

    public void hideKeyboard(Activity activity, View root) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(root.getApplicationWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}