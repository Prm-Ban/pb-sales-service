--
-- PostgreSQL database dump
--

-- Dumped from database version 11.4
-- Dumped by pg_dump version 11.4

-- Started on 2019-07-24 17:42:36

--SET statement_timeout = 0;
--SET lock_timeout = 0;
--SET idle_in_transaction_session_timeout = 0;
--SET client_encoding = 'UTF8';
--SET standard_conforming_strings = on;
--SELECT pg_catalog.set_config('search_path', '', false);
--SET check_function_bodies = false;
--SET xmloption = content;
--SET client_min_messages = warning;
--SET row_security = off;
--
--SET default_tablespace = '';
--
--SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 18544)
-- Name: cartdetail; Type: TABLE; Schema: public; Owner: dbexerphi_dba
--

CREATE TABLE public.cartdetail (
    id_customer bigint NOT NULL,
    id_item integer NOT NULL,
    item_name character varying(255),
    qty double precision,
    reqnote character varying(255)
);


ALTER TABLE public.cartdetail OWNER TO dbexerphi_dba;

--
-- TOC entry 200 (class 1259 OID 18697)
-- Name: salesorder; Type: TABLE; Schema: public; Owner: dbexerphi_dba
--

CREATE TABLE public.salesorder (
    systemid bigint NOT NULL,
    status_canceled integer NOT NULL,
    status_delivery integer,
    disc double precision,
    disc_memo character varying(255),
    id_customer bigint NOT NULL,
    issuedate timestamp without time zone NOT NULL,
    memo character varying(255),
    misc_charge double precision,
    misc_charge_memo character varying(255),
    status_payment integer,
    promocode_used integer,
    shipping_line integer,
    vat double precision,
    vat_inclusive boolean
);


ALTER TABLE public.salesorder OWNER TO dbexerphi_dba;

--
-- TOC entry 196 (class 1259 OID 18542)
-- Name: salesorder_id_so_seq; Type: SEQUENCE; Schema: public; Owner: dbexerphi_dba
--

CREATE SEQUENCE public.salesorder_id_so_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.salesorder_id_so_seq OWNER TO dbexerphi_dba;

--
-- TOC entry 199 (class 1259 OID 18695)
-- Name: salesorder_systemid_seq; Type: SEQUENCE; Schema: public; Owner: dbexerphi_dba
--

CREATE SEQUENCE public.salesorder_systemid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.salesorder_systemid_seq OWNER TO dbexerphi_dba;

--
-- TOC entry 2831 (class 0 OID 0)
-- Dependencies: 199
-- Name: salesorder_systemid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dbexerphi_dba
--

ALTER SEQUENCE public.salesorder_systemid_seq OWNED BY public.salesorder.systemid;


--
-- TOC entry 198 (class 1259 OID 18560)
-- Name: salesorderitem; Type: TABLE; Schema: public; Owner: dbexerphi_dba
--

CREATE TABLE public.salesorderitem (
    id_item integer NOT NULL,
    parent bigint NOT NULL,
    disc double precision,
    harga_jual double precision,
    item_name character varying(255) NOT NULL,
    qty double precision
);


ALTER TABLE public.salesorderitem OWNER TO dbexerphi_dba;

--
-- TOC entry 2697 (class 2604 OID 18700)
-- Name: salesorder systemid; Type: DEFAULT; Schema: public; Owner: dbexerphi_dba
--

ALTER TABLE ONLY public.salesorder ALTER COLUMN systemid SET DEFAULT nextval('public.salesorder_systemid_seq'::regclass);


--
-- TOC entry 2699 (class 2606 OID 18551)
-- Name: cartdetail cartdetail_pkey; Type: CONSTRAINT; Schema: public; Owner: dbexerphi_dba
--

ALTER TABLE ONLY public.cartdetail
    ADD CONSTRAINT cartdetail_pkey PRIMARY KEY (id_customer, id_item);


--
-- TOC entry 2703 (class 2606 OID 18705)
-- Name: salesorder salesorder_pkey; Type: CONSTRAINT; Schema: public; Owner: dbexerphi_dba
--

ALTER TABLE ONLY public.salesorder
    ADD CONSTRAINT salesorder_pkey PRIMARY KEY (systemid);


--
-- TOC entry 2701 (class 2606 OID 18564)
-- Name: salesorderitem salesorderitem_pkey; Type: CONSTRAINT; Schema: public; Owner: dbexerphi_dba
--

ALTER TABLE ONLY public.salesorderitem
    ADD CONSTRAINT salesorderitem_pkey PRIMARY KEY (id_item, parent);


--
-- TOC entry 2704 (class 2606 OID 18706)
-- Name: salesorderitem salesorderitem_parent_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dbexerphi_dba
--

ALTER TABLE ONLY public.salesorderitem
    ADD CONSTRAINT salesorderitem_parent_fkey FOREIGN KEY (parent) REFERENCES public.salesorder(systemid);


-- Completed on 2019-07-24 17:42:37

--
-- PostgreSQL database dump complete
--

