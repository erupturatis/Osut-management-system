--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

-- Started on 2024-01-15 13:48:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4835 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 17000)
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department (
    department_name character varying,
    department_description character varying,
    department_id character varying NOT NULL
);


ALTER TABLE public.department OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17071)
-- Name: general_meeting; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.general_meeting (
    general_meeting_id character varying NOT NULL,
    general_meeting_date date
);


ALTER TABLE public.general_meeting OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17022)
-- Name: project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.project (
    project_id character varying NOT NULL,
    project_name character varying,
    project_description character varying,
    project_funding integer,
    department_id character varying
);


ALTER TABLE public.project OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17049)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    role_name character varying,
    role_description character varying,
    role_id character varying NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16993)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    user_id character varying NOT NULL,
    user_password character varying,
    is_admin boolean,
    age integer,
    able_to_work boolean
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 17078)
-- Name: user_attendance; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_attendance (
    user_id character varying,
    general_meeting_id character varying
);


ALTER TABLE public.user_attendance OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17007)
-- Name: user_department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_department (
    user_id character varying,
    department_id character varying,
    status character varying,
    join_date date
);


ALTER TABLE public.user_department OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17034)
-- Name: user_project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_project (
    user_id character varying,
    project_id character varying,
    role_id character varying
);


ALTER TABLE public.user_project OWNER TO postgres;

--
-- TOC entry 4823 (class 0 OID 17000)
-- Dependencies: 216
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.department VALUES ('IT', 'Face it stuff', '1');
INSERT INTO public.department VALUES ('Imagine', 'Creeaza obiecte de imagine pt osut', '2');
INSERT INTO public.department VALUES ('Arte', 'Dedica timpul artelor fine ', '3');
INSERT INTO public.department VALUES ('Afaceri', 'Gestioneaza afacerile cu facultatea si business-uri', '4');
INSERT INTO public.department VALUES ('Travel', 'Se ocupa cu raspandirea osutului in lume', '5');
INSERT INTO public.department VALUES ('Databases', 'Make better databases', '6');


--
-- TOC entry 4828 (class 0 OID 17071)
-- Dependencies: 221
-- Data for Name: general_meeting; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.general_meeting VALUES ('1', '2023-12-31');
INSERT INTO public.general_meeting VALUES ('2', '2023-12-17');
INSERT INTO public.general_meeting VALUES ('3', '2023-12-10');
INSERT INTO public.general_meeting VALUES ('4', '2023-11-26');
INSERT INTO public.general_meeting VALUES ('5', '2023-11-19');
INSERT INTO public.general_meeting VALUES ('6', '2023-11-12');
INSERT INTO public.general_meeting VALUES ('7', '2023-11-05');
INSERT INTO public.general_meeting VALUES ('8', '2023-10-29');
INSERT INTO public.general_meeting VALUES ('9', '2023-10-22');
INSERT INTO public.general_meeting VALUES ('10', '2023-10-15');


--
-- TOC entry 4825 (class 0 OID 17022)
-- Dependencies: 218
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.project VALUES ('1', 'Facebook', 'make facebook app', 1000, '1');
INSERT INTO public.project VALUES ('2', 'AGI', 'race with openAI to agi', 5000, '1');
INSERT INTO public.project VALUES ('3', 'OsutApp', 'make the osut app', 200, '1');
INSERT INTO public.project VALUES ('4', 'Osut shirts', 'design osut shirts', 50, '2');
INSERT INTO public.project VALUES ('5', 'Monalisa', 'make a superior version of monalisa', 200, '3');
INSERT INTO public.project VALUES ('6', 'Negotiate deals', 'negotiate deals with local businesses to sponsor osut', 400, '4');
INSERT INTO public.project VALUES ('7', 'Get subsidies', 'get subsidies from the government', 500, '4');
INSERT INTO public.project VALUES ('8', 'Germany trip', 'make a trip to nuremberg', 750, '5');


