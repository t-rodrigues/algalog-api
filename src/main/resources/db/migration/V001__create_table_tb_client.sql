create table tb_client(
    id uuid not null,
    name varchar(150) not null,
    email varchar(255) not null unique,
    phone_number varchar(20) not null,

    primary key (id)
);
