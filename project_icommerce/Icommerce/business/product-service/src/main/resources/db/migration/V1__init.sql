SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
                             `id` bigint(0) NOT NULL AUTO_INCREMENT,
                             `created_at` bigint(0) NULL DEFAULT NULL,
                             `created_by` bigint(0) NULL DEFAULT NULL,
                             `deleted` bit(1) NULL DEFAULT NULL,
                             `updated_at` bigint(0) NULL DEFAULT NULL,
                             `updated_by` bigint(0) NULL DEFAULT NULL,
                             `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 1637258928618, -1, b'0', NULL, NULL, 'Clothing');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
                            `id` bigint(0) NOT NULL AUTO_INCREMENT,
                            `created_at` bigint(0) NULL DEFAULT NULL,
                            `created_by` bigint(0) NULL DEFAULT NULL,
                            `deleted` bit(1) NULL DEFAULT NULL,
                            `updated_at` bigint(0) NULL DEFAULT NULL,
                            `updated_by` bigint(0) NULL DEFAULT NULL,
                            `branch` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `colour` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `price` decimal(19, 2) NULL DEFAULT NULL,
                            `category_id` bigint(0) NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `FK7l29ekt1x29jup80y2iigimyy`(`category_id`) USING BTREE,
                            CONSTRAINT `FK7l29ekt1x29jup80y2iigimyy` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 1637258928619, -1, b'0', NULL, NULL, 'branch-8', NULL, 'Description - 8', 'Hoddies', 200.00, 1);
INSERT INTO `product` VALUES (2, 1637258928619, -1, b'0', NULL, NULL, 'branch-10', NULL, 'Description - 10', 'Hoddies', 250.00, 1);
INSERT INTO `product` VALUES (3, 1637258928619, -1, b'0', NULL, NULL, 'branch-4', NULL, 'Description - 4', 'Hoddies', 100.00, 1);
INSERT INTO `product` VALUES (4, 1637258928619, -1, b'0', NULL, NULL, 'branch-1', NULL, 'Description - 1', 'Hoddies', 25.00, 1);
INSERT INTO `product` VALUES (5, 1637258928619, -1, b'0', NULL, NULL, 'branch-6', NULL, 'Description - 6', 'Hoddies', 150.00, 1);
INSERT INTO `product` VALUES (6, 1637258928619, -1, b'0', NULL, NULL, 'branch-7', NULL, 'Description - 7', 'Hoddies', 175.00, 1);
INSERT INTO `product` VALUES (7, 1637258928619, -1, b'0', NULL, NULL, 'branch-3', NULL, 'Description - 3', 'Hoddies', 75.00, 1);
INSERT INTO `product` VALUES (8, 1637258928619, -1, b'0', NULL, NULL, 'branch-9', NULL, 'Description - 9', 'Hoddies', 225.00, 1);
INSERT INTO `product` VALUES (9, 1637258928619, -1, b'0', NULL, NULL, 'branch-2', NULL, 'Description - 2', 'Hoddies', 50.00, 1);
INSERT INTO `product` VALUES (10, 1637258928619, -1, b'0', NULL, NULL, 'branch-5', NULL, 'Description - 5', 'Hoddies', 125.00, 1);

SET FOREIGN_KEY_CHECKS = 1;