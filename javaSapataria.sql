drop database javasapataria;
-- a linha abaixa cria o banco de dados

create database javasapataria;

-- a linha abaixo escolhe o banco de dados
use javasapataria;

-- a linha abaixo cria tabela
create table tbusuarios(
codigoUsuario int primary key auto_increment,
nomeUsuario varchar(50) not null,
sobrenomeUsuario varchar(50) not null,
telefoneUsuario varchar(15),
loginUsuario varchar(15) not null unique,
senhaUsuario varchar(15) not null,
perfilUsuario varchar(15) not null,
comissaoUsuario decimal(5,2) default '0.00', 
statusUsuario varchar(15) not null
);


create table tbclientes(
codigoCliente int primary key auto_increment,
tipoCliente varchar(20) not null,
cpfCnpjCliente varchar(18),
nomeCliente varchar(50) not null,
sobrenomeCliente varchar(50) not null,
telefoneCliente varchar(15) not null,
foneDoisCliente varchar(15),
observacoesCliente varchar(150)
);

create table tbItem(
codigoItem int auto_increment primary key,
tipoItem varchar(25),
qtdPesItem varchar(15),
statusItem varchar(25),
detalhesItem varchar(250) not null,
valorItem decimal(10,2) not null,
codigoCliente int not null,
foreign key (codigoCliente) references tbclientes(codigocliente)
);


create table tbOs(
codigoOs int primary key auto_increment,
dataOs timestamp default current_timestamp,
previsaoEntregaOs date not null,
dataEntregaOs date,
totalItensOs int,
descontoOs decimal(10,2),
valorTotalOs decimal(10,2),
codigoCliente int not null,
codigoUsuario int not null,
foreign key(codigoCliente) references tbclientes(codigoCliente),
foreign key(codigoUsuario) references tbusuarios(codigoUsuario)
);


create table itemOs(
codigoItemOs int primary key auto_increment,
codigoItem int not null,
foreign key (codigoItem) references tbItem(codigoItem),
codigoOs int not null,
foreign key (codigoOs) references tbos(codigoOs)
);

create table tbprodutos(
codigoProduto int primary key auto_increment,
nomeProduto varchar(50) not null,
qtdProduto int not null
);

-- o script abaixo mostra a ordem de serviço, itens e cliente, inner join 3 tabelas
select
Ordens.codigoos as 'Ordem de serviço',dataOs as 'Data de entrada',valorTotalOs as 'Valor total da OS',
Clientes.nomecliente as 'Cliente', telefonecliente as 'Telefone',
Itens.tipoitem as 'Tipo de serviço',detalhesitem as 'Detalhes do serviço',valoritem as 'Valor do serviço'
from tbos as Ordens
inner join tbclientes as Clientes
on (Ordens.codigocliente = Clientes.codigocliente)
inner join tbitem as Itens
on(Itens.codigocliente = Clientes.codigocliente);

select * from tbusuarios where loginUsuario='admin' and senhaUsuario='admin';

-- o script abaixo mostra os e usuario que incluiu os
select
Orden.codigoos,dataOs,valorTotalOs,
Usuario.codigousuario,nomeUsuario
from tbos as Orden
inner join tbusuarios as Usuario
on (Orden.codigoUsuario = Usuario.codigoUsuario);


select * from tbusuarios;



select * from tbusuarios where nomeUsuario like 'j%';

select nomeCliente,codigoOs,dataOs,valorTotalOs
from tbos as os
join tbclientes as clientes
on clientes.codigoCliente = os.codigoCliente
where nomeCliente or codigoOs like 'w%';

describe tbusuarios;

select * from tbusuarios;

select 
Orden.codigoos,Usuario.codigousuario from tbos as Ordem 
inner join tbusuarios as Usuario on (Orden.codigoUsuario = Usuario.codigoUsuario) 
where codigoUsuario = 1;

select
codigoUsuario from tbOs where codigoUsuario = 1;

-- o script abaixo faz um update
update tbUsuarios set nomeUsuario=will where codigoUsuario=1;

-- o script abaixo muda o status do usuário
update tbUsuarios set statusUsuario = 'USUÁRIO INATIVO' where codigoUsuario = ?;

-- o script abaixo muda o nome das tabelas no select
select codigoUsuario as ID, nomeUsuario as Nome, sobrenomeUsuario as Sobrenome,foneUsuario as Telefone,
loginUsuario as Usuário,perfilUsuario as Perfil,comissaoUsuario as Comissão from tbUsuarios;
