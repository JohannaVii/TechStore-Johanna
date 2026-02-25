Projektuppgift – TechStore

📌
Introduktion
TechStore är en webbshop för tekniska produkter. Den är byggd som ett grupprojekt. Applikationen hanterar produkter -
kunder - leverantörer - recensioner. Min del var recensioner:  att skriva - ändra och ta bort omdömen. Allt är byggt med
Spring Boot och Thymeleaf. Databasen ligger på Neon  (PostGreSQL) i molnet.

🎯
Syfte
Projektet visar på hela processen - från kod till en färdig applikation. Som grupp har vi jobbat med Git - skrivit
tester - automatiserat byggen med GitHub Actions - paketerat i Docker och driftsatt i molnet.

⚙️
Funktionalitet
Reviews låter användaren att:

Se alla recensioner
Läsa en specifik recension
Skapa ny recension
Ändra recension
Ta bort recension

🛠️
Tekniker
Spring Boot, Thymeleaf
PostgreSQL (Neon), H2 (tester)
Maven, Git, GitHub
GItHub Actions, Docker
Render (istället för Koyeb)
Logback, JUnit, Mockito
Jenkins

🧱
Arkitektur
Koden delades upp i lager:

Controller - Tar emot anrop
Service - Affärslogik
Repository - Databasfrågor
Validator - Kontrollerar att data är korrekt
Entity - Motsvarar databastabell

Vilket gör koden enklare att testa och underhålla.

📝
Loggning
All loggning sparas i techstore-johanna.log.
Filen visar när recensioner hämtas - skapas - uppdateras - stöter på ett fel.
Det underlättar felsökning.

✅
Tester
(G) Enhetstester för Service - Testar alla metoder (Mockito)
(G) Enhetstester för Validator - Testar valideringsregler (JUnit)
(VG) Integrationstester - Testar att allt funkar med databasen (H2)

Alla 79 tester i projektet är godkända - lyser grönt!

🚀
Driftsättning
Problem: Koyeb krävde kreditkort och ID
Lösning: Jag hittade gratistjänsen - Render
Hur: Kopplade GitHub-repot, lade in miljövariabler, satte main som branch.
Resultat: Appen publiceras automatiskt vid varje push.

👥
Arbetsprocess
Vi jobbade med branches och pull requests på GitHub. Main är skyddad - Allt måste gå genom pull requests och godkännas.
Kommunikation har skett på plats eller på Discord vid distans.

👤
Min roll
För recensioner har följande byggts:

SQL-tabell, Entity, Repository, Service, Validator, Controller, HTML-sidor
Enhetstester för Service och Validator
Integrationstester för flödet
Logging i Service och Validator
LogBack-konfiguration
Jenkins-bygge (VG)

📈
Utmaningar
ReviewService i fel branch - Hamnade i första branchen av misstag. GitHub Action failade. Fick sedan återställa och
lägga den rätt.

Tabellnamn Review -> Reviews - Försökte att merga själv, men eftersom main var skyddad så fick göra pull request
istället.

Exceptions utan mapp eller klass - Skrev in exceptions i koden utan att skapa en exceptionklass då main saknade
exceptionmapp. Gruppkamrat påpekade det i en change request. Fixade innan merge.

Fel exception i test - Testade ReviewNotFoundException istället för ReviewNotValidException. Upptäckte och fixade.

Rating 0-100 -> 0-10 - Validator och tester matchade inte. Återgärdade.

Glömde @Test - Ena metoden kunde inte köras då annotation saknades. Lade till den.

💡
Vad har jag lärt mig
Verkligen fått känna på hur det är att arbeta och koda i grupp. Eftersom vi valde ett projekt som kändes relevant för
kursen, blev det lättare att leva sig in i det och se det som mer än bara ett skolprojekt.

Vi stötte på utmaningar med planering och att få projektet att fungera tillsammans. Jag har fått mer ingående övning i
Spring Boot, med mycket fokus på att jobba med branches, pull requests, automatiska byggen och blivit mer självsäker på
tester.

Loggning och Jenkins var nytt och den största utmaningen. När jag fastnade hittade jag pålitliga sidor att läsa på själv
om integrationstester, Jenkins och loggning. Det blev en del misstag längs vägen, men till slut kunde jag förstå varför
och åtgärda dem

📊
Slutsats
Applikationen fungerar och ligger på Render. Testen är alla gröna, loggning fungerar och Jenkins byggs utan problem.

Det som varit mycket bra utöver koden är gruppen. Alla var punktliga, hade respekt för varandra och ställde upp när
någon behövde hjälp. Speciellt Anwar som är mer kunnig drog ett stort lass.
Vi alla ville jobba ihop på plats, vilket kändes väldigt bra för arbetet. Sammarbetet var toppen. När vi jobbade själva
så tog alla ändå sitt ansvar. Kommunikationen var
aktiv och flöt på genom hela projektet.

Reflektion på sånt som kunde göras annorlunda: Tekniskt sett kunde vi ha kopplat ihop delarna ännu mer - till exempel
låtit recensioner vara kopplade till specifika produkter, och sett till att leverantörer och kunder hängde ihop också.
Vi kunde också varit mer enade om kodstandard, typ som kommentarer. Man kunde ha gjort en sista branch där man snyggade
till koden till exempel, typ clean code.

Jag reflekterar över min roll i gruppen. Ibland var jag kanske för lyhörd och lät andra göra saker jag själv velat testa
för att utmana mig. Samtidigt tog jag plats på andra områden. Nästa gång vill jag vara mer drivande inom det jag vill
kunna bättre, för att växa.

🔗
Länkar
Gruppens GitHub: https://github.com/iths-grupp/TechStore

Appen på Render: https://techstore.onrender.com

Min fork (VG): https://github.com/JohannaVii/TechStore-Johanna