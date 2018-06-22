import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.util.*;

class DaumBlogCrawl
{
    String URL1;
    String URL2;
    String URL3;
    String search;
    int page = 1;
    int pages = 3;

    public DaumBlogCrawl(String s)
    {
        search = s;
    }

    public void run(Vector vec1, Vector vec2)
    {
        URL1 = "https://search.daum.net/search?w=blog&nil_search=btn&DA=PGD&enc=utf8&q=";
        URL2 = "&page=";
        URL3 = "&m=board";
        try
        {
            Document doc;
            Elements elem;
            for (int i = 0; i < pages; i++, page++)
            {
                sleep(500);
                doc = Jsoup.connect(URL1 + search + URL2 + page + URL3).get();
                elem = doc.select("#blogColl .wrap_tit.mg_tit a");
                for (Element a : elem)
                {
                    vec1.addElement(a.text());
                    vec2.addElement(a.attr("href"));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void sleep(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
