# WebServer PYTHON
Il progetto è composto da 2 macro parti:
 - un web server python, implementato tramite Flask;
 - la corrispondente App Android

<h2>Funzionamento</h2>
<h2>Web server Python</h2>
Il web server viene avviato tramite python e rimane in ascolto in locale sulla porta TCP 5000.<br/>
Al suo avvio legge il file .csv presente in _DATA/movies_genres_en.csv_ e lo carica in una struttura _DataFrame Panda_<br/><br/>

Il file è disponibile qui: https://davidsbatista.net/blog/2017/04/01/document_classification/<br/>
Il file contiene più di 117K film. Ogni film ha 27 features booleane associate a diversi generi (war, western, romance, scifi, ec...).<br/><br/>


Il server gestisce request http POST dove come body deve essere passato un JSON contenente il valore di _k_ e le 27 feature da riconoscere.
Il server parsa i dati json e restituisce i primi _k_ film 'più vicini' in termini di distanza euclidea dalle 27 feature contenute nel json. Il risultato è anch'esso passato come JSON.

<h3>Esempio body della request HTTP</h3>
_{
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
}_

<h3>Esempio risposta del server</h3>
{"data": [{"title": "The Rickey Smiley Show (2012) {The Reconciliation (#2.9)}", "score": 0.0}, {"title": "Ninja Babes
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


<b>Link Utili:</b><br/>
 - https://davidsbatista.net/blog/2017/04/01/document_classification/
 - https://flask.palletsprojects.com/en/stable/
 - https://en.wikipedia.org/wiki/K-nearest_neighbors_algorithm
 

