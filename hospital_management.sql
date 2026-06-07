-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 05, 2026 at 02:49 PM
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
  `appointment_date` varchar(20) NOT NULL,
  `appointment_time` varchar(20) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `patient_name` varchar(100) NOT NULL,
  `department` varchar(50) NOT NULL,
  `doctor` varchar(100) NOT NULL,
  `visit_type` varchar(50) NOT NULL,
  `room_number` varchar(20) NOT NULL,
  `notes` text NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appt_id`, `appointment_date`, `appointment_time`, `patient_id`, `patient_name`, `department`, `doctor`, `visit_type`, `room_number`, `notes`, `status`) VALUES
(1, '06-20-2026', '10:00 AM', 1, 'Mark Zamora', 'Laboratory', 'Dr. Ricardo Reyes', 'Diagnostic/Lab Test', 'RM-201', 'xray and ct-scan', 'Pending'),
(2, '06-15-2026', '9:00 AM', 2, 'Bryan Toledo', 'Emergency(ER)', 'Dr. Juan dela Cruz', 'Emergency Visit', 'ER-01', 'heart surgery', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `hospital_reports`
--

CREATE TABLE `hospital_reports` (
  `report_id` varchar(50) NOT NULL,
  `report_name` varchar(100) NOT NULL,
  `report_type` varchar(50) NOT NULL,
  `generated_by` varchar(100) DEFAULT NULL,
  `date_generated` varchar(50) DEFAULT NULL,
  `reporting_period` varchar(100) DEFAULT NULL,
  `executive_summary` text DEFAULT NULL,
  `status` varchar(50) DEFAULT 'Generated'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hospital_reports`
--

INSERT INTO `hospital_reports` (`report_id`, `report_name`, `report_type`, `generated_by`, `date_generated`, `reporting_period`, `executive_summary`, `status`) VALUES
('RPT-101', 'Flu Case', 'Admissions Summary', 'Dr. Elena Cruz', '05-07-2026', '05-20-2026', 'There\'s a lot of flu cases within 2 weeks.', 'Generated');

-- --------------------------------------------------------

--
-- Table structure for table `hospital_staff`
--

CREATE TABLE `hospital_staff` (
  `employee_id` varchar(50) NOT NULL,
  `full_name` varchar(150) NOT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  `gender` varchar(30) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `contact_number` varchar(50) DEFAULT NULL,
  `marital_status` varchar(50) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `role` varchar(100) NOT NULL,
  `hire_date` varchar(50) DEFAULT NULL,
  `day_off` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `client_name` varchar(150) DEFAULT NULL,
  `eval_staff_name` varchar(150) DEFAULT NULL,
  `eval_role` varchar(100) DEFAULT NULL,
  `performance_rate` varchar(50) DEFAULT NULL,
  `comments` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hospital_staff`
--

INSERT INTO `hospital_staff` (`employee_id`, `full_name`, `birthday`, `gender`, `email`, `contact_number`, `marital_status`, `department`, `role`, `hire_date`, `day_off`, `status`, `client_name`, `eval_staff_name`, `eval_role`, `performance_rate`, `comments`) VALUES
('EMP-001', 'Jane Doe', '08-24-1992', 'Female', 'jane.doe@hospital.com', '0912-345-6789', 'Single', 'Emergency', 'Nurse', '11-15-2023', 'Sunday', 'Active', 'John Smith', 'Dr. Gregory House', 'Head of Diagnostic Medicine', '5 - Excellent', 'Jane handled the ER rush exceptionally well this week.');

-- --------------------------------------------------------

--
-- Table structure for table `medical_records`
--

CREATE TABLE `medical_records` (
  `record_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `patient_name` varchar(100) NOT NULL,
  `record_type` varchar(50) NOT NULL,
  `height` double NOT NULL DEFAULT 0,
  `weight` double NOT NULL DEFAULT 0,
  `recorded_by` varchar(50) NOT NULL,
  `record_date` varchar(20) NOT NULL,
  `record_time` varchar(20) NOT NULL,
  `doctor` varchar(100) NOT NULL,
  `blood_pressure` varchar(20) NOT NULL,
  `heart_rate` varchar(20) NOT NULL,
  `temperature` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `medical_records`
--

INSERT INTO `medical_records` (`record_id`, `patient_id`, `patient_name`, `record_type`, `height`, `weight`, `recorded_by`, `record_date`, `record_time`, `doctor`, `blood_pressure`, `heart_rate`, `temperature`) VALUES
(1, 1, 'Mark Zamora', 'Lab Result', 175, 65, 'NRS-001', '06-15-2026', '9:00 AM', 'Dr. Ricardo Reyes', '180/30', '95', 37.5);

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `patient_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `birthday` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `contact_number` varchar(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `marital_status` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `room_number` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`patient_id`, `first_name`, `last_name`, `age`, `birthday`, `gender`, `contact_number`, `address`, `email`, `marital_status`, `status`, `room_number`) VALUES
(1, 'Mark', 'Zamora', 45, '05-24-1981', 'Male', '0956-587-0487', 'Platero, Biñan', 'maarkzamora@gmail.com', 'Married', 'Admitted', 'RM-201');

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy`
--

CREATE TABLE `pharmacy` (
  `medication_id` int(11) NOT NULL,
  `item_code` varchar(50) NOT NULL,
  `brand_name` varchar(100) NOT NULL,
  `generic_name` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL,
  `dosage_form` varchar(50) NOT NULL,
  `strength` varchar(50) NOT NULL,
  `current_stock` int(11) NOT NULL DEFAULT 0,
  `reorder_level` int(11) NOT NULL DEFAULT 0,
  `unit_price` decimal(10,2) NOT NULL DEFAULT 0.00,
  `expiration_date` varchar(30) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'In Stock'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pharmacy`
--

INSERT INTO `pharmacy` (`medication_id`, `item_code`, `brand_name`, `generic_name`, `category`, `dosage_form`, `strength`, `current_stock`, `reorder_level`, `unit_price`, `expiration_date`, `status`) VALUES
(1, 'MED-001', 'Generic', 'Mefenamic', 'Antibiotic', 'Capsule', '5', 100, 20, 50.00, '09-10-2028', 'In Stock');

-- --------------------------------------------------------

--
-- Table structure for table `prescriptions`
--

CREATE TABLE `prescriptions` (
  `rx_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `patient_name` varchar(100) NOT NULL,
  `medication_name` varchar(100) NOT NULL,
  `prescription_date` varchar(30) NOT NULL,
  `dosage` varchar(50) NOT NULL,
  `frequency` varchar(50) NOT NULL,
  `duration` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1,
  `diagnosis` varchar(150) NOT NULL,
  `refill_info` varchar(50) NOT NULL,
  `special_notes` text NOT NULL,
  `doctor` varchar(100) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prescriptions`
--

INSERT INTO `prescriptions` (`rx_id`, `patient_id`, `patient_name`, `medication_name`, `prescription_date`, `dosage`, `frequency`, `duration`, `quantity`, `diagnosis`, `refill_info`, `special_notes`, `doctor`, `status`) VALUES
(1, 1, 'Juan Santos', 'Mefenamic', '06-07-2026', '100 mg', 'Twice daily (BID)', '7 weeks', 10, 'Antibiotics', '5', 'take it until it heals', 'Dr. Ricardo Reyes', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(30) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `role`, `firstname`, `lastname`) VALUES
(1, 'ADM-001', 'admin123', 'Admin', 'John', 'Nayan'),
(4, 'ADM-002', 'thea123', 'Admin', 'Thea', 'Dela Cruz'),
(2, 'DOC-001', 'doctor123', 'Doctor', 'Rod', 'Orfella'),
(5, 'DOC-002', 'eiros123', 'Doctor', 'Eiros', 'Agulto'),
(3, 'NRS-001', 'nurse123', 'Nurse', 'Ara', 'Andal');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appt_id`);

--
-- Indexes for table `hospital_reports`
--
ALTER TABLE `hospital_reports`
  ADD PRIMARY KEY (`report_id`);

--
-- Indexes for table `hospital_staff`
--
ALTER TABLE `hospital_staff`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `medical_records`
--
ALTER TABLE `medical_records`
  ADD PRIMARY KEY (`record_id`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `pharmacy`
--
ALTER TABLE `pharmacy`
  ADD PRIMARY KEY (`medication_id`);

--
-- Indexes for table `prescriptions`
--
ALTER TABLE `prescriptions`
  ADD PRIMARY KEY (`rx_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `medical_records`
--
ALTER TABLE `medical_records`
  MODIFY `record_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `patients`
--
ALTER TABLE `patients`
  MODIFY `patient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pharmacy`
--
ALTER TABLE `pharmacy`
  MODIFY `medication_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `prescriptions`
--
ALTER TABLE `prescriptions`
  MODIFY `rx_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
