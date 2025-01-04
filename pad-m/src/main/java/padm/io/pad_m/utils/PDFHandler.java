package padm.io.pad_m.utils;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.xml.xmp.XmpWriter;

import padm.io.pad_m.domain.Assinador;
import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.fileserver.FilesStorageService;
import padm.io.pad_m.service.AssinadorService;
import padm.io.pad_m.service.DocService;

@Service
public class PDFHandler {
	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final Path root = Paths.get("./uploads");

	@Autowired
	private FilesStorageService storageService;

	@Autowired
	private DocService docService;
	
	@Autowired
	private AssinadorService assinadorService;

	// @Value("${path.files.upload}")
	private String pdfDir = "documentos";
	private String pdfVerify = "verify";
	String dest = "";
	

	// Método para gerar o hash de um arquivo
	public String generateFileHash(Doc doc, Usuario author, String algorithm)
			throws NoSuchAlgorithmException, IOException, DocumentException {
		dest = root.resolve(pdfDir) + "/" + FilenameUtils.getBaseName(root.resolve(pdfDir) + "/" + doc.getHashdoc())
		+ "." + FilenameUtils.getExtension(doc.getHashdoc());
		MessageDigest digest = MessageDigest.getInstance(algorithm);		
		
		/**************************************************/   
	     
		PDDocument document = PDDocument.load(new File(dest));
		int numpages = 0; 
		//Verificar se esse doc ja foi assinado alguma vez		
		Optional<Assinador> assinador = assinadorService.findFirstByDoc_id(doc.getId());
		
		PDPageContentStream contentStream = null;
		
		if(assinador.isPresent()){			
			 PDPage lastPage = document.getPage(document.getNumberOfPages() - 1);
			 contentStream = new PDPageContentStream(document, lastPage, PDPageContentStream.AppendMode.APPEND, true, true);
		}else{			
			PDPage newPage = new PDPage();
	        document.addPage(newPage);
			contentStream = new PDPageContentStream(document, newPage, PDPageContentStream.AppendMode.APPEND, true, true);
		}		
		 
		long total_assinaturas = assinadorService.countByDoc_id(doc.getId());
		
		// Set font and size
        contentStream.setFont(PDType1Font.COURIER, 9);    	
    	
        float margin = 36; // Margin from the bottom and left
        float x = margin;
        float y = 0;
        if(total_assinaturas == 0){
        	numpages = document.getNumberOfPages();
        	y=  document.getPage(numpages).getMediaBox().getHeight() - 36; // y-coordinate from the top; // Y position from the bottom of the page
        
        }        	
        else{
        	numpages = document.getNumberOfPages() - 1;        	
        	y = document.getPage(numpages).getMediaBox().getHeight() - (36 + (total_assinaturas * 20)); // y-coordinate from the top; // Y position from the bottom of the page
        }
        	
      

        String dataFormatada = LocalDateTime.now().format(parser);
        // Write the first paragraph
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.setNonStrokingColor(Color.RED);
        contentStream.showText("[Assinado por " + author.getServidorId().getNome() + "] Em : " + dataFormatada);
        contentStream.endText();        


        // Close the content stream
        contentStream.close(); 
        
        document.save(dest);
        document.close();

		try (FileInputStream fis = new FileInputStream(new File(dest))) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = fis.read(buffer)) != -1) {
				digest.update(buffer, 0, bytesRead);
			}
		}

		byte[] hashBytes = digest.digest();
		StringBuilder hashString = new StringBuilder();
		for (byte b : hashBytes) {
			hashString.append(String.format("%02x", b));
		}
		return hashString.toString();
	}

	public boolean isFileUnchanged(MultipartFile file, String expectedHash, String algorithm) {
		try {
			String actualHash = generateVerificacaoFileHash(file, algorithm);
			return actualHash.equalsIgnoreCase(expectedHash); // Comparação do hash gerado com o esperado
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Método para gerar o hash de um arquivo
	public String generateVerificacaoFileHash(MultipartFile file, String algorithm)
			throws NoSuchAlgorithmException, IOException {
		String[] ext = file.getContentType().split("/");
		dest = root.resolve(pdfVerify) + "/"
				+ FilenameUtils.getBaseName(root.resolve(pdfVerify) + "/" + file.getOriginalFilename()) + "." + ext[1];		
		MessageDigest digest = MessageDigest.getInstance(algorithm);

		try (FileInputStream fis = new FileInputStream(new File(dest))) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = fis.read(buffer)) != -1) {
				digest.update(buffer, 0, bytesRead);
			}
		}

		byte[] hashBytes = digest.digest();
		StringBuilder hashString = new StringBuilder();
		for (byte b : hashBytes) {
			hashString.append(String.format("%02x", b));
		}
		return hashString.toString();
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
		
	}

	public Map<String, String> getMetaDado(String pdfFile) {
		// String idFile="";
		Map<String, String> info;
		try {
			PdfReader reader = new PdfReader(pdfFile);
			info = reader.getInfo();
			// idFile = info.get("Keywords");
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

			// System.out.println("SHA-256 Hash: " + hexHash);
			// Exemplo de verificação
			// String expectedHash =
			// "e42ecb19e83a158dcdd5e00e46dc20ec674f81bb499ac0f646ffb1fe8b4976dd"; //
			// Substitua pelo hash previamente calculado
			// boolean isHashMatch = verifyHash(hexHash, expectedHash);
			// System.out.println("Is hash valid? " + isHashMatch);
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			// throw new RuntimeException("Ocorreu um erro ao gerar hash do arquivo. Erro: "
			// + e.getMessage());
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
		return digest.digest(); // Obtain the hash
	}

	public static void main(String[] args) {
		// String src = "/home/mviana/temp/file01.pdf";
		// String IMG = "/opt/db00/verified.png";

		// PDFHandler pdfUtils = PDFHandler.getInstance();
		// pdfUtils.InsertStamp(src);

	}

}
