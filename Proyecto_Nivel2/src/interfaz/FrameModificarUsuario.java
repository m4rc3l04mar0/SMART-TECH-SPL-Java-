package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.naming.InitialContext;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.EventQueue;

import com.servicios.UsuarioBeanRemote;

import objetos.Usuario;

@SuppressWarnings("serial")
public class FrameModificarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtPerfil;
	private JPasswordField txtContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario u = new Usuario();
					FrameModificarUsuario window = new FrameModificarUsuario(u);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameModificarUsuario(Usuario usuario) {
		initialize(usuario);

		setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Usuario usuario) {
		setTitle("Modificar Usuario");
		setResizable(false);
		setBounds(100, 100, 340, 325);
		contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setForeground(Color.DARK_GRAY);
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombres.setBounds(20, 20, 70, 20);
		contentPane.add(lblNombres);

		txtNombre = new JTextField();
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setEditable(false);
		txtNombre.setForeground(Color.DARK_GRAY);
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNombre.setColumns(10);
		txtNombre.setBounds(110, 18, 200, 23);
		contentPane.add(txtNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setForeground(Color.DARK_GRAY);
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApellidos.setBounds(20, 65, 70, 20);
		contentPane.add(lblApellidos);

		txtApellido = new JTextField();
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setEditable(false);
		txtApellido.setForeground(Color.DARK_GRAY);
		txtApellido.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtApellido.setColumns(10);
		txtApellido.setBounds(110, 63, 200, 23);
		contentPane.add(txtApellido);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.DARK_GRAY);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBounds(20, 110, 70, 20);
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setForeground(Color.DARK_GRAY);
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtUsuario.setBackground(Color.WHITE);
		txtUsuario.setEditable(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(110, 110, 200, 23);
		contentPane.add(txtUsuario);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setForeground(Color.DARK_GRAY);
		lblPerfil.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPerfil.setBounds(20, 155, 70, 20);
		contentPane.add(lblPerfil);

		txtPerfil = new JTextField();
		txtPerfil.setForeground(Color.DARK_GRAY);
		txtPerfil.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPerfil.setEditable(false);
		txtPerfil.setColumns(10);
		txtPerfil.setBackground(Color.WHITE);
		txtPerfil.setBounds(110, 153, 200, 23);
		contentPane.add(txtPerfil);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(Color.DARK_GRAY);
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasea.setBounds(20, 200, 80, 20);
		contentPane.add(lblContrasea);

		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(110, 199, 200, 23);
		contentPane.add(txtContraseña);

		txtNombre.setText(usuario.getNombre());
		txtApellido.setText(usuario.getApellido());
		txtUsuario.setText(usuario.getUsuario());
		txtPerfil.setText(usuario.getPerfil().toString());


		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.DARK_GRAY);
		btnGuardar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String contraseña = txtContraseña.getText();

				usuario.setContraseña(contraseña);

				if(contraseña.isEmpty()) {

					JOptionPane.showMessageDialog(null, "Es necesario ingresar todos los datos requeridos",
							"Datos incompletos!", JOptionPane.INFORMATION_MESSAGE);
				}else if(contraseña.length()<8 || contraseña.length()>16) {

					JOptionPane.showMessageDialog(null, "La contraseña debe contener entre 8 y 16 caracteres",
							"Datos incorrectos!", JOptionPane.WARNING_MESSAGE);
					
					txtContraseña.setText("");
				}else {
					try {
						UsuarioBeanRemote usuariobean = (UsuarioBeanRemote) InitialContext
								.doLookup("ProyectoEJB/UsuarioBean!com.servicios.UsuarioBeanRemote");

						usuariobean.editarUsuario(usuario);

						JOptionPane.showMessageDialog(null, "Contraseña modificada correctamente",
								"CONTRASEÑA MODIFICADA", JOptionPane.INFORMATION_MESSAGE);

						cerrarVentana();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGuardar.setBounds(210, 242, 100, 35);
		contentPane.add(btnGuardar);


		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setBounds(100, 242, 100, 35);
		contentPane.add(btnCancelar);

		JLabel lblAsterisco = new JLabel("*");
		lblAsterisco.setForeground(Color.DARK_GRAY);
		lblAsterisco.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco.setBounds(312, 199, 12, 20);
		contentPane.add(lblAsterisco);
	}

	public void cerrarVentana(){
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}