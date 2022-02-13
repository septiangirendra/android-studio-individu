-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 13, 2022 at 06:44 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_inixtraining`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_kelas`
--

CREATE TABLE `tb_detail_kelas` (
  `id_detail_kls` int(10) NOT NULL,
  `id_kls` int(10) NOT NULL,
  `id_pst` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_detail_kelas`
--

INSERT INTO `tb_detail_kelas` (`id_detail_kls`, `id_kls`, `id_pst`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 1, 7),
(8, 1, 8),
(9, 1, 9),
(10, 1, 10),
(11, 1, 11),
(12, 1, 12),
(13, 1, 13),
(14, 1, 14),
(15, 1, 15),
(16, 1, 16),
(17, 1, 17),
(18, 1, 18),
(19, 1, 19),
(20, 1, 20),
(21, 2, 1),
(22, 2, 2),
(23, 2, 3),
(24, 2, 4),
(25, 2, 5),
(26, 2, 6),
(27, 2, 7),
(28, 2, 8),
(29, 2, 9),
(30, 2, 10),
(31, 2, 11),
(32, 2, 12),
(33, 2, 13),
(34, 2, 14),
(35, 2, 15),
(36, 2, 16),
(37, 2, 17),
(38, 2, 18),
(39, 2, 19),
(40, 2, 20),
(41, 2, 21),
(42, 2, 22),
(43, 2, 23),
(44, 2, 24),
(45, 3, 1),
(46, 3, 2),
(47, 3, 3),
(48, 3, 4),
(49, 3, 5),
(50, 3, 6),
(51, 3, 7),
(52, 3, 8),
(53, 3, 9),
(54, 3, 10),
(55, 4, 10),
(56, 4, 11),
(57, 4, 12),
(58, 4, 13),
(59, 4, 14),
(60, 4, 15),
(61, 4, 16),
(62, 4, 17),
(63, 4, 18),
(64, 4, 19),
(65, 4, 20),
(66, 5, 5),
(67, 5, 6),
(68, 5, 7),
(69, 5, 8),
(70, 5, 9),
(71, 5, 10),
(72, 5, 11),
(73, 5, 12),
(74, 5, 13),
(75, 5, 14),
(76, 5, 15),
(77, 6, 3),
(78, 6, 4),
(79, 6, 5),
(80, 6, 6),
(81, 6, 7),
(82, 6, 8),
(83, 6, 9),
(84, 6, 10),
(85, 6, 11),
(86, 6, 12),
(87, 7, 3),
(88, 7, 5),
(89, 7, 7),
(90, 7, 9),
(91, 7, 11),
(92, 7, 13),
(93, 7, 15),
(94, 7, 17),
(95, 7, 19),
(96, 7, 21),
(97, 7, 23),
(98, 8, 2),
(99, 8, 4),
(100, 8, 6),
(101, 8, 8),
(102, 8, 10),
(103, 8, 12),
(104, 8, 14),
(105, 8, 16),
(106, 8, 18),
(107, 8, 20),
(108, 8, 22),
(109, 9, 11),
(110, 9, 13),
(111, 9, 15),
(112, 9, 17),
(113, 9, 19),
(114, 9, 21),
(115, 9, 23),
(116, 10, 6),
(117, 10, 8),
(118, 10, 10),
(119, 10, 12),
(120, 10, 14),
(121, 10, 16),
(122, 10, 18),
(123, 10, 20),
(124, 11, 11),
(125, 11, 13),
(126, 11, 15),
(127, 11, 17),
(128, 11, 19),
(129, 11, 21),
(130, 11, 23),
(131, 12, 1),
(132, 12, 2),
(133, 12, 3),
(134, 12, 4),
(135, 12, 5),
(136, 12, 6),
(137, 12, 7),
(138, 12, 8),
(139, 12, 9),
(140, 12, 10),
(141, 13, 12),
(142, 13, 13),
(143, 13, 14),
(144, 13, 15),
(145, 13, 16),
(146, 13, 17),
(147, 13, 18),
(148, 13, 19),
(149, 13, 20),
(150, 13, 21),
(151, 13, 22),
(152, 14, 10),
(153, 14, 12),
(154, 14, 14),
(155, 14, 16),
(156, 14, 18),
(157, 14, 20),
(158, 14, 22),
(159, 14, 24),
(160, 15, 2),
(161, 15, 4),
(162, 15, 6),
(163, 15, 8),
(164, 15, 10),
(165, 15, 12),
(166, 15, 14),
(167, 15, 16),
(168, 15, 18),
(169, 15, 20),
(170, 16, 1),
(171, 16, 2),
(172, 16, 3),
(173, 16, 4),
(174, 16, 5),
(175, 16, 6),
(176, 16, 7),
(177, 16, 8),
(178, 16, 9),
(179, 16, 10),
(180, 17, 8),
(181, 17, 10),
(182, 17, 12),
(183, 17, 14),
(184, 17, 16),
(185, 17, 18),
(186, 17, 20),
(187, 17, 22),
(188, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tb_instruktur`
--

CREATE TABLE `tb_instruktur` (
  `id_ins` int(3) NOT NULL,
  `nama_ins` varchar(25) NOT NULL,
  `email_ins` varchar(50) NOT NULL,
  `hp_ins` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_instruktur`
--

INSERT INTO `tb_instruktur` (`id_ins`, `nama_ins`, `email_ins`, `hp_ins`) VALUES
(1, 'ACHMAD RAIHAN', 'ahmdraihann01@gmail.com', '16119950'),
(2, 'ANDIKADWIROMADHAN', 'andika21@student.gunadarma.ac.id', '10119737'),
(3, 'ANSELMUS PRADHITO BIMO', 'anselronaldo1@gmail.com', '10119940'),
(4, 'ARIEF PRIAMBODO', 'ARIEFPOD@GMAIL.COM', '11119015'),
(5, 'ARIF SURYA MAULANA', 'arifsurya906@gmail.com', '11119028'),
(6, 'ARYA TRI PRADANA', 'pradanatriarya25@gmail.com', '11119087'),
(7, 'ARYAGUNASURYANUGRAHA', 'sacrificed12@student.gunadarma.ac.id', '11119088'),
(8, 'BIMO BAGUS BAGASKORO', 'bimobagusbagaskoro@gmail.com', '11119360'),
(9, 'DICOFIRMANSYAHPUTRA', 'dicosaputra@student.gunadarma.ac.id', '11119785'),
(10, 'DIMAS ALIF ADITYA', 'dimasalifaditya04@gmail.com', '11119806'),
(11, 'DINDA MONICA KUSUMAYANTI', 'dindamonica75@gmail.com', '11119858'),
(12, 'FADILAHKHOIRUNNISA', 'fadilahkhoirunnisa@student.gunadarma.ac.id', '12119132'),
(13, 'FAJAR HAMZAH SAPUTRA', 'fajarhamzahsaputra38@gmail.com', '12119196'),
(14, 'GRAYGENZALESHERMAWAN', 'graygenzalez@student.gunadarma.ac.id', '12119648'),
(15, 'IMAM BAHY PUTRA SUSETYO', 'imam.12119961@student.gunadarma.ac.id', '12119961'),
(16, 'INDAH PURNAMA SARI', 'iiindahpurnamasariii@gmail.com', '13119003'),
(17, 'IRFAN SETIAWAN HUTAGAOL', 'setiawanirfan601@gmail.com', '17119230'),
(18, 'JOANTIRAANGGARA', 'joantira99@student.gunadarma.ac.id', '17119553'),
(19, 'LANIHANADERI', 'lanihanaderi@student.gunadarma.ac.id', '13119365'),
(24, 'SIGID BUDIANTO', 'sigitbudianto@gmail.com', '082234649388');

-- --------------------------------------------------------

--
-- Table structure for table `tb_kelas`
--

CREATE TABLE `tb_kelas` (
  `id_kls` int(10) NOT NULL,
  `tgl_mulai_kls` date NOT NULL,
  `tgl_akhir_kls` date NOT NULL,
  `id_ins` int(10) NOT NULL,
  `id_mat` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_kelas`
--

INSERT INTO `tb_kelas` (`id_kls`, `tgl_mulai_kls`, `tgl_akhir_kls`, `id_ins`, `id_mat`) VALUES
(1, '2022-01-14', '2022-01-28', 1, 5),
(2, '2022-02-01', '2022-02-28', 2, 2),
(3, '2022-03-01', '2022-03-31', 3, 3),
(4, '2022-04-01', '2022-03-31', 4, 4),
(5, '2022-04-01', '2022-04-30', 5, 5),
(6, '2022-05-01', '2022-05-31', 6, 6),
(7, '2022-06-01', '2022-06-30', 7, 7),
(8, '2022-07-01', '2022-07-31', 8, 8),
(9, '2022-08-01', '2022-08-31', 9, 9),
(10, '2022-09-01', '2021-09-30', 10, 10),
(11, '2021-10-01', '2021-10-31', 11, 11),
(12, '2021-11-01', '2021-11-30', 12, 12),
(13, '2021-12-01', '2021-12-31', 13, 13),
(14, '2022-01-01', '2022-01-31', 14, 14),
(15, '2022-02-01', '2022-02-28', 15, 15),
(16, '2022-03-01', '2022-03-31', 16, 16),
(17, '2022-04-01', '2022-04-30', 17, 17),
(18, '2022-05-01', '2022-05-31', 18, 18),
(19, '2022-06-01', '2022-06-30', 19, 19),
(22, '2022-01-01', '2022-01-01', 1, 4),
(23, '2022-01-01', '2022-01-01', 1, 1),
(24, '2022-01-15', '2022-01-15', 4, 19),
(25, '2022-02-01', '2021-11-01', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_materi`
--

CREATE TABLE `tb_materi` (
  `id_mat` int(11) NOT NULL,
  `nama_mat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_materi`
--

INSERT INTO `tb_materi` (`id_mat`, `nama_mat`) VALUES
(1, 'Praktikum Komputasi Big Data'),
(2, 'Pemrograman Berbasis WEB **'),
(3, 'Testing dan Implementasi Sistem *'),
(4, 'Bahasa Inggris Bisnis 1'),
(5, 'Sistem Multimedia'),
(6, 'Pengelolaan Proyek Sis.Informasi*'),
(7, 'Sistem Terdistribusi'),
(8, 'Audit Teknologi Sistem Informasi#'),
(9, 'Komputasi Big Data'),
(10, 'Analisis Kinerja Sistem'),
(11, 'Sistem Keamanan Tek. Informasi *'),
(12, 'Grafik Komp. & Pengolahan Citra**'),
(13, 'Sistem Basis Data 1 **'),
(14, 'Pemrograman Berbasis Web **'),
(15, 'Jejaring Sosial&Konten Kreatif #'),
(16, 'Pemrog. Berorientasi Objek **'),
(17, 'Praktikum Teknologi dan Kecerdasan Artifisial'),
(18, 'Interaksi Manusia & Komputer*/**'),
(19, 'Graf & Analisis Algoritma'),
(20, 'Teknologi Kecerdasan Artificial'),
(21, 'Teknik Pemrog. Terstruktur **'),
(22, 'Peng. Org. & Arst Komputer'),
(23, 'Inovasi SI & New Technology #'),
(24, 'Algoritma & Pemrograman 1A *'),
(25, 'Peng. Tek. Sistem Informasi B**'),
(28, 'TEKNIK INDUSTRIAL');

-- --------------------------------------------------------

--
-- Table structure for table `tb_peserta`
--

CREATE TABLE `tb_peserta` (
  `id_pst` int(11) NOT NULL,
  `nama_pst` varchar(50) NOT NULL,
  `email_pst` varchar(50) NOT NULL,
  `hp_pst` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_peserta`
--

INSERT INTO `tb_peserta` (`id_pst`, `nama_pst`, `email_pst`, `hp_pst`) VALUES
(1, 'ADELIA MAWADDAH', 'adeliamawaddah56@gmail.com', '10119131'),
(2, 'ALBERTO YUBILIO LELO', 'aganlelo18@gmail.com', '10119415'),
(3, 'ALDO RIZKY ALPRIADI', 'aldorizky33@gmail.com', '10119444'),
(4, 'AMINAH KHANSA KAMILA', 'AMINAHKHANSA@GMAIL.COM', '10119659'),
(5, 'ANDRIANI MARSHANDA PUTRI', 'marshandaandriani6@gmail.com', '10119781'),
(6, 'ANISA AMELIA PUTRI', 'an15saputri.aap@gmail.com', '10119850'),
(7, 'AWAN MUKTI JAYA', 'awan.mukti@yahoo.co.id', '11119204'),
(8, 'AZZAM FADHILAH', 'AZZAM.F02@GMAIL.COM', '11119253'),
(9, 'BADAR PUTRI GAHARI', 'BADARPUTRIGAHARI11@GMAIL.COM', '16119980'),
(10, 'BERIL FRANCISCO', 'BERILFRANCISCO77@GMAIL.COM', '11119339'),
(11, 'DEWA RIZQY SYAH', 'rawajatiok@gmail.com', '11119717'),
(12, 'ERWIN DWI PUTRA', 'erwin.pakpahan10@gmail.com', '12119067'),
(13, 'FAKHRI AQIL HIDAYAT', 'aafakhri6@gmail.com', '12119210'),
(14, 'ISMAIL ADHA LAKONI', 'ismail@ilab.com', '17119540'),
(15, 'KEVIN GANDRI FERNANDO', 'KEVIN_GANDRI1234@GMAIL.COM', '13119261'),
(16, 'KEVIN PANOGARI ABY PRADANA', 'kevinabyy172@gmail.com', '13119269'),
(17, 'KORNELIUS PASKA NUGRAHA GRENAUER', 'onelnauer16@gmail.com', '13119332'),
(18, 'LA MACCA', 'lamacca187@gmail.com', '13119353'),
(19, 'M.TEGUH FATWA', 'Kiki.satria30@gmail.com', '13119495'),
(20, 'MOCHAMAD ALFAN CATUR KUSUMA YUDHA', 'MOCHAMAD.KUSUMA@GMAIL.COM', '13119744'),
(21, 'MUHAMMAD ALFIN MAJID', 'malfinnmajid@gmail.com', '13119994'),
(22, 'MUHAMMAD DAFI ARYASUTA', 'dafimuhammad75@gmail.com', '14119083'),
(23, 'MUHAMMAD ERIK AKBAR PRAMANA', 'erik.bamzyyy@gmail.com', '14119102'),
(24, 'MUHAMMAD FAIZAL', 'muhammadfaizal6969@gmail.com', '14119133'),
(25, 'ACHMAD RAIHAN', 'ahmdraihann01@gmail.com', '1644769157'),
(26, 'ANDIKADWIROMADHAN', 'andika21@student.gunadarma.ac.id', '1644769157'),
(27, 'ANSELMUS PRADHITO BIMO', 'anselronaldo1@gmail.com', '1644769157'),
(28, 'ARIEF PRIAMBODO', 'ARIEFPOD@GMAIL.COM', '1644769157'),
(29, 'ARIF SURYA MAULANA', 'arifsurya906@gmail.com', '1644769157'),
(30, 'ARYA TRI PRADANA', 'pradanatriarya25@gmail.com', '1644769157'),
(31, 'ARYAGUNASURYANUGRAHA', 'sacrificed12@student.gunadarma.ac.id', '1644769157'),
(32, 'BIMO BAGUS BAGASKORO', 'bimobagusbagaskoro@gmail.com', '1644769157'),
(33, 'DICOFIRMANSYAHPUTRA', 'dicosaputra@student.gunadarma.ac.id', '1644769157'),
(34, 'DIMAS ALIF ADITYA', 'dimasalifaditya04@gmail.com', '1644769157'),
(35, 'DINDA MONICA KUSUMAYANTI', 'dindamonica75@gmail.com', '1644769157'),
(36, 'FADILAHKHOIRUNNISA', 'fadilahkhoirunnisa@student.gunadarma.ac.id', '1644769157'),
(37, 'FAJAR HAMZAH SAPUTRA', 'fajarhamzahsaputra38@gmail.com', '1644769157'),
(38, 'GRAYGENZALESHERMAWAN', 'graygenzalez@student.gunadarma.ac.id', '1644769157'),
(39, 'IMAM BAHY PUTRA SUSETYO', 'imam.12119961@student.gunadarma.ac.id', '1644769157'),
(40, 'INDAH PURNAMA SARI', 'iiindahpurnamasariii@gmail.com', '1644769157'),
(41, 'IRFAN SETIAWAN HUTAGAOL', 'setiawanirfan601@gmail.com', '1644769157'),
(42, 'JOANTIRAANGGARA', 'joantira99@student.gunadarma.ac.id', '1644769157'),
(43, 'LANIHANADERI', 'lanihanaderi@student.gunadarma.ac.id', '1644769157'),
(44, 'LUTHFIRACHMAN', 'luthman@student.gunadarma.ac.id', '1644769157'),
(45, 'MARIO RONGGO KUSUMAWARDHANA', 'marioronggo@gmail.com', '1644769157'),
(46, 'MARTINAANGELAMARYATI', 'martinaangel@student.gunadarma.ac.id', '1644769157'),
(47, 'MIRZAADHIRAJASA', 'mirzaadhirajasa@student.gunadarma.ac.id', '1644769157'),
(48, 'MUHAMMAD FARHAN', 'farhangels248@gmail.com', '1644769157'),
(49, 'MUHAMMAD RIFQI ARKAN', 'Arkanwae@gmail.com', '1644769157'),
(50, 'MUHAMMAD RIYAN NUR RACHMAN', 'riyanrachman@gmail.com', '1644769157'),
(51, 'NUGROHOAGUNGFADILAH', 'nugrohoagung@student.gunadarma.ac.id', '1644769157'),
(52, 'R.SOCOGILANGPAMEKAS', 'socogilang@student.gunadarma.ac.id', '1644769157'),
(53, 'RAFFIIMAMZUHDI', 'raffiimamzuhdi@student.gunadarma.ac.id', '1644769157'),
(54, 'RAVILLAALFAIDAH', 'ravillaalfaidah@student.gunadarma.ac.id', '1644769157'),
(55, 'SANTOJERICHORANTEOKI', 'santojericho29@student.gunadarma.ac.id', '1644769157'),
(56, 'Siti Rokhaila', 'sitirokhaila@gmail.com', '1644769157'),
(57, 'SYAFIQ RIZAL WIBOWO', 'syafikrizal23@gmail.com', '1644769157'),
(58, 'VANDIOROMERIONANTAYAAZIS', 'vandio@student.gunadarma.ac.id', '1644769157'),
(59, 'YUDISTIRA SEPTIA AJI', 'Yudistiraseptiaaji@gmail.com', '1644769157');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_detail_kelas`
--
ALTER TABLE `tb_detail_kelas`
  ADD PRIMARY KEY (`id_detail_kls`),
  ADD KEY `tb_detail_kelas_ibfk_1` (`id_kls`),
  ADD KEY `tb_detail_kelas_ibfk_2` (`id_pst`);

--
-- Indexes for table `tb_instruktur`
--
ALTER TABLE `tb_instruktur`
  ADD PRIMARY KEY (`id_ins`);

--
-- Indexes for table `tb_kelas`
--
ALTER TABLE `tb_kelas`
  ADD PRIMARY KEY (`id_kls`),
  ADD KEY `tb_kelas_ibfk_1` (`id_ins`),
  ADD KEY `tb_kelas_ibfk_2` (`id_mat`);

--
-- Indexes for table `tb_materi`
--
ALTER TABLE `tb_materi`
  ADD PRIMARY KEY (`id_mat`);

--
-- Indexes for table `tb_peserta`
--
ALTER TABLE `tb_peserta`
  ADD PRIMARY KEY (`id_pst`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_detail_kelas`
--
ALTER TABLE `tb_detail_kelas`
  MODIFY `id_detail_kls` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=189;

--
-- AUTO_INCREMENT for table `tb_instruktur`
--
ALTER TABLE `tb_instruktur`
  MODIFY `id_ins` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `tb_kelas`
--
ALTER TABLE `tb_kelas`
  MODIFY `id_kls` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `tb_materi`
--
ALTER TABLE `tb_materi`
  MODIFY `id_mat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `tb_peserta`
--
ALTER TABLE `tb_peserta`
  MODIFY `id_pst` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_detail_kelas`
--
ALTER TABLE `tb_detail_kelas`
  ADD CONSTRAINT `tb_detail_kelas_ibfk_1` FOREIGN KEY (`id_kls`) REFERENCES `tb_kelas` (`id_kls`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_detail_kelas_ibfk_2` FOREIGN KEY (`id_pst`) REFERENCES `tb_peserta` (`id_pst`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_kelas`
--
ALTER TABLE `tb_kelas`
  ADD CONSTRAINT `tb_kelas_ibfk_1` FOREIGN KEY (`id_ins`) REFERENCES `tb_instruktur` (`id_ins`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_kelas_ibfk_2` FOREIGN KEY (`id_mat`) REFERENCES `tb_materi` (`id_mat`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
