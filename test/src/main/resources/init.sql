SELECT * FROM public.actors;
SELECT * FROM public.users_roles;
SELECT * FROM public.users;

SELECT *
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE table_name = 'roles';

INSERT INTO public.roles(id, name_)
VALUES (0, 'ROLE_ADMIN'), (1, 'ROLE_MANAGER'), (2, 'ROLE_CLIENT');

INSERT INTO public.users(id, username, password, email) /* password is passw0rd*/
VALUES (1, 'Garry5', 
	'$2a$10$LNvvbsVs.4M2gfjOd6JIqekWW1g4NeScMYLDzmsFYIKYG.eaBRq/m', 'lol@gmail.com');

INSERT INTO public.users_roles(role_id, user_id)
VALUES(0,  1);

INSERT INTO public.countries(code, name_)
VALUES ('0', 'Finland'),
	('1', 'USA'),
	('2', 'Russia'),
	('3', 'Italy'),
	('4', 'United Kingdom'),
	('5', 'India'),
	('6', 'China'),
	('7', 'Germany'),
	('8', 'France'),
	('9', 'Egypt'),
	('10', 'Brazil'),
	('11', 'Argentina'),
	('12', 'Australia'),
	('13', 'Canada');
	
INSERT into public.actors(id, name_, birth_date, sex, country_code)
VALUES (0, 'Leonardo DiCaprio', '1974-11-11', 'Male', '1'),
	(1, 'Adriano Celentano', '1938-01-06', 'Male', '3'),
	(2, 'Bruce Lee', '1940-11-27', 'Male', '6'),
	(3, 'Benedict Cumberbatch', '1976-07-19', 'Male', '4');
	
