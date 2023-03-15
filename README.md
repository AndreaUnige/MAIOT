App che dimostra il passaggio dati da una Activity ad un'altra.

Utilizza:
 - 'startActivity'
 - 'startActivityForResults'

Utilizzando 'startActivity' si apre un'altra Activity ma quando questa termina i dati che essa contiene non vengono back-propagati.
Diversamente 'startActivityForResults' viene usato per passare dei dati ad un'altra Activity ed intercettare (back-propagation) le eventuali modifiche.
