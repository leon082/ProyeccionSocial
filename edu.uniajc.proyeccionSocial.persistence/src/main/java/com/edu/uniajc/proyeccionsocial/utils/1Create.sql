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
--   en:        2017-11-26 15:27:32 COT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



CREATE TABLE tb_beneficiario (
    id_beneficiario      NUMBER(10) NOT NULL,
    id_proyecto          NUMBER(10) NOT NULL,
    id_tercero           NUMBER(10) NOT NULL,
    estadobeneficiario   NUMBER(1) DEFAULT 1 NOT NULL,
    observacion          VARCHAR2(300),
    creadopor            VARCHAR2(50) NOT NULL,
    creadoen             DATE DEFAULT SYSDATE,
    modificadopor        VARCHAR2(50),
    modificadoen         DATE
);

COMMENT ON TABLE tb_beneficiario IS
    'Tabla donde se registran los integrantes de un proyecto';

COMMENT ON COLUMN tb_beneficiario.id_beneficiario IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_beneficiario.id_proyecto IS
    'Identificador unico de la table TB_Proyecto';

COMMENT ON COLUMN tb_beneficiario.id_tercero IS
    'Identificador unico de la tabla TB_Tercero';

COMMENT ON COLUMN tb_beneficiario.estadobeneficiario IS
    'Estado del beneficiario. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

COMMENT ON COLUMN tb_beneficiario.observacion IS
    'Observacion del beneficiario';

ALTER TABLE tb_beneficiario ADD CONSTRAINT pk_beneficiario PRIMARY KEY ( id_beneficiario );

ALTER TABLE tb_beneficiario ADD CONSTRAINT un_beneficiario_001 UNIQUE ( id_proyecto,
id_tercero );

CREATE TABLE tb_fase (
    id_fase               NUMBER(10) NOT NULL,
    descripcion           VARCHAR2(100) NOT NULL,
    id_programaservicio   NUMBER(10) NOT NULL,
    estadofase            NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor             VARCHAR2(50) NOT NULL,
    creadoen              DATE DEFAULT SYSDATE,
    modificadopor         VARCHAR2(50),
    modificadoen          DATE
);

COMMENT ON TABLE tb_fase IS
    'Tabla donde se registran los integrantes de un proyecto';

COMMENT ON COLUMN tb_fase.id_fase IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_fase.descripcion IS
    'Descripcion de la fase';

COMMENT ON COLUMN tb_fase.estadofase IS
    'Estado de la fase. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_fase ADD CONSTRAINT pk_fase PRIMARY KEY ( id_fase );

CREATE TABLE tb_faseproyecto (
    id_faseproyecto      NUMBER(10) NOT NULL,
    id_proyecto          NUMBER(10) NOT NULL,
    id_fase              NUMBER(10) NOT NULL,
    estadofaseproyecto   NUMBER(1) DEFAULT 1 NOT NULL,
    observacion          VARCHAR2(300),
    fechainicio          DATE,
    fechafin             DATE,
    creadopor            VARCHAR2(50) NOT NULL,
    creadoen             DATE DEFAULT SYSDATE,
    modificadopor        VARCHAR2(50),
    modificadoen         DATE
);

COMMENT ON TABLE tb_faseproyecto IS
    'Tabla donde se registran los integrantes de un proyecto';

COMMENT ON COLUMN tb_faseproyecto.id_faseproyecto IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_faseproyecto.id_proyecto IS
    'Identificador unico de la table TB_Proyecto';

COMMENT ON COLUMN tb_faseproyecto.id_fase IS
    'Identificador unico de la tabla TB_Fase';

COMMENT ON COLUMN tb_faseproyecto.estadofaseproyecto IS
    'Estado de la FaseProyecto. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

COMMENT ON COLUMN tb_faseproyecto.observacion IS
    'Observacion de la FaseProyecto';

ALTER TABLE tb_faseproyecto ADD CONSTRAINT pk_faseproyecto PRIMARY KEY ( id_faseproyecto );

ALTER TABLE tb_faseproyecto ADD CONSTRAINT un_faseproyecto_001 UNIQUE ( id_proyecto,
id_fase );

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

