#!/bin/bash

# Compila
cd ..
javac -d bin $(find src -name "*.java")


if [ $? -ne 0 ]; then
  echo "❌ Errore durante la compilazione"
  exit 1
fi

# Leggi i nomi (max 4, min 2)



noms=()
for i in 1 2 3 4; do
  read -p "Inserisci il nome del giocatore $i (invio per fermarti): " nome
  if [ -z "$nome" ]; then
    if [ $i -le 2 ]; then
      echo "⚠️ Devi inserire almeno 2 giocatori."
      i=$((i - 1)) # Ripeti
      continue
    else
      break
    fi
  fi
  noms+=("$nome")
done

# Verifica numero giocatori valido
if [ ${#noms[@]} -lt 2 ]; then
  echo "❌ Numero di giocatori insufficiente."
  exit 1
fi

# Esegui
echo "✅ Avvio partita con: ${noms[*]}"
java -cp bin main.Main "${noms[@]}"
