package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;

import com.servicios.UsuarioBeanRemote;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.naming.InitialContext;
import javax.swing.DefaultComboBoxModel;

import enums.Perfil;
import objetos.Usuario;

import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class FrameAltaUsuario {

	private JFrame frmAltaUsuario;
	private JTextField txtNombre1;
	private JTextField txtNombre2;
	private JTextField txtApellido1;
	private JTextField txtApellido2;
	private JTextField txtUsuario;
	@SuppressWarnings("rawtypes")
	private JComboBox comboPerfil;
	private JPasswordField txtContraseña;

	public Window getAltaUsuario() {
		return frmAltaUsuario;
	}

	/**
	 * Create the application.
	 */
	public FrameAltaUsuario() {
		initialize();

		frmAltaUsuario.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmAltaUsuario = new JFrame();
		frmAltaUsuario.setResizable(false);
		frmAltaUsuario.setTitle("Nuevo Usuario");
		frmAltaUsuario.setBounds(100, 100, 340, 325);
		frmAltaUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAltaUsuario.getContentPane().setLayout(null);

		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setForeground(Color.DARK_GRAY);
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombres.setBounds(20, 20, 70, 20);
		frmAltaUsuario.getContentPane().add(lblNombres);

		txtNombre1 = new JTextField();
		txtNombre1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				if(!Character.isLetter(letra)){
					e.consume();
				}
			}
		});
		txtNombre1.setForeground(Color.DARK_GRAY);
		txtNombre1.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNombre1.setColumns(10);
		txtNombre1.setBounds(110, 18, 98, 23);
		frmAltaUsuario.getContentPane().add(txtNombre1);

		txtNombre2 = new JTextField();
		txtNombre2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				if(!Character.isLetter(letra)){
					e.consume();
				}
			}
		});
		txtNombre2.setForeground(Color.DARK_GRAY);
		txtNombre2.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNombre2.setColumns(10);
		txtNombre2.setBounds(212, 18, 98, 23);
		frmAltaUsuario.getContentPane().add(txtNombre2);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setForeground(Color.DARK_GRAY);
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApellidos.setBounds(20, 65, 70, 20);
		frmAltaUsuario.getContentPane().add(lblApellidos);

		txtApellido1 = new JTextField();
		txtApellido1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				if(!Character.isLetter(letra)){
					e.consume();
				}
			}
		});
		txtApellido1.setForeground(Color.DARK_GRAY);
		txtApellido1.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtApellido1.setColumns(10);
		txtApellido1.setBounds(110, 63, 98, 23);
		frmAltaUsuario.getContentPane().add(txtApellido1);

		txtApellido2 = new JTextField();
		txtApellido2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				if(!Character.isLetter(letra)){
					e.consume();
				}
			}
		});
		txtApellido2.setForeground(Color.DARK_GRAY);
		txtApellido2.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtApellido2.setColumns(10);
		txtApellido2.setBounds(212, 63, 98, 23);
		frmAltaUsuario.getContentPane().add(txtApellido2);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.DARK_GRAY);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBounds(20, 110, 70, 20);
		frmAltaUsuario.getContentPane().add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				generarUsuario(txtNombre1.getText(), txtApellido1.getText(), txtApellido2.getText());
			}
		});
		txtUsuario.setForeground(Color.DARK_GRAY);
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtUsuario.setBackground(Color.WHITE);
		txtUsuario.setEditable(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(110, 110, 200, 23);
		frmAltaUsuario.getContentPane().add(txtUsuario);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setForeground(Color.DARK_GRAY);
		lblPerfil.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPerfil.setBounds(20, 155, 70, 20);
		frmAltaUsuario.getContentPane().add(lblPerfil);

		comboPerfil = new JComboBox();
		comboPerfil.setModel(new DefaultComboBoxModel(Perfil.values()));
		comboPerfil.setForeground(Color.DARK_GRAY);
		comboPerfil.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboPerfil.setBounds(110, 153, 200, 25);
		frmAltaUsuario.getContentPane().add(comboPerfil);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(Color.DARK_GRAY);
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasea.setBounds(20, 200, 80, 20);
		frmAltaUsuario.getContentPane().add(lblContrasea);

		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(110, 199, 200, 23);
		frmAltaUsuario.getContentPane().add(txtContraseña);


		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.DARK_GRAY);
		btnGuardar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Perfil perfil = (Perfil) comboPerfil.getSelectedItem();
				String nombre1 = txtNombre1.getText();
				String apellido1 = txtApellido1.getText();
				String apellido2 = txtApellido2.getText();
				String usuario = txtUsuario.getText();
				String contraseña = txtContraseña.getText();
				String nombre = txtNombre1.getText() + " " + txtNombre2.getText();
				String apellido = txtApellido1.getText() + " " + txtApellido2.getText();

				Usuario usu = new Usuario(0, usuario, nombre, apellido, perfil, contraseña);

				if(nombre1.isEmpty() || apellido1.isEmpty() || apellido2.isEmpty() || contraseña.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Es necesario ingresar todos los datos requeridos",
							"Datos incompletos!", JOptionPane.WARNING_MESSAGE);
				}else if(nombre.length()>50) {
					JOptionPane.showMessageDialog(null, "El nombre debe contener menos de 50 caracteres",
							"Datos incorrectos!", JOptionPane.WARNING_MESSAGE);

					txtNombre1.setText("");
					txtNombre2.setText("");
				}else if(apellido.length()>50) {
					JOptionPane.showMessageDialog(null, "El apellido debe contener menos de 50 caracteres",
							"Datos incorrectos!", JOptionPane.WARNING_MESSAGE);

					txtApellido1.setText("");
					txtApellido2.setText("");
				}else if(contraseña.length()<8 || contraseña.length()>16) {
					JOptionPane.showMessageDialog(null, "La contraseña debe contener entre 8 y 16 caracteres",
							"Datos incorrectos!", JOptionPane.WARNING_MESSAGE);

					txtContraseña.setText("");
				}else {
					try {
						UsuarioBeanRemote altausuariobean = (UsuarioBeanRemote) InitialContext
								.doLookup("ProyectoEJB/UsuarioBean!com.servicios.UsuarioBeanRemote");

						altausuariobean.ingresarUsuario(usu); 

						JOptionPane.showMessageDialog(null, "Usuario creado correctamente!\n Su usuario es: " + usuario, "USUARIO CREADO", JOptionPane.INFORMATION_MESSAGE);

						txtNombre1.setText("");
						txtNombre2.setText("");
						txtApellido1.setText("");
						txtApellido2.setText("");
						txtUsuario.setText("");
						txtContraseña.setText("");
						txtNombre1.requestFocus();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGuardar.setBounds(210, 242, 100, 35);
		frmAltaUsuario.getContentPane().add(btnGuardar);


		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAltaUsuario.dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setBounds(100, 242, 100, 35);
		frmAltaUsuario.getContentPane().add(btnCancelar);

		JLabel lblAsterisco = new JLabel("*");
		lblAsterisco.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco.setForeground(Color.DARK_GRAY);
		lblAsterisco.setBounds(313, 18, 11, 20);
		frmAltaUsuario.getContentPane().add(lblAsterisco);

		JLabel lblAsterisco1 = new JLabel("*");
		lblAsterisco1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco1.setForeground(Color.DARK_GRAY);
		lblAsterisco1.setBounds(313, 63, 11, 20);
		frmAltaUsuario.getContentPane().add(lblAsterisco1);

		JLabel lblAsterisco2 = new JLabel("*");
		lblAsterisco2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco2.setForeground(Color.DARK_GRAY);
		lblAsterisco2.setBounds(313, 110, 11, 20);
		frmAltaUsuario.getContentPane().add(lblAsterisco2);

		JLabel lblAsterisco3 = new JLabel("*");
		lblAsterisco3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco3.setForeground(Color.DARK_GRAY);
		lblAsterisco3.setBounds(313, 153, 11, 20);
		frmAltaUsuario.getContentPane().add(lblAsterisco3);

		JLabel lblAsterisco4 = new JLabel("*");
		lblAsterisco4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco4.setForeground(Color.DARK_GRAY);
		lblAsterisco4.setBounds(313, 199, 11, 20);
		frmAltaUsuario.getContentPane().add(lblAsterisco4);
	}

	public String generarUsuario(String nombre1, String apellido1, String apellido2){
		String usuario = "";

		try {		
			usuario = nombre1.toLowerCase() + "." + apellido1.toLowerCase();
			txtUsuario.setText(usuario);

			UsuarioBeanRemote altausuariobean = (UsuarioBeanRemote) InitialContext
					.doLookup("ProyectoEJB/UsuarioBean!com.servicios.UsuarioBeanRemote");

			LinkedList<Usuario> usuarios = altausuariobean.obtenerTodosUsuarios();

			for(int i= 0; i<usuarios.size(); i++) {

				if(usuarios.get(i).getUsuario().equals(nombre1.toLowerCase() + "." + apellido1.toLowerCase())){
					usuario = usuario + "." + apellido2.toLowerCase().charAt(0);
					txtUsuario.setText(usuario);
				}else if(usuarios.get(i).getUsuario().equals(nombre1.toLowerCase() + "." + apellido1.toLowerCase() + "." + apellido2.toLowerCase().charAt(0))) {
					JOptionPane.showMessageDialog(null, "Usuario ya existente\nNo se puede crear otro igual");

					txtUsuario.setText("");
				}
			}
			return usuario;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Es necesario completar los campos nombre y apellido");
		}
		return usuario;
	}
}