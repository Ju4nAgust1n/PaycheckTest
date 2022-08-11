package SumaBSSRL.main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.commons.io.FilenameUtils;

@SuppressWarnings("serial")
public class MainForm extends JFrame{
	
	String outputPath = System.getProperty("user.dir") + File.separator + "OutPut" + File.separator;
	String pdfFile = null;
	String imgFile = null;
	
	boolean pdfOk = false;
	boolean imgOk = false;
	
	public String getOutPutPath() {
		return this.outputPath;
	}
	
	public boolean findAllFilesInFolder(File folder) {
		
		insertImage obj;
		
		int i = 0;
		
		for (File file : folder.listFiles()) {
			
			if (!file.isDirectory()) {
				
				String ext = FilenameUtils.getExtension(file.getName());
				ext = ext.toUpperCase();
				
				if (ext == null) continue; //si es nula la extensión, salteamos el index
				
				if (!ext.equals("PDF")) {
					JOptionPane.showMessageDialog(null, "La carpeta contiene archivos que no son formato PDF");
					return false;
				}
				
				try {
					i++;
					obj = new insertImage(file.getAbsolutePath(),this.imgFile, this.outputPath + "Recibo" + i + ".pdf");
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
					return false;
				}
				
			}
		}
		
		String texto = "Impresión de firmas finalizada correctamente. Los archivos de salida fueron guardados en la ruta: " + this.outputPath;
		
		JOptionPane.showMessageDialog(null, "<html><body><p style='width:200px;'>" + texto);
		
		return true;
		
	}
	
	public MainForm() {
		
		super("SumaApp SRL");
		
		this.setResizable(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); //si cierra esta ventana, se cierra el proceso
		
		getContentPane().setLayout(new FlowLayout()); //organiza los eventos de izquierda a derecha y de arriba hacia abajo

		final JButton boton = new JButton("Ruta PDF...");
		final JButton boton2 = new JButton("Ruta Imagen...");
		final JButton boton3 = new JButton("Resetear rutas");
		final JButton boton4 = new JButton("Procesar datos...");
		
		getContentPane().add(boton);
		getContentPane().add(boton2);
		getContentPane().add(boton3);
		getContentPane().add(boton4);
		
		setSize(400,105);
		setVisible(true);
		
		setLocationRelativeTo(null); //al ser null, nos centra la pantalla del JFrame
		
		boton.addActionListener(
				new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
					FileChooser file = new FileChooser();
					pdfFile = file.getPath(1);
					
					if (pdfFile == null) return;
					
					if (!imgOk) {
						
						imgOk = true;
						JOptionPane.showMessageDialog(null, "Ruta seleccionada para los archivos PDF. Ahora seleccione la ruta de la imágen");
						
					}else {
						JOptionPane.showMessageDialog(null, "Rutas cargadas correctamente");
				}
			}
			
			});
		
		boton2.addActionListener(
				new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
					FileChooser file = new FileChooser();
					imgFile = file.getPath(0);
					
					if (imgFile == null) return;
					
					if (!pdfOk) {
						imgOk = true;
						JOptionPane.showMessageDialog(null, "Ruta seleccionada para las imágenes (firma). Ahora seleccione la ruta de los PDF");
					}else {
						JOptionPane.showMessageDialog(null, "Rutas cargadas correctamente");
					}
				}
			
			});
		
		boton3.addActionListener(
				new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
					pdfFile = null;
					imgFile = null;
					imgOk = false;
					pdfOk = false;			
					
					JOptionPane.showMessageDialog(null, "Ruta de archivos reseteada correctamente");
				}
			
			});
		
		boton4.addActionListener(
				new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				if (pdfFile == null) {
					JOptionPane.showMessageDialog(null, "Primero debes cargar la ruta donde se encuentran los ficheros PDF");
					return;
				}
				
				if (imgFile == null) {
					JOptionPane.showMessageDialog(null, "Primero debes cargar la imagen con la firma digital");
					return;
				}
				
				System.out.println(imgFile);
				
				File src = new File(pdfFile);
				
				
				if (!findAllFilesInFolder(src)) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado al intentar insertar la firma digital en los recibos de sueldo");
				}
					
				}
			
			});
		
	}

}
