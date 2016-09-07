package com.italikdesign.pont.chaban;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.italikdesign.inappbilling.util.IabException;
import com.italikdesign.inappbilling.util.IabHelper;
import com.italikdesign.inappbilling.util.IabResult;
import com.italikdesign.inappbilling.util.Inventory;
import com.italikdesign.inappbilling.util.Purchase;
import com.onesignal.OneSignal;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "com.italikdesign.inappbilling";

    public static final String ITEM_SKU = "test8";

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    IabHelper mHelper;
    boolean mIsPremium = false;
    boolean mIsUserPremium = false;
    boolean searchAllowed = false;


    static boolean active = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Le Pont Chaban Delmas");
        toolbar.setSubtitle("Dates et Heures de Levées");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //How to change elements in the header programatically
        View headerView = navigationView.getHeaderView(0);
        TextView emailText = (TextView) headerView.findViewById(R.id.subnav);
        emailText.setText("Les Levées");

        navigationView.setNavigationItemSelectedListener(this);

        //Preferences App-billing

        SharedPreferences prefs = getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        mIsPremium = prefs.getBoolean("premium", false);




        if (mIsPremium) {

            MainFragmentDisAd fragment = new MainFragmentDisAd();

            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else {
            MainFragment fragment = new MainFragment();

            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }



            //InAppBilling


        String base64EncodedPublicKey = getString(R.string.base64);
        // compute your public key and store it in base64EncodedPublicKey
        mHelper = new IabHelper(this, base64EncodedPublicKey);

        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);

        // Showing status
        if (status != ConnectionResult.SUCCESS) {

            mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
                                   @SuppressLint("LongLogTag")
                                   public void onIabSetupFinished(IabResult result) {
                                       if (!result.isSuccess()) {
                                           // Oh no, there was a problem.
                                           Log.d(TAG, "Problem setting up In-app Billing: " + result);

                                       }

                                       // Hooray, IAB is fully set up. Now, let's get an inventory of

                                       Log.d(TAG, "Setup successful. Querying inventory.");
                                       mHelper.queryInventoryAsync(mGotInventoryListener);


                                   }
                               }
            );
        }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (!mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        else {
            Log.i(TAG, "onActivityResult handled by IABUtil.");
        }

    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        @SuppressLint("LongLogTag")
        @Override
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            Log.d(TAG, "Achat validé: " + result + ", achat: "
                    + purchase);
            if (result.isFailure()) {
                complain("erreur lors de l'achat: " + result);
                // Handle error

                return;


            }
            if (!verifyDeveloperPayload(purchase)) {
                complain("Error purchasing. Authenticity verification failed.");

                return;

            }
            Log.d(TAG, "Purchase successful.");

            if (purchase.getSku().equals(ITEM_SKU)) {
                // bought the premium upgrade!
                Log.d(TAG, "Purchase is premium upgrade. Congratulating user.");
                alert("Thank you for upgrading to premium!");
                mIsPremium = true;
                SharedPreferences prefs = getBaseContext().getSharedPreferences(
                        "com.italikdesign.pont.chaban", 0);
                prefs.edit().putBoolean("premium", true).apply();


            }

        }

    };


    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        @SuppressLint("LongLogTag")
        @Override
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {
            Log.d(TAG, "Query inventory finished.");
            if (result.isFailure()) {
                complain("Failed to query inventory: " + result);

                return;
            }

        /*if (inventory.hasPurchase(PREM_SKU)) {

            mHelper.consumeAsync(inventory.getPurchase(PREM_SKU), null);
        }*/

            Log.d(TAG, "Query inventory was successful.");


            Purchase premiumPurchase = inventory.getPurchase(ITEM_SKU);
            mIsPremium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));
            Log.d(TAG, "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));
            if (mIsPremium) {
                searchAllowed = true;
                mIsUserPremium = true;
                Log.d(TAG, "Should be premium by now...");
                SharedPreferences prefs = getBaseContext().getSharedPreferences(
                        "com.italikdesign.pont.chaban", 0);
                prefs.edit().putBoolean("premium", true).apply();


            }

            Log.d(TAG, "Initial inventory query finished; enabling main UI.");


        }


    };

    boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();

    /*
     * TODO: verify that the developer payload of the purchase is correct.
     * It will be the same one that you sent when initiating the purchase.
     *
     * WARNING: Locally generating a random string when starting a purchase
     * and verifying it here might seem like a good approach, but this will
     * fail in the case where the user purchases an item on one device and
     * then uses your app on a different device, because on the other device
     * you will not have access to the random string you originally
     * generated.
     *
     * So a good developer payload has these characteristics:
     *
     * 1. If two different users purchase an item, the payload is different
     * between them, so that one user's purchase can't be replayed to
     * another user.
     *
     * 2. The payload must be such that you can verify it even when the app
     * wasn't the one who initiated the purchase flow (so that items
     * purchased by the user on one device work on other devices owned by
     * the user).
     *
     * Using your own server to store and verify developer payloads across
     * app installations is recommended.
     */

        return true;

    }

/*public void consumeItem() {
    mHelper.queryInventoryAsync(mReceivedInventoryListener);

}*/

    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {

            if (result.isFailure()) {
                // Handle failure

            } else {
                searchAllowed = false;
                //mHelper.consumeAsync(inventory.getPurchase(PREM_SKU),
                //      mConsumeFinishedListener);
            }
        }

    };

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        @Override
        public void onConsumeFinished(Purchase purchase, IabResult result) {


            if (result.isSuccess()) {


            } else {
                // handle error

            }
        }

    };



    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mHelper != null)
            mHelper.dispose();
        mHelper = null;





    }

    @SuppressLint("LongLogTag")
    void complain(String message) {
        Log.e(TAG, "**** TrivialDrive Error: " + message);
        alert("Error: " + message);
    }

    @SuppressLint("LongLogTag")
    void alert(String message) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        Log.d(TAG, "Showing alert dialog: " + message);
        bld.create().show();
    }



    @SuppressLint("LongLogTag")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_agenda) {


            if (mIsPremium) {

                MainFragmentDisAd fragment = new MainFragmentDisAd();

                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            } else {
                MainFragment fragment = new MainFragment();

                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        }


        // Handle the camera action
        else if (id == R.id.nav_notification) {
            Preferences fragment = new Preferences();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_cadena) {

            Log.d(TAG,
                    "Upgrade button clicked; launching purchase flow for upgrade.");
    /*
     * TODO: for security, generate your payload here for verification. See
     * the comments on verifyDeveloperPayload() for more info. Since this is
     * a SAMPLE, we just use an empty string, but on a production app you
     * should carefully generate this.
     */
            String payload = "";

            mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001,
                    mPurchaseFinishedListener, payload);


        } else if (id == R.id.nav_apropos) {
            //Set the fragment initially
            AproposFragment fragment = new AproposFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_github) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://github.com/marlenech/PontChabanDelmas"));
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //for Pushbots (customPushReceiver)

    public static boolean isActive(){
        return active;
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
