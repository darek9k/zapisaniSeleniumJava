# Testy z zadań 'zapisani' w Selenium Java

Selenium jest bardziej toporne niż Cypress. Trzeba pamiętać o 'czekaniu','czy element widoczny' etc i wszystkich znaczkach średniczkach etc.
Trochę rekompensuje to IntelliJ :)

FormPage - Page Object Model - nasze pola, metody.

FormTest - dwa testy jeden przy wyborze produktu z 0 ilością, drugi HappyPatch z wyciągnięciem count z response.

Wydobycie Responsa w Selenium to droga przez mękę. 
Dodatkowa klasa JsonParserHelper pozwala zaciągnąć całego JSON'a z response i przy użyciu klasy Gson przerobienia go na JsonObject
potem przeszukiwanie w poszukiwaniu określonego pola :) 

Dlaczego z response? - uparłem się, że to wydobęde i wydobyłem :) 
używanie takich selektorów o kilometrowej długości jest bleee :) 
przykład : /html/body/div/div[2]/div/div[3]/div/div/div[2]/div/form/div[1]/div/div/div/div/div/div/div/div/article/div/div[3]/div/div[4]/div[2]/div/div/div/div/div/div/div[5]/div

HappyPatchWitchXPatch - wg nazwy z długim .... bleee selektorem XPatch.

Może komuś sie przyda, może nie. 

Ja swoją lekcje odrobiłem.

PS. Kod się sam nie napisze :) 

Poniżej testy pierwotnie żądanie o Cypress, ale dla treningu dlaczego nie zrobić ich w Selenium Webdriver :)



Utwórz test Cypress, który:
- wejdzie na stronę https://testy-zadanie.zapisani.dev/
- wypełni poprawnie formularz
- zweryfikuje, że po wybraniu "Produkt ze skończoną pulą" po kliknięciu "Uzyskaj dostęp przedpremierowy" - zostanie wyświetlone okienko wyskakujące z komunikatem błędu mówiącym o braku dostępności produktu

Utwórz test Cypress, który:
- wejdzie na stronę https://testy-zadanie.zapisani.dev/
- wypełni poprawnie formularz
- zweryfikuje, że po wybraniu "Produkt z organiczoną pulą" po kliknięciu "Uzyskaj dostęp przedpremierowy" - zostanie przekierowany na stronę płatności.
- po wybraniu płatności gotówkowej przejdzie na stronę "sukcesu" z gratulacjami po poprawnym wypełnieniu formularza
- sprawdzi, że po ponownym wejściu na formularz, liczba dostępnych produktów będzie o 1 miejsza od liczby produktów dostępnych pierwotnie.
