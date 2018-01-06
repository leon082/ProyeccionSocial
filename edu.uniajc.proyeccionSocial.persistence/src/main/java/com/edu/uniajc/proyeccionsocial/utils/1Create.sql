/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  rlara
 * Created: 26/11/2017
 */

-- Generado por Oracle SQL Developer Data Modeler 17.3.0.261.1529
--   en:        2017-11-26 16:15:23 COT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



CREATE TABLE tb_beneficiario (
    id_beneficiario   NUMBER(10) NOT NULL,
    id_proyecto       NUMBER(10) NOT NULL,
    id_tercero        NUMBER(10) NOT NULL,
    estado            NUMBER(1) DEFAULT 1 NOT NULL,
    observacion       VARCHAR2(300),
    creadopor         VARCHAR2(50) NOT NULL,
    creadoen          DATE DEFAULT SYSDATE,
    modificadopor     VARCHAR2(50),
    modificadoen      DATE
);
CREATE SEQUENCE  "SQ_TB_BENEFICIARIO"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;


CREATE TABLE tb_listavalor (
    id_listavalor   NUMBER(10) NOT NULL,
    agrupacion      VARCHAR2(50) NOT NULL,
    descripcion     VARCHAR2(100) NOT NULL,
    estado          NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor       VARCHAR2(50) NOT NULL,
    creadoen        DATE DEFAULT SYSDATE,
    modificadopor   VARCHAR2(50),
    modificadoen    DATE
);

CREATE TABLE tb_listavalordetalle (
    id_listavalordetalle   NUMBER(10) NOT NULL,
    id_listavalor          NUMBER(10) NOT NULL,
    valor                  VARCHAR2(100) NOT NULL,
    estado                 NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor              VARCHAR2(50) NOT NULL,
    creadoen               DATE DEFAULT SYSDATE,
    modificadopor          VARCHAR2(50),
    modificadoen           DATE
);
CREATE TABLE tb_etapa (
    id_etapa        NUMBER(10) NOT NULL,
    descripcion     VARCHAR2(100) NOT NULL,
    estado          NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor       VARCHAR2(50) NOT NULL,
    creadoen        DATE DEFAULT SYSDATE,
    modificadopor   VARCHAR2(50),
    modificadoen    DATE
);
CREATE SEQUENCE  "SQ_TB_ETAPA"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;

CREATE TABLE tb_servicioetapa (
    id_servicioetapa   NUMBER(10) NOT NULL,
    id_servicio        NUMBER(10) NOT NULL,
    id_etapa           NUMBER(10) NOT NULL,
    estado             NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor          VARCHAR2(50) NOT NULL,
    creadoen           DATE DEFAULT SYSDATE,
    modificadopor      VARCHAR2(50),
    modificadoen       DATE
);
CREATE SEQUENCE  "SQ_TB_SERVICIOETAPA"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;

CREATE TABLE tb_servicio (
    id_servicio     NUMBER(10) NOT NULL,
    descripcion     VARCHAR2(100) NOT NULL,
    estado          NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor       VARCHAR2(50) NOT NULL,
    creadoen        DATE DEFAULT SYSDATE,
    modificadopor   VARCHAR2(50),
    modificadoen    DATE
);
CREATE SEQUENCE  "SQ_TB_SERVICIO"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
CREATE TABLE tb_oferente (
    id_oferente     NUMBER(10) NOT NULL,
    id_proyecto     NUMBER(10) NOT NULL,
    id_tercero      NUMBER(10) NOT NULL,
    estado          NUMBER(1) DEFAULT 1 NOT NULL,
    observacion     VARCHAR2(300),
    creadopor       VARCHAR2(50) NOT NULL,
    creadoen        DATE DEFAULT SYSDATE,
    modificadopor   VARCHAR2(50),
    modificadoen    DATE
);
CREATE SEQUENCE  "SQ_TB_OFERENTE"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;

CREATE TABLE tb_programa (
    id_programa     NUMBER(10) NOT NULL,
    descripcion     VARCHAR2(100) NOT NULL,
    estado          NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor       VARCHAR2(50) NOT NULL,
    creadoen        DATE DEFAULT SYSDATE,
    modificadopor   VARCHAR2(50),
    modificadoen    DATE
);
CREATE SEQUENCE  "SQ_TB_PROGRAMA"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;

