--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-05-22 17:28:59

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

--
-- TOC entry 4977 (class 1262 OID 16388)
-- Name: spotifei; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE spotifei WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'pt-BR';


ALTER DATABASE spotifei OWNER TO postgres;

\connect spotifei

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
-- TOC entry 220 (class 1259 OID 16419)
-- Name: artistas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.artistas (
    id_artista integer NOT NULL,
    nome_artista character varying NOT NULL
);


ALTER TABLE public.artistas OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16418)
-- Name: artistas_id_artista_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.artistas_id_artista_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.artistas_id_artista_seq OWNER TO postgres;

--
-- TOC entry 4978 (class 0 OID 0)
-- Dependencies: 219
-- Name: artistas_id_artista_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.artistas_id_artista_seq OWNED BY public.artistas.id_artista;


--
-- TOC entry 222 (class 1259 OID 16428)
-- Name: generos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.generos (
    id_genero integer NOT NULL,
    nome_genero character varying NOT NULL
);


ALTER TABLE public.generos OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16427)
-- Name: generos_id_genero_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.generos_id_genero_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.generos_id_genero_seq OWNER TO postgres;

--
-- TOC entry 4979 (class 0 OID 0)
-- Dependencies: 221
-- Name: generos_id_genero_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.generos_id_genero_seq OWNED BY public.generos.id_genero;


--
-- TOC entry 230 (class 1259 OID 16508)
-- Name: historico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.historico (
    id_historico integer NOT NULL,
    id_usuario integer NOT NULL,
    texto_pesquisa text NOT NULL,
    data_pesquisa timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.historico OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16507)
-- Name: historico_id_historico_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.historico_id_historico_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.historico_id_historico_seq OWNER TO postgres;

--
-- TOC entry 4980 (class 0 OID 0)
-- Dependencies: 229
-- Name: historico_id_historico_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.historico_id_historico_seq OWNED BY public.historico.id_historico;


--
-- TOC entry 224 (class 1259 OID 16437)
-- Name: musicas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.musicas (
    id_musica integer NOT NULL,
    titulo_musica character varying NOT NULL,
    id_artista integer,
    id_genero integer,
    duracao character varying(5) NOT NULL
);


ALTER TABLE public.musicas OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16436)
-- Name: musicas_id_musica_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.musicas_id_musica_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.musicas_id_musica_seq OWNER TO postgres;

--
-- TOC entry 4981 (class 0 OID 0)
-- Dependencies: 223
-- Name: musicas_id_musica_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.musicas_id_musica_seq OWNED BY public.musicas.id_musica;


--
-- TOC entry 227 (class 1259 OID 16474)
-- Name: playlist_musica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.playlist_musica (
    id_playlist integer NOT NULL,
    id_musica integer NOT NULL
);


ALTER TABLE public.playlist_musica OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16466)
-- Name: playlists; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.playlists (
    id_playlist integer NOT NULL,
    nome_playlist character varying NOT NULL,
    id_usuario integer NOT NULL
);


ALTER TABLE public.playlists OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16465)
-- Name: playlists_id_playlist_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.playlists_id_playlist_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.playlists_id_playlist_seq OWNER TO postgres;

--
-- TOC entry 4982 (class 0 OID 0)
-- Dependencies: 225
-- Name: playlists_id_playlist_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.playlists_id_playlist_seq OWNED BY public.playlists.id_playlist;


--
-- TOC entry 228 (class 1259 OID 16489)
-- Name: usuario_musica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_musica (
    id_usuario integer NOT NULL,
    id_musica integer NOT NULL,
    tipo_reacao character(1) NOT NULL,
    CONSTRAINT ck_usuario_musica_tipo_reacao CHECK ((tipo_reacao = ANY (ARRAY['C'::bpchar, 'D'::bpchar])))
);


ALTER TABLE public.usuario_musica OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16390)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id_usuario integer NOT NULL,
    login_usuario character varying NOT NULL,
    senha_usuario text NOT NULL,
    nome_usuario character varying
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16389)
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuarios_id_usuario_seq OWNER TO postgres;

--
-- TOC entry 4983 (class 0 OID 0)
-- Dependencies: 217
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_id_usuario_seq OWNED BY public.usuarios.id_usuario;


