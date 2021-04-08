#drop table enroll;
#drop table grade;
#drop table subject;
#drop table task;
#drop table user;
#drop database mladb;

#Create DB
CREATE DATABASE mladb;
USE mladb;

#USER
CREATE TABLE USER (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_name VARCHAR(200) NOT NULL,
  password VARCHAR(200) NOT NULL,
  user_type VARCHAR(200) NOT NULL,
  first_name VARCHAR(200) NOT NULL,
  last_name VARCHAR(200) NOT NULL,
  email_id VARCHAR(200) NOT NULL,
  telephone VARCHAR(200) NOT NULL,
  alias_mail_id VARCHAR(200),
  address VARCHAR(200),
  skype_id VARCHAR(200)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#SUBJECT
CREATE TABLE SUBJECT (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  description VARCHAR(200) NOT NULL,
  subject_term VARCHAR(200),
  subject_type VARCHAR(200) NOT NULL,
  mailing_alias VARCHAR(200),
  instructor INT NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  start_time VARCHAR(200) NOT NULL,
  end_time VARCHAR(200) NOT NULL,
  duration INT,
  audio_enabled VARCHAR(200),
  video_enabled VARCHAR(200),
  FOREIGN KEY (instructor) REFERENCES USER(ID)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#ENROLL
CREATE TABLE ENROLL (
    STUDENT_ID INT NOT NULL,
    SUBJECT_ID INT NOT NULL,
	UNIQUE (STUDENT_ID, SUBJECT_ID),
    FOREIGN KEY (STUDENT_ID) REFERENCES USER(ID),
	FOREIGN KEY (SUBJECT_ID) REFERENCES SUBJECT(ID)
);

#Task
CREATE TABLE TASK (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  subject INT NOT NULL,
  topic VARCHAR(200) NOT NULL,
  description VARCHAR(200) NOT NULL,
  instructor INT NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  start_time VARCHAR(200) NOT NULL,
  end_time VARCHAR(200) NOT NULL,
  repeat_task VARCHAR(200) NOT NULL,
  audio_enabled VARCHAR(200),
  video_enabled VARCHAR(200),
  FOREIGN KEY (subject) REFERENCES SUBJECT(ID),
  FOREIGN KEY (instructor) REFERENCES USER(ID)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#Grade
CREATE TABLE GRADE (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  grade VARCHAR(200),
  subject INT NOT NULL,
  instructor INT NOT NULL,
  student INT NOT NULL,  
  task INT NOT NULL,
  FOREIGN KEY (subject) REFERENCES SUBJECT(ID),
  FOREIGN KEY (instructor) REFERENCES USER(ID),
  FOREIGN KEY (student) REFERENCES USER(ID),
  FOREIGN KEY (task) REFERENCES TASK(ID)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
 