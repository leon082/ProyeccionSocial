/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.ReporteProyecto;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IReporteProyectoDao;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author LuisLeon
 */
public class ReporteProyectoDAO implements IReporteProyectoDao {

    Connection connection;
    private static final Logger LOGGER =  Logger.getLogger(ReporteProyectoDAO.class.getName());

    public ReporteProyectoDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param idPrograma
     * @param idServicio
     * @param idTerceroOferente
     * @param idTerceroCreadoPor
     * @param fechaDesde
     * @param fechaHasta
     * @param estado
     * @param facultad
     * @return
     */
    @Override
    public ArrayList<ReporteProyecto> getAllProyect(int idPrograma, int idServicio, int idTerceroOferente, int idTerceroCreadoPor, Date fechaDesde, Date fechaHasta, int estado, int facultad) {
        ArrayList<ReporteProyecto> listaProyectos = new ArrayList<>();
        PreparedStatement ps =null;
         ResultSet rs = null;

        try {

            

            String SQL = "select tb_proyecto.tituloProyecto,"
                    + " oferente.id_tercero idTerceroOferente,"
                    + " trim(replace(oferente.primerNombre||' '||oferente.segundoNombre||' '||oferente.primerApellido||' '||oferente.segundoApellido, '  ',' ')) oferente,"
                    + " usuario.id_tercero idTerceroUsuario,"
                    + " trim(replace(usuario.primerNombre||' '||usuario.segundoNombre||' '||usuario.primerApellido||' '||usuario.segundoApellido, '  ',' ')) creadoPor,"
                    + " tb_proyecto.creadoEn,"
                    + " tb_programa.id_programa,"
                    + " tb_programa.descripcion programa,"
                    + " tb_servicio.id_servicio,"
                    + " tb_servicio.descripcion servicio,"
                    + " lv.VALOR facultad,"
                    + " tb_proyecto.estado id_estado,"
                    + " case tb_proyecto.estado"
                    + " when 0 then 'Creado / Pdte Aprobacion'"
                    + " when 1 then 'Aprobado'"
                    + " when 2 then 'Rechazado'"
                    + " when 3 then 'Finalizado'"
                    + " when 4 then 'Cancelado'"
                    + " else null end as estadoProyecto"
                    + " from tb_proyecto"
                    + " left join tb_programa on tb_proyecto.id_programa = tb_programa.id_programa"
                    + " left join tb_programaservicio on tb_programa.id_programa = tb_programaservicio.id_programa"
                    + " left join tb_servicio on tb_programaservicio.id_servicio = tb_servicio.id_servicio"
                    + " and tb_proyecto.id_servicio = tb_servicio.id_servicio"
                    + " left join tb_oferente on tb_proyecto.id_proyecto = tb_oferente.id_proyecto"
                    + " left join tb_tercero oferente on tb_oferente.id_tercero = oferente.id_tercero"
                    + " left join tb_usuario on tb_proyecto.creadopor = tb_usuario.usuario"
                    + " left join tb_tercero usuario on tb_usuario.id_tercero = usuario.id_tercero"
                    + " left join TB_LISTAVALORDETALLE lv on tb_proyecto.FACULTAD = lv.ID_LISTAVALORDETALLE"
                    + " where tb_programa.id_programa = DECODE(?, 0,tb_programa.id_programa, ?)"
                    + " and tb_servicio.id_servicio = DECODE(?, 0,tb_servicio.id_servicio, ?) "
                    + " and oferente.id_tercero = DECODE(?, 0,oferente.id_tercero, ?)"
                    + " and usuario.id_tercero = DECODE(?, 0,usuario.id_tercero, ?)"
                    + " and tb_proyecto.creadoen between nvl(?, to_date('01011990','ddmmyyyy'))"
                    + " and nvl(?, to_date('31122049','ddmmyyyy'))"
                    + " and tb_proyecto.estado = DECODE(?, -1,tb_proyecto.estado, ?)"
                    + "and lv.ID_LISTAVALORDETALLE = decode (?,0,lv.ID_LISTAVALORDETALLE,?) ";
            ps = connection.prepareStatement(SQL);
            
            ps.setInt(1, idPrograma);
            ps.setInt(2, idPrograma);

            ps.setInt(3, idServicio);
            ps.setInt(4, idServicio);

            ps.setInt(5, idTerceroOferente);
            ps.setInt(6, idTerceroOferente);

            ps.setInt(7, idTerceroCreadoPor);
            ps.setInt(8, idTerceroCreadoPor);

            ps.setDate(9, fechaDesde);
            ps.setDate(10, fechaHasta);

            ps.setInt(11, estado);
            ps.setInt(12, estado);
            
            ps.setInt(13, facultad);
            ps.setInt(14, facultad);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                
                ReporteProyecto reporte = new ReporteProyecto();
                reporte.setTituloProyecto(rs.getString("TITULOPROYECTO"));
                reporte.setOferente(rs.getString("OFERENTE"));
                reporte.setCreadoPor(rs.getString("CREADOPOR"));
                reporte.setCreadoEn(rs.getDate("CREADOEN"));
                reporte.setPrograma(rs.getString("PROGRAMA"));
                reporte.setServicio(rs.getString("SERVICIO"));
                reporte.setEstado(rs.getString("ESTADOPROYECTO"));
                reporte.setFacultad(rs.getString("FACULTAD"));
                listaProyectos.add(reporte);

            }

            
            
            return listaProyectos;
        } catch (SQLException e) {
            LOGGER.error("Error en Menu DAO getMenuByUser " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

}
