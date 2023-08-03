create table if not exists  transaction_type (
	id bigserial primary key NOT NULL,
	description varchar(255) NOT NULL,
	nature varchar(255) NOT NULL,
	signal varchar(255) NOT NULL
);

INSERT INTO transaction_type(id, description, nature, signal)
VALUES(1, 'Débito', 'Entrada', '+');
INSERT INTO transaction_type(id, description, nature, signal)
VALUES(2, 'Boleto', 'Saída', '-');
INSERT INTO transaction_type(id, description, nature, signal)
VALUES(3, 'Financiamento', 'Saída', '-');
INSERT INTO transaction_type(id, description, nature, signal)
VALUES(4, 'Crédito', 'Entrada', '+');
INSERT INTO transaction_type(id, description, nature, signal)
VALUES(5, 'Recebimento Empréstimo', 'Entrada', '+');
INSERT INTO transaction_type(id, description, nature, signal)
VALUES(6, 'Vendas', 'Entrada', '+');
INSERT INTO transaction_type(id, description, nature, signal)
VALUES(7, 'Recebimento TED', 'Entrada', '+');
INSERT INTO transaction_type(id, description, nature, signal)
VALUES(8, 'Recebimento DOC', 'Entrada', '+');
INSERT INTO transaction_type(id, description, nature, signal)
VALUES(9, 'Aluguel', 'Saída', '-');

create table if not exists transactions (
	id uuid primary key default uuid_generate_v4(),
	type_id bigserial references transaction_type(id) NOT NULL,
	"date" date NULL,
	value float8 NOT NULL,
	cpf varchar(255) NULL,
	card_number varchar(255) NULL,
	"hour" time NULL,
	store_owner varchar(255) NULL,
	store_name varchar(255) NULL
);