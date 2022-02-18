package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import enums.TipoRegistro;
import objetos.Peso;
import objetos.Ternera;
import com.servicios.PesoBeanRemote;
import com.servicios.TerneraBeanRemote;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class FrameGananciaPeso {

	private JFrame frmGananciaPeso;
	private JTextField txtPeso;
	private JTable tablaTerneras;

	DefaultTableModel model;
	TableRowSorter<TableModel> ordenarTabla;

	public Window getGananciaPeso() {
		return frmGananciaPeso;
	}

	/**
	 * Create the application.
	 */
	public FrameGananciaPeso() {
		initialize();

		frmGananciaPeso.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmGananciaPeso = new JFrame();
		frmGananciaPeso.setTitle("Ganancia de Peso");
		frmGananciaPeso.setResizable(false);
		frmGananciaPeso.setBounds(100, 100, 515, 320);
		frmGananciaPeso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGananciaPeso.getContentPane().setLayout(null);

		JLabel lblTipoRegistro = new JLabel("Tipo de Registro");
		lblTipoRegistro.setForeground(Color.DARK_GRAY);
		lblTipoRegistro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoRegistro.setBounds(25, 25, 125, 20);
		frmGananciaPeso.getContentPane().add(lblTipoRegistro);

		JComboBox comboTipoRegistro = new JComboBox();
		comboTipoRegistro.setForeground(Color.DARK_GRAY);
		comboTipoRegistro.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboTipoRegistro.setModel(new DefaultComboBoxModel(TipoRegistro.values()));
		comboTipoRegistro.setBounds(155, 23, 150, 25);
		frmGananciaPeso.getContentPane().add(comboTipoRegistro);

		JLabel lblFechaRegistro = new JLabel("Fecha de Registro");
		lblFechaRegistro.setForeground(Color.DARK_GRAY);
		lblFechaRegistro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaRegistro.setBounds(25, 70, 125, 20);
		frmGananciaPeso.getContentPane().add(lblFechaRegistro);

		JDateChooser fechaRegistro = new JDateChooser();
		fechaRegistro.setDateFormatString("dd/MM/yyyy");
		fechaRegistro.setForeground(Color.DARK_GRAY);
		fechaRegistro.setBounds(155, 68, 150, 25);
		frmGananciaPeso.getContentPane().add(fechaRegistro);

		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setForeground(Color.DARK_GRAY);
		lblPeso.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPeso.setBounds(25, 120, 60, 20);
		frmGananciaPeso.getContentPane().add(lblPeso);

		txtPeso = new JTextField();
		txtPeso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char numero = e.getKeyChar();
				if (((numero<'0') || (numero>'9')) && (numero != KeyEvent.VK_BACK_SPACE) && (numero != '.')) {
					e.consume();
				}
				if(numero == '.' && txtPeso.getText().contains(".")) {
					e.consume();
				}
			}
		});
		txtPeso.setForeground(Color.DARK_GRAY);
		txtPeso.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPeso.setBounds(80, 119, 60, 24);
		frmGananciaPeso.getContentPane().add(txtPeso);
		txtPeso.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(325, 0, 184, 291);
		frmGananciaPeso.getContentPane().add(scrollPane);

		tablaTerneras = new JTable();
		tablaTerneras.setForeground(Color.DARK_GRAY);
		tablaTerneras.setFont(new Font("Tahoma", Font.BOLD, 13));
		tablaTerneras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaTerneras.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablaTerneras);
		try {
			cargarTabla();
		} catch (NamingException e1) {
			e1.printStackTrace();
		}


		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(txtPeso.getText().isEmpty() || fechaRegistro.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Es necesario ingresar todos los datos requeridos",
							"Datos incompletos!", JOptionPane.INFORMATION_MESSAGE);
				}else if(tablaTerneras.getSelectedRow()<0){
					JOptionPane.showMessageDialog(null, "Debe seleccionar una ternera de la tabla",
							"Error!", JOptionPane.INFORMATION_MESSAGE);
				}else {
					long idTernera = Long.parseLong(tablaTerneras.getModel().getValueAt(tablaTerneras.getSelectedRow(), 0).toString());
					String tipoRegistro = comboTipoRegistro.getSelectedItem().toString();
					Date fecRegistro = fechaRegistro.getDate();
					double peso = Double.parseDouble(txtPeso.getText());

					try {
						TerneraBeanRemote ternerabean = (TerneraBeanRemote) InitialContext
								.doLookup("ProyectoEJB/TerneraBean!com.servicios.TerneraBeanRemote");

						Ternera ternera = ternerabean.obtenerPorId(idTernera);

						Peso p = new Peso(TipoRegistro.valueOf(tipoRegistro), fecRegistro, peso, ternera);

						double ganancia = calcularPeso(idTernera, peso);
						DecimalFormat df = new DecimalFormat("0000.00");

						try {
							PesoBeanRemote pesobean = (PesoBeanRemote) InitialContext
									.doLookup("ProyectoEJB/PesoBean!com.servicios.PesoBeanRemote");

							pesobean.ingresarPeso(p);

							JOptionPane.showMessageDialog(null, "Peso ingresado correctamente", "PESO INGRESADO",
									JOptionPane.INFORMATION_MESSAGE);

							if(ganancia>0) {
								JOptionPane.showMessageDialog(null, "La ternera ha aumentado " + df.format(ganancia) + " KG desde el ultimo registro",
										"PESO INGRESADO", JOptionPane.INFORMATION_MESSAGE);
							}else if(ganancia==0) {
								JOptionPane.showMessageDialog(null, "El peso de la ternera no ha variado desde el ultimo registro",
										"PESO INGRESADO", JOptionPane.INFORMATION_MESSAGE);
							}else {
								ganancia *= -1;
								JOptionPane.showMessageDialog(null, "La ternera ha perdido " + df.format(ganancia) + " KG desde el ultimo registro",
										"PESO INGRESADO", JOptionPane.INFORMATION_MESSAGE);
							}

							txtPeso.setText("");
							fechaRegistro.setCalendar(null);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
						}
					} catch (NamingException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnGuardar.setForeground(Color.DARK_GRAY);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGuardar.setBounds(25, 165, 100, 35);
		frmGananciaPeso.getContentPane().add(btnGuardar);

		JLabel lblAsterisco = new JLabel("*");
		lblAsterisco.setForeground(Color.DARK_GRAY);
		lblAsterisco.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco.setBounds(307, 23, 8, 20);
		frmGananciaPeso.getContentPane().add(lblAsterisco);

		JLabel lblAsterisco1 = new JLabel("*");
		lblAsterisco1.setForeground(Color.DARK_GRAY);
		lblAsterisco1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco1.setBounds(307, 68, 8, 20);
		frmGananciaPeso.getContentPane().add(lblAsterisco1);

		JLabel lblAsterisco2 = new JLabel("*");
		lblAsterisco2.setForeground(Color.DARK_GRAY);
		lblAsterisco2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsterisco2.setBounds(142, 120, 8, 20);
		frmGananciaPeso.getContentPane().add(lblAsterisco2);
	}

	@SuppressWarnings("serial")
	public void cargarTabla() throws NamingException {

		TerneraBeanRemote ternerabean = (TerneraBeanRemote) InitialContext
				.doLookup("ProyectoEJB/TerneraBean!com.servicios.TerneraBeanRemote");

		PesoBeanRemote pesobean = (PesoBeanRemote) InitialContext
				.doLookup("ProyectoEJB/PesoBean!com.servicios.PesoBeanRemote");

		LinkedList<Ternera> terneras = ternerabean.obtenerTodasTerneras();

		String[] columnas = new String[] {"ID", "Caravana", "Peso"};

		Object[][] datos = new Object[terneras.size()][3];

		int fila = 0;

		for(Ternera t : terneras){
			datos[fila][0] = t.getIdTernera();
			datos[fila][1] = t.getNumCaravana();
			LinkedList<Peso> pesos = pesobean.obtenerPorTernera(t.getIdTernera());
			if(pesos.isEmpty()) {
				datos[fila][2] = t.getPesoNacimiento();
			}else {
				for(Peso p : pesos) {
					datos[fila][2] = p.getPeso();
				}
			}
			fila++;
		}

		model = new DefaultTableModel(datos, columnas) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				tablaTerneras.getColumnModel().getColumn(0).setPreferredWidth(25);
				tablaTerneras.getColumnModel().getColumn(0).setMaxWidth(25);
				tablaTerneras.getColumnModel().getColumn(2).setMaxWidth(40);
				return String.class;
			}
		};
		tablaTerneras.setModel(model);

		ordenarTabla = new TableRowSorter<TableModel>(model);
		tablaTerneras.setRowSorter(ordenarTabla);
	}

	public double calcularPeso(long idTernera, double peso) throws NamingException {
		PesoBeanRemote pesobean = (PesoBeanRemote) InitialContext
				.doLookup("ProyectoEJB/PesoBean!com.servicios.PesoBeanRemote");

		LinkedList<Peso> pesos = pesobean.obtenerPorTernera(idTernera);

		TerneraBeanRemote ternerabean = (TerneraBeanRemote) InitialContext
				.doLookup("ProyectoEJB/TerneraBean!com.servicios.TerneraBeanRemote");

		Ternera ternera = ternerabean.obtenerPorId(idTernera);
		
		double ganancia = 0;

		if(pesos.isEmpty()) {
			ganancia = peso-ternera.getPesoNacimiento();
		}else {
			ganancia = peso-pesos.get(pesos.size()-1).getPeso();;
		}
		return ganancia;
	}
}