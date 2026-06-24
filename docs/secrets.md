# Secrets and Local Configuration

This repository intentionally includes real service integrations in the base template. Keep real credentials local or in GitHub Environments. Commit only `.template` placeholders.

## Test Credentials

Every real config file is gitignored. For local compile/test smoke, copy each committed `*.template` to its non-template filename — same idea as `cp .env.example .env`.

macOS / Linux:

```bash
find app demo config -name '*.template' -exec sh -c 'cp -n "$1" "${1%.template}"' _ {} \;
```

Windows (PowerShell):

```powershell
Get-ChildItem -Recurse -Path app,demo,config -Filter *.template | ForEach-Object {
  $dest = $_.FullName -replace '\.template$',''
  if (-not (Test-Path $dest)) { Copy-Item $_.FullName $dest }
}
```

The copied files are syntactically valid placeholders, not real provider projects. They exist so Gradle plugins, manifest placeholders, and app startup can parse required config. They should not be used to assert provider behavior.

Testing rule: unit tests, screenshot tests, Robolectric tests, and normal Maestro E2E bind fake/no-op provider implementations for Firebase, Measure, AdMob/UMP, Branch, and Supabase. These tests must not fail because a third-party service is unavailable or because fake credentials cannot talk to a live backend. Provider dashboard verification belongs in explicit opt-in smoke jobs with real secrets.

Known safe test values:

```properties
ADMOB_APP_ID=ca-app-pub-3940256099942544~3347511713
ADMOB_BANNER_AD_UNIT_ID=ca-app-pub-3940256099942544/6300978111
ADMOB_NATIVE_AD_UNIT_ID=ca-app-pub-3940256099942544/2247696110
```

Google publishes these Mobile Ads demo IDs for testing. Replace them before production release.

## Local Base App Files

Firebase config files, one per environment:

```txt
app/src/dev/google-services.json
app/src/staging/google-services.json
app/src/prod/google-services.json
```

Create each from the matching `.template` file. For the template repository, these files may contain copied values from the same Firebase project. Keep the files separate so forks can split projects later.

Base properties, one per environment:

```txt
config/base/dev.properties
config/base/staging.properties
config/base/prod.properties
```

Required keys:

```properties
DUMMYJSON_BASE_URL=https://dummyjson.com
MEASURE_API_KEY=
ADMOB_APP_ID=
ADMOB_BANNER_AD_UNIT_ID=
ADMOB_NATIVE_AD_UNIT_ID=
BRANCH_KEY=
BRANCH_DOMAIN=
```

## Local Twitter Demo Files

The Twitter-lite demo should use separate service projects from the base app.

Firebase config files:

```txt
demo/twitter/app/src/dev/google-services.json
demo/twitter/app/src/staging/google-services.json
demo/twitter/app/src/prod/google-services.json
```

Demo properties:

```txt
config/demo-twitter/dev.properties
config/demo-twitter/staging.properties
config/demo-twitter/prod.properties
```

Required keys:

```properties
DEMO_TWITTER_SUPABASE_URL=
DEMO_TWITTER_SUPABASE_ANON_KEY=
DEMO_TWITTER_GOOGLE_WEB_CLIENT_ID=
DEMO_TWITTER_MEASURE_API_KEY=
DEMO_TWITTER_ADMOB_APP_ID=
DEMO_TWITTER_ADMOB_BANNER_AD_UNIT_ID=
DEMO_TWITTER_ADMOB_NATIVE_AD_UNIT_ID=
DEMO_TWITTER_BRANCH_KEY=
DEMO_TWITTER_BRANCH_DOMAIN=
```

## CI Secrets

Use GitHub Environments for secrets. V1 CI needs only base config/secrets (real values or generated placeholders); demo app-smoke and release/publishing secrets are post-implementation additions so they do not block base checks.

Firebase files should be stored as Base64 string secrets, then decoded by the workflow into the expected source-set path. Example:

```bash
base64 -w 0 app/src/dev/google-services.json > base-dev-google-services.b64
gh secret set --env base-dev BASE_DEV_GOOGLE_SERVICES_JSON_BASE64 < base-dev-google-services.b64
```

Workflow decode pattern:

```bash
mkdir -p app/src/dev
echo "$BASE_DEV_GOOGLE_SERVICES_JSON_BASE64" | base64 --decode > app/src/dev/google-services.json
```

GitHub supports reading secret values from files with `gh secret set SECRET_NAME < file` and decoding Base64 secrets inside workflows. Base64 is transport encoding, not encryption; GitHub secret storage is the protection boundary.

Base CI:

