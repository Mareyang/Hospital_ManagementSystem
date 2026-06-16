-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2026 at 08:47 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appt_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `appointment_date` date NOT NULL,
  `appointment_time` time NOT NULL,
  `visit_type` varchar(50) DEFAULT NULL,
  `notes` text DEFAULT NULL,
  `status_id` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appt_id`, `patient_id`, `doctor_id`, `appointment_date`, `appointment_time`, `visit_type`, `notes`, `status_id`) VALUES
(1, 1, 2, '2026-06-15', '09:00:00', 'Routine Checkup', 'Patient complains of mild headaches.', 2),
(2, 2, 2, '2026-06-14', '10:30:00', 'Follow-up', 'Checking recovery post-surgery.', 2),
(3, 2, 1, '2026-06-20', '08:00:00', 'Follow-up', 'follow-up heart check-up', 3),
(4, 5, 2, '2026-06-25', '08:00:00', 'New Consultation', 'check up for 1 week fever', 2),
(5, 4, 2, '2026-08-16', '14:00:00', 'Routine Checkup', 'eye checkup', 2);

-- --------------------------------------------------------

--
-- Table structure for table `appointment_status`
--

CREATE TABLE `appointment_status` (
  `status_id` int(11) NOT NULL,
  `status_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointment_status`
--

INSERT INTO `appointment_status` (`status_id`, `status_name`) VALUES
(3, 'Cancelled'),
(2, 'Completed'),
(4, 'No Show'),
(1, 'Scheduled');

-- --------------------------------------------------------

--
-- Table structure for table `billing`
--

CREATE TABLE `billing` (
  `billing_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `appointment_id` int(11) DEFAULT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `discount_amount` decimal(10,2) DEFAULT 0.00,
  `net_amount` decimal(10,2) NOT NULL,
  `billing_date` datetime DEFAULT current_timestamp(),
  `status_id` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `billing`
--

INSERT INTO `billing` (`billing_id`, `patient_id`, `appointment_id`, `total_amount`, `discount_amount`, `net_amount`, `billing_date`, `status_id`) VALUES
(1, 2, 2, 150.00, 0.00, 150.00, '2026-06-14 19:23:02', 2),
(2, 1, NULL, 500.00, 0.00, 500.00, '2026-06-16 14:22:07', 1),
(3, 3, NULL, 100.00, 0.00, 100.00, '2026-06-16 14:25:26', 3);

-- --------------------------------------------------------

--
-- Table structure for table `billing_items`
--

CREATE TABLE `billing_items` (
  `item_id` int(11) NOT NULL,
  `billing_id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1,
  `unit_price` decimal(10,2) NOT NULL,
  `total_price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `billing_items`
--

INSERT INTO `billing_items` (`item_id`, `billing_id`, `description`, `quantity`, `unit_price`, `total_price`) VALUES
(1, 2, 'Consultation Fee (APT-001)', 1, 500.00, 500.00),
(2, 3, 'Pharmacy: Biogesic (Paracetamol)', 10, 10.00, 100.00);

-- --------------------------------------------------------

--
-- Table structure for table `billing_status`
--

CREATE TABLE `billing_status` (
  `status_id` int(11) NOT NULL,
  `status_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `billing_status`
--

INSERT INTO `billing_status` (`status_id`, `status_name`) VALUES
(3, 'Fully Paid'),
(2, 'Partially Paid'),
(1, 'Unpaid'),
(4, 'Voided / Refunded');

-- --------------------------------------------------------

--
-- Table structure for table `hospital_reports`
--

CREATE TABLE `hospital_reports` (
  `report_id` int(11) NOT NULL,
  `report_title` varchar(100) NOT NULL,
  `report_type_id` int(11) NOT NULL,
  `generated_by_id` int(11) NOT NULL,
  `generated_datetime` datetime DEFAULT current_timestamp(),
  `reporting_period` varchar(100) DEFAULT NULL,
  `report_body` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medical_records`
--

CREATE TABLE `medical_records` (
  `record_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `recorded_by_id` int(11) NOT NULL,
  `record_type_id` int(11) DEFAULT NULL,
  `height` decimal(5,2) DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL,
  `blood_pressure` varchar(20) DEFAULT NULL,
  `heart_rate` int(11) DEFAULT NULL,
  `temperature` decimal(4,2) DEFAULT NULL,
  `record_datetime` datetime DEFAULT current_timestamp(),
  `notes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `medical_records`
--

INSERT INTO `medical_records` (`record_id`, `patient_id`, `doctor_id`, `recorded_by_id`, `record_type_id`, `height`, `weight`, `blood_pressure`, `heart_rate`, `temperature`, `record_datetime`, `notes`) VALUES
(1, 1, 2, 3, 1, 198.00, 98.50, '120/80', 65, 36.50, '2026-06-14 19:20:15', NULL),
(2, 2, 2, 3, 2, 175.00, 70.00, '110/70', 72, 37.00, '2026-06-14 19:20:00', 'family history of diabetes');

-- --------------------------------------------------------

--
-- Table structure for table `medication_categories`
--

CREATE TABLE `medication_categories` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `medication_categories`
--

INSERT INTO `medication_categories` (`category_id`, `category_name`) VALUES
(1, 'Analgesic / Painkiller'),
(2, 'Antibiotic'),
(3, 'Antihistamine / Allergy'),
(4, 'Cardiovascular'),
(5, 'Supplement / Vitamin');

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `patient_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `birthday` date NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `contact_number` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`patient_id`, `first_name`, `last_name`, `birthday`, `gender`, `contact_number`, `email`, `address`, `status_id`) VALUES
(1, 'Michael', 'Jordan', '1985-04-12', 'Male', '09875698745', 'mjordan@email.com', 'Sta. Rosa, Laguna', 1),
(2, 'Serena', 'Williams', '1990-09-26', 'Female', '09326598741', 'swilliams@email.com', 'San Pedro, Laguna', 2),
(3, 'Tony', 'Stark', '1970-05-29', 'Male', '09658741235', 'tstark@email.com', 'Binan, Laguna', 3),
(4, 'Jose', 'Cruz', '2000-05-12', 'Male', '09568742315', 'josecruz@gmail.com', 'Carmona, Cavite', 3),
(5, 'Maria', 'Del Valle', '2004-03-12', 'Female', '09564781236', 'mariadelvalle@gmail.com', 'San Antonio, Binan Laguna', 2);

-- --------------------------------------------------------

--
-- Table structure for table `patient_status`
--

CREATE TABLE `patient_status` (
  `status_id` int(11) NOT NULL,
  `status_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient_status`
--

INSERT INTO `patient_status` (`status_id`, `status_name`) VALUES
(2, 'Admitted'),
(5, 'Deceased'),
(3, 'Discharged'),
(1, 'Outpatient'),
(4, 'Transferred');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL,
  `billing_id` int(11) NOT NULL,
  `payment_method` varchar(50) NOT NULL,
  `amount_paid` decimal(10,2) NOT NULL,
  `payment_date` datetime DEFAULT current_timestamp(),
  `processed_by_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`payment_id`, `billing_id`, `payment_method`, `amount_paid`, `payment_date`, `processed_by_id`) VALUES
(1, 1, 'Credit Card', 75.00, '2026-06-14 19:23:32', 1),
(2, 3, 'Cash', 100.00, '2026-06-16 14:25:58', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy`
--

CREATE TABLE `pharmacy` (
  `medication_id` int(11) NOT NULL,
  `item_code` varchar(50) NOT NULL,
  `brand_name` varchar(100) DEFAULT NULL,
  `generic_name` varchar(100) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `dosage_form` varchar(50) DEFAULT NULL,
  `strength` varchar(50) DEFAULT NULL,
  `current_stock` int(11) DEFAULT 0,
  `reorder_level` int(11) DEFAULT 20,
  `unit_price` decimal(10,2) NOT NULL,
  `expiration_date` date DEFAULT NULL,
  `status_id` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pharmacy`
--

INSERT INTO `pharmacy` (`medication_id`, `item_code`, `brand_name`, `generic_name`, `category_id`, `dosage_form`, `strength`, `current_stock`, `reorder_level`, `unit_price`, `expiration_date`, `status_id`) VALUES
(1, 'MED-001', 'Tylenol', 'Paracetamol', 1, 'Tablet', '500mg', 1000, 100, 0.25, NULL, 1),
(2, 'MED-002', 'Amoxil', 'Amoxicillin', 2, 'Capsule', '250mg', 9, 50, 1.50, NULL, 1),
(3, 'MED-003', 'Claritin', 'Loratadine', 3, 'Syrup', '10mg/5ml', 0, 20, 5.00, NULL, 2),
(4, 'MED-0004', 'Biogesic', 'Paracetamol', 1, 'Tablet', '10ml', 95, 20, 10.00, '2028-05-05', 1),
(5, 'MED-005', 'Neozep', 'Cetirizine', 3, 'Capsule', '20mg', 50, 10, 12.00, '2028-04-15', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy_status`
--

CREATE TABLE `pharmacy_status` (
  `status_id` int(11) NOT NULL,
  `status_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pharmacy_status`
--

INSERT INTO `pharmacy_status` (`status_id`, `status_name`) VALUES
(1, 'Active'),
(2, 'Discontinued'),
(3, 'Recalled');

-- --------------------------------------------------------

--
-- Table structure for table `prescriptions`
--

CREATE TABLE `prescriptions` (
  `prescription_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `diagnosis` varchar(150) DEFAULT NULL,
  `special_notes` text DEFAULT NULL,
  `prescription_date` datetime DEFAULT current_timestamp(),
  `status_id` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prescriptions`
--

INSERT INTO `prescriptions` (`prescription_id`, `patient_id`, `doctor_id`, `diagnosis`, `special_notes`, `prescription_date`, `status_id`) VALUES
(1, 1, 2, 'Tension Headaches', 'Drink plenty of water.', '2026-06-14 19:21:31', 3),
(2, 5, 2, 'fever', 'take it after eating.', '2026-06-17 10:00:00', 2),
(3, 3, 2, 'headache', 'drink plenty of water', '2026-06-21 11:00:00', 2);

-- --------------------------------------------------------

--
-- Table structure for table `prescription_details`
--

CREATE TABLE `prescription_details` (
  `prescription_id` int(11) NOT NULL,
  `medication_id` int(11) NOT NULL,
  `dosage` varchar(50) NOT NULL,
  `frequency` varchar(50) NOT NULL,
  `duration` varchar(50) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `refill_info` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prescription_details`
--

INSERT INTO `prescription_details` (`prescription_id`, `medication_id`, `dosage`, `frequency`, `duration`, `quantity`, `refill_info`) VALUES
(1, 1, '500mg', 'Every 6 hours as needed', '5 Days', 20, 0),
(2, 4, '5 mg', 'Twice daily (BID)', '3 days', 10, 5),
(3, 4, '10 mg', 'Three times daily (TID)', '5 Days', 10, 5);

-- --------------------------------------------------------

--
-- Table structure for table `prescription_status`
--

CREATE TABLE `prescription_status` (
  `status_id` int(11) NOT NULL,
  `status_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prescription_status`
--

INSERT INTO `prescription_status` (`status_id`, `status_name`) VALUES
(3, 'Cancelled'),
(2, 'Dispensed'),
(1, 'Pending Pharmacy');

-- --------------------------------------------------------

--
-- Table structure for table `record_types`
--

CREATE TABLE `record_types` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `record_types`
--

INSERT INTO `record_types` (`type_id`, `type_name`) VALUES
(3, 'Discharge Summary'),
(1, 'Intake Vitals'),
(4, 'Lab Result'),
(2, 'Progress Note');

-- --------------------------------------------------------

--
-- Table structure for table `report_types`
--

CREATE TABLE `report_types` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `report_types`
--

INSERT INTO `report_types` (`type_id`, `type_name`) VALUES
(5, 'Appointments Summary'),
(4, 'Clinical / Prescriptions'),
(2, 'Financial Revenue'),
(1, 'Inventory Report'),
(7, 'Medical Records Activity'),
(3, 'Patient Demographics'),
(6, 'Staff & HR Report');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(30) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `email` varchar(150) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `status_id` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `role`, `firstname`, `lastname`, `email`, `department`, `status_id`) VALUES
(1, 'ADM-001', 'admin123', 'Admin', 'System', 'Admin', 'admin@hospital.com', 'IT & Operations', 1),
(2, 'DOC-001', 'doctor123', 'Doctor', 'John', 'Smith', 'jsmith@hospital.com', 'Internal Medicine', 1),
(3, 'NRS-001', 'nurse123', 'Nurse', 'Mark', 'Bautista', 'mbautista@hospital.com', 'Pharmacy & Dispensary', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_status`
--

CREATE TABLE `user_status` (
  `status_id` int(11) NOT NULL,
  `status_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_status`
--

INSERT INTO `user_status` (`status_id`, `status_name`) VALUES
(1, 'Active'),
(3, 'Contract'),
(4, 'Inactive'),
(2, 'On Leave');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appt_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `doctor_id` (`doctor_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `appointment_status`
--
ALTER TABLE `appointment_status`
  ADD PRIMARY KEY (`status_id`),
  ADD UNIQUE KEY `status_name` (`status_name`);

--
-- Indexes for table `billing`
--
ALTER TABLE `billing`
  ADD PRIMARY KEY (`billing_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `appointment_id` (`appointment_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `billing_items`
--
ALTER TABLE `billing_items`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `billing_id` (`billing_id`);

--
-- Indexes for table `billing_status`
--
ALTER TABLE `billing_status`
  ADD PRIMARY KEY (`status_id`),
  ADD UNIQUE KEY `status_name` (`status_name`);

--
-- Indexes for table `hospital_reports`
--
ALTER TABLE `hospital_reports`
  ADD PRIMARY KEY (`report_id`),
  ADD KEY `generated_by_id` (`generated_by_id`),
  ADD KEY `report_type_id` (`report_type_id`);

--
-- Indexes for table `medical_records`
--
ALTER TABLE `medical_records`
  ADD PRIMARY KEY (`record_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `doctor_id` (`doctor_id`),
  ADD KEY `recorded_by_id` (`recorded_by_id`),
  ADD KEY `record_type_id` (`record_type_id`);

--
-- Indexes for table `medication_categories`
--
ALTER TABLE `medication_categories`
  ADD PRIMARY KEY (`category_id`),
  ADD UNIQUE KEY `category_name` (`category_name`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`patient_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `patient_status`
--
ALTER TABLE `patient_status`
  ADD PRIMARY KEY (`status_id`),
  ADD UNIQUE KEY `status_name` (`status_name`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `billing_id` (`billing_id`),
  ADD KEY `processed_by_id` (`processed_by_id`);

--
-- Indexes for table `pharmacy`
--
ALTER TABLE `pharmacy`
  ADD PRIMARY KEY (`medication_id`),
  ADD UNIQUE KEY `item_code` (`item_code`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `pharmacy_status`
--
ALTER TABLE `pharmacy_status`
  ADD PRIMARY KEY (`status_id`),
  ADD UNIQUE KEY `status_name` (`status_name`);

--
-- Indexes for table `prescriptions`
--
ALTER TABLE `prescriptions`
  ADD PRIMARY KEY (`prescription_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `doctor_id` (`doctor_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `prescription_details`
--
ALTER TABLE `prescription_details`
  ADD PRIMARY KEY (`prescription_id`,`medication_id`),
  ADD KEY `medication_id` (`medication_id`);

--
-- Indexes for table `prescription_status`
--
ALTER TABLE `prescription_status`
  ADD PRIMARY KEY (`status_id`),
  ADD UNIQUE KEY `status_name` (`status_name`);

--
-- Indexes for table `record_types`
--
ALTER TABLE `record_types`
  ADD PRIMARY KEY (`type_id`),
  ADD UNIQUE KEY `type_name` (`type_name`);

--
-- Indexes for table `report_types`
--
ALTER TABLE `report_types`
  ADD PRIMARY KEY (`type_id`),
  ADD UNIQUE KEY `type_name` (`type_name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `user_status`
--
ALTER TABLE `user_status`
  ADD PRIMARY KEY (`status_id`),
  ADD UNIQUE KEY `status_name` (`status_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `appointment_status`
--
ALTER TABLE `appointment_status`
  MODIFY `status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `billing`
--
ALTER TABLE `billing`
  MODIFY `billing_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `billing_items`
--
ALTER TABLE `billing_items`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `billing_status`
--
ALTER TABLE `billing_status`
  MODIFY `status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `hospital_reports`
--
ALTER TABLE `hospital_reports`
  MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `medical_records`
--
ALTER TABLE `medical_records`
  MODIFY `record_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `medication_categories`
--
ALTER TABLE `medication_categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `patients`
--
ALTER TABLE `patients`
  MODIFY `patient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `patient_status`
--
ALTER TABLE `patient_status`
  MODIFY `status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pharmacy`
--
ALTER TABLE `pharmacy`
  MODIFY `medication_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `pharmacy_status`
--
ALTER TABLE `pharmacy_status`
  MODIFY `status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `prescriptions`
--
ALTER TABLE `prescriptions`
  MODIFY `prescription_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `prescription_status`
--
ALTER TABLE `prescription_status`
  MODIFY `status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `record_types`
--
ALTER TABLE `record_types`
  MODIFY `type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `report_types`
--
ALTER TABLE `report_types`
  MODIFY `type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user_status`
--
ALTER TABLE `user_status`
  MODIFY `status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `appointment_status` (`status_id`);

--
-- Constraints for table `billing`
--
ALTER TABLE `billing`
  ADD CONSTRAINT `billing_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  ADD CONSTRAINT `billing_ibfk_2` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`appt_id`),
  ADD CONSTRAINT `billing_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `billing_status` (`status_id`);

--
-- Constraints for table `billing_items`
--
ALTER TABLE `billing_items`
  ADD CONSTRAINT `billing_items_ibfk_1` FOREIGN KEY (`billing_id`) REFERENCES `billing` (`billing_id`);

--
-- Constraints for table `hospital_reports`
--
ALTER TABLE `hospital_reports`
  ADD CONSTRAINT `hospital_reports_ibfk_1` FOREIGN KEY (`generated_by_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `hospital_reports_ibfk_2` FOREIGN KEY (`report_type_id`) REFERENCES `report_types` (`type_id`);

--
-- Constraints for table `medical_records`
--
ALTER TABLE `medical_records`
  ADD CONSTRAINT `medical_records_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  ADD CONSTRAINT `medical_records_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `medical_records_ibfk_3` FOREIGN KEY (`recorded_by_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `medical_records_ibfk_4` FOREIGN KEY (`record_type_id`) REFERENCES `record_types` (`type_id`);

--
-- Constraints for table `patients`
--
ALTER TABLE `patients`
  ADD CONSTRAINT `patients_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `patient_status` (`status_id`);

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`billing_id`) REFERENCES `billing` (`billing_id`),
  ADD CONSTRAINT `payments_ibfk_2` FOREIGN KEY (`processed_by_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `pharmacy`
--
ALTER TABLE `pharmacy`
  ADD CONSTRAINT `pharmacy_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `medication_categories` (`category_id`),
  ADD CONSTRAINT `pharmacy_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `pharmacy_status` (`status_id`);

--
-- Constraints for table `prescriptions`
--
ALTER TABLE `prescriptions`
  ADD CONSTRAINT `prescriptions_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  ADD CONSTRAINT `prescriptions_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `prescriptions_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `prescription_status` (`status_id`);

--
-- Constraints for table `prescription_details`
--
ALTER TABLE `prescription_details`
  ADD CONSTRAINT `prescription_details_ibfk_1` FOREIGN KEY (`prescription_id`) REFERENCES `prescriptions` (`prescription_id`),
  ADD CONSTRAINT `prescription_details_ibfk_2` FOREIGN KEY (`medication_id`) REFERENCES `pharmacy` (`medication_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `user_status` (`status_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
