CREATE TABLE IF NOT EXISTS employee (
    "employee_id" int NOT NULL,
    "employee_name" character varying(256) NULL,
    CONSTRAINT employee_pkey PRIMARY KEY ("employee_id")
);