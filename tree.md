```markdown

src/
├── main/
│   └── Main.java // Punto di ingresso del gioco
├── controllore/
│   ├── GiocoController.java // Gestisce la logica del gioco
│   ├── NaveController.java // Gestisce la nave spaziale
│   ├── RisorsaController.java // Gestisce le risorse di gioco
│   ├── UtenteController.java // Gestisce le informazioni utente
│   ├── VoloController.java // Gestisce il volo della nave
│   └── PunteggioController.java // Gestisce i punteggi finali
├── modello/
│   ├── Gioco.java // Rappresenta il gioco stesso
│   ├── Nave.java // Rappresenta una nave spaziale
│   ├── ComponenteNave.java // Componente della nave
│   ├── Risorsa.java // Rappresenta una risorsa
│   ├── Utente.java // Rappresenta un utente
│   ├── Volo.java // Rappresenta un volo
│   └── Evento.java // Rappresenta un evento
├── servizio/
│   ├── CalcoloPunteggioServizio.java // Calcola i punteggi
│   ├── GestioneEventiServizio.java // Gestisce gli eventi
│   ├── GestioneRisorseServizio.java // Gestisce le risorse
│   ├── GestioneUtentiServizio.java // Gestisce gli utenti
│   └── GestioneVoloServizio.java // Gestisce i voli
└── utilità/
    ├── LoggerUtil.java // Utilità per i log
    └── FileUtil.java // Utilità per i file
```