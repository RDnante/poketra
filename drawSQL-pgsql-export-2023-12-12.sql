CREATE TABLE type (
    id_type SERIAL PRIMARY KEY,
    Nom VARCHAR(255) NOT NULL
);
CREATE TABLE style (
    id_style SERIAL PRIMARY KEY,
    Nom VARCHAR(255) NOT NULL
);
CREATE TABLE matiere(
    id_matiere SERIAL PRIMARY KEY,
    Nom VARCHAR(255) NOT NULL
);
CREATE  TABLE taille(
    id_taille SERIAL PRIMARY KEY,
    Nom VARCHAR(255) NOT NULL,
    Unite INTEGER NOT NULL
);

CREATE TABLE poketra(
    id_poketra SERIAL PRIMARY KEY,
    Nom VARCHAR(255) NOT NULL,
    id_style INTEGER NOT NULL,
    id_type INTEGER NOT NULL,
    FOREIGN KEY (id_style) REFERENCES style(id_style),
    FOREIGN KEY (id_type) REFERENCES type(id_type)
);

CREATE TABLE production(
    id_production SERIAL PRIMARY KEY,
    id_matiere INTEGER NOT NULL,
    id_taille INTEGER NOT NULL,
    id_poketra INTEGER NOT NULL,
    quatite DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_matiere) REFERENCES matiere(id_matiere),
    FOREIGN KEY (id_taille) REFERENCES taille(id_taille),
    FOREIGN KEY (id_poketra) REFERENCES poketra(id_poketra)
);