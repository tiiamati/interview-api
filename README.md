# interview-api

Collection postman:
https://www.getpostman.com/collections/92fb93ec2671de229234


# database (neeeeeeeed to improve)
create database interviews

CREATE INTERVIEW
create table interview (id int auto_increment, 
id_employee int, 
id_interviewee int, 
important boolean, 
date date, 
annotation varchar(999), 
timestamp timestamp not null default current_timestamp,  
primary key (id), 
CONSTRAINT id_employee FOREIGN KEY (id_employee) REFERENCES employee (id), 
CONSTRAINT id_interviewee FOREIGN KEY (id_interviewee) REFERENCES interviewee (id)) 
engine = innoDB;

CREATE EMPLOYEE
create table employee (id int auto_increment, 
name varchar(50) not null, 
document_number varchar(20), 
timestamp timestamp not null default current_timestamp, 
primary key (id)) 
engine=innodb;

CREATE INTERVIEWEE
create table interviewee (id int auto_increment, 
name varchar(50) not null, 
cellphone varchar(10), 
email varchar(100), 
curriculum varchar(255), 
city varchar(30), 
date_of_birth varchar(10), 
time_worked varchar(4), 
linkedin varchar(255), 
timestamp timestamp not null default current_timestamp, 
primary key (id)) 
engine = innoDB;
