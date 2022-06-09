DELETE FROM [amagon].[customer] WHERE [customer_id] > 0;

-- RESET SEED/AUTO INCREMENT
DBCC CHECKIDENT ('[amagon].[customer]', RESEED, 0)

INSERT INTO [amagon].[customer]
VALUES ('Stefanie', 'Drescher', 'Schaarsteinweg 53', N'Mötzing');

INSERT INTO [amagon].[customer]
VALUES ('Sabine', 'Kappel', 'Scharnweberstrasse 84','Neuberg');

INSERT INTO [amagon].[customer]
VALUES ('Florian', 'Nussbaum', N'Kantstraße 60', 'Meiningen');

INSERT INTO [amagon].[customer]
VALUES ('Mike', 'Freeh', 'Frankfurter Allee 8', N'Grünwettersbach');

INSERT INTO [amagon].[customer]
VALUES ('David', 'Glockner', N'Hardenbergstraße 56', N'Brücken');

INSERT INTO [amagon].[customer]
VALUES ('Maria', 'Zweig', N'Konstanzer Strasse 48', N'Gedern');