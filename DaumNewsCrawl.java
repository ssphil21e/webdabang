import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.util.*;

class DaumNewsCrawl
{
    String URL1;
    String URL2;
    String search;
    int page = 1;
    int pages = 3;

    public DaumNewsCrawl(String s)
    {
        search = s;
    }

    public void run(Vector vec1, Vector vec2)
    {
        URL1 = "https://search.daum.net/search?w=news&nil_search=btn&DA=PGD&enc=utf8&cluster=y&cluster_page=1&q=";
        URL2 = "&p=";

        try
        {
            Document doc;
            Elements elem;
            for (int i = 0; i < pages; i++, page++)
            {
                sleep(500);
                doc = Jsoup.connect(URL1 + search + URL2 + page).get();
                elem = doc.select("#clusterResultUL .wrap_tit.mg_tit a");
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