CREATE TABLE tb_programaservicio (
    id_programaservicio   NUMBER(10) NOT NULL,
    id_programa           NUMBER(10) NOT NULL,
    id_servicio           NUMBER(10) NOT NULL,
    estado                NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor             VARCHAR2(50) NOT NULL,
    creadoen              DATE DEFAULT SYSDATE,
    modificadopor         VARCHAR2(50),
    modificadoen          DATE
);

CREATE SEQUENCE  "SQ_TB_PROGRAMASERVICIO"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;

CREATE TABLE tb_proyecto (
    id_proyecto       NUMBER(10) NOT NULL,
    tituloproyecto    VARCHAR2(100) NOT NULL,
    resumenproyecto   VARCHAR2(700) NOT NULL,
    id_programa       NUMBER(10) NOT NULL,
    id_servicio       NUMBER(10) NOT NULL, no 
    estado            NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor         VARCHAR2(50) NOT NULL,
    creadoen          DATE DEFAULT SYSDATE,
    modificadopor     VARCHAR2(50),
    modificadoen      DATE
);
CREATE SEQUENCE  "SQ_TB_PROYECTO"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;

CREATE TABLE tb_proyectoetapa (
    id_proyectoetapa   NUMBER(10) NOT NULL,
    id_proyecto        NUMBER(10) NOT NULL,
    id_etapa           NUMBER(10) NOT NULL,
    estado             NUMBER(1) DEFAULT 1 NOT NULL,
    observacion        VARCHAR2(300),
    fechainicio        DATE,
    fechafin           DATE,
    creadopor          VARCHAR2(50) NOT NULL,
    creadoen           DATE DEFAULT SYSDATE,
    modificadopor      VARCHAR2(50),
    modificadoen       DATE
);

CREATE TABLE tb_rol (
    id_rol          NUMBER(10) NOT NULL,
    valor           VARCHAR2(50) NOT NULL,
    descripcion     VARCHAR2(100) NOT NULL,
    estado          NUMBER(10) NOT NULL,
    creadopor       VARCHAR2(50) NOT NULL,
    creadoen        DATE DEFAULT SYSDATE,
    modificadopor   VARCHAR2(50),
    modificadoen    DATE
);

CREATE TABLE tb_soporteproyectoetapa (
    id_soporteproyectoetapa   NUMBER(10) NOT NULL,
    id_proyectoetapa          NUMBER(10) NOT NULL,
    archivo                   VARCHAR2(300) NOT NULL,
    creadopor                 VARCHAR2(50) NOT NULL,
    creadoen                  DATE DEFAULT SYSDATE,
    modificadopor             VARCHAR2(50),
    modificadoen              DATE
);

CREATE TABLE tb_tercero (
    id_tercero                 NUMBER(10) NOT NULL,
    id_lv_tipoidentificacion   NUMBER(10) NOT NULL,
    numidentificacion          VARCHAR2(20) NOT NULL,
    primernombre               VARCHAR2(100) NOT NULL,
    segundonombre              VARCHAR2(50),
    primerapellido             VARCHAR2(100) NOT NULL,
    segundoapellido            VARCHAR2(50),
    fechanacimiento            DATE NOT NULL,
    telefonofijo               VARCHAR2(50),
    telefonocelular            VARCHAR2(50),
	correo              	   VARCHAR2(50),
    estado                     NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor                  VARCHAR2(50) NOT NULL,
    creadoen                   DATE DEFAULT SYSDATE,
    modificadopor              VARCHAR2(50),
    modificadoen               DATE
	
);

  CREATE TABLE tb_usuario
  (
    id_usuario    NUMBER (10) NOT NULL , 
    id_tercero      NUMBER(10),
    usuario         VARCHAR2(20) NOT NULL,
    contrasena      VARCHAR2(200) NOT NULL,
    estado          NUMBER(10) NOT NULL
  ) ;
  
CREATE TABLE tb_usuariorol (
    id_usuariorol   NUMBER(10) NOT NULL,
    id_usuario      NUMBER(10) NOT NULL,
    id_rol          NUMBER(10) NOT NULL,
    estado          NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor       VARCHAR2(50) NOT NULL,
    creadoen        DATE DEFAULT SYSDATE,
    modificadopor   VARCHAR2(50),
    modificadoen    DATE
);

ALTER TABLE tb_beneficiario
    ADD CONSTRAINT ck_beneficiario_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_beneficiario IS
    'Tabla donde se registran los integrantes de un proyecto';

COMMENT ON COLUMN tb_beneficiario.id_beneficiario IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_beneficiario.id_proyecto IS
    'Identificador unico de la tabla TB_Proyecto';

