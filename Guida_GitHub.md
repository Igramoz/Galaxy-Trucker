# Guida su come lavorare al progetto Galaxy-Trucker

Lavorare con GitHub.

## Prima volta

### Clonare il repository

Apri il terminale e clona il repository con il seguente comando:

```bash
git clone https://github.com/Igramoz/Galaxy-Trucker.git
```

### Importare il progetto in Eclipse

1. Apri Eclipse.
2. Vai su `File > Open File...`.
3. Seleziona la cartella `Galaxy-Trucker` che hai clonato sul tuo computer.

### Creare classi e scrivere codice

Nel progetto importato, crea le classi e scrivi il codice (oppure copia il codice che hai già scritto) nei pacchetti corretti. Per capire quali sono i pacchetti corretti, guarda il file tree su GitHub.

### Eseguire commit e push

1. Apri la cartella `Galaxy-Trucker` sul tuo computer.
2. Fai clic con il tasto destro e seleziona `Apri nel terminale`.
3. Esegui i seguenti comandi:

```bash
git add .
git commit -m "Descrizione del commit"
git push
```

**Nota:** È importante scrivere una buona descrizione del commit perché verrà vista dal professore. Inoltre, se un giorno avremo problemi, sarà necessario capire quale commit ha determinato l'errore solo leggendo la descrizione.

## Le volte successive

### Aggiornare il repository locale

Prima di iniziare a lavorare, assicurati che il tuo repository locale sia aggiornato con l'ultima versione. Apri il terminale nella cartella `Galaxy-Trucker` e esegui:

```bash
git pull
```

### Eseguire commit e push

Dopo aver fatto le modifiche, esegui i seguenti comandi:

```bash
git add .
git commit -m "Descrizione del commit"
git push
```

**Nota:** Come per la prima volta, ricordati di scrivere una buona descrizione del commit.