DROP TABLE IF EXISTS public.post;

CREATE TABLE public.post (
    id uuid NOT NULL,
    author character varying(255),
    comments jsonb,
    content character varying(255),
    post_date timestamp(6) without time zone,
    title character varying(255)
);

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (id HASH);

