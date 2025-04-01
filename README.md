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

``public static String[] URLs = new String[] {
            "https://images.unsplash.com/photo-1597348989645-46b190ce4918?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1737100593814-8ceb04f29cca?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://plus.unsplash.com/premium_photo-1738090991139-d185eb31cb12?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1738362542910-b06a4f00f024?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1591154669695-5f2a8d20c089?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1738676469786-6fe6c1acabf2?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://plus.unsplash.com/premium_photo-1671209881093-589125ec2a86?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1738762388661-f09b9b9b5df2?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://plus.unsplash.com/premium_photo-1671017848638-a154949b71e1?q=80&w=1866&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    }; ``
    
    
 
 ```
function test() {
  console.log("notice the blank line before this function?");
}
```

<b>Link Utili:</b><br/>
 - https://developer.android.com/reference/android/os/AsyncTask
 - https://developer.android.com/reference/java/lang/Thread
 - https://unsplash.com/ (per le immagini)

