package com.example.RetailApp;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.RetailApp.component.BMAAlertDialog;
import com.example.RetailApp.fragment.BMAFragment;
import com.example.RetailApp.fragment.DashBoardFragment;
import com.example.RetailApp.fragment.FragmentLoader;
import com.example.RetailApp.fragment.HeaderFragment;
import com.example.RetailApp.fragment.ProfileFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import helper.MainActivityHelper;
import helper.Misc;
import util.BMAConstants;

public class MainActivity extends AppCompatActivity implements LocationListener {

    FragmentManager fragmentManager;
    private Fragment fragment = null;
    private Location currentLocation;
    private LocationManager mLocationManager;
    Misc misc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityHelper.setApplicationObj(this);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        fragmentManager = getSupportFragmentManager();
        fragment = new DashBoardFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(BMAConstants.menu_id, "home");
        fragment.setArguments(bundle);
        this.updateFragment(fragment);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView profileImageName = headerView.findViewById(R.id.profileImageName);
       // this.agentName = MainActivityHelper.applicationHelper().getSharedPrefrences(BMAConstants.LOGIN_INFO).getString("agent_name", "");
        profileImageName.setText("Umesh Sah");


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    fragment = new DashBoardFragment();
                    fragment.setArguments(bundle);
                    MainActivity.this.updateFragment(fragment);
                }else if (id == R.id.profile) {
                    fragment = new ProfileFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(BMAConstants.menu_id, "Profile");
                    bundle.putString(BMAConstants.HEADER_TXT,"Profile");
                    fragment.setArguments(bundle);
                    MainActivity.this.updateFragment(fragment, true);

                } else if (id == R.id.nav_logout) {
                    BMAAlertDialog bmaAlertDialog = new BMAAlertDialog(MainActivity.this, true, false) {
                        @Override
                        public void onConfirmation() {
                            resetDeviceData();
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            MainActivity.this.finish();
                        }
                    };
                    bmaAlertDialog.show("Are you sure you want to logout?");
                }
//                else  if (id == R.id.notification) {
//                    fragment = new BMANotificationFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString(BMAConstants.HEADER_TXT, getString(R.string.notification));
//                    bundle.putString(BMAConstants.menu_id, getString(R.string.notification));
//                    fragment.setArguments(bundle);
//                    MainActivity.this.updateFragment(fragment, true);
               // }



                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                assert drawer != null;
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    // This method is used to reset all device data or local data stored
    private void resetDeviceData() {
      //  String deviceToken= MainActivityHelper.applicationHelper().getSharedPrefrences(BMAConstants.NOTIF_INFO).getString("device_firebase_token",null);
        MainActivityHelper.applicationHelper().getSharedPrefrences(BMAConstants.LOGIN_INFO).edit().clear().commit();
        //SharedPreferences.Editor editor= MainActivityHelper.applicationHelper().getSharedPrefrences(BMAConstants.NOTIF_INFO).edit();
       // editor.putString("device_firebase_token", deviceToken);
        //editor.commit();
    }
// this method is used to call when we ant to close drawer
    public boolean closeDrawers() {
        if (this.drawerLayoutID() != null && this.drawerLayoutID().isDrawerOpen(GravityCompat.START)) {
            this.drawerLayoutID().closeDrawers();
            return true;
        }
        return false;
    }

    long previousTime;
    // this method is used to call when we ant to open drawer
    public void showSlider() {
        if (drawerLayoutID().isDrawerOpen(GravityCompat.START)) {
            drawerLayoutID().closeDrawer(GravityCompat.START);
        } else {
            drawerLayoutID().openDrawer(GravityCompat.START);
        }
    }

    // this method is used to return drawer layout id
    public DrawerLayout drawerLayoutID() {
        return findViewById(R.id.drawer_layout);
    }


    @Override
    protected void onStart() {
        Log.d("aaaaaa","onStartMainActivity");
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            BMAAlertDialog dialog = new BMAAlertDialog(this, true, false) {
                @Override
                public void onConfirmation() {
                    this.dismiss();
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                }
            };
            dialog.show(getString(R.string.enableLocation));
        }
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        misc=new Misc(this);
        if(misc.checkAndLocationRequestPermissions()) {
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 10, this);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20000, 10, this);
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        mLocationManager.removeUpdates(this);
    }


    @Override
    public void onBackPressed() {
        Log.d("aaaaa","MainActivity onback pressesd= "+getPageFragment());
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Log.d("aaaaa","pagename= "+ this.getPageFragment().getPageName());


            if(this.getPageFragment()!=null && !(this.getPageFragment() instanceof FragmentLoader) && this.getPageFragment().getPageName().equalsIgnoreCase("DashBoardFragment")){
                Log.d("aaaaa","ovetaskback "+previousTime+"                       curenttime = "+System.currentTimeMillis());
                if (2000 + previousTime > (previousTime = System.currentTimeMillis())) {
                    Log.d("aaaaa","MMMMovetaskback ");
                                moveTaskToBack(true);

                            } else {
                                Snackbar.make(findViewById(dataContainerResID()), getString(R.string.exitConfirmInfo), Snackbar.LENGTH_SHORT).show();
                            }
            }else {
                Log.d("aaaaa","superonbackpressed ");
                super.onBackPressed();
            }
//            if (this.getPageFragment() != null) {//&& this.getPageFragment().onBackPressed()) {
//
//                if (this.getPageFragment() instanceof FragmentLoader) {
//                    FragmentLoader fragmentLoader = (FragmentLoader) this.getPageFragment();
//
//                    if (this.getSupportFragmentManager().getBackStackEntryCount() == 1) {
//                        if (fragmentLoader.getCurrentPage().getClass().getSimpleName().equalsIgnoreCase("AllTasksFragment")) {
//                            if (2000 + previousTime > (previousTime = System.currentTimeMillis())) {
//                                moveTaskToBack(true);
//                            } else {
//                                Snackbar.make(findViewById(dataContainerResID()), getString(R.string.exitConfirmInfo), Snackbar.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            this.redirectToHomeMenu();
//                            //   super.onBackPressed();
//                        }
//                    } else {
//
//                        if (fragmentLoader.getCurrentPage().getClass().getSimpleName().equalsIgnoreCase("AllTasksFragment")) {
//                            if (2000 + previousTime > (previousTime = System.currentTimeMillis())) {
//                                moveTaskToBack(true);
//                            } else {
//                                Snackbar.make(findViewById(dataContainerResID()), getString(R.string.exitConfirmInfo), Snackbar.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            this.redirectToHomeMenu();
//                        }
//                    }
//                } else {else
                //    super.onBackPressed();
//                }
//            }
       }
    }

