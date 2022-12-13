ALTER TABLE ``.``
ADD COLUMN `tenant_id` CHAR(6) NOT NULL COMMENT '租户id' AFTER `id`,
ADD COLUMN `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
ADD COLUMN `created_by` varchar(64) NOT NULL COMMENT '创建者' AFTER `created_at`,
ADD COLUMN `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' AFTER `created_by`,
ADD COLUMN `updated_by` varchar(64) NOT NULL COMMENT '更新者' AFTER `updated_at`,
ADD COLUMN `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '已删除的' AFTER `updated_by`;