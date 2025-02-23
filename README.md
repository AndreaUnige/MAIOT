# Repository MAIOT
Repository del materiale software (codice Android, java-based) relativo alle lezioni dell'insegnamento _APP MULTIMEDIALI E INTERNET OF THINGS_ (cod. 84490), CORSO DI LAUREA - INGEGNERIA ELETTRONICA E TECNOLOGIE DELL'INFORMAZIONE.

__Software disponibile:__
  - __Empty Activity__: semplice App di _Hello World_; Link: https://github.com/AndreaUnige/MAIOT/tree/EmptyActivity
  - __Esempio Views__: App che illustra i principi fondamentali relativi alla gestione del layout e dei principali Widgets; Link: https://github.com/AndreaUnige/MAIOT/tree/EsempioViews
  - __Passaggio dati tra Activities__: scopo di questa App è illustrare le diverse modalità con cui Activities differenti possono comunicare e scambiare dati; Link: https://github.com/AndreaUnige/MAIOT/tree/PassaggioTraActivities
  - __Broadcast Receiver__: semplice App che fornisce un semplice esempio del funzionamento di un Broadcast Receiver. Link: https://github.com/AndreaUnige/MAIOT/tree/BroadcastReceiver
  - __Accelerometro__: semplice App mono-activity che aggancia il sensore accelerometrico del telefono e mostra in tempo reale (tramite TextViews) i valori di accelerazione lungo i 3 assi (x, y, z) espressi in m/s^2. Link: https://github.com/AndreaUnige/MAIOT/tree/Accelerometro
  - __AudioRecorder__: App mono-activity che fornisce implementa un registratore audio. All'avvio del registratore vengono acquisiti campioni PCM (16 bit), disponibili come array di short all'interno del codice, successivamente salvati dentro un file .wav. Link: https://github.com/AndreaUnige/MAIOT/tree/AudioRecorder
Nota: l'implementazione NON PREVEDE l'uso di AsyncTask ma solo di Thread. Per la versione con AsyncTask vedi [qui](https://2022.aulaweb.unige.it/mod/folder/view.php?id=112359)
  - __Service__: App mono-activity che illustra i principi basi di un _Service_. Vengono descritti ed evidenziati i principali funzionamenti degli _Started  Services_ (Foreground e Background) e dei _Bounded Services_. Fare riferimento alla guida Android disponibile [qui](https://developer.android.com/guide/components/services). Link: https://github.com/AndreaUnige/MAIOT/tree/Service
  - __WiFiScanner__: App mono-activity che illustra come effettuare scansioni WiFi recuperando informazioni come la RSS, MAC Address e SSID. Link: https://github.com/AndreaUnige/MAIOT/tree/WifiScanner
  - __AsyncTask__: App mono-activity che illustra come effettuare operazioni tramite thread in background asincroni. Link: https://github.com/AndreaUnige/MAIOT/tree/AsyncWithThreads
  - __MQTT__: App che che illustra i principi del protocollo MQTT. Prevede sue modalità di funzionamento: _Publisher_ e _Subscriber_. Link: https://github.com/AndreaUnige/MAIOT/tree/MQTT
  - __Edge-AI__: App che che illustra come utilizzare una rete neurale <i>on the edge</i> (ovvero mettendola onboard al telefono). Link: https://github.com/AndreaUnige/MAIOT/tree/EdgeAI_NeuralNet
- __WebServerPython 4 Movies__: Utilizzo di python per realizzare un semplice webserver. L'app interroga il server e si fa restituire tramite JSON una lista di film preferiti. Link: https://github.com/AndreaUnige/MAIOT/tree/WebServer_Python_MovieRecognizer

    
__Disclaimer:__
Tutto il codice del seguente repo è rilasciato secondo la licenza GNU General Public License (GNU GPL) 3.0.
Lo scopo di questa licenza è di rendere un manuale, un testo o altri documenti scritti "liberi" nel senso di assicurare a tutti la libertà effettiva di copiarli e redistribuirli, con o senza modifiche, a fini di lucro o no. In secondo luogo questa licenza prevede per autori ed editori il modo per ottenere il giusto riconoscimento del proprio lavoro, preservandoli dall'essere considerati responsabili per modifiche apportate da altri. Questa licenza è un "copyleft": ciò vuol dire che i lavori che derivano dal documento originale devono essere ugualmente liberi.
Ref: https://www.gnu.org/licenses/gpl-3.0.en.html