--
-- TOC entry 4827 (class 0 OID 17049)
-- Dependencies: 220
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role VALUES ('Treasurer', 'Works with money', '1');
INSERT INTO public.role VALUES ('Secretary', 'Handles calls and people', '2');
INSERT INTO public.role VALUES ('Worker', 'Works on stuff to be done', '3');
INSERT INTO public.role VALUES ('Coordinator', 'Coordinates everyone', '4');
INSERT INTO public.role VALUES ('Senior', 'Handles the thinking behind the organization', '5');


--
-- TOC entry 4822 (class 0 OID 16993)
-- Dependencies: 215
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."user" VALUES ('zefir', 'zefir123', false, 23, true);
INSERT INTO public."user" VALUES ('gicagel', 'aaa', false, 1, true);
INSERT INTO public."user" VALUES ('amumu', 'abc', false, 32, true);
INSERT INTO public."user" VALUES ('evghen', 'eugen123', true, 11, true);
INSERT INTO public."user" VALUES ('calin111', 'calin', false, 52, true);
INSERT INTO public."user" VALUES ('alex', 'alex123', true, 875, true);
INSERT INTO public."user" VALUES ('cevanumebun', 'aa33', false, 10, true);
INSERT INTO public."user" VALUES ('amanda12', 'amanda123', true, 32, false);


--
-- TOC entry 4829 (class 0 OID 17078)
-- Dependencies: 222
-- Data for Name: user_attendance; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_attendance VALUES ('alex', '1');
INSERT INTO public.user_attendance VALUES ('alex', '2');
INSERT INTO public.user_attendance VALUES ('alex', '3');
INSERT INTO public.user_attendance VALUES ('zefir', '3');
INSERT INTO public.user_attendance VALUES ('alex', '4');
INSERT INTO public.user_attendance VALUES ('zefir', '4');
INSERT INTO public.user_attendance VALUES ('alex', '5');
INSERT INTO public.user_attendance VALUES ('alex', '6');
INSERT INTO public.user_attendance VALUES ('alex', '7');
INSERT INTO public.user_attendance VALUES ('alex', '8');
INSERT INTO public.user_attendance VALUES ('alex', '9');
INSERT INTO public.user_attendance VALUES ('zefir', '9');
INSERT INTO public.user_attendance VALUES ('alex', '10');
INSERT INTO public.user_attendance VALUES ('zefir', '10');
INSERT INTO public.user_attendance VALUES ('evghen', '1');
INSERT INTO public.user_attendance VALUES ('evghen', '3');
INSERT INTO public.user_attendance VALUES ('evghen', '4');
INSERT INTO public.user_attendance VALUES ('evghen', '7');
INSERT INTO public.user_attendance VALUES ('amanda12', '1');
INSERT INTO public.user_attendance VALUES ('amanda12', '2');
INSERT INTO public.user_attendance VALUES ('amanda12', '3');
INSERT INTO public.user_attendance VALUES ('amanda12', '7');
INSERT INTO public.user_attendance VALUES ('amanda12', '8');


--
-- TOC entry 4824 (class 0 OID 17007)
-- Dependencies: 217
-- Data for Name: user_department; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_department VALUES ('alex', '1', NULL, NULL);
INSERT INTO public.user_department VALUES ('alex', '2', NULL, NULL);
INSERT INTO public.user_department VALUES ('amumu', '4', NULL, NULL);
INSERT INTO public.user_department VALUES ('amumu', '5', NULL, NULL);
INSERT INTO public.user_department VALUES ('evghen', '4', NULL, NULL);
INSERT INTO public.user_department VALUES ('evghen', '1', NULL, NULL);
INSERT INTO public.user_department VALUES ('evghen', '5', NULL, NULL);
INSERT INTO public.user_department VALUES ('calin111', '4', NULL, NULL);
INSERT INTO public.user_department VALUES ('calin111', '5', NULL, NULL);
INSERT INTO public.user_department VALUES ('calin111', '3', NULL, NULL);
INSERT INTO public.user_department VALUES ('cevanumebun', '1', NULL, NULL);
INSERT INTO public.user_department VALUES ('cevanumebun', '3', NULL, NULL);
INSERT INTO public.user_department VALUES ('cevanumebun', '4', NULL, NULL);
INSERT INTO public.user_department VALUES ('zefir', '2', NULL, NULL);
INSERT INTO public.user_department VALUES ('zefir', '1', NULL, NULL);
INSERT INTO public.user_department VALUES ('amanda12', '1', NULL, NULL);
INSERT INTO public.user_department VALUES ('amanda12', '2', NULL, NULL);
INSERT INTO public.user_department VALUES ('amanda12', '4', NULL, NULL);


