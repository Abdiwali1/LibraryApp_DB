#Day Recordings Exercises

select count(*) from books;

select count(*) from users;

select * from book_borrow
where is_returned = 0;

select name from book_categories;

# name,author ,isbn,desc,year

select name,isbn,year,author,description from books
where name = 'Agile Testing';

select full_name from users
where email = 'librarian55@library';

select status from users where email='anisa.stokes@gmail.com';

-- Live Session Queries

-- How many users that is active?
select * from users
where status='ACTIVE';

select count(*) from users
where status='ACTIVE';

-- 1 - Admin
-- 2 - Librarian
-- 3 - Student

-- Active users
-- UI 120
-- DB 126(& admins included) --> (user_group_id=1)

select count(*) from users
where status='ACTIVE' and user_group_id <> 1;

-- How many users that is inactive?
select * from users
where status='INACTIVE';

select count(*) from users
where status='INACTIVE';

-- How many book we have in each category name ?

select bc.name, count(*)
from books b
            inner join book_categories bc on bc.id=b.book_category_id
where bc.name='Anthology' ;