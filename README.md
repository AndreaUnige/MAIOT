# MovieRecognizer Android-Python
Il progetto è composto da 2 macro parti:
 - un web server python, implementato tramite Flask;
 - la corrispondente App Android
 
L'idea è di simulare il metodo con cui Netflix, PrimeVideo ed altri provider di film e serie forniscono suggerimenti sulla base del film appena visto.<br/><br/>

<h2>Funzionamento</h2>
<h3>Python</h3>
Il web server viene avviato tramite python e rimane in ascolto in locale sulla porta TCP 5000.<br/>
Al suo avvio legge il file .csv presente in _DATA/movies_genres_en.csv_ e lo carica in una struttura _DataFrame Panda_<br/><br/>

Il file è disponibile qui: https://davidsbatista.net/blog/2017/04/01/document_classification/<br/>
Il file contiene più di 117K film. Ogni film ha 27 features booleane associate a diversi generi (war, western, romance, scifi, ec...).<br/><br/>

Per tenere snello il funzionamento (sopratutto lato Android) delle 27 features booleane ne solo state lasciate (tramite codice, non modificando il DB) solamente 8: "Action", "Fantasy", "History", "Horror", "Music", "Sport", "War", "Western".<br/><br/>


Il server gestisce request http POST dove come body deve essere passato un JSON contenente il valore di _k_ e le 8 feature da riconoscere.
Il server parsa i dati json e restituisce i primi _k_ film 'più vicini' in termini di distanza euclidea dalle 8 feature contenute nel json. Il risultato è anch'esso passato come JSON.<br/><br/>

<h3>Esempio body della request HTTP</h3>
<i>{
    "k": 20,
    "movieToRecognize": {
    	"Action": 0,
    	"Fantasy": 0,
    	"History": 0,
    	"Horror": 0,
    	"Music": 0,
    	"Sport": 0,
    	"War": 0,
    	"Western": 1
    	}
}</i>

<h3>Esempio risposta del server</h3>
<i>{"data": [{"title": "Alias Smith and Jones (1971) {The Man Who Broke the Bank at Red Gap (#2.17)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {The Legacy of Charlie ORourke (#1.15)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {The Girl in Boxcar #3 (#1.5)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {The Day They Hanged Kid Curry (#2.1)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {The Bounty Hunter (#2.12)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {Stagecoach Seven (#1.9)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {Smiler with a Gun (#2.4)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {Shootout at Diablo Station (#2.11)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {Only Three to a Bed (#3.12)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {Never Trust an Honest Man (#1.14)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {McGuffin (#3.10)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {Jailbreak at Junction City (#2.3)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {High Lonesome
Country (#3.2)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {Everything Else You Can Steal  (#2.13)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {Dont Get Mad, Get Even (#2.21)}", "score": 100.0}, {"title": "Alias Smith and Jones (1971) {Witness to a Lynching (#3.11)}", "score": 100.0}, {"title": "Zorro (1990) {He Who Lives by the Sword (#2.5)}", "score": 100.0}, {"title": "Zorro (1990) {Zorro: The Legend Continues (#1.0)}", "score": 100.0}, {"title": "Zorro (1990) {Wicked, Wicked Zorro (#3.9)}", "score": 100.0}, {"title": "Zorro (1990) {Whereabouts (#1.16)}", "score": 100.0}]}
</i>


<h3>App Android</h3>
La rispettiva App Android ha 8 _CheckBox_ (una per ogni feature) per permettere all'utente di settare i valori. E' presente anche una _EditText_ per selezionare manualmente il valore di _k_. Alla pressione del bottone _Submit_ l'app genera un json e "colpisce l'endpoint del server (al momento harcoded dentro la classe _Constants_). Il risultato restituito dal server viene poi recuperato e visualizzato in un'altra activity formattato e renderizzato HTML.<br/><br/>

La request http viene effettuata tramite libreria _Volley_: https://google.github.io/volley/<br/><br/>

<b>Osservazioni:</b>
 - L'App effettua chiamate http (e non https) per semplicità. Per permetterlo, è stato inserito l'attributo _android:usesCleartextTraffic="true"_ nel _AndroidManifest.xml_.
 - La request http è fatta su un Thread parallelo.<br/><br/>


<b>Possibili sviluppi futuri:</b>
 - Implementare il riconoscimento con features numeriche, ad esempio assegnando un valore tra 0 e 10 per ogni feature.
 - Implementare la versione _weighted_ del K-NN.
 - Usare altri classificatori (J-48, SVM, ecc..) per il riconoscimento del film <br/><br/>

<b>Link Utili:</b><br/>
 - https://davidsbatista.net/blog/2017/04/01/document_classification/
 - https://flask.palletsprojects.com/en/stable/
 - https://en.wikipedia.org/wiki/K-nearest_neighbors_algorithm
 - https://google.github.io/volley/
 - https://jsonlint.com/
 - https://www.postman.com/downloads/
 
