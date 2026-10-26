# AGHAI TV — configuration status

## Already present in the Android project

- Google Mobile Ads SDK dependency is included in `android-app/app/build.gradle`.
- Android manifest already contains an AdMob App ID.
- `MainActivity.java` already creates and loads a banner ad.
- GitHub Actions workflow exists for building the Android app.

These existing settings have been preserved. Do not replace the current IDs unless you confirm they belong to the intended AdMob account.

## Still missing / not verified

- Firebase Web App config (`apiKey`, `authDomain`, `projectId`, `storageBucket`, `messagingSenderId`, `appId`).
- Firebase Realtime Database URL and properly configured database security rules (required for a shared live viewer count).
- Optional AdMob interstitial Ad Unit ID (no interstitial unit is currently configured in the Android source).
- Successful fresh Android build and real ad serving have not yet been verified.

## Safety and limitations

- Never add Firebase service-account JSON, private keys, or admin credentials to this public repository.
- A Firebase web config is not enough by itself for a secure global counter. Database URL, rules, and a suitable presence/counting design are needed.
- The GitHub Pages website itself cannot show native AdMob ads; the existing banner integration is in the Android wrapper.
- Until Firebase is configured and tested, the website must not claim that shared live viewers or global total views are active.
- Preserve the existing AGHAI TV page and its existing features.

## Where to get missing values

1. Firebase Console → Project settings → General → Your apps → Web app → SDK setup/config.
2. Firebase Console → Build → Realtime Database → copy the database URL and configure secure rules.
3. AdMob Console → Apps → select the Android app → Ad units → create/copy an interstitial unit ID if you want interstitial ads.
