DROP DATABASE IF EXISTS `task_list`;
CREATE DATABASE `task_list` DEFAULT CHARSET utf8 COLLATE utf8_bin;
GRANT ALL PRIVILEGES ON `task_list`.* TO task_list@localhost IDENTIFIED BY 'パスワード';