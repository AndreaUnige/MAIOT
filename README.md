# WebServer PYTHON
Il progetto è composto da 2 macro parti:
 - un web server python, implementato tramite Flask;
 - la corrispondente App Android

<b>Funzionamento:</b><br/>
Il web server viene avviato tramite python e rimane in ascolto in locale sulla porta TCP 5000.<br/>
Al suo avvio legge il file .csv presente in _DATA/movies_genres_en.csv_ 

L'app presenta 2 bottoni <i>"Sequential"</i> o <i>"Parallel"</i> che triggerano:
 - <i>"Sequential"</i>: il download ed il relativo processing (rotazione) sequenziale di ogni immagine;
 - <i>"Parallel"</i>: il download ed il relativo processing (rotazione) in parallelo di ogni immagine;
<br/><br/>

Il download avviene sempre (indipendentemente dal fatto che l'utente abbia scelto <i>"Sequential"</i> o <i>"Parallel"</i>) mediante la creazione di thread in background, per evitare ogni operazione sul thread principale. Al termine dell'esecuzione, viene stampato a schermo il tempo complessivo impiegato.

<h3>Osservazione</h3>
Android prevedeva una classe <i>ad-hoc</i> per operazioni in backgroud denominata AsyncTask che però ad oggi è deprecata. Sebbene ancora funzionante, è sconsigliato utilizzarla. Pertanto, l'app è implementata attraverso l'uso di Thread ed interfacce per la gestione delle callback.<br/><br/>

<b>Link Utili:</b><br/>
 - https://developer.android.com/reference/android/os/AsyncTask
 - https://developer.android.com/reference/java/lang/Thread
 - https://unsplash.com/ (per le immagini)

