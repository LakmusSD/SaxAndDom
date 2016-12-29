package main;

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

    private DocumentBuilder builder;
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
        try { builder = factory.newDocumentBuilder(); }
        catch (ParserConfigurationException e) { e.printStackTrace(); }
    }

    public void WriteParamXML(String name) throws TransformerException, IOException {
        Document doc=builder.newDocument();
        Element RootElement=doc.createElement("catalog");
        for(Book book : books) {
            Element Book=doc.createElement("book");
            Book.setAttribute("id",book.getId());
            RootElement.appendChild(Book);
            Element Author = doc.createElement("author");
            Author.appendChild(doc.createTextNode(book.getAuthor()));
            Book.appendChild(Author);
            Element Title = doc.createElement("title");
            Author.appendChild(doc.createTextNode(book.getTitle()));
            Book.appendChild(Title);
            Element Genre = doc.createElement("genre");
            Genre.appendChild(doc.createTextNode(book.getGenre()));
            Book.appendChild(Genre);
            Element Price = doc.createElement("price");
            Genre.appendChild(doc.createTextNode(book.getPrice()));
            Book.appendChild(Price);
            Element Publish_date = doc.createElement("publish_date");
            Genre.appendChild(doc.createTextNode(book.getPublish_date()));
            Book.appendChild(Publish_date);
            Element Description = doc.createElement("description");
            Genre.appendChild(doc.createTextNode(book.getDescription()));
            Book.appendChild(Description);
        }
        doc.appendChild(RootElement);

        Transformer t= TransformerFactory.newInstance().newTransformer();
        t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(name+".xml")));

    }
}