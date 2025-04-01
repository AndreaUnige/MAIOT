# AsyncTasks
Semplice App che illustra come utilizzare thread paralleli per effettuare operazioni computazionalmente pesanti.

<b>Funzionamento:</b><br/>
L'app scarica 9 immagini ad alta definizione (le cui URL sono hard-coded dentro il codice per semplicità). Ogni volta che una immagine è scaricata viene rotata per un certo numero di volte (es: 100) per simulare un processing oneroso.<br/><br/>

L'app presenta 2 bottoni <i>"Sequential"</i> o <i>"Parallel"</i> che triggerano:
 - <i>"Sequential"</i>: il download ed il relativo processing (rotazione) sequenziale di ogni immagine;
 - <i>"Parallel"</i>: il download ed il relativo processing (rotazione) in parallelo di ogni immagine;
<br/><br/>

Il download avviene sempre (indipendentemente dal fatto che l'utente abbia scelto <i>"Sequential"</i> o <i>"Parallel"</i>) mediante la creazione di thread in background, per evitare ogni operazione sul thread principale. Al termine dell'esecuzione, viene stampato a schermo il tempo complessivo impiegato.

<h3>Osservazione</h3>
Android prevedeva una classe <i>ad-hoc</i> per operazioni in backgroud denominata AsyncTask che però ad oggi è deprecata. Sebbene ancora funzionante, è sconsigliato utilizzarla. Pertanto, l'app è implementata attraverso l'uso di Thread ed interfacce per la gestione delle callback.<br/><br/>

<h3>URLS delle immagini da scaricare</h3>
```sh
cd dillinger
npm i
node app
```

<b>Link Utili:</b><br/>
 - https://developer.android.com/reference/android/os/AsyncTask
 - https://developer.android.com/reference/java/lang/Thread
 - https://unsplash.com/ (per le immagini)

