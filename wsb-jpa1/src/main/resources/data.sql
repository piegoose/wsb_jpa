INSERT INTO address (id, city, address_line1, address_line2, postal_code)
VALUES
    (1, 'New York', '123 Main St', NULL, '10001'),
    (2, 'Los Angeles', '456 Sunset Blvd', 'Apt 5B', '90001'),
    (3, 'Chicago', '789 Lake Shore Dr', NULL, '60601'),
    (4, 'Miami', '101 Ocean Dr', NULL, '33101'),
    (5, 'Dallas', '202 Main St', NULL, '75201'),
    (6, 'Houston', '303 Bay Area Blvd', NULL, '77058'),
    (7, 'Phoenix', '404 Desert Rd', 'Suite 12', '85001'),
    (8, 'San Francisco', '505 Golden Gate Ave', NULL, '94102'),
    (9, 'Seattle', '606 Rainy St', 'Apt 3C', '98101'),
    (10, 'Boston', '707 Beacon St', NULL, '02108'),
    (11, 'Denver', '808 Rocky Rd', NULL, '80201'),
    (12, 'Las Vegas', '909 Casino Blvd', 'Penthouse', '89109'),
    (13, 'Atlanta', '100 Peachtree St', NULL, '30303');

insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, has_insurence)
values
    (1, 'John', 'Doe', '1234567890', 'john.doe@example.com', 'P001', '1990-01-01', 1, TRUE),
    (2, 'Jane', 'Smith', '9876543210', 'jane.smith@example.com', 'P002', '1985-05-15', 2, FALSE),
    (3, 'Robert', 'Brown', '4567891230', 'robert.brown@example.com', 'P003', '1975-03-25', 3, TRUE),
    (4, 'Emily', 'White', '7891234560', 'emily.white@example.com', 'P004', '1992-07-30', 4, TRUE),
    (5, 'Michael', 'Taylor', '6543219870', 'michael.taylor@example.com', 'P005', '1988-11-20', 5, FALSE),
    (6, 'Alice', 'Johnson', '1112223330', 'alice.johnson@example.com', 'P006', '1993-04-12', 6, TRUE),
    (7, 'Tom', 'Harris', '4445556660', 'tom.harris@example.com', 'P007', '1980-02-14', 7, FALSE),
    (8, 'Tomek', 'Slayer', '4445556660', 'tomek.slayer@example.com', 'P007', '1980-02-14', 7, FALSE);

INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES (1, 'John', 'Doctor', '1234567890', 'john.doctor@example.com', 'D001', 'SURGEON', 8),
       (2, 'Jane', 'Doctor', '9876543210', 'jane.doctor@example.com', 'D002', 'DERMATOLOGIST', 8),
       (3, 'James', 'Wilson', '4561237890', 'james.wilson@example.com', 'D003', 'CARDIOLOGIST', 9),
       (4, 'Sarah', 'Connor', '3216549870', 'sarah.connor@example.com', 'D004', 'ORTHOPEDIST', 10),
       (5, 'Chris', 'Evans', '7896541230', 'chris.evans@example.com', 'D005', 'NEUROLOGIST', 11),
       (6, 'Anna', 'Smith', '7778889990', 'anna.smith@example.com', 'D006', 'GP', 12),
       (7, 'Mark', 'Taylor', '9990001110', 'mark.taylor@example.com', 'D007', 'OCULIST', 13);


-- insert into address (id, address_line1, address_line2, city, postal_code, patient_id)
-- values (1, 'xx', 'yy', 'city', '62-030', 1);
-- INSERT INTO address (id, city, address_line1, address_line2, postal_code, doctor_id)
-- VALUES (2, 'New York', '123 Main St', NULL, '10001', 1);

INSERT INTO visit (id, description, time, doctor_id, patient_id)
VALUES
    (1, 'Cardiac consultation', '2024-12-05 10:00:00', 1, 1),
    (2, 'Follow-up visit', '2024-12-06 14:00:00', 3, 2),
    (3, 'Skin check-up', '2024-12-07 09:00:00', 2, 3),
    (4, 'Orthopedic consultation', '2024-12-08 11:00:00', 4, 4),
    (5, 'Neurological assessment', '2024-12-09 15:00:00', 5, 5),
    (6, 'General surgery follow-up', '2024-12-10 13:00:00', 1, 1),
    (7, 'Routine Heart Check-up', '2024-12-11 09:30:00', 3, 1),
    (8, 'Abdominal Ultrasound', '2024-12-12 11:00:00', 2, 4),
    (9, 'Chest X-Ray', '2024-12-13 14:30:00', 1, 2),
    (10, 'Cardiac Follow-Up', '2024-12-14 08:45:00', 3, 5),
    (11, 'Pelvic Ultrasound', '2024-12-15 10:15:00', 2, 3),
    (12, 'Pelvic Ultrasound', '2024-12-15 10:15:00', 2, 3),
    (13, 'Pelvic Ultrasound', '2024-12-15 10:15:00', 2, 3);

INSERT INTO medical_treatment (id, description, type, visit_id)
VALUES
    (1, 'ECG Analysis', 'DIAGNOSTIC', 1),
    (2, 'Blood Test', 'DIAGNOSTIC', 1),
    (3, 'Consultation', 'TREATMENT', 2),
    (4, 'X-Ray', 'DIAGNOSTIC', 3),
    (5, 'Physiotherapy', 'REHABILITATION', 4),
    (6, 'MRI Scan', 'DIAGNOSTIC', 5),
    (7, 'Surgery', 'TREATMENT', 6),
    (8, 'Wound Dressing', 'FOLLOWUP', 6),
    (9, 'Heart Rhythm Analysis', 'EKG', 7),
    (10, 'Detailed Ultrasound', 'USG', 8),
    (11, 'Lung X-Ray', 'RTG', 9),
    (12, 'Stress Test', 'EKG', 10),
    (13, 'Full Pelvic Scan', 'USG', 11);

