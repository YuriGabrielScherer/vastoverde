create table pessoa (
	id int not null auto_increment primary key,
	nome varchar(50) not null ,
	senha varchar(30) not null,
	email varchar(40) not null,
	dataNascimento varchar(10) not null, 
	telefone varchar(11) not null,
	cpf varchar(11) not null,
	tipoUsuario int not null
)