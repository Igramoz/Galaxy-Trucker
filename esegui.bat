@echo off
cd /d "%~dp0"
setlocal EnableDelayedExpansion

echo Compilazione dei sorgenti...

REM Crea la cartella bin se non esiste
if not exist bin mkdir bin

REM Costruisce una variabile contenente tutti i .java
set "sources="

for /R src %%f in (*.java) do (
    set "sources=!sources! %%f"
)

REM Compila tutti i file insieme
javac -d bin -cp src !sources!
if errorlevel 1 (
    echo  Errore durante la compilazione.
    pause
    exit /b 1
)

echo  Compilazione completata con successo.



REM Inserimento nomi giocatori
set count=0

:loop
set /a index=!count!+1
set name=
set /p name=Inserisci il nome del giocatore !index! (invio per fermarti): 

if "!name!"=="" (
    if !count! LSS 2 (
        echo Attenzione Devi inserire almeno 2 giocatori.
        goto loop
    ) else (
        goto run
    )
)

set "player!count!=!name!"
set /a count+=1

if !count! GEQ 4 goto run
goto loop

:run
echo Avvio partita con:
set "args="

set /a maxIndex=!count!-1
for /L %%i in (0,1,!maxIndex!) do (
    call set current=%%player%%i%%
    echo !current!
    set "args=!args! !current!"
)

echo Avvio: java -cp bin main.Main !args!
java -cp bin main.Main !args!
pause
