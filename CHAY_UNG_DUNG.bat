@echo off
title Bai Tap Buoi 4 - Android Runner
color 0A
echo =======================================================
echo    DANG MO UNG DUNG BAI TAP BUOI 4 TREN MAY AO...
echo =======================================================
cd /d D:\bai_tap_buoi_4

echo Kiem tra va cai dat app len may ao Android...
"D:\andord_studio_SDK\platform-tools\adb.exe" install -r "D:\bai_tap_buoi_4\app\build\outputs\apk\debug\app-debug.apk"

echo Khoi chay man hinh chinh...
"D:\andord_studio_SDK\platform-tools\adb.exe" shell am start -n com.example.baitapbuoi4/.MainActivity
echo.
echo Da mo ung dung thanh cong tren man hinh may ao!
pause