--
-- TOC entry 4826 (class 0 OID 17034)
-- Dependencies: 219
-- Data for Name: user_project; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_project VALUES ('alex', '1', NULL);
INSERT INTO public.user_project VALUES ('alex', '2', NULL);
INSERT INTO public.user_project VALUES ('zefir', '7', NULL);
INSERT INTO public.user_project VALUES ('zefir', '1', NULL);
INSERT INTO public.user_project VALUES ('evghen', '5', NULL);
INSERT INTO public.user_project VALUES ('amanda12', '3', NULL);


--
-- TOC entry 4670 (class 2606 OID 17077)
-- Name: general_meeting attendace_date_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.general_meeting
    ADD CONSTRAINT attendace_date_pk PRIMARY KEY (general_meeting_id);


--
-- TOC entry 4664 (class 2606 OID 17006)
-- Name: department departament_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT departament_pk PRIMARY KEY (department_id);


--
-- TOC entry 4666 (class 2606 OID 17028)
-- Name: project projects_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT projects_pk PRIMARY KEY (project_id);


--
-- TOC entry 4668 (class 2606 OID 17055)
-- Name: role role_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pk PRIMARY KEY (role_id);


--
-- TOC entry 4662 (class 2606 OID 16999)
-- Name: user users_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT users_pk PRIMARY KEY (user_id);


--
-- TOC entry 4673 (class 2606 OID 17029)
-- Name: project projects_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT projects_fk FOREIGN KEY (department_id) REFERENCES public.department(department_id);


--
-- TOC entry 4677 (class 2606 OID 17121)
-- Name: user_attendance user_attendance_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_attendance
    ADD CONSTRAINT user_attendance_fk FOREIGN KEY (user_id) REFERENCES public."user"(user_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4678 (class 2606 OID 17088)
-- Name: user_attendance user_attendance_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_attendance
    ADD CONSTRAINT user_attendance_fk_1 FOREIGN KEY (general_meeting_id) REFERENCES public.general_meeting(general_meeting_id);


--
-- TOC entry 4671 (class 2606 OID 17116)
-- Name: user_department user_departaments_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_department
    ADD CONSTRAINT user_departaments_fk FOREIGN KEY (user_id) REFERENCES public."user"(user_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4672 (class 2606 OID 17017)
-- Name: user_department user_departaments_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_department
    ADD CONSTRAINT user_departaments_fk_1 FOREIGN KEY (department_id) REFERENCES public.department(department_id);


--
-- TOC entry 4674 (class 2606 OID 17141)
-- Name: user_project user_project_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_project
    ADD CONSTRAINT user_project_fk FOREIGN KEY (role_id) REFERENCES public.role(role_id);


--
-- TOC entry 4675 (class 2606 OID 17126)
-- Name: user_project user_projects_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_project
    ADD CONSTRAINT user_projects_fk FOREIGN KEY (project_id) REFERENCES public.project(project_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4676 (class 2606 OID 17136)
-- Name: user_project user_projects_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_project
    ADD CONSTRAINT user_projects_fk_1 FOREIGN KEY (user_id) REFERENCES public."user"(user_id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2024-01-15 13:48:27

--
-- PostgreSQL database dump complete
--

