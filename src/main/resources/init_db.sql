USE internet_shop;
CREATE TABLE products
(
    `product_id`    BIGINT(11)     NOT NULL AUTO_INCREMENT,
    `product_name`  VARCHAR(225)   NOT NULL,
    `product_price` DECIMAL(25, 2) NOT NULL,
    PRIMARY KEY (`product_id`)
);
CREATE TABLE `users`
(
    `user_id`         bigint       NOT NULL AUTO_INCREMENT,
    `user_login`      varchar(45)  NOT NULL,
    `user_password`   varchar(500) NOT NULL,
    `user_first_name` varchar(45) DEFAULT NULL,
    `user_last_name`  varchar(45) DEFAULT NULL,
    `user_age`        int         DEFAULT '' 0 '',
    `user_phone`      varchar(45) DEFAULT NULL,
    `user_email`      varchar(45) DEFAULT NULL,
    PRIMARY KEY (`user_id`)
);
CREATE TABLE `roles`
(
    `role_id`   bigint      NOT NULL AUTO_INCREMENT,
    `role_name` varchar(45) NOT NULL,
    PRIMARY KEY (`role_id`)
);
CREATE TABLE `users_roles`
(
    `user_id` bigint NOT NULL,
    `role_id` bigint NOT NULL,
    KEY `user_id_idx` (`user_id`),
    KEY `role_id_idx` (`role_id`),
    CONSTRAINT `URrole_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
    CONSTRAINT `URuser_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);
CREATE TABLE orders
(
    `order_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id`  bigint NOT NULL,
    PRIMARY KEY (`order_id`),
    KEY `order_user_id_idx` (`user_id`),
    CONSTRAINT `Oorder_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);
CREATE TABLE `orders_products`
(
    `order_id`   bigint NOT NULL,
    `product_id` bigint NOT NULL,
    KEY `order_id_idx` (`order_id`),
    KEY `product_id_idx` (`product_id`),
    CONSTRAINT `OPorder_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
    CONSTRAINT `OPproduct_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
);
CREATE TABLE `shopping_carts`
(
    `shopping_cart_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id`          bigint NOT NULL,
    PRIMARY KEY (`shopping_cart_id`),
    KEY `user_id_idx` (`user_id`),
    CONSTRAINT `SCshopping_cart_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);
CREATE TABLE `shopping_carts_products`
(
    `shopping_cart_id` bigint NOT NULL,
    `product_id`       bigint NOT NULL,
    KEY `shopping_cart_id_idx` (`shopping_cart_id`),
    KEY `product_id_idx` (`product_id`),
    CONSTRAINT `SCPproduct_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
    CONSTRAINT `SCPshopping_cart_id` FOREIGN KEY (`shopping_cart_id`) REFERENCES `shopping_carts` (`shopping_cart_id`)
);
ALTER TABLE `internet_shop`.`users`
    ADD COLUMN `salt` VARBINARY(16) NULL AFTER `user_email`;

