insert into Katedra values (1,'Matematika');
insert into Ispit values (1,6, 'Matematika 3', 'MAT3',1);
insert into Ispit values (2,6, 'Matematika 2', 'MAT2',1);
insert into Ispit values (3,6, 'Matematika 1', 'MAT1',1);
insert into Ispit values (4,5, 'Numericka analiza', 'NA',1);
insert into Ispit values (5,5, 'Diskretne matematicke strukture', 'DMS',1);
insert into student values (1, 2009, 'vitor', 'tomic');
insert into student values(2,2009,'filip', 'trifunovic');

insert into ispitnirok values (1,'2017-01-01', 'Januar 2017',1);
insert into ispitnirok values (2,'2017-01-02', 'Januar 2017',2);
insert into ispitnirok values (3,'2017-01-03', 'Januar 2017',3);
insert into ispitnirok values (4,'2017-01-04', 'Januar 2017',4);
insert into ispitnirok values (5,'2017-01-05', 'Januar 2017',5);


insert into prijavaispita values (0,0,8,1,null,1);
insert into prijavaispita values (1,0,7,2,null,1);
insert into prijavaispita values (2,0,5,3,null,2);
insert into prijavaispita values (3,0,7,4,null,2);
insert into prijavaispita values (4,0,null,5,null,2);
insert into prijavaispita values (5,0,6,3,null,1);
insert into student_prijavaispita values(1,0);
insert into student_prijavaispita values(1,1);
insert into student_prijavaispita values(2,2);
insert into student_prijavaispita values(2,3);
insert into student_prijavaispita values(2,4);
insert into student_prijavaispita values(1,5);