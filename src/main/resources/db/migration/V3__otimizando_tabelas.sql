alter table if exists item_venda alter column preco_total set data type numeric(38,2);
alter table if exists item_venda alter column preco_unitario set data type numeric(38,2);
alter table if exists Produto alter column preco set data type numeric(38,2);
alter table if exists Venda alter column valor_total set data type numeric(38,2);