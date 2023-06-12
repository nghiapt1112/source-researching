SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
                             `category_id` bigint(0) NOT NULL,
                             `created_at` bigint(0) NULL DEFAULT NULL,
                             `created_by` bigint(0) NULL DEFAULT NULL,
                             `deleted` bit(1) NULL DEFAULT NULL,
                             `updated_at` bigint(0) NULL DEFAULT NULL,
                             `updated_by` bigint(0) NULL DEFAULT NULL,
                             `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                             PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
    `next_val` bigint(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
                            `product_id` bigint(0) NOT NULL AUTO_INCREMENT,
                            `created_at` bigint(0) NULL DEFAULT NULL,
                            `created_by` bigint(0) NULL DEFAULT NULL,
                            `deleted` bit(1) NULL DEFAULT NULL,
                            `updated_at` bigint(0) NULL DEFAULT NULL,
                            `updated_by` bigint(0) NULL DEFAULT NULL,
                            `branch` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `price` decimal(19, 2) NULL DEFAULT NULL,
                            `category_category_id` bigint(0) NULL DEFAULT NULL,
                            PRIMARY KEY (`product_id`) USING BTREE,
                            INDEX `FK8k3eluikx7psbc4fnp8h616w1`(`category_category_id`) USING BTREE,
                            CONSTRAINT `FK8k3eluikx7psbc4fnp8h616w1` FOREIGN KEY (`category_category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;
