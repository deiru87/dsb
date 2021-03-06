CREATE TABLE public.incidents
(
    id bigint NOT NULL,
    incident_date character(100) COLLATE pg_catalog."default" DEFAULT 'phishing'::bpchar,
    incident_date_group character(100) COLLATE pg_catalog."default" DEFAULT 'phishing'::bpchar,
    incident_type character(100) COLLATE pg_catalog."default" DEFAULT 'phishing'::bpchar,
    CONSTRAINT incidents_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.incidents
    OWNER to postgres;
COMMENT ON TABLE public.incidents
    IS 'Table for security incidents';