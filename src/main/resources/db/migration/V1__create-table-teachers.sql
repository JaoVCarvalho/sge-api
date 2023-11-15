create table teachers(

    id bigint not null auto_increment,
    name varchar(100) not null,
    education_level ENUM('GRADUADO','MESTRADO','DOUTORADO') not null,
    department ENUM('NATUREZA','HUMANAS','LINGUAGENS','MATEMATICA') not null,
    graduated_in ENUM('MATEMATICA','QUIMICA','FISICA','BIOLOGIA','HISTORIA','FILOSOFIA','GEOGRAFIA','SOCIOLOGIA','ARTES','LETRAS') not null,

    primary key(id)
);