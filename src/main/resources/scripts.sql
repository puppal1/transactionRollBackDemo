
CREATE TABLE `order_address` (
`order_id` bigint(20) unsigned NOT NULL,
`line_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
`address_id` BIGINT(11) unsigned DEFAULT NULL,
`created_date` datetime NOT NULL,
`modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`line_id`)
) DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;



CREATE TABLE `transaction_log` (
`txn_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
`user_id` bigint(20) unsigned DEFAULT NULL,
`order_id` bigint(20) unsigned DEFAULT NULL,
`transaction_status` varchar(35)  DEFAULT 'SUCCESS',
`failure_reason` varchar(255)  DEFAULT NULL,
`created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`txn_id`)
) DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `user_address` (

`address_id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
`user_id` bigint(20) unsigned NOT NULL,
`first_name` varchar(40) DEFAULT NULL,
`last_name` varchar(40) DEFAULT NULL,
`line1` varchar(255) NOT NULL,
`line2` varchar(35) DEFAULT NULL,
`city` varchar(100) DEFAULT NULL,
`state` varchar(50) NOT NULL,
`zip` varchar(10) DEFAULT NULL,
`country` varchar(20) NOT NULL DEFAULT 'US',
`created_by` int(11) DEFAULT NULL,
`created_date` datetime NOT NULL,
`modified_by` int(11) DEFAULT NULL,
`modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`address_id`)
)DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

