CREATE TABLE users (
	id_users INT PRIMARY KEY,
	username VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(50) NOT NULL,
	lastname VARCHAR(50),
	firstname VARCHAR(50),
	email VARCHAR(50)
); 