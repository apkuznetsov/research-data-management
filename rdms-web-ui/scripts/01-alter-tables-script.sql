CREATE TABLE users
(
 "UserId"	serial	NOT NULL,
 "Email"	text	NOT NULL,
 "Password"	text	NOT NULL,
 "Surname"	text	NOT NULL,
 "Forename"	text	NOT NULL,
	
 CONSTRAINT "PK_User" PRIMARY KEY ( "UserId" )
);

ALTER TABLE users ADD CONSTRAINT constraint_unique_user_email UNIQUE ("Email");

CREATE TABLE user_experiments
(
 "UserExperimentsId"  serial  NOT NULL,
 "UserId"             serial  NOT NULL,
 "ExperimentId"       serial  NOT NULL,
	
 CONSTRAINT "PK_User_Experiments" PRIMARY KEY ( "UserExperimentsId" ),

 CONSTRAINT "FK_User_Experiments_UserId" FOREIGN KEY ( "UserId" ) 
	REFERENCES users ( "UserId" ),
 CONSTRAINT "FK_User_Experiments_ExperimentId" FOREIGN KEY ( "ExperimentId" ) 
	REFERENCES experiments ( "ExperimentId" )
);

ALTER TABLE Sensors ADD COLUMN "Name" text NOT NULL UNIQUE;
ALTER TABLE Experiments ADD COLUMN "Name" text NOT NULL UNIQUE;
ALTER TABLE Tests ADD COLUMN "Name" text NOT NULL UNIQUE;
ALTER TABLE Processings ADD COLUMN "Name" text NOT NULL UNIQUE;

ALTER TABLE Sensors ADD CONSTRAINT constraint_unique_sensor_name UNIQUE ("Name");
ALTER TABLE Experiments ADD CONSTRAINT constraint_unique_experiment_name UNIQUE ("Name");
ALTER TABLE Tests ADD CONSTRAINT constraint_unique_test_name UNIQUE ("Name");
ALTER TABLE Processings ADD CONSTRAINT constraint_unique_processing_name UNIQUE ("Name");

ALTER TABLE datatypes ALTER COLUMN "Metadata" DROP NOT NULL;

ALTER TABLE experiments DROP "CreatedAt";
ALTER TABLE experiments ADD COLUMN "CreatedAt" timestamp with time zone;

ALTER TABLE experiment_sensors ALTER COLUMN "ExperimentId" SET DATA TYPE int;
ALTER TABLE experiment_sensors DROP "ExperimentSensorId";
ALTER TABLE experiment_sensors ADD COLUMN "ExperimentSensorId" SERIAL PRIMARY KEY;

ALTER TABLE test_storage_files ADD COLUMN "IsInput" boolean;
ALTER TABLE storage_files DROP COLUMN "IsInput";
ALTER TABLE processed_data DROP COLUMN "Metadata";