COMMENT ON COLUMN tb_beneficiario.id_tercero IS
    'Identificador unico de la tabla TB_Tercero';

COMMENT ON COLUMN tb_beneficiario.estado IS
    'Estado del beneficiario. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

COMMENT ON COLUMN tb_beneficiario.observacion IS
    'Observacion del beneficiario';

ALTER TABLE tb_beneficiario ADD CONSTRAINT pk_beneficiario PRIMARY KEY ( id_beneficiario );

ALTER TABLE tb_beneficiario ADD CONSTRAINT un_beneficiario_001 UNIQUE ( id_proyecto,
id_tercero );



ALTER TABLE tb_etapa
    ADD CONSTRAINT ck_etapa_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_etapa IS
    'Tabla donde se registran las etapas de un servicio';

COMMENT ON COLUMN tb_etapa.id_etapa IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_etapa.descripcion IS
    'Descripcion de la Etapa';

COMMENT ON COLUMN tb_etapa.estado IS
    'Estado de la Etapa. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_etapa ADD CONSTRAINT pk_etapa PRIMARY KEY ( id_etapa );



ALTER TABLE tb_listavalor
    ADD CONSTRAINT ck_listavalor_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_listavalor IS
    'Tabla donde se registran los maestros de los combos';

COMMENT ON COLUMN tb_listavalor.id_listavalor IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_listavalor.agrupacion IS
    'Identificador unico del combo principal';

COMMENT ON COLUMN tb_listavalor.descripcion IS
    'Descripcion del combo';

COMMENT ON COLUMN tb_listavalor.estado IS
    'Estado del combo. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_listavalor ADD CONSTRAINT pk_listavalor PRIMARY KEY ( id_listavalor );



ALTER TABLE tb_listavalordetalle
    ADD CONSTRAINT ck_listavalordetalle_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_listavalordetalle IS
    'Tabla donde se registra el detalle de cada uno de los combos de la tabla TB_ListaValor';

COMMENT ON COLUMN tb_listavalordetalle.id_listavalordetalle IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_listavalordetalle.id_listavalor IS
    'Identificador unico de la tabla TB_ListaValor';

COMMENT ON COLUMN tb_listavalordetalle.valor IS
    'Valores que se mostraran al usuario cuando presione el combo';

COMMENT ON COLUMN tb_listavalordetalle.estado IS
    'Estado del detalle dentro del maestro. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_listavalordetalle ADD CONSTRAINT pk_listavalordetalle PRIMARY KEY ( id_listavalordetalle );



ALTER TABLE tb_oferente
    ADD CONSTRAINT ck_oferente_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_oferente IS
    'Tabla donde se registran los integrantes de un proyecto';

COMMENT ON COLUMN tb_oferente.id_oferente IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_oferente.id_proyecto IS
    'Identificador unico de la tabla TB_Proyecto';

COMMENT ON COLUMN tb_oferente.id_tercero IS
    'Identificador unico de la tabla TB_Tercero';

COMMENT ON COLUMN tb_oferente.estado IS
    'Estado del Oferente. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

COMMENT ON COLUMN tb_oferente.observacion IS
    'Observacion del Oferente';

ALTER TABLE tb_oferente ADD CONSTRAINT pk_oferente PRIMARY KEY ( id_oferente );

ALTER TABLE tb_oferente ADD CONSTRAINT un_oferente_001 UNIQUE ( id_proyecto,
id_tercero );



ALTER TABLE tb_programa
    ADD CONSTRAINT ck_programa_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_programa IS
    'Tabla donde se registran los programas';

COMMENT ON COLUMN tb_programa.id_programa IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_programa.descripcion IS
    'Descripcion del programa ofertado';

COMMENT ON COLUMN tb_programa.estado IS
    'Estado del Programa. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_programa ADD CONSTRAINT pk_programa PRIMARY KEY ( id_programa );



ALTER TABLE tb_programaservicio
    ADD CONSTRAINT ck_programaservicio_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_programaservicio IS
    'Tabla donde se registra la asociacion entre Programa y Servicio';

COMMENT ON COLUMN tb_programaservicio.id_programaservicio IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_programaservicio.id_programa IS
    'Identificador unico de la tabla TB_Programa';

COMMENT ON COLUMN tb_programaservicio.id_servicio IS
    'Identificador unico de la tabla TB_Servicio';

COMMENT ON COLUMN tb_programaservicio.estado IS
    'Estado del ProgramaServicio. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_programaservicio ADD CONSTRAINT pk_programaservicio PRIMARY KEY ( id_programaservicio );

