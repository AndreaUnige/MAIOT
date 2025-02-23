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


Il server gestisce request http POST dove come body deve essere passato un JSON contenente il valore di _k_ e le 27 feature da riconoscere.
Il server parsa i dati json e restituisce i primi _k_ film 'più vicini' in termini di distanza euclidea dalle 27 feature contenute nel json. Il risultato è anch'esso passato come JSON.<br/><br/>

<h3>Esempio body della request HTTP</h3>
<i>{
    "k": 20,
    "movieToRecognize": {
        "Action": 0,
        "Adult": 0,
        "Adventure": 0,
        "Animation": 0,
        "Biography": 0,
        "Comedy": 1,
        "Crime": 0,
        "Documentary": 0,
        "Drama": 0,
        "Family": 0,
        "Fantasy": 0,
        "Game-Show": 0,
        "History": 0,
        "Horror": 0,
        "Music": 0,
        "Musical": 0,
        "Mystery": 0,
        "News": 0,
        "Reality-TV": 0,
        "Romance": 0,
        "Sci-Fi": 0,
        "Short": 0,
        "Sport": 0,
        "Talk-Show": 0,
        "Thriller": 0,
        "War": 0,
        "Western": 0
    }
}</i>

<h3>Esempio risposta del server</h3>
<i>{"data": [{"title": "The Rickey Smiley Show (2012) {The Reconciliation (#2.9)}", "score": 0.0}, {"title": "Ninja Babes
from Space (2007)", "score": 0.0}, {"title": "No Chill (2017)", "score": 0.0}, {"title": "Nneka the Uber Driver (2016)",
"score": 0.0}, {"title": "Hi-de-Hi! (1980) {Together Again (#6.1)}", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {The
Society Entertainer (#4.4)}", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {The Pay-Off (#3.4)}", "score": 0.0}, {"title":
"Hi-de-Hi! (1980) {The New Broom (#8.2)}", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {The Great Cat Robbery (#7.1)}",
"score": 0.0}, {"title": "Hi-de-Hi! (1980) {The Epidemic (#5.7)}", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {The
Beauty Queen Affair (#1.3)}", "score": 0.0}, {"title": "Hi-de-Hi! (1980) {Ted at the Helm (#6.2)}", "score": 0.0},
{"title": "High Spirits with Shirley Ghostman (2005) {Fat (#1.4)}", "score": 0.0}, {"title": "High Society (1995)",
"score": 0.0}, {"title": "High Heels, Low Standards (2013) {Stranger Danger (#1.1)}", "score": 0.0}, {"title": "Big and
Slim (2014)", "score": 0.0}, {"title": "Cuckoo (2012/II) {Potato Party (#2.2)}", "score": 0.0}, {"title": "Cuckoo
(2012/II) {Mums Group (#3.3)}", "score": 0.0}, {"title": "Cuckoo (2012/II) {Ken on E (#1.3)}", "score": 0.0}, {"title":
"Cuckoo (2012/II) {Grandfathers Cat (#1.4)}", "score": 0.0}]}
</i>


<h3>App Android</h3>
La rispettiva App Android ha 27 _CheckBox_ (una per ogni feature) per permettere all'utente di settare i valori. E' presente anche una _EditText_ per selezionare manualmente il valore di _k_. Alla pressione del bottone _Submit_ l'app genera un json e "colpisce l'endpoint del server (al momento harcoded dentro la classe _Constants_ come "http://192.168.1.249:5000/recognize"). Il risultato restituito dal server viene poi recuperato e visualizzato in un'altra activity.<br/><br/>

La request http viene effettuata tramite libreria _Volley_: https://google.github.io/volley/<br/><br/>

Osservazioni:
 - L'App effettua chiamate http (e non https) per semplicità. Per permetterlo, è stato inserito l'attributo _android:usesCleartextTraffic="true"_ nel _AndroidManifest.xml_.
 - La request http è fatta su un Thread parallelo.




<b>Link Utili:</b><br/>
 - https://davidsbatista.net/blog/2017/04/01/document_classification/
 - https://flask.palletsprojects.com/en/stable/
 - https://en.wikipedia.org/wiki/K-nearest_neighbors_algorithm
 - https://google.github.io/volley/
 - https://jsonlint.com/
 - https://www.postman.com/downloads/
 

