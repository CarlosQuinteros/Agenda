PGDMP     %    +    
            {            agenda    13.9    13.9     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16395    agenda    DATABASE     f   CREATE DATABASE agenda WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Argentina.1252';
    DROP DATABASE agenda;
                agenda    false            �            1259    16398    contacto    TABLE     �   CREATE TABLE public.contacto (
    id bigint NOT NULL,
    imagen_url character varying(255),
    titulo character varying(255) NOT NULL,
    empresa_id bigint NOT NULL,
    persona_id bigint NOT NULL
);
    DROP TABLE public.contacto;
       public         heap    agenda    false            �            1259    16396    contacto_id_seq    SEQUENCE     x   CREATE SEQUENCE public.contacto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.contacto_id_seq;
       public          agenda    false    201            �           0    0    contacto_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.contacto_id_seq OWNED BY public.contacto.id;
          public          agenda    false    200            �            1259    16409    empresa    TABLE     �   CREATE TABLE public.empresa (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    imagen_url character varying(255),
    nombre character varying(255) NOT NULL,
    sitio_web character varying(255) NOT NULL
);
    DROP TABLE public.empresa;
       public         heap    agenda    false            �            1259    16407    empresa_id_seq    SEQUENCE     w   CREATE SEQUENCE public.empresa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.empresa_id_seq;
       public          agenda    false    203            �           0    0    empresa_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.empresa_id_seq OWNED BY public.empresa.id;
          public          agenda    false    202            �            1259    16420    persona    TABLE       CREATE TABLE public.persona (
    id bigint NOT NULL,
    apellido character varying(255) NOT NULL,
    ciudad character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    nombre character varying(255) NOT NULL,
    telefono character varying(255) NOT NULL
);
    DROP TABLE public.persona;
       public         heap    agenda    false            �            1259    16418    persona_id_seq    SEQUENCE     w   CREATE SEQUENCE public.persona_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.persona_id_seq;
       public          agenda    false    205            �           0    0    persona_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.persona_id_seq OWNED BY public.persona.id;
          public          agenda    false    204            1           2604    16401    contacto id    DEFAULT     j   ALTER TABLE ONLY public.contacto ALTER COLUMN id SET DEFAULT nextval('public.contacto_id_seq'::regclass);
 :   ALTER TABLE public.contacto ALTER COLUMN id DROP DEFAULT;
       public          agenda    false    200    201    201            2           2604    16412 
   empresa id    DEFAULT     h   ALTER TABLE ONLY public.empresa ALTER COLUMN id SET DEFAULT nextval('public.empresa_id_seq'::regclass);
 9   ALTER TABLE public.empresa ALTER COLUMN id DROP DEFAULT;
       public          agenda    false    203    202    203            3           2604    16423 
   persona id    DEFAULT     h   ALTER TABLE ONLY public.persona ALTER COLUMN id SET DEFAULT nextval('public.persona_id_seq'::regclass);
 9   ALTER TABLE public.persona ALTER COLUMN id DROP DEFAULT;
       public          agenda    false    205    204    205            �          0    16398    contacto 
   TABLE DATA           R   COPY public.contacto (id, imagen_url, titulo, empresa_id, persona_id) FROM stdin;
    public          agenda    false    201          �          0    16409    empresa 
   TABLE DATA           K   COPY public.empresa (id, email, imagen_url, nombre, sitio_web) FROM stdin;
    public          agenda    false    203   �       �          0    16420    persona 
   TABLE DATA           P   COPY public.persona (id, apellido, ciudad, email, nombre, telefono) FROM stdin;
    public          agenda    false    205   �       �           0    0    contacto_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.contacto_id_seq', 6, true);
          public          agenda    false    200            �           0    0    empresa_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.empresa_id_seq', 7, true);
          public          agenda    false    202            �           0    0    persona_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.persona_id_seq', 18, true);
          public          agenda    false    204            5           2606    16406    contacto contacto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.contacto
    ADD CONSTRAINT contacto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.contacto DROP CONSTRAINT contacto_pkey;
       public            agenda    false    201            7           2606    16417    empresa empresa_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.empresa DROP CONSTRAINT empresa_pkey;
       public            agenda    false    203            9           2606    16428    persona persona_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_pkey;
       public            agenda    false    205            ;           2606    16434 $   contacto fkhqage4jxeg2p8dfkssscjdvwx    FK CONSTRAINT     �   ALTER TABLE ONLY public.contacto
    ADD CONSTRAINT fkhqage4jxeg2p8dfkssscjdvwx FOREIGN KEY (persona_id) REFERENCES public.persona(id);
 N   ALTER TABLE ONLY public.contacto DROP CONSTRAINT fkhqage4jxeg2p8dfkssscjdvwx;
       public          agenda    false    201    205    2873            :           2606    16429 $   contacto fkmn823afhtvwqlv8n1t036gfwx    FK CONSTRAINT     �   ALTER TABLE ONLY public.contacto
    ADD CONSTRAINT fkmn823afhtvwqlv8n1t036gfwx FOREIGN KEY (empresa_id) REFERENCES public.empresa(id);
 N   ALTER TABLE ONLY public.contacto DROP CONSTRAINT fkmn823afhtvwqlv8n1t036gfwx;
       public          agenda    false    201    203    2871            �   �   x�%�1�0 ���\�����qpv11M)X,-�@��;��W�kY�tb����=���Q�ihv�u$ܨ{+��G݊UE��R�?�M�z��$��P����u�t�-χ��gSV�E@@b%@�W�S ��e6d�.�w� ��c?N1�      �   �  x��RMo� <�_�SO��I;��n�]���(R"����cV\ �s�o��"�R.{���fx e�bu�~��]�ܹ�>@hv���h�][���8Z�$AL�QH7�����K�Q�d�0��}�UT�/�U 3!%��V�<Ym�X&d����)����{+؍�A]��5H����[��Iq#V�"���P�j���0���n���^H��u|�x��[���G�@ۇ���ډph19;��:�,��j�1n�T�\�a���g'���V��U�\k.����IBX�3S��|���\à����6	�P���_�����7zK�JA����� �ܱ|��\&EӞK�.7<���\J��¥��瓁��>���vO7����o͹\6[�~99]��ƻ��|0l�M��x�/p}g�~��[��9>|�����      �   *  x���MO�0��ί�/�����`!�`g.^k�Tm�zٯ'i��IC"�%o��y-+�\ �6_���j���cݡk��;x��Ke��r�����ع3r3
9��w�t����̀�wR2�-j#c�1+�:#�:�<K�h�",�'z=P�a��<�&Xؔ@�aշ�
�M�s~pc==8Y�q	�a��q�ؑk"�����/�nj�^�U���GbƘH�q�]�P��*U�Wn�����rT�v���J[Z�EN���-c,o�ĘlO��R*!`7�?��8y,��8E��i,�6ZZ�Y!~���h     