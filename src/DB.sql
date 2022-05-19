DELETE FROM book WHERE ctid NOT IN(SELECT max(ctid) FROM book GROUP BY book);
select * from book;
select * from book where title = 'Мастер и Маргарита '