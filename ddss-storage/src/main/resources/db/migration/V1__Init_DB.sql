CREATE TABLE IF NOT EXISTS device_user
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    about    VARCHAR(256)
);

alter table device_user
    add constraint uniq_device_user_username unique (username);
