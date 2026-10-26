# AGHAI TV — missing configuration checklist

The current GitHub Pages app is a web app. It does not contain Firebase SDK setup or AdMob integration yet.

## Missing values

- Firebase Web App config: apiKey, authDomain, projectId, storageBucket, messagingSenderId, appId.
- Firebase Realtime Database URL (needed for a live shared viewer count).
- Firebase Realtime Database security rules (must be configured before public writes).
- AdMob Android App ID (format: ca-app-pub-...~...).
- AdMob banner Ad Unit ID (format: ca-app-pub-.../...).
- Optional interstitial Ad Unit ID (format: ca-app-pub-.../...).

## Important limitations

- Do not paste Firebase service-account JSON, private keys, or admin credentials into this public repository.
- Firebase web config alone does not make a secure global counter; database URL and suitable security rules are also required.
- AdMob's native Android ads cannot be activated by adding an Ad Unit ID to this GitHub Pages HTML page. They require an Android app project with the Google Mobile Ads SDK and a new APK/AAB build.
- Until the missing values and platform integration are supplied, the app must not claim that shared live viewers or ads are active.
- Preserve the existing AGHAI TV page and its existing features while adding integrations.

## Where to get the values

1. Firebase Console → Project settings → General → Your apps → Web app → SDK setup/config.
2. Firebase Console → Build → Realtime Database → copy the database URL and configure secure rules.
3. AdMob Console → Apps → select/create the Android app → App settings for the App ID.
4. AdMob Console → Ad units → create/copy banner and (if wanted) interstitial unit IDs.
