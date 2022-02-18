package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;
import java.util.Date;
import java.util.LinkedList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import com.servicios.AlimentoBeanRemote;
import com.servicios.ConsumoBeanRemote;
import com.servicios.TerneraBeanRemote;

import objetos.Alimento;
import objetos.Consumo;
import objetos.Ternera;

import javax.swing.JComboBox;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class FrameConsumoAlimentoTernera {

	private JFrame frmConsumoAlimentos;
	private JTextField txtCantidad;
	private JTable tablaTerneras;

	DefaultTableModel model;
	TableRowSorter<TableModel> ordenarTabla;

	public Window getConsumoAlimentos() {
		return frmConsumoAlimentos;
	}

	/**
	 * Create the application.
	 */
	public FrameConsumoAlimentoTernera() {
		initialize();

		frmConsumoAlimentos.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmConsumoAlimentos = new JFrame();
		frmConsumoAlimentos.getContentPane().setForeground(Color.DARK_GRAY);
		frmConsumoAlimentos.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));
		frmConsumoAlimentos.setTitle("Consumo de Alimentos por Ternera");
		frmConsumoAlimentos.setResizable(false);
		frmConsumoAlimentos.setBounds(100, 100, 460, 240);
		frmConsumoAlimentos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmConsumoAlimentos.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(295, 11, 149, 190);
		frmConsumoAlimentos.getContentPane().add(scrollPane);

		tablaTerneras = new JTable();
		tablaTerneras.setForeground(Color.DARK_GRAY);
		tablaTerneras.setFont(new Font("Tahoma", Font.BOLD, 13));
		tablaTerneras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaTerneras.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablaTerneras);
		try {
			cargarTabla();
		} catch (NamingException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}

		JLabel lblAlimento = new JLabel("Alimento");
		lblAlimento.setForeground(Color.DARK_GRAY);
		lblAlimento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlimento.setBounds(20, 23, 80, 20);
		frmConsumoAlimentos.getContentPane().add(lblAlimento);

		JComboBox comboAlimento = new JComboBox();
		comboAlimento.setForeground(Color.DARK_GRAY);
		comboAlimento.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboAlimento.setBounds(100, 21, 180, 24);
		frmConsumoAlimentos.getContentPane().add(comboAlimento);

		try {
			AlimentoBeanRemote alimentobean = (AlimentoBeanRemote) InitialContext
					.doLookup("ProyectoEJB/AlimentoBean!com.servicios.AlimentoBeanRemote");

			LinkedList<String> alimentos = alimentobean.obtenerNombreAlimentos();
			for(String alimento : alimentos){
				comboAlimento.addItem(alimento);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}

		JLabel lblCantidadConsumida = new JLabel("Cantidad Consumida");
		lblCantidadConsumida.setForeground(Color.DARK_GRAY);
		lblCantidadConsumida.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCantidadConsumida.setBounds(20, 68, 150, 20);
		frmConsumoAlimentos.getContentPane().add(lblCantidadConsumida);

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
		txtCantidad.setBounds(180, 66, 100, 24);
		frmConsumoAlimentos.getContentPane().add(txtCantidad);
		txtCantidad.setColumns(10);

		JLabel lblFechaDeConsumo = new JLabel("Fecha de Consumo");
		lblFechaDeConsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaDeConsumo.setForeground(Color.DARK_GRAY);
		lblFechaDeConsumo.setBounds(20, 113, 150, 20);
		frmConsumoAlimentos.getContentPane().add(lblFechaDeConsumo);

		JDateChooser fechaDeConsumo = new JDateChooser();
		fechaDeConsumo.getCalendarButton().setForeground(Color.DARK_GRAY);
		fechaDeConsumo.getCalendarButton().setFont(new Font("Tahoma", Font.BOLD, 13));
		fechaDeConsumo.setForeground(Color.DARK_GRAY);
		fechaDeConsumo.setDateFormatString("dd/MM/yyyy");
		fechaDeConsumo.setBounds(180, 111, 100, 24);
		frmConsumoAlimentos.getContentPane().add(fechaDeConsumo);


		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long idTernera = Long.parseLong(tablaTerneras.getModel().getValueAt(tablaTerneras.getSelectedRow(), 0).toString());
				String nombreAlimento = comboAlimento.getSelectedItem().toString();
				double cantidad = Double.parseDouble(txtCantidad.getText());
				Date fechaConsumo = fechaDeConsumo.getDate();

				try {
					TerneraBeanRemote ternerabean = (TerneraBeanRemote) InitialContext
							.doLookup("ProyectoEJB/TerneraBean!com.servicios.TerneraBeanRemote");

					Ternera ternera = ternerabean.obtenerPorId(idTernera);

					AlimentoBeanRemote alimentobean = (AlimentoBeanRemote) InitialContext
							.doLookup("ProyectoEJB/AlimentoBean!com.servicios.AlimentoBeanRemote");

					Alimento alimento = alimentobean.obtenerPorNombre(nombreAlimento);

					Consumo consumo = new Consumo(fechaConsumo, cantidad, alimento, ternera);

					if(txtCantidad.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Es necesario ingresar todos los datos requeridos",
								"Datos incompletos!", JOptionPane.INFORMATION_MESSAGE);
					}else if(tablaTerneras.getSelectedRow()<0){
						JOptionPane.showMessageDialog(null, "Debe seleccionar una ternera de la tabla",
								"Error!", JOptionPane.INFORMATION_MESSAGE);
					}else if(txtCantidad.getText().length()>5) {
						JOptionPane.showMessageDialog(null, "La cantidad debe contener menos de 5 digitos",
								"Datos incorrectos!", JOptionPane.WARNING_MESSAGE);

						txtCantidad.setText("");
					}else {
						try {
							ConsumoBeanRemote consumobean = (ConsumoBeanRemote) InitialContext
									.doLookup("ProyectoEJB/ConsumoBean!com.servicios.ConsumoBeanRemote");

							consumobean.ingresarConsumo(consumo);

							JOptionPane.showMessageDialog(null, "Consumo registrado correctamente", "CONSUMO INGRESADO", JOptionPane.INFORMATION_MESSAGE);

							alimento.setCantidad(modificarStock(alimento, cantidad));
							alimentobean.editarAlimento(alimento);

							txtCantidad.setText("");
							fechaDeConsumo.setCalendar(null);

						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				}catch (NamingException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setForeground(Color.DARK_GRAY);
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAceptar.setBounds(180, 157, 100, 35);
		frmConsumoAlimentos.getContentPane().add(btnAceptar);


		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmConsumoAlimentos.dispose();
			}
		});
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setBounds(70, 157, 100, 35);
		frmConsumoAlimentos.getContentPane().add(btnCancelar);

		JLabel lblAsterisco = new JLabel("*");
		lblAsterisco.setForeground(Color.DARK_GRAY);
		lblAsterisco.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco.setBounds(283, 21, 11, 20);
		frmConsumoAlimentos.getContentPane().add(lblAsterisco);

		JLabel lblAsterisco1 = new JLabel("*");
		lblAsterisco1.setForeground(Color.DARK_GRAY);
		lblAsterisco1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco1.setBounds(283, 66, 11, 20);
		frmConsumoAlimentos.getContentPane().add(lblAsterisco1);

		JLabel lblAsterisco2 = new JLabel("*");
		lblAsterisco2.setForeground(Color.DARK_GRAY);
		lblAsterisco2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco2.setBounds(283, 111, 11, 20);
		frmConsumoAlimentos.getContentPane().add(lblAsterisco2);
	}

	@SuppressWarnings("serial")
	public void cargarTabla() throws NamingException {
		TerneraBeanRemote ternerabean = (TerneraBeanRemote) InitialContext
				.doLookup("ProyectoEJB/TerneraBean!com.servicios.TerneraBeanRemote");

		LinkedList<Ternera> terneras = ternerabean.obtenerTodasTerneras();

		String[] columnas = new String[] { "ID", "Caravana" };

		Object[][] datos = new Object[terneras.size()][2];

		int fila = 0;

		for (Ternera t : terneras) {
			datos[fila][0] = t.getIdTernera();
			datos[fila][1] = t.getNumCaravana();
			fila++;
		}

		model = new DefaultTableModel(datos, columnas) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				tablaTerneras.getColumnModel().getColumn(0).setPreferredWidth(15);
				return String.class;
			}
		};
		tablaTerneras.setModel(model);

		ordenarTabla = new TableRowSorter<TableModel>(model);
		tablaTerneras.setRowSorter(ordenarTabla);
	}

	public double modificarStock(Alimento alimento, double cantidad) {

		double cantidadConsumida = cantidad;
		double cantidadInicial = alimento.getCantidad();
		double cantidadFinal = cantidadInicial - cantidadConsumida;

		return cantidadFinal;
	}
}