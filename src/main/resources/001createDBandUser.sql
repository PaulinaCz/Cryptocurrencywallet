CREATE DATABASE IF NOT EXISTS cryptocurrenctapp;
CREATE USER IF NOT EXISTS 'cryptocurrenctapp' IDENTIFIED BY 'cryptocurrenctapp';
GRANT ALL PRIVILEGES ON cryptocurrenctapp.* TO 'cryptocurrenctapp';

CREATE TABLE `role` (
                        `id` bigint(20) NOT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `first_name` varchar(255) DEFAULT NULL,
                        `surename` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
);


CREATE TABLE `users_roles` (
                               `user_id` bigint(20) NOT NULL,
                               `role_id` bigint(20) NOT NULL,
                               KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
                               KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`),
                               CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                               CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);
