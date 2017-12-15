-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 15.12.2017 klo 21:05
-- Palvelimen versio: 10.0.32-MariaDB-0+deb8u1
-- PHP Version: 5.6.30-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dynamoapp`
--

-- --------------------------------------------------------

--
-- Rakenne taululle `review`
--

CREATE TABLE IF NOT EXISTS `review` (
`reviewid` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `place` varchar(70) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Vedos taulusta `review`
--

INSERT INTO `review` (`reviewid`, `score`, `place`) VALUES
(1, 4, 'Ravintola Fiilu'),
(6, 2, 'JAMK Ravintola Bittipannu'),
(7, 4, 'JAMK Ravintola Radis'),
(8, 3, 'JAMK Ravintola Radis'),
(9, 1, 'JAMK Ravintola Radis'),
(10, 3, 'JAMK Ravintola Radis'),
(11, 1, 'JAMK Ravintola Radis'),
(12, 3, 'Ravintola Fiilu'),
(14, 2, 'JAMK Ravintola Radis'),
(16, 4, 'JAMK Ravintola Bittipannu'),
(17, 3, ''),
(18, 5, ''),
(19, 3, 'Ravintola Fiilu'),
(20, 2, 'JAMK Ravintola Bittipannu'),
(21, 5, 'JAMK Ravintola Bittipannu'),
(22, 5, 'Ravintola Fiilu'),
(23, 4, 'Ravintola Fiilu'),
(24, 5, 'Ravintola Fiilu'),
(25, 5, 'Ravintola Fiilu'),
(26, 5, 'Ravintola Fiilu'),
(27, 5, 'Ravintola Fiilu');

-- --------------------------------------------------------

--
-- Rakenne taululle `visit`
--

CREATE TABLE IF NOT EXISTS `visit` (
`visitid` int(11) NOT NULL,
  `place` varchar(70) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `enter_time` varchar(50) NOT NULL,
  `exit_time` varchar(50) NOT NULL,
  `visitor` varchar(300) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=715 DEFAULT CHARSET=latin1;

--
-- Vedos taulusta `visit`
--

INSERT INTO `visit` (`visitid`, `place`, `duration`, `enter_time`, `exit_time`, `visitor`) VALUES
(494, 'JAMK Ravintola Bittipannu', 55, '2017-12-13 13:33:19', '2017-12-13 14:28:39', 'HUAWEI HONOR/PLK-L01/HWPLK:6.0/HONORPLK-L01/C432B390:user/release-keys'),
(496, 'JAMK Ravintola Bittipannu', 8, '2017-12-13 14:29:14', '2017-12-13 14:37:40', 'HUAWEI HONOR/PLK-L01/HWPLK:6.0/HONORPLK-L01/C432B390:user/release-keys'),
(658, 'Ravintola Fiilu', 11, '2017-12-14 12:58:46', '2017-12-14 13:10:17', 'OnePlus OnePlus/OnePlus5/OnePlus5:7.1.1/NMF26X/11141953:user/release-keys'),
(660, 'Ravintola Fiilu', 137, '2017-12-14 13:11:23', '2017-12-14 15:28:52', 'OnePlus OnePlus/OnePlus5/OnePlus5:7.1.1/NMF26X/11141953:user/release-keys'),
(708, 'JAMK Ravintola Bittipannu', 10, '2017-12-15 08:46:42', '2017-12-15 08:56:57', 'HUAWEI HONOR/PLK-L01/HWPLK:6.0/HONORPLK-L01/C432B390:user/release-keys'),
(709, 'JAMK Ravintola Bittipannu', 60, '2017-12-15 08:57:48', '2017-12-15 09:58:14', 'HUAWEI HONOR/PLK-L01/HWPLK:6.0/HONORPLK-L01/C432B390:user/release-keys'),
(710, 'JAMK Ravintola Bittipannu', NULL, '2017-12-15 09:08:57', '', 'OnePlus OnePlus/OnePlus5/OnePlus5:7.1.1/NMF26X/11141953:user/release-keys'),
(711, 'JAMK Ravintola Bittipannu', 0, '2017-12-15 09:58:21', '2017-12-15 09:58:23', 'HUAWEI HONOR/PLK-L01/HWPLK:6.0/HONORPLK-L01/C432B390:user/release-keys'),
(712, 'JAMK Ravintola Bittipannu', NULL, '2017-12-15 09:58:53', '', 'HUAWEI HONOR/PLK-L01/HWPLK:6.0/HONORPLK-L01/C432B390:user/release-keys'),
(713, 'JAMK Ravintola Bittipannu', 0, '2017-12-15 15:00:01', '2017-12-15 15:00:44', 'OnePlus OnePlus/OnePlus5/OnePlus5:7.1.1/NMF26X/10171617:user/release-keys'),
(714, 'JAMK Ravintola Bittipannu', 0, '2017-12-15 15:01:35', '2017-12-15 15:01:44', 'OnePlus OnePlus/OnePlus5/OnePlus5:7.1.1/NMF26X/10171617:user/release-keys');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `review`
--
ALTER TABLE `review`
 ADD PRIMARY KEY (`reviewid`);

--
-- Indexes for table `visit`
--
ALTER TABLE `visit`
 ADD PRIMARY KEY (`visitid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
MODIFY `reviewid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `visit`
--
ALTER TABLE `visit`
MODIFY `visitid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=715;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
