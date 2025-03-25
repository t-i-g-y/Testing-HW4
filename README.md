# Тестирование методов реализации классе `root.vending.VendingMachine`. Ошибки в требованиях и коде:
## 0. Формат представления сообщений о проблеме
### 0.1. Код до исправления:
Строка №: `код`
### 0.2. Данные, на которых наблюдается некорректное поведения:
`название теста`: состояние объекта класса (`vendingMachine`), которое привело к некорректному поведению
### 0.3 Ожидаемое значение, полученное значение:
ожидаемое: `значение1`\
полученное: `значение2`
### 0.4. Код после исправления:
Строка №: `код`

## 1. Некорректная реализация проверки режима Mode объекта в методе `Response fillProducts()`.
### 1.1. Код до исправления:
Строка 96: `{`\
Строка 97: `num = max;`\
Строка 98: `return Response.OK;`
### 1.2. Данные, на которых наблюдается некорректное поведения
`void test_fill_products_default()`: `vendingMachine.mode = Mode.OPERATION`, `vendingMachine.fillProducts();`
### 1.3 Ожидаемое значение, полученное значение:
ожидаемое: `ILLEGAL_OPERATION`\
полученное: `OK`
### 1.4. Код после исправления:
Строка 97: `if(mode == Mode.OPERATION) return Response.ILLEGAL_OPERATION;`
Строка 98: `num = max;`
Строка 99: `return Response.OK;`

## 2. Некорректная реализация поведения метода `int getCoins2()` в режиме `Mode.Operating`.
### 2.1. Код до исправления:
Строка 84: `if(mode == Mode.OPERATION)`\
Строка 85: `return coins1;`
### 2.2. Данные, на которых наблюдается некорректное поведения
`void test_fill_get_coins2_op(int c1, int c2)`: `vendingMachine.mode = Mode.OPERATING`, `vendingMachine.coins2 = c2` `vendingMachine.getCoins2();`
### 2.3 Ожидаемое значение, полученное значение:
ожидаемое: `0`\
полученное: `c1`
### 2.4. Код после исправления:
Строка 84: `if(mode == Mode.OPERATION)`\
Строка 85: `return 0;`

## 3. Некорректная реализация проверки параметра метода `Response fillCoins(int c1, int c2)` на превышающие ограничения значения.
### 3.1. Код до исправления:
Строка 105:`if(c1 <= 0 || c2 > maxc1) return Response.INVALID_PARAM;`
### 3.2. Данные, на которых наблюдается некорректное поведения
`test_fill_coins_admin_invalid_param(int c1, int c2)`: `vendingMachine.mode = Mode.ADMINISTERING`, `vendingMachine.fillCoins(104, 3);`
### 3.3 Ожидаемое значение, полученное значение:
ожидаемое: `INVALID_PARAM`\
полученное: `OK`
### 3.4. Код после исправления:
Строка 105:`if(c1 <= 0 || c1 > maxc1) return Response.INVALID_PARAM;`

## 4. Некорректная реализация проверки параметра метода `Response setPrices(int p)` на неположительные значения.
### 4.1. Код до исправления:
Строка 128:`if(price <= 0) return Response.INVALID_PARAM;`
### 4.2. Данные, на которых наблюдается некорректное поведения
`test_set_prices_admin_invalid()`: `vendingMachine.mode = Mode.ADMINISTERING`, `vendingMachine.setPrices(-1)`
### 4.3 Ожидаемое значение, полученное значение:
ожидаемое: `INVALID_PARAM`\
полученное: `OK`
### 4.4. Код после исправления:
Строка 128: `if(p <= 0) return Response.INVALID_PARAM;`

## 5. Некорректная реализация проверки кол-ва монет метода `Response putCoin1()`.
### 5.1. Код до исправления:
Строка 136: `if(coins2 == maxc2)            return Response.CANNOT_PERFORM;`
### 5.2. Данные, на которых наблюдается некорректное поведения
`void test_put_coin1_op_max()`: `vendingMachine.mode = Mode.OPERATING`, `vendingMachine.fillCoins(1, 50);`, `vendingMachine.putCoin2()`
### 5.3 Ожидаемое значение, полученное значение:
ожидаемое: `CANNOT_PERFORM`\
полученное: `OK`
### 5.4. Код после исправления:
Строка 147: `if(coins1 == maxc1)            return Response.CANNOT_PERFORM;`

## 6. Некорректная реализация добавления монет метода `Response putCoin2()`.
### 6.1. Код до исправления:
Строка 138: `balance += coinval2;`\
Строка 139: `coins2++;`
### 6.2. Данные, на которых наблюдается некорректное поведения
`void test_put_coin1_op()`: `vendingMachine.mode = Mode.OPERATING`, `vendingMachine.putCoin2()`, `vendingMachine.getCurrentBalance()`
### 6.3 Ожидаемое значение, полученное значение:
ожидаемое: `2`\
полученное: `1`
### 6.4. Код после исправления:
Строка 138: `balance += coinval1;`\
Строка 139: `coins1++;`

## 7. Некорректная реализация проверки кол-ва монет метода `Response putCoin2()`.
### 7.1. Код до исправления:
Строка 147: `if(coins1 == maxc1)            return Response.CANNOT_PERFORM;`
### 7.2. Данные, на которых наблюдается некорректное поведения
`void test_put_coin2_op_max()`: `vendingMachine.mode = Mode.OPERATING`, `vendingMachine.fillCoins(1, 50);`, `vendingMachine.putCoin2()`
### 7.3 Ожидаемое значение, полученное значение:
ожидаемое: `CANNOT_PERFORM`\
полученное: `OK`
### 7.4. Код после исправления:
Строка 147: `if(coins2 == maxc2)            return Response.CANNOT_PERFORM;`

## 8. Некорректная реализация добавления монет метода `Response putCoin2()`.
### 8.1. Код до исправления:
Строка 149: `balance += coinval1;`\
Строка 150: `coins1++;`
### 8.2. Данные, на которых наблюдается некорректное поведения
`void test_put_coin2_op()`: `vendingMachine.mode = Mode.OPERATING`, `vendingMachine.putCoin2()`, `vendingMachine.getCurrentBalance()`
### 8.3 Ожидаемое значение, полученное значение:
ожидаемое: `2`\
полученное: `1`
### 8.4. Код после исправления:
Строка 149: `balance += coinval2;`\
Строка 150: `coins2++;`

## 9. Некорректная реализация вывода метода `enterAdminMode(long code)` при наличии внесенных средств.
### 9.1. Код до исправления:
Строка 114: `if(balance != 0) return Response.UNSUITABLE_CHANGE;`
### 9.2. Данные, на которых наблюдается некорректное поведения
`void test_enter_admin_mode_pos_balance_coin1()`: `vendingMachine.mode = Mode.OPERATING`, `vendingMachine.putCoin1()`, `vendingMachine.enterAdminMode(117345294655382L)`
### 9.3 Ожидаемое значение, полученное значение:
ожидаемое: `CANNOT_PERFORM`\
полученное: `UNSUITABLE_CHANGE`
### 9.4. Код после исправления:
Строка 114: `if(balance != 0) return Response.CANNOT_PERFORM;`