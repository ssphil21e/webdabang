import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;

public class Main
{
    JFrame frame;
    JPanel panel;
    JTextField text_box;
    JList search_list_news, search_list_blog;
    JButton search_button, link_button_blog, link_button_news;
    JLabel logo;
    JComboBox combo;
    Vector vTitle_blog, vTitle_news;
    Vector vLink_blog, vLink_news;
    String[] menu = { "Naver", "Daum" };
    
    public void run()
    {
        frame = new JFrame("project");

        panel = new JPanel();
      
        link_button_blog = new JButton(new ImageIcon("./img/blog.png"));
        link_button_blog.addActionListener(new ConnectListenerBlog());
        link_button_blog.setBorderPainted(false);

        link_button_news = new JButton(new ImageIcon("./img/news.png"));
        link_button_news.addActionListener(new ConnectListenerNews());
        link_button_news.setBorderPainted(false);

        search_button = new JButton(new ImageIcon("./img/search.png"));
        search_button.addActionListener(new SearchListener());
        search_button.setBorderPainted(false);

        text_box = new JTextField(50);
        text_box.addActionListener(new EnterListener());
        text_box.setBackground(new Color(255, 255, 233));

        search_list_blog = new JList();
        search_list_blog.setBackground(new Color(255, 255, 233));

        search_list_news = new JList();
        search_list_news.setBackground(new Color(255, 255, 233));

        combo = new JComboBox<String>(menu);

        logo = new JLabel(new ImageIcon("./img/logo.png"));
        
        panel.setBackground(new Color(30, 52, 74));
        panel.setLayout(null);
        panel.add(link_button_blog);
        panel.add(link_button_news);
        panel.add(search_button);
        panel.add(text_box);
        panel.add(search_list_blog);
        panel.add(search_list_news);
        panel.add(combo);
        panel.add(logo);
        
        text_box.setBounds(120, 30, 350, 50);
        search_button.setBounds(480, 30, 82, 58);
        link_button_blog.setBounds(225, 715, 90, 57);
        link_button_news.setBounds(815, 715, 80, 57);
        search_list_blog.setBounds(20, 100, 520, 600);
        search_list_news.setBounds(600, 100, 520, 600);
        combo.setBounds(20, 30, 100, 50);
        logo.setBounds(600, 1, 520, 139);
        
        frame.setBackground(new Color(30, 52, 74));
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1140, 825);
        frame.setResizable(false);
        frame.setVisible(true);
    }
   
    public static void main(String[] args) 
    {
        new Main().run();
    }

    class SearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String search = text_box.getText();
            int site = combo.getSelectedIndex();

            vLink_blog = new Vector();
            vTitle_blog = new Vector();
            vLink_news = new Vector();
            vTitle_news = new Vector();

            if (site == 0)
            {
                NaverBlogCrawl bc = new NaverBlogCrawl(search);
                bc.run(vTitle_blog, vLink_blog);
                search_list_blog.setListData(vTitle_blog);

                NaverNewsCrawl nc = new NaverNewsCrawl(search);
                nc.run(vTitle_news, vLink_news);
                search_list_news.setListData(vTitle_news);
            }
            else if (site == 1)
            {
                DaumBlogCrawl bc = new DaumBlogCrawl(search);
                bc.run(vTitle_blog, vLink_blog);
                search_list_blog.setListData(vTitle_blog);

                DaumNewsCrawl nc = new DaumNewsCrawl(search);
                nc.run(vTitle_news, vLink_news);
                search_list_news.setListData(vTitle_news);
            }
        }
    }

    class ConnectListenerBlog implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int index = search_list_blog.getSelectedIndex();
            String uri = vLink_blog.get(index).toString();
            try
            {
                Desktop.getDesktop().browse(new URI(uri));
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    class ConnectListenerNews implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int index = search_list_news.getSelectedIndex();
            String uri = vLink_news.get(index).toString();
            try
            {
                Desktop.getDesktop().browse(new URI(uri));
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    class EnterListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            new SearchListener().actionPerformed(event);
        }
    }
}