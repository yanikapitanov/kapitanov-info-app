CREATE USER info_bot_user WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE dbs_kapitanov_info TO info_bot_user;
CREATE SCHEMA scm_info_app AUTHORIZATION info_bot_user;
GRANT ALL PRIVILEGES ON SCHEMA scm_info_app TO info_bot_user;
