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

create table ouvrier (
    id_ouvrier serial primary key,
    nom varchar,
    prix decimal
);

create table max_taille (
    valeur decimal
);

create table min_taille (
    valeur decimal
);

create table main_oeuvre_taille (
    id_taille int references taille(id_taille),
    id_ouvrier int references ouvrier(id_ouvrier)
);

create table max_style (
    valeur decimal
);

create table duree_min_style (
    duree decimal
);

create table prix_vente_poketra (
    id_poketra int references poketra(id_poketra),
    id_taille int references taille(id_taille),
    prix_vente decimal
);


create or replace view reste_stock_matiere as
   select esm.id_matiere,(select sum(quantite) from entrer_stock_matiere where entrer_stock_matiere.id_matiere = esm.id_matiere) as sum_entrer,
          (select sum(quantite) from sortie_stock_matiere where sortie_stock_matiere.id_matiere = esm.id_matiere) as sum_sortie,
          ((select sum(quantite) from entrer_stock_matiere where entrer_stock_matiere.id_matiere = esm.id_matiere) - (select sum(quantite) from sortie_stock_matiere where sortie_stock_matiere.id_matiere = esm.id_matiere)) as reste from entrer_stock_matiere as esm
                                                                                                                                                                                                                                                join sortie_stock_matiere ssm on esm.id_matiere = ssm.id_matiere
   group by esm.id_matiere;

insert into entrer_stock_matiere values (default,4,5,default);
insert into sortie_stock_matiere values (default,4,2,default);

create view production_prix as
select p.id_poketra,p.id_taille,p.id_matiere,m.prix,p.quantite,(m.prix * p.quantite) as prix_total from  production as p
    join matiere m on m.id_matiere = p.id_matiere;

-- prix total production par matiere
create view production_prix_total as
    select id_poketra,id_taille,sum(prix_total) as total from production_prix group by id_poketra,id_taille;

select id_poketra,id_taille from production_prix_total where total > 300 and total < 1000;

-- prix total production par main d'oeuvre
    select mot.id_taille,(sum(o.prix) * (mot.id_taille - 1)  * (select valeur from max_style) ) as fafa from main_oeuvre_taille as mot
        join ouvrier o on mot.id_ouvrier = o.id_ouvrier
        where mot.id_taille > 1
        group by mot.id_taille;

    create view total_prix as
   (select mot.id_taille,sum(o.prix) as fafa from main_oeuvre_taille as mot
                                                      join ouvrier o on mot.id_ouvrier = o.id_ouvrier
    where  mot.id_taille = 1
    group by mot.id_taille)
   union
   (select mot.id_taille,(sum(o.prix)) as fafa from main_oeuvre_taille as mot
                                                        join ouvrier o on mot.id_ouvrier = o.id_ouvrier
    where mot.id_taille > 1
    group by mot.id_taille);


    (select (1000 * (l.id_look - 1) * (select valeur from max_style)) as total from look as l);

insert into sortie_stock_matiere values (default,2,0,default);
insert into sortie_stock_matiere values (default,3,0,default);
insert into sortie_stock_matiere values (default,4,0,default);
insert into sortie_stock_matiere values (default,5,0,default);
insert into sortie_stock_matiere values (default,6,0,default);
insert into sortie_stock_matiere values (default,7,0,default);
insert into sortie_stock_matiere values (default,8,0,default);
insert into sortie_stock_matiere values (default,2,0,default);

insert into max_taille values (2);
insert into min_taille values (1);

insert into max_style values (2);
insert into duree_min_style values (1);

insert into main_oeuvre_taille values (1,1);
insert into main_oeuvre_taille values (2,1);
insert into main_oeuvre_taille values (2,2);
insert into main_oeuvre_taille values (3,1);

insert into prix_vente_poketra values (1,1,2000);


    create view comp_poketra as
    select p.id_poketra,pr.id_taille,p.id_style from poketra as p
        join production as pr on p.id_poketra = pr.id_poketra
        group by p.id_poketra, pr.id_taille, p.id_style;

    create view detail_prix_revient as
    (select cp.id_poketra,cp.id_taille,cp.id_style,tp.fafa,ppt.total, (select (tp.fafa * (select duree from duree_min_style)) as total from look as l where l.id_look = cp.id_style) as test from comp_poketra as cp
        join total_prix as tp on tp.id_taille = cp.id_taille
        join  production_prix_total as ppt on ppt.id_poketra = cp.id_poketra and ppt.id_taille = cp.id_taille
        where cp.id_style = 1)
    union
    (select cp.id_poketra,cp.id_taille,cp.id_style,tp.fafa,ppt.total, (select (tp.fafa * (cp.id_style - 1) * (select valeur from max_style) ) as total from look as l where l.id_look = cp.id_style) as test from comp_poketra as cp
        join total_prix as tp on tp.id_taille = cp.id_taille
        join  production_prix_total as ppt on ppt.id_poketra = cp.id_poketra and ppt.id_taille = cp.id_taille
     where cp.id_style > 1);

    create view detail_benefice as
    select  dp.id_poketra,dp.id_taille,dp.id_style,pvp.prix_vente,(dp.total + dp.test) as prix_revient, (pvp.prix_vente - (dp.total + dp.test)) as benefice  from detail_prix_revient as dp
    join prix_vente_poketra as pvp on pvp.id_poketra = dp.id_poketra and pvp.id_taille = dp.id_taille;






