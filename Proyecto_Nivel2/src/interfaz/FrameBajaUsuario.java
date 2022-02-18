package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;

import javax.naming.InitialContext;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import com.servicios.UsuarioBeanRemote;

import objetos.Usuario;

@SuppressWarnings("serial")
public class FrameBajaUsuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the application.
	 */
	public FrameBajaUsuario(Usuario usuario) {
		initialize(usuario);

		setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Usuario usuario) {
		setTitle("Eliminar Usuario");
		setResizable(false);
		setBounds(100, 100, 345, 155);
		contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMensaje = new JLabel("Seguro que desea eliminar el usuario seleccionado?");
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setForeground(Color.DARK_GRAY);
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMensaje.setBounds(10, 11, 319, 56);
		contentPane.add(lblMensaje);


		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try{
					Usuario usu = new Usuario(usuario.getIdUsuario());

					UsuarioBeanRemote usuariobean = (UsuarioBeanRemote) InitialContext
							.doLookup("ProyectoEJB/UsuarioBean!com.servicios.UsuarioBeanRemote");
					usuariobean.eliminarUsuario(usu);

					JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");

					cerrarVentana();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminar.setForeground(Color.DARK_GRAY);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEliminar.setBounds(229, 78, 100, 35);
		contentPane.add(btnEliminar);


		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setBounds(119, 78, 100, 35);
		contentPane.add(btnCancelar);
	}

	public void cerrarVentana(){
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}