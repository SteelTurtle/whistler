drop database if exists whistler_api_dev;
create database whistler_api_dev encoding 'UTF-8' lc_collate 'en_US.UTF-8' lc_ctype 'en_US.UTF-8' template template0 owner whistler_db_service;

\c whistler_api_dev

create schema whistler_schema authorization whistler_db_service;
set search_path to whistler_schema;