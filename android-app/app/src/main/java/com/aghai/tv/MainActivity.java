package com.aghai.tv;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.AdError;

public class MainActivity extends Activity {
    private WebView web;
    private InterstitialAd exitInterstitial;
    private boolean exitAfterAd = false;
    // Google-provided TEST ad unit. Replace with your own AdMob interstitial unit before production.
    private static final String INTERSTITIAL_TEST_AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);

        web = new WebView(this);
        web.setLayoutParams(new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        web.setWebViewClient(new WebViewClient());
        root.addView(web);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-2437759700185612/7719735503");
        root.addView(adView, new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        setContentView(root);
        web.loadUrl("https://ahmedsunny538-png.github.io/Newtvaghai/");

        MobileAds.initialize(this, initializationStatus -> {
            AdRequest request = new AdRequest.Builder().build();
            adView.loadAd(request);
            loadExitInterstitial();
        });
    }

    private void loadExitInterstitial() {
        AdRequest request = new AdRequest.Builder().build();
        InterstitialAd.load(this, INTERSTITIAL_TEST_AD_UNIT_ID, request,
            new InterstitialAdLoadCallback() {
                @Override public void onAdLoaded(InterstitialAd ad) {
                    exitInterstitial = ad;
                    exitInterstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override public void onAdDismissedFullScreenContent() {
                            exitInterstitial = null;
                            if (exitAfterAd) finish();
                            else loadExitInterstitial();
                        }
                        @Override public void onAdFailedToShowFullScreenContent(AdError error) {
                            exitInterstitial = null;
                            if (exitAfterAd) finish();
                        }
                    });
                    if (exitAfterAd) exitInterstitial.show(MainActivity.this);
                }
                @Override public void onAdFailedToLoad(LoadAdError error) {
                    exitInterstitial = null;
                    if (exitAfterAd) finish();
                }
            });
    }

    @Override public void onBackPressed() {
        if (web != null && web.canGoBack()) {
            web.goBack();
        } else if (exitInterstitial != null) {
            exitAfterAd = true;
            exitInterstitial.show(this);
        } else {
            exitAfterAd = true;
            finish();
        }
    }

    @Override protected void onPause() {
        if (web != null) web.onPause();
        super.onPause();
    }

    @Override protected void onResume() {
        super.onResume();
        if (web != null) web.onResume();
    }

    @Override protected void onDestroy() {
        if (web != null) web.destroy();
        super.onDestroy();
    }
}