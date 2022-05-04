CREATE TABLE IF NOT EXISTS DeviceUser
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    about    VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS CatalogRecord
(
    id           SERIAL PRIMARY KEY,
    about        VARCHAR(2048),
    protoScheme  VARCHAR(2048) NOT NULL,
    createAt     TIMESTAMP     NOT NULL,
    deviceUserId BIGINT
);

CREATE TABLE IF NOT EXISTS StorageUser
(
    id                       SERIAL PRIMARY KEY,
    username                 VARCHAR(64) NOT NULL,
    password                 VARCHAR(64) NOT NULL,
    about                    VARCHAR(2048),
    ipAddress                VARCHAR(15) NOT NULL,
    port                     SMALLINT,
    availableMegabytesNumber BIGINT      NOT NULL
);

CREATE TABLE IF NOT EXISTS CatalogWithStorageRecord
(
    id              SERIAL PRIMARY KEY,
    isAvailable     BOOLEAN NOT NULL,
    catalogRecordId BIGINT  NOT NULL,
    storageUserId   BIGINT
);

alter table DeviceUser
    add constraint uniq_DeviceUser_username unique (username);

alter table StorageUser
    add constraint uniq_StorageUser_username unique (username);

alter table CatalogRecord
    add constraint fk_CatalogRecord_deviceUserId
        foreign key (deviceUserId) references DeviceUser;

alter table CatalogWithStorageRecord
    add constraint fk_CatalogWithStorageRecord_catalogRecordId
        foreign key (catalogRecordId) references CatalogRecord;

alter table CatalogWithStorageRecord
    add constraint fk_CatalogWithStorageRecord_storageUserId
        foreign key (storageUserId) references StorageUser;
