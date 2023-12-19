CREATE TABLE "look_matiere"(
    "id_look_matiere" serial NOT NULL,
    "id_matiere" INTEGER NOT NULL UNIQUE,
    "id_look" INTEGER NOT NULL UNIQUE
);
ALTER TABLE
    "look_matiere" ADD PRIMARY KEY("id_look_matiere");
CREATE TABLE "matiere"(
    "id_matiere" serial NOT NULL,
    "nom" VARCHAR(255) NOT NULL,
    "luxure" INTEGER NOT NULL
);
ALTER TABLE
    "matiere" ADD PRIMARY KEY("id_matiere");
CREATE TABLE "look"(
    "id_look" serial NOT NULL,
    "nom" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "look" ADD PRIMARY KEY("id_look");
ALTER TABLE
    "look_matiere" ADD CONSTRAINT "look_matiere_id_matiere_foreign" FOREIGN KEY("id_matiere") REFERENCES "matiere"("id_matiere");
ALTER TABLE
    "look_matiere" ADD CONSTRAINT "look_matiere_id_look_foreign" FOREIGN KEY("id_look") REFERENCES "look"("id_look");


-- conception

