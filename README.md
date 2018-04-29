# [Semesteroppgave 2: “Fire på rad”](https://retting.ii.uib.no/inf101.v18.sem2/blob/master/SEM-2.md)


* **README**
* [Oppgavetekst](SEM-2.md)

Dette prosjektet inneholder [Semesteroppgave 2](SEM-2.md). Du kan også [lese oppgaven online](https://retting.ii.uib.no/inf101.v18.oppgaver/inf101.v18.sem2/blob/master/SEM-2.md) (kan evt. ha små oppdateringer i oppgaveteksten som ikke er med i din private kopi).

**Innleveringsfrist:**
* Hele oppgaven skal være ferdig til **fredag 27. april kl. 2359** ([AoE](https://www.timeanddate.com/worldclock/fixedtime.html?msg=4&iso=20180427T2359&p1=3399))
* [Ekstra tips til innlevering](https://retting.ii.uib.no/inf101/inf101.v18/wikis/innlevering)

(Kryss av under her, i README.md, så kan vi følge med på om du anser deg som ferdig med ting eller ikke.)

**Utsettelse:** Hvis du trenger forlenget frist er det mulig å be om det (spør gruppeleder – evt. foreleser/assistenter hvis det er en spesiell situasjon). Hvis du ber om utsettelse bør du være i gang (ha gjort litt ting, og pushet) før fristen
   * En dag eller to går greit uten begrunnelse.
   * Eksamen er relativt tidlig i år, så vi vil helst unngå lange utsettelser.
   * Om det er spesielle grunner til at du vil trenge lengre tid, så er det bare å ta kontakt, så kan vi avtale noe. Ta også kontakt om du [trenger annen tilrettelegging](http://www.uib.no/student/49241/trenger-du-tilrettelegging-av-ditt-studiel%C3%B8p). 
   
# Fyll inn egne svar/beskrivelse/kommentarer til prosjektet under
* Levert av:   *NAVN* (*BRUKERNAVN*)
* [x] hele semesteroppgaven er ferdig og klar til retting!
* Code review:
   * [x] jeg har fått tilbakemelding underveis fra @brukernavn, Oda Innana Stene
   * [x] jeg har gitt tilbakemelding underveis til @brukernavn, Oda Innana Stene
* Sjekkliste:
   * [X] Kjørbart Fire på Rad-spill
   * [X] Forklart designvalg, hvordan koden er organisert, abstraksjon, og andre ting 
   * [X] Tester
   * [X] Dokumentasjon (JavaDoc, kommentarer, diagrammer, README, etc.)
   * [X] Fornuftige navn på klasser, interfaces, metoder og variabler
   * [X] Fornuftige abstraksjoner og innkapsling (bruk av klasser, interface, metoder, etc.)

## Oversikt
Jeg har valgt å løse oppgaven med print-statements i stedet for en GUI. All print (output) og input skjer gjennom IO-klassen, så det skal være relativt enkelt å legge inn en GUI. 
Spillet er delt inn i klasser som har hver sin oppgave: 

- Main initierer Game() og kjører startGame()

-~~ Game har oversikten over hva som skjer og sørger for at ting skjer i riktig rekkefølge. Litt som en Dungeon Master i DnD, eller en ordfører i en diskusjon. Game skal selv ha relativt lite funksjon, men sørger for at ting skjer riktig i henhold til hvordan spillet skal oppføre seg. Game sørger for at spillerene utfører hver sin tur, den sjekker i reglene om en spiller har vunnet, og den sørger for at brikkene blir plassert riktig inn i brettet (at de faller ned).
Game er nødt til å ha funksjonaliteten til å "droppe" en token på grunn av måten Board er blitt implementert på. Siden Board blir fyllt opp med "gjennomsiktige" tokens i stedet for en haug med null-verdier, så må funksjonen som sjekker om en rekke har plass til en ny token finne ut av om tokene som ligger der er BLANK eller om de har en farge. Siden Board skal være generisk så ber Game om en token på en gitt posisjon, sjekker om det er mulig å plassere en spillers Token der. Hvis et er mulig så skifter Game fargen på den korrekte token. For spilleren ser det ut som at en ny farget token blir droppet i en rad der det tidligere var tomt. ~~

      Game har ikke lenger dropToken(), den er flyttet til Board. 

- Board<T>. Basert på MyGrid fra forelesningene. Generisk klasse som inneholder en liste med Tokens. Klassen har enkel funksjonalitet som å returnere en token på en viss posisjon i griden. 

      Arver nå fra Grid<T> og har egne metoder som er unike for connect4. Den fylles nå også opp med null istedetfor BLANK tokens.


- IO. All input og output. Player kaller denne klassen for å få input fra brukeren, Game kaller også denne klassen for å skrive ut spillbrettet. 
Målet er at denne klassen enkelt skal kunne skiftes ut med en GUI. 

- IPlayer. Felles Interface for Player og AIPlayer. Har kun metodene doTurn og getColor. GetColor returnerer denne spillerens farge (RED eller YELLOW), mens doTurn blir kallt av Game og gjør at spilleren utfører sitt trekk.

   - Player. DoTurn her ber om input fra brukeren gjennom IO-klassen. 
   - IAPlayer. DoTurn her returnerer en int basert på en algoritme. Foreløpig gis det nummeret til en tilfeldig rad, men i fremtiden kan denne metoden returnere et smartere valg. Det bør være enkelt å implementere (gitt at man har en god algoritme) siden alle IPlayers får tilgang til Board når de blir kallt av Game, og fordi den kun skal returnere en int som er mellom 1 - board.getWidth

- TokenColor. Enum som gir en verdi lik den fargen en token har. Hver spiller han sin egen TokenColor og hver token har enten TokenColor BLANK,RED eller YELLOW. 

- ~~Rules. Regelbok og dommer. Her ligger den store algoritmen som sjekker om en spiller har vunnet. Dette er en modifisert algoritme herfra: https://codereview.stackexchange.com/questions/127091/java-connect-four-four-in-a-row-detection-algorithms?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa~~

      Algoritmen er fremdeles løst basert på den linken, men harn å for-loops, samt at den må sjekke etter null i Board<T>. Dette gjør den litt mer 
      komplisert, så jeg delte denn opp i flere metoder. En som sjekker loddrett, en som sjekker vannrett og en som sjekker diagonalt begge veier. 
      Alle disse blir kjørt gjennom hasWon(). 
      Den er nå litt mindre effektiv, men mye mer fleksibel. Vi kan for eksempel endre hvor mange like på rad man må ha for å vinne ved hjelp av variablen
      WIN_CONDITION i Game().
   
   ~~Opprinnelig ville jeg kun sjekke siste droppede token, men dette viste seg å være vanskelig når jeg skulle sjekke diagonalt. Nå sjekker den alle tokens om den har fire på rad, men den hopper over Tokens som har TokenColor.BLANK. 
   Ulempen med algoritmen jeg har valgt er at den kun sjekker fire på rad. Det må en del koding til for at den f.eks skal sjekke fem på rad, men det er mulig jeg finner en måte å fikse det på etterhvert.  ~~

      Se endring over. 

-Tester: Tester for hver klasse sin metode. Sjekker at riktig output får ved et gitt input. 

### Bruk
* For å starte programmet kjør: Main()
      Velg mellom tre game-modes: 
         0: Spill to spillere mot hverandre.
         1: Éns spiller og én AI
         2: To AI mot hverandre

## Designvalg
Jeg har valgt å dele det opp slik: 
- ~~Board er en generisk klasse og ikke spesielt laget for connect 4. det er en grid som får beskjed om hvor stor den skal være og som holder orden på hvor i       griden dens elementer er plassert. Det er mulig jeg flytter dropToken over fra Game til Board og kaller den for dropElement. ~~

      ENDRET: Board er nå en generisk klasse spessielt laget for connect four, og den arver en haug med vanlige grid-metoder fra en abstrakt klasse Grid<T>. 
         I tillegg fylles Board opp med null-verdier til å begynne med i stedet for BLANK tokens. Dette gjør jeg kunne flytte dropToken() inn i Board fra Game, siden metoden ikke lenger trenger å sjekke fargen på forskjellige tokens. Den kan heller bare sjekke om det ligger en token på en bestemt plass eller ikke. 

- ~~Game er derimot laget for connect four og inneholder funksjonalitet som gjør det mulig å bruke Board til connect 4. Den viktigste er dropToken() som gjør at brikkene faller lengst ned i raden. ~~ 
      ENDRET: dropToken er nå lagt til Board.

- Rules inneholder algoritmen som sjekker om noen har vunnet. Den er helt adskilt slik at det skal være mulig å endre på reglene. 
   Den er i tillegg generisk, slik at det skal være mulig for den å sjekke andre ting enn mine Tokens. 
   Den benytter seg av en metode i Game for å sammenligne fargen på Tokens den finner. 

- IO er klassen som tar seg av all out- og input. Denne er statisk siden litt forskjellige klasser kan tenkes å benytte denne. Player kan tenkes å oppdatere   
   status eller lignende i senere versjoner, og da er det dumt og vi må sende rundt IO til forskjellige metoder. 

- IPlayer. Interface til Player og AIPlayer. 
   Disse oppfører seg veldig likt, eneste forskjellen er at Player ber om input fra brukeren gjennom IO, mens AIPlayer har en veldig dum AI som velger en tilfeldig int mellom 1 - boardWidth() som den legger en Token i.



### Bruk av abstraksjon
*(hvordan du har valgt objekter/klasser for å representere ting i spillet)*

- Board er selve brettet og inneholder all fysikken og logikken som er særegen for connect4. Board blir sendt mellom aktørene Game, Player, Rules og IO.

- IPlayer er selve spilleren som bestemmer hvor man skal legge neste brikke. 

- Rules er regelhådnboka. Man sender Board til Rules som så ser over here Board for å sjekke om det er en vinnende kombinasjon der.
   Hvis den finner en vinner, så sier den ifra til Game

- Game er den som styrer spillets gang. Holder orden på spillerenes tur, og sørger for at alle aktørene får den informasjonen den trenger for å 
   gjøre jobben sin. Den støtter også andre aktører, feks kan den fortelle Rules om to tokens er like eller ikke. 


### Erfaring – hvilke valg viste seg å være gode / dårlige?
*(designerfaringer – er det noe du ville gjort annerledes?)*
~~Ville kanskje fyllt opp Bord med null-verdier i stedet for å bruke tomme tokens. Mistenker at jeg da kunne lettere lagt dropToken inn i Board, og ikke i Game.
Jeg syns ikke det i utgangspunktet er noe problem å ha den i Game, men det hadde vært en liten forbedring. 
Hadde jeg startet på nytt igjen hadde jeg fyllt opp brettet med null-verdier heller enn BLANK tokens. Da kunne jeg enklere flyttet dropToken() inn i Board. Jeg forsøkte å gjøre det, men det ble kluss med måten jeg printer ut på og jeg måtte ha endret en del metoder. 

Hadde jeg hatt mer tid ville jeg skrevet om Board slik at den inneholder en dropToken-metode. Også hadde jeg laget en abstract class som Board kunne arvet fra, som inneholder alle de "standard" grid-metodene, slik at jeg hadde en vanlig Grid og en Board som er spesialisert for connect 4. 
Fikk dessverre ikke en slik abstrakt arving til å fungere. ~~
         
         ENDRET: Siden vi fikk litt mer tid fikk jeg gjort alt dette.
                 I tillegg fikk jeg gjort Rules generisk, skrevet en haug med tester og finpusset litt. 
                 Rules er også nå dynamisk, slik at man legge til andre regler (f.eks 5 på rad.)

## Testing
Laget tester til de fleste metodene der jeg sjekker at det som kommer ut samsvarer med det som går inn. 

Jeg brukte også testing aktivt i utviklingen, spesielt mot slutten. Det viste seg å være enkelt å skrive tester for hvordan ting
skal oppføre seg, og så utvikle metodene og klassene slik at de etterhvert klarte å plassere testene. 

Fikk en tilbakemelding om at jeg brukte for mye print og ikke nok asserts, i testene, nå er så og si alle testene kun asserts, så man vil se masse grønt :)

      Testen i PlayerTest krever input for å sjekke at feil input ikke kræsjer programmet

## Funksjonalitet, bugs
Spillet fungerer bra, og jeg er fornøyd med hvordan det er satt opp. Den eneste kjente bugen er at det ikke kommer noen feilmelding hvis brettet fylles
helt opp med brikker uten at noen har fått fire på rad. 

## Evt. erfaring fra code review
*(lærte du noe av å gå gjennom din eller andres kode?)*
Review med Oda Innana Stene. 
Hjalp meg til å gjøre Board helt generisk. Tidligere hadde jeg en del metoder som castet T til (Token) inne i Board<T>.
   Dette hadde gjort det litt mer komplisert å bytte ut mine Tokens med en annen klasse som fungerer litt annerledes eller heter noe annet.  

## Annet


