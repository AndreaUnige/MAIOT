# WEKA Machine Learning
Semplice App che illustra come importare un modello Weka direttamente sullo smartphone.

<b>Funzionamento:</b><br/>
Da Weka addestrare il modello scelto (nell'esempio l'albero binario J48) e salvarlo come file <i>.model</i>.<br/>
Il modello deve essere salvato nella cartella <i>assets</i> dell'App.<br/><br/>

NOTA:<br/>
Se la cartella <i>assets</i> non esiste, creala cliccando col tasto destro sulla cartella app -> New Folder -> Assets Folder.<br/>

Il dataset di riferimento è IRIS (<b>storico dataset di fiori</b>).
Avviando il programma, l'app permette di inserire i dati delle quattro feature previste (sepallength, sepalwidth, petallength, petalwidth) tramite quattro <i>EditText</i>.<br/>
Alla pressione del bottone, l'App recupera i valori delle 4 features e classifica tramite albero.

<h3>Osservazioni:</h3>
- Le tre classi utilizzate sono <i>Iris-setosa,Iris-versicolor,Iris-virginica</i>
- Le decisione è sempre presa secondo l'albero J48 addestrato tramite Weka
- Non è presente "intelligenza" relativamente all'elemento nullo (o outliers). In pratica, il modello decide sempre, anche se le features non sono ragionevoli.

<b>Link Utili:</b><br/>
 - Weka: https://www.weka.io/
