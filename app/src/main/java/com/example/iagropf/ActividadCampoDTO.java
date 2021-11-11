package com.example.iagropf;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

public class ActividadCampoDTO {

    @Expose(serialize = true)
    private Long iddepartamento;

    @Expose(serialize = true)
    private Long idestacionMuestreo;

    @Expose(serialize = true)
    private Long idmetMuestreo;

    @Expose(serialize = true)
    private Long idformulario;

    @Expose(serialize = true)
    private String nombre;

    @Expose(serialize = true)
    private String descripcion;

    @Expose(serialize = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String fecha;
    @Expose(serialize = true)
    private String cantidad;

    @Expose(serialize = true)
    private List<RespuestaCasillaDTO> respuestas;
    //private Usuario usuario;
    //@Expose(serialize = true)
    //private FormularioDTO formulario = new FormularioDTO();
    //@Expose(serialize = true)
    //private MetodoMuestreoDTO metMuestreo = new MetodoMuestreoDTO();
   // @Expose(serialize = true)
   // private EstacionMuestreoDTO estacionMuestreo = new  EstacionMuestreoDTO();
   // @Expose(serialize = true)
   // private DepartamentoDTO departamento = new DepartamentoDTO();


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getCantidad() {
        return cantidad;
    }
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public List<RespuestaCasillaDTO> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaCasillaDTO> respuestas) {
        this.respuestas = respuestas;
    }
    /* public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public FormularioDTO getFormulario() {
        return formulario;
    }

    public void setFormulario(FormularioDTO formulario) {
        this.formulario = formulario;
    }
    public MetodoMuestreoDTO getMetMuestreo() {
        return metMuestreo;
    }
    public void setMetMuestreo(MetodoMuestreoDTO metMuestreo) {
        this.metMuestreo = metMuestreo;
    }
    public EstacionMuestreoDTO getEstacionMuestreo() {
        return estacionMuestreo;
    }
    public void setEstacionMuestreo(EstacionMuestreoDTO estacionMuestreo) {
        this.estacionMuestreo = estacionMuestreo;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }
    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }*/

    public Long getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Long iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public Long getIdestacionMuestreo() {
        return idestacionMuestreo;
    }

    public void setIdestacionMuestreo(Long idestacionMuestreo) {
        this.idestacionMuestreo = idestacionMuestreo;
    }

    public Long getIdmetMuestreo() {
        return idmetMuestreo;
    }

    public void setIdmetMuestreo(Long idmetMuestreo) {
        this.idmetMuestreo = idmetMuestreo;
    }

    public Long getIdformulario() {
        return idformulario;
    }

    public void setIdformulario(Long idformulario) {
        this.idformulario = idformulario;
    }

    public ActividadCampoDTO() {
        super();
    }

   /* public ActividadCampoDTO(String nombre, String descripcion, Date fecha, int cantidad, FormularioDTO formulario, MetodoMuestreoDTO metMuestreo, EstacionMuestreoDTO estacionMuestreo, DepartamentoDTO departamento) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.formulario = formulario;
        this.metMuestreo = metMuestreo;
        this.estacionMuestreo = estacionMuestreo;
        this.departamento = departamento;
    }*/

    public ActividadCampoDTO(String nombre, String descripcion, String fecha, String cantidad, Long idformulario, Long idmetMuestreo, Long idestacionMuestreo, Long iddepartamento) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.idformulario = idformulario;
        this.idmetMuestreo = idmetMuestreo;
        this.idestacionMuestreo = idestacionMuestreo;
        this.iddepartamento = iddepartamento;
    }

    public ActividadCampoDTO(String nombre, String descripcion, String fecha) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.idformulario = idformulario;
        this.idmetMuestreo = idmetMuestreo;
        this.idestacionMuestreo = idestacionMuestreo;
        this.iddepartamento = iddepartamento;
    }

}
