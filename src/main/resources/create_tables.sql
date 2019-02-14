drop table if exists "user";
CREATE TABLE "user"
(
  "id"           serial  NOT NULL,
  "login"        varchar NOT NULL UNIQUE,
  "password"     varchar NOT NULL,
  "name"         varchar NOT NULL,
  "email"        varchar NOT NULL UNIQUE,
  "phone_number" varchar NOT NULL,
  "role_id"      int     NOT NULL,
  "city_id"      int     NOT NULL,
  "is_actual"    BOOLEAN NOT NULL,
  CONSTRAINT user_pk PRIMARY KEY ("id")
) WITH (
    OIDS= FALSE
  );


drop table if exists "role";
CREATE TABLE "role"
(
  "id"   serial  NOT NULL,
  "name" varchar NOT NULL UNIQUE,
  CONSTRAINT role_pk PRIMARY KEY ("id")
) WITH (
    OIDS= FALSE
  );


drop table if exists "ad";
CREATE TABLE "ad"
(
  "id"          serial  NOT NULL,
  "title"       varchar NOT NULL,
  "text"        varchar NOT NULL,
  "user_id"     int     NOT NULL,
  "category_id" int     NOT NULL,
  "price"       DECIMAL NOT NULL,
  "price_min"   DECIMAL NOT NULL,
  "date"        DATE    NOT NULL,
  "day_count"   int     NOT NULL,
  "confirm"     BOOLEAN NOT NULL DEFAULT false,
  "is_actual"   BOOLEAN NOT NULL DEFAULT true,
  CONSTRAINT ad_pk PRIMARY KEY ("id")
) WITH (
    OIDS= FALSE
  );


drop table if exists "category";
CREATE TABLE "category"
(
  "id"        serial  NOT NULL,
  "name"      varchar NOT NULL UNIQUE,
  "parent_id" int,
  CONSTRAINT category_pk PRIMARY KEY ("id")
) WITH (
    OIDS= FALSE
  );


drop table if exists "image";
CREATE TABLE "image"
(
  "id"      serial  NOT NULL,
  "img"     varchar NOT NULL,
  "is_main" BOOLEAN NOT NULL,
  "ad_id"   int     NOT NULL,
  CONSTRAINT image_pk PRIMARY KEY ("id")
) WITH (
    OIDS= FALSE
  );


drop table if exists "city";
CREATE TABLE "city"
(
  "id"   serial  NOT NULL,
  "name" varchar NOT NULL UNIQUE,
  CONSTRAINT city_pk PRIMARY KEY ("id")
) WITH (
    OIDS= FALSE
  );



ALTER TABLE "user"
  ADD CONSTRAINT "user_fk0" FOREIGN KEY ("role_id") REFERENCES "role" ("id");
ALTER TABLE "user"
  ADD CONSTRAINT "user_fk1" FOREIGN KEY ("city_id") REFERENCES "city" ("id");


ALTER TABLE "ad"
  ADD CONSTRAINT "ad_fk0" FOREIGN KEY ("user_id") REFERENCES "user" ("id");
ALTER TABLE "ad"
  ADD CONSTRAINT "ad_fk1" FOREIGN KEY ("category_id") REFERENCES "category" ("id");

ALTER TABLE "category"
  ADD CONSTRAINT "category_fk0" FOREIGN KEY ("parent_id") REFERENCES "category" ("id");

ALTER TABLE "image"
  ADD CONSTRAINT "image_fk0" FOREIGN KEY ("ad_id") REFERENCES "ad" ("id");


