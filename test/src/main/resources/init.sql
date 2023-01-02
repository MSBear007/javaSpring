SELECT * FROM public.actors;
SELECT * FROM public.users_roles;
SELECT * FROM public.users;

SELECT *
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE table_name = 'roles';

INSERT INTO public.roles(name_)
VALUES ('ROLE_ADMIN'), ('ROLE_MANAGER'), ('ROLE_CLIENT');

INSERT INTO public.users(username, password, email) /* password is passw0rd*/
VALUES ('Garry5', '$2a$10$LNvvbsVs.4M2gfjOd6JIqekWW1g4NeScMYLDzmsFYIKYG.eaBRq/m', 'lol@gmail.com'),
	('Garry4','$2a$10$LNvvbsVs.4M2gfjOd6JIqekWW1g4NeScMYLDzmsFYIKYG.eaBRq/m', 'garry4@gmail.com'),
	('Garry3','$2a$10$LNvvbsVs.4M2gfjOd6JIqekWW1g4NeScMYLDzmsFYIKYG.eaBRq/m', 'garry3@gmail.com');

INSERT INTO public.users_roles(role_id, user_id)
VALUES(1,  1), (2, 1), (3, 1);

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
	
INSERT into public.actors(name_, birth_date, sex, country_code, thumbnail_path)
VALUES ('Leonardo DiCaprio', '1974-11-11', 'Male', '1', 'leo_dicaprio128x128.jpg'),
	('Adriano Celentano', '1938-01-06', 'Male', '3', 'adriano_celentano128x128.jpg'),
	('Bruce Lee', '1940-11-27', 'Male', '6', 'bruce_lee128x128.jpg'),
	('Benedict Cumberbatch', '1976-07-19', 'Male', '4', 'butterbread_cumbutbush128x128.jpg');

CREATE OR REPLACE FUNCTION update_movie_rating() 
RETURNS TRIGGER AS 
$$
DECLARE new_count BIGINT;
DECLARE average_rating DOUBLE PRECISION;
BEGIN
	UPDATE public.movies
		SET ratings_count = ratings_count + 1
		WHERE id = NEW.movie_id
		RETURNING ratings_count INTO new_count;
	
	IF MOD(new_count, 30) = 0 THEN
		SELECT avg(stars) into average_rating FROM public.movies_ratings
			WHERE movie_id = NEW.movie_id;
		UPDATE public.movies
			SET rating = average_rating
			WHERE id = NEW.movie_id;
	END IF;
	RETURN NULL;
END
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER update_rating_trigger AFTER INSERT ON public.movies_ratings
FOR EACH ROW EXECUTE PROCEDURE update_movie_rating();

INSERT INTO public.movies(title, year, poster_path, rating)
VALUES ('Godfather', 1972, 'godfather.jpg', 8.8),
	('Once Upon A Time In Hollywood', 2019, 'once-upon-a-time-in-hollywood.jpeg', 7.9),
	('Il bisbetico domato', 1980, 'il-bisbetico-domato.webp', 7.6);

INSERT INTO public.movies_ratings(movie_id, user_id, stars)
VALUES (1, 1, 5), (1, 2, 10), (1, 3, 9),
	(2, 1, 4), (2, 2, 10), (2, 3, 8), 
	(3, 1, 10), (3, 2, 7), (3, 3, 5);