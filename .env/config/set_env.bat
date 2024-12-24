@echo off
setlocal enabledelayedexpansion

:: Проверяем наличие файла config.env
if not exist "config.env" (
    echo Файл config.env не найден.
    exit /b 1
)

:: Читаем файл построчно
for /f "tokens=1,2 delims== eol=#" %%A in (config.env) do (
    set "key=%%A"
    set "value=%%B"

    :: Удаляем лишние пробелы
    for /f "delims=" %%i in ("!key!") do set "key=%%i"
    for /f "delims=" %%i in ("!value!") do set "value=%%i"

    :: Проверяем, существует ли переменная окружения
    for /f "tokens=2 delims==" %%E in ('reg query "HKCU\Environment" /v !key! 2^>nul') do (
        set "exists=1"
    )

    if not defined exists (
        :: Если переменная не существует, добавляем её
        reg add "HKCU\Environment" /v !key! /t REG_SZ /d "!value!" /f >nul
        echo Установлена переменная окружения: !key! = !value!
    ) else (
        echo Переменная окружения !key! уже существует, пропускаем.
        set "exists="
    )
)

:: Обновляем переменные окружения в текущей сессии
set "cmd=cmd.exe /c setx"
%cmd% >nul

echo Все изменения завершены.
exit /b 0