```txt
BASE_DEV_GOOGLE_SERVICES_JSON_BASE64
BASE_STAGING_GOOGLE_SERVICES_JSON_BASE64
BASE_PROD_GOOGLE_SERVICES_JSON_BASE64
BASE_DEV_MEASURE_API_KEY
BASE_STAGING_MEASURE_API_KEY
BASE_PROD_MEASURE_API_KEY
BASE_DEV_ADMOB_APP_ID
BASE_DEV_ADMOB_BANNER_AD_UNIT_ID
BASE_DEV_ADMOB_NATIVE_AD_UNIT_ID
BASE_STAGING_ADMOB_APP_ID
BASE_STAGING_ADMOB_BANNER_AD_UNIT_ID
BASE_STAGING_ADMOB_NATIVE_AD_UNIT_ID
BASE_PROD_ADMOB_APP_ID
BASE_PROD_ADMOB_BANNER_AD_UNIT_ID
BASE_PROD_ADMOB_NATIVE_AD_UNIT_ID
BASE_DEV_BRANCH_KEY
BASE_DEV_BRANCH_DOMAIN
BASE_STAGING_BRANCH_KEY
BASE_STAGING_BRANCH_DOMAIN
BASE_PROD_BRANCH_KEY
BASE_PROD_BRANCH_DOMAIN
CODECOV_TOKEN
GRADLE_ENCRYPTION_KEY
```

Demo CI:

```txt
DEMO_TWITTER_DEV_GOOGLE_SERVICES_JSON_BASE64
DEMO_TWITTER_STAGING_GOOGLE_SERVICES_JSON_BASE64
DEMO_TWITTER_PROD_GOOGLE_SERVICES_JSON_BASE64
DEMO_TWITTER_DEV_SUPABASE_URL
DEMO_TWITTER_STAGING_SUPABASE_URL
DEMO_TWITTER_PROD_SUPABASE_URL
DEMO_TWITTER_DEV_SUPABASE_ANON_KEY
DEMO_TWITTER_STAGING_SUPABASE_ANON_KEY
DEMO_TWITTER_PROD_SUPABASE_ANON_KEY
DEMO_TWITTER_DEV_GOOGLE_WEB_CLIENT_ID
DEMO_TWITTER_STAGING_GOOGLE_WEB_CLIENT_ID
DEMO_TWITTER_PROD_GOOGLE_WEB_CLIENT_ID
DEMO_TWITTER_DEV_MEASURE_API_KEY
DEMO_TWITTER_STAGING_MEASURE_API_KEY
DEMO_TWITTER_PROD_MEASURE_API_KEY
DEMO_TWITTER_DEV_ADMOB_APP_ID
DEMO_TWITTER_DEV_ADMOB_BANNER_AD_UNIT_ID
DEMO_TWITTER_DEV_ADMOB_NATIVE_AD_UNIT_ID
DEMO_TWITTER_STAGING_ADMOB_APP_ID
DEMO_TWITTER_STAGING_ADMOB_BANNER_AD_UNIT_ID
DEMO_TWITTER_STAGING_ADMOB_NATIVE_AD_UNIT_ID
DEMO_TWITTER_PROD_ADMOB_APP_ID
DEMO_TWITTER_PROD_ADMOB_BANNER_AD_UNIT_ID
DEMO_TWITTER_PROD_ADMOB_NATIVE_AD_UNIT_ID
DEMO_TWITTER_DEV_BRANCH_KEY
DEMO_TWITTER_DEV_BRANCH_DOMAIN
DEMO_TWITTER_STAGING_BRANCH_KEY
DEMO_TWITTER_STAGING_BRANCH_DOMAIN
DEMO_TWITTER_PROD_BRANCH_KEY
DEMO_TWITTER_PROD_BRANCH_DOMAIN
DEMO_TWITTER_DEV_SUPABASE_SERVICE_ROLE_KEY
DEMO_TWITTER_STAGING_SUPABASE_SERVICE_ROLE_KEY
DEMO_TWITTER_PROD_SUPABASE_SERVICE_ROLE_KEY
```

Release CI:

```txt
SIGNING_KEY_BASE64
SIGNING_STORE_PASSWORD
SIGNING_KEY_ALIAS
SIGNING_KEY_PASSWORD
PLAY_SERVICE_ACCOUNT_JSON
FIREBASE_APP_DISTRIBUTION_SA_JSON
```

The per-flavor `DEMO_TWITTER_{DEV,STAGING,PROD}_SUPABASE_SERVICE_ROLE_KEY` secrets are CI-only for demo seeding/policy tests against each environment's Supabase project. They must never be bundled into any app artifact.
## Provider-Smoke Policy

Default PR checks use generated test credentials plus fake/no-op provider bindings. Provider-smoke uses real GitHub Environment secrets and should start as a manual `workflow_dispatch` job with environment approval.

Run provider-smoke when:

- Firebase, Google Services, Crashlytics, Performance, or FCM dependencies change.
- Measure SDK or Gradle plugin changes.
- Google Mobile Ads / UMP changes.
- Branch SDK changes.
- Supabase / Google Identity dependencies change for the demo.
- CI secret handling or config-file generation changes.

For Renovate PRs, compile/unit/screenshot checks run automatically with fake credentials. Real provider-smoke should be manually triggered after reviewing the dependency diff. Do not run real secrets automatically on arbitrary PR code; dependency updates are exactly where supply-chain risk is highest.

## Never Commit

```txt
**/google-services.json
config/**/*.properties
*.keystore
*.jks
Play service-account JSON (post-implementation release pipeline)
Firebase App Distribution service-account JSON (post-implementation release pipeline)
Supabase service-role key
```

## Rotation Notes

If a value leaks, revoke or rotate it at the provider first, then update local files and GitHub Environment secrets. For future release signing keys, treat rotation as a release-management event because installed app upgrade continuity can be affected.




