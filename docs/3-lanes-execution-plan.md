# Parallel Execution Plan — 3-Agent Build of the Modern Android Template

> **Companion to** [`docs/PRD.md`](./PRD.md). The PRD is the single source of truth for *what* to
> build (stack, architecture, requirements). This document reorganizes the PRD's **§18 Build Roadmap**
> (a serial M1→M11 milestone chain) into **3 minimally-conflicting execution lanes** for 3 AI agents
> building in parallel. It does **not** restate requirements — it assigns ownership, sequences the
> work into phases, and names the sync gates and intersections. Cross-references like "§6.2", "FR-AUTH-3",
> "SC-ISOLATION-1" point into the PRD.
>
> **Status:** proposal. **Scope:** everything in PRD §1–§19 except §22 post-implementation actionables
> (passkeys P1, release/signing/publishing CI, provider-smoke) — explicitly out of scope here.

---

## 0. The core problem and the chosen model

PRD §18 is a **serial dependency chain**: M1 (foundation) → M2 (core layers) → M3 (design system) →
M4/M5 (base features) → M6/M7/M8 (demo) → M9/M10/M11 (cross-cutting). Nearly everything depends on
M1+M2+M3, so 3 agents cannot fan out on day one — the contracts and conventions they build against
don't exist yet.

**Chosen model: foundation-first, then fan out by layer.**

1. **Phase 0 — Foundation (Lane A solo).** One agent freezes the foundation: build infrastructure, the
   full `:core:*` contract surface, the design system, shared test rules, and a minimal **walking
   skeleton** (compilable `:app` + one base feature skeleton) that proves the spine builds end-to-end.
   Lanes B and C wait for the freeze gate. *(The one dependency-free exception — Lane C's `supabase/`
   backend — may optionally be pulled into Phase 0; see §3 Phase 0 note.)*
2. **Phase 1 — Fan-out (A ∥ B ∥ C).** Once contracts are frozen, the three lanes run in parallel, each
   owning a disjoint set of modules (§1).
3. **Phase 2 — Convergence (joint, A leads shared).** Cross-cutting tests, CI, isolation/removal
   contracts, docs, and the security/privacy hardening task.

**Why by-layer (not vertical slices):** module ownership is the cleanest conflict boundary. Two agents
rarely edit the same file because each owns whole modules. The cost is that Lane A is upstream of B and
C — managed by the freeze gate and the front-loading rule (§3).

**Shared-build ownership:** Lane A is the **sole gatekeeper** of `settings.gradle.kts`,
`gradle/libs.versions.toml`, `build-logic/`, the config mechanism, and `.github/workflows/`. B and C
request additions; catalog/settings edits are **append-only** so git auto-merges them.

---

## 1. Lane ownership (every PRD §6.2 module assigned exactly once)

### Lane A — Base Core & Platform  *(foundation lead + shared-build gatekeeper)*
| Group | Modules |
|---|---|
| Build infra | `build-logic`, `gradle/libs.versions.toml`, `settings.gradle.kts`, config mechanism (`config/<target>/<env>.properties` loader, `.template` files, flavor dimension, `scripts/create-test-credentials.sh` generator) |
| Core generic | `:core:model`, `:core:common`, `:core:network`, `:core:database`, `:core:datastore`, `:core:security`, `:core:data`, `:core:domain` |
| Design | `:core:designsystem`, `:core:ui` |
| Platform | `:core:observability`, `:core:push`, `:core:playservices`, `:core:ads`, `:core:deeplink`, `:core:dynamicdelivery` |
| Vendor impls | `:ads` (AdMob+UMP), `:deeplink-branch` (Branch) |
| Test infra | `:core:testing` (rules/helpers only) |
| Walking skeleton (Phase 0) | minimal compilable `:app` shell (Application + Koin startup + flavor/config wiring + fake provider bindings + empty edge-to-edge activity) **+** one base feature skeleton proving the `:api`/`:impl` spine — both handed to Lane B in Phase 1 |
| Quality gates | `compose-lints` (slackhq) hygiene pack in the lint convention (§8.14 GATE-1) |
| Shared (Phase 2) | architecture/isolation tests (incl. §8.14 GATE-2 design-system-wrapper rule), shared CI workflows, Dokka aggregation, root README + setup checklist, security/privacy hardening task (**SR-4 backup rules + SR-10 telemetry redaction**) |

### Lane B — Base App & Features
| Group | Modules |
|---|---|
| App | `:app` — the real product app, **extending Lane A's minimal Phase-0 shell**: `NavigationSuiteScaffold` adaptive root, Nav3 route graph, feature wiring, edge-to-edge, predictive back, list-detail showcase, base starter flow |
| Base features | `:feature:splash`, `:feature:auth`, `:feature:account`, `:feature:sample`, `:feature:settings`, `:feature:devtools` (each `:api` + `:impl`) |
| Optional / perf | `:app-catalog` skeleton, `:macrobenchmark` (base target) + baseline profile, base Maestro smoke |

### Lane C — Demo (Twitter-lite, deletable)
| Group | Modules |
|---|---|
| Backend | `supabase/` (migrations, RLS, triggers, RPCs, `send-push` Edge Function, seeds), `config/demo-twitter/*` |
| Social data | `:demo:twitter:supabase`, `:demo:twitter:model`, `:demo:twitter:network`, `:demo:twitter:database`, `:demo:twitter:data` |
| Demo features | `:demo:twitter:feature:auth`, `:onboarding`, `:feed`, `:post`, `:profile`, `:search`, `:notifications` |
| DFM | `:demo:twitter:feature:imagecrop:api` + `:impl` (consumes A's `:core:dynamicdelivery`) |
| App / perf | `:demo:twitter:app` (wiring, `SplitCompat`), demo macrobenchmark, demo Maestro E2E |

---

## 2. Phase structure (with old M1–M11 mapping)

### Phase 0 — Foundation / walking skeleton  *(Lane A solo)*  — merges M1 + M2 + M3 (foundation, core/contract spine, design system, config + testing substrate)
**A builds, B and C wait.** Deliverables:

- **Build infra (from M1):** AGP 9.2.0 / Gradle 9.4.1, version catalog, `build-logic` convention
  plugins (`template.android.application/library/compose/feature/koin/room/test/detekt/kover`),
  `settings.gradle.kts` module wiring.
- **Config substrate:** `environment` flavor dimension (dev/staging/prod), `config/*.properties`
  loader, committed `.template` files (incl. `google-services.json.template` placement), gitignore, CI
  base64-decode pattern, **base** BuildConfig fields (`DUMMYJSON_BASE_URL`, `DEVTOOLS_ENABLED`), and a
  **`scripts/create-test-credentials.sh`** generator that produces local placeholder config from the
  templates in one command (bash). Per-service fields appended later by their lane. *Exit substrate
  check:* a fresh clone runs the script → `:app:assembleDevDebug` builds the **minimal Phase-0 `:app`
  shell** (below) with fake/no-op provider bindings and no real secrets tracked.
- **Core generic layers (M2):** `:core:model/common/network(generic Ktor·OkHttp)/database/datastore/
  security/data/domain` — `Result`/`UiState`, `SessionProvider`/`AuthRepository` interface,
  `TokenStore` contract, Room + DataStore conventions, generic repository patterns.
- **Design system (M3) + `:core:ui`:** `:core:designsystem` (Ember tokens from `docs/design/Ember*.dc.html` —
  full `ColorScheme` role sets + `LocalEmberExtendedColors`, Outfit font, type/shape/spacing, wrapped
  `App*` atoms with a11y baseline + 48dp targets, multipreview, Showkase debug wiring) **then**
  `:core:ui` (generic list/detail/empty/error/loading scaffolds + reusable `ListDetailPaneScaffold`
  shell + `NavigationSuiteScaffold` helper; features supply content). Also wires the `compose-lints`
  hygiene pack into the lint convention (§8.14 GATE-1); the in-app Showkase entrypoint is exposed
  later via `:feature:devtools` (Lane B, Phase 1).
- **Platform contract surface:** `:core:observability/push/playservices/ads/deeplink/dynamicdelivery`
  as **interfaces + NoOp/Fake doubles**. `:core:ads` and `:core:deeplink` are contract-only modules →
  fully done here. The SDK-backed ones (observability/push/playservices/dynamicdelivery) ship their
  **interfaces + fakes now; real bindings deferred to A's Phase 1.** This is what makes the freeze
  meaningful — B's settings/devtools and C's demo bindings compile against frozen interfaces.
- **Test substrate (`:core:testing`, two slices):** (slice 1, with core) coroutine test rule, Koin
  test rule + `checkModules`, Ktor `MockEngine` helpers; (slice 2, with design) Roborazzi +
  `roborazzi-accessibility-check` config + ComposablePreviewScanner wiring. **No feature fakes here** —
  those stay local to each feature's test sources.
- **Walking skeleton:** a **minimal compilable `:app`** (Application + Koin startup +
  flavor/config wiring + fake/no-op provider bindings + one empty edge-to-edge activity) **and one base
  feature skeleton** (`:api` exposes route keys/contracts only; `:impl` owns screen/ViewModel/Koin
  bindings; no `impl`→`impl` edge; consumes `:core:ui` scaffolds). Proves the spine end-to-end before
  fan-out; both are handed to Lane B, which extends them into the real app + real features in Phase 1.

> **Phase 0 ships only a *minimal* `:app` shell — Lane B owns the real app.** Lane A's Phase-0 `:app`
> exists only to make the substrate buildable (it's what `assembleDevDebug` compiles). The real adaptive
> root, Nav3 route graph, feature wiring, and base starter flow are Lane B's first Phase-1 work (PRD §18
> M1 places the `:app` shell in foundation; the product app is M4-era).

**⛔ FREEZE GATE (G-FREEZE)** — the gate that stops 3 agents inventing incompatible patterns, so it
verifies everything B and C build against:
1. all `:core:*` contracts compile;
2. `scripts/create-test-credentials.sh` generates placeholder config → **minimal `:app` builds** with
   fake bindings, no real secrets (config substrate proven);
3. `template.android.feature` convention plugin creates/enforces the `:api`/`:impl` structure;
4. the **base feature skeleton** proves the pattern in use (keys-only `:api`, impl-owned screen/VM/Koin,
   consumes `:core:ui`);
5. dependency-boundary checks catch forbidden `impl`→`impl` edges; Koin `checkModules()` green vs fakes;
6. generic repository round-trips over Ktor `MockEngine`; design-system Roborazzi + a11y checks green.

**On pass → Lanes B and C start.**

### Phase 1 — Fan-out  *(A ∥ B ∥ C)*

**Lane A** — platform implementations (M5 core parts + feature flags):
- `:core:observability` Measure.sh + Firebase **composite** behind `CrashReporter`/`PerformanceTracer`/
  `AnalyticsLogger`; `:core:push` real FCM `FirebaseMessagingService` + `TokenSyncWorker` + injected
  `TokenSink`; `:core:playservices` Play Core `AppUpdateCoordinator` + `ReviewPrompter`; `:ads` AdMob +
  UMP behind `:core:ads`; `:deeplink-branch` Branch behind `:core:deeplink`; feature-flags plumbing
  (`:core:datastore` Proto + `:core:data` `FeatureFlagProvider` over Remote Config, incl. local
  override layer); `:core:dynamicdelivery` real `SplitInstallManager` wrapper + Nav3 `openDynamic()`
  gate + `InstallingKey`.

**Lane B** — base app + features (M1 real app + M4 + M5 settings/devtools):
1. **Extend the minimal Phase-0 `:app` shell** into the real app (adaptive `NavigationSuiteScaffold`
   root, Nav3 route graph, predictive back, base starter flow).
2. **Front-load demo-reused features** (unblocks C — see G-REUSE): `:feature:splash` (injected
   `SplashRouter`), `:feature:account` (deletion flow), `:feature:settings` (theme/dynamic-color/lang +
   update/review/delete entries), `:feature:devtools` (dev/staging diagnostics).
3. Then `:feature:auth` (Credential Manager username/password → DummyJSON token auth, refresh, logout),
   `:feature:sample` (Paging 3 + Room SSOT + search), the `ListDetailPaneScaffold` adaptive showcase,
   `:app-catalog` skeleton.

**Lane C** — demo (M6 + M7 + M8):
1. `supabase/` backend (schema, RLS, triggers, `complete_profile`/`mark_*`/`get_home_feed`/
   `username_available` RPCs, `send-push` Edge Function, seeds) — *unless already pulled into Phase 0.*
2. Social data modules: `:demo:twitter:supabase/model/network/database/data` (social repos + Supabase
   `AuthRepository` binding + demo `TokenSink` upsert).
3. Demo features: `feature:auth` (Google SSO + asymmetric nonce → Supabase), `onboarding`
   (`complete_profile` gate), `feed`/`post`/`profile`/`search`/`notifications` (Paging 3 keyset +
   realtime + multi-image upload worker); `:demo:twitter:app` wiring + `SplitCompat`.
4. `imagecrop` DFM — **after G-DFM** (consumes A's `:core:dynamicdelivery`).

### Phase 2 — Convergence  *(joint; A leads shared)* — merges M9 + M10 + M11 + the hardening task
- **Continuous per-lane (throughout Phase 1, finalized here):** each lane's unit/UI tests, ViewModel
  Kover floors (TR-1), per-module READMEs.
- **A-owned shared:** architecture/isolation tests (Konsist social-noun denylist + Gradle dependency-
  graph assertion → SC-ISOLATION-2; design-system-wrapper usage rule → §8.14 GATE-2); whole-demo-removal build (SC-ISOLATION-1) + single-feature-removal
  Koin `checkModules` matrix (SC6); shared CI (`pr-check`/`main-ci`, `base`/`demo-compile` profiles,
  Semgrep SAST, Renovate self-hosted, dependency-submission/review, PR hygiene, APK size-diff, Codecov);
  Dokka multi-module site → GitHub Pages, root README + split setup checklist (§19);
  **security/privacy hardening task (SR-4 backup rules + SR-10 telemetry redaction)** — backup-rules XML
  (`data_extraction_rules`/`full_backup_content`, SR-4) for both app targets + telemetry redaction config
  (Measure header blocklist + `httpDisableEventForUrls`, OkHttp `redactHeader`, Crashlytics no-PII, SR-10/OR-6).
- **Per-target:** macrobenchmark + baseline profile (B for `:app`, C for `:demo:twitter:app`); Maestro
  smoke (B: login → sample → detail → logout; C: seeded `dev`/`staging` Supabase auth → feed → detail).
- **Joint integration pass:** build both targets green; verify removal contracts; all gates pass
  (SC2/SC4/SC5/SC7).

---

## 3. Sync gates

| Gate | When | Condition | Unblocks |
|---|---|---|---|
| **G-FREEZE** | end of Phase 0 | the 6-point gate in §2: contracts compile · config substrate generates + minimal `:app` builds · convention plugin enforces `:api`/`:impl` · base skeleton + `checkModules` + dependency-boundary green · MockEngine round-trip · designsystem a11y green | B and C start Phase 1 |
| **G-REUSE** | mid Phase 1 | B delivers `:feature:splash/settings/account/devtools` (`:api` + `:impl`) | C wires `:demo:twitter:app` + onboarding gate |
| **G-DFM** | late Phase 1 | A delivers `:core:dynamicdelivery` (real `SplitInstallManager` + Nav3 gate) | C builds `imagecrop` DFM |
| **G-CONVERGE** | start of Phase 2 | all three lanes feature-complete | joint tests / CI / docs / hardening |

> **SC-STARTER-1 is a joint A+B completion gate, not a lane handoff.** Per revised PRD §18, M4 no
> longer claims SC-STARTER-1 — the base starter runs end-to-end only once **A's** platform impls
> (observability/push/playservices/ads/deeplink/flags) **and B's** settings/devtools both land (late
> Phase 1). Neither lane satisfies it alone; track it as a late-Phase-1 milestone, verified in Phase 2.

> **Phase 0 note (optional acceleration, with a hard boundary):** Lane C may start early **only** on
> dependency-free backend artifacts — `supabase/migrations/`, `supabase/functions/send-push/`, seed
> files, RLS/RPC/trigger SQL — which are pure SQL/Deno and touch no shared file. **Defer** `config/demo-twitter/*`,
> demo `BuildConfig` fields, and any `:demo:twitter:*` Android/app wiring until Lane A's config substrate
> is merged (they depend on A's mechanism — starting them early causes rework/conflict). Default
> sequencing keeps Phase 0 as Lane A solo; pulling only the backend earlier is the safe, conflict-free win.

---

## 4. Intersection risk table  *(the accepted "slight intersections")*

| Intersection | Lanes | Resolution |
|---|---|---|
| `settings.gradle.kts` / `libs.versions.toml` / `build-logic` | A owns; B, C touch | A gatekeeps; B/C request via **append-only** edits (git auto-merges) |
| `:app` minimal shell + base feature skeleton (walking skeleton) | A → B | A builds both in Phase 0 to prove the spine; B extends/replaces them in Phase 1 — **sequential handoff at G-FREEZE**, not concurrent editing |
| `:core:dynamicdelivery` ↔ `imagecrop` DFM | A, C | api-in-base pattern; C consumes A's frozen contract via one reflection call — no compile edge |
| demo reuses base `splash/settings/account/devtools` | B, C | B **front-loads** them (G-REUSE); C depends on `:api` only, never `:impl` |
| backup-rules XML referenced by both app targets | A, B, C | A defines the exclusion **policy** in the Phase 2 hardening task; B and C reference it in each app's own `res/xml` |
| `config/*` fields for shared services | A, B, C | A owns the mechanism; each lane **appends** its own service's fields/templates |
| Koin startup + nav registration | B vs C | separate files per app target (`:app` vs `:demo:twitter:app`) — no conflict |

All intersections are file-local and PR-resolvable. No two lanes co-own a single module.

---

## 5. Coverage checklist

**Every §6.2 module → exactly one lane** (§1 above): `:core:*` (incl. `:core:testing`) + `build-logic`
+ vendor impls (`:ads`, `:deeplink-branch`) → A; `:app` + 6 base `:feature:*` + `:app-catalog` + base
`:macrobenchmark` → B; all `:demo:twitter:*` modules + the `supabase/` backend → C. No orphans; no
module **concurrently** co-owned. The two **walking-skeleton** artifacts (`:app` + one base `:feature:*`)
are the sole exception — Lane A scaffolds them in Phase 0, Lane B takes ownership in Phase 1 as a
**sequential handoff** (§4), never a parallel edit.

**Every §8/§9 FR → a lane task:**
| PRD requirement | Lane | Phase |
|---|---|---|
| 8.1 Splash, 8.2 Auth, 8.4 Settings, 8.10 Devtools, 8.15 Account deletion, 8.16 Sample | B | 1 |
| 8.3 Push, 8.5 Update, 8.6 Review, 8.7 Flags, 8.8 Ads, 8.9 Deep links | A | 0 (contract) + 1 (impl) |
| 8.11 Design system + catalog | A (designsystem/ui) + B (`:app-catalog`, Showkase entry) | 0 / 1 |
| 8.12 Adaptive UI, 8.13 Accessibility | A (`:core:ui` scaffolds + atom semantics) + B (app-screen tests) | 0 / 1–2 |
| §9 demo: FR-DAUTH-*, FR-ONB-*, FR-DEMO-1..14, 9.1–9.5 schema/RLS/feed, FR-PUSH-4/5 demo bindings | C | 1 |

**Every substrate task → placed** *(named explicitly — not `G*` shorthand, which would collide with PRD §2
product goals G1–G6):* **testing substrate** (`:core:testing`), **config substrate** (mechanism +
generator), and **`:core:ui`** are folded into PRD §18 directly (revised M2 names `:core:ui`/`:core:testing`;
revised M1 names flavors/config/templates) — this plan maps them to Phase 0. **SR-4 backup rules** +
**SR-10 telemetry redaction** remain a **Phase 2 hardening task** here (a planning choice; PRD §18
distributes the underlying SR-4/SR-10 ownership across M2/M5). The `:api`/`:impl`/Koin pattern is encoded
in the `template.android.feature` convention plugin **and proven in-use by one base feature skeleton built
in Phase 0** (`checkModules()` + dependency-boundary checks green at the freeze gate); Lane B extends that
skeleton into the first real base feature in Phase 1. The **demo** pattern (Supabase bindings, onboarding
gate, DFM) is Lane C's to prove in Phase 1 — no demo skeleton in Phase 0 (the base skeleton already
proves the generic spine; demo-specific patterns surface when Lane C builds).

**Old M1–M11 → all mapped:** M1 split (core infra + **minimal** `:app` shell → Phase 0 A · **real** `:app` → Phase 1 B); M2, M3 →
Phase 0 A; M4 → Phase 1 B (no longer claims SC-STARTER-1 per revised §18); M5 split (core/vendor →
Phase 1 A · settings/devtools → Phase 1 B; **SC-STARTER-1 completes here, joint A+B**); M6/M7/M8
→ Phase 1 C; M9/M10/M11 → Phase 2. **§22 actionables excluded** (passkeys P1, release/publishing CI,
provider-smoke) per scope.

---

## 6. Quick reference — who does what, when

```
PHASE 0  (A solo)         A: build-logic · catalog · config substrate + generator · all :core:* contracts
                             · designsystem · :core:ui · :core:testing · minimal :app + base skeleton
                          B: ⏳ wait      C: ⏳ wait  (backend-only may start early — §3 boundary)
            │
        ⛔ G-FREEZE  (contracts compile · config gen + minimal :app builds · convention plugin enforces
                      :api/:impl · base skeleton + checkModules + boundary green · MockEngine · a11y)
            │
PHASE 1  (A ∥ B ∥ C)      A: real SDK bindings (obs/push/playservices/ads/deeplink/flags/DFM plumbing)
                          B: extend :app shell → [splash·settings·account·devtools] → auth·sample·showcase
                                                   │
                                              ⛔ G-REUSE → C
                          C: supabase/ → social data → demo features → :demo app → [⛔ G-DFM] imagecrop
            │
        ⛔ G-CONVERGE
            │
PHASE 2  (joint, A leads) isolation/removal tests · shared CI · Dokka/docs · hardening (backup+redaction)
                          · per-target macrobench + Maestro · integration build · all gates green
```
