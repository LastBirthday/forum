Упрощенный форум, в котором есть следующие возможности:
    -управление (CRUD) подфорумами
    -управление (CRUD) темами в любых подфорумах
    -возможность оставлять посты в темах
    -регистрация пользователей
    -иерархия доступа (админ, модератор и пользователь)
    -валидация данных при вводе.
    
Деплой инструкция под Ubuntu:
1. git clone https://github.com/LastBirthday/forum.git      // Копируем весь проект себе на машину.
2. https://www.jetbrains.com/idea/download/                 // Скачиваем IDE для запуска приложения.
3. sudo apt-get install oracle-java7-installer              // Скачиваем java SDK
4. sudo apt-get install tomcat7                             // Скачиваем web сервер Tomcat
5. sudo apt-get install mysql-server                        // Скачиваем базу данных MySQL
6. Запускаем IDEA. Открываем скачанный проект. Жмем File -> Import Settings и выбираем settings.jar, что лежит в корневой папке. Скорее всего придется указать свои пути к папке со скачанным Tomcat и указать где лежит java SDK.
7. Ждем пока Maven накачает вам все зависимости на машину. Запускаем, выбрав в запуске Tomcat 7.
