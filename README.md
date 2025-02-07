# Broadcast Receiver
Semplice App che illustra il funzionamento di un _Broadcast Receiver_.<br/><br/>

<b>Funzionamento:</b><br/>
All'avvio, l'App registra un _Broadcast Receiver_ su 2 _intent filter_:
 - un _Intent_ custom
 - un _Intent_ per la rilevazione del cambio della modalità aereo.
<br/><br/>

Scrivendo del testo nella _EditText_ e premendo il bottone si triggera l'Intent custom mentre passando da manualmente _AIRPLANE_MODE_ON_ a _AIRPLANE_MODE_OFF_ si triggera l'Intent sulla modalità aereo.<br/><br/>

<h3>Osservazione</h3>
A partire da Android 8.0 (API level 26), non bisogna più registrare i _Broadcast Receiver_ sul _Manifest_ dell'App per intenti __impliciti__ ovvero che non indicano un componente specifico, ma dichiarano un'azione generale da eseguire.

<b>Link Utili:</b><br/>
 - https://developer.android.com/reference/android/content/BroadcastReceiver
 - https://developer.android.com/about/versions/oreo/android-8.0-migration#rbr
 - https://developer.android.com/guide/components/intents-filters?hl=it#Types

