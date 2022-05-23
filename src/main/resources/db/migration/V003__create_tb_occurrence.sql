create table tb_occurrence
(
    id              uuid not null,
    delivery_id     uuid not null,
    description     text,
    occurrence_date timestamp without time zone,

    primary key (id),
    constraint fk_occurrence_delivery foreign key (delivery_id) references tb_delivery (id)
);
