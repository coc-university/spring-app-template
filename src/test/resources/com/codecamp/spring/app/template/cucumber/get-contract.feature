# language: de
# keywords: https://cucumber.io/docs/gherkin/languages/

Funktionalität: Abruf eines Vertrags

  Vorbedingungen:
    Angenommen es geht um den Endpunkt "/v1/contract"

  Szenario: Erfolgreicher Abruf des Vertrags
    Angenommen ich bin authentifiziert
    Wenn ich den Vertrag abrufe
    Dann ist der Http Status Code 200
    Und der Body ist korrekt

  Szenario: Abruf des Vertrags ohne Authentifizierung
    Angenommen ich bin nicht authentifiziert
    Wenn ich den Vertrag abrufe
    Dann ist der Http Status Code 401
