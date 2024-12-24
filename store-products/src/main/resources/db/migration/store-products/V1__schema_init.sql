create schema if not exists store;

create table if not exists store.products
(
    id          serial           not null check (id > 0),
    title       varchar(50)      not null check (length(trim((title))) >= 3),
    description varchar(1000),
    price       double precision not null check (price >= 0.1),
    primary key (id)
);