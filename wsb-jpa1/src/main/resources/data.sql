insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth)
values (1, 'John', 'Doe', '1234567890', 'john.doe@example.com', 'P001', '1990-01-01'),
       (2, 'Jane', 'Smith', '9876543210', 'jane.smith@example.com', 'P002', '1985-05-15');
INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization)
VALUES (1, 'John', 'Doe', '1234567890', 'john.doe@example.com', 'D001', 'CARDIOLOGY'),
       (2, 'Jane', 'Smith', '9876543210', 'jane.smith@example.com', 'D002', 'DERMATOLOGY');
insert into address (id, address_line1, address_line2, city, postal_code, patient_id)
values (1, 'xx', 'yy', 'city', '62-030', 1);
INSERT INTO address (id, city, address_line1, address_line2, postal_code, doctor_id)
VALUES (2, 'New York', '123 Main St', NULL, '10001', 1);
INSERT INTO visit (id, description, time, doctor_id, patient_id)
VALUES (1, 'Cardiac consultation', '2024-12-05 10:00:00', 1, 1),
       (2, 'Follow-up visit', '2024-12-06 14:00:00', 1, 2),
       (3, 'Skin check-up', '2024-12-07 09:00:00', 2, 1);

INSERT INTO medical_treatment (id, description, type, visit_id)
VALUES (1, 'ECG Analysis', 'DIAGNOSTIC', 1),
       (2, 'Blood Test', 'DIAGNOSTIC', 1),
       (3, 'Consultation', 'TREATMENT', 2);

