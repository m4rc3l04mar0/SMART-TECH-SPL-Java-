package interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Color;

public class FramePrincipal {

	private JFrame frmPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipal window = new FramePrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FramePrincipal() {
		initialize();

		frmPrincipal.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Principal");
		frmPrincipal.setBounds(100, 100, 600, 450);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);


		JMenu mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setForeground(Color.DARK_GRAY);
		mnUsuarios.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(mnUsuarios);

		JMenuItem mntmNuevoUsuario = new JMenuItem("Nuevo Usuario");
		mntmNuevoUsuario.setForeground(Color.DARK_GRAY);
		mntmNuevoUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		mntmNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameAltaUsuario frmAltaUsuario = new FrameAltaUsuario();
				frmAltaUsuario.getAltaUsuario().setVisible(true);
			}
		});
		mnUsuarios.add(mntmNuevoUsuario);

		JMenuItem mntmListarUsuarios = new JMenuItem("Lista de Usuarios");
		mntmListarUsuarios.setForeground(Color.DARK_GRAY);
		mntmListarUsuarios.setFont(new Font("Tahoma", Font.BOLD, 14));
		mntmListarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameListarUsuarios frmBuscarUsuarios = new FrameListarUsuarios();
				frmBuscarUsuarios.getBuscarUsuarios().setVisible(true);
			}
		});
		mnUsuarios.add(mntmListarUsuarios);


		JMenu mnAlimentos = new JMenu("Alimentos");
		mnAlimentos.setForeground(Color.DARK_GRAY);
		mnAlimentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(mnAlimentos);

		JMenuItem mntmNuevoAlimento = new JMenuItem("Nuevo Alimento");
		mntmNuevoAlimento.setForeground(Color.DARK_GRAY);
		mntmNuevoAlimento.setFont(new Font("Tahoma", Font.BOLD, 14));
		mntmNuevoAlimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameAltaAlimento frmAltaAlimento = new FrameAltaAlimento();
				frmAltaAlimento.getAltaAlimento().setVisible(true);
			}
		});
		mnAlimentos.add(mntmNuevoAlimento);

		JMenuItem mntmListarAlimentos = new JMenuItem("Lista de Alimentos");
		mntmListarAlimentos.setForeground(Color.DARK_GRAY);
		mntmListarAlimentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		mntmListarAlimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameListarAlimentos frmListarAlimentos = new FrameListarAlimentos();
				frmListarAlimentos.getListarAlimentos().setVisible(true);
			}
		});
		mnAlimentos.add(mntmListarAlimentos);


		JMenu mnTerneras = new JMenu("Terneras");
		mnTerneras.setForeground(Color.DARK_GRAY);
		mnTerneras.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(mnTerneras);

		JMenuItem mntmConsumoAlimentos = new JMenuItem("Consumo de Alimentos");
		mntmConsumoAlimentos.setForeground(Color.DARK_GRAY);
		mntmConsumoAlimentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		mntmConsumoAlimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameConsumoAlimentoTernera frmConsumoAlimentos = new FrameConsumoAlimentoTernera();
				frmConsumoAlimentos.getConsumoAlimentos().setVisible(true);
			}
		});
		mnTerneras.add(mntmConsumoAlimentos);

		JMenuItem mntmGananciaDePeso = new JMenuItem("Ganancia de Peso");
		mnTerneras.add(mntmGananciaDePeso);
		mntmGananciaDePeso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameGananciaPeso frmGananciaPeso = new FrameGananciaPeso();
				frmGananciaPeso.getGananciaPeso().setVisible(true);
			}
		});
		mntmGananciaDePeso.setForeground(Color.DARK_GRAY);
		mntmGananciaDePeso.setFont(new Font("Tahoma", Font.BOLD, 14));


		JMenu mnInformes = new JMenu("Informes");
		mnInformes.setForeground(Color.DARK_GRAY);
		mnInformes.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(mnInformes);

		JMenuItem mntmEventosClinicos = new JMenuItem("Eventos Clinicos");
		mntmEventosClinicos.setForeground(Color.DARK_GRAY);
		mntmEventosClinicos.setFont(new Font("Tahoma", Font.BOLD, 14));
		mntmEventosClinicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameEventosClinicos frmEventosClinicos = new FrameEventosClinicos();
				frmEventosClinicos.getEventosClinicos().setVisible(true);
			}
		});
		mnInformes.add(mntmEventosClinicos);
	}
}