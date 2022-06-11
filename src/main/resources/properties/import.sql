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
VALUES ('MSI GeForce RTX 3080 Ti SUPRIM X 12GB', 'Grafikkarten', '1751.01', 7),
       ('12GB MSI GeForce RTX 3060 Ventus 2X', 'Grafikkarten', '429', 12),
       ('Samsung 980 PRO 1 TB PCIe 4.0', 'Solid State Drives (SSD)', '133.90', 9),
       ('750 Watt be quiet!', 'Netzteile (PSU)', '131.99', 7),
       ('SanDisk 128 GB Ultra microSD', 'Speicherkarten', '111.90', 14),
       ('16GB G.Skill RipJaws V schwarz DDR4-3200','Arbeitsspeicher (RAM)', '62.47', 23),
       ('NZXT Kraken X63','Kühlung Wasser(WaKü)', '133.70', 23),
       ('MSI Tomahawk WIFI Intel B660 So. 1700 Dual Channel DDR4','Mainboards', '206.99', 17);