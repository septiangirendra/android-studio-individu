package com.septian.projectindividual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.septian.projectindividual.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;
    private OnBackPressedListener onBackPressedListener;
    String myStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final Fragment[] fragments = {null};
        switch (item.getItemId()){
            case R.id.toolbar_search_peserta:
                fragments[0] = new PencarianPesertaFragment();
                getSupportActionBar().setTitle("Search Peserta");
                callFragment(fragments[0]);
                break;
            case R.id.toolbar_search_instructor:
                fragments[0] = new PencarianInstrukturFragment();
                getSupportActionBar().setTitle("Search Instruktur");
                callFragment(fragments[0]);
                break;
            case R.id.toolbar_search_materi:
                fragments[0] = new PencarianMateriFragment();
                getSupportActionBar().setTitle("Search Materi");
                callFragment(fragments[0]);
                break;
            case R.id.toolbar_search_materi_ikut:
                fragments[0] = new PencarianMateriPesertaFragment();
                getSupportActionBar().setTitle("Search Materi Peserta");
                callFragment(fragments[0]);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initView() {
        // CUSTOM Toolbar
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitleTextColor(Color.WHITE);

        //set menu
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Fragment fragmentMenu = null;
        myStr = "home";
        if (extras != null)
            if (extras != null) {
                myStr = extras.getString("keyName");
            } else {
                myStr = "home";
            }

        switch (myStr) {
            case "home":
                //default fragment dibuka pertama kali
               getSupportActionBar().setTitle("Home");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, new HomeFragment())
                        .commit();
                binding.navView.setCheckedItem(R.id.nav_home);
                break;
            case "instruktur":
             getSupportActionBar().setTitle("Instruktur");
                fragmentMenu = new InstrukturFragment();
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragmentMenu);
                binding.navView.setCheckedItem(R.id.nav_instruktur);
                break;
            case "materi":
                getSupportActionBar().setTitle("Materi");
                fragmentMenu = new MateriFragment();
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragmentMenu);
                binding.navView.setCheckedItem(R.id.nav_materi);
                break;
            case "peserta":
                getSupportActionBar().setTitle("Peserta");
                fragmentMenu = new PesertaFragment();
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragmentMenu);
                binding.navView.setCheckedItem(R.id.nav_materi);
                break;
            case "kelas":
                getSupportActionBar().setTitle("Kelas");
                fragmentMenu = new KelasFragment();
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragmentMenu);
                binding.navView.setCheckedItem(R.id.nav_materi);
                break;
            case "detail_kelas":
                getSupportActionBar().setTitle("Detail Kelas");
                fragmentMenu = new DetailKelasFragment();
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragmentMenu);
                binding.navView.setCheckedItem(R.id.nav_materi);
                break;
        }

        // clickable pada button di custom header
        NavigationView navigationView = findViewById(R.id.navView);
        View headerView = getLayoutInflater().inflate(R.layout.nav_header_layout,
                navigationView, false);
        navigationView.addHeaderView(headerView);

////        Button btn_go_to_web = headerView.findViewById(R.id.btn_go_to_web);
//        btn_go_to_web.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Test Header Click",
//                        Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://www.inixindo.id"));
//                startActivity(intent);
//            }
//        });

        // custom toolbar


// default fragment dibuka pertama kali
        getSupportActionBar().setTitle("Home");
//        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
//        binding.navView.setCheckedItem(R.id.nav_home);

        // membuka drawer
        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar,
                R.string.open, R.string.close);
        binding.drawer.addDrawerListener(toggle);
        // membuat anak panah drawer
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        // sinkronisasi drawer
        toggle.syncState();

        // salah satu menu navigasi dipilih
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_instruktur:
                        fragment = new InstrukturFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_materi:
                        fragment = new MateriFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_peserta:
                        fragment = new PesertaFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_kelas:
                        fragment = new KelasFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_detail_kelas:
                        fragment = new DetailKelasFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
//                    case R.id.nav_pencarian:
//                        fragment = new PencarianFragment();
//                        binding.drawer.closeDrawer(GravityCompat.START);
//                        callFragment(fragment);
//                        break;
                }
                return true;
            }
        });
    }

    private void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // mengantisipasi tombol backpressed
    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            getSupportActionBar().setTitle("Home");
            binding.navView.setCheckedItem(R.id.nav_home);
            onBackPressedListener.doBack();
            binding.drawer.closeDrawer(GravityCompat.START);
        } else if (onBackPressedListener == null) {
            finish();
            super.onBackPressed();
        }
    }

    public interface OnBackPressedListener {
        void doBack();
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    protected void onDestroy() {
        onBackPressedListener = null;
        super.onDestroy();
    }

}