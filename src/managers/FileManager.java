package managers;

import classes.*;
import exceptions.InvalidInputException;
import exceptions.RootException;
import java.time.LocalDate;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Управляет чтением и записью коллекции из/в XML-файл.
 */
public class FileManager {

    /**
     * Читает данные коллекции из XML-файла и добавляет их в коллекцию.
     *
     * @param path Путь к файлу.
     * @throws ParserConfigurationException Если возникает ошибка конфигурации парсера.
     * @throws IOException                  Если возникает ошибка ввода-вывода.
     * @throws SAXException                 Если возникает ошибка при парсинге XML.
     * @throws RootException                Если возникает ошибка при чтении корневого элемента.
     * @throws InvalidInputException        Если ввод некорректен.
     */
    public static void read(String path) throws ParserConfigurationException, IOException, SAXException, RootException, InvalidInputException {


        StringBuilder text = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
            return;
        }
        if (text.isEmpty()){
            System.out.println("No element to add, your collection is clear");

        }

        InputSource in = new InputSource(new StringReader(text.toString()));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(in);

        NodeList productElements = document.getDocumentElement().getElementsByTagName("Product");
        if (productElements.getLength() == 0){
            System.out.println("No element to add, your collection is clear");

        } else {
            for (int i = 0; i < productElements.getLength(); i++) {
                Node products = productElements.item(i);

                NamedNodeMap attributes =  products.getAttributes();
                Product product1 = new Product(Integer.parseInt(attributes.getNamedItem("id").getNodeValue()));
                product1.setName(attributes.getNamedItem("name").getNodeValue());
                product1.setCoordinates(new Coordinates(
                        Long.parseLong(attributes.getNamedItem("x").getNodeValue()),
                        Integer.parseInt(attributes.getNamedItem("y").getNodeValue())
                ));
                product1.setCreationDate(LocalDate.parse(attributes.getNamedItem("creationDate").getNodeValue()));
                product1.setPrice(Integer.parseInt(attributes.getNamedItem("price").getNodeValue()));
                product1.setUnitOfMeasure(UnitOfMeasure.valueOf(attributes.getNamedItem("unit_of_measure").getNodeValue()));
                product1.setManufacturer(new Organization(
                        Long.parseLong(attributes.getNamedItem("org_id").getNodeValue()),
                        String.valueOf(attributes.getNamedItem("org_name").getNodeValue()),
                        String.valueOf(attributes.getNamedItem("org_full_name").getNodeValue()),
                        OrganizationType.valueOf(attributes.getNamedItem("org_type").getNodeValue())
                ));
                CollectionManager.add(product1);

            }
        }

    }

    /**
     * Записывает данные коллекции в XML-файл.
     *
     * @param path Путь к файлу.
     * @throws IOException   Если возникает ошибка ввода-вывода.
     * @throws RootException Если возникает ошибка при записи корневого элемента.
     */
    public static void write(String path) throws IOException, RootException {

        File file = new File(path);

        StringBuilder xml = new StringBuilder("""
                <?xml version="1.0" encoding="UTF-8" ?>

                <collection>
                \t<Products>
                """);

        LinkedList<Product> queue = CollectionManager.getCollection();
        for (Product product : queue) {
            xml.append("\t\t<Product ");
            xml.append(product.toXML()).append("/>\n");
        }

        xml.append("\t</Products>\n" + "</collection>");

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path));
        bufferedOutputStream.write(xml.toString().getBytes());
        bufferedOutputStream.close();

    }


}

