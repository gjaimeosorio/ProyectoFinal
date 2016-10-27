package com.dglivesports.com.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private String name, password, email, tablas;
    private String[] opciones = new String[] {"Resultados","Partidos y tablas","Noticias","Videos","Social",
            "Notificaciones..[3]","Configuración","Cerrar Sesión"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager2);
        mViewPager.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener(){
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

        ActionBar.Tab tab = actionBar.newTab().setText("Ayer").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Hoy").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Mañana").setTabListener(tabListener);
        actionBar.addTab (tab);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);

            }
        });

        /////////////////////navigation Drawer///////////////////////////

        actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal_2);
        listView = (ListView) findViewById(R.id.menuIzq_2);

        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case(0):
                        Intent intent = new Intent(MainActivity.this, ResultadosActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case(1):
                        Intent intent2 = new Intent(MainActivity.this, ParttablasActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case(2):
                        Intent intent3 = new Intent(MainActivity.this, NoticiassActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case(3):
                        Intent intent4 = new Intent(MainActivity.this, VideosActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case(4):
                        Intent intent8 = new Intent(MainActivity.this, SocialActivity.class);
                        startActivity(intent8);
                        finish();
                        break;
                    case(5):
                        Intent intent5 = new Intent(MainActivity.this, NotificacionesActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                    case(6):
                        Intent intent6 = new Intent(MainActivity.this, ConfiguracionesActivity.class);
                        startActivity(intent6);
                        finish();
                        break;
                    case(7):
                        Intent intent7 = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent7);
                        finish();
                        break;
                }

                listView.setItemChecked(i,true);
                drawerLayout.closeDrawer(listView);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.abierto, R.string.cerrado);

        drawerLayout.setDrawerListener(drawerToggle);

    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new AyerFragment();
                case 1: return new AyerFragment();
                case 2: return new AyerFragment();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