--
-- TOC entry 4776 (class 2604 OID 16422)
-- Name: artistas id_artista; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artistas ALTER COLUMN id_artista SET DEFAULT nextval('public.artistas_id_artista_seq'::regclass);


--
-- TOC entry 4777 (class 2604 OID 16431)
-- Name: generos id_genero; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.generos ALTER COLUMN id_genero SET DEFAULT nextval('public.generos_id_genero_seq'::regclass);


--
-- TOC entry 4780 (class 2604 OID 16511)
-- Name: historico id_historico; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico ALTER COLUMN id_historico SET DEFAULT nextval('public.historico_id_historico_seq'::regclass);


--
-- TOC entry 4778 (class 2604 OID 16440)
-- Name: musicas id_musica; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musicas ALTER COLUMN id_musica SET DEFAULT nextval('public.musicas_id_musica_seq'::regclass);


--
-- TOC entry 4779 (class 2604 OID 16469)
-- Name: playlists id_playlist; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.playlists ALTER COLUMN id_playlist SET DEFAULT nextval('public.playlists_id_playlist_seq'::regclass);


--
-- TOC entry 4775 (class 2604 OID 16393)
-- Name: usuarios id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuarios_id_usuario_seq'::regclass);


--
-- TOC entry 4961 (class 0 OID 16419)
-- Dependencies: 220
-- Data for Name: artistas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.artistas VALUES (1, 'Adele');
INSERT INTO public.artistas VALUES (2, 'The Weeknd');
INSERT INTO public.artistas VALUES (3, 'Taylor Swift');
INSERT INTO public.artistas VALUES (4, 'Arctic Monkeys');
INSERT INTO public.artistas VALUES (5, 'Ludwig van Beethoven');
INSERT INTO public.artistas VALUES (6, 'Anitta');


--
-- TOC entry 4963 (class 0 OID 16428)
-- Dependencies: 222
-- Data for Name: generos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.generos VALUES (1, 'Pop');
INSERT INTO public.generos VALUES (2, 'Rock');
INSERT INTO public.generos VALUES (3, 'Clássico');
INSERT INTO public.generos VALUES (4, 'Funk');
INSERT INTO public.generos VALUES (5, 'Indie');


--
-- TOC entry 4971 (class 0 OID 16508)
-- Dependencies: 230
-- Data for Name: historico; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4965 (class 0 OID 16437)
-- Dependencies: 224
-- Data for Name: musicas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.musicas VALUES (1, 'Hello', 1, 1, '4:55');
INSERT INTO public.musicas VALUES (2, 'Blinding Lights', 2, 1, '03:20');
INSERT INTO public.musicas VALUES (3, 'Shake It Off', 3, 1, '03:39');
INSERT INTO public.musicas VALUES (4, 'Do I Wanna Know?', 4, 2, '04:31');
INSERT INTO public.musicas VALUES (5, 'Sinfonia nº 5', 5, 3, '07:10');
INSERT INTO public.musicas VALUES (6, 'Envolver', 6, 4, '03:13');


--
-- TOC entry 4968 (class 0 OID 16474)
-- Dependencies: 227
-- Data for Name: playlist_musica; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4967 (class 0 OID 16466)
-- Dependencies: 226
-- Data for Name: playlists; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4969 (class 0 OID 16489)
-- Dependencies: 228
-- Data for Name: usuario_musica; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4959 (class 0 OID 16390)
-- Dependencies: 218
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4984 (class 0 OID 0)
-- Dependencies: 219
-- Name: artistas_id_artista_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.artistas_id_artista_seq', 6, true);


--
-- TOC entry 4985 (class 0 OID 0)
-- Dependencies: 221
-- Name: generos_id_genero_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.generos_id_genero_seq', 5, true);


--
-- TOC entry 4986 (class 0 OID 0)
-- Dependencies: 229
-- Name: historico_id_historico_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.historico_id_historico_seq', 23, true);


--
-- TOC entry 4987 (class 0 OID 0)
-- Dependencies: 223
-- Name: musicas_id_musica_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.musicas_id_musica_seq', 6, true);


--
-- TOC entry 4988 (class 0 OID 0)
-- Dependencies: 225
-- Name: playlists_id_playlist_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.playlists_id_playlist_seq', 9, true);


