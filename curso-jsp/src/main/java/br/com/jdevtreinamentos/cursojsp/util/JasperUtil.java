package br.com.jdevtreinamentos.cursojsp.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class JasperUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public byte[] gerarRelatorioPdf(List<?> dados, String nomeRelatorio, ServletContext context) {
		
		try {
			JRBeanCollectionDataSource dadosJasper = new JRBeanCollectionDataSource(dados);
			
			String caminho = context.getRealPath( "src" + File.separator + "resources" + File.separator + "relatorios")+ File.separator + nomeRelatorio + ".jasper"; 
			
			caminho = caminho.replace("\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1", "").replace("\\wtpwebapps", "");
			
			JasperPrint impressor = JasperFillManager.fillReport(caminho, new HashMap<String, Object>(), dadosJasper);
			
			return JasperExportManager.exportReportToPdf(impressor);
			
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	
	public byte[] gerarRelatorioPdf(List<?> dados, String nomeRelatorio, Map<String, Object> params, ServletContext context) {
			
			try {
				JRBeanCollectionDataSource dadosJasper = new JRBeanCollectionDataSource(dados);
				
				String caminho = context.getRealPath( "src" + File.separator + "resources" + File.separator + "relatorios")+ File.separator + nomeRelatorio + ".jasper"; 
				
				caminho = caminho.replace("\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1", "").replace("\\wtpwebapps", "");
				
				JasperPrint impressor = JasperFillManager.fillReport(caminho, params, dadosJasper);
				
				return JasperExportManager.exportReportToPdf(impressor);
				
			} catch (JRException e) {
				e.printStackTrace();
				return null;
			}
			
	}
	
	
	public byte[] gerarRelatorioExcel(List<?> dados, String nomeRelatorio, Map<String, Object> params, ServletContext context) {
		
		try {
			JRBeanCollectionDataSource dadosJasper = new JRBeanCollectionDataSource(dados);
			
			String caminho = context.getRealPath( "src" + File.separator + "resources" + File.separator + "relatorios")+ File.separator + nomeRelatorio + ".jasper"; 
			
			caminho = caminho.replace("\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1", "").replace("\\wtpwebapps", "");
			
			JasperPrint impressor = JasperFillManager.fillReport(caminho, params, dadosJasper);
			
			/*precisa do apache POI*/
			JRExporter exporter = new JRXlsExporter();
			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressor);
			
			ByteArrayOutputStream saida = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);
			
			exporter.exportReport();
			
			return saida.toByteArray();
			
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}
		
}
}
