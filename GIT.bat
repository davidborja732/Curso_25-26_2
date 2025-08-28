@echo off
setlocal

:: Cambia esta ruta a tu repositorio
set REPO_PATH=D:\Grado Superior\Curso_25-26_2

:: Cambia esta ruta a la ubicación de bash.exe en tu instalación de Git
set GIT_BASH_PATH="C:\Program Files\Git\bin\bash.exe"

:: Configurar nombre de usuario y correo electrónico de Git start "" %GIT_BASH_PATH% -c "git config --global user.name \"David Borja Mateo\" && git config --global user.email \dborjam@iesch.org\

:: Obtener la fecha actual en el formato YYYY-MM-DD usando PowerShell 
for /f %%i in ('powershell -command "Get-Date -Format yyyy-MM-dd"') do set commit_message=%%i

:menu
cls
echo.
echo Selecciona una opcion:
echo 1. Hacer un git push
echo 2. Hacer un git pull
echo 3. Salir
echo.
set /p option="Introduce el numero de la opcion deseada: "

if "%option%"=="1" (
    :: Mostrar el valor del commit_message para depuración
    echo commit_message="[%commit_message%]"

    :: Añade las comillas externas y ejecuta el comando
    start "" %GIT_BASH_PATH% -c "cd '%REPO_PATH%' && git add . && git commit -m \"%commit_message%\" && git push; read -p 'Presiona Enter para continuar...'"
) else if "%option%"=="2" (
    :: Ejecuta git status para verificar si hay un merge pendiente y luego git pull
    start "" %GIT_BASH_PATH% -c "cd '%REPO_PATH%' && git status; if [ -f .git/MERGE_HEAD ]; then echo 'Hay un merge pendiente. Resuélvelo antes de continuar.'; else git pull --no-commit; fi; read -p 'Presiona Enter para continuar...'"
) else if "%option%"=="3" (
    echo Saliendo...
    exit 
) else (
    echo Opción no válida. Intenta de nuevo.
    timeout /t 2 >nul
    goto menu
)
endlocal
exit /b