ALTER TABLE tb_programaservicio ADD CONSTRAINT un_programaservicio_001 UNIQUE ( id_programa,
id_servicio );



ALTER TABLE tb_proyecto
    ADD CONSTRAINT ck_proyecto_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_proyecto IS
    'Tabla donde se registran los proyectos';

COMMENT ON COLUMN tb_proyecto.id_proyecto IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_proyecto.tituloproyecto IS
    'Titulo del proyecto';

COMMENT ON COLUMN tb_proyecto.resumenproyecto IS
    'Resumen o descripcion del proyecto';

COMMENT ON COLUMN tb_proyecto.id_programa IS
    'Identificador unico de la tabla TB_Programa';

COMMENT ON COLUMN tb_proyecto.id_servicio IS
    'Identificador unico de la tabla TB_Servicio';

COMMENT ON COLUMN tb_proyecto.estado IS
    'Estado del proyecto. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_proyecto ADD CONSTRAINT pk_proyecto PRIMARY KEY ( id_proyecto );



ALTER TABLE tb_proyectoetapa
    ADD CONSTRAINT ck_proyectoetapa_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_proyectoetapa IS
    'Tabla donde se registran las etapas del proyecto';

COMMENT ON COLUMN tb_proyectoetapa.id_proyectoetapa IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_proyectoetapa.id_proyecto IS
    'Identificador unico de la tabla TB_Proyecto';

COMMENT ON COLUMN tb_proyectoetapa.id_etapa IS
    'Identificador unico del tabla TB_Etapa';

COMMENT ON COLUMN tb_proyectoetapa.estado IS
    'Estado del ProyectoEtapa. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

COMMENT ON COLUMN tb_proyectoetapa.observacion IS
    'Observacion del ProyectoEtapa';

COMMENT ON COLUMN tb_proyectoetapa.fechainicio IS
    'Fecha Inicio del ProyectoEtapa';

COMMENT ON COLUMN tb_proyectoetapa.fechafin IS
    'Fecha Fin del ProyectoEtapa';

ALTER TABLE tb_proyectoetapa ADD CONSTRAINT pk_proyectoetapa PRIMARY KEY ( id_proyectoetapa );

ALTER TABLE tb_proyectoetapa ADD CONSTRAINT un_proyectoetapa_001 UNIQUE ( id_proyecto,
id_etapa );



ALTER TABLE tb_rol
    ADD CONSTRAINT ck_rol_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_rol IS
    'Tabla donde se registran todos los roles del sistema';

COMMENT ON COLUMN tb_rol.id_rol IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_rol.valor IS
    'Valor del rol. Descripcion corta';

COMMENT ON COLUMN tb_rol.descripcion IS
    'Descripcion del rol';

COMMENT ON COLUMN tb_rol.estado IS
    'Estado del rol. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_rol ADD CONSTRAINT pk_rol PRIMARY KEY ( id_rol );



ALTER TABLE tb_servicio
    ADD CONSTRAINT ck_servicio_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_servicio IS
    'Tabla donde se registran los servicios de un programa';

COMMENT ON COLUMN tb_servicio.id_servicio IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_servicio.descripcion IS
    'Descripcion del servicio ofertado';

COMMENT ON COLUMN tb_servicio.estado IS
    'Estado del Servicio. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_servicio ADD CONSTRAINT pk_servicio PRIMARY KEY ( id_servicio );


ALTER TABLE tb_servicioetapa
    ADD CONSTRAINT ck_servicioetapa_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_servicioetapa IS
    'Tabla donde se registra la asociacion entre Servicio y Etapa';

COMMENT ON COLUMN tb_servicioetapa.id_servicioetapa IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_servicioetapa.id_servicio IS
    'Identificador unico de la tabla TB_Servicio';

COMMENT ON COLUMN tb_servicioetapa.id_etapa IS
    'Identificador unico de la tabla TB_Etapa';

COMMENT ON COLUMN tb_servicioetapa.estado IS
    'Estado del ServicioEtapa. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_servicioetapa ADD CONSTRAINT pk_servicioetapa PRIMARY KEY ( id_servicioetapa );

ALTER TABLE tb_servicioetapa ADD CONSTRAINT un_servicioetapa_001 UNIQUE ( id_servicio,
id_etapa );


COMMENT ON TABLE tb_soporteproyectoetapa IS
    'Tabla donde se registran los integrantes de un proyecto';

