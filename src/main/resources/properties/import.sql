-- INHALTE LÖSCHEN
DELETE FROM [amagon].[order_item] WHERE [order_id] > 0;
DELETE FROM [amagon].[order] WHERE [order_id] > 0;
DELETE FROM [amagon].[customer] WHERE [customer_id] > 0;
DELETE FROM [amagon].[product] WHERE [product_id] > 0;

-- RESET SEED/AUTO INCREMENT
DBCC CHECKIDENT ('[amagon].[customer]', RESEED, 0)
DBCC CHECKIDENT ('[amagon].[product]', RESEED, 0)
DBCC CHECKIDENT ('[amagon].[order]', RESEED, 0)
DBCC CHECKIDENT ('[amagon].[order_item]', RESEED, 0)

-- TESTDATEN KUNDEN
INSERT INTO [amagon].[customer]
VALUES
    ('Combs','Quynn','Ap #809-893 Mauris. Rd.','Zaporizhzhia oblast'),
    ('Freeh','Mike','Frankfurter Allee 8', N'Grünwettersbach'),
    ('Odonnell','Hedda','108-2753 Nulla. Road','Free State'),
    ('Zweig','Maria', N'Konstanzer Strasse 48', N'Gedern'),
    ('Nieves','Sydney','Ap #866-2914 Odio. Ave','Indiana'),
    ('Glockner','David', N'Hardenbergstraße 56', N'Brücken'),
    ('Caldwell','Colt','P.O. Box 871, 4961 Luctus Road','Jeju'),
    ('Nussbaum','Florian', N'Kantstraße 60', 'Meiningen'),
    ('Harrison','Dahlia','Ap #723-7235 Nec, Road','North Chungcheong'),
    ('Kappel','Sabine', 'Scharnweberstrasse 84','Neuberg'),
    ('Drescher','Stefanie', 'Schaarsteinweg 53', N'Mötzing');

-- TESTDATEN PRODUKTE
INSERT INTO [amagon].[product]
VALUES ('MSI GeForce RTX 3080 Ti SUPRIM X 12GB', 'Grafikkarten', '1751.01', 7),
       ('12GB MSI GeForce RTX 3060 Ventus 2X', 'Grafikkarten', '429', 12),
       ('Samsung 980 PRO 1 TB PCIe 4.0', 'Solid State Drives (SSD)', '133.90', 9),
       ('750 Watt be quiet!', 'Netzteile (PSU)', '131.99', 7),
       ('SanDisk 128 GB Ultra microSD', 'Speicherkarten', '111.90', 14),
       ('16GB G.Skill RipJaws V schwarz DDR4-3200','Arbeitsspeicher (RAM)', '62.47', 23),
       ('NZXT Kraken X63','Kühlung Wasser(WaKü)', '133.70', 23),
       ('MSI Tomahawk WIFI Intel B660 So. 1700 Dual Channel DDR4','Mainboards', '206.99', 17);

-- TESTDATEN BESTELLUNGEN
INSERT INTO [amagon].[order]
VALUES (CAST(GETDATE() AS Date),4),
       (CAST(GETDATE() AS Date),8);

-- TESTDATEN POSITIONSTABELLE
INSERT INTO [amagon].[order_item]
VALUES (1,2,1,0),
       (1,1,1,0),
       (2,4,2,0),
       (2,8,3,0),
       (2,7,1,0),
       (2,3,1,0),
       (2,6,2,0);