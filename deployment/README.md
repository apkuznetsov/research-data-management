# Распределённая система хранения данных

### Программные требования
* Среда выполнения Java 11 (например, https://www.oracle.com/java/technologies/downloads/)
* PostgreSQL 14.2 (https://www.postgresql.org/download/)

### Порядок развёртывания
* Подсистема Каталог. Создать в PostgreSQL базу данных ddss_catalog, запустить ddss-catalog/1-up-with-java.bat
* Подсистема Хранилище. Создать в PostgreSQL базу данных ddss_storage, запустить ddss-storage/1-up-with-java.bat
* Подсистема Устройство. Запустить ddss-device/1-up-with-java.bat

## ЛИБО

### Программные требования (использовать Докер)
* Docker 4.8.1 (https://docs.docker.com/release-notes/)
* Образ postgres 14.2-alpine (https://hub.docker.com/_/postgres)

### Порядок развёртывания (использовать Докер)
* Подсистема Каталог. Запустить ddss-catalog/1-up-with-docker.bat
* Подсистема Хранилище. Запустить ddss-storage/1-up-with-with-docker.bat
* Подсистема Устройство. Запустить ddss-device/1-up-with-java.bat

