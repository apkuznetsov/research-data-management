CREATE TABLE IF NOT EXISTS device_user
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    about    VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS catalog_record
(
    id             SERIAL PRIMARY KEY,
    about          VARCHAR(2048),
    proto_scheme   VARCHAR(2048) NOT NULL,
    created_at     TIMESTAMP     NOT NULL,
    device_user_id INTEGER
);

CREATE TABLE IF NOT EXISTS storage_user
(
    id                         SERIAL PRIMARY KEY,
    username                   VARCHAR(64) NOT NULL,
    password                   VARCHAR(64) NOT NULL,
    about                      VARCHAR(2048),
    ip_address                 VARCHAR(15) NOT NULL,
    port                       SMALLINT,
    available_megabytes_number BIGINT      NOT NULL
);

CREATE TABLE IF NOT EXISTS catalog_with_storage_record
(
    id                SERIAL PRIMARY KEY,
    is_available      BOOLEAN NOT NULL,
    catalog_record_id INTEGER NOT NULL,
    storage_user_id   INTEGER
);

alter table device_user
    add constraint uniq_device_user_username unique (username);

alter table storage_user
    add constraint uniq_storage_user_username unique (username);

alter table catalog_record
    add constraint fk_catalog_record_device_user_id
        foreign key (device_user_id) references device_user;

alter table catalog_with_storage_record
    add constraint fk_catalog_with_storage_record_catalog_record_id
        foreign key (catalog_record_id) references catalog_record;

alter table catalog_with_storage_record
    add constraint fk_catalog_with_storage_record_storage_user_id
        foreign key (storage_user_id) references storage_user;
