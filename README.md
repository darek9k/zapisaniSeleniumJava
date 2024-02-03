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
