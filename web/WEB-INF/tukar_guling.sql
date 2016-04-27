/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : tukar_guling

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2016-04-27 17:12:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cetak
-- ----------------------------
DROP TABLE IF EXISTS `cetak`;
CREATE TABLE `cetak` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ttssnya_nomor` bigint(20) DEFAULT NULL,
  `user_login_id` bigint(20) DEFAULT NULL,
  `wkt_cetak` datetime DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `cetakan_ke` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_cetak_ttssnya_1` (`ttssnya_nomor`),
  KEY `ix_cetak_userLogin_2` (`user_login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3342 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cetak
-- ----------------------------

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `shipto` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for printer
-- ----------------------------
DROP TABLE IF EXISTS `printer`;
CREATE TABLE `printer` (
  `nama_printer` varchar(255) NOT NULL,
  `keterangan` varchar(255) DEFAULT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of printer
-- ----------------------------
INSERT INTO `printer` VALUES ('noprint', 'noprint', '16');

-- ----------------------------
-- Table structure for progress
-- ----------------------------
DROP TABLE IF EXISTS `progress`;
CREATE TABLE `progress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no_ttrb` varchar(255) DEFAULT NULL,
  `no_bkb` varchar(255) DEFAULT NULL,
  `tgl_bkb` varchar(255) DEFAULT NULL,
  `kode_outlet` bigint(20) DEFAULT NULL,
  `nama_checker` varchar(255) DEFAULT NULL,
  `waktu_terima_checker` datetime DEFAULT NULL,
  `nama_dir_exp` varchar(255) DEFAULT NULL,
  `waktu_terima_dir_exp` datetime DEFAULT NULL,
  `nama_penerima_kembali` varchar(255) DEFAULT NULL,
  `waktu_kembali` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of progress
-- ----------------------------
INSERT INTO `progress` VALUES ('1', 'asdas', 'asdas', '2016-04-27 17:12:15', '12312', 'SCASD', '2016-04-27 17:12:15', 'asdas', '2016-04-27 17:12:15', 'asdas', '2016-04-27 17:12:15');

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nama_setting` varchar(255) NOT NULL,
  `nilai_setting` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES ('1', 'pdf_path', 'D://output/');
INSERT INTO `setting` VALUES ('2', 'saldo_awal_transfer', '0');
INSERT INTO `setting` VALUES ('3', 'tgl_saldo_awal', '2016-04-01');
INSERT INTO `setting` VALUES ('4', 'saldo_awal_dropping', '0');
INSERT INTO `setting` VALUES ('5', 'smtp_host', '10.100.11.10');
INSERT INTO `setting` VALUES ('6', 'smtp_port', '25');
INSERT INTO `setting` VALUES ('7', 'smtp_username', 'emailnya@enseval.com');
INSERT INTO `setting` VALUES ('8', 'smtp_password', 'password');
INSERT INTO `setting` VALUES ('9', 'email_from', 'app-cashmon.ptk@enseval.com');
INSERT INTO `setting` VALUES ('10', 'email_to', 'epm_12_pontianak@enseval.com');
INSERT INTO `setting` VALUES ('11', 'email_aktif', 'YA');
INSERT INTO `setting` VALUES ('12', 'nama_cabang', 'PONTIANAK');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `akses` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin', 'ADMINISTRATOR');
