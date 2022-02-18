package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.servicios.AlimentoBeanRemote;
import com.servicios.UnidadBeanRemote;
import objetos.Alimento;
import objetos.Unidad;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.naming.InitialContext;
import javax.swing.DefaultComboBoxModel;
import enums.TipoUnidad;

public class FrameAltaAlimento {

	private JFrame frmAltaAlimento;
	private JTextField txtNombre;
	private JTextField txtCostoPorUnidad;
	private JTextField txtCantidadDisponible;

	public Window getAltaAlimento(){
		return frmAltaAlimento;
	}

	/**
	 * Create the application.
	 */
	public FrameAltaAlimento() {
		initialize();

		frmAltaAlimento.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes"})
	private void initialize() {
		frmAltaAlimento = new JFrame();
		frmAltaAlimento.setResizable(false);
		frmAltaAlimento.setTitle("Alta de Alimento");
		frmAltaAlimento.setBounds(100, 100, 345, 280);
		frmAltaAlimento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAltaAlimento.getContentPane().setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.DARK_GRAY);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(20, 20, 150, 20);
		frmAltaAlimento.getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNombre.setForeground(Color.DARK_GRAY);
		txtNombre.setBounds(175, 18, 140, 24);
		frmAltaAlimento.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblCostoPorUnidad = new JLabel("Costo por Unidad");
		lblCostoPorUnidad.setForeground(Color.DARK_GRAY);
		lblCostoPorUnidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCostoPorUnidad.setBounds(22, 65, 150, 20);
		frmAltaAlimento.getContentPane().add(lblCostoPorUnidad);

		txtCostoPorUnidad = new JTextField();
		txtCostoPorUnidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCostoPorUnidad.setForeground(Color.DARK_GRAY);
		txtCostoPorUnidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char numero = e.getKeyChar();
				if (((numero<'0') || (numero>'9')) && (numero != KeyEvent.VK_BACK_SPACE) && (numero != '.')) {
					e.consume();
				}
				if(numero == '.' && txtCostoPorUnidad.getText().contains(".")) {
					e.consume();
				}
			}
		});
		txtCostoPorUnidad.setBounds(175, 63, 140, 24);
		frmAltaAlimento.getContentPane().add(txtCostoPorUnidad);
		txtCostoPorUnidad.setColumns(10);

		JLabel lblCantidadDisponible = new JLabel("Cantidad Disponible");
		lblCantidadDisponible.setForeground(Color.DARK_GRAY);
		lblCantidadDisponible.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCantidadDisponible.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadDisponible.setBounds(20, 110, 150, 20);
		frmAltaAlimento.getContentPane().add(lblCantidadDisponible);

		txtCantidadDisponible = new JTextField();
		txtCantidadDisponible.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCantidadDisponible.setForeground(Color.DARK_GRAY);
		txtCantidadDisponible.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char numero = e.getKeyChar();
				if (((numero<'0') || (numero>'9')) && (numero != KeyEvent.VK_BACK_SPACE) && (numero != '.')) {
					e.consume();
				}
				if(numero == '.' && txtCantidadDisponible.getText().contains(".")) {
					e.consume();
				}
			}
		});
		txtCantidadDisponible.setBounds(175, 108, 140, 24);
		frmAltaAlimento.getContentPane().add(txtCantidadDisponible);
		txtCantidadDisponible.setColumns(10);


		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAltaAlimento.dispose();
			}
		});

		JLabel lblUnidad = new JLabel("Unidad");
		lblUnidad.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUnidad.setForeground(Color.DARK_GRAY);
		lblUnidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnidad.setBounds(20, 155, 60, 20);
		frmAltaAlimento.getContentPane().add(lblUnidad);

		JComboBox comboUnidad = new JComboBox();
		comboUnidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboUnidad.setForeground(Color.DARK_GRAY);
		comboUnidad.setModel(new DefaultComboBoxModel(TipoUnidad.values()));
		comboUnidad.setBounds(80, 153, 80, 24);
		frmAltaAlimento.getContentPane().add(comboUnidad);
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setBounds(105, 194, 100, 35);
		frmAltaAlimento.getContentPane().add(btnCancelar);


		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(txtNombre.getText().isEmpty() || txtCostoPorUnidad.getText().isEmpty() || txtCantidadDisponible.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Es necesario ingresar todos los datos requeridos",
							"Datos incompletos!", JOptionPane.INFORMATION_MESSAGE);
				}else if(txtNombre.getText().length()>25) {
					JOptionPane.showMessageDialog(null, "El nombre debe contener menos de 25 caracteres",
							"Datos incorrectos!", JOptionPane.WARNING_MESSAGE);

					txtNombre.setText("");
				}else if(txtCostoPorUnidad.getText().length()>5) {
					JOptionPane.showMessageDialog(null, "El costo debe contener menos de 5 digitos",
							"Datos incorrectos!", JOptionPane.WARNING_MESSAGE);

					txtCostoPorUnidad.setText("");
				}else if(txtCantidadDisponible.getText().length()>5) {
					JOptionPane.showMessageDialog(null, "La cantidad debe ser menor a 99999",
							"Datos incorrectos!", JOptionPane.WARNING_MESSAGE);

					txtCostoPorUnidad.setText("");
				}else{

					String nombre = txtNombre.getText();
					double costoPorUnidad = Double.parseDouble(txtCostoPorUnidad.getText());
					double cantidadDisponible = Double.parseDouble(txtCantidadDisponible.getText());
					String uni = comboUnidad.getSelectedItem().toString();
					long idUnidad = 0;

					if(uni.equals("KG")) {
						idUnidad = 1;
					}
					else if(uni.equals("L")) {
						idUnidad = 2;
					}

					try {
						UnidadBeanRemote unidadbean = (UnidadBeanRemote) InitialContext
								.doLookup("ProyectoEJB/UnidadBean!com.servicios.UnidadBeanRemote");

						Unidad unidad = unidadbean.obtenerUnidad(idUnidad);

						Alimento alimento = new Alimento(1, nombre, costoPorUnidad, cantidadDisponible, unidad);

						AlimentoBeanRemote alimentobean = (AlimentoBeanRemote) InitialContext
								.doLookup("ProyectoEJB/AlimentoBean!com.servicios.AlimentoBeanRemote");

						alimentobean.ingresarAlimento(alimento);

						JOptionPane.showMessageDialog(null, "Alimento ingresado correctamente!", "ALIMENTO INGRESADO", JOptionPane.INFORMATION_MESSAGE);

						txtNombre.setText("");
						txtCostoPorUnidad.setText("");
						txtCantidadDisponible.setText("");
						txtNombre.requestFocus();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnGuardar.setForeground(Color.DARK_GRAY);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGuardar.setBounds(215, 194, 100, 35);
		frmAltaAlimento.getContentPane().add(btnGuardar);

		JLabel lblAsterisco = new JLabel("*");
		lblAsterisco.setForeground(Color.DARK_GRAY);
		lblAsterisco.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco.setBounds(318, 18, 11, 20);
		frmAltaAlimento.getContentPane().add(lblAsterisco);

		JLabel lblAsterisco1 = new JLabel("*");
		lblAsterisco1.setForeground(Color.DARK_GRAY);
		lblAsterisco1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco1.setBounds(318, 63, 11, 20);
		frmAltaAlimento.getContentPane().add(lblAsterisco1);

		JLabel lblAsterisco2 = new JLabel("*");
		lblAsterisco2.setForeground(Color.DARK_GRAY);
		lblAsterisco2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco2.setBounds(318, 108, 11, 20);
		frmAltaAlimento.getContentPane().add(lblAsterisco2);

		JLabel lblAsterisco3 = new JLabel("*");
		lblAsterisco3.setForeground(Color.DARK_GRAY);
		lblAsterisco3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco3.setBounds(163, 151, 20, 24);
		frmAltaAlimento.getContentPane().add(lblAsterisco3);
	}
}