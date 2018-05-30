/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.Model;

/**
 *
 * @author luis.leon
 */
 public class EtapasEntregas {

        private String nombreEtapa;
        private String estado;
        private int idProyectoEtapa;
        private boolean flag;

        public String getNombreEtapa() {
            return nombreEtapa;
        }

        public void setNombreEtapa(String nombreEtapa) {
            this.nombreEtapa = nombreEtapa;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public int getIdProyectoEtapa() {
            return idProyectoEtapa;
        }

        public void setIdProyectoEtapa(int idProyectoEtapa) {
            this.idProyectoEtapa = idProyectoEtapa;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

    }
