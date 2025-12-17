CREATE TABLE users (
	created_at timestamp(6) NOT NULL,
	updated_at timestamp(6) NOT NULL,
	id uuid NOT NULL,
	email varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	role varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT uk_users_email UNIQUE (email),
    CONSTRAINT uk_users_username UNIQUE (username),
	CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'PROVIDER'::character varying, 'CUSTOMER'::character varying, 'DEFAULT'::character varying])::text[])))
);

CREATE TABLE service_type (
	duration int4 NOT NULL,
	price numeric(38, 2) NOT NULL,
	created_at timestamp(6) NOT NULL,
	updated_at timestamp(6) NOT NULL,
	id uuid NOT NULL,
	description varchar(255) NULL,
	name varchar(255) NOT NULL,
	CONSTRAINT service_type_pkey PRIMARY KEY (id)
);

CREATE TABLE appointment (
	created_by_ai bool NOT NULL DEFAULT false,
	created_at timestamp(6) NOT NULL,
	end_time timestamp(6) NOT NULL,
	start_time timestamp(6) NOT NULL,
	updated_at timestamp(6) NOT NULL,
	creator_user_id uuid NULL,
	customer_id uuid NOT NULL,
	id uuid NOT NULL,
	service_type_id uuid NOT NULL,
	status varchar(255) NOT NULL,
	CONSTRAINT appointment_pkey PRIMARY KEY (id),
	CONSTRAINT appointment_status_check CHECK (((status)::text = ANY ((ARRAY['CONFIRMED'::character varying, 'FINISHED'::character varying, 'CANCELLED'::character varying])::text[]))),
	CONSTRAINT fki71h8mcr2o9jv5mgpjtf2spuc FOREIGN KEY (customer_id) REFERENCES users(id),
	CONSTRAINT fkm0kldwkdlioorrtjf504moa2y FOREIGN KEY (creator_user_id) REFERENCES users(id),
	CONSTRAINT fks3yieb1gxfqp3bdwq6224ktf2 FOREIGN KEY (service_type_id) REFERENCES service_type(id)
);