create table tb_delivery
(
    id                     uuid         not null,
    client_id              uuid         not null,
    fee                    decimal(10, 2),
    status                 varchar(20)  not null,
    order_date             timestamp without time zone not null,
    end_date               timestamp without time zone,
    recipient_name         varchar(150) not null,
    recipient_street       varchar(150) not null,
    recipient_number       varchar(30),
    recipient_neighborhood varchar(100),

    primary key (id),
    constraint fk_delivery_client foreign key (client_id) references tb_client (id)
);
