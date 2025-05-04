alter table if exists item_venda alter column preco_total set data type float(53);
alter table if exists item_venda alter column preco_unitario set data type float(53);
alter table if exists Produto alter column preco set data type float(53);
alter table if exists Venda alter column valor_total set data type float(53);