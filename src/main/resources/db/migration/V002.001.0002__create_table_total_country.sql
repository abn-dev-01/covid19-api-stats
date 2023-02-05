CREATE TABLE "PUBLIC_SCHEMA"."entity_total_country"
(
    id              INT         NOT NULL PRIMARY KEY,
    country         VARCHAR(50) NOT NULL,
    country_code    CHAR(2) NOT NULL,
    province        VARCHAR(50) NOT NULL,
    city            VARCHAR(128) NOT NULL,
    city_code       VARCHAR(16) NOT NULL,
    lat             VARCHAR(32) NOT NULL,
    lon             VARCHAR(32) NOT NULL,
    confirmed       INT NOT NULL,
    deaths          INT NOT NULL,
    recovered       INT NOT NULL,
    active          INT NOT NULL,
    date_stat       DATE NOT NULL,
    comment         VARCHAR(128)
);