ALTER TABLE tb_listavalor
    ADD CONSTRAINT chk_listavalor_001 CHECK ( estado IN (
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

ALTER TABLE tb_listavalordetalle
    ADD CONSTRAINT chk_listavalordetalle_001 CHECK ( estado IN (
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

CREATE TABLE tb_oferente (
    id_oferente      NUMBER(10) NOT NULL,
    id_proyecto      NUMBER(10) NOT NULL,
    id_tercero       NUMBER(10) NOT NULL,
    estadooferente   NUMBER(1) DEFAULT 1 NOT NULL,
    observacion      VARCHAR2(300),
    creadopor        VARCHAR2(50) NOT NULL,
    creadoen         DATE DEFAULT SYSDATE,
    modificadopor    VARCHAR2(50),
    modificadoen     DATE
);

COMMENT ON TABLE tb_oferente IS
    'Tabla donde se registran los integrantes de un proyecto';

COMMENT ON COLUMN tb_oferente.id_oferente IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_oferente.id_proyecto IS
    'Identificador unico de la table TB_Proyecto';

COMMENT ON COLUMN tb_oferente.id_tercero IS
    'Identificador unico de la tabla TB_Tercero';

COMMENT ON COLUMN tb_oferente.estadooferente IS
    'Estado del Oferente. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

COMMENT ON COLUMN tb_oferente.observacion IS
    'Observacion del Oferente';

ALTER TABLE tb_oferente ADD CONSTRAINT pk_oferente PRIMARY KEY ( id_oferente );

ALTER TABLE tb_oferente ADD CONSTRAINT un_oferente_001 UNIQUE ( id_proyecto,
id_tercero );

CREATE TABLE tb_programa (
    id_programa      NUMBER(10) NOT NULL,
    descripcion      VARCHAR2(100) NOT NULL,
    estadoprograma   NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor        VARCHAR2(50) NOT NULL,
    creadoen         DATE DEFAULT SYSDATE,
    modificadopor    VARCHAR2(50),
    modificadoen     DATE
);

COMMENT ON TABLE tb_programa IS
    'Tabla donde se registran los integrantes de un proyecto';

COMMENT ON COLUMN tb_programa.id_programa IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_programa.descripcion IS
    'Descripcion del programa ofertado';

COMMENT ON COLUMN tb_programa.estadoprograma IS
    'Estado del Programa. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_programa ADD CONSTRAINT pk_programa PRIMARY KEY ( id_programa );

CREATE TABLE tb_programaservicio (
    id_programaservicio      NUMBER(10) NOT NULL,
    descripcion              VARCHAR2(100) NOT NULL,
    id_programa              NUMBER(10) NOT NULL,
    estadoprogramaservicio   NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor                VARCHAR2(50) NOT NULL,
    creadoen                 DATE DEFAULT SYSDATE,
    modificadopor            VARCHAR2(50),
    modificadoen             DATE
);

COMMENT ON TABLE tb_programaservicio IS
    'Tabla donde se registran los integrantes de un proyecto';

COMMENT ON COLUMN tb_programaservicio.id_programaservicio IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_programaservicio.descripcion IS
    'Descripcion del ProgramaServicio ofertado';

COMMENT ON COLUMN tb_programaservicio.id_programa IS
    'Identificador unico de la table TB_Programa';

COMMENT ON COLUMN tb_programaservicio.estadoprogramaservicio IS
    'Estado del ProgramaServicio. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_programaservicio ADD CONSTRAINT pk_programaservicio PRIMARY KEY ( id_programaservicio );

CREATE TABLE tb_proyecto (
    id_proyecto           NUMBER(10) NOT NULL,
    tituloproyecto        VARCHAR2(100) NOT NULL,
    resumenproyecto       VARCHAR2(700) NOT NULL,
    id_programa           NUMBER(10) NOT NULL,
    id_programaservicio   NUMBER(10) NOT NULL,
    estadoproyecto        NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor             VARCHAR2(50) NOT NULL,
    creadoen              DATE DEFAULT SYSDATE,
    modificadopor         VARCHAR2(50),
    modificadoen          DATE
);

COMMENT ON TABLE tb_proyecto IS
    'Tabla donde se registran los proyectos';

COMMENT ON COLUMN tb_proyecto.id_proyecto IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_proyecto.tituloproyecto IS
    'Titulo del proyecto';

COMMENT ON COLUMN tb_proyecto.resumenproyecto IS
    'Resumen o descripcion del proyecto';

COMMENT ON COLUMN tb_proyecto.id_programa IS
    'Identificador unico de la table TB_Programa';

COMMENT ON COLUMN tb_proyecto.id_programaservicio IS
    'Identificador unico de la table TB_ProgramaServicio';

COMMENT ON COLUMN tb_proyecto.estadoproyecto IS
    'Estado del proyecto. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_proyecto ADD CONSTRAINT pk_proyecto PRIMARY KEY ( id_proyecto );

CREATE TABLE tb_rol (
    id_rol        NUMBER(10) NOT NULL,
    valor         NUMBER(20) NOT NULL,
    descripcion   VARCHAR2(100) NOT NULL,
    estadorol     NUMBER(10) NOT NULL
);

COMMENT ON TABLE tb_rol IS
    'Tabla donde se registran todos los roles del sistema';

COMMENT ON COLUMN tb_rol.id_rol IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_rol.valor IS
    'Valor del rol. Descripcion corta';

COMMENT ON COLUMN tb_rol.descripcion IS
    'Descripcion del rol';

COMMENT ON COLUMN tb_rol.estadorol IS
    'Estado del rol. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_rol ADD CONSTRAINT pk_usuariorolv1 PRIMARY KEY ( id_rol );

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
    estadotercero              NUMBER(1) DEFAULT 1 NOT NULL,
    creadopor                  VARCHAR2(50) NOT NULL,
    creadoen                   DATE DEFAULT SYSDATE,
    modificadopor              VARCHAR2(50),
    modificadoen               DATE
);

COMMENT ON TABLE tb_tercero IS
    'Tabla donde se registran todos los usuarios del sistema';

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

COMMENT ON COLUMN tb_tercero.estadotercero IS
    'Estado del tercero. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_tercero ADD CONSTRAINT pk_tercero PRIMARY KEY ( id_tercero );

ALTER TABLE tb_tercero ADD CONSTRAINT un_tercero_001 UNIQUE ( numidentificacion );

CREATE TABLE tb_usuario (
    id_usuario      NUMBER(10) NOT NULL,
    id_tercero      NUMBER(10),
    usuario         VARCHAR2(20) NOT NULL,
    estadousuario   NUMBER(10) NOT NULL
);

COMMENT ON TABLE tb_usuario IS
    'Tabla donde se registran todos los usuarios del sistema';

COMMENT ON COLUMN tb_usuario.id_usuario IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_usuario.id_tercero IS
    'Identificador unico de la tabla TB_Tercero';

COMMENT ON COLUMN tb_usuario.usuario IS
    'Nombre de usuario';

COMMENT ON COLUMN tb_usuario.estadousuario IS
    'Estado del usuario. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_usuario ADD CONSTRAINT pk_usuariorol PRIMARY KEY ( id_usuario );

ALTER TABLE tb_usuario ADD CONSTRAINT un_usuariorol_001 UNIQUE ( usuario );

CREATE TABLE tb_usuariorol (
    id_usuariorol      NUMBER(10) NOT NULL,
    id_usuario         NUMBER(10) NOT NULL,
    id_rol             NUMBER(10) NOT NULL,
    estadousuariorol   NUMBER(1) DEFAULT 1 NOT NULL
);

COMMENT ON TABLE tb_usuariorol IS
    'Tabla donde se registran todos los usuarios del sistema';

COMMENT ON COLUMN tb_usuariorol.id_usuariorol IS
    'Identificador unico de la tabla';

COMMENT ON COLUMN tb_usuariorol.id_usuario IS
    'Identificador unico de la tabla TB_Usuario';

COMMENT ON COLUMN tb_usuariorol.id_rol IS
    'Identificador unico de la tabla TB_Rol';

COMMENT ON COLUMN tb_usuariorol.estadousuariorol IS
    'Estado del rol para el usuario. Puede ser 0-Inactivo 1-Activo. Por defecto es 1-Activo';

ALTER TABLE tb_usuariorol ADD CONSTRAINT pk_usuariorolv2 PRIMARY KEY ( id_usuariorol );

ALTER TABLE tb_usuariorol ADD CONSTRAINT un_usuariorol_001v1 UNIQUE ( id_usuario,
id_rol );