//    public String getServiceCenterId() {
//        return MainActivityHelper.applicationHelper().getSharedPrefrences(BMAConstants.LOGIN_INFO).getString("service_center_id", "");
//
//    }
//
//    public String getEngineerId() {
//        return MainActivityHelper.applicationHelper().getSharedPrefrences(BMAConstants.LOGIN_INFO).getString("engineerID", "");
//
//    }


    // when we nat to move direclty home menu from any page then we call this method
    public void redirectToHomeMenu() {
        //  this.closeDrawers();
        clearBackStack();
        FragmentLoader fragmentLoader = new FragmentLoader();
        Bundle bundle = new Bundle();
        bundle.putString(BMAConstants.menu_id, "home");
        fragmentLoader.setArguments(bundle);
        this.updateFragment(fragmentLoader, true, null);
    }

    // Call this method to clear all back stack
    public void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void updateFragment(Fragment fragment, Integer drawable) {
        this.updateFragment(fragment, false, drawable);

    }
// this method used to change fragment
    public void updateFragment(Fragment fragment) {
        this.updateFragment(fragment, false);

    }
    // this method used to change fragment
    public void updateFragment(Fragment fragment, boolean isAddToBackStack) {
        this.updateFragment(fragment, isAddToBackStack, null);

    }
    // this method used to change fragment
    public void updateFragment(Fragment fragment, boolean isAddToBackStack, Integer headerImageDrawable) {
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!(fragment instanceof FragmentLoader))
            fragment.setTargetFragment(this.getPageFragment(), 101);
        fragmentTransaction.replace(dataContainerResID(), fragment);

        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }


        Bundle fragmentBundle = fragment.getArguments();
        if (headerImageDrawable != null)
            fragmentBundle.putInt("drawable", headerImageDrawable);
        String headerTitleText;
        headerFragment = new HeaderFragment();

        headerFragment.setArguments(fragmentBundle);
        fragmentTransaction.replace(R.id.headerFragmentConatiner, headerFragment);
        fragmentTransaction.commit();

    }
    HeaderFragment headerFragment;
    public HeaderFragment getHeaderFragment(){
        return this.headerFragment;

    }

    // this method used to get page fragment
    public BMAFragment getPageFragment() {
        Fragment fragment = this.findFragmentById(dataContainerResID());
        return fragment != null ? (BMAFragment) fragment : null;
    }
// Using this method by passing fragment id can get fragment
    public Fragment findFragmentById(int fragmentContainerID) {
        return this.getSupportFragmentManager().findFragmentById(fragmentContainerID);
    }

    //// this method used to return contianer id
    @IdRes
    protected int dataContainerResID() {
        try {
            return R.id.container;
        } catch (NoSuchFieldError e) {
            return 0;
        }
    }

//    public void loadtabFragment(int index) {
//        if (this.fragment != null && this.fragment instanceof FragmentLoader) {
//            ((FragmentLoader) fragment).loadTab(index);
//        }
//
//    }

//    public Fragment getAllTaskFragment() {
//        return fragment != null && this.fragment instanceof FragmentLoader ? ((FragmentLoader) fragment).customFragmentPageAdapter.getItem(3) : null;
//
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("aaaaa","MAinactivity onRequestPermissionsResult");
        if (getPageFragment() != null)
            getPageFragment().onRequestPermissionsResult( requestCode,permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("aaaaa","MainActivity0");
        if (getPageFragment() != null)
            getPageFragment().onActivityResult(requestCode, resultCode, data);
    }


// this method called for seraching
    public void startSearch(String filterStr) {
        if (getPageFragment() != null) {
            getPageFragment().startSearch(filterStr);
        }
    }
    public String getPinCode() {
        if (misc == null)
            misc = new Misc(this);
        return misc.getLocationPinCode();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
