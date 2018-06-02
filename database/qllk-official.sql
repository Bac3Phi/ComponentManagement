﻿-- MySQL Script generated by MySQL Workbench
<<<<<<< HEAD
-- Sat Jun  2 12:54:57 2018
=======
-- Sat Jun  2 20:30:28 2018
>>>>>>> ComponentManager
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema database-qllk
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema database-qllk
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `database-qllk` DEFAULT CHARACTER SET utf8 ;
USE `database-qllk` ;

-- -----------------------------------------------------
-- Table `database-qllk`.`LOAIMATHANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`LOAIMATHANG` (
  `MaLoai` VARCHAR(10) NOT NULL,
  `TenLoai` NVARCHAR(45) NOT NULL,
  PRIMARY KEY (`MaLoai`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`KHO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`KHO` (
  `MaKhu` VARCHAR(4) NOT NULL,
  `TenKhu` NVARCHAR(45) NULL,
  PRIMARY KEY (`MaKhu`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`DONVITINH`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`DONVITINH` (
  `MaDV` VARCHAR(10) NOT NULL,
  `TenDV` NVARCHAR(45) NULL,
  PRIMARY KEY (`MaDV`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`MATHANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`MATHANG` (
  `MaMH` VARCHAR(10) NOT NULL,
  `TenMH` NVARCHAR(45) NOT NULL,
  `HangSX` NVARCHAR(45) NULL,
  `MaDV` VARCHAR(10) NULL,
  `CauHinh` NVARCHAR(100) NULL,
  `HinhMH` NVARCHAR(30) NULL,
  `MaLoai` VARCHAR(10) NULL,
  `MaKhu` VARCHAR(4) NULL,
  `SoLuong` INT NULL,
  PRIMARY KEY (`MaMH`),
  INDEX `MaLoai_idx` (`MaLoai` ASC),
  INDEX `MaKhu_idx` (`MaKhu` ASC),
  INDEX `MaDV_idx` (`MaDV` ASC),
  CONSTRAINT `MaLoai`
    FOREIGN KEY (`MaLoai`)
    REFERENCES `database-qllk`.`LOAIMATHANG` (`MaLoai`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `MaKhu`
    FOREIGN KEY (`MaKhu`)
    REFERENCES `database-qllk`.`KHO` (`MaKhu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `MaDV`
    FOREIGN KEY (`MaDV`)
    REFERENCES `database-qllk`.`DONVITINH` (`MaDV`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`PHONGBAN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`PHONGBAN` (
  `MaPhong` VARCHAR(10) NOT NULL,
  `TenPhong` NVARCHAR(45) NOT NULL,
  PRIMARY KEY (`MaPhong`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`NHANVIEN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`NHANVIEN` (
  `MaNV` VARCHAR(10) NOT NULL,
  `TenNV` NVARCHAR(45) NOT NULL,
  `Phai` NVARCHAR(4) NULL,
  `MaPhong` VARCHAR(10) NULL,
  PRIMARY KEY (`MaNV`),
  INDEX `MaPhong_idx` (`MaPhong` ASC),
  CONSTRAINT `MaPhong`
    FOREIGN KEY (`MaPhong`)
    REFERENCES `database-qllk`.`PHONGBAN` (`MaPhong`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`KHACHHANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`KHACHHANG` (
  `MaKH` VARCHAR(10) NOT NULL,
  `TenKH` NVARCHAR(45) NOT NULL,
  `DiaChi` NVARCHAR(60) NULL,
  `Email` VARCHAR(40) NULL,
  `SoDT` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`MaKH`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`HOADON`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`HOADON` (
  `MaHD` VARCHAR(10) NOT NULL,
  `NgayLap` DATETIME NULL,
  `MaSoThue` VARCHAR(45) NULL,
  `TienThanhToan` DECIMAL NOT NULL,
  `LoaiHD` VARCHAR(7) NOT NULL,
  `MaNV` VARCHAR(10) NULL,
  `MaKH` VARCHAR(10) NULL,
  PRIMARY KEY (`MaHD`),
  INDEX `MaNV_idx` (`MaNV` ASC),
  INDEX `MaKH_idx` (`MaKH` ASC),
  CONSTRAINT `MaNV`
    FOREIGN KEY (`MaNV`)
    REFERENCES `database-qllk`.`NHANVIEN` (`MaNV`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `MaKH`
    FOREIGN KEY (`MaKH`)
    REFERENCES `database-qllk`.`KHACHHANG` (`MaKH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`CHITIETHOADON`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`CHITIETHOADON` (
  `MaCTHD` VARCHAR(10) NOT NULL,
  `DonGiaBan` DECIMAL NOT NULL,
  `SoLuong` INT NULL,
  `MaHD` VARCHAR(10) NULL,
  `MaMH` VARCHAR(10) NULL,
  PRIMARY KEY (`MaCTHD`),
  INDEX `MaHD_idx` (`MaHD` ASC),
  INDEX `MaMH_idx` (`MaMH` ASC),
  CONSTRAINT `MaHD`
    FOREIGN KEY (`MaHD`)
    REFERENCES `database-qllk`.`HOADON` (`MaHD`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `MaMH`
    FOREIGN KEY (`MaMH`)
    REFERENCES `database-qllk`.`MATHANG` (`MaMH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`NHACUNGCAP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`NHACUNGCAP` (
  `MaNCC` VARCHAR(10) NOT NULL,
  `TenNCC` NVARCHAR(50) NULL,
  `DiaChiNCC` NVARCHAR(50) NULL,
  `EmailNCC` VARCHAR(40) NULL,
  `SoDTNCC` VARCHAR(15) NULL,
  PRIMARY KEY (`MaNCC`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`DONDATHANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`DONDATHANG` (
  `MaDDH` VARCHAR(10) NOT NULL,
  `NgayLap` DATETIME NULL,
  `MaNV` VARCHAR(10) NULL,
  `MaNCC` VARCHAR(10) NULL,
  PRIMARY KEY (`MaDDH`),
  INDEX `MaNV_idx` (`MaNV` ASC),
  INDEX `MaNCC_idx` (`MaNCC` ASC),
  CONSTRAINT `MaNV`
    FOREIGN KEY (`MaNV`)
    REFERENCES `database-qllk`.`NHANVIEN` (`MaNV`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `MaNCC`
    FOREIGN KEY (`MaNCC`)
    REFERENCES `database-qllk`.`NHACUNGCAP` (`MaNCC`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`CHITIETDONDATHANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`CHITIETDONDATHANG` (
  `MaCTDDH` VARCHAR(10) NOT NULL,
  `DonGia` VARCHAR(45) NULL,
  `SoLuong` INT NULL,
  `MaDDH` VARCHAR(10) NULL,
  `MaMH` VARCHAR(10) NULL,
  PRIMARY KEY (`MaCTDDH`),
  INDEX `MaDDH_idx` (`MaDDH` ASC),
  INDEX `MaMH_idx` (`MaMH` ASC),
  CONSTRAINT `MaDDH`
    FOREIGN KEY (`MaDDH`)
    REFERENCES `database-qllk`.`DONDATHANG` (`MaDDH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `MaMH`
    FOREIGN KEY (`MaMH`)
    REFERENCES `database-qllk`.`MATHANG` (`MaMH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`PHANQUYEN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`PHANQUYEN` (
  `MaPQ` VARCHAR(10) NOT NULL,
  `TenPQ` NVARCHAR(45) NULL,
  PRIMARY KEY (`MaPQ`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`DANGNHAP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`DANGNHAP` (
  `MaDN` VARCHAR(10) NOT NULL,
  `TenHienThi` NVARCHAR(30) NULL,
  `Username` VARCHAR(100) NULL,
  `Password` VARCHAR(20) NULL,
  `MaPQ` VARCHAR(10) NULL,
  PRIMARY KEY (`MaDN`),
  INDEX `Username_idx` (`Username` ASC),
  INDEX `MaPQ_idx` (`MaPQ` ASC),
  CONSTRAINT `MaPQ`
    FOREIGN KEY (`MaPQ`)
    REFERENCES `database-qllk`.`PHANQUYEN` (`MaPQ`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Username`
    FOREIGN KEY (`Username`)
    REFERENCES `database-qllk`.`NHANVIEN` (`MaNV`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`PHIEUNHAPHANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`PHIEUNHAPHANG` (
  `MaPN` VARCHAR(10) NOT NULL,
  `NgayLapPhieu` DATETIME NULL,
  `MaNV` VARCHAR(10) NULL,
  `TongTienPN` DECIMAL NULL,
<<<<<<< HEAD
  PRIMARY KEY (`MaPN`),
  INDEX `MaNV_idx` (`MaNV` ASC),
  CONSTRAINT `MaNV`
=======
  `MaDDH` VARCHAR(10) NULL,
  PRIMARY KEY (`MaPN`),
  INDEX `MaNV_idx` (`MaNV` ASC),
  INDEX `MaDDH_idx` (`MaDDH` ASC),
  CONSTRAINT `fk_PHIEUNHAPHANG_NHANVIEN`
>>>>>>> ComponentManager
    FOREIGN KEY (`MaNV`)
    REFERENCES `database-qllk`.`NHANVIEN` (`MaNV`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PHIEUNHAPHANG_DONDATHANG`
    FOREIGN KEY (`MaDDH`)
    REFERENCES `database-qllk`.`DONDATHANG` (`MaDDH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`CHITIETPHIEUNHAP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`CHITIETPHIEUNHAP` (
  `MaCTPN` VARCHAR(10) NOT NULL,
  `SoLuong` INT NULL,
  `DonGia` VARCHAR(45) NULL,
  `DonGiaBan` VARCHAR(45) NULL,
  `GhiChu` VARCHAR(45) NULL,
  `MaPN` VARCHAR(10) NULL,
  `TongTien` DECIMAL NULL,
<<<<<<< HEAD
  PRIMARY KEY (`MaCTPN`),
  INDEX `MaPN_idx` (`MaPN` ASC),
  INDEX `MaDDH_idx` (`MaDDH` ASC),
  CONSTRAINT `MaDDH`
    FOREIGN KEY (`MaDDH`)
    REFERENCES `database-qllk`.`DONDATHANG` (`MaDDH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `MaPN`
    FOREIGN KEY (`MaPN`)
    REFERENCES `database-qllk`.`PHIEUNHAPHANG` (`MaPN`)
    ON DELETE NO ACTION
=======
  `MaMH` VARCHAR(10) NULL,
  PRIMARY KEY (`MaCTPN`),
  INDEX `MaPN_idx` (`MaPN` ASC),
  INDEX `MaMH_idx` (`MaMH` ASC),
  CONSTRAINT `fk_CHITIETPHIEUNHAP_PHIEUNHAPHANG`
    FOREIGN KEY (`MaPN`)
    REFERENCES `database-qllk`.`PHIEUNHAPHANG` (`MaPN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CHITIETPHIEUNHAP_MATHANG`
    FOREIGN KEY (`MaMH`)
    REFERENCES `database-qllk`.`MATHANG` (`MaMH`)
    ON DELETE NO ACTION
>>>>>>> ComponentManager
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`BAOCAOHANGTON`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`BAOCAOHANGTON` (
  `MaBCHT` VARCHAR(10) NOT NULL,
  `TonDau` INT NULL,
  `TonCuoi` INT NULL,
  `MaCTPN` VARCHAR(10) NULL,
  `MaKhu` VARCHAR(4) NULL,
  `MaHD` VARCHAR(10) NULL,
  `MaNV` VARCHAR(10) NULL,
  PRIMARY KEY (`MaBCHT`),
  INDEX `MaCTPN_idx` (`MaCTPN` ASC),
  INDEX `MaHD_idx` (`MaHD` ASC),
  INDEX `MaKhu_idx` (`MaKhu` ASC),
  INDEX `MaNV_idx` (`MaNV` ASC),
  CONSTRAINT `MaCTPN`
    FOREIGN KEY (`MaCTPN`)
    REFERENCES `database-qllk`.`CHITIETPHIEUNHAP` (`MaCTPN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `MaHD`
    FOREIGN KEY (`MaHD`)
    REFERENCES `database-qllk`.`HOADON` (`MaHD`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
<<<<<<< HEAD
  CONSTRAINT `MaKhu`
=======
  CONSTRAINT `fk_BAOCAOHANGTON_KHO`
>>>>>>> ComponentManager
    FOREIGN KEY (`MaKhu`)
    REFERENCES `database-qllk`.`KHO` (`MaKhu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `MaNV`
    FOREIGN KEY (`MaNV`)
    REFERENCES `database-qllk`.`NHANVIEN` (`MaNV`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database-qllk`.`BAOCAOTHUCHI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-qllk`.`BAOCAOTHUCHI` (
  `MaBCTC` VARCHAR(10) NOT NULL,
  `NgayLap` DATETIME NULL,
  `TongThu` DECIMAL NULL,
  `TongChi` DECIMAL NULL,
  `MaNV` VARCHAR(10) NULL,
  `TuyChon` VARCHAR(45) NULL,
  `ThoiGian` VARCHAR(8) NULL,
  PRIMARY KEY (`MaBCTC`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


<<<<<<< HEAD
=======

>>>>>>> ComponentManager
SELECT * FROM `database-qllk`.khachhang;
insert into `database-qllk`.khachhang values ('KH001', N'Đặng Hoàng Long', N'123 Đường ABC', N'long97@gmail.com', '0123456789');
insert into `database-qllk`.khachhang values ('KH002', N'Phạm Nhật Phi', N'3 Đường số 2', N'phipn@gmail.com', '0903123456');

SELECT * FROM `database-qllk`.phongban;
insert into `database-qllk`.PHONGBAN values ('KT001', N'Bộ phận kế toán');
insert into `database-qllk`.PHONGBAN values ('KD002', N'Bộ phận kinh doanh');
insert into `database-qllk`.PHONGBAN values ('TK003', N'Bộ phận thủ kho');
INSERT INTO `database-qllk`.PHONGBAN VALUES ('GD004', N'Ban Giám đốc');

SELECT * FROM `database-qllk`.nhanvien;
insert into `database-qllk`.NHANVIEN values ('NV002', N'Trần Văn A', N'Nam', 'KT001');
insert into `database-qllk`.NHANVIEN values ('NV001', N'Phạm Thị Minh', N'Nữ', 'KD002');
insert into `database-qllk`.NHANVIEN values ('NV003', N'Đặng Văn Thành', N'Nam', 'GD004');
insert into `database-qllk`.NHANVIEN values ('NV004', N'Nguyễn Thị Trang', N'Nữ', 'TK003');

SELECT * FROM `database-qllk`.NHACUNGCAP;
INSERT INTO `database-qllk`.NHACUNGCAP VALUES('NCC01', N'Công Ty TNHH Tin học Đông Phúc', N'147 ĐƯỜNG BA CU', 'DONGPHUC@GMAIL.COM', '0978456812');
INSERT INTO `database-qllk`.NHACUNGCAP VALUES('NCC02', N'Công Ty TNHH Nam Thông Bảo', N'258 ĐƯỜNG LÊ DUẨN', 'NAMTHONGBAO@GMAIL.COM', '0903456200');
INSERT INTO `database-qllk`.NHACUNGCAP VALUES('NCC03', N'Công Ty TNHH TM DV Tin Học Hoàng Khang', N'369 ĐƯỜNG HOA SỮA', 'HOANGKHANG@GMAIL.COM', '09039994533');
INSERT INTO `database-qllk`.NHACUNGCAP VALUES('NCC04', N'Công Ty TNHH TM & DV Nhật Tân Tín', N'357 ĐƯỜNG VÕ THỊ SÁU', 'NHATTANTIN@GMAIL.COM', '01228041103');
INSERT INTO `database-qllk`.NHACUNGCAP VALUES('NCC05', N'Công Ty TNHH MTV Tên Lửa Số', N'159 ĐƯỜNG ĐINH TIÊN HOÀNG', 'TENLUASO@GMAIL.COM', '01227456666');
INSERT INTO `database-qllk`.NHACUNGCAP VALUES('NCC06', N'Công Ty TNHH Vi Tính Tiến Phát', N'123 ĐƯỜNG LÊ VĂN SỈ', 'TIENPHAT@GMAIL.COM', '01697542311');

SELECT * FROM `database-qllk`.PHANQUYEN;
insert into `database-qllk`.PHANQUYEN values ('PQ001', N'Admin');
insert into `database-qllk`.PHANQUYEN values ('PQ002', N'Kế Toán');
insert into `database-qllk`.PHANQUYEN values ('PQ003', N'Kinh doanh');
insert into `database-qllk`.PHANQUYEN values ('PQ004', N'Thủ kho');

SELECT * FROM `database-qllk`.DANGNHAP;
INSERT INTO `database-qllk`.DANGNHAP VALUES ('DN001', N'Bộ phận kế toán', 'NV002', '123', 'PQ002');
INSERT INTO `database-qllk`.DANGNHAP VALUES ('DN002', N'Bộ phận kinh doanh', 'NV001', '456', 'PQ003');
INSERT INTO `database-qllk`.DANGNHAP VALUES ('DN003', N'Bộ phận thủ kho', 'NV004', 'ABC', 'PQ004');
INSERT INTO `database-qllk`.DANGNHAP VALUES ('DN004', N'ADMIN Giám đốc', 'NV003', 'xyz', 'PQ001');

SELECT * FROM `database-qllk`.LOAIMATHANG;
INSERT INTO `database-qllk`.LOAIMATHANG VALUES ('RA001', N'RAM MÁY TÍNH');
INSERT INTO `database-qllk`.LOAIMATHANG VALUES ('CA002', N'CARD ĐỒ HỌA');
INSERT INTO `database-qllk`.LOAIMATHANG VALUES ('PC003', N'MÀN HÌNH MÁY TÍNH');
INSERT INTO `database-qllk`.LOAIMATHANG VALUES ('KB004', N'BÀN PHÍM MÁY TÍNH');

SELECT * FROM `database-qllk`.MATHANG;

SELECT * FROM `database-qllk`.BAOCAOHANGTON;

SELECT * FROM `database-qllk`.BAOCAOTHUCHI;

SELECT * FROM `database-qllk`.DONDATHANG;

SELECT * FROM `database-qllk`.CHITIETDONDATHANG;

SELECT * FROM `database-qllk`.HOADON;

SELECT * FROM `database-qllk`.CHITIETHOADON;

SELECT * FROM `database-qllk`.PHIEUNHAPHANG;

SELECT * FROM `database-qllk`.CHITIETPHIEUNHAP;

SELECT * FROM `database-qllk`.PHIEUXUATHANG;

SELECT * FROM `database-qllk`.CHITIETPHIEUXUAT;

SELECT * FROM `database-qllk`.HANGTONKHO;