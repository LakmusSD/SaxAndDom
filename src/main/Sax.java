package main;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

public class Sax extends DefaultHandler {
    private Stack<Book> bookList = new Stack<>();
    private String currentTag;
    private Attributes attr;

    public Stack<Book> getBookList() {
        return bookList;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Начало разбора документа!");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Разбор документа окончен!");
        new Main().setStack(bookList);//передаем список книг в мейн чтобы потом передать его в Dom
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTag = qName;
        System.out.println("Тег: "+qName);
        if(qName.equals("book")) {
            System.out.println("id книги " + attributes.getValue("id"));
            bookList.add(new Book());
        }
        attr=attributes;

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Тег разобран: "+qName);
    }

    @Override
    public void characters(char[] c, int start, int length) throws SAXException {
        switch (currentTag) {
            case "book": {
                bookList.peek().setId(attr.getValue("id"));
                break;
            }
            case "author": {
                bookList.peek().setAuthor(new String(c, start, length));
                break;
            }
            case "title": {
                bookList.peek().setTitle(new String(c, start, length));
                break;
            }
            case "genre": {
                bookList.peek().setGenre(new String(c, start, length));
                break;
            }
            case "price": {
                bookList.peek().setPrice(new String(c, start, length));
                break;
            }
            case "publish_date": {
                bookList.peek().setPublish_date(new String(c, start, length));
                break;
            }
            case "description": {
                bookList.peek().setDescription(new String(c, start, length));
                break;
            }
            default: {
                System.out.println("I don't know about this tag");
            }
        }
    }

}
