-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2018 at 12:31 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qllk`
--

-- --------------------------------------------------------

--
-- Table structure for table `banhang`
--

CREATE TABLE `banhang` (
  `maHD` varchar(4) NOT NULL,
  `MHID` varchar(4) NOT NULL,
  `SOLUONG` int(11) DEFAULT NULL,
  `HOADON_HDID` varchar(4) NOT NULL,
  `MATHANG_MHID` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `dathang`
--

CREATE TABLE `dathang` (
  `DONHANGID` varchar(4) NOT NULL,
  `MHID` varchar(4) NOT NULL,
  `SOLUONG` int(11) DEFAULT NULL,
  `DONHANG_DONHANGID` varchar(4) NOT NULL,
  `MATHANG_MHID` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `DONHANGID` varchar(4) NOT NULL,
  `NGAYLAP` date DEFAULT NULL,
  `NVID` varchar(6) DEFAULT NULL,
  `NCCID` varchar(5) DEFAULT NULL,
  `NHACUNGCAP_NCCID` varchar(5) NOT NULL,
  `NHANVIEN_NVID` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `maHD` varchar(4) NOT NULL,
  `maKH` varchar(4) DEFAULT NULL,
  `NGAYLAP` date DEFAULT NULL,
  `NVID` varchar(4) DEFAULT NULL,
  `MASOTHUE` varchar(15) DEFAULT NULL,
  `SOTIENTT` varchar(12) DEFAULT NULL,
  `KHACHHANG_KHID` varchar(4) NOT NULL,
  `NHANVIEN_NVID` varchar(6) NOT NULL,
  `NHANVIEN_PHONGBAN_PBID` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `maKH` varchar(4) NOT NULL,
  `TENKH` varchar(50) DEFAULT NULL,
  `DIACHI` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `SODIENTHOAI` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `mathang`
--

CREATE TABLE `mathang` (
  `MHID` varchar(4) NOT NULL,
  `TENHANG` varchar(45) DEFAULT NULL,
  `DONVITINH` varchar(10) DEFAULT NULL,
  `DONGIA` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `NCCID` varchar(5) NOT NULL,
  `TENNCC` varchar(50) DEFAULT NULL,
  `DIACHINCC` varchar(50) DEFAULT NULL,
  `EMAILNCC` varchar(45) DEFAULT NULL,
  `SODIENTHOAINCC` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `NVID` varchar(6) NOT NULL,
  `TENNV` varchar(45) DEFAULT NULL,
  `PBID` varchar(4) DEFAULT NULL,
  `PHONGBAN_PBID` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `phongban`
--

CREATE TABLE `phongban` (
  `PBID` varchar(4) NOT NULL,
  `TENPB` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `banhang`
--
ALTER TABLE `banhang`
  ADD PRIMARY KEY (`maHD`,`MHID`,`HOADON_HDID`,`MATHANG_MHID`),
  ADD KEY `fk_BANHANG_HOADON_idx` (`HOADON_HDID`),
  ADD KEY `fk_BANHANG_MATHANG1_idx` (`MATHANG_MHID`);

--
-- Indexes for table `dathang`
--
ALTER TABLE `dathang`
  ADD PRIMARY KEY (`DONHANGID`,`MHID`,`DONHANG_DONHANGID`,`MATHANG_MHID`),
  ADD KEY `fk_DATHANG_DONHANG1_idx` (`DONHANG_DONHANGID`),
  ADD KEY `fk_DATHANG_MATHANG1_idx` (`MATHANG_MHID`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`DONHANGID`,`NHACUNGCAP_NCCID`,`NHANVIEN_NVID`),
  ADD KEY `fk_DONHANG_NHACUNGCAP1_idx` (`NHACUNGCAP_NCCID`),
  ADD KEY `fk_DONHANG_NHANVIEN1_idx` (`NHANVIEN_NVID`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`maHD`,`KHACHHANG_KHID`,`NHANVIEN_NVID`,`NHANVIEN_PHONGBAN_PBID`),
  ADD KEY `fk_HOADON_KHACHHANG1_idx` (`KHACHHANG_KHID`),
  ADD KEY `fk_HOADON_NHANVIEN1_idx` (`NHANVIEN_NVID`,`NHANVIEN_PHONGBAN_PBID`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`maKH`);

--
-- Indexes for table `mathang`
--
ALTER TABLE `mathang`
  ADD PRIMARY KEY (`MHID`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`NCCID`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`NVID`,`PHONGBAN_PBID`),
  ADD KEY `fk_NHANVIEN_PHONGBAN1_idx` (`PHONGBAN_PBID`);

--
-- Indexes for table `phongban`
--
ALTER TABLE `phongban`
  ADD PRIMARY KEY (`PBID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `banhang`
--
ALTER TABLE `banhang`
  ADD CONSTRAINT `fk_BANHANG_HOADON` FOREIGN KEY (`HOADON_HDID`) REFERENCES `hoadon` (`maHD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_BANHANG_MATHANG1` FOREIGN KEY (`MATHANG_MHID`) REFERENCES `mathang` (`MHID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `dathang`
--
ALTER TABLE `dathang`
  ADD CONSTRAINT `fk_DATHANG_DONHANG1` FOREIGN KEY (`DONHANG_DONHANGID`) REFERENCES `donhang` (`DONHANGID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_DATHANG_MATHANG1` FOREIGN KEY (`MATHANG_MHID`) REFERENCES `mathang` (`MHID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `fk_DONHANG_NHACUNGCAP1` FOREIGN KEY (`NHACUNGCAP_NCCID`) REFERENCES `nhacungcap` (`NCCID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_DONHANG_NHANVIEN1` FOREIGN KEY (`NHANVIEN_NVID`) REFERENCES `nhanvien` (`NVID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `fk_HOADON_KHACHHANG1` FOREIGN KEY (`KHACHHANG_KHID`) REFERENCES `khachhang` (`maKH`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_HOADON_NHANVIEN1` FOREIGN KEY (`NHANVIEN_NVID`,`NHANVIEN_PHONGBAN_PBID`) REFERENCES `nhanvien` (`NVID`, `PHONGBAN_PBID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `fk_NHANVIEN_PHONGBAN1` FOREIGN KEY (`PHONGBAN_PBID`) REFERENCES `phongban` (`PBID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
