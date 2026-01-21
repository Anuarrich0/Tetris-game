--
-- PostgreSQL database dump
--

\restrict R1uultNzagoZzP6c7dl39D1PbBxOD7ZGtbxCqgXNluKntCPvEfD1YOOnGk4AgA3

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

-- Started on 2026-01-21 18:58:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 16775)
-- Name: leaderboards; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.leaderboards (
    id integer NOT NULL,
    user_id integer,
    mode character varying(20) NOT NULL,
    score integer NOT NULL,
    time_taken double precision,
    played_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.leaderboards OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16774)
-- Name: leaderboards_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.leaderboards_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.leaderboards_id_seq OWNER TO postgres;

--
-- TOC entry 5028 (class 0 OID 0)
-- Dependencies: 221
-- Name: leaderboards_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.leaderboards_id_seq OWNED BY public.leaderboards.id;


--
-- TOC entry 220 (class 1259 OID 16762)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16761)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 5029 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 4863 (class 2604 OID 16778)
-- Name: leaderboards id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.leaderboards ALTER COLUMN id SET DEFAULT nextval('public.leaderboards_id_seq'::regclass);


--
-- TOC entry 4861 (class 2604 OID 16765)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 5022 (class 0 OID 16775)
-- Dependencies: 222
-- Data for Name: leaderboards; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.leaderboards (id, user_id, mode, score, time_taken, played_at) FROM stdin;
1	3	3	200	108.289	2026-01-19 23:46:27.844816
2	3	1	400	129.963	2026-01-19 23:51:11.841651
3	3	3	58	54.469	2026-01-19 23:56:33.070109
4	3	Zen	0	0	2026-01-20 02:45:08.638856
5	3	Zen	4118	0	2026-01-20 02:56:33.699727
6	3	Zen	0	0	2026-01-20 03:06:05.07058
7	3	Zen	184	0	2026-01-20 12:04:09.443868
8	3	Zen	184	0	2026-01-20 12:04:11.684273
9	3	Zen	224	0	2026-01-20 12:43:16.954796
10	3	Zen	9	0	2026-01-20 12:54:47.380546
11	3	Zen	0	0	2026-01-20 12:56:19.3898
12	3	Zen	0	0	2026-01-20 13:05:59.642866
13	3	Zen	101	0	2026-01-20 13:15:32.601673
14	3	Zen	58	0	2026-01-20 13:24:05.502127
15	3	Zen	120	0	2026-01-21 18:08:52.800095
16	3	Zen	651	0	2026-01-21 18:13:29.121652
17	3	Zen	78	0	2026-01-21 18:50:26.189983
\.


--
-- TOC entry 5020 (class 0 OID 16762)
-- Dependencies: 220
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, username, password, created_at) FROM stdin;
2	Anuar2008	12344321	2026-01-19 14:11:38.08933
3	Maxat	1234	2026-01-19 14:24:42.524167
\.


--
-- TOC entry 5030 (class 0 OID 0)
-- Dependencies: 221
-- Name: leaderboards_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.leaderboards_id_seq', 17, true);


--
-- TOC entry 5031 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 3, true);


--
-- TOC entry 4870 (class 2606 OID 16784)
-- Name: leaderboards leaderboards_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.leaderboards
    ADD CONSTRAINT leaderboards_pkey PRIMARY KEY (id);


--
-- TOC entry 4866 (class 2606 OID 16771)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4868 (class 2606 OID 16773)
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- TOC entry 4871 (class 2606 OID 16785)
-- Name: leaderboards leaderboards_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.leaderboards
    ADD CONSTRAINT leaderboards_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2026-01-21 18:58:27

--
-- PostgreSQL database dump complete
--

\unrestrict R1uultNzagoZzP6c7dl39D1PbBxOD7ZGtbxCqgXNluKntCPvEfD1YOOnGk4AgA3

