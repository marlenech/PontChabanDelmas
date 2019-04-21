package com.italikdesign.pont.chaban;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
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


import com.italikdesign.inappbilling.util.IabHelper;
import com.italikdesign.inappbilling.util.IabResult;
import com.italikdesign.inappbilling.util.Inventory;
import com.italikdesign.inappbilling.util.Purchase;
import com.kobakei.ratethisapp.RateThisApp;
import com.onesignal.OneSignal;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "com.italikdesign.inappbilling";

    public static final String ITEM_SKU = "ad_one";

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    CardView cardview = null;
    IabHelper mHelper;
    boolean mIsPremium = false;
    boolean mIsUserPremium = false;
    boolean searchAllowed = false;

    //Notifs
    boolean notif00a07 = true;
    boolean notif07a0930 = true;
    boolean notif0930a1130 = true;
    boolean notif1130a14 = true;
    boolean notif14a1630 = true;
    boolean notif1630a19 = true;
    boolean notif19a00 = true;
    boolean notifsamedi = true;
    boolean notifdimanche = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        toolbar = findViewById(R.id.toolbar);


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

        //AlertDialog Rate

        // Custom condition: 3 days and 5 launches
        RateThisApp.Config config = new RateThisApp.Config(3, 5);
        config.setTitle(R.string.my_own_title);
        config.setMessage(R.string.my_own_message);
        config.setYesButtonText(R.string.my_own_rate);
        config.setNoButtonText(R.string.my_own_thanks);
        config.setCancelButtonText(R.string.my_own_cancel);
        RateThisApp.init(config);

        // Monitor launch times and interval from installation
        RateThisApp.onStart(this);
        // If the condition is satisfied, "Rate this app" dialog will be shown
        RateThisApp.showRateDialogIfNeeded(this);

        //Notifs
        SharedPreferences prefs1 = getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        notif00a07 = prefs1.getBoolean("notif00a07", true);

        SharedPreferences prefs2 = getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        notif07a0930 = prefs2.getBoolean("notif07a0930", true);

        SharedPreferences prefs3 = getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        notif0930a1130 = prefs3.getBoolean("notif0930a1130", true);

        SharedPreferences prefs4 = getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        notif1130a14 = prefs4.getBoolean("notif1130a14", true);

        SharedPreferences prefs5 = getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        notif14a1630 = prefs5.getBoolean("notif14a1630", true);

        SharedPreferences prefs6 = getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        notif1630a19 = prefs6.getBoolean("notif1630a19", true);

        SharedPreferences prefs7 = getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        notif19a00 = prefs7.getBoolean("notif19a00", true);

        SharedPreferences prefs8 = getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        notifsamedi = prefs8.getBoolean("notifsamedi", true);

        SharedPreferences prefs9 = getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        notifdimanche = prefs9.getBoolean("notifdimanche", true);

        if (notif00a07) {
            OneSignal.sendTag("00a07", "00a07");
        }else{
            OneSignal.deleteTag("00a07");
        }
        if (notif07a0930) {
            OneSignal.sendTag("07a0930", "07a0930");
        }else{
            OneSignal.deleteTag("07a0930");
        }
        if (notif0930a1130) {
            OneSignal.sendTag("0930a1130", "0930a1130");
        }else{
            OneSignal.deleteTag("0930a1130");
        }
        if (notif1130a14) {
            OneSignal.sendTag("1130a14", "1130a14");
        }else{
            OneSignal.deleteTag("1130a14");
        }
        if (notif14a1630) {
            OneSignal.sendTag("14a1630", "14a1630");
        }else{
            OneSignal.deleteTag("14a1630");
        }
        if (notif1630a19) {
            OneSignal.sendTag("1630a19", "1630a19");
        }else{
            OneSignal.deleteTag("1630a19");
        }
        if (notif19a00) {
            OneSignal.sendTag("19a00", "19a00");
        }else{
            OneSignal.deleteTag("19a00");
        }
        if (notifsamedi) {
            OneSignal.sendTag("samedi", "samedi");
        }else{
            OneSignal.deleteTag("samedi");
        }
        if (notifdimanche) {
            OneSignal.sendTag("dimanche", "dimanche");
        }else{
            OneSignal.deleteTag("dimanche");
        }



        //InAppBilling


        String base64EncodedPublicKey = getString(R.string.base64);
        // compute your public key and store it in base64EncodedPublicKey
        mHelper = new IabHelper(this, base64EncodedPublicKey);



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
            Preferences fragment = new Preferences();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
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
                complain("Erreur lors de l'achat: " + result);
                // Handle error

                return;


            }
            if (!verifyDeveloperPayload(purchase)) {
                complain("Erreur lors de l'achat. Authentification non reconnue.");

                return;

            }
            Log.d(TAG, "Purchase successful.");

            if (purchase.getSku().equals(ITEM_SKU)) {
                // bought the premium upgrade!
                Log.d(TAG, "Vous avez acheté la version sans publicité. Félicitation.");
                alert("Merci pour votre achat!");
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
            Log.d(TAG, "L'inventaire des achats est terminé.");
            if (result.isFailure()) {
                complain("Echec lors de l'inventaire des achats: " + result);

                return;
            }

        /*if (inventory.hasPurchase(PREM_SKU)) {

            mHelper.consumeAsync(inventory.getPurchase(PREM_SKU), null);
        }*/

            Log.d(TAG, "L'inventaire a été réalisé avec succés.");


            Purchase premiumPurchase = inventory.getPurchase(ITEM_SKU);
            mIsPremium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));
            Log.d(TAG, "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));
            if (mIsPremium) {
                searchAllowed = true;
                mIsUserPremium = true;
                Log.d(TAG, "Vous devez être premium...");
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
        Log.e(TAG, "**** PontChaban Error: " + message);
        alert("Error: " + message);
    }

    @SuppressLint("LongLogTag")
    void alert(String message) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        Log.d(TAG, "Message: " + message);
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
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.partage));
            startActivity(Intent.createChooser(shareIntent, getString(R.string.app_name)));


        } else if (id == R.id.nav_github) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://github.com/marlenech/PontChabanDelmas"));
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
