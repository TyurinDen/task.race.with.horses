CREATE TABLE IF NOT EXISTS Horses (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR (20) NOT NULL,
  breed VARCHAR (20) NOT NULL,
  color VARCHAR (20) NOT NULL,
  weight FLOAT NOT NULL,
  age INTEGER NOT NULL,
  maxspeed INTEGER NOT NULL DEFAULT 15,
  stamina FLOAT NOT NULL DEFAULT 1 
);

--INSERT INTO Horses (name, breed, color, weight, age, maxspeed, stamina)
--  VALUES('', 'Орловский рысак', 'Черный', 501, 32, 15 ,1);
  
