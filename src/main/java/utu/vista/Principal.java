package utu.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import utu.modelo.Usuario;

public class Principal extends JFrame{

    private Usuario usuarioLogeado;
    private JDesktopPane escritorio;
    private JLabel lblUsuario;
    private JLabel lblPerfil;

    public Principal(Usuario usuario){

        this.usuarioLogeado = usuario;

        iniciarComponentes(); 
    }

    private void iniciarComponentes(){

        setTitle("Sistema de Gestión Hospitalaria");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        escritorio = new JDesktopPane();
        add(escritorio, BorderLayout.CENTER);
        crearMenu();
        crearBarraEstado();
        setVisible(true);

    }
    private void crearMenu(){
        JMenuBar barra = new JMenuBar();

        JMenu archivo = new JMenu("Archivo");
        JMenu seguridad = new JMenu("Seguridad");
        JMenu mantenimiento = new JMenu("Mantenimiento");
        JMenu procesos = new JMenu("Procesos");
        JMenu ayuda = new JMenu("Ayuda");

        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(e -> System.exit(0));

        archivo.add(salir);

        JMenuItem usuarios = new JMenuItem("Usuarios");
        JMenuItem perfiles = new JMenuItem("Perfiles");

        seguridad.add(usuarios);
        seguridad.add(perfiles);

        JMenuItem marcas = new JMenuItem("Marcas");
        JMenuItem modelos = new JMenuItem("Modelos");
        JMenuItem proveedores = new JMenuItem("Proveedores");
        JMenuItem equipos = new JMenuItem("Equipos");

        marcas.addActionListener(e -> {
            FrmMarca frmMarca = new FrmMarca();
            escritorio.add(frmMarca);
            frmMarca.setVisible(true);

            try {
                frmMarca.setSelected(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        mantenimiento.add(marcas);
        mantenimiento.add(modelos);
        mantenimiento.add(proveedores);
        mantenimiento.add(equipos);

        JMenuItem intervenciones = new JMenuItem("Intervenciones");
        procesos.add(intervenciones);

        barra.add(archivo);
        barra.add(seguridad);
        barra.add(mantenimiento);
        barra.add(procesos);
        barra.add(ayuda);

        setJMenuBar(barra);

    }
    private void crearBarraEstado(){

        JPanel estado = new JPanel(new BorderLayout());
        estado.setPreferredSize(new Dimension(100, 30));

        lblUsuario = new JLabel("Usuario: " + usuarioLogeado.getNombre() + " " + usuarioLogeado.getApellido());

        lblPerfil = new JLabel("Perfil: " + usuarioLogeado.getPerfil().getNombre() + " ");

        estado.add(lblUsuario, BorderLayout.WEST);
        estado.add(lblPerfil, BorderLayout.EAST);
        add(estado, BorderLayout.SOUTH);
    }
    public JDesktopPane getEscritorio() {
        return escritorio;
    }

    public void abrirFormulario(JInternalFrame formulario) {
        
        for(JInternalFrame ventana : escritorio.getAllFrames()){
            if(ventana.getClass().equals(formulario.getClass())){
                try {
                    ventana.setSelected(true);
                    ventana.toFront();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        escritorio.add(formulario);
        formulario.setVisible(true);
    }


   
}
