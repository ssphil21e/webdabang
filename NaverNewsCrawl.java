import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.util.*;

class NaverNewsCrawl
{
    String URL1;
    String URL2;
    String URL3;
    String search;
    int page = 1;
    int pages = 3;

    public NaverNewsCrawl(String s)
    {
        search = s;
    }

    public void run(Vector vec1, Vector vec2)
    {
        URL1 = "https://search.naver.com/search.naver?&where=news&query=";
        URL2 = "&sm=tab_pge&sort=0&photo=0&field=0&reporter_article=&pd=0&ds=&de=&docid=&nso=so:r,p:all,a:all&mynews=0&cluster_rank=24&start=";
        URL3 = "&refresh_start=0";

        try
        {
            Document doc;
            Elements elem;
            for (int i = 0; i < pages; i++, page += 10)
            {
                sleep(500);
                doc = Jsoup.connect(URL1 + search + URL2 + page + URL3).get();
                elem = doc.select(".type01 dl dt a");
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
