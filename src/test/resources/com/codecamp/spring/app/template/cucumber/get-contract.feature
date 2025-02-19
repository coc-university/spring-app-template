# language: de
# keywords: https://cucumber.io/docs/gherkin/languages/

Funktionalität: Abruf eines Vertrags

  Vorbedingungen:
    Angenommen es geht um den Endpunkt "/v1/contract"

  Szenario: Erfolgreicher Abruf des Vertrags
    Angenommen ich bin authentifiziert
    Und es geht um den Vertrag "Versicherung ABC"
    Wenn ich den Vertrag versuche abzurufen
    Dann ist der Http Status Code 200
    Und der Body ist korrekt

  Szenario: Vertrag wird nicht gefunden
    Angenommen ich bin authentifiziert
    Und es geht um den Vertrag "Versicherung DEF"
    Wenn ich den Vertrag versuche abzurufen
    Dann ist der Http Status Code 404

  Szenario: Abruf des Vertrags ohne Authentifizierung
    Angenommen ich bin nicht authentifiziert
    Und es geht um den Vertrag "Versicherung ABC"
    Wenn ich den Vertrag versuche abzurufen
    Dann ist der Http Status Code 401