COMMENT ON COLUMN tb_soporteproyectoetapa.id_soporteproyectoetapa IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_soporteproyectoetapa.id_proyectoetapa IS
    'Identificador unico de la tabla TB_ProyectoEtapa';

COMMENT ON COLUMN tb_soporteproyectoetapa.archivo IS
    'Archivo o ruta del archivo que soporta la etapa del proyecto';

ALTER TABLE tb_soporteproyectoetapa ADD CONSTRAINT pk_soporteproyectoetapa PRIMARY KEY ( id_soporteproyectoetapa );



ALTER TABLE tb_tercero
    ADD CONSTRAINT ck_tercero_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_tercero IS
    'Tabla donde se registran todos los terceros (estudiantes, profesores, empresas, entidades) del sistema';

COMMENT ON COLUMN tb_tercero.id_tercero IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_tercero.id_lv_tipoidentificacion IS
    'Identificador unico de la tabla TB_ListaValorDetalle - Agrupacion TipoIdentificacion';

COMMENT ON COLUMN tb_tercero.numidentificacion IS
    'Numero unico de identificacion del tercero';

COMMENT ON COLUMN tb_tercero.primernombre IS
    'Primer nombre del tercero';

COMMENT ON COLUMN tb_tercero.segundonombre IS
    'Segundo nombre â€”opcionalâ€” del tercero';

COMMENT ON COLUMN tb_tercero.primerapellido IS
    'Primer apellido del tercero';

COMMENT ON COLUMN tb_tercero.segundoapellido IS
    'Segundo apellido â€”opcionalâ€” del tercero';

COMMENT ON COLUMN tb_tercero.fechanacimiento IS
    'Fecha de Nacimiento del tercero';

COMMENT ON COLUMN tb_tercero.telefonofijo IS
    'Telefono fijo del tercero';

COMMENT ON COLUMN tb_tercero.telefonocelular IS
    'Telefono celular del tercero';

COMMENT ON COLUMN tb_tercero.estado IS
    'Estado del tercero. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_tercero ADD CONSTRAINT pk_tercero PRIMARY KEY ( id_tercero );

ALTER TABLE tb_tercero ADD CONSTRAINT un_tercero_001 UNIQUE ( numidentificacion );


/*CREATE TABLE tb_usuario (
    id_usuario      NUMBER(10) NOT NULL,
    id_tercero      NUMBER(10),
    usuario         VARCHAR2(20) NOT NULL,
    contrasena      VARCHAR2(200) NOT NULL,
    estado          NUMBER(10) NOT NULL,
    creadopor       VARCHAR2(50) NOT NULL,
    creadoen        DATE DEFAULT SYSDATE,
    modificadopor   VARCHAR2(50),
    modificadoen    DATE
);
*/
ALTER TABLE tb_usuario
    ADD CONSTRAINT ck_usuario_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_usuario IS
    'Tabla donde se registran todos los usuarios del sistema';

COMMENT ON COLUMN tb_usuario.id_usuario IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_usuario.id_tercero IS
    'Identificador unico de la tabla TB_Tercero';

COMMENT ON COLUMN tb_usuario.usuario IS
    'Nombre de usuario';

COMMENT ON COLUMN tb_usuario.contrasena IS
    'Contrasena de usuario';

COMMENT ON COLUMN tb_usuario.estado IS
    'Estado del usuario. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_usuario ADD CONSTRAINT pk_usuario PRIMARY KEY ( id_usuario );

ALTER TABLE tb_usuario ADD CONSTRAINT un_usuario_001 UNIQUE ( usuario );



ALTER TABLE tb_usuariorol
    ADD CONSTRAINT ck_usuariorol_001 CHECK ( estado IN (
        0,
        1
    ) );

COMMENT ON TABLE tb_usuariorol IS
    'Tabla donde se registran todos los usuarios del sistema';

COMMENT ON COLUMN tb_usuariorol.id_usuariorol IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_usuariorol.id_usuario IS
    'Identificador unico de la tabla TB_Usuario';

COMMENT ON COLUMN tb_usuariorol.id_rol IS
    'Identificador unico de la tabla TB_Rol';

COMMENT ON COLUMN tb_usuariorol.estado IS
    'Estado del rol para el usuario. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_usuariorol ADD CONSTRAINT pk_usuariorol PRIMARY KEY ( id_usuariorol );

ALTER TABLE tb_usuariorol ADD CONSTRAINT un_usuariorol_001 UNIQUE ( id_usuario,
id_rol );

