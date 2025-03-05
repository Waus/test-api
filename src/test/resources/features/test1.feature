Feature: Exchange Rates from NBP

 Scenario Outline: Kursy walut
    When Pobieram kursy walut
    Then Wyświetlam kurs dla waluty o kodzie <CurrencyId>
    Then Wyświetlam kurs dla waluty o nazwie <Currency>
    Then Wyświetlam waluty o kursie powyżej 5
    Then Wyświetlam waluty o kursie poniżej 3
   Examples:
     | CurrencyId | Currency |
     | 'USD'    |      "dolar amerykański"      |
     | 'EUR'    | "euro"                        |

