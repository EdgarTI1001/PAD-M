package padm.io.pad_m.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.xml.xmp.XmpWriter;

import padm.io.pad_m.domain.Usuario;

@Service
public class PDFHandler {
	 private final Path root = Paths.get("./uploads");
	//@Value("${path.files.stamp}")
	private String STAMP;
	
	//@Value("${path.files.upload}")
	private String pdfDir = "documentos";
	
	/*privatee static PDFHandler uniqueInstance;
	 * private PDFHandler() { }
	 * 
	 * public static synchronized PDFHandler getInstance() { if (uniqueInstance ==
	 * null) uniqueInstance = new PDFHandler(); return uniqueInstance; }
	 */
	
	
	public String InsertStamp(String pdfFile, Usuario author) {
		String dest = "";
		//String src = pdfDir + "/" + pdfFile;
		Path file = root.resolve(pdfDir).resolve(pdfFile);
		//System.out.println(src);
		try {
			
			//RENOMEANDO O ARQUIVO
	    	dest = file + "/" + FilenameUtils.getBaseName(file.toString()) +"-"+ LocalDateTime.now().getYear() + "-"
	    			+ LocalDateTime.now().getMonthValue() + "-" + LocalDateTime.now().getDayOfMonth()
	    			+ "-" + System.currentTimeMillis() + "-ass." + FilenameUtils.getExtension(pdfFile);
	    	//System.out.println("Criando arquivos = " + dest);
	        PdfReader reader = new PdfReader(file.toString());
	        int numpages = reader.getNumberOfPages();
	        Rectangle pageSize = reader.getPageSize(numpages);
	        float pageX = pageSize.getRight() - 90;
	        //System.out.println(pageX + " , " + pageSize.getBottom());
	          
	        // INSERINDO METADADOS PARA VERIFICAÇÃO DO HASH
	          PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(file.toString() ));
	          HashMap<String, String> hMap = reader.getInfo();          
	          hMap.put("Title", "TRE-AMAZONAS/E-Signify");
	          hMap.put("Subject", "Documento assinado eletrônicamente");
	         // hMap.put("Keywords", uuid.toString().toUpperCase());
	          hMap.put("Creator", "eSignify");
	          hMap.put("Author", author.getNome());
	          
	        //Image image = Image.getInstance(STAMP);
	       // image.scalePercent(35);
	       // image.setAbsolutePosition(pageX, 0);
	       // PdfContentByte over = stamper.getOverContent(numpages);
	       // over.addImage(image);
	        stamper.setMoreInfo(hMap);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        XmpWriter xmp = new XmpWriter(baos, hMap);
	        xmp.close();
	        stamper.setXmpMetadata(baos.toByteArray());
	        //INSERINDO UM TEXTO INFERIOR ESQUERDO
	        PdfContentByte canva = stamper.getOverContent(numpages);
	        ColumnText.showTextAligned(canva, Element.ALIGN_LEFT, new Phrase("[Assinado por " + author + "]",FontFactory.getFont(FontFactory.COURIER,9,new BaseColor(0xFF, 0x00, 0x00))),10,10,90);
	        //new Phrase("Assinado por 015697172275")
	        //Phrase x = new Phrase("Ass",FontFactory.getFont(FontFactory.COURIER,9,new BaseColor(0xFF, 0x00, 0x00)));
	        stamper.close();
	        reader.close();
	        //readMetaDados(dest);
      } catch (Exception e) {
    	  e.printStackTrace();
    	  throw new RuntimeException("Ocorreu um erro: " + e.getMessage());
	    }
		return dest;
   }
	
	 public static String bytesToHex(byte[] bytes) {
	        StringBuilder result = new StringBuilder();
	        for (byte aByte : bytes) {
	            result.append(String.format("%02x", aByte));
	        }
	        return result.toString();
	    }

	    public boolean verifyHash(String calculatedHash, String expectedHash) {
	        // Comparando o hash calculado com o hash previamente armazenado
	        return calculatedHash.equals(expectedHash);
	    }

	    
	public String getIDMetaDados(String arqPDF) {
		String idFile = "";
		try {
			PdfReader reader = new PdfReader(arqPDF);
			Map<String, String> info = reader.getInfo();
			idFile = info.get("Keywords");
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao ler metadados. Erro: " + e.getMessage());
		}
		return idFile;
		//System.out.println("Verificado metadados " + arqPDF);
		//byte[] xmp = reader.getMetadata();
		//String xmpString = new String(xmp, StandardCharsets.UTF_8);
		//System.out.println(xmpString);
	}
	
	public Map<String, String> getMetaDado(String pdfFile) {
		//String idFile="";
		Map<String, String> info;
		try {
			PdfReader reader = new PdfReader(pdfFile);
			info = reader.getInfo();
			//idFile = info.get("Keywords");
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro: " + e.getMessage());
		}
		return info;
	}

	
	public String generateHash(String pdfFile) {
		String hexHash = "";
		try {
            byte[] fileHash = calculateSHA256(pdfFile);
            hexHash = bytesToHex(fileHash);

            //System.out.println("SHA-256 Hash: " + hexHash);
            // Exemplo de verificação
            //String expectedHash = "e42ecb19e83a158dcdd5e00e46dc20ec674f81bb499ac0f646ffb1fe8b4976dd"; // Substitua pelo hash previamente calculado
            //boolean isHashMatch = verifyHash(hexHash, expectedHash);
            //System.out.println("Is hash valid? " + isHashMatch);
        } catch (IOException | NoSuchAlgorithmException e) {
        	throw new RuntimeException("Ocorreu um erro ao gerar hash do arquivo. Erro: " + e.getMessage());
        }
		return hexHash;
	}

	
	@SuppressWarnings("unused")
    public static byte[] calculateSHA256(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] buffer = new byte[8192];
		int bytesRead;
        try (DigestInputStream digestInputStream = new DigestInputStream(new FileInputStream(filePath), digest)) {
            while ((bytesRead = digestInputStream.read(buffer)) != -1) {
                // Reading the file updates the digest automatically
            }
        }
        return digest.digest();  // Obtain the hash
    }

	public static void main(String[] args) {
		//String src = "/home/mviana/temp/file01.pdf";
	  	//String IMG = "/opt/db00/verified.png";
	  	
	  	//PDFHandler pdfUtils = PDFHandler.getInstance();
	  	//pdfUtils.InsertStamp(src);
	  	
	  	
    }
	
	
	
}
