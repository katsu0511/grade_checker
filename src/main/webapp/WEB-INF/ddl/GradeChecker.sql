CREATE TABLE User (
	UserID          VARCHAR(100)           ,
	Password        VARCHAR(20)    NOT NULL,
	PRIMARY KEY     (UserID)
);

CREATE TABLE Term (
	TermID          INT            AUTO_INCREMENT ,
	Term            VARCHAR(20)    NOT NULL UNIQUE,
	PRIMARY KEY     (TermID)
);

CREATE TABLE Mark (
	MarkID          INT            AUTO_INCREMENT ,
	Mark            VARCHAR(2)     NOT NULL UNIQUE,
	PRIMARY KEY     (MarkID)
);

CREATE TABLE Gpa (
	GpaID           INT                  AUTO_INCREMENT ,
	Gpa             DECIMAL(3, 2)        NOT NULL UNIQUE,
	PRIMARY KEY     (GpaID)
);

CREATE TABLE Grade (
	UserID          VARCHAR(100)           NOT NULL,
	Term            INT                    NOT NULL,
	Code            VARCHAR(9)             NOT NULL,
	Course          VARCHAR(100)           NOT NULL,
	Grade           INT                    NOT NULL,
	PRIMARY KEY     (UserID, Term, Code)           ,
	FOREIGN KEY     (UserID) REFERENCES User(UserID) ON DELETE CASCADE   ON UPDATE CASCADE  ,
	FOREIGN KEY     (Term)   REFERENCES Term(TermID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY     (Grade)  REFERENCES Mark(MarkID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY     (Grade)  REFERENCES Gpa(GpaID)   ON DELETE NO ACTION ON UPDATE NO ACTION
);
