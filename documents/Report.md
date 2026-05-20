# Отчёт о проведённом тестировании

## Краткое описание

Автоматизировно тестирование комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка.

Общее количество тест кейсов: **54**

## Статистика успешных/неуспешных кейсов

### При подключении к БД MySQL
<img width="679" height="189" alt="image" src="https://github.com/user-attachments/assets/9825d188-8de4-4428-83e1-fa4518251ce7" />

- Успешных кейсов 48% (26 кейсов)
- Неуспешных кейсов 52% (28 кейсов)


### При подключении к БД PostgreSQL
<img width="673" height="193" alt="image" src="https://github.com/user-attachments/assets/5f6a26b9-8aa2-4431-9fe6-be84912de4de" />


- Успешных кейсов 44% (24 кейса)
- Неуспешных кейсов 56% (30 кейсов)



## UI-тесты при подключении к БД MySQL
Позитивные (HappyPath) тесты - **4**

Негативные - **46**

<img width="677" height="184" alt="image" src="https://github.com/user-attachments/assets/a9bb4c13-5dea-4b3c-b61b-82a1338d6c39" />


## UI-тесты при подключении к БД PostgreSQL
Позитивные (HappyPath) тесты - **4**

Негативные - **46**

<img width="672" height="188" alt="image" src="https://github.com/user-attachments/assets/34127d26-2b42-40a5-a821-aa4820b06a99" />



## API-тесты

При подключении к обеим БД все тест-кейсы прошли успешно (4 шт.)
<img width="749" height="161" alt="image" src="https://github.com/user-attachments/assets/ef5cdb62-6d71-43b0-be6c-fa2e8090c645" />



## Общие рекомендации

1. Исправить визуальные и орфографические ошибки ([ошибка в названии вкладки](https://github.com/daryamorozova/QA-Diploma/issues/28) и [ошибка в слове Марракеш](https://github.com/daryamorozova/QA-Diploma/issues/1))
Также сделать одинаковыми кнопки "Купить" и "Купить в кредит", чтобы при нажатии они меняли цвет (красный - активная вкладка, белый неактивная)
2. Очень много ошибок (20 из 28 неуспешных кейсов) связаны с неверными сообщениями об ошибках, необходимо это скорректировать
3. Решить проблему с подключением БД PostgreSQL, так как аналогичные тесты проходят на MySQL, а на PostgreSQL не проходят (описано в [issue#29](https://github.com/daryamorozova/QA-Diploma/issues/29) и [issue30](https://github.com/daryamorozova/QA-Diploma/issues/30))
4. Для поля "Владелец" ввести ограничение на вводимые символы - только английские буквы, нечувствительные к регистру
5. Сделать кнопку "Продолжить" неактивной, если есть пустые и/или неправильно заполненные поля

Полный список найденных дефектов находится в [Issues](https://github.com/daryamorozova/QA-Diploma/issues)
