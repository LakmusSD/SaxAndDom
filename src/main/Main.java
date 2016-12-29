package main;

import org.xml.sax.SAXException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

public class Main {
    static Stack<Book> bookStack;

    void setStack(Stack<Book> bookStack) {
        this.bookStack = bookStack;
    }

    static void menu() {
        System.out.println("1 - SAX reading and DOM writing");
        System.out.println("0 - exit");

        Scanner scanner = new Scanner(System.in);
        int menu = scanner.nextInt();
        switch (menu) {
            case 1: {
                saxParse();
                Dom dom = new Dom(bookStack);
                break;
            }
            case 0: {
                System.exit(0);
            }
            default: {
                System.exit(0);
            }
        }
    }

    static void saxParse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        try (InputStream xmlData = new FileInputStream("task.xml")) {
            parser = factory.newSAXParser();
            parser.parse(xmlData, new Sax());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        menu();
    }
}