--
-- TOC entry 4989 (class 0 OID 0)
-- Dependencies: 217
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_id_usuario_seq', 19, true);


--
-- TOC entry 4788 (class 2606 OID 16426)
-- Name: artistas artistas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artistas
    ADD CONSTRAINT artistas_pkey PRIMARY KEY (id_artista);


--
-- TOC entry 4790 (class 2606 OID 16435)
-- Name: generos generos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.generos
    ADD CONSTRAINT generos_pkey PRIMARY KEY (id_genero);


--
-- TOC entry 4802 (class 2606 OID 16515)
-- Name: historico historico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico
    ADD CONSTRAINT historico_pkey PRIMARY KEY (id_historico);


--
-- TOC entry 4792 (class 2606 OID 16444)
-- Name: musicas musicas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musicas
    ADD CONSTRAINT musicas_pkey PRIMARY KEY (id_musica);


--
-- TOC entry 4798 (class 2606 OID 16478)
-- Name: playlist_musica playlist_musica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.playlist_musica
    ADD CONSTRAINT playlist_musica_pkey PRIMARY KEY (id_playlist, id_musica);


--
-- TOC entry 4794 (class 2606 OID 16506)
-- Name: playlists playlist_nome_playlist_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.playlists
    ADD CONSTRAINT playlist_nome_playlist_key UNIQUE (nome_playlist);


--
-- TOC entry 4796 (class 2606 OID 16473)
-- Name: playlists playlists_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.playlists
    ADD CONSTRAINT playlists_pkey PRIMARY KEY (id_playlist);


--
-- TOC entry 4804 (class 2606 OID 16523)
-- Name: historico unica_pesquisa; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico
    ADD CONSTRAINT unica_pesquisa UNIQUE (id_usuario, texto_pesquisa);


--
-- TOC entry 4800 (class 2606 OID 16494)
-- Name: usuario_musica usuario_musica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_musica
    ADD CONSTRAINT usuario_musica_pkey PRIMARY KEY (id_usuario, id_musica);


--
-- TOC entry 4784 (class 2606 OID 16399)
-- Name: usuarios usuarios_login_usuario_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_login_usuario_key UNIQUE (login_usuario);


--
-- TOC entry 4786 (class 2606 OID 16397)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 4812 (class 2606 OID 16516)
-- Name: historico FK_historico_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico
    ADD CONSTRAINT "FK_historico_usuario" FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4805 (class 2606 OID 16455)
-- Name: musicas FK_musica_id_artista; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musicas
    ADD CONSTRAINT "FK_musica_id_artista" FOREIGN KEY (id_artista) REFERENCES public.artistas(id_artista) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 4806 (class 2606 OID 16460)
-- Name: musicas FK_musica_id_genero; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musicas
    ADD CONSTRAINT "FK_musica_id_genero" FOREIGN KEY (id_genero) REFERENCES public.generos(id_genero) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 4807 (class 2606 OID 16524)
-- Name: playlists FK_playlist_id_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.playlists
    ADD CONSTRAINT "FK_playlist_id_usuario" FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 4808 (class 2606 OID 16484)
-- Name: playlist_musica FK_playlist_musica_musica; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.playlist_musica
    ADD CONSTRAINT "FK_playlist_musica_musica" FOREIGN KEY (id_musica) REFERENCES public.musicas(id_musica) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4809 (class 2606 OID 16479)
-- Name: playlist_musica FK_playlist_musica_playlist; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.playlist_musica
    ADD CONSTRAINT "FK_playlist_musica_playlist" FOREIGN KEY (id_playlist) REFERENCES public.playlists(id_playlist) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4810 (class 2606 OID 16500)
-- Name: usuario_musica FK_usuario_musica_musica; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_musica
    ADD CONSTRAINT "FK_usuario_musica_musica" FOREIGN KEY (id_musica) REFERENCES public.musicas(id_musica) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4811 (class 2606 OID 16495)
-- Name: usuario_musica FK_usuario_musica_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_musica
    ADD CONSTRAINT "FK_usuario_musica_usuario" FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2025-05-22 17:28:59

--
-- PostgreSQL database dump complete
--

