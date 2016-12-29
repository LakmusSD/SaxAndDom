package parsers;

import org.w3c.dom.Attr;
import models.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Dom {

    private Stack<Book> books;

    public Dom(Stack<Book> books) {
        this.books=books;
        ParamLangXML();  //Инициализация XML
        System.out.println("Введите имя файла");
        Scanner in = new Scanner(System.in);
        String name = in.next();
        try {
            WriteParamXML(name); // запись
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }

    public void ParamLangXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e) { e.printStackTrace(); }
    }

    private Document document;
    public void WriteParamXML(String name) throws TransformerException, IOException {
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().
                    newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Element catalog = document.createElement("catalog");
        document.appendChild(catalog);

        for (Book itemBook : books) {
            Element book = document.createElement("book");
            catalog.appendChild(book);

            Attr id = document.createAttribute(itemBook.getId());
            id.setTextContent(itemBook.getId());
            book.setAttributeNode(id);

            Element author = document.createElement("author");
            author.setTextContent(itemBook.getAuthor());
            book.appendChild(author);

            Element title = document.createElement("title");
            title.setTextContent(itemBook.getTitle());
            book.appendChild(title);

            Element genre = document.createElement("genre");
            genre.setTextContent(itemBook.getGenre());
            book.appendChild(genre);

            Element price = document.createElement("price");
            price.setTextContent(itemBook.getPrice());
            book.appendChild(price);

            Element publishDate = document.createElement("publishDate");
            publishDate.setTextContent(itemBook.getPublish_date());
            book.appendChild(publishDate);

            Element description = document.createElement("description");
            description.setTextContent(itemBook.getDescription());
            book.appendChild(description);

        }


        Transformer t= TransformerFactory.newInstance().newTransformer();
        t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(name+".xml")));

    }
}