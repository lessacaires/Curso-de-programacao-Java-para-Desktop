-- Table: public.estados

-- DROP TABLE public.estados;

CREATE TABLE IF NOT EXISTS public.estados
(
    id_estado integer,
    nome_estado character varying(50) COLLATE pg_catalog."default",
    siga_estado character varying(2) COLLATE pg_catalog."default",
    CONSTRAINT pk_estado PRIMARY KEY (id_estado)
)

TABLESPACE pg_default;

ALTER TABLE public.estados
    OWNER to postgres;

-------------------------------//--tabela--cidades---//-----------------------------------

-- Table: public.cidades

-- DROP TABLE public.cidades;

CREATE TABLE IF NOT EXISTS public.cidades
(
    id_cidade integer,
    nome_cidade character varying(50) COLLATE pg_catalog."default",
    id_estado integer,
    CONSTRAINT pk_cidade PRIMARY KEY (id_cidade),
    CONSTRAINT fk_estado FOREIGN KEY (id_cidade)
        REFERENCES public.estados (id_estado) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.cidades
    OWNER to postgres;

--------------------------------------------------------------------------------------------------

-- Table: public.bairros

-- DROP TABLE public.bairros;

CREATE TABLE IF NOT EXISTS public.bairros
(
    id_bairro integer,
    nome_bairro character varying(50) COLLATE pg_catalog."default",
    id_cidade integer,
    CONSTRAINT pk_bairro PRIMARY KEY (id_bairro),
    CONSTRAINT fk_cidade FOREIGN KEY (id_bairro)
        REFERENCES public.cidades (id_cidade) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.bairros
    OWNER to postgres;

-------------------------------------------------------------------------

-- Table: public.clientes

-- DROP TABLE public.clientes;

CREATE TABLE IF NOT EXISTS public.clientes
(
    id_cliente integer,
    nome_cliente character varying(50) COLLATE pg_catalog."default",
    endereco_cliente character varying(50) COLLATE pg_catalog."default",
    rg_cliente character varying(12) COLLATE pg_catalog."default",
    cpf_cliente character varying(12) COLLATE pg_catalog."default",
    id_bairro integer,
    id_cidade integer,
    CONSTRAINT pk_cliente PRIMARY KEY (id_cliente),
    CONSTRAINT fk_bairro FOREIGN KEY (id_cliente)
        REFERENCES public.bairros (id_bairro) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_cidade FOREIGN KEY (id_cliente)
        REFERENCES public.cidades (id_cidade) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.clientes
    OWNER to postgres;


-- Table: public.fornecedores

-- DROP TABLE public.fornecedores;

CREATE TABLE IF NOT EXISTS public.fornecedores
(
    id_fornecedor integer ,
    nome_fornecedor character varying(50) COLLATE pg_catalog."default",
    enderco_fornecedor character varying(50) COLLATE pg_catalog."default",
    id_cidade integer,
    id_bairro integer,
    cnpj_fornecedor character varying(30) COLLATE pg_catalog."default",
    CONSTRAINT pk_fornecedor PRIMARY KEY (id_fornecedor),
    CONSTRAINT fk_bairro FOREIGN KEY (id_fornecedor)
        REFERENCES public.bairros (id_bairro) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_cidade FOREIGN KEY (id_fornecedor)
        REFERENCES public.cidades (id_cidade) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.fornecedores
    OWNER to postgres;


-- Table: public.itens_tel_forn

-- DROP TABLE public.itens_tel_forn;

CREATE TABLE IF NOT EXISTS public.itens_tel_forn
(
    id_tel integer
)

TABLESPACE pg_default;

ALTER TABLE public.itens_tel_forn
    OWNER to postgres;

-- Table: public.produtos

-- DROP TABLE public.produtos;

CREATE TABLE IF NOT EXISTS public.produtos
(
    id_produto integer,
    nome_produto character varying(30) COLLATE pg_catalog."default",
    preco_compra numeric,
    preco_venda numeric,
    quantidade integer,
    id_fornecedor integer,
    CONSTRAINT pf_produto PRIMARY KEY (id_produto),
    CONSTRAINT fk_fornecedor FOREIGN KEY (id_produto)
        REFERENCES public.fornecedores (id_fornecedor) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.produtos
    OWNER to postgres;


-- Table: public.telefone

-- DROP TABLE public.telefone;

CREATE TABLE IF NOT EXISTS public.telefone
(
    id_tel integer,
    numero_tel character varying(15) COLLATE pg_catalog."default",
    CONSTRAINT telefone_pkey PRIMARY KEY (id_tel)
)

TABLESPACE pg_default;

ALTER TABLE public.telefone
    OWNER to postgres;


-- Table: public.vendas

-- DROP TABLE public.vendas;

CREATE TABLE IF NOT EXISTS public.vendas
(
    id_venda integer,
    data_venda character varying(10) COLLATE pg_catalog."default",
    valor_venda numeric,
    id_cliente integer,
    CONSTRAINT pk_venda PRIMARY KEY (id_venda),
    CONSTRAINT "fk-cliente" FOREIGN KEY (id_venda)
        REFERENCES public.clientes (id_cliente) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.vendas
    OWNER to postgres;