ALTER TABLE tb_beneficiario
    ADD CONSTRAINT fk_beneficiario_001 FOREIGN KEY ( id_proyecto )
        REFERENCES tb_proyecto ( id_proyecto )
    NOT DEFERRABLE;

ALTER TABLE tb_beneficiario
    ADD CONSTRAINT fk_beneficiario_002 FOREIGN KEY ( id_tercero )
        REFERENCES tb_tercero ( id_tercero )
    NOT DEFERRABLE;

ALTER TABLE tb_listavalordetalle
    ADD CONSTRAINT fk_listavalordetalle_001 FOREIGN KEY ( id_listavalor )
        REFERENCES tb_listavalor ( id_listavalor )
    NOT DEFERRABLE;

ALTER TABLE tb_oferente
    ADD CONSTRAINT fk_oferente_001 FOREIGN KEY ( id_proyecto )
        REFERENCES tb_proyecto ( id_proyecto )
    NOT DEFERRABLE;

ALTER TABLE tb_oferente
    ADD CONSTRAINT fk_oferente_002 FOREIGN KEY ( id_tercero )
        REFERENCES tb_tercero ( id_tercero )
    NOT DEFERRABLE;

ALTER TABLE tb_programaservicio
    ADD CONSTRAINT fk_programaservicio_001 FOREIGN KEY ( id_programa )
        REFERENCES tb_programa ( id_programa )
    NOT DEFERRABLE;

ALTER TABLE tb_programaservicio
    ADD CONSTRAINT fk_programaservicio_002 FOREIGN KEY ( id_servicio )
        REFERENCES tb_servicio ( id_servicio )
    NOT DEFERRABLE;

ALTER TABLE tb_proyecto
    ADD CONSTRAINT fk_proyecto_001 FOREIGN KEY ( id_programa )
        REFERENCES tb_programa ( id_programa )
    NOT DEFERRABLE;

ALTER TABLE tb_proyecto
    ADD CONSTRAINT fk_proyecto_002 FOREIGN KEY ( id_servicio )
        REFERENCES tb_servicio ( id_servicio )
    NOT DEFERRABLE;

ALTER TABLE tb_proyectoetapa
    ADD CONSTRAINT fk_proyectoetapa_001 FOREIGN KEY ( id_proyecto )
        REFERENCES tb_proyecto ( id_proyecto )
    NOT DEFERRABLE;

ALTER TABLE tb_proyectoetapa
    ADD CONSTRAINT fk_proyectoetapa_002 FOREIGN KEY ( id_etapa )
        REFERENCES tb_etapa ( id_etapa )
    NOT DEFERRABLE;

ALTER TABLE tb_servicioetapa
    ADD CONSTRAINT fk_servicioetapa_001 FOREIGN KEY ( id_servicio )
        REFERENCES tb_servicio ( id_servicio )
    NOT DEFERRABLE;

ALTER TABLE tb_servicioetapa
    ADD CONSTRAINT fk_servicioetapa_002 FOREIGN KEY ( id_etapa )
        REFERENCES tb_etapa ( id_etapa )
    NOT DEFERRABLE;

ALTER TABLE tb_soporteproyectoetapa
    ADD CONSTRAINT fk_soporteproyectoetapa_001 FOREIGN KEY ( id_proyectoetapa )
        REFERENCES tb_proyectoetapa ( id_proyectoetapa )
    NOT DEFERRABLE;

ALTER TABLE tb_tercero
    ADD CONSTRAINT fk_tercero_001 FOREIGN KEY ( id_lv_tipoidentificacion )
        REFERENCES tb_listavalordetalle ( id_listavalordetalle )
    NOT DEFERRABLE;

ALTER TABLE tb_usuario
    ADD CONSTRAINT fk_usuario_001 FOREIGN KEY ( id_tercero )
        REFERENCES tb_tercero ( id_tercero )
    NOT DEFERRABLE;

ALTER TABLE tb_usuariorol
    ADD CONSTRAINT fk_usuariorol_001 FOREIGN KEY ( id_usuario )
        REFERENCES tb_usuario ( id_usuario )
    NOT DEFERRABLE;

ALTER TABLE tb_usuariorol
    ADD CONSTRAINT fk_usuariorol_002 FOREIGN KEY ( id_rol )
        REFERENCES tb_rol ( id_rol )
    NOT DEFERRABLE;
	
CREATE SEQUENCE  "SQ_TB_TERCERO"  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "SQ_TB_USUARIO"  MINVALUE 1 MAXVALUE 9999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;