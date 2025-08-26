## IdentifyServices — Selenium + Cucumber + TestNG Automation Framework

A Java automation framework that validates core user journeys on Justdial and extracts service details. It supports local and Selenium Grid execution, BDD-style test definitions with Cucumber, TestNG orchestration with retries, data-driven testing via Excel, JSON exports, and rich reporting with Allure, ExtentReports, and Cucumber HTML.

### Tech stack
- Java 21
- Maven
- Selenium 4
- Cucumber JVM 7 (Gherkin BDD)
- TestNG
- Allure Reports + ExtentReports + Cucumber HTML report
- Apache POI (Excel)
- Log4j2
- JSON-simple

### Project structure
```
IdentifyServices/
├─ pom.xml
├─ testng.xml
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  ├─ base/                 # BasePage and common selenium wait/JS helpers
│  │  │  ├─ model/                # Domain models (e.g., Service)
│  │  │  └─ pages/                # Page Objects (HomePage, ServicePage, ResultsPage, FreeListingPage)
│  │  │     
│  │  │  └─ utilities/            # ConfigReader, ExcelUtility, JsonUtility, ReportOpener, ScreenshotUtility
│  │  └─ resources/
│  │     ├─ config.properties     # App/config settings (env, waits, Allure path, etc.)
│  │     └─ log4j2.xml
│  └─ test/
│     ├─ java/
│     │  ├─ hooks/                # WebDriver lifecycle, screenshots, Allure attachments
│     │  ├─ stepDefinitions/      # Step definitions per feature
│     │  ├─ testRunner/           # Cucumber TestNG runner
│     │  └─ reRun/                # TestNG retry listener/analyzer
│     └─ resources/
│        ├─ features/             # Gherkin feature files
│        ├─ extent.properties
│        └─ allure.properties
├─ selenium_grid/                  # Convenience scripts to run Grid hub/node/standalone (Windows .bat)
├─ testdata/                       # Excel test data (referenced in config)
├─ output/                         # JSON export target
├─ Report/                         # Extent report output (also mirrored under test-output/*)
└─ target/                         # Build + Allure/Cucumber outputs
```

### Key capabilities
- Page Object Model with explicit waits and safe JS interactions
- Local and Remote (Selenium Grid) execution
- Cucumber BDD with Gherkin features and TestNG runner
- Retry of failed tests (once) via TestNG listeners
- Data-driven test flows from Excel
- Structured JSON result export
- Automatic report generation and opening:
  - Allure (requires Allure CLI)
  - Extent Spark HTML
  - Cucumber HTML
- Screenshots on failure attached to Allure and Cucumber reports

### Prerequisites
- JDK 21
- Maven 3.8+
- Google Chrome or Microsoft Edge installed
- Allure Commandline (to auto-open Allure report from the suite):
  - Install Allure CLI and note the `allure.bat` path on Windows
  - Set `allure.bat.path` in `src/main/resources/config.properties`
- Optional: Selenium Grid (if running `environment=remote`)

Selenium 4 includes Selenium Manager which can provision drivers automatically when using `new ChromeDriver()`/`new EdgeDriver()`.

### Configuration
Edit `src/main/resources/config.properties`:
- `environment` = `local` or `remote`
- `remote.url` = Grid hub URL (e.g., `http://localhost:4444`)
- `app.url` = AUT base URL (default: `https://justdial.com`)
- `implicit.wait` = seconds for implicit wait
- `explicit.wait` = seconds for WebDriverWait
- `test.data.file` = path to Excel test data (e.g., `testdata/test_data.xlsx`)
- `test.data.sheet.name` = sheet name (e.g., `ServiceData`)
- `total.number.of.services` = number of result cards to evaluate
- `expected.votes` = minimum votes threshold when filtering result cards
- `json.file.path` = JSON export path (e.g., `output/result.json`)
- `allure.bat.path` = absolute path to `allure.bat`

Edit TestNG parameters in `testng.xml` (applies to local and remote):
- `<parameter name="browser" value="chrome|edge"/>`
- `<parameter name="platform" value="windows|linux|mac"/>`

### Running the tests
#### 1) From Maven (default suite runs via `testng.xml`)
```
mvn clean test
```
This triggers the TestNG suite, which invokes the Cucumber runner in `testRunner.TestRunner`.

By default, the runner points `features` to:
```
src/test/resources/features/category_display.feature
```
To run all features or filter by tags, edit `src/test/java/testRunner/TestRunner.java`:
- Set `features = {"src/test/resources/features"}` to run all
- Optionally enable tags: `tags = "@sanity or @regression"`

#### 2) From your IDE
- Right-click `testng.xml` and run
- Or run the `TestRunner` class directly

#### 3) Remote execution on Selenium Grid
- Set `environment=remote` in `config.properties`
- Set `remote.url` to the Grid hub (e.g., `http://<hub-host>:4444`)
- Start Grid (Windows sample `.bat` scripts in `selenium_grid/`):
  - `hub.bat` (hub)
  - `node.bat` (node) — ensure it points to your hub URL
  - `standalone.bat` (single node)
- Then run `mvn clean test`

### Feature coverage (examples)
- `category_display.feature`: View All Categories shows expected count
- `search_functionality.feature`: Search with valid/invalid inputs and page navigation
- `filter_functionality.feature`: Apply rating filters and validate results
- `show_number_popup.feature`: Validate Show Number popup reveals contact
- `service_page_query.feature`: Submit query with valid/invalid inputs and OTP/error handling
- `excel_reading.feature`: Data-driven run from Excel; retrieves services and saves to JSON
- `retrieve_items.feature`: Retrieve items above a vote threshold and log count

### Data-driven testing (Excel)
- Excel location and sheet configured via `config.properties`
- Expected columns used by step definitions:
  - `Location`
  - `Service Name`
  - `Quick Filters` (e.g., `Top Rated`, `Quick Response`, `Jd Verified`, `Jd Trust`, or `none`)
  - `Sort By` (e.g., `Relevance`, `Rating`, `Popular`, or `none`)
  - `Ratings` (e.g., `Any`, `3.5+`, `4.0+`, `4.5+`, `5` or `none`)

### JSON export
- During `excel_reading.feature` with `@SaveJson`, services are collected and written to a JSON array per service name
- Output path: `output/result.json` (configurable)

### Reports
Reports are generated automatically and opened after the suite (Windows) by `utilities.ReportOpener`:
- Allure: `target/allure-results` → `target/allure-report` (requires `allure.bat.path`)
- Extent Spark: `test-output/JustDailReport <timestamp>/Report/CucumberExtentReport.html`
- Cucumber HTML: `target/cucumber-report.html`

Manual Allure commands (if you prefer CLI):
```
allure generate target/allure-results --clean -o target/allure-report
allure open target/allure-report
```

### Logging and screenshots
- Log4j2 for framework logs (`src/main/resources/log4j2.xml`)
- Screenshots captured on step failure and attached to Allure and Cucumber outputs

### Retries
- A failed test is retried once via `reRun.RetryListener` and `reRun.RetryAnalyzer`

### Tips & troubleshooting
- Allure not opening: verify `allure.bat.path` in `config.properties` and that Allure CLI is installed
- Grid connection errors: confirm `remote.url` is reachable (default hub `http://localhost:4444`)
- Popups blocking actions: popups are handled in step backgrounds; ensure the AUT UI hasn’t changed
- Element timing: adjust `implicit.wait`/`explicit.wait` in `config.properties`
- Feature selection: update `features` and `tags` in `TestRunner.java` to run desired scenarios
