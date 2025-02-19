# language: de
# keywords: https://cucumber.io/docs/gherkin/languages/

Funktionalität: Abruf eines Vertrags

  Szenario: Erfolgreicher Abruf des Vertrags
    Wenn ich authentifiziert bin
    Und ich den Vertrag abrufe
    Dann ist der Http Status Code 200
    Und der Body ist korrekt

  Szenario: Abruf des Vertrags ohne Authentifizierung
    Wenn ich nicht authentifiziert bin
    Und ich den Vertrag abrufe
    Dann ist der Http Status Code 401
