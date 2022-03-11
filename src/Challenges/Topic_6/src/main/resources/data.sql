insert into Bank ( id_bank , name_bank ) values (1, 'Bancolombia');
insert into Bank ( id_bank , name_bank ) values (2, 'Davivienda');
insert into Bank ( id_bank , name_bank ) values (3, 'Banco Caja Social');
insert into Bank ( id_bank , name_bank ) values (4, 'BBVA');
insert into Bank ( id_bank , name_bank ) values (5, 'Globant Bank');

insert into BANK_ACCOUNT (id_account, type_account, owner_id_number, owner_first_name, owner_last_name, funds_account, bank) values ('13625524788', 'CORRIENTE', '6649552142', 'Geraldine', 'Crabb', 350000.55, 5);
insert into BANK_ACCOUNT (id_account, type_account, owner_id_number, owner_first_name, owner_last_name, funds_account, bank) values ('91671132822', 'AHORROS', '7816829520', 'Dore', 'Ogles', 7657065.71, 1);
insert into BANK_ACCOUNT (id_account, type_account, owner_id_number, owner_first_name, owner_last_name, funds_account, bank) values ('65916269200', 'CORRIENTE', '9201717215', 'Murdoch', 'Axelby', 1175553.89, 4);
insert into BANK_ACCOUNT (id_account, type_account, owner_id_number, owner_first_name, owner_last_name, funds_account, bank) values ('17385739284', 'CORRIENTE', '8888082920', 'Torre', 'Roberti', 1750000.99, 5);

insert into BILL (id_bill, id_company, name_company, value_bill, expiration_date, is_paid) values ('0012345', 'ETB', 'Empresa de Telecomunicaciones de Bogotá', 90000.00, '2022-04-25', 0);