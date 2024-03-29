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

    select ((t.id_taille - 1) * (select valeur from max_taille)) as max from taille as t where t.id_taille = 2;

-- 23/01/2024
create table poste (
    id_poste serial primary key,
    nom varchar(100),
    salaire decimal
);


create table mpiasa (
    id_mpiasa serial primary key,
    id_poste int references poste(id_poste),
    annee_exp int,
    nom varchar(100),
    dte_naissance date default current_date
);

insert into mpiasa values (default,1,0,'Rakoto','2003-12-25');
insert into mpiasa values (default,1,0,'Bekoto','2002-12-12');
insert into mpiasa values (default,1,0,'Rajean','2000-01-12');

create table status_mpiasa (
    id_status_mpiasa serial primary key,
    nom varchar(50),
    min_diff int,
    max_diff int,
    valeur decimal
);

insert into status_mpiasa values (default,'ouvrier',0,1,1);
insert into status_mpiasa values (default,'senior',2,4,2);
insert into status_mpiasa values (default,'expert',5,50,3);

create table date_recrutement (
    id_date_recrutement serial primary key,
    id_mpiasa int references mpiasa(id_mpiasa),
    date_recrutement date default current_date
);

insert into date_recrutement values (default,1,'2018-12-25');
insert into date_recrutement values (default,2,'2019-12-25');
insert into date_recrutement values (default,3,'2020-12-25');

create table karama (
    id_karama serial primary key,
    valeur decimal
);

insert into karama values (default,2000);
 -- 25/01/2024
create table client (
    id_client serial primary key,
    nom varchar,
    genre int
);

insert into client values (default,'pascal',1);
insert into client values (default,'Cynthia',0);


create table vente (
    id_vente serial primary key,
    id_poketra int references  poketra(id_poketra),
    id_client int references client(id_client),
    date_vente date default current_date,
    nombre int
);

insert into vente values (default,3,1,default,2);
insert into vente values (default,3,2,default,1);
insert into vente values (default,1,2,default,1);

    create view vente_client as
        select vente.id_vente,vente.id_client,vente.id_poketra,vente.date_vente,vente.nombre,c.nom,c.genre from vente
            join client c on c.id_client = vente.id_client;

-- create view vente_par_genre

    create view vente_par_genre as
    select sum(nombre),genre,id_poketra from vente_client
        group by genre,id_poketra;

    create view vente_genre_total as
    select sum(nombre),genre from vente_client
        group by genre;

    select (select sum(nombre) from vente_client where genre = 0 and id_poketra = 3) as vente_feminin,
           (select sum(nombre) from vente_client where genre = 1 and id_poketra = 3) as vente_masculin;


create table etat_stock_poketra (
    id_etat_stock_poketra serial primary key,
    id_poketra int references poketra(id_poketra),
    entrer int,
    sortie int,
    date date default  current_date
);

insert into etat_stock_poketra values (default,1,0,1,default);

create view reste_poketra as
    select id_poketra,(sum(entrer) - sum(sortie)) as reste from etat_stock_poketra
        group by id_poketra;




