package padm.io.pad_m.utils;

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
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.xml.xmp.XmpWriter;

import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.fileserver.FilesStorageService;
import padm.io.pad_m.service.DocService;

@Service
public class PDFHandler {
	private final Path root = Paths.get("./uploads");

	@Autowired
	private FilesStorageService storageService;

	@Autowired
	private DocService docService;

	// @Value("${path.files.upload}")
	private String pdfDir = "documentos";
	private String pdfVerify = "verify";
	String dest = "";
	

	// Método para gerar o hash de um arquivo
	public String generateFileHash(Doc doc, Usuario author, String algorithm)
			throws NoSuchAlgorithmException, IOException, DocumentException {
		dest = root.resolve(pdfDir) + "/" + FilenameUtils.getBaseName(root.resolve(pdfDir) + "/" + doc.getHashdoc())
				+ "_assinado." + FilenameUtils.getExtension(doc.getHashdoc());
		MessageDigest digest = MessageDigest.getInstance(algorithm);

		PdfReader reader = new PdfReader(root.resolve(pdfDir) + "/" + doc.getHashdoc());
		int numpages = reader.getNumberOfPages();
		Rectangle pageSize = reader.getPageSize(numpages);
		float pageX = pageSize.getRight() - 90;

		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
		HashMap<String, String> hMap = reader.getInfo();
		//hMap.put("Title", "TRE-AMAZONAS/E-Signify");
		//hMap.put("Subject", "Documento assinado eletrônicamente");
		// hMap.put("Keywords", uuid.toString().toUpperCase());
		//hMap.put("Creator", "eSignify");
		// hMap.put("Author", author.getNome());

		// Image image = Image.getInstance(STAMP);
		// image.scalePercent(35);
		// image.setAbsolutePosition(pageX, 0);
		// PdfContentByte over = stamper.getOverContent(numpages);
		// over.addImage(image);
		stamper.setMoreInfo(hMap);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XmpWriter xmp = new XmpWriter(baos, hMap);
		xmp.close();
		stamper.setXmpMetadata(baos.toByteArray());

		// INSERINDO UM TEXTO INFERIOR ESQUERDO
		PdfContentByte canva = stamper.getOverContent(numpages);
		ColumnText.showTextAligned(canva, Element.ALIGN_LEFT,new Phrase("[Assinado por " + author.getServidorId().getNome() + "]", FontFactory.getFont(FontFactory.COURIER, 9, new BaseColor(0xFF, 0x00, 0x00))),
						10, 10, 0);        

		String[] parts = doc.getHashdoc().split("\\.");
		doc.setHashdoc(parts[0] + "_assinado." + FilenameUtils.getExtension(doc.getHashdoc()));

		docService.save(doc);

		stamper.close();
		reader.close();

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
