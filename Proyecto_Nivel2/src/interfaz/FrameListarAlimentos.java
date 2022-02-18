package interfaz;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.servicios.AlimentoBeanRemote;

import objetos.Alimento;

import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class FrameListarAlimentos extends JFrame {

	private JFrame frmListarAlimentos;
	private JTable tablaAlimentos;

	DefaultTableModel model;
	TableRowSorter<TableModel> ordenarTabla;

	public Window getListarAlimentos() {
		return frmListarAlimentos;
	}

	/**
	 * Create the application.
	 */
	public FrameListarAlimentos() {
		initialize();

		frmListarAlimentos.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListarAlimentos = new JFrame();
		frmListarAlimentos.setTitle("Lista de Alimentos");
		frmListarAlimentos.setResizable(false);
		frmListarAlimentos.setBounds(100, 100, 490, 350);
		frmListarAlimentos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmListarAlimentos.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 484, 264);
		frmListarAlimentos.getContentPane().add(scrollPane);

		tablaAlimentos = new JTable();
		tablaAlimentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaAlimentos.setForeground(Color.DARK_GRAY);
		tablaAlimentos.setFont(new Font("Tahoma", Font.BOLD, 13));
		tablaAlimentos.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablaAlimentos);
		try {
			cargarTabla();
		} catch (NamingException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}


		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tablaAlimentos.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un alimento de la tabla");
				}else {
					int row = tablaAlimentos.getSelectedRow();
					long idAlimento = Long.parseLong(tablaAlimentos.getModel().getValueAt(row, 0).toString());
					String nombre = tablaAlimentos.getModel().getValueAt(row, 1).toString();
					double costo = Double.parseDouble(tablaAlimentos.getModel().getValueAt(row, 2).toString());
					double cantidad = Double.parseDouble(tablaAlimentos.getModel().getValueAt(row, 3).toString());

					Alimento alim = new Alimento();
					alim.setIdAlimento(idAlimento);
					alim.setNombre(nombre);
					alim.setCostoUnidad(costo);
					alim.setCantidad(cantidad);

					FrameModificarAlimento frmModificarAlimento = new FrameModificarAlimento(alim);
					frmModificarAlimento.setVisible(true);

					frmModificarAlimento.addWindowListener(new java.awt.event.WindowAdapter() {
						@Override
						public void windowClosing(java.awt.event.WindowEvent windowEvent) {
							try {
								recargarPanel();
							} catch (NamingException e) {
								JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
							}
						}
					});
				}
			}
		});
		btnModificar.setForeground(Color.DARK_GRAY);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnModificar.setBounds(374, 275, 100, 35);
		frmListarAlimentos.getContentPane().add(btnModificar);


		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmListarAlimentos.dispose();
			}
		});
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setBounds(265, 275, 100, 35);
		frmListarAlimentos.getContentPane().add(btnCancelar);
	}

	public void cargarTabla() throws NamingException {

		AlimentoBeanRemote alimentobean = (AlimentoBeanRemote) InitialContext
				.doLookup("ProyectoEJB/AlimentoBean!com.servicios.AlimentoBeanRemote");

		LinkedList<Alimento> alimento = alimentobean.obtenerTodosAlimentos();

		String[] columnas = new String[] {"ID", "Nombre", "Costo Unidad", "Cantidad"};

		Object[][] datos = new Object[alimento.size()][4];

		int fila = 0;

		for(Alimento a : alimento){
			datos[fila][0] = a.getIdAlimento();
			datos[fila][1] = a.getNombre();
			datos[fila][2] = a.getCostoUnidad();
			datos[fila][3] = a.getCantidad();
			fila++;
		}

		model = new DefaultTableModel(datos, columnas) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				tablaAlimentos.getColumnModel().getColumn(0).setPreferredWidth(5);
				tablaAlimentos.getColumnModel().getColumn(0).setMinWidth(5);
				tablaAlimentos.getColumnModel().getColumn(1).setPreferredWidth(120);
				tablaAlimentos.getColumnModel().getColumn(1).setMinWidth(5);
				tablaAlimentos.getColumnModel().getColumn(2).setPreferredWidth(40);
				tablaAlimentos.getColumnModel().getColumn(3).setPreferredWidth(40);
				return String.class;
			}
		};
		tablaAlimentos.setModel(model);

		ordenarTabla = new TableRowSorter<TableModel>(model);
		tablaAlimentos.setRowSorter(ordenarTabla);
	}

	public void recargarPanel() throws NamingException{
		cargarTabla();
		this.revalidate();
		this.repaint();
	}
}