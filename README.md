# transactions-summary

## Run
```gradle bootRun```

## Test
```
curl -d '{"clients":{"client":[{"info":{"name":"Tomasz","surname":"Karcznski"},"balance":{"total":"12110","currency":"PLN","date":"01.05.2020"},"transactions":[{"type":"income","description":"salary","date":"04.05.2020","value":"7500","currency":"PLN"},{"type":"outcome","description":"mortgage","date":"06.05.2020","value":"1100","currency":"PLN"},{"type":"income","description":"interests","date":"10.05.2020","value":"1700","currency":"PLN"},{"type":"outcome","description":"transfer","date":"11.05.2020","value":"1200","currency":"PLN"}]},{"info":{"name":"Natalia","surname":"Nowak","country":"Poland"},"balance":{"total":"6750","currency":"PLN","date":"01.05.2020"},"transactions":[{"type":"income","description":"salary","date":"04.05.2020","value":"10500","currency":"PLN"},{"type":"outcome","description":"transfer","date":"10.05.2020","value":"1200","currency":"PLN"},{"type":"outcome","description":"transfer","date":"11.05.2020","value":"1050,50","currency":"PLN"}]}]}}' -H 'content-Type: application/json' http://localhost:8080/transactions/summary
```
Response:
```
[{"clientInfo":{"name":"Natalia","surname":"Nowak","country":"Poland"},"balance":{"amount":14999.50,"currency":"PLN"},"revenue":{"amount":10500.00,"currency":"PLN"},"expenditure":{"amount":2250.50,"currency":"PLN"},"turnover":{"amount":12750.50,"currency":"PLN"}},{"clientInfo":{"name":"Tomasz","surname":"Karcznski","country":null},"balance":{"amount":19010.00,"currency":"PLN"},"revenue":{"amount":9200.00,"currency":"PLN"},"expenditure":{"amount":2300.00,"currency":"PLN"},"turnover":{"amount":11500.00,"currency":"PLN"}}]
```