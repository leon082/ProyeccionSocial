/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.ReporteProyecto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author LuisLeon
 */
public class ReporteProyectoDAO {

    public ArrayList<ReporteProyecto> getAllProyect(int idPrograma, int idServicio, int idTerceroOferente, int idTerceroCreadoPor, Date fechaDesde, Date fechaHasta, int estado) {
        ArrayList<ReporteProyecto> listaProyectos = new ArrayList<>();

        try {

            PreparedStatement ps = null;
            
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
                    + " tb_proyecto.estado id_estado,"
                    + " case tb_proyecto.estado"
                    + " when 0 then 'Creado / Pdte Aprobacion'"
                    + " when 1 then 'Aprobado'"
                    + " when 2 then 'Rechazado'"
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
                    + " where tb_programa.id_programa = DECODE(?, 0,tb_programa.id_programa, ?)"
                    + " and tb_servicio.id_servicio = DECODE(?, 0,tb_servicio.id_servicio, ?) "
                    + " and oferente.id_tercero = DECODE(?, 0,oferente.id_tercero, ?)"
                    + " and usuario.id_tercero = DECODE(?, 0,usuario.id_tercero, ?)"
                    + " and tb_proyecto.creadoen between nvl(?, to_date('01011990','ddmmyyyy'))"
                    + " and nvl(?, to_date('31122049','ddmmyyyy'))"
                    + " and tb_proyecto.estado = DECODE(?, -1,tb_proyecto.estado, ?) ";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            
            ps.setInt(1, idPrograma); 
            ps.setInt(2, idPrograma); 
            
            ps.setInt(3, idServicio);
            ps.setInt(4, idServicio);
            
            ps.setInt(5, idTerceroOferente);
            ps.setInt(6, idTerceroOferente);
            
            ps.setInt(7, idTerceroCreadoPor );
            ps.setInt(8, idTerceroCreadoPor );
            
            ps.setDate(9, fechaDesde);
            ps.setDate(10, fechaHasta);
            
            ps.setInt(11, estado);
            ps.setInt(12, estado);
            
            ResultSet rs = ps.executeQuery();
            
            

            while (rs.next()) {
                System.out.println("entro al while");
                ReporteProyecto reporte = new ReporteProyecto();
                reporte.setTituloProyecto(rs.getString("TITULOPROYECTO"));
                reporte.setOferente(rs.getString("OFERENTE"));
                reporte.setCreadoPor(rs.getString("CREADOPOR"));
                reporte.setCreadoEn(rs.getDate("CREADOEN"));
                reporte.setPrograma(rs.getString("PROGRAMA"));
                reporte.setServicio(rs.getString("SERVICIO"));
                reporte.setEstado(rs.getString("ESTADOPROYECTO"));
                listaProyectos.add(reporte);

            }

            ps.close();
            System.out.println("retorna la lista->"+listaProyectos.size());
            return listaProyectos;
        } catch (SQLException e) {
            System.out.println("Error en Menu DAO getMenuByUser " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }
    
   
}
