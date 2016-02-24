package com.ethereal.team.kochu.DefinitionFetcher;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Khyrul Bashar
 */
public class DefinitionFetcher
{
    private String word;

    public DefinitionFetcher(String word)
    {
        this.word = word;
    }

    public ArrayList<Definition> fetch()
    {
        ArrayList<Definition> definitions = new ArrayList<>();
        try
        {
            Document document = Jsoup.connect("http://www.bing.com/search?q=define+"+word).get();
            Log.v("Document",document.toString());
            if (hasChildrenOfClass(document.getElementById("b_content"), "dc_pds"))
            {
                Elements elements = document.getElementsByClass("dc_pdm");
                for (Element element : elements)
                {
                    Elements dc_st = element.getElementsByClass("dc_st");
                    String partsOfSpeech = dc_st.text();

                    Elements dc_pms = element.getElementsByClass("dc_pm");
                    for (Element dc_pm : dc_pms)
                    {
                        Definition definition = new Definition(partsOfSpeech);
                        definition.setDefinition(dc_pm.getElementsByClass("dc_mn").get(0).text());
                        if (hasChildrenOfClass(dc_pm, "b_demoteText"))
                        {
                            definition.setExample(dc_pm.getElementsByClass("b_demoteText").get(0).ownText());
                        }
                        if (hasChildrenOfClass(dc_pm, "dc_synb"))
                        {
                            List<String> synonyms =new ArrayList<>();

                            for (Element a : dc_pm.getElementsByTag("a"))
                            {
                                String synonym = a.ownText();
                                if (!synonym.equals("") && !synonym.equals("[more]"))
                                {
                                    synonyms.add(synonym);
                                }
                            }
                            definition.setSynonyms(synonyms);
                        }
                        definitions.add(definition);
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return definitions;
    }

    private boolean hasChildrenOfClass(Element element, String className)
    {
        if (element.getElementsByClass(className).size()>0)
        {
            return true;
        }
        return false;
    }
}
