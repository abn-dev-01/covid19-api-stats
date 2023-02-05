CREATE TABLE "PUBLIC_SCHEMA"."entity_country"
(
    id           INT         NOT NULL PRIMARY KEY,
    country_name VARCHAR(50) NOT NULL,
    slug         VARCHAR(50) NOT NULL,
    iso2         CHAR(2)     NOT NULL
);
