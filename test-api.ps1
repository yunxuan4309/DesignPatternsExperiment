# API测试脚本
# 使用PowerShell测试后端API

Write-Host "========================================"
Write-Host "   API Test"
Write-Host "========================================"
Write-Host ""

# Test 1: Get coffee types
Write-Host "[Test 1] Get coffee types"
Write-Host "GET http://localhost:8080/api/coffees/types"
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/coffees/types" -Method Get
    Write-Host "Response: " -NoNewline
    $response | ConvertTo-Json -Depth 5
} catch {
    Write-Host "Error: $_"
}
Write-Host ""

# Test 2: Create order (Wechat Pay)
Write-Host "[Test 2] Create order - Latte + Medium + Wechat"
Write-Host "POST http://localhost:8080/api/order/create"
$body1 = @{
    coffeeType = "LATTE"
    size = "MEDIUM"
    paymentType = "WECHAT"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/order/create" -Method Post -Body $body1 -ContentType "application/json"
    Write-Host "Response: " -NoNewline
    $response | ConvertTo-Json -Depth 5
} catch {
    Write-Host "Error: $_"
}
Write-Host ""

# Test 3: Create order (Alipay)
Write-Host "[Test 3] Create order - Americano + Large + Alipay"
Write-Host "POST http://localhost:8080/api/order/create"
$body2 = @{
    coffeeType = "AMERICANO"
    size = "LARGE"
    paymentType = "ALIPAY"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/order/create" -Method Post -Body $body2 -ContentType "application/json"
    Write-Host "Response: " -NoNewline
    $response | ConvertTo-Json -Depth 5
} catch {
    Write-Host "Error: $_"
}
Write-Host ""

Write-Host "========================================"
Write-Host "   Test Complete!"
Write-Host "========================================"
