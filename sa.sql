-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2021-03-27 10:47:54
-- 伺服器版本： 10.4.14-MariaDB
-- PHP 版本： 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `sa`
--

-- --------------------------------------------------------

--
-- 資料表結構 `coupon`
--

CREATE TABLE `coupon` (
  `id` int(10) NOT NULL,
  `sn` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` int(10) NOT NULL,
  `id_OrderGet` int(10) NOT NULL,
  `id_Member` int(10) NOT NULL,
  `id_OrderUsed` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 資料表結構 `cup`
--

CREATE TABLE `cup` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `image` varchar(50) NOT NULL,
  `price` int(10) NOT NULL,
  `quantity` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `cup`
--

INSERT INTO `cup` (`id`, `name`, `image`, `price`, `quantity`) VALUES
(1, '天氣晴', 'sunny.png', 30, 100),
(2, '曙光', 'sunshine.png', 30, 100),
(3, '水漾森林', 'forest.png', 30, 100),
(4, '炙熱的愛', 'love.png', 30, 100);

-- --------------------------------------------------------

--
-- 資料表結構 `member`
--

CREATE TABLE `member` (
  `id` int(3) NOT NULL,
  `phoneNumber` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `registerDate` datetime DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `member`
--

INSERT INTO `member` (`id`, `phoneNumber`, `name`, `password`, `registerDate`, `status`) VALUES
(1, '0987654321', '管理者', '12345678', NULL, 0),
(2, '0900111111', '勇妹', '00000000', '2021-01-06 23:53:56', 1),
(3, '0911222333', '老王', '00000000', '2021-01-06 23:53:56', 1),
(4, '0900222333', '郭育慈', '00000000', '2021-01-06 23:53:56', 1),
(5, '0977888999', '品婷', '00000000', '2021-01-06 23:53:56', 1),
(6, '0955121000', '阿黃', '00000000', '2021-01-06 23:53:56', 1);

-- --------------------------------------------------------

--
-- 資料表結構 `orderdetails`
--

CREATE TABLE `orderdetails` (
  `id` int(10) NOT NULL,
  `id_Order` int(10) NOT NULL,
  `id_Product` int(10) NOT NULL,
  `specification_Product` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantity_Product` int(10) NOT NULL,
  `price_Product` int(10) NOT NULL,
  `id_Cup` varchar(10) DEFAULT NULL,
  `quantity_Cup` int(10) DEFAULT NULL,
  `price_Cup` int(10) DEFAULT NULL,
  `price_Coupon` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 資料表結構 `orders`
--

CREATE TABLE `orders` (
  `id` int(10) NOT NULL,
  `dateOforder` datetime DEFAULT NULL,
  `stateOforder` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_Member` int(10) NOT NULL,
  `phoneNumber_Member` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sn_Coupon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表結構 `products`
--

CREATE TABLE `products` (
  `id` int(3) NOT NULL,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` int(3) NOT NULL,
  `image` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `image`, `description`) VALUES
(1, '紅茶', 30, 'BlackTea.png', '這是紅茶'),
(2, '麥茶', 30, 'MaiTea.png', '這是麥茶'),
(3, '青草茶', 35, 'GrassTea.png', '這是青草茶'),
(4, '烏龍綠茶', 35, 'Oolong.png', '這是烏龍綠茶'),
(5, '四季春青茶', 35, 'SeasonTea.png', '這是四季春青茶'),
(6, '紅茶拿鐵', 55, 'BlackTeaMilk.png', '這是紅茶拿鐵'),
(7, '可可鮮奶', 55, 'CocoaMilk.png', '這是可可鮮奶'),
(8, '抹茶鮮奶', 60, 'MachaMilk.png', '這是抹茶鮮奶'),
(9, '芋頭鮮奶', 60, 'TaroMilk.png', '這是芋頭鮮奶');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `sn` (`sn`);

--
-- 資料表索引 `cup`
--
ALTER TABLE `cup`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `coupon`
--
ALTER TABLE `coupon`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `cup`
--
ALTER TABLE `cup`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `member`
--
ALTER TABLE `member`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `orderdetails`
--
ALTER TABLE `orderdetails`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `products`
--
ALTER TABLE `products`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
