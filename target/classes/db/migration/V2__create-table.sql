create table provincias (
    id   bigint primary key not null,
    designation varchar(50) not null
);

create table distritos (
                           id bigint primary key not null,
                           designation varchar(60) not null,
                           province_id bigint             not null,
                           foreign key (province_id) references provincias (id)
);