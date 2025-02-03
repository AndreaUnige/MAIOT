# Edge AI
Semplice App che illustra come utilizzare una rete neurale addestrata con tensorflow (python) direttamente sullo smartphone.

<b>Funzionamento:</b><br/>
Il modello tensorflow è presente dentro la cartella del progetto <i>app/src/main/ml/</i> sotto il nome <i>model.tflite</i>.
<br/><br/>

Avviando il programma, l'app permette di scattare una foto da camera o recuperarla dalla galleria. La foto viene pre-processata (reshaping) e passata al modello che restituisce un valore di <i>confidence</i>. Viene riconosciuta la classe a <i>confidence</i> maggiore.

<h3>Osservazioni:</h3>
- Le tre classi utilizzate sono ("Apple", "Banana", "Orange")
- Le decisione è sempre presa secondo il criterio di <i>max-confidence</i>
- Non è presente "intelligenza" relativamente all'elemento nullo. In pratica, il modello decide sempre, anche se la foto che viene passata non contiente nessuna delle tre classi.

<h3>Possibili sviluppi futuri:</h3>
- Inserire la gestione dell'elemento nullo
- Spostare la classificazione dal main thread ad un background thread
<br/><br/>

<b>Link Utili:</b><br/>
 - registerForActivityResult: https://www.youtube.com/watch?v=JMdHMMEO8ZQ&ab_channel=MSCode009
 - Main youtube vide: https://www.youtube.com/watch?v=yV9nrRIC_R0&ab_channel=IJApps
 - COLAB NEURAL NET -> https://colab.research.google.com/drive/1XHNNYwDYYoaJaQVCy1uIeKqErXTYpwLu?usp=sharing

