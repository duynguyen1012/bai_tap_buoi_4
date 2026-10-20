@echo off
title Bai Tap Buoi 4 - Android Runner
color 0A
echo =======================================================
echo    DANG KHOI DONG MAY AO VA APP BAI TAP BUOI 4...
echo =======================================================
cd /d D:\bai_tap_buoi_4

set ADB="D:\andord_studio_SDK\platform-tools\adb.exe"
set EMULATOR="D:\andord_studio_SDK\emulator\emulator.exe"
set APK="D:\bai_tap_buoi_4\app\build\outputs\apk\debug\app-debug.apk"

REM Kiem tra xem may ao da chay chua
%ADB% devices | findstr /R "emulator-[0-9]*.*device$" >nul
if %ERRORLEVEL% equ 0 (
    echo May ao da dang bat tren man hinh!
) else (
    echo Dang mo cua so may ao Android Pixel 4 len man hinh desktop...
    start "" %EMULATOR% -avd Pixel_4 -netdelay none -netspeed full
    echo Dang cho may ao khoi dong xong, vui long doi giay lat...
    %ADB% wait-for-device
    :wait_boot
    timeout /t 3 /nobreak >nul
    for /f "tokens=*" %%a in ('%ADB% shell getprop sys.boot_completed 2^>nul') do set BOOT=%%a
    if not "%BOOT%"=="1" (
        echo Dang cho he dieu hanh Android len man hinh...
        goto wait_boot
    )
    echo May ao Android da khoi dong xong!
)

echo.
echo Dang cai dat va khoi chay ung dung Student List...
%ADB% install -r %APK%
%ADB% shell am start -n com.example.baitapbuoi4/.MainActivity

echo.
echo =======================================================
echo  DA MO UNG DUNG THANH CONG TREN CUA SO MAY AO CUA BAN!
echo =======================================================
pause
