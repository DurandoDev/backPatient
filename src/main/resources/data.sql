INSERT INTO patient (firstname, name, date_of_birth, gender, address, phone_number)
SELECT * FROM (SELECT 'TestNone', 'Test', '1966-12-31', 'F', '1 Brookside St', '100-222-3333') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE phone_number = '100-222-3333');

INSERT INTO patient (firstname, name, date_of_birth, gender, address, phone_number)
SELECT * FROM (SELECT 'TestBorderline', 'Test', '1945-06-24', 'M', '2 High St', '200-333-4444') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE phone_number = '200-333-4444');

INSERT INTO patient (firstname, name, date_of_birth, gender, address, phone_number)
SELECT * FROM (SELECT 'TestInDanger', 'Test', '2004-06-18', 'M', '3 Club Road', '300-444-5555') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE phone_number = '300-444-5555');

INSERT INTO patient (firstname, name, date_of_birth, gender, address, phone_number)
SELECT * FROM (SELECT 'TestEarlyOnSet', 'Test', '2002-06-28', 'F', '4 Valley Dr', '400-555-6666') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM patient WHERE phone_number = '400-555-6666');
