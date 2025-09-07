# 🔧 Configurable root directory for reports
$reportsRoot = "reports"  # Change to absolute path if needed

# 📦 Project metadata
$version = "1.0-SNAPSHOT"
$projectName = "PortfolioDemo"

# 🧠 Extract suite name from testng.xml
[xml]$testng = Get-Content "testng.xml"
$suiteName = $testng.suite.test.name
if (-not $suiteName) { $suiteName = "UnknownSuite" }

# 📅 Get date and full timestamp (with seconds)
$date = Get-Date -Format "yyyy-MM-dd"
$timestamp = Get-Date -Format "yyyy-MM-dd_HHmmss"

# 🏷️ Build archive folder name (filename-style)
$archiveName = "${projectName}_${suiteName}_${version}_${date}_${timestamp}"

# 📁 Build full path: reportsRoot/suiteName/date/archiveName
$datePath = Join-Path -Path $reportsRoot -ChildPath "$suiteName\$date"
$archivePath = Join-Path -Path $datePath -ChildPath $archiveName

# 🧪 Generate Allure report
allure generate target/allure-results --clean -o target/allure-report

# 📂 Ensure date folder exists
New-Item -ItemType Directory -Path $datePath -Force | Out-Null

# 🧹 Clean archive folder if it already exists
if (Test-Path $archivePath) {
    Remove-Item $archivePath -Recurse -Force
}
New-Item -ItemType Directory -Path $archivePath -Force | Out-Null

# 📥 Copy report contents
Copy-Item -Path "target/allure-report" -Destination $archivePath -Recurse -Force

Write-Host "✅ Allure report archived to $archivePath"
