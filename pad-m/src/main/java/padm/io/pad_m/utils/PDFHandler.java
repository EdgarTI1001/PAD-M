package padm.io.pad_m.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;

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
	private String imagensDir = "imagens";
	private String pdfVerify = "verify";
	String dest = "";

	// Método para gerar o hash de um arquivo
	public String generateFileHash(Doc doc, Usuario author, String algorithm)
			throws NoSuchAlgorithmException, IOException, DocumentException, WriterException {
		dest = root.resolve(pdfDir) + "/" + FilenameUtils.getBaseName(root.resolve(pdfDir) + "/" + doc.getHashdoc())
				+ "." + FilenameUtils.getExtension(doc.getHashdoc());
		MessageDigest digest = MessageDigest.getInstance(algorithm);

		/**************************************************/

		PDDocument document = PDDocument.load(new File(dest));
		int numpages = 0;

		String text = new PDFTextStripper().getText(document);// Verificando o ultimo Paragrafo

		String[] paragrafos = text.split("\\n\\n"); // Divide por parágrafos (duas quebras de linha)

		// Encontrar o último parágrafo não vazio
		String lastParagraph = "";
		for (int i = paragrafos.length - 1; i >= 0; i--) {
			if (!paragrafos[i].trim().isEmpty()) {
				lastParagraph = paragrafos[i];
				break;
			}
		}

		// Conteúdo do QR Code
		 String qrText = "localhost:8080/documentos/asinaturas"+ doc.getId();
		 String textoAoLado = "Assinaturas";
		 
		// Carregar a imagem do selo/assinatura (ajuste o caminho!)
		 BufferedImage seloImage = ImageIO.read(new File(root.resolve(imagensDir) +"/" + "selo_assinatura.jpg"));
		 PDImageXObject seloPDImage = LosslessFactory.createFromImage(document, seloImage);

		 // Configurações da imagem e texto
		 float seloWidth = 65; // Largura da imagem no PDF
		 float seloHeight = 40; // Altura da imagem no PDF
		 float seloMargin = 10; // Margem do canto
		 float seloFontSize = 10; 
		 
		// Tamanho do QR Code em pixels
		int qrPixelSize = 100;

		// Gera o QR Code usando ZXing	
		BitMatrix bitMatrix = new MultiFormatWriter().encode(qrText, BarcodeFormat.QR_CODE, qrPixelSize, qrPixelSize);
		BufferedImage qrBufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

		 // Configurações em pontos (1 polegada = 72 pontos)
	        float qrWidth = 40;     // Largura do QR Code no PDF
	        float qrHeight = 40;    // Altura do QR Code no PDF
	        float margin = 10;       // Margem do canto da página
	        float fontSize = 12;     // Tamanho da fonte para o texto

		// Itera por todas as páginas do documento
		for (PDPage page : document.getPages()) {
		    float pageWidth = page.getMediaBox().getWidth();
		    // Define as coordenadas do QR Code (canto inferior direito)
            float qrX = pageWidth - margin - qrWidth;
            float qrY = margin;
            
            // Posiciona o texto à esquerda do QR Code
            // Aqui, 'textX' é calculado de forma que o texto fique a uma distância fixa do QR Code.
            float textX = qrX - 150; // ajuste esse valor conforme o comprimento do seu texto
            // Alinha verticalmente o texto ao centro do QR Code
            float textY = qrY + (qrHeight / 2) - (fontSize / 2);
		    
			// Abre um PDPageContentStream no modo APPEND para não sobrescrever o conteúdo existente
			PDPageContentStream contentStream = new PDPageContentStream(document, page,
					PDPageContentStream.AppendMode.APPEND, true, true);

			 // POSICIONANDO IMAGEM NO CANTO INFERIOR ESQUERDO
		    float seloX = margin; 
		    float seloY = margin;
		    contentStream.drawImage(seloPDImage, seloX, seloY, seloWidth, seloHeight);

		    // POSICIONANDO TEXTO AO LADO DA IMAGEM
		    float textXSelo = seloX + seloWidth + 5; // 5pts de espaçamento
		    float textYSelo = seloY + (seloHeight / 2) + 3; // Ajuste fino para alinhamento vertical
		    
			String dataFormatada = LocalDateTime.now().format(parser);
			// Write the first paragraph
			contentStream.setFont(PDType1Font.HELVETICA, 12);
			contentStream.beginText();
			contentStream.newLineAtOffset(textXSelo, textYSelo);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.showText("[Assinado Eletronicamente por " + author.getServidorId().getNome()
					+ " e outros ] Em : " + dataFormatada + " ás " + LocalDateTime.now().getHour() + ":"
					+ LocalDateTime.now().getMinute());
			contentStream.endText();
			
			// Converte a imagem gerada do QR Code para PDImageXObject
            PDImageXObject qrImage = LosslessFactory.createFromImage(document, qrBufferedImage);

            // Adiciona o QR Code no canto inferior direito
            contentStream.drawImage(qrImage, qrX, qrY, qrWidth, qrHeight);
            
			// Close the content stream
			contentStream.close();
		}



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
