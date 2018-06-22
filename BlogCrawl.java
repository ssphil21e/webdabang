import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.util.*;

class BlogCrawl
{
    String URL1;
    String URL2;
    String search;
    int page = 1;
    int pages = 3;

    public BlogCrawl(String s)
    {
        search = s;
    }

    public void run(Vector vec1, Vector vec2)
    {
        URL1 = "https://search.naver.com/search.naver?date_from=&date_option=0&date_to=&dup_remove=1&nso=&post_blogurl=&post_blogurl_without=&query=";
        URL2 = "&sm=tab_pge&srchby=all&st=sim&where=post&start=";

        try
        {
            Document doc;
            Elements elem;
            for (int i = 0; i < pages; i++, page += 10)
            {
                sleep(500);
                doc = Jsoup.connect(URL1 + search + URL2 + page).get();
                elem = doc.select(".sh_blog_top dl dt a");
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
