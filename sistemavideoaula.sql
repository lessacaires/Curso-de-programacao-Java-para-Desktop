CREATE TABLE estados
(
  id_estado serial NOT NULL,
  nome_estado character varying(50),
  sigla_estado character varying(2),
  CONSTRAINT pk_estado PRIMARY KEY (id_estado)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE estados OWNER TO postgres;

-- Table: cidades

-- DROP TABLE cidades;

CREATE TABLE cidades
(
  id_cidade serial NOT NULL,
  nome_cidade character varying(50),
  id_estado integer,
  CONSTRAINT pk_cidade PRIMARY KEY (id_cidade),
  CONSTRAINT fk_estado FOREIGN KEY (id_estado)
      REFERENCES estados (id_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cidades OWNER TO postgres;


-- Table: bairros

-- DROP TABLE bairros;

CREATE TABLE bairros
(
  id_bairro serial NOT NULL,
  nome_bairro character varying(50),
  id_cidade integer,
  CONSTRAINT pk_bairro PRIMARY KEY (id_bairro),
  CONSTRAINT fk_cidade FOREIGN KEY (id_cidade)
      REFERENCES cidades (id_cidade) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE bairros OWNER TO postgres;


-- Table: telefones

-- DROP TABLE telefones;

CREATE TABLE telefones
(
  id_telefone serial NOT NULL,
  numero_tel character varying(15),
  CONSTRAINT pk_telefone PRIMARY KEY (id_telefone)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE telefones OWNER TO postgres;


-- Table: clientes

-- DROP TABLE clientes;

CREATE TABLE clientes
(
  id_cliente serial NOT NULL,
  nome_cliente character varying(50),
  endereco_cliente character varying(50),
  rg_cliente character varying(12),
  cpf_cliente character varying(12),
  id_bairro integer,
  id_cidade integer,
  CONSTRAINT pk_cliente PRIMARY KEY (id_cliente),
  CONSTRAINT fk_bairro FOREIGN KEY (id_bairro)
      REFERENCES bairros (id_bairro) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_cidade FOREIGN KEY (id_cidade)
      REFERENCES cidades (id_cidade) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE clientes OWNER TO postgres;


-- Table: fornecedores

-- DROP TABLE fornecedores;

CREATE TABLE fornecedores
(
  id_fornecedor serial NOT NULL,
  nome_fornecedor character varying(50),
  endereco_fornecedor character varying(50),
  id_cidade integer,
  id_bairro integer,
  cnpj_fornecedor character varying(30),
  CONSTRAINT pk_fornecedor PRIMARY KEY (id_fornecedor),
  CONSTRAINT fk_bairro FOREIGN KEY (id_bairro)
      REFERENCES bairros (id_bairro) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_cidade FOREIGN KEY (id_cidade)
      REFERENCES cidades (id_cidade) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fornecedores OWNER TO postgres;


-- Table: produtos

-- DROP TABLE produtos;

CREATE TABLE produtos
(
  id_produto serial NOT NULL,
  nome_produto character varying(50),
  preco_compra numeric,
  preco_venda numeric,
  quantidade integer,
  id_fornecedor integer,
  CONSTRAINT pk_produto PRIMARY KEY (id_produto),
  CONSTRAINT fk_fornecedor FOREIGN KEY (id_fornecedor)
      REFERENCES fornecedores (id_fornecedor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE produtos OWNER TO postgres;


-- Table: vendas

-- DROP TABLE vendas;

CREATE TABLE vendas
(
  id_venda serial NOT NULL,
  data_venda character varying(10),
  valor_venda numeric,
  id_cliente integer,
  CONSTRAINT pk_venda PRIMARY KEY (id_venda),
  CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)
      REFERENCES cidades (id_cidade) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE vendas OWNER TO postgres;

-- Table: itens_tel_forn

-- DROP TABLE itens_tel_forn;

CREATE TABLE itens_tel_forn
(
  id_tel integer NOT NULL,
  id_fornecedor integer NOT NULL,
  CONSTRAINT pk_tel PRIMARY KEY (id_tel, id_fornecedor),
  CONSTRAINT fk_forn FOREIGN KEY (id_fornecedor)
      REFERENCES fornecedores (id_fornecedor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tel FOREIGN KEY (id_tel)
      REFERENCES telefones (id_telefone) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE itens_tel_forn OWNER TO postgres;


-- Table: itens_cli_tel

-- DROP TABLE itens_cli_tel;

CREATE TABLE itens_cli_tel
(
  id_cliente integer NOT NULL,
  id_tel integer NOT NULL,
  CONSTRAINT pk_tel_cli PRIMARY KEY (id_cliente, id_tel),
  CONSTRAINT fk_cli FOREIGN KEY (id_cliente)
      REFERENCES clientes (id_cliente) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tel FOREIGN KEY (id_tel)
      REFERENCES telefones (id_telefone) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE itens_cli_tel OWNER TO postgres;


-- Table: itens_venda_produto

-- DROP TABLE itens_venda_produto;

CREATE TABLE itens_venda_produto
(
  id_venda integer NOT NULL,
  id_produto integer NOT NULL,
  quantidade_produto integer,
  CONSTRAINT pf_produto_venda PRIMARY KEY (id_venda, id_produto),
  CONSTRAINT fk_produto FOREIGN KEY (id_produto)
      REFERENCES produtos (id_produto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_venda FOREIGN KEY (id_venda)
      REFERENCES vendas (id_venda) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE itens_venda_produto OWNER TO postgres;

