import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradoraDeFigurinhas {

    void cria(InputStream inputStream, String archiveName) throws Exception {
//        InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
//        InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage original = ImageIO.read(inputStream);
        int width = original.getWidth();
        int height = original.getHeight();
        int newHeight = height + 200;

        BufferedImage image = new BufferedImage(width, newHeight, Transparency.TRANSLUCENT);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.drawImage(original, 0, 0, null);

        Font font = new Font("Impact", Font.BOLD, 80);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);
        String text = "TOPZERA";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle2D = fontMetrics.getStringBounds(text, graphics);
        int textWidth = (int) rectangle2D.getWidth();
        int textXPosition = (width - textWidth) / 2;
        int textYPosition = newHeight - 100;
        graphics.drawString(text, textXPosition, textYPosition);
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(text, font, fontRenderContext);
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(textXPosition, textYPosition);
        graphics.setTransform(transform);

        BasicStroke outlineStroke = new BasicStroke(width * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        var folder = new File("figurinhas");
        folder.mkdir();
        ImageIO.write(image, "png", new File("figurinhas/".concat(archiveName)));
    }

}
