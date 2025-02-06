# Audio Recorder
Semplice App che illustra come utilizzare il microfono embedded del telefono per effettuare una registazione audio.<br/><br/>

L'App è effettua quindi registrazioni cicliche (come finisce una registrazione inizia immediatamente la successiva) e alla termine di una registrazione visualizza su grafico il segnale audio nel tempo e il suo spettro di potenza in frequenza.<br/><br/>

<b>Funzionamento:</b><br/>
All'avvio, attraverso la pressione del bottone di <i>start</i>, l'App inizia la registrazione. Il bottone <i>stop</i> la ferma.
<br/><br/>

<h3>Osservazioni</h3>
 - Il processing è molto semplice: visualizzazione in frequenza dello spettro del segnale vocale. Al momento tutto il processing è svolto sul <i>Main Thread</i>. Si potrebbe spostare su un thread parallelo per ottimizzare.

 - Come ulteriore esercizio si potrebbero estrarre alcune <i>features</i> spettrali come pitch, energie nelle sotto-bande, MFCC... Questo viene lasciato allo studente come esercizio.


<b>Link Utili:</b><br/>
 - https://github.com/wendykierp/JTransforms
 - https://engineering.icalculator.com/discrete-fourier-transform-calculator.html
 - https://www.szynalski.com/tone-generator/
 - https://www.youtube.com/watch?v=ksw2dD-lW6M&ab_channel=MusicinSpace

