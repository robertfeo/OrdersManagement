DELETE FROM [amagon].[customer] WHERE [customer_id] > 0;
DELETE FROM [amagon].[product] WHERE [product_id] > 0;

-- RESET SEED/AUTO INCREMENT
DBCC CHECKIDENT ('[amagon].[customer]', RESEED, 0)
DBCC CHECKIDENT ('[amagon].[product]', RESEED, 0)

INSERT INTO [amagon].[customer]
VALUES
    ('Quynn','Combs','Ap #809-893 Mauris. Rd.','Zaporizhzhia oblast'),
    ('Mike', 'Freeh', 'Frankfurter Allee 8', N'Grünwettersbach'),
    ('Hedda','Odonnell','108-2753 Nulla. Road','Free State'),
    ('Maria', 'Zweig', N'Konstanzer Strasse 48', N'Gedern'),
    ('Sydney','Nieves','Ap #866-2914 Odio. Ave','Indiana'),
    ('David', 'Glockner', N'Hardenbergstraße 56', N'Brücken'),
    ('Colt','Caldwell','P.O. Box 871, 4961 Luctus Road','Jeju'),
    ('Florian', 'Nussbaum', N'Kantstraße 60', 'Meiningen'),
    ('Dahlia','Harrison','Ap #723-7235 Nec, Road','North Chungcheong'),
    ('Sabine', 'Kappel', 'Scharnweberstrasse 84','Neuberg'),
    ('Stefanie', 'Drescher', 'Schaarsteinweg 53', N'Mötzing');

INSERT INTO [amagon].[product]
VALUES ('LED Strip 20m', 'Beleuchtung', '29.99', 4),
       ('MSI GeForce RTX 3080 Ti SUPRIM X 12GB', N'Computer & Zubehör', '1751.01', 2),
       ('Samsung 980 PRO 1 TB PCIe 4.0', N'Computer & Zubehör', '133.90', 4),
       ('Arcon 207X Kompatibel Tonerkartusche', 'Drucker & Patronen', '131.99', 7),
       ('SanDisk 128 GB Ultra microSD', N'Computer & Zubehör', '14.99', 12),
       ('Office 2021 Home und Student Multilingual', 'Software', '88.00', 21),
       ('Siemens Kaffeevollautomat EQ.500', N'Haushalt & Wohnen', '618.75', 6),
       ('Schreibtisch-Winkelkombination Tisch', N'Haushalt & Wohnen', '148.99', 3);