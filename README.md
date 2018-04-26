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
* [ ] hele semesteroppgaven er ferdig og klar til retting!
* Code review:
   * [ ] jeg har fått tilbakemelding underveis fra @brukernavn, ...
   * [ ] jeg har gitt tilbakemelding underveis til @brukernavn, ...
* Sjekkliste:
   * [X] Kjørbart Fire på Rad-spill
   * [ ] Forklart designvalg, hvordan koden er organisert, abstraksjon, og andre ting 
   * [ ] Tester
   * [X] Dokumentasjon (JavaDoc, kommentarer, diagrammer, README, etc.)
   * [ ] Fornuftige navn på klasser, interfaces, metoder og variabler
   * [ ] Fornuftige abstraksjoner og innkapsling (bruk av klasser, interface, metoder, etc.)

## Oversikt
Jeg har valgt å løse oppgaven med print-statements i stedet for en GUI. All print (output) og input skjer gjennom IO-klassen, så det skal være relativt enkelt å legge inn en GUI. 
Spillet er delt inn i klasser som har hver sin oppgave: 

- Main initierer Game() og kjører startGame()

- Game har oversikten over hva som skjer og sørger for at ting skjer i riktig rekkefølge. Litt som en Dungeon Master i DnD, eller en ordfører i en diskusjon. Game skal selv ha relativt lite funksjon, men sørger for at ting skjer riktig i henhold til hvordan spillet skal oppføre seg. Game sørger for at spillerene utfører hver sin tur, den sjekker i reglene om en spiller har vunnet, og den sørger for at brikkene blir plassert riktig inn i brettet (at de faller ned).
Game er nødt til å ha funksjonaliteten til å "droppe" en token på grunn av måten Board er blitt implementert på. Siden Board blir fyllt opp med "gjennomsiktige" tokens i stedet for en haug med null-verdier, så må funksjonen som sjekker om en rekke har plass til en ny token finne ut av om tokene som ligger der er BLANK eller om de har en farge. Siden Board skal være generisk så ber Game om en token på en gitt posisjon, sjekker om det er mulig å plassere en spillers Token der. Hvis et er mulig så skifter Game fargen på den korrekte token. For spilleren ser det ut som at en ny farget token blir droppet i en rad der det tidligere var tomt. 

- Board<T>. Basert på MyGrid fra forelesningene. Generisk klasse som inneholder en liste med Tokens. Klassen har enkel funksjonalitet som å returnere en token på en viss posisjon i griden. 

- IO. All input og output. Player kaller denne klassen for å få input fra brukeren, Game kaller også denne klassen for å skrive ut spillbrettet. 
Målet er at denne klassen enkelt skal kunne skiftes ut med en GUI. 

- IPlayer. Felles Interface for Player og AIPlayer. Har kun metodene doTurn og getColor. GetColor returnerer denne spillerens farge (RED eller YELLOW), mens doTurn blir kallt av Game og gjør at spilleren utfører sitt trekk.

   - Player. DoTurn her ber om input fra brukeren gjennom IO-klassen. 
   - IAPlayer. DoTurn her returnerer en int basert på en algoritme. Foreløpig gis det nummeret til en tilfeldig rad, men i fremtiden kan denne metoden returnere et smartere valg. Det bør være enkelt å implementere (gitt at man har en god algoritme) siden alle IPlayers får tilgang til Board når de blir kallt av Game, og fordi den kun skal returnere en int som er mellom 1 - board.getWidth

- TokenColor. Enum som gir en verdi lik den fargen en token har. Hver spiller han sin egen TokenColor og hver token har enten TokenColor BLANK,RED eller YELLOW. 

- Rules. Regelbok og dommer. Her ligger den store algoritmen som sjekker om en spiller har vunnet. Dette er en modifisert algoritme herfra: https://codereview.stackexchange.com/questions/127091/java-connect-four-four-in-a-row-detection-algorithms?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
   
   Opprinnelig ville jeg kun sjekke siste droppede token, men dette viste seg å være vanskelig når jeg skulle sjekke diagonalt. Nå sjekker den alle tokens om den har fire på rad, men den hopper over Tokens som har TokenColor.BLANK. 
   Ulempen med algoritmen jeg har valgt er at den kun sjekker fire på rad. Det må en del koding til for at den f.eks skal sjekke fem på rad, men det er mulig jeg finner en måte å fikse det på etterhvert.  

-Tester: Tester for hver klasse sin metode. Sjekker at riktig output får ved et gitt input. 

### Bruk
* For å starte programmet kjør: Main()

## Designvalg
Jeg har valgt å dele det opp slik: 
- Board er en generisk klasse og ikke spesielt laget for connect 4. det er en grid som får beskjed om hvor stor den skal være og som holder orden på hvor i griden dens elementer er plassert. Det er mulig jeg flytter dropToken over fra Game til Board og kaller den for dropElement. 

- Game er derimot laget for connect four og inneholder funksjonalitet som gjør det mulig å bruke Board til connect 4. Den viktigste er dropToken() som gjør at brikkene faller lengst ned i raden. 

- Rules inneholder algoritmen som sjekker om noen har vunnet. Den er helt adskilt slik at det skal være mulig å endre på reglene. 

- IO er klassen som tar seg av all out- og input. Denne er statisk siden litt forskjellige klasser kan tenkes å benytte denne. Player kan tenkes å oppdatere status eller lignende i senere versjoner, og da er det dumt og vi må sende rundt IO til forskjellige metoder. 

### Bruk av abstraksjon
*(hvordan du har valgt objekter/klasser for å representere ting i spillet)*



### Erfaring – hvilke valg viste seg å være gode / dårlige?
*(designerfaringer – er det noe du ville gjort annerledes?)*
Ville kanskje fyllt opp Bord med null-verdier i stedet for å bruke tomme tokens. Mistenker at jeg da kunne lettere lagt dropToken inn i Board, og ikke i Game.
Jeg syns ikke det i utgangspunktet er noe problem å ha den i Game, men det hadde vært en liten forbedring. 

## Testing
*(hvordan du har testet ting)*

## Funksjonalitet, bugs
Spillet fungerer bra, og jeg er fornøyd med hvordan det er satt opp. Den eneste kjente bugen er at det ikke kommer noen feilmelding hvis brettet fylles
helt opp med brikker uten at noen har fått fire på rad. 

## Evt. erfaring fra code review
*(lærte du noe av å gå gjennom din eller andres kode?)*
Review med Oda Innana Stene. 
Hjalp meg til å gjøre Board helt generisk. Tidligere hadde jeg en del metoder som castet T til (Token). 

## Annet
*(er det noe du ville gjort annerledes?)*

