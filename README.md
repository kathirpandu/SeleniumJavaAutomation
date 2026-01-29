# MMT Selenium Automation Framework (Allure)

## Run
```bash
mvn clean test
```

## Headless
```bash
mvn clean test -Dheadless=true
```

## Generate Allure Report
```bash
mvn allure:report
```

If Allure CLI is installed:
```bash
allure serve target/allure-results
```
