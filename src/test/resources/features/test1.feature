Feature: Exchange Rates from NBP

  Scenario: Kursy walut
    When  Pobieram kursy walut
    Then Wyświetlam kurs dla waluty o kodzie "USD"
    Then Wyświetlam kurs dla waluty o nazwie "dolar amerykański"
    Then Wyświetlam waluty o kursie powyżej 5
    Then Wyświetlam waluty o kursie poniżej 3
