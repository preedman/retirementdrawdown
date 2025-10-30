# Retirement Drawdown Calculator (Vaadin + Spring Boot)

A simple Vaadin 24 application built on Spring Boot 3 that helps explore retirement drawdown scenarios. You can input starting balance, expected annual return, inflation, and yearly withdrawals to see a year‑by‑year projection and the number of years the portfolio may last. Includes basic login and the ability to download results (future/disabled by default).

User and Technical Guide (project wiki):
- https://github.com/preedman/retirementdrawdown/wiki


## Features
- Vaadin 24 UI (server-side) with `AppLayout`
- Parameters dialog for:
  - Starting balance
  - Inflation rate
  - Percentage return
  - Yearly withdrawals
  - Optional 4% rule toggle (stored on the model for future use)
- Grid view listing annual drawdown calculations
- Notification with calculated number of withdrawal years
- Basic authentication using Spring Security
  - Login view at `/login`
  - In‑memory user configured in `SecurityConfig`
- Download button scaffolded (currently disabled) for exporting results


## Tech Stack
- Java 17
- Spring Boot 3.2.x
- Vaadin 24.4.x
- Maven (with `vaadin-maven-plugin`)
- Vite/Node handled by Vaadin during `prepare-frontend`


## Project layout
```
src/main/java/com/reedmanit/retirementdrawdown/
  ├─ DrawnDownCalculator.java        # Application entry point (@SpringBootApplication)
  ├─ model/
  │   └─ DrawDownParameters.java     # Input parameters model
  ├─ security/
  │   └─ LoginView.java              # Login UI (/login)
  ├─ service/
  │   ├─ SecurityConfig.java         # Spring Security setup (in‑memory user, login view)
  │   ├─ SecurityService.java        # Auth context helper (get user, logout)
  │   ├─ DrawDownService.java        # Drawdown calculations (see tests)
  │   └─ DownloadDataService.java    # CSV export (used by Download button)
  └─ views/
      ├─ MainAppView.java            # Main shell, nav, grid, parameter handling
      ├─ ParametersDialogView.java   # Shows current parameters in a dialog
      └─ (other views like ParameterFormView, DrawdownGridView)

src/main/resources/
  └─ application.properties          # Example: date=2025

src/test/java/com/reedmanit/retirementdrawdown/
  └─ service/DrawDownServiceTest.java
```


## Prerequisites
- Java 17 (ensure `java -version` shows 17)
- Maven 3.9+ (recommended)
- Internet access to download dependencies

Note: Vaadin’s Maven plugin will fetch the needed Node/Vite toolchain automatically for `prepare-frontend`. You don’t need Node installed globally unless developing the frontend manually.


## Quick start
1. Clone the repository
2. From the project root, run the app:
   ```bash
   mvn -DskipTests spring-boot:run
   ```
3. Open the app: http://localhost:8080/
4. You will be redirected to the login page at `/login`.

### Default login
Default in‑memory user is defined in `SecurityConfig`:
- Username: `guest`
- Password: set via a BCrypt hash in code. If `guest/guest` does not work in your environment, open `SecurityConfig.java` to verify or change the password (instructions below).

To change the default user/password, update `users()` in `SecurityConfig`:
```java
@Bean
public UserDetailsService users() {
    UserDetails guest = User.builder()
        .username("guest")
        .password("{bcrypt}<your_bcrypt_hash>")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(guest);
}
```
Tip: Generate a BCrypt hash with Spring Security’s `BCryptPasswordEncoder` or any trusted generator.


## Usage
- Click the plus icon to enter parameters (starting balance, inflation, return, withdrawals).
- Click the info icon to review current parameters.
- The grid updates with the computed annual drawdown projection.
- A notification shows the number of withdrawal years.
- The download button exists but is disabled by default (future feature).
- Use the exit icon to log out.


## Configuration
Application properties live in `src/main/resources/application.properties`. Example property included:
```
date=2025
```
Add your own settings as needed. Spring profiles are supported conventionally via `application-<profile>.properties`.


## Build a production JAR
```bash
mvn -Pproduction -DskipTests clean package
```
Then run:
```bash
java -jar target/Retirement-1.0-SNAPSHOT.jar
```
Notes:
- The profile `production` activates Vaadin production bundling via the Maven plugin.
- First build may take longer due to Vaadin frontend preparation.


## Running tests
```bash
mvn test
```
You can run a single test class:
```bash
mvn -Dtest=com.reedmanit.retirementdrawdown.service.DrawDownServiceTest test
```


## Troubleshooting
- Vaadin frontend prep takes a while on first run. If you see frontend errors, try:
  ```bash
  mvn clean vaadin:prepare-frontend
  mvn -DskipTests spring-boot:run
  ```
- If login fails, verify the username/password and the encoded hash in `SecurityConfig`.
- If port 8080 is busy, change `server.port` in `application.properties`.
- Windows PowerShell may cache the node executable locked by antivirus; a clean rebuild usually fixes it.


## License
No explicit license is provided in this repository. By default, all rights are reserved by the author. If you intend to use this project, please open an issue to clarify licensing.
