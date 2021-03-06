--CREATE SCHEMA IF NOT EXISTS SPECIFICATION ;
SET SCHEMA SPECIFICATION ;
/*
INSERT INTO farukon_resource (id, created_by, created_date, last_modified_by, last_modified_date, version, resource) VALUES (1, null, null, null, '2021-06-03 11:15:33', 1, '/**');
INSERT INTO farukon_role (id, created_by, created_date, last_modified_by, last_modified_date, version, rolecode, rolename) VALUES (1, null, null, null, '2021-06-03 11:15:33', 1, 'admin', 'admin');
INSERT INTO farukon_role (id, created_by, created_date, last_modified_by, last_modified_date, version, rolecode, rolename) VALUES (2, null, '2021-06-09 16:11:38', null, null, 0, '34324', '4324324');
INSERT INTO farukon_role (id, created_by, created_date, last_modified_by, last_modified_date, version, rolecode, rolename) VALUES (4, null, '2021-06-09 16:12:20', null, null, 0, '23423', '32424');
INSERT INTO farukon_role (id, created_by, created_date, last_modified_by, last_modified_date, version, rolecode, rolename) VALUES (3, null, '2021-06-09 16:12:03', null, '2021-06-09 17:02:14', 3, '45334543', '4354359');
INSERT INTO farukon_role_ref_resource (roleid, resourceid) VALUES (1, 1);
INSERT INTO farukon_user (id, created_by, created_date, last_modified_by, last_modified_date, version, password, username, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (3, null, '2021-06-03 13:46:26', null, null, 0, '$2a$10$DU0agxKxdhlvJ6PrikgEReSBu0ilknW3TXeYce1u0oboqz1CPFjmW', '2131244', true, true, true, true);
INSERT INTO farukon_user (id, created_by, created_date, last_modified_by, last_modified_date, version, password, username, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (4, null, '2021-06-03 15:25:35', null, null, 0, '$2a$10$1FyUo6vT/BUD3f2CNSE10erR7SkZN73Cv.TehzmobdH09nS9aWaZi', '21321321', true, true, true, true);
INSERT INTO farukon_user (id, created_by, created_date, last_modified_by, last_modified_date, version, password, username, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (5, null, '2021-06-04 15:44:33', null, null, 0, '$2a$10$/Gz/SjigcArHnSNtV5REJ.QzQXBcpgIurKXZCRwe2.82Vyaotqr8a', 'fuser7', true, true, true, true);
INSERT INTO farukon_user (id, created_by, created_date, last_modified_by, last_modified_date, version, password, username, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (2, null, '2021-06-03 13:46:16', null, null, 0, '$2a$10$nk3OCV8Szg0ojEPZ4eQAYOAskDI3SYU.ddwf1w.ZuTe7beNtRTTam', '21312', true, true, true, true);
INSERT INTO farukon_user (id, created_by, created_date, last_modified_by, last_modified_date, version, password, username, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (7, null, '2021-06-08 15:28:44', null, null, 0, '$2a$10$1IxC06IGfa5ZNqdXMvehoe98BOE0fQJA2O1uwJs5B9acz0IOJ4y8u', '324324324324', true, true, true, false);
INSERT INTO farukon_user (id, created_by, created_date, last_modified_by, last_modified_date, version, password, username, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (1, null, '2021-06-03 11:15:32', null, null, 0, '$2a$10$EUdk7Ca1uOKO7figdSDTmuQXQCrvdrXQPfXpvsMbhaG1jxgHrwmqa', 'admin', true, true, true, true);
INSERT INTO farukon_user (id, created_by, created_date, last_modified_by, last_modified_date, version, password, username, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (15, null, '2021-06-10 16:09:02', null, null, 0, '$2a$10$TXIJIDUyLV.vnz9e7DXUvuBQlD18A.n2FLHbS1q0kqxz4XuPj8Pa6', '20210610User002', true, true, true, true);
INSERT INTO farukon_role_ref_user (userid, roleid) VALUES (1, 1);
INSERT INTO system_role (id, created_by, created_date, last_modified_by, last_modified_date, version, authorize_role_id) VALUES (1, null, null, null, '2021-06-03 11:15:33', 1, 1);
INSERT INTO system_role (id, created_by, created_date, last_modified_by, last_modified_date, version, authorize_role_id) VALUES (2, null, '2021-06-09 16:11:38', null, null, 0, 2);
INSERT INTO system_role (id, created_by, created_date, last_modified_by, last_modified_date, version, authorize_role_id) VALUES (4, null, '2021-06-09 16:12:20', null, null, 0, 4);
INSERT INTO system_role (id, created_by, created_date, last_modified_by, last_modified_date, version, authorize_role_id) VALUES (3, null, '2021-06-09 16:12:03', null, '2021-06-09 17:02:14', 3, 3);
INSERT INTO system_user (id, created_by, created_date, last_modified_by, last_modified_date, version, nickname, authorize_user_id) VALUES (1, null, '2021-06-03 11:15:32', null, null, 2, 'admin11111', 1);
INSERT INTO system_user (id, created_by, created_date, last_modified_by, last_modified_date, version, nickname, authorize_user_id) VALUES (2, null, '2021-06-03 13:46:16', null, null, 0, '213213', 2);
INSERT INTO system_user (id, created_by, created_date, last_modified_by, last_modified_date, version, nickname, authorize_user_id) VALUES (3, null, '2021-06-03 13:46:26', null, '2021-06-03 13:46:34', 1, '21', 3);
INSERT INTO system_user (id, created_by, created_date, last_modified_by, last_modified_date, version, nickname, authorize_user_id) VALUES (4, null, '2021-06-03 15:25:35', null, '2021-06-03 15:25:42', 1, '21321', 4);
INSERT INTO system_user (id, created_by, created_date, last_modified_by, last_modified_date, version, nickname, authorize_user_id) VALUES (5, null, '2021-06-04 15:44:33', null, null, 0, null, 5);
INSERT INTO system_user (id, created_by, created_date, last_modified_by, last_modified_date, version, nickname, authorize_user_id) VALUES (7, null, '2021-06-08 15:28:44', null, null, 0, '32432143214', 7);
INSERT INTO system_user (id, created_by, created_date, last_modified_by, last_modified_date, version, nickname, authorize_user_id) VALUES (15, null, '2021-06-10 16:09:02', null, null, 0, '20210610User002', 15);
INSERT INTO system_role_ref_user (userid, roleid) VALUES (1, 1);
INSERT INTO system_role_ref_user (userid, roleid) VALUES (2, 1);
*/
