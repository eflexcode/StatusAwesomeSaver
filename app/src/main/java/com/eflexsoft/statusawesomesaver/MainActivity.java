package com.eflexsoft.statusawesomesaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.eflexsoft.statusawesomesaver.fragments.WhatsAppFragment;
import com.eflexsoft.statusawesomesaver.fragments.WaBusinessFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bnv);
        frameLayout = findViewById(R.id.frame);

        FrameLayout container =findViewById(R.id.fragment_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 4);
        }

        StartAppSDK.init(this, "your id", false);
        StartAppAd.disableSplash();
        StartAppSDK.enableReturnAds(false);
        if (container != null && container.getChildCount() < 1) {
            container.addView(new Banner(this), new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, Gravity.CENTER));
            StartAppAd.showAd(this);
        }

        if (savedInstanceState == null) {
            fragment = new WhatsAppFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.whatsapp:
                        fragment = new WhatsAppFragment();
                        break;
                    case R.id.whatsapp_business:
                        fragment = new WaBusinessFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();

                return true;
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 4 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "App cannot function whit out these permissions", Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    public void onBackPressed() {

//        WhatsAppFragment whatsAppFragment = new WhatsAppFragment();

        if (fragment instanceof WhatsAppFragment){
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new WhatsAppFragment()).commit();
            super.onBackPressed();
        }else {

            bottomNavigationView.setSelectedItemId(R.id.whatsapp);
        }

    }
}