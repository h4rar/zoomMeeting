# Задание:  
Дано: Java 8, без фреймфорков  
Нужно: реализовать автоматическое создание видео-встречи в ZOOM при помощи zoom API.
На вход приходит дата и время (возможно еще что-то если нужно). На выходе получаем ссылку.

Для запуска потребуется:
1. ID клиента (Client ID)
2. Секрет клиента (Client Secret)
3. ID аккаунта (Account ID)

Для их получения необходимо создать Server-to-Server OAuth app. Для этого:  
Переходим в AppMarketplace https://marketplace.zoom.us/develop/create  
Регестрируемся -> Developer -> Build App  
![img.png](imgs/img.png)

Проматываем вниз и выбираем Server-to-Server OAuth  
![img_1.png](imgs/img_1.png)

Вводим название -> create  
![img_2.png](imgs/img_2.png)

После создания сгенерируются Account ID, Client ID и Client Secret. Копируем их в наш код, в поля ACCOUNT_ID, CLIENT_ID и CLIENT_SECRET соответственно  
![img_3.png](imgs/img_3.png)

На вкладке Information заполняем обязательные поля:
![img_5.png](imgs/img_5.png)

Переходим далее, необходимо заполнить Scopes:
Для работы с Митингами необходимы следующие scopes: meeting:write:admin
![img_4.png](imgs/img_4.png)

Активируем приложение на вкладке Activation
![img_6.png](imgs/img_6.png)

Запускаем приложение.

**Обратите внимание**: чтобы разрешить всем пользователям демонстрировать экран, необходимо в настройках аккаунта выдать соответствующие разрешение 
![img_8.png](imgs/img_8.png)
