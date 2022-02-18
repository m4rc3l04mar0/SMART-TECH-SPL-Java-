package interfaz;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.servicios.AlimentoBeanRemote;

import objetos.Alimento;

import java.awt.Font;

import javax.naming.InitialContext;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrameModificarAlimento extends JFrame {

	JFrame frmModificarAlimento;
	JPanel contentPane;
	JTextField txtNombre;
	JTextField txtCosto;
	JTextField txtCantidad;
	JTextField txtIdAlimento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alimento a = new Alimento();
					FrameModificarAlimento window = new FrameModificarAlimento(a);
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
	public FrameModificarAlimento(Alimento alimento) {
		initialize(alimento);

		setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Alimento alimento) {
		setTitle("Modificar Alimento");
		setResizable(false);
		setBounds(100, 100, 345, 275);
		contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIdAlimento = new JLabel("ID Alimento");
		lblIdAlimento.setForeground(Color.DARK_GRAY);
		lblIdAlimento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIdAlimento.setBounds(20, 20, 150, 20);
		contentPane.add(lblIdAlimento);

		txtIdAlimento = new JTextField();
		txtIdAlimento.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtIdAlimento.setForeground(Color.DARK_GRAY);
		txtIdAlimento.setEditable(false);
		txtIdAlimento.setColumns(10);
		txtIdAlimento.setBackground(Color.WHITE);
		txtIdAlimento.setBounds(175, 18, 40, 24);
		contentPane.add(txtIdAlimento);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.DARK_GRAY);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(20, 65, 150, 20);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNombre.setForeground(Color.DARK_GRAY);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(175, 63, 140, 24);
		contentPane.add(txtNombre);

		JLabel lblCostoPorUnidad = new JLabel("Costo por Unidad");
		lblCostoPorUnidad.setForeground(Color.DARK_GRAY);
		lblCostoPorUnidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCostoPorUnidad.setBounds(20, 110, 150, 20);
		contentPane.add(lblCostoPorUnidad);

		txtCosto = new JTextField();
		txtCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char numero = e.getKeyChar();
				if (((numero<'0') || (numero>'9')) && (numero != KeyEvent.VK_BACK_SPACE) && (numero != '.')) {
					e.consume();
				}
				if(numero == '.' && txtCosto.getText().contains(".")) {
					e.consume();
				}
			}
		});
		txtCosto.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCosto.setForeground(Color.DARK_GRAY);
		txtCosto.setColumns(10);
		txtCosto.setBounds(175, 108, 140, 24);
		contentPane.add(txtCosto);

		JLabel lblCantidadDisponible = new JLabel("Cantidad Disponible");
		lblCantidadDisponible.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadDisponible.setForeground(Color.DARK_GRAY);
		lblCantidadDisponible.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCantidadDisponible.setBounds(20, 155, 150, 20);
		contentPane.add(lblCantidadDisponible);

		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char numero = e.getKeyChar();
				if (((numero<'0') || (numero>'9')) && (numero != KeyEvent.VK_BACK_SPACE) && (numero != '.')) {
					e.consume();
				}
				if(numero == '.' && txtCantidad.getText().contains(".")) {
					e.consume();
				}
			}
		});
		txtCantidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCantidad.setForeground(Color.DARK_GRAY);
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(175, 153, 140, 24);
		contentPane.add(txtCantidad);

		txtIdAlimento.setText(String.valueOf(alimento.getIdAlimento()));
		txtNombre.setText(alimento.getNombre());
		txtCosto.setText(String.valueOf(alimento.getCostoUnidad()));
		txtCantidad.setText(String.valueOf(alimento.getCantidad()));


		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setBounds(111, 195, 100, 35);
		contentPane.add(btnCancelar);


		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double costo = Double.parseDouble(txtCosto.getText().toString());
				double cantidad = Double.parseDouble(txtCantidad.getText().toString());

				alimento.setCostoUnidad(costo);
				alimento.setCantidad(cantidad);

				if(txtCosto.getText().isEmpty() || txtCantidad.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Es necesario ingresar todos los datos requeridos",
							"Datos incompletos!", JOptionPane.INFORMATION_MESSAGE);
				}else {

					try {
						AlimentoBeanRemote alimentobean = (AlimentoBeanRemote) InitialContext
								.doLookup("ProyectoEJB/AlimentoBean!com.servicios.AlimentoBeanRemote");

						alimentobean.editarAlimento(alimento);

						JOptionPane.showMessageDialog(null, "Alimento modificado correctamente",
								"ALIMENTO MODIFICADO", JOptionPane.INFORMATION_MESSAGE);

						cerrarVentana();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnGuardar.setForeground(Color.DARK_GRAY);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGuardar.setBounds(221, 195, 100, 35);
		contentPane.add(btnGuardar);

		JLabel lblAsterisco = new JLabel("*");
		lblAsterisco.setForeground(Color.DARK_GRAY);
		lblAsterisco.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco.setBounds(317, 108, 12, 20);
		contentPane.add(lblAsterisco);

		JLabel lblAsterisco1 = new JLabel("*");
		lblAsterisco1.setForeground(Color.DARK_GRAY);
		lblAsterisco1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco1.setBounds(317, 153, 12, 20);
		contentPane.add(lblAsterisco1);
	}

	public void cerrarVentana(){
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}