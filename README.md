<h1 align="center">Currency Wallet</h1>

<p align="center">
  A clean, minimal currency converter for Android, built with <b>Kotlin</b> and <b>Jetpack Compose</b>,<br/>
  following <b>Clean Architecture</b> and <b>MVVM</b>.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?logo=jetpackcompose&logoColor=white" alt="Jetpack Compose"/>
  <img src="https://img.shields.io/badge/Hilt-Dagger-2E7D32" alt="Hilt"/>
  <img src="https://img.shields.io/badge/Room-Database-5B3DF5" alt="Room"/>
  <img src="https://img.shields.io/badge/Retrofit-REST-00C2A8" alt="Retrofit"/>
</p>

<hr/>

<h2>Screenshots</h2>

<p align="center">
  <img src="screenshots/converter_light.png" width="250" alt="Converter (Light)"/>
  <img src="screenshots/converter_dark.png" width="250" alt="Converter (Dark)"/>
  <img src="screenshots/favorites.png" width="250" alt="Favorites"/>
</p>

<p align="center">
  <img src="screenshots/currency_picker.png" width="250" alt="Currency picker"/>
  <img src="screenshots/favorites_empty.png" width="250" alt="Empty favorites"/>
  <img src="screenshots/error_state.png" width="250" alt="Error state"/>
</p>

<h2>Features</h2>

<ul>
  <li>Convert between 30 currencies using rates from the <a href="https://frankfurter.dev">Frankfurter API</a></li>
  <li>Currency picker bottom sheet, loaded from the API</li>
  <li>Swap currencies and quick-amount chips</li>
  <li>Save favorite pairs with a single tap (Room database)</li>
  <li>Favorites screen with swipe-to-delete and Undo</li>
  <li>Loading and error states with typed failures</li>
  <li>Light and dark themes (Material 3)</li>
  <li>Animated "Add to favorites" button (press scale and star bounce)</li>
  <li>Test-driven development: use cases covered by unit tests (JUnit)</li>
</ul>

<h2>Tech Stack</h2>

<table>
  <tr><th align="left">Area</th><th align="left">Technology</th></tr>
  <tr><td>Language</td><td>Kotlin</td></tr>
  <tr><td>UI</td><td>Jetpack Compose, Material 3</td></tr>
  <tr><td>Architecture</td><td>Clean Architecture, MVVM, feature-based packages</td></tr>
  <tr><td>Dependency Injection</td><td>Hilt (built on Dagger)</td></tr>
  <tr><td>Networking</td><td>Retrofit, OkHttp, Gson</td></tr>
  <tr><td>Local storage</td><td>Room</td></tr>
  <tr><td>Async</td><td>Coroutines, Flow, StateFlow</td></tr>
  <tr><td>Navigation</td><td>Navigation Compose</td></tr>
  <tr><td>Error handling</td><td>Sealed <code>Failure</code> and <code>Either</code></td></tr>
  <tr><td>Testing</td><td>JUnit, TDD approach</td></tr>
</table>

<h2>Architecture</h2>

<p>
  The project is organized by feature. Each feature has its own <code>domain</code>,
  <code>data</code>, and <code>ui</code> layers, and shared code lives in <code>core</code>.
</p>

<pre>
com.example.multi_currencywallet
├── core
│   ├── database      Room: entity, DAO, database, DI module
│   ├── network       Retrofit/OkHttp setup, API constants
│   ├── error         Failure (sealed class)
│   ├── util          Either, CurrencyMapper
│   ├── model         Currency
│   ├── components    Shared composables (CurrencyChip, CurrencyPickerSheet)
│   ├── navigation    NavHost, bottom bar
│   └── theme         Colors, theme
└── feature
    ├── converter
    │   ├── domain    entity, repository (interface), use cases
    │   ├── data      remote API, DTOs, data source, repository impl
    │   ├── di        Hilt bindings
    │   └── ui        screen, ViewModel, UiState, components
    └── favorites
        ├── domain    entity, repository (interface), use cases
        ├── data      local data source (Room), repository impl
        ├── di        Hilt bindings
        └── ui        screen, ViewModel, components
</pre>

<h3>Data flow</h3>

<pre>
UI (Compose) → ViewModel → UseCase → Repository (interface)
                                          ↑ implements
                               RepositoryImpl → DataSource → Retrofit / Room DAO
</pre>

<ul>
  <li>The <b>domain layer</b> has no Android or framework dependencies.</li>
  <li>The <b>repository implementation</b> converts exceptions into typed <code>Failure</code> values
      (<code>NoInternet</code>, <code>Timeout</code>, <code>Server</code>, <code>EmptyData</code>, <code>Unknown</code>),
      so the UI never handles raw exceptions.</li>
  <li><b>ViewModels</b> expose a single <code>StateFlow&lt;UiState&gt;</code>; the UI renders state and sends events back.</li>
</ul>

<h2>Getting Started</h2>

<ol>
  <li>Clone the repository:
<pre>git clone https://github.com/tokaahmed345/multi_currency_wallet</pre>
  </li>
  <li>Open it in <b>Android Studio</b> (latest stable).</li>
  <li>Sync Gradle and run on an emulator or a device.</li>
</ol>

<p>No API key is required.</p>

<h2>Notes on Data</h2>

<ul>
  <li>Rates come from the Frankfurter API, which uses European Central Bank data and is updated
      <b>once per working day</b> (not real-time).</li>
  <li>The API supports about 30 currencies. Some currencies (for example EGP, SAR, AED) are not available.</li>
</ul>

<h2>Author</h2>

<p>
  <b>Toka Ahmed Elsharkawy</b><br/>
  <a href="https://www.linkedin.com/in/toka-elshrkawy-3822aa290/">LinkedIn</a> ·
</p>
