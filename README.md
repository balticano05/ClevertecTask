# Check-app 
---
Приложение создает чек на основе передаваемых аргументов о товарах и дисконтной карте, путях. Считывает данные из файлов, и генерирует сам чек в отдельном файле по указанному пути.

## Зависимости

Java 21.

## Структура проекта

* src/main/java/ru/clevertec/check/CheckRunner.java - класс для запуска приложения и получения аргументов.

* src/main/resources/products.csv - файл с информацией о продуктах.

* src/main/resources/discountCards.csv - файл с информацией о дисконтных картах.

* result.csv - файл, в который сохраняется чек.

## Запуск приложения

Для запуска приложения:

**1)** Выкачайте репозиторий

```
git clone https://github.com/balticano05/Check.git
```

**2)** Перейдите в кореневую папку проекта через cmd:
``` cd ..\task\check ```

**3)** Создайте папку для скомпилированных классов (если еще не создана):
``` mkdir project ```

**4)** Скомпилируйте все .java файлы с относительными путями: ```javac -d project -sourcepath src\main\java\ru\clevertec\check\*.java```

**5)** После компиляции выполните команду: ```java -cp project ru.clevertec.check.CheckRunner id-quantity discountCard=xxxx
balanceDebitCard=xxxx pathToFile=XXXX saveToFile=xxxx ```

**Пример:** ```java -cp project ru.clevertec.check.CheckRunner 3-1 2-5 5-1 discountCard=1111
balanceDebitCard=100 pathToFile=./src/main/resources/products.csv saveToFile=./result.csv```

После выполнения команды в корне проекта будет создан файл result.csv, содержащий сгенерированный чек. Результат выполнения генерации также будет продублирован в консоль.

Пример файла после выполнения:

```
Date;Time
5.07.2024;22:51:10

QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL
1;Yogurt 400g;2,1$;0,1$;2,1$
5;Cream 400g;2,71$;1,35$;13,55$
1;Packed cabbage 1kg;1,19$;0,06$;1,19$

DISCOUNT CARD;DISCOUNT PERCENTAGE
4444;5%

TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT
16,84$;1,52$;15,32$
```
В противном случае результаты выполнения:

Если файл не найден, или указан pathToFile, но не указан saveToFile будет создан result.csv в корне проекта; если указан saveToFile, но не указан pathToFile будет создан результирующий файл по пути saveToFile результат которого будет:
```
ERROR
BAD REQUEST
```
Если недостаточно средств в файле по пути saveToFile: 
```
ERROR
NOT ENOUGH MONEY
```
В иных случаях: 
```
ERROR
INTERNAL ERROR
```

## Примечание

* Дисконтная карточка может отсутствовать.

* Баланс обязателен в запросе.

* Минимум один заказ id-quantity.

* Файл с продуктами не изменяется.

* Если оптового товара более 4 на эту позицию применяется скидка 10%, персональная скидка по карте на эту позицию не распространяется.

* Если не указаны пути, также будет сформирован файл result.csv с ответом BAD REQUEST.
