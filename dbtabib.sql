CREATE DATABASE IF NOT EXISTS dbtabib;
use dbtabib;
-- --------------------------------------------------------
--
-- --------------------------------------------------------
--
-- Table structure for table `DOCTOR_SPECIALIZATION`
--

CREATE TABLE IF NOT EXISTS `T_APPOINTMENT` (
  `ID` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `bloodPressure` varchar(255) DEFAULT NULL,
  `charges` int(11) DEFAULT NULL,
  `DIAGNOSIS` varchar(255) DEFAULT NULL,
  `followupDate` varchar(255) DEFAULT NULL,
  `PRESCRIPTION` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `TOKEN` int(11) DEFAULT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL,
  `VISIT_DATE` varchar(255) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `CLINIC_ID` int(11) DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DOCTOR_SPECIALIZATION`
--

INSERT INTO `T_APPOINTMENT` (`ID`, `age`, `bloodPressure`, `charges`, `DIAGNOSIS`, `followupDate`, `PRESCRIPTION`, `status`, `TOKEN`, `UPDATE_DATE`, `VISIT_DATE`, `weight`, `CLINIC_ID`, `DOCTOR_ID`, `PATIENT_ID`) VALUES
(1, 34, '120/180', 1000, 'Malaria', '2024-04-03', 'Panadol', 1, 2, '2024-03-08 12:19:13', '2024-10-10', 80, 1, 2, 2),
(16, 34, '900/700', 10000, 'Susti', '2024-08-16', 'Mota Injection', 0, 4, '2024-03-11 10:50:26', '2024-03-04', 120, 1, 2, 2),
(17, 43, '999/999', 999, 'Hello', '2024-04-08', 'Hello', 1, 1, '2024-03-08 13:57:43', '2024-06-01', 120, 3, 1, 6),
(45, 14, '', 0, '', '2024-06-23', '', 0, 1, '2024-03-13 10:50:17', '2024-06-01', 0, 3, 5, 7),
(46, 34, '', 0, '', '2024-08-11', '', 0, 1, '2024-03-13 10:55:27', '2024-06-01', 0, 2, 1, 2),
(47, 0, '', 0, '', '2024-12-25', '', 0, 2, '2024-03-13 10:58:30', '2024-06-01', 0, 2, 1, 8),
(48, 34, '100/160', 0, 'Common Cold', '', 'Ibuprofen ', 1, 1, '2024-03-18 07:07:05', '2024-04-03', 80, 3, 1, 2),
(49, 19, '', 0, '', '', '', 0, 2, '2024-03-18 07:08:50', '', 0, 3, 1, 9),
(50, 4, '', 0, '', '', '', 0, 1, '2024-03-19 17:58:24', '', 0, 1, 1, 4),
(51, 34, '', 0, '', '', '', 0, 2, '2024-03-19 18:02:19', '', 0, 1, 1, 2),
(52, 4, '', 0, '', '', '', 0, 1, '2024-03-19 23:58:51', '', 0, 2, 1, 4),
(53, 9, '', 0, '', '', '', 0, 2, '2024-03-22 10:58:35', '', 0, 2, 1, 5),
(54, 10, '', 0, '', '', '', 0, 3, '2024-03-23 11:22:10', '', 0, 1, 1, 10),
(55, 4, '', 0, '', '', '', 0, 4, '2024-03-23 11:50:05', '', 0, 1, 1, 4),
(56, 4, '', 0, '', '', '', 0, 3, '2024-03-23 11:53:03', '', 0, 2, 1, 4),
(57, 34, '', 0, '', '', '', 0, 4, '2024-04-02 11:17:34', '', 0, 2, 1, 2),
(59, 34, '', 0, '', '', '', 0, 1, '2024-04-02 11:24:00', '2024-04-02', 0, 3, 1, 2),
(60, 9, '', 0, '', '', '', 0, 2, '2024-04-02 11:37:51', '2024-04-02', 0, 3, 1, 5),
(61, 4, '', 0, '', '', '', 0, 3, '2024-04-02 11:46:05', '2024-04-02', 0, 3, 1, 4),
(62, 14, '', 0, '', '', '', 0, 4, '2024-04-02 11:47:12', '2024-04-02', 0, 3, 1, 7);

-- --------------------------------------------------------

--
-- Table structure for table `T_CLINIC`
--

CREATE TABLE IF NOT EXISTS `T_CLINIC` (
  `ID` int(11) NOT NULL,
  `LATLONG` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `T_CLINIC`
--

INSERT INTO `T_CLINIC` (`ID`, `LATLONG`, `ADDRESS`, `NAME`, `UPDATE_DATE`) VALUES
(1, '24.88698500524078,67.14920279786963', 'Shahrae Faisal', 'Dental Vision 2000', '2024-03-04 08:39:00'),
(2, '24.936011673986155, 67.09700367391424', 'ST-18, Block 4 Gulshan-e-Iqbal', 'Patel Hospital', '2024-03-06 06:44:07'),
(3, '24.925696403600487, 67.0924404002646', 'Street 1, Block 6 Gulshan-e-Iqbal', 'T.O Clinic', '2024-03-06 06:44:07'),
(4, '24.91064881437952, 67.12531532266284', 'Jauhar Square, B-12/8, Block 18 Gulistan-e-Johar', 'Family Care Clinic', '2024-03-06 06:44:07'),
(5, '24.897859596511033, 67.0995810616582', 'KDA Officers Housing Society Block A', 'Diabetes & Foot Care', '2024-03-06 06:44:07'),
(6, '24.902686422897073, 67.09833651675332', 'W32X+P9X, Colony Main Road, Shanti Nagar', 'Ahmed Clinic', '2024-03-06 06:44:07');

-- --------------------------------------------------------

--
-- Table structure for table `T_DOCTOR`
--

CREATE TABLE IF NOT EXISTS `T_DOCTOR` (
  `ID` int(11) NOT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `AGE` int(11) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `T_DOCTOR`
--

INSERT INTO `T_DOCTOR` (`ID`, `ADDRESS`, `AGE`, `GENDER`, `NAME`, `PASSWORD`, `PRIORITY`, `TYPE`, `UPDATE_DATE`, `USERNAME`) VALUES
(1, 'Shahrae Faisal', 41, 'male', 'Dr. Furqan Ahmed', '123456', 1, 1, '2024-03-04 08:40:31', 'furqan.ahmed'),
(2, 'Gulshan-e-Iqbal Block 13D', 45, 'male', 'Syed Faheem Ahmed', 'password123', 1, 3, '2024-03-06 06:27:34', 's.faheem'),
(3, '123 Main Street', 35, 'female', 'Jane Doe', 'password123', 1, 1, '2024-03-06 06:30:36', 'johndoe'),
(4, '456 Elm Street', 42, 'female', 'Jane Smith', 'password123', 1, 1, '2024-03-06 06:30:36', 'janesmith'),
(5, '789 Oak Avenue', 50, 'female', 'Michelle Johnson', 'password123', 1, 1, '2024-03-06 06:30:36', 'michaeljohnson'),
(6, '101 Pine Road', 28, 'female', 'Emily Davis', 'password123', 1, 1, '2024-03-06 06:30:36', 'emilydavis'),
(7, '202 Cedar Lane', 45, 'male', 'Christopher Wilson', 'password123', 1, 1, '2024-03-06 06:30:36', 'christopherwilson');

-- --------------------------------------------------------

--
-- Table structure for table `T_DOCTOR_CLINIC`
--

CREATE TABLE IF NOT EXISTS `T_DOCTOR_CLINIC` (
  `ID` int(11) NOT NULL,
  `CHARGES` int(11) DEFAULT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `startTime` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL,
  `CLINIC_ID` int(11) DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `T_DOCTOR_CLINIC`
--

INSERT INTO `T_DOCTOR_CLINIC` (`ID`, `CHARGES`, `endTime`, `startTime`, `UPDATE_DATE`, `CLINIC_ID`, `DOCTOR_ID`) VALUES
(1, 1000, '22:00', '17:00', '0000-00-00 00:00:00', 1, 1),
(2, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 2, 1),
(3, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 3, 1),
(4, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 2, 2),
(5, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 5, 2),
(6, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 6, 2),
(7, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 1, 3),
(8, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 6, 3),
(9, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 3, 5),
(10, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 1, 2),
(11, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 4, 4),
(12, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 5, 4),
(13, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 6, 6),
(14, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 5, 6),
(15, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 4, 7),
(16, 1000, '22:00', '17:00', '2024-03-06 08:32:10', 1, 7);

-- --------------------------------------------------------

--
-- Table structure for table `T_DOCTOR_QUALIFICATIONN`
--
CREATE TABLE IF NOT EXISTS `T_DOCTOR_QUALIFICATION` (
  `ID` int(11) NOT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `QUALIFICATION_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `T_DOCTOR_QUALIFICATION`
--

INSERT INTO `T_DOCTOR_QUALIFICATION` (`ID`, `UPDATE_DATE`, `DOCTOR_ID`, `QUALIFICATION_ID`) VALUES
(1, '2024-03-04 08:50:04', 1, 2),
(2, '2024-03-04 08:50:04', 1, 3),
(3, '2024-03-06 08:45:09', 2, 1),
(4, '2024-03-06 08:45:19', 2, 2),
(5, '2024-03-06 08:45:25', 2, 3),
(6, '2024-03-06 08:45:45', 4, 1),
(7, '2024-03-06 08:45:52', 5, 1),
(8, '2024-03-06 08:45:59', 6, 1),
(9, '2024-03-06 08:46:04', 7, 1),
(11, '2024-03-06 08:46:19', 4, 2),
(12, '2024-03-06 08:46:23', 4, 4),
(13, '2024-03-06 08:46:29', 5, 2),
(14, '2024-03-06 08:46:36', 5, 4),
(15, '2024-03-06 08:46:42', 5, 6),
(16, '2024-03-06 08:48:06', 3, 1),
(17, '2024-03-06 08:48:14', 3, 2),
(18, '2024-03-06 08:48:20', 3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `T_DOCTOR_SPECIALIZATION`
--

CREATE TABLE IF NOT EXISTS `T_DOCTOR_SPECIALIZATION` (
  `ID` int(11) NOT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `SPECIALIZATION_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `T_DOCTOR_SPECIALIZATION`
--

INSERT INTO `T_DOCTOR_SPECIALIZATION` (`ID`, `UPDATE_DATE`, `DOCTOR_ID`, `SPECIALIZATION_ID`) VALUES
(1, '2024-03-20 09:07:03', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `T_FEEDBACK`
--

CREATE TABLE IF NOT EXISTS `T_FEEDBACK` (
  `ID` int(11) NOT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `RATING` int(11) DEFAULT NULL,
  `TOKEN` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `T_LOGINSTATUS`
--

CREATE TABLE IF NOT EXISTS `T_LOGINSTATUS` (
  `ID` int(11) NOT NULL,
  `FCMTOKEN` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `TOKEN` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `T_LOGINSTATUS`
--

INSERT INTO `T_LOGINSTATUS` (`ID`, `FCMTOKEN`, `STATUS`, `TOKEN`, `TYPE`, `UPDATE_DATE`, `USERNAME`) VALUES
(159, NULL, '1', '170976797807207-03-2024-04-32-54', '2', '2024-03-06 23:32:58', '03323403109'),
(167, NULL, '1', '170976869947207-03-2024-04-44-59', '2', '2024-03-06 23:44:59', '03323403109'),
(168, NULL, '1', '170978430072307-03-2024-09-04-58', '2', '2024-03-07 04:05:00', '03323403109'),
(171, NULL, '1', '171009382978310-03-2024-23-02-11', '3', '2024-03-10 18:03:49', 's.faheem'),
(172, NULL, '1', '171014259024011-03-2024-12-36-27', '3', '2024-03-11 07:36:30', 's.faheem'),
(173, NULL, '1', '171016208720711-03-2024-18-01-24', '2', '2024-03-11 13:01:27', '03333233771'),
(176, NULL, '1', '171032443542113-03-2024-15-07-16', '2', '2024-03-13 10:07:15', '03323403109'),
(177, NULL, '1', '171039802574487587590', '2', '2024-03-14 06:33:45', '03323403109'),
(178, NULL, '1', '171074242532218-03-2024-11-13-46', '2', '2024-03-18 06:13:45', '03323403109'),
(179, NULL, '1', '171074713881918-03-2024-12-32-20', '3', '2024-03-18 07:32:18', 's.faheem'),
(180, NULL, '1', '171074736600118-03-2024-12-36-05', '3', '2024-03-18 07:36:06', 's.faheem'),
(181, NULL, '1', '171074738564518-03-2024-12-36-24', '3', '2024-03-18 07:36:25', 's.faheem'),
(182, NULL, '1', '171074742873218-03-2024-12-37-05', '3', '2024-03-18 07:37:08', 's.faheem'),
(183, NULL, '1', '171074755035818-03-2024-12-39-11', '3', '2024-03-18 07:39:10', 's.faheem'),
(184, NULL, '1', '171074758474318-03-2024-12-39-43', '3', '2024-03-18 07:39:44', 's.faheem'),
(185, NULL, '1', '171074758598318-03-2024-12-39-47', '3', '2024-03-18 07:39:45', 's.faheem'),
(186, NULL, '1', '171074761584918-03-2024-12-39-48', '3', '2024-03-18 07:40:15', 's.faheem'),
(187, NULL, '1', '171074764486318-03-2024-12-40-46', '3', '2024-03-18 07:40:44', 's.faheem'),
(188, NULL, '1', '171082673622919-03-2024-10-35-11', '2', '2024-03-19 05:38:56', '03323403109'),
(189, NULL, '1', '171083347438119-03-2024-12-31-13', '2', '2024-03-19 07:31:14', '03323403109'),
(190, NULL, '1', '171083354875019-03-2024-12-32-31', '2', '2024-03-19 07:32:28', '03323403109'),
(191, NULL, '1', '171083357739619-03-2024-12-33-00', '2', '2024-03-19 07:32:57', '03323403109'),
(192, NULL, '1', '171083566561119-03-2024-13-06-34', '2', '2024-03-19 08:07:45', '03323403109'),
(193, NULL, '1', '171086473355819-03-2024-21-12-11', '2', '2024-03-19 16:12:13', '03323403109'),
(194, NULL, '1', '171086477753219-03-2024-21-13-00', '2', '2024-03-19 16:12:57', '03323403109'),
(195, NULL, '1', '171086478570119-03-2024-21-13-09', '2', '2024-03-19 16:13:05', '03323403109'),
(196, NULL, '1', '171088210760420-03-2024-02-01-04', '2', '2024-03-19 21:01:47', '03323403109'),
(197, NULL, '1', '1710925984818123', '2', '2024-03-20 09:13:04', '03323403109'),
(198, NULL, '1', '171099870194521-03-2024-10-25-05', '2', '2024-03-21 05:25:01', '03333233771'),
(199, NULL, '1', '171099992447721-03-2024-10-45-28', '2', '2024-03-21 05:45:24', '03323403109'),
(200, NULL, '1', '171101153465121-03-2024-13-58-53', '2', '2024-03-21 08:58:54', '03323403109'),
(201, NULL, '1', '171101164669121-03-2024-13-58-54', '2', '2024-03-21 09:00:46', '03323403109'),
(202, NULL, '1', '171101164764021-03-2024-13-58-54', '2', '2024-03-21 09:00:47', '03323403109'),
(203, NULL, '1', '171101164774921-03-2024-13-58-54', '2', '2024-03-21 09:00:47', '03323403109'),
(204, NULL, '1', '171101164856521-03-2024-13-58-54', '2', '2024-03-21 09:00:48', '03323403109'),
(205, NULL, '1', '171101164872621-03-2024-13-58-54', '2', '2024-03-21 09:00:48', '03323403109'),
(206, NULL, '1', '171101164890121-03-2024-13-58-54', '2', '2024-03-21 09:00:48', '03323403109'),
(207, NULL, '1', '171101164906321-03-2024-13-58-54', '2', '2024-03-21 09:00:49', '03323403109'),
(208, NULL, '1', '171101180099721-03-2024-13-58-54', '2', '2024-03-21 09:03:20', '03323403109'),
(209, NULL, '1', '171101180161221-03-2024-13-58-54', '2', '2024-03-21 09:03:21', '03323403109'),
(210, NULL, '1', '171101180171021-03-2024-13-58-54', '2', '2024-03-21 09:03:21', '03323403109'),
(211, NULL, '1', '171101180281321-03-2024-13-58-54', '2', '2024-03-21 09:03:22', '03323403109'),
(212, NULL, '1', '171101183779821-03-2024-13-58-54', '2', '2024-03-21 09:03:57', '03323403109'),
(213, NULL, '1', '171101183779821-03-2024-13-58-54', '2', '2024-03-21 09:03:57', '03323403109'),
(214, NULL, '1', '171101183789421-03-2024-13-58-54', '2', '2024-03-21 09:03:57', '03323403109'),
(215, NULL, '1', '171101184171021-03-2024-14-04-00', '2', '2024-03-21 09:04:01', '03323403109'),
(216, NULL, '1', '171101185008921-03-2024-14-04-09', '2', '2024-03-21 09:04:10', '03323403109'),
(217, NULL, '1', '171101185482521-03-2024-14-04-12', '2', '2024-03-21 09:04:14', '03323403109'),
(218, NULL, '1', '171101186225921-03-2024-14-04-21', '2', '2024-03-21 09:04:22', '03323403109'),
(219, NULL, '1', '171101186930021-03-2024-14-04-28', '2', '2024-03-21 09:04:29', '03323403109'),
(220, NULL, '1', '171101656295721-03-2024-15-20-27', '2', '2024-03-21 10:22:42', '03323403109'),
(221, NULL, '1', '171101661936821-03-2024-15-23-37', '2', '2024-03-21 10:23:39', '03323403109'),
(222, NULL, '1', '171101662439021-03-2024-15-23-43', '2', '2024-03-21 10:23:44', '03323403109'),
(223, NULL, '1', '171101667532121-03-2024-15-24-33', '2', '2024-03-21 10:24:35', '03323403109'),
(224, NULL, '1', '171101671503821-03-2024-15-25-08', '2', '2024-03-21 10:25:15', '03323403109'),
(225, NULL, '1', '171101672093021-03-2024-15-25-19', '2', '2024-03-21 10:25:20', '03323403109'),
(226, NULL, '1', '171101682939721-03-2024-15-27-08', '2', '2024-03-21 10:27:09', '03323403109'),
(227, NULL, '1', '171101683050821-03-2024-15-27-09', '2', '2024-03-21 10:27:10', '03323403109'),
(228, NULL, '1', '171101685996021-03-2024-15-27-33', '2', '2024-03-21 10:27:39', '03323403109'),
(229, NULL, '1', '171101694570921-03-2024-15-28-44', '2', '2024-03-21 10:29:05', '03323403109'),
(230, NULL, '1', '171101698435921-03-2024-15-29-43', '2', '2024-03-21 10:29:44', '03323403109'),
(231, NULL, '1', '171101699283121-03-2024-15-29-52', '2', '2024-03-21 10:29:52', '03323403109'),
(232, NULL, '1', '171101699945221-03-2024-15-29-58', '2', '2024-03-21 10:29:59', '03323403109'),
(233, NULL, '1', '171101700644521-03-2024-15-30-05', '2', '2024-03-21 10:30:06', '03323403109'),
(234, NULL, '1', '171101701116721-03-2024-15-30-08', '2', '2024-03-21 10:30:11', '03323403109'),
(235, NULL, '1', '171101701138821-03-2024-15-30-11', '2', '2024-03-21 10:30:11', '03323403109'),
(236, NULL, '1', '171110124632022-03-2024-14-48-53', '2', '2024-03-22 09:54:06', '03323403109'),
(237, NULL, '1', '171110244815722-03-2024-15-13-35', '2', '2024-03-22 10:14:08', '03323403109'),
(238, NULL, '1', '171110247087422-03-2024-15-14-30', '2', '2024-03-22 10:14:30', '03323403109'),
(239, NULL, '1', '171150125632127-03-2024-06-00-46', '3', '2024-03-27 01:00:56', 's.faheem'),
(240, NULL, '1', '171194665104301-04-2024-09-43-32', '3', '2024-04-01 04:44:11', 's.faheem'),
(241, NULL, '1', '171207850164102-04-2024-22-21-41', '2', '2024-04-02 17:21:41', '03323403109'),
(242, NULL, '1', '171216619448203-04-2024-22-43-13', '3', '2024-04-03 17:43:14', 's.faheem');

-- --------------------------------------------------------

--
-- Table structure for table `T_PATIENT`
--

CREATE TABLE IF NOT EXISTS `T_PATIENT` (
  `ID` int(11) NOT NULL,
  `CELL_NUMBER` varchar(255) DEFAULT NULL,
  `DOB` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `T_PATIENT`
--

INSERT INTO `T_PATIENT` (`ID`, `CELL_NUMBER`, `DOB`, `GENDER`, `NAME`, `PASSWORD`, `status`, `UPDATE_DATE`) VALUES
(2, '03323403109', '1990-02-28', 'male', 'Syed Muhammad Hassan', 'muhammad123', 1, '2024-03-04 08:31:19'),
(3, 'moin.ansari', '2000-01-01', 'female', 'Aisha Khan', NULL, 1, '2024-03-06 07:53:20'),
(4, '03323403109', '2020-03-01', 'male', 'Hashir Ahmed', NULL, 1, '2024-03-06 11:42:31'),
(5, '03323403109', '2014-07-19', 'male', 'Qasim Farooq', NULL, 1, '2024-03-06 11:43:07'),
(6, '03333233771', '1980-08-02', 'female', 'Qurat ul Ain', 'password123', 1, '2024-03-11 12:43:55'),
(7, '03323403109', '2010-03-02', 'male', 'Zulqarnain', NULL, 1, '2024-03-13 11:43:07'),
(8, '03323403109', '2015-11-02', 'male', 'Areeb Ahmed', NULL, 1, '2024-03-13 10:57:58'),
(9, '03323403109', '2004-11-24', 'female', 'New member', NULL, 1, '2024-03-18 06:42:57'),
(10, '03323403109', '2014-01-01', 'female', 'Sadia Muhammad', NULL, 1, '2024-03-19 08:31:48'),
(26, '03333233771', '2015-02-01', 'female', 'Noor', NULL, 1, '2024-03-21 05:42:20'),
(27, '03332222233', '21-Mar-2024', 'male', 'Fdhhbb', 'aaa', 1, '2024-03-21 09:56:31'),
(28, '03552222233', '21-Mar-2024', 'male', 'Fdhhbb', 'aaa', 1, '2024-03-21 09:56:58'),
(29, '03552222233', '21-Mar-2024', 'male', 'Fdhhbb', 'aaa', 1, '2024-03-21 09:59:01'),
(30, '03961605454', '31-Dec-1969', 'male', 'Anas ', 'abv', 1, '2024-03-21 10:00:30'),
(37, '03323403109', '2016-11-01', 'male', 'Huzaifa', NULL, 1, '2024-04-02 11:48:56'),
(38, '03323403109', '1995-04-01', 'male', 'Ahmed Chaudhary', NULL, 1, '2024-04-02 21:57:08');

-- --------------------------------------------------------

--
-- Table structure for table `T_QUALIFICATION`
--

CREATE TABLE IF NOT EXISTS `T_QUALIFICATION` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `T_QUALIFICATION`
--

INSERT INTO `T_QUALIFICATION` (`ID`, `NAME`, `UPDATE_DATE`) VALUES
(1, 'MBBS', '2024-03-04 08:47:56'),
(2, 'BDS', '2024-03-04 08:47:56'),
(3, 'RDS', '2024-03-04 08:48:10'),
(4, 'FCPS', '2024-03-06 08:34:55'),
(5, 'MCPS', '2024-03-06 08:34:55'),
(6, 'MS', '2024-03-06 08:34:55');

-- --------------------------------------------------------

--
-- Table structure for table `T_SPECIALIZATION`
--

CREATE TABLE IF NOT EXISTS `T_SPECIALIZATION` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `T_SPECIALIZATION`
--

INSERT INTO `T_SPECIALIZATION` (`ID`, `NAME`, `UPDATE_DATE`) VALUES
(1, 'Dentist', '2024-03-20 09:06:17');

-- --------------------------------------------------------

--
-- Table structure for table `T_TREATMENT`
--

CREATE TABLE IF NOT EXISTS `T_TREATMENT` (
  `ID` int(11) NOT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL,
  `APPOINTMENT_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `T_TREATMENT`
--

INSERT INTO `T_TREATMENT` (`ID`, `DETAIL`, `NAME`, `UPDATE_DATE`, `APPOINTMENT_ID`) VALUES
(2, 'Details B', 'Treatment B', '2024-04-08 04:44:45', 17),
(47, 'Details A', 'Treatment A', '2024-04-08 04:44:45', 17),
(48, 'Detail 1', 'Crown Luting', '2024-04-08 04:48:49', 17);

-- --------------------------------------------------------

--
-- Table structure for table `T_TREATMENT_BANK`
--

CREATE TABLE IF NOT EXISTS `T_TREATMENT_BANK` (
  `ID` int(11) NOT NULL,
  `CHARGES` int(11) DEFAULT NULL,
  `DOCTORTYPE` int(11) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `T_TREATMENT_BANK`
--

INSERT INTO `T_TREATMENT_BANK` (`ID`, `CHARGES`, `DOCTORTYPE`, `NAME`, `UPDATE_DATE`) VALUES
(1, 0, 1, 'Laboratory Tests', '2022-09-01 09:56:08'),
(2, 0, 2, 'Tooth Extraction', '2022-09-01 09:56:08'),
(3, 0, 1, 'Injection', '2022-09-01 09:56:08'),
(4, 0, 2, 'Root Canal', '2022-09-01 09:56:08'),
(5, 0, 2, 'Consultation', '2022-09-01 09:56:08'),
(6, 0, 2, 'X-RAY', '2022-09-01 09:56:08'),
(7, 0, 2, 'Dressing', '2022-09-01 09:56:08'),
(8, 0, 2, 'Posterior Restoration', '2022-09-01 09:56:08'),
(9, 0, 2, 'Light Cure Treatment', '2022-09-01 09:56:08'),
(10, 0, 2, 'Scaling with polishing', '2022-09-01 09:56:08'),
(11, 0, 2, 'Procelein Crown', '2022-09-01 09:56:08'),
(12, 0, 2, 'Procelein Metallic', '2022-09-01 09:56:08'),
(13, 0, 2, 'Crown Zirconium', '2022-09-01 09:56:08'),
(14, 0, 2, 'Partial Denture', '2022-09-01 09:56:08'),
(15, 0, 2, 'Cast Partial Denture', '2022-09-01 09:56:08'),
(16, 0, 2, 'Soft Based Denture', '2022-09-01 09:56:08'),
(17, 0, 2, 'Denture Repair', '2022-09-01 09:56:08'),
(18, 0, 2, 'Denture Relining Treatment', '2022-09-01 09:56:08'),
(19, 0, 2, 'Broken Down Root', '2022-09-01 09:56:08'),
(20, 0, 2, 'Third Molar Impaction', '2022-09-01 09:56:08'),
(21, 0, 2, 'Orthodontic appliances', '2022-09-01 09:56:08'),
(22, 0, 2, 'Orthodontic Treatment', '2022-09-01 09:56:08'),
(23, 0, 2, 'Whitening Treatment', '2022-09-01 09:56:08'),
(24, 0, 2, 'Dental Implant', '2022-09-01 09:56:08'),
(25, 0, 2, 'Crown Luting', '2022-09-01 09:56:08'),
(26, 0, 2, 'Bridge Luting', '2022-09-01 09:56:08');

-- --------------------------------------------------------

--
-- Table structure for table `T_USER`
--

CREATE TABLE IF NOT EXISTS `T_USER` (
  `ID` int(11) NOT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `AGE` int(11) DEFAULT NULL,
  `CELL_NUMBER` varchar(255) DEFAULT NULL,
  `FCM_TOKEN` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` varchar(255) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `T_USER`
--

INSERT INTO `T_USER` (`ID`, `ADDRESS`, `AGE`, `CELL_NUMBER`, `FCM_TOKEN`, `NAME`, `PASSWORD`, `PRIORITY`, `TYPE`, `UPDATE_DATE`, `USER_ID`, `USERNAME`) VALUES
(1, 'North Nazimabad', 30, '03214759384', NULL, 'Moin Ansari', 'moin123', 0, '3', '2023-05-16 13:46:04', 3, 'moin.ansari1'),
(2, NULL, 0, '03323403109', '', 'Tehmoor Ali', 'password1234', 0, '2', NULL, 2, '03323403109'),
(3, 'Gulshan-e-Iqbal Block 13D', 46, '03048680864', '', 'Syed Faheem Ahmed', 'password123', 0, '3', '2024-03-07 09:27:57', 4, 's.faheem'),
(5, NULL, 0, '03333233771', 'PwZodSJ1698EIwcXmEcOU-', 'Qurat ul Ain', 'password123', 0, '2', NULL, 6, '03333233771'),
(8, NULL, 0, '03552222233', NULL, 'Fdhhbb', 'aaa', 0, '2', NULL, 29, '03552222233'),
(9, NULL, 0, '03961605454', NULL, 'Anas ', 'abv', 0, '2', NULL, 30, '03961605454');

--
-- Indexes for dumped tables
--
--
--
ALTER TABLE `T_APPOINTMENT`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK46537014A86715E5` (`DOCTOR_ID`),
  ADD KEY `FK46537014AED03C6F` (`PATIENT_ID`),
  ADD KEY `FK46537014D6F9545` (`CLINIC_ID`);

--
-- Indexes for table `T_CLINIC`
--
ALTER TABLE `T_CLINIC`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `T_DOCTOR`
--
ALTER TABLE `T_DOCTOR`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `T_DOCTOR_CLINIC`
--
ALTER TABLE `T_DOCTOR_CLINIC`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK4734FF5DA86715E5` (`DOCTOR_ID`),
  ADD KEY `FK4734FF5DD6F9545` (`CLINIC_ID`);

--
-- Indexes for table `T_DOCTOR_QUALIFICATION`
--
ALTER TABLE `T_DOCTOR_QUALIFICATION`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK82D681BAA86715E5` (`DOCTOR_ID`),
  ADD KEY `FK82D681BAC99A86F` (`QUALIFICATION_ID`);

--
-- Indexes for table `T_DOCTOR_SPECIALIZATION`
--
ALTER TABLE `T_DOCTOR_SPECIALIZATION`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK8F378700A86715E5` (`DOCTOR_ID`),
  ADD KEY `FK8F37870040987365` (`SPECIALIZATION_ID`);

--
-- Indexes for table `T_FEEDBACK`
--
ALTER TABLE `T_FEEDBACK`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK50BA2B10A86715E5` (`DOCTOR_ID`),
  ADD KEY `FK50BA2B10AED03C6F` (`PATIENT_ID`);

--
-- Indexes for table `T_LOGINSTATUS`
--
ALTER TABLE `T_LOGINSTATUS`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `T_PATIENT`
--
ALTER TABLE `T_PATIENT`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `T_QUALIFICATION`
--
ALTER TABLE `T_QUALIFICATION`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `T_SPECIALIZATION`
--
ALTER TABLE `T_SPECIALIZATION`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `T_TREATMENT`
--
ALTER TABLE `T_TREATMENT`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK249D2A2D92B4D350` (`APPOINTMENT_ID`);

--
-- Indexes for table `T_TREATMENT_BANK`
--
ALTER TABLE `T_TREATMENT_BANK`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `T_USER`
--
ALTER TABLE `T_USER`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `DOCTOR_SPECIALIZATION`
--
--
-- AUTO_INCREMENT for table `T_CLINIC`
--
ALTER TABLE `T_CLINIC`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `T_DOCTOR`
--
ALTER TABLE `T_DOCTOR`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `T_DOCTOR_CLINIC`
--
ALTER TABLE `T_DOCTOR_CLINIC`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `T_DOCTOR_QUALIFICATION`
--
ALTER TABLE `T_DOCTOR_QUALIFICATION`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `T_DOCTOR_SPECIALIZATION`
--
ALTER TABLE `T_DOCTOR_SPECIALIZATION`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `T_FEEDBACK`
--
ALTER TABLE `T_FEEDBACK`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `T_PATIENT`
--
ALTER TABLE `T_PATIENT`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `T_QUALIFICATION`
--
ALTER TABLE `T_QUALIFICATION`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `T_SPECIALIZATION`
--
ALTER TABLE `T_SPECIALIZATION`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `T_TREATMENT`
--
ALTER TABLE `T_TREATMENT`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `T_TREATMENT_BANK`
--
ALTER TABLE `T_TREATMENT_BANK`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `T_USER`
--
ALTER TABLE `T_USER`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `DOCTOR_PATIENT`
--
--
-- Constraints for table `T_DOCTOR_CLINIC`
--
ALTER TABLE `T_DOCTOR_CLINIC`
  ADD CONSTRAINT `FK4734FF5DA86715E5` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `T_DOCTOR` (`ID`),
  ADD CONSTRAINT `FK4734FF5DD6F9545` FOREIGN KEY (`CLINIC_ID`) REFERENCES `T_CLINIC` (`ID`);

--
-- Constraints for table `T_DOCTOR_QUALIFICATION`
--
ALTER TABLE `T_DOCTOR_QUALIFICATION`
  ADD CONSTRAINT `FK82D681BAA86715E5` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `T_DOCTOR` (`ID`),
  ADD CONSTRAINT `FK82D681BAC99A86F` FOREIGN KEY (`QUALIFICATION_ID`) REFERENCES `T_QUALIFICATION` (`ID`);

--
-- Constraints for table `T_DOCTOR_SPECIALIZATION`
--
ALTER TABLE `T_DOCTOR_SPECIALIZATION`
  ADD CONSTRAINT `FK8F37870040987365` FOREIGN KEY (`SPECIALIZATION_ID`) REFERENCES `T_SPECIALIZATION` (`ID`),
  ADD CONSTRAINT `FK8F378700A86715E5` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `T_DOCTOR` (`ID`);

--
-- Constraints for table `T_FEEDBACK`
--
ALTER TABLE `T_FEEDBACK`
  ADD CONSTRAINT `FK50BA2B10A86715E5` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `T_DOCTOR` (`ID`),
  ADD CONSTRAINT `FK50BA2B10AED03C6F` FOREIGN KEY (`PATIENT_ID`) REFERENCES `T_PATIENT` (`ID`);

--
-- Constraints for table `T_TREATMENT`
--
ALTER TABLE `T_TREATMENT`
  ADD CONSTRAINT `FK249D2A2D92B4D350` FOREIGN KEY (`APPOINTMENT_ID`) REFERENCES `T_APPOINTMENT` (`ID`);
COMMIT;