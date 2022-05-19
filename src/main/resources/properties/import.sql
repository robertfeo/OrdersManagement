DELETE FROM [amagon].[customer] WHERE [customer_id] > 0;

-- RESET SEED/AUTO INCREMENT
DBCC CHECKIDENT ('[amagon].[customer]', RESEED, 0)

INSERT INTO [amagon].[customer]
VALUES (1, 'Stefanie', 'Drescher', 'Schaarsteinweg 53', N'Mötzing');

INSERT INTO [amagon].[customer]
VALUES (2, 'Sabine', 'Kappel', 'Scharnweberstrasse 84','Neuberg');

INSERT INTO [amagon].[customer]
VALUES (3, 'Florian', 'Nussbaum', N'Kantstraße 60', 'Meiningen');

INSERT INTO [amagon].[customer]
VALUES (4, 'Mike', 'Freeh', 'Frankfurter Allee 8', N'Grünwettersbach');

INSERT INTO [amagon].[customer]
VALUES (5, 'David', 'Glockner', N'Hardenbergstraße 56', N'Brücken');

INSERT INTO [amagon].[customer]
VALUES (6, 'Maria', 'Zweig', N'Konstanzer Strasse 48', N'Gedern');