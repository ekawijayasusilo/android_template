# Modern Android Template

Opinionated Android starter template for a modern Kotlin + Compose app. The architecture and implementation requirements are tracked in [docs/PRD.md](docs/PRD.md).

## Getting Started

The base `:app` is opinionated & complete — it intentionally includes real Firebase, Measure.sh, AdMob/UMP, and Branch integrations as best-practice modules. Local credentials are required for runnable app variants. Do not commit real credentials.

### 1. Create local config from templates

Every real config file is gitignored. Copy each committed `*.template` to its non-template filename — same idea as `cp .env.example .env`.

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

The templates already contain syntactically valid fake values (Google's demo AdMob IDs, dummy Firebase keys), so the copied files compile as-is — no editing required for a local smoke build. Replace values with real ones only when you want to verify provider dashboards (step 2). These are build-time placeholders only; unit, screenshot, and E2E tests bind fake provider implementations and must not fail because a third-party service is unavailable.

### 2. Configure the base app

Create one Firebase/Measure/AdMob/Branch project for the base template if you only need a template smoke-test. Keep separate source files per environment even when they contain copied values, so forks know where to split dev/staging/prod credentials later. Replace generated values with real local project values when you want to verify provider dashboards.

Firebase config files:

```txt
app/src/dev/google-services.json
app/src/staging/google-services.json
app/src/prod/google-services.json
```

Base service properties:

```txt
config/base/dev.properties
config/base/staging.properties
config/base/prod.properties
```

Expected base properties:

```properties
DUMMYJSON_BASE_URL=https://dummyjson.com
MEASURE_API_KEY=
ADMOB_APP_ID=
ADMOB_BANNER_AD_UNIT_ID=
ADMOB_NATIVE_AD_UNIT_ID=
BRANCH_KEY=
BRANCH_DOMAIN=
```

### 3. Build the base app

```bash
./gradlew :app:assembleDevDebug
./gradlew :app:check
```

### 4. Use the template for your own app

When forking this repo into a real app, overwrite the template identity and product bindings manually. No rename/scaffold script is required.

Replace app identity:

```txt
Android namespace
applicationId
app display name
launcher icons/adaptive icon
root project name
package/folder names if you want the Kotlin package to match the final app ID
```

Replace product wiring:

```txt
DummyJSON base URL and sample/auth binding with your real backend
Firebase projects and google-services.json files
Measure.sh project/API keys
AdMob app/ad unit IDs and UMP setup
Branch key/domain and App Links assetlinks.json host/package/signing fingerprints
future release/publishing config when you add the post-implementation pipeline (signing, Play Console, Firebase App Distribution)
privacy policy / terms URLs and telemetry/ads consent copy
```

Then remove or replace `:feature:sample`, delete `:demo:twitter:*` if you do not need the showcase, and remove any base modules your product intentionally does not use.

### 5. Configure the Twitter-lite demo

Use separate service projects/placeholders for the demo because it has real Supabase auth/data, social flows, push behavior, and telemetry. Replace generated test values with real demo project values after those projects exist.

Demo Firebase config files:

```txt
demo/twitter/app/src/dev/google-services.json
demo/twitter/app/src/staging/google-services.json
demo/twitter/app/src/prod/google-services.json
```

Demo service properties:

```txt
config/demo-twitter/dev.properties
config/demo-twitter/staging.properties
config/demo-twitter/prod.properties
```

Expected demo properties:

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

## Secret Rules

Commit only `.template` placeholders. Keep real `google-services.json`, `config/**/*.properties`, keystores, Play service-account JSON, Firebase App Distribution credentials, and Supabase service-role keys out of git.

For CI, store Firebase `google-services.json` files as Base64 string secrets and decode them back to files in the workflow. Other provider values can be normal scalar secrets. See [docs/secrets.md](docs/secrets.md).

