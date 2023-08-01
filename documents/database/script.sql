
-- SCRIPT CONSTRUIDO PARA BANCO DE DADOS ORACLE, PARA OUTROS BANCOS DE DADOS PODE SER DIFERENTE.


-- SCRIPT ESTRUTURAS

create sequence seq_page;

create table tb_page(
    id number default seq_page.nextval,
    url varchar2(255) not null,
    constraint tb_page_id_pk primary key(id),
    constraint tb_page_url_un unique(url)
);

create sequence seq_tag;

create table tb_tag(
    id number default seq_tag.nextval,
    name varchar2(255) not null,
    count number not null,
    id_page number not null,
    constraint tb_tag_id_pk primary key(id),
    constraint tb_tag_id_page_fk foreign key(id_page) references tb_page(id)
);

-- SCRIPT SEED

insert into tb_page (url)
values ('https://example.com/');

insert into tb_tag (name, count, id_page)
values ('html', 1, 1);

-- SCRIPT BUSCA

select pa.url, tg.name tag_name, tg.count tag_count
from tb_page pa
join tb_tag tg on (tg.id_page = pa.id)
where pa.url = 'https://example.com/';








