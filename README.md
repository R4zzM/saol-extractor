saol-extractor
==============

<b>DISCLAIMER:</b>
This application is (hopefully) legal but the way you use the material that you can extract with it might not be. I have no part or responsibility in your actions!! 

<b>For the English speaking</b>
This Android app allows you to extract SAOL (Svenska Akademins Ordlista, a Swedish wordlist) from the Android App into a plain text file to use it for whatever personal pupose you want. Since you must understand Swedish for the wordlist to be of any meaning, the rest of this readme file is in Swedish for convenience reasons.

<b>Översikt</b>
Som sagt: Denna app demonstrerar hur man kan extrahera precis allt innehåll i Svenska Akademins Ordlista via deras app som finns att hämta gratis från Google Play. 
Även om applikationen inte på något sätt är fullständig och kan hämta ut precis alla börningar mm. visar den principen ganska så väl och det är relativt enkelt att bygga ut den om man skulle vilja ha den informationen också. 

saol-extractor är testad med version 1.0.6 av SAOLs appen men principen borde fungera för vilken version som helst då den är ganska så generell.

<b>Förkrav</b> 
- En styck fysisk Android telefon som kan köra SAOL appen. 
- En apk dekompilator, exempelvis dex2jar.  
- Android SDK installerad på din dator (behövs för att ladda på appen).
- Git.

<b>Tillvägagång</b>
1, Använd din Android telefon för att ladda ner SAOL appen från Google Play. 

2, Här kan det krävas en del pillande: Du måste nu extrahera SAOL appens apk-fil från telefonen till datorn. 
Eftersom inte jag kan tillhandahålla en guide för varenda telefon som finns ute på marknaden måste du labba lite själv här. Jag använde en telefon som kunde göra backuper av alla installerade appar på telefonen till SD-kortet, sen kopplade jag in SD-kortet till datorn och plockade ut filen. Har inte din telefon denna funktion och du inte kan hitta någon annan väg frammåt måste jag tyvärr hänvisa dig till fabror Google.

3, När du ändå pillar med telefonen kan du passa på att kopiera filerna saolarticles.sox och saolff.sox till valfri plats på din dator. Dessa ligger i appens "datakatalog" vilket (i Linux) är en dold katalog som jag *tror* vanligtvis ligger på SD-kortet. Om du installerar en utforkare (typ Astro file manager) på telefonen kommer du garanterat att kunna hitta den om du söker runt lite. Dessa filerna innehåller allt vi vill få ut, oturligt nog är de på någon form av binärformat så vi behöver något som kan avkoda dem...

4, Med hjälp av apk dekompilatorn spränger du upp apkn och plockar ut en fil som heter libsaoldict.so. Denna ligger under libs/armeabi. Kopiera denna till valfri plats på din dator. 
libsaoldict.so är ett bibliotek som läser upp och avkodar ord från SAOLs binärformat till SAOLs app som sen kan presentera dem på skärmen för användaren.
"saol-extractor" (som du snart skall starta) hookar in sig mot funktionerna i biblioteket via JNI men skriver istället orden till en fil. 
Bra att veta är att för att detta skall fungera måste bl.a tillsynes oskyldiga detaljer som paket- och klassnamn vara identiska mot SAOLs app. 
Om du bestämt dig för att läsa ut mer information ur binärfilerna och vill ändra i appen så ta det försiktigt om du känner att du inte riktigt vet vad du pillar på.  

5, Om du inte redan klonat ner repot så gör det, i shellet skriv:
<code>git clone https://github.com/R4zzM/saol-extractor.git</code>

6, Skapa katalogen libs/armeabi i repot som du just klonade ner:
<code>mkdir -p libs/armeabi</code>
Placera libsaoldict.so i den nyskapade katalogen.

7, Skapa en Android simulator med hjälp av din SDK. Se till att ge den ett virtuellt SD-kort. Kopiera sen saolarticles.sox och saolff.sox till roten på SD-kortet (alltså under /sdcard).

8, Starta simulatorn och ladda på appen. I sitt grundutförande kommer applikationen att skriva ner alla ord till /sdcard/saoldict.txt. Detta kan dock ändras i SAOLActivity.java.  

Lycka till!
 






