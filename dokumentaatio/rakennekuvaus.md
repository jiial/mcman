***Ohjelman rakenne***

Peli jakautuu kolmeen pakkaukseen:
* fi.mcman.peli, jossa pelin käynnistävä Main-luokka
* fi.mcman.peli.kayttoliittyma, jossa käyttöliittymään littyvät luokat Kayttoliittyma, Piirtoalusta ja NappaimistonKuuntelija
* fi.mcman.peli.logiikka, jossa pelin varsinaiseen logiikkaan liittyvät luokat. Logiikan pääluokat ovat Peli, Pelaaja, Vihollinen ja Taso. 
  1. Peli-luokka hoitaa pelin päivittämisen. 
  2. Pelaaja-luokka toteuttaa pelaajan hahmon toiminnallisuuden. 
  3. Vihollinen luokka määrittelee kuinka pelin viholliset liikkuvat. 
  4. Taso sisältää pelin kenttään liittyvän datan, eli karkeasti mihin "tiileen" voi liikkua ja mihin ei.
