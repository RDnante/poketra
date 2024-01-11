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

alter  table  matiere add column prix decimal;

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
    id_poketra INTEGER NOT NULL,
    id_matiere INTEGER NOT NULL,
    id_taille INTEGER NOT NULL,
    quantite DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_matiere) REFERENCES matiere(id_matiere),
    FOREIGN KEY (id_taille) REFERENCES taille(id_taille),
    FOREIGN KEY (id_poketra) REFERENCES poketra(id_poketra)
);

-- 11/01/2024
create table entrer_stock_matiere (
    id_entrer_stock serial primary key,
    id_matiere Integer references matiere(id_matiere),
    quantite decimal,
    date_entrer date default current_date
);

create table sortie_stock_matiere (
    id_sortie_stock serial primary key,
    id_matiere INTEGER references matiere(id_matiere),
    quantite decimal,
    date_sortie date default current_date
);

insert into entrer_stock_matiere values (default,4,5,default);
insert into sortie_stock_matiere values (default,4,2,default);

create view production_prix as
select p.id_poketra,p.id_taille,p.id_matiere,m.prix,p.quantite,(m.prix * p.quantite) as prix_total from  production as p
    join matiere m on m.id_matiere = p.id_matiere;

create view production_prix_total as
    select id_poketra,id_taille,sum(prix_total) as total from production_prix group by id_poketra,id_taille;

select id_poketra,id_taille from production_prix_total where total > 300 and total < 1000;

create view reste_stock_matiere as
select esm.id_matiere,sum(esm.quantite) as sum_entrer,sum(ssm.quantite) as sum_sortie,(sum(esm.quantite) - sum(ssm.quantite)) as reste from entrer_stock_matiere as esm join
    sortie_stock_matiere ssm on esm.id_matiere = ssm.id_matiere
    group by esm.id